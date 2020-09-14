package org.jordyn.vsav.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jordyn.vsav.controller.helper.TwitterControllerHelper;
import org.jordyn.vsav.request.GenerateAccessTokenRequest;
import org.jordyn.vsav.service.api.TwitterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

/**
 * REST controller that contains endpoints for {@link org.jordyn.vsav.service.api.TwitterService}
 * 
 * @author jordyn
 */
@RestController
public class TwitterController extends TwitterControllerHelper {

	private static final Logger logger = LoggerFactory.getLogger(TwitterController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("twitterServiceImpl")
	private TwitterService twitterService;

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/GetToken")
	public ResponseEntity<RequestToken> getToken(final HttpServletRequest request) {
		try {
			Twitter twitter = twitterService.getTwitter();
			String callbackUrl = env.getProperty("twitter.callback.url");
			RequestToken requestToken = twitter.getOAuthRequestToken(callbackUrl);
			return new ResponseEntity<>(requestToken, HttpStatus.OK);
		} catch(TwitterException | IllegalStateException e) {
			logger.error("Error getting Twitter token:", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, value = "/GenerateAccessToken")
	public ResponseEntity<String> twitterCallback(final @Valid @RequestBody GenerateAccessTokenRequest request) {
		Twitter twitter = (Twitter) twitterService.getTwitter();
		try {
			RequestToken requestToken = new RequestToken(request.getToken(), request.getTokenSecret());
			AccessToken twitterAccessToken = twitter.getOAuthAccessToken(requestToken, request.getOauthVerifier());
			String jwt = getJwt(twitterAccessToken);
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error getting Twitter access token or generating JWT:", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
