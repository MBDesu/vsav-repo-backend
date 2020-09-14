package org.jordyn.vsav.response;

import java.util.List;

import org.jordyn.vsav.db.domain.Video;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response POJO for {@link org.jordyn.vsav.service.api.VideoService}
 * @author jordyn
 *
 */
public class RetrieveVideosResponse {

	@JsonProperty
	private long totalResults;

	@JsonProperty
	private List<Video> videos;

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(final long totalResults) {
		this.totalResults = totalResults;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(final List<Video> videos) {
		this.videos = videos;
	}



}
