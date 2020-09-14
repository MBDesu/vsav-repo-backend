package org.jordyn.vsav.service.api;

import org.jordyn.vsav.contract.YoutubeContract;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.VideoListResponse;

/**
 * Interface that defines the YouTube service
 * @author jordyn
 *
 */
@Service
public interface YoutubeService {

	public ResponseEntity<VideoListResponse> retrieveVideoMetadata(final YoutubeContract contract);

}
