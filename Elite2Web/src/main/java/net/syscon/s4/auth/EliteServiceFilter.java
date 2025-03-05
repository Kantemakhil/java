package net.syscon.s4.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import net.syscon.s4.common.utils.UserSecurityUtils;


public class EliteServiceFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtering on...........................................................");
		HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
        response.setHeader("X-Frame-Options", "SAMEORIGIN");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("X-XSS-Protection", "1");
        setUserDetails();
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {
		//init
	}

	public void destroy() {
		//destroy
	}
	
	private void setUserDetails(){
		SecurityContext secureContext = SecurityContextHolder.getContext();
		Authentication auth = secureContext.getAuthentication();
			if(auth != null){
			Object principal = auth.getPrincipal();
			if(principal != null){
				String userName = null;
				if (principal instanceof UserDetails) {
				    UserDetails userDetails = (UserDetails) principal;
				    if(userDetails != null)
				    	userName = userDetails.getUsername();
				} else {
				    userName = principal.toString();
				}
				if(userName != null && !userName.isEmpty() && !userName.equals("elite2-trusted-client") ){
					UserSecurityUtils.getInstance().setCurrentUserName(userName);
				}
			}
		}
	}

}