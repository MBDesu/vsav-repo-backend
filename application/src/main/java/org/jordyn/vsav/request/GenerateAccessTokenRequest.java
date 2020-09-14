package org.jordyn.vsav.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to generate a Twitter access token
 * @author jordyn
 *
 */
public class GenerateAccessTokenRequest {

	@NotNull
	@ApiModelProperty(value = "The OAuth verifier returned from Twitter")
	private String oauthVerifier;

	@NotNull
	@ApiModelProperty(value = "The token from the Twitter callback")
	private String token;

	@NotNull
	@ApiModelProperty(value = "The token secret from the Twitter callback")
	private String tokenSecret;

	public String getOauthVerifier() {
		return oauthVerifier;
	}

	public void setOauthVerifier(final String oauthVerifier) {
		this.oauthVerifier = oauthVerifier;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(final String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

}
