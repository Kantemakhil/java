package net.syscon.s4.services.test;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

public abstract class  AbstractMockTestCase {

	@Autowired
	protected WebApplicationContext ctx;

	protected static Logger logger = LogManager.getLogger(OsiosearControllerMockTestCase.class);

	protected MockMvc mockMvc;

	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
				//.apply(springSecurity())
				.dispatchOptions(true).build();
		
		/*mockMvc.perform(MockMvcRequestBuilders.get("/api/token")
	            .with(authentication(getOauthTestAuthentication()))
	            .sessionAttr("scopedTarget.oauth2ClientContext", getOauth2ClientContext()))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
	            .andExpect(jsonPath("$.username").value("bwatkins"))
	            .andExpect(jsonPath("$.token").value("my-fun-token"));*/
	}
	
	private Authentication getOauthTestAuthentication() {
	    return new OAuth2Authentication(getOauth2Request(), getAuthentication());
	}
	
	private OAuth2Request getOauth2Request () {
	    String clientId = "oauth-client-id";
	    Map<String, String> requestParameters = Collections.emptyMap();
	    boolean approved = true;
	    String redirectUrl = "http://my-redirect-url.com";
	    Set<String> responseTypes = Collections.emptySet();
	    Set<String> scopes = Collections.emptySet();
	    Set<String> resourceIds = Collections.emptySet();
	    Map<String, Serializable> extensionProperties = Collections.emptyMap();
	    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("Everything");

	    OAuth2Request oAuth2Request = new OAuth2Request(requestParameters, clientId, authorities,
	        approved, scopes, resourceIds, redirectUrl, responseTypes, extensionProperties);

	    return oAuth2Request;
	}
	
	private Authentication getAuthentication() {
	    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("Everything");

	    User userPrincipal = new User("user", "", true, true, true, true, authorities);

	    HashMap<String, String> details = new HashMap<String, String>();
	    details.put("user_name", "bwatkins");
	    details.put("email", "bwatkins@test.org");
	    details.put("name", "Brian Watkins");

	    TestingAuthenticationToken token = new TestingAuthenticationToken(userPrincipal, null, authorities);
	    token.setAuthenticated(true);
	    token.setDetails(details);

	    return token;
	}
	
	private OAuth2ClientContext getOauth2ClientContext () {
	    /*OAuth2ClientContext mockClient = mock(OAuth2ClientContext.class);
	    when(mockClient.getAccessToken()).thenReturn(new DefaultOAuth2AccessToken("my-fun-token"));

	    return mockClient;*/
		return null;
	}
	
	
}
