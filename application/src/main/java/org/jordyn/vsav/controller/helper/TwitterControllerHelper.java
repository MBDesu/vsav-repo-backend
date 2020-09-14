package org.jordyn.vsav.controller.helper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import twitter4j.auth.AccessToken;

/**
 * Helper class for {@link org.jordyn.vsav.controller.TwitterController}
 * @author jordyn
 *
 */
public class TwitterControllerHelper {

	@Autowired
	private Environment env;

	public String getJwt(final AccessToken accessToken) {
		String secret = env.getProperty("jwt.secret");
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts
				.builder()
				.setId("vsavrepoJWT")
				.setSubject(accessToken.getScreenName())
				.claim("authorities", grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
		return new StringBuilder("{ \"jwt\": \"").append(env.getProperty("jwt.prefix")).append(' ').append(token).append("\" }").toString();
	}

}
