package net.syscon.s4.services.oauth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import net.syscon.s4.common.UserAuthenticationService;
import net.syscon.s4.global.EliteAuthenticationService;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.global.impl.EliteAuthenticationServiceImpl;

@Component
public class EliteAuthenticationProvider implements AuthenticationProvider, Serializable {

	//@Autowired
	//private UserAuthenticationService serAuthenticationService;
	
	//@Autowired
	//private EliteAuthenticationService eliteAuthService;

	private List<GrantedAuthority> authorityList = new ArrayList<>();

	public EliteAuthenticationProvider(){
		authorityList.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return "ROLE_TRUSTED_CLIENT";
			}
		});
		authorityList.add(new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return "ROLE_CLIENT";
			}
		});
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
		EliteAuthenticationService eliteAuthService = ctx.getBean(EliteAuthenticationServiceImpl.class);
		UserAuthenticationService serAuthenticationService = ctx.getBean(UserAuthenticationService.class);
		String userStatus = eliteAuthService.getUserStatus(name);
		if (name != null && !name.isEmpty() && password != null
				&& !password.isEmpty() && serAuthenticationService.authenticate(name, password) && !userStatus.equalsIgnoreCase("INACT")) {
			if("ADMINQA".equals(name)){
				return new UsernamePasswordAuthenticationToken(name, password,authorityList);
			}else{
				authorityList.add(new GrantedAuthority() {

					@Override
					public String getAuthority() {
						return "ROLE_USER";
					}
				});
				return new UsernamePasswordAuthenticationToken(name, password,authorityList);
			}
		} else {
			if(userStatus.equalsIgnoreCase("INACT")) {
				throw new InsufficientAuthenticationException("Inactive_User");
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
