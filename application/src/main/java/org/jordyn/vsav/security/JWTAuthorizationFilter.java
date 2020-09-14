package org.jordyn.vsav.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * JWT filter
 * @author jordyn
 *
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private String header;
	private String prefix;
	private String secret;

	@Autowired
	public JWTAuthorizationFilter(Environment env) {
		header = env.getRequiredProperty("jwt.header");
		prefix = env.getRequiredProperty("jwt.prefix");
		secret = env.getRequiredProperty("jwt.secret");
	}

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain)
			throws ServletException, IOException {
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");
			if (jwtIsNotMalformed(request, response)) {
				Claims claims = validateJwt(request);
				if (claims.get("authorities") != null) {
					setUpSpringAuthentication(claims);
				} else {
					SecurityContextHolder.clearContext();
				}
			} else {
				SecurityContextHolder.clearContext();
			}
			chain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}

	private Claims validateJwt(final HttpServletRequest request) {
		String jwt = request.getHeader(header).replace(new StringBuffer(prefix).append(' ').toString(), "");
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwt).getBody();
	}

	private boolean jwtIsNotMalformed(final HttpServletRequest request, final HttpServletResponse response) {
		String authHeader = request.getHeader(header);
		return authHeader != null && authHeader.startsWith(prefix);
	}

	private void setUpSpringAuthentication(final Claims claims) {
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> authorities = (List) claims.get("authorities");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
