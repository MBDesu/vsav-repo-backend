package org.jordyn.vsav.contract;

/**
 * Contract to be used with {@link org.jordyn.vsav.service.api.YoutubeService}
 * @author jordyn
 *
 */
public class YoutubeContract {

	private String youtubeId;

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(final String youtubeId) {
		this.youtubeId = youtubeId;
	}

}
