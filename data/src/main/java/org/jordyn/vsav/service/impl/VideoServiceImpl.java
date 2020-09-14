package org.jordyn.vsav.service.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.jordyn.vsav.contract.MatchContract;
import org.jordyn.vsav.contract.VideoContract;
import org.jordyn.vsav.db.domain.Match;
import org.jordyn.vsav.db.domain.Players;
import org.jordyn.vsav.db.domain.Video;
import org.jordyn.vsav.exception.util.VsavRepoExceptionUtil;
import org.jordyn.vsav.response.RetrieveMatchesResponse;
import org.jordyn.vsav.response.RetrieveVideosResponse;
import org.jordyn.vsav.service.api.VideoService;
import org.jordyn.vsav.service.sql.VideoServiceSql;
import org.jordyn.vsav.service.util.VideoServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Implementation of the {@link org.jordyn.vsav.service.api.VideoService}
 * @author jordyn
 *
 */
@Service
public class VideoServiceImpl implements VideoService {

	private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

	@Autowired
	private EntityManager em;

	// TODO: convert native queries to Hibernate queries?

	@Override
	@Transactional
	public ResponseEntity<Video> addVideo(final VideoContract contract) {
		try {
			Query addVideoQuery = em.createNativeQuery(VideoServiceSql.addVideo);
			Query getVideoIdByYoutubeId = em.createNativeQuery(VideoServiceSql.getVideoIdByYoutubeId);
			Query retrieveVideoQuery = em.createNativeQuery(VideoServiceSql.retrieveVideo, Video.class);
			Query addMatchToVideoQuery = em.createNativeQuery(VideoServiceSql.addMatchToVideo);
			Query addMetadataQuery = em.createNativeQuery(VideoServiceSql.addMetadata);
			addVideoQuery.setParameter(1, contract.getYoutubeId());
			addVideoQuery.executeUpdate();
			getVideoIdByYoutubeId.setParameter(1, contract.getYoutubeId());
			contract.setVideoId(BigInteger.valueOf((int) getVideoIdByYoutubeId.getSingleResult()));
			VideoServiceUtil.populateAddMatchToVideoQueryParameters(addMatchToVideoQuery, contract);
			addMatchToVideoQuery.executeUpdate();
			VideoServiceUtil.populateAddMetadataQueryParameters(addMetadataQuery, contract);
			addMetadataQuery.executeUpdate();
			retrieveVideoQuery.setParameter(1, contract.getVideoId());
			return new ResponseEntity<Video>((Video) retrieveVideoQuery.getSingleResult(), HttpStatus.OK);
		} catch(PersistenceException e) {
			logger.error("Error adding video to database: ", e);
			throw VsavRepoExceptionUtil.convertException(e);
		}
	}

