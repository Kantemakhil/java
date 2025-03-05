package net.syscon.s4.services.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		List<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new GrantedAuthority() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "ROLE_TRUSTED_CLIENT";
			}
		});
		//https://stackoverflow.com/questions/29510759/how-to-test-spring-security-oauth2-resource-server-security
		//https://docs.spring.io/spring-security/site/docs/4.0.x/reference/htmlsingle/#test-method-withsecuritycontext
		Authentication auth = new UsernamePasswordAuthenticationToken(customUser.username(), customUser.password(), authorityList);
		context.setAuthentication(auth);
		return context;
	}
}
