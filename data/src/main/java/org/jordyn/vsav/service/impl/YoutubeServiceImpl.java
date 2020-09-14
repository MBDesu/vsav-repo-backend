package org.jordyn.vsav.service.impl;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.jordyn.vsav.contract.YoutubeContract;
import org.jordyn.vsav.service.api.YoutubeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;

/**
 * Implementation of the {@link org.jordyn.vsav.service.api.YoutubeService}
 * @author jordyn
 *
 */
@Service
public class YoutubeServiceImpl implements YoutubeService {

	private static final Logger logger = LoggerFactory.getLogger(YoutubeServiceImpl.class);

	@Autowired
	private Environment env;

	@Override
	public ResponseEntity<VideoListResponse> retrieveVideoMetadata(final YoutubeContract contract) {
		String apiKey = env.getProperty("youtube.api.key");
		try {
			ConcurrentHashMap<String, String> params = new ConcurrentHashMap<>();
			params.put("part", "snippet,contentDetails,statistics");
			params.put("id", contract.getYoutubeId());
			YouTube youtube = getYoutube();
			YouTube.Videos.List videosListByIdRequest = youtube.videos().list(params.get("part").toString());
			if (params.containsKey("id") && params.get("id") != "") {
				videosListByIdRequest.setId(params.get("id").toString());
			}
			videosListByIdRequest.setKey(apiKey);
			VideoListResponse response = videosListByIdRequest.execute();
			return new ResponseEntity<VideoListResponse>(response, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error retrieving video metadata: ", e);
			return new ResponseEntity<VideoListResponse>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	private YouTube getYoutube() {
		HttpTransport httpTransport = new NetHttpTransport();
		JsonFactory jsonFactory = new JacksonFactory();
		return new YouTube.Builder(httpTransport, jsonFactory, new HttpRequestInitializer() {
			@Override
			public void initialize(final HttpRequest request) throws IOException {
				// this method intentionally left blank
			}
		}).setApplicationName(env.getProperty("youtube.app.name")).build();
	}

}
