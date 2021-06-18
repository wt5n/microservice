package ru.wt5n.microservice.filters;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.wt5n.microservice.models.entity.User;
import ru.wt5n.microservice.repositories.UserRepository;
import ru.wt5n.microservice.utils.JwtTokenUtil;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	public static final String AUTHORIZATION = "Authorization";

	private final JwtTokenUtil jwtTokenUtil;
	private final UserRepository usersRepository;

	@SneakyThrows
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain){
		String token = getTokenFromRequest(httpServletRequest);
		if (token != null && jwtTokenUtil.validateToken(token)) {
			String userLogin = jwtTokenUtil.getLoginFromToken(token);
			User user = usersRepository.findByUsername(userLogin).orElseThrow(() -> new UsernameNotFoundException("Invalid token"));
//			if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//				SecurityContextHolder.setContext(new SecurityContextImpl(new AuthenticationImpl(user)));
//			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearer = request.getHeader(AUTHORIZATION);
		if (hasText(bearer) && bearer.startsWith("Bearer ")) {
			return bearer.substring(7);
		}
		return null;
	}
}
