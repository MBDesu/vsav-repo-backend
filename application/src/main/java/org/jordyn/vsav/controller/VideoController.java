package org.jordyn.vsav.controller;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.jordyn.vsav.contract.MatchContract;
import org.jordyn.vsav.contract.VideoContract;
import org.jordyn.vsav.contract.YoutubeContract;
import org.jordyn.vsav.db.domain.Match;
import org.jordyn.vsav.db.domain.Video;
import org.jordyn.vsav.request.AddMatchRequest;
import org.jordyn.vsav.request.AddVideoRequest;
import org.jordyn.vsav.request.DeleteMatchRequest;
import org.jordyn.vsav.request.PaginatedApiRequest;
import org.jordyn.vsav.request.RetrieveMatchRequest;
import org.jordyn.vsav.request.RetrieveMatchesRequest;
import org.jordyn.vsav.request.RetrieveVideoFromMatchRequest;
import org.jordyn.vsav.request.RetrieveVideoRequest;
import org.jordyn.vsav.request.RetrieveVideosByPlayerRequest;
import org.jordyn.vsav.request.UpdateMatchRequest;
import org.jordyn.vsav.response.RetrieveMatchesResponse;
import org.jordyn.vsav.response.RetrieveVideosResponse;
import org.jordyn.vsav.service.api.VideoService;
import org.jordyn.vsav.service.api.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.youtube.model.VideoListResponse;

/**
 * REST controller that contains endpoints for {@link org.jordyn.vsav.service.api.VideoService}
 * 
 * @author jordyn
 */
@RestController
public class VideoController {

	// TODO: break this controller up

	@Autowired
	@Qualifier("videoServiceImpl")
	private VideoService videoService;

	@Autowired
	@Qualifier("youtubeServiceImpl")
	private YoutubeService youtubeService;

	@RequestMapping(method = RequestMethod.POST, value = "/RetrieveVideos")
	public ResponseEntity<RetrieveVideosResponse> retrieveVideos(final @Valid @RequestBody PaginatedApiRequest request) {
		VideoContract contract = new VideoContract();
		contract.setPage(request.getPage());
		contract.setPageSize(request.getPageSize());
		return videoService.retrieveVideos(contract);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/RetrieveMatches")
	public ResponseEntity<RetrieveMatchesResponse> retrieveMatches(final @Valid @RequestBody RetrieveMatchesRequest request) {
		VideoContract contract = new VideoContract();
		contract.setPage(request.getPage());
		contract.setPageSize(request.getPageSize());
		contract.setPlayerOneName(request.getPlayerOneName());
		contract.setPlayerTwoName(request.getPlayerTwoName());
		contract.setPlayerOneChar(request.getPlayerOneChar());
		contract.setPlayerTwoChar(request.getPlayerTwoChar());
		return videoService.retrieveMatches(contract);
	}

	@CacheEvict(value = "playerCache", allEntries = true)
	@RequestMapping(method = RequestMethod.POST, value = "/AddVideo")
	public ResponseEntity<Video> addVideo(final @Valid @RequestBody AddVideoRequest request) {
		VideoContract contract = new VideoContract();
		YoutubeContract youtubeContract = new YoutubeContract();
		contract.setPlayerOneChar(request.getPlayerOneChar());
		contract.setPlayerOneName(request.getPlayerOneName());
		contract.setPlayerTwoChar(request.getPlayerTwoChar());
		contract.setPlayerTwoName(request.getPlayerTwoName());
		contract.setYoutubeId(request.getYoutubeId());
		contract.setYoutubeTimestamp(request.getYoutubeTimestamp());
		youtubeContract.setYoutubeId(request.getYoutubeId());
		ResponseEntity<VideoListResponse> videoMetadataResponse = youtubeService.retrieveVideoMetadata(youtubeContract);
		if(videoMetadataResponse.getStatusCodeValue() == 200) {
			com.google.api.services.youtube.model.Video videoMetadata = videoMetadataResponse.getBody().getItems().get(0);
			String title = videoMetadata.getSnippet().getTitle();
			String channel = videoMetadata.getSnippet().getChannelTitle();
			String description = videoMetadata.getSnippet().getDescription();
			Date uploadDate = Date.from(Instant.parse(videoMetadata.getSnippet().getPublishedAt().toString()));
			contract.setTitle(title);
			contract.setChannel(channel);
			contract.setDescription(description);
			contract.setUploadDate(uploadDate);
		} else {
			return new ResponseEntity<Video>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		ResponseEntity<Video> response = videoService.addVideo(contract);
		if(response.getStatusCodeValue() != 200) {
			response = new ResponseEntity<Video>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@CacheEvict(value = "playerCache", allEntries = true)
	@RequestMapping(method = RequestMethod.POST, value = "/AddMatch")
	public ResponseEntity<Video> addMatchToVideo(final @Valid @RequestBody AddMatchRequest request) {
		VideoContract contract = new VideoContract();
		contract.setVideoId(request.getVideoId());
		contract.setPlayerOneChar(request.getPlayerOneChar());
		contract.setPlayerTwoChar(request.getPlayerTwoChar());
		contract.setPlayerOneName(request.getPlayerOneName());
		contract.setPlayerTwoName(request.getPlayerTwoName());
		contract.setYoutubeTimestamp(request.getYoutubeTimestamp());
		return videoService.addMatchToVideo(contract);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/RetrieveVideoFromMatch")
	public ResponseEntity<Video> retrieveVideoFromMatch(final @Valid @RequestBody RetrieveVideoFromMatchRequest request) {
		VideoContract contract = new VideoContract();
		contract.setMatchId(request.getMatchId());
		return videoService.retrieveVideoFromMatch(contract);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/RetrieveVideosByPlayer")
	public ResponseEntity<List<Video>> retrieveVideosByPlayer(final @Valid @RequestBody RetrieveVideosByPlayerRequest request) {
		VideoContract contract = new VideoContract();
		contract.setPlayerOneName(request.getPlayer());
		return videoService.retrieveVideosByPlayer(contract);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/RetrieveVideo")
	public ResponseEntity<Video> retrieveVideo(final @Valid @RequestBody RetrieveVideoRequest request) {
		VideoContract contract = new VideoContract();
		contract.setVideoId(request.getVideoId());
		return videoService.retrieveVideo(contract);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/RetrieveMatch")
	public ResponseEntity<Match> retrieveMatch(final @Valid @RequestBody RetrieveMatchRequest request) {
		MatchContract contract = new MatchContract();
		contract.setMatchId(request.getMatchId());
		return videoService.retrieveMatch(contract);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/RetrieveUniquePlayers")
	public ResponseEntity<String> retrieveUniquePlayers() {
		return videoService.retrieveUniquePlayers();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/DeleteMatch")
	public ResponseEntity<String> deleteMatch(final @Valid @RequestBody DeleteMatchRequest request) {
		MatchContract contract = new MatchContract();
		contract.setMatchId(request.getMatchId());
		return videoService.deleteMatch(contract);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/UpdateMatch")
	public ResponseEntity<String> updateMatch(final @Valid @RequestBody UpdateMatchRequest request) {
		MatchContract contract = new MatchContract();
		contract.setMatchId(request.getMatchId());
		contract.setPlayerOneName(request.getPlayerOneName());
		contract.setPlayerTwoName(request.getPlayerTwoName());
		contract.setPlayerOneChar(request.getPlayerOneChar());
		contract.setPlayerTwoChar(request.getPlayerTwoChar());
		contract.setYoutubeId(request.getYoutubeTimestamp());
		return videoService.updateMatch(contract);
	}

}
