package org.jordyn.vsav.service.impl;

import org.jordyn.vsav.service.api.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Implementation of the {@link org.jordyn.vsav.service.api.TwitterService}
 * @author jordyn
 *
 */
@Service
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	private Environment env;

	@Override
	public ResponseEntity<String> signIn() {
		return null;
	}

	@Override
	public Twitter getTwitter() {
		Twitter twitter = null;

		String consumerKey = env.getProperty("twitter.api.key");
		String consumerSecret = env.getProperty("twitter.api.secret.key");

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey(consumerKey);
		builder.setOAuthConsumerSecret(consumerSecret);

		Configuration configuration = builder.build();

		TwitterFactory factory = new TwitterFactory(configuration);

		twitter = factory.getInstance();

		return twitter;
	}

}
