package org.jordyn.vsav.service.api;

import java.util.List;

import org.jordyn.vsav.contract.MatchContract;
import org.jordyn.vsav.contract.VideoContract;
import org.jordyn.vsav.db.domain.Match;
import org.jordyn.vsav.db.domain.Video;
import org.jordyn.vsav.response.RetrieveMatchesResponse;
import org.jordyn.vsav.response.RetrieveVideosResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Interface that defines the video service
 * @author jordyn
 *
 */
@Service
public interface VideoService {

	// TODO: break this service up
	ResponseEntity<Video> addVideo(final VideoContract contract);
	ResponseEntity<Video> retrieveVideo(final VideoContract contract);
	ResponseEntity<Video> addMatchToVideo(final VideoContract contract);
	ResponseEntity<Video> retrieveVideoFromMatch(final VideoContract contract);
	ResponseEntity<List<Video>> retrieveVideosByPlayer(final VideoContract contract);
	ResponseEntity<RetrieveVideosResponse> retrieveVideos(final VideoContract contract);
	ResponseEntity<RetrieveMatchesResponse> retrieveMatches(final VideoContract contract);
	ResponseEntity<Match> retrieveMatch(final MatchContract contract);
	ResponseEntity<String> retrieveUniquePlayers();
	ResponseEntity<String> deleteMatch(final MatchContract contract);
	ResponseEntity<String> updateMatch(final MatchContract contract);

}
