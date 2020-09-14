package org.jordyn.vsav.request;

import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to retrieve a particular video
 * @author jordyn
 *
 */
@ApiModel
public class RetrieveVideoRequest {

	@ApiModelProperty(value = "The video ID of the video to retrieve; this is returned by any service that retrieves a video (except this one, obviously)", required = true)
	private BigInteger videoId;

	public BigInteger getVideoId() {
		return videoId;
	}

	public void setVideoId(final BigInteger videoId) {
		this.videoId = videoId;
	}

}