	@Override
	@CacheEvict("playerCache")
	@Transactional
	public ResponseEntity<Video> addMatchToVideo(final VideoContract contract) {
		try {
			Query addMatchToVideoQuery = em.createNativeQuery(VideoServiceSql.addMatchToVideo);
			Query retrieveVideoQuery = em.createNativeQuery(VideoServiceSql.retrieveVideo, Video.class);
			VideoServiceUtil.populateAddMatchToVideoQueryParameters(addMatchToVideoQuery, contract);
			addMatchToVideoQuery.executeUpdate();
			retrieveVideoQuery.setParameter(1, contract.getVideoId());
			return new ResponseEntity<Video>((Video) retrieveVideoQuery.getSingleResult(), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error adding match to video: ", e);
			return new ResponseEntity<Video>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Video> retrieveVideoFromMatch(final VideoContract contract) {
		try {
			Query query = em.createNativeQuery(VideoServiceSql.retrieveVideoFromMatch, Video.class);
			query.setParameter(1, contract.getMatchId());
			return new ResponseEntity<Video>((Video) query.getSingleResult(), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error retrieving video from match: ", e);
			return new ResponseEntity<Video>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Video> retrieveVideo(final VideoContract contract) {
		try {
			Query query = em.createNativeQuery(VideoServiceSql.retrieveVideo, Video.class);
			query.setParameter(1, contract.getVideoId());
			return new ResponseEntity<Video>((Video) query.getSingleResult(), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error retrieving video: ", e);
			return new ResponseEntity<Video>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<Video>> retrieveVideosByPlayer(final VideoContract contract) {
		try {
			TypedQuery<Video> query = em.createQuery(VideoServiceSql.retrieveVideosByPlayer, Video.class);
			query.setParameter(0, contract.getPlayerOneName().toLowerCase());
			query.setParameter(1, contract.getPlayerOneName().toLowerCase());
			return new ResponseEntity<List<Video>>((List<Video>) query.getResultList(), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error retrieving video by player: ", e);
			return new ResponseEntity<List<Video>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<RetrieveVideosResponse> retrieveVideos(final VideoContract contract) {
		int page = contract.getPage();
		int pageSize = contract.getPageSize();
		TypedQuery<Video> query = em.createQuery(VideoServiceSql.retrieveVideos, Video.class);
		TypedQuery<Long> totalResultsQuery = em.createQuery("select count (v.videoId) from video v", Long.class);
		query.setFirstResult(page * pageSize);
		query.setMaxResults(pageSize);
		Long totalResults = totalResultsQuery.getSingleResult();
		RetrieveVideosResponse response = new RetrieveVideosResponse();
		response.setTotalResults(totalResults);
		response.setVideos(query.getResultList());
		return new ResponseEntity<RetrieveVideosResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<RetrieveMatchesResponse> retrieveMatches(final VideoContract contract) {
		// TODO: clean and break this up
		int page = contract.getPage();
		int pageSize = contract.getPageSize();
		String p1Name = contract.getPlayerOneName();
		String p2Name = contract.getPlayerTwoName();
		boolean p1Exists = !StringUtils.isEmpty(p1Name);
		boolean p2Exists = !StringUtils.isEmpty(p2Name);
		if (p1Exists) p1Name = p1Name.toLowerCase();
		if (p2Exists) p2Name = p2Name.toLowerCase();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Match> matchesCr = cb.createQuery(Match.class);
		CriteriaQuery<Long> countCr = cb.createQuery(Long.class);
		Root<Match> root = matchesCr.from(Match.class);
		Predicate predicate = null;
		if (p1Exists && p2Exists) {
			predicate = cb.or(
					cb.and(cb.equal(root.get("playerOneNameIndex"), p1Name), cb.equal(root.get("playerTwoNameIndex"), p2Name)),
					cb.and(cb.equal(root.get("playerOneNameIndex"), p2Name), cb.equal(root.get("playerTwoNameIndex"), p1Name))
					);
		} else if (p1Exists || p2Exists) {
			final String player = p1Exists ? p1Name : p2Name;
			predicate = cb.or(cb.equal(root.get("playerOneNameIndex"), player), cb.equal(root.get("playerTwoNameIndex"), player));
		}
		if (predicate != null) {
			matchesCr.select(root).where(predicate);
			countCr.select(cb.count(countCr.from(Match.class))).where(predicate);
		} else {
			matchesCr.select(root);
			countCr.select(cb.count(countCr.from(Match.class)));
		}
		matchesCr.orderBy(cb.desc(root.get("video").get("metadata").get("uploadDate")));
		TypedQuery<Match> matchesQuery = em.createQuery(matchesCr).setFirstResult(page * pageSize).setMaxResults(pageSize);
		List<Match> matches = matchesQuery.getResultList();
		RetrieveMatchesResponse response = new RetrieveMatchesResponse();
		response.setMatches(matches);
		response.setTotalResults(em.createQuery(countCr).getSingleResult());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<Match> retrieveMatch(final MatchContract contract) {
		Query query = em.createNativeQuery(VideoServiceSql.retrieveMatch, Match.class);
		query.setParameter(1, contract.getMatchId());
		return new ResponseEntity<Match>((Match) query.getSingleResult(), HttpStatus.OK);
	}

	@Override
	@Cacheable("playerCache")
	@SuppressWarnings("unchecked")
	public ResponseEntity<String> retrieveUniquePlayers() {
		// TODO: this method is a hack
		Query query = em.createNativeQuery(VideoServiceSql.retrieveUniquePlayers);
		ObjectMapper mapper = new ObjectMapper();
		List<Object> result = query.getResultList();
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		try {
			Players players = new Players(result);
			response = new ResponseEntity<>(mapper.writeValueAsString(players), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	@Transactional
	public ResponseEntity<String> deleteMatch(final MatchContract contract) {
		Query query = em.createNativeQuery("DELETE from matches WHERE I_MTCH_ID=:matchId");
		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.OK);
		try {
			query.setParameter("matchId", contract.getMatchId());
			query.executeUpdate();
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Override
	@Transactional
	public ResponseEntity<String> updateMatch(final MatchContract contract) {
		Query query = em.createNativeQuery("UPDATE matches SET C_PLYR_ONE = :playerOneName, C_PLYR_TWO = :playerTwoName, C_CHAR_ONE = :playerOneChar, C_CHAR_TWO = :playerTwoChar WHERE I_MTCH_ID=:matchId", Match.class);
		query.setParameter("playerOneName", contract.getPlayerOneName());
		query.setParameter("playerTwoName", contract.getPlayerTwoName());
		query.setParameter("playerOneChar", contract.getPlayerOneChar());
		query.setParameter("playerTwoChar", contract.getPlayerTwoChar());
		query.setParameter("matchId", contract.getMatchId());
		query.executeUpdate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

