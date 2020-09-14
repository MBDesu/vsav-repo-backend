package org.jordyn.vsav.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to retrieve videos by one player
 * @author jordyn
 *
 */
@ApiModel
public class RetrieveVideosByPlayerRequest {

	@NotNull
	@ApiModelProperty(value = "Player to search for")
	@Size(min = 1, max = 50)
	public String player;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(final String player) {
		this.player = player;
	}

}
