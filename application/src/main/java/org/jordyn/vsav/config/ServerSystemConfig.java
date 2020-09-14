package org.jordyn.vsav.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration class for SSL connections, mostly
 * @author jordyn
 *
 */
@Configuration
public class ServerSystemConfig {

	@Autowired
	private Environment env;
	
	@PostConstruct
	private void configureServerSystemConfig() {
		// Comment these out before building for your local
		// System.setProperty("javax.net.ssl.trustStore", env.getProperty("server.ssl.trust-store"));
		// System.setProperty("javax.net.ssl.trustStorePassword", env.getProperty("server.ssl.trust-store-password"));
	}
	
}
