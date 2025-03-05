package net.syscon.s4.services.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class EliteOAuthSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	protected DataSource dataSource;
	
	/*@Autowired
	private EliteAuthenticationProvider authenticationProvider;*/
	
	/*@Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider)inMemoryAuthentication()
        .withUser("bill").password("abc123").roles("ADMIN").and()
        .withUser("bob").password("abc123").roles("USER");
    }*/
	
	DefaultTokenServices tokenServices;
	TokenStore tokenStore;
	

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.anonymous().disable()
	  	.authorizeRequests()
	  	.antMatchers("/oauth/token","/api/getLoginMsgResources", "/api/iwp/getProperties", "/api/schprofile","/api/getLoginLogo","/api/getSysSetConfADData","/api/configuration","/api/app/version","/api/documentEditor/SystemClipboard","/api/documentEditor/SpellCheck",
	  			"/api/documentEditor/Import","/api/documentEditor/SpellCheckByPage","/api/documentEditor/RestrictEditing","/api/documentEditor/Save","/api/documentEditor/ExportSFDT","/api/documentEditor/Export",	"/api/documentEditor/Export").permitAll().and()
	  	.logout().deleteCookies("remove").invalidateHttpSession(true)
			.logoutUrl("/oauth/logout")
			.logoutSuccessHandler((new LogoutSuccessHandler() {
				
				@Override
				public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
						throws IOException, ServletException {
					
					final OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
				    final String token = tokenStore.getAccessToken(auth).getValue();
				    tokenServices.revokeToken(token);
				    
					response.setStatus(HttpStatus.OK.value());
					response.getWriter().flush();
					
				}
			}
		))
			.logoutSuccessUrl("/")
			.clearAuthentication(true);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
    	return super.userDetailsService();
    }


	@Bean
	public TokenStore tokenStore() {
		this.tokenStore = new PGJDBCTokenStore(this.dataSource);
		return tokenStore;
	}

	@Bean
	@Autowired
	public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
		TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
		handler.setTokenStore(tokenStore);
		handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		handler.setClientDetailsService(clientDetailsService);
		return handler;
	}
	
	@Bean
	@Autowired
	public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
		TokenApprovalStore store = new TokenApprovalStore();
		store.setTokenStore(tokenStore);
		return store;
	}
	
	@Bean
	@Autowired
	public PasswordEncoder passwordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
	    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	    defaultTokenServices.setTokenStore(tokenStore());
	    defaultTokenServices.setSupportRefreshToken(true);
	    defaultTokenServices.setReuseRefreshToken(false);
	    defaultTokenServices.setAccessTokenValiditySeconds(25000); // - 8 hour
	    defaultTokenServices.setRefreshTokenValiditySeconds(26000);
	    this.tokenServices = defaultTokenServices;
	    return defaultTokenServices;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/api/getLoginMsgResources", "/api/iwp/getProperties", "/api/schprofile","/api/getLoginLogo","/api/getSysSetConfADData", "/api/configuration", "/api/app/version","/api/documentEditor/SystemClipboard","/api/documentEditor/SpellCheck",
				"/api/documentEditor/Import","/api/documentEditor/SpellCheckByPage","/api/documentEditor/RestrictEditing","/api/documentEditor/Save","/api/documentEditor/ExportSFDT","/api/documentEditor/Export",	"/api/documentEditor/Export");
	}
	
	  
}
