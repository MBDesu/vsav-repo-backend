package org.jordyn.vsav.service.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import twitter4j.Twitter;

/**
 * Interface that defines the Twitter service
 * @author jordyn
 *
 */
@Service
public interface TwitterService {

	Twitter getTwitter();
	ResponseEntity<String> signIn();

}
