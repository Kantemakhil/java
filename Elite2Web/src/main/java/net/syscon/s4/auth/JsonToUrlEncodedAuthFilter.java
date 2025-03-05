package net.syscon.s4.auth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.IncorrectClaimException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MissingClaimException;
import io.jsonwebtoken.SignatureException;
import net.syscon.s4.common.beans.EmailUser;
import net.syscon.s4.global.impl.Omss40ServiceImpl;
import net.syscon.s4.globalconfiguration.impl.OumsysetServiceImpl;

@WebFilter("/oauth/*")
public class JsonToUrlEncodedAuthFilter extends OncePerRequestFilter {
	
	private static Logger logger = LogManager.getLogger(JsonToUrlEncodedAuthFilter.class.getName());
	
	Omss40ServiceImpl omssService;
	OumsysetServiceImpl oumsysetService;
	
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (Objects.equals(request.getServletPath(), "/oauth/token") && Objects.equals(request.getContentType(), "application/json")) {
        	
        	if(request.getHeader("adToken") != null && !request.getHeader("adToken").equals("")) {
        		String referer = request.getHeader("Referer");
        		logger.error("V5 AD login attempt for domain "+referer);
        		//Validate Request is coming from same domain
        		if(request.getHeader("Referer") != null && !request.getHeader("Referer").equals("")) {
        			ApplicationContext ctx = WebApplicationContextUtils
  	        		      .getRequiredWebApplicationContext(request.getServletContext());
  	        		    this.oumsysetService = ctx.getBean(OumsysetServiceImpl.class);
  	        		  List<Map<String, Object>> adProviderDetail = this.oumsysetService.getSysData("AD", "AZUREAD");
  	        		  String redirectUrl = "";
  	        		  for (Map<String, Object> adDetail:adProviderDetail) {
  	        			Object obj = adDetail.get("KEY_CODE");
  	        			if (obj != null && obj.toString().equalsIgnoreCase("AD_RED_URI")) {
  	        				Object valueObj = adDetail.get("VALUE");
  	        				referer =  referer.substring(0, referer.lastIndexOf("/"));
  	        				redirectUrl = valueObj != null?valueObj.toString():"";
  	        				if(!redirectUrl.equalsIgnoreCase("")) {
  	        					redirectUrl = redirectUrl.replaceAll("%3A", ":").replaceAll("%2F", "/");
  	        					logger.error("V5 AD login attempt for domain "+referer +" and configured domain is "+redirectUrl);
  	        					if(!redirectUrl.equalsIgnoreCase(referer)) {
  	        						logger.error("V5 AD login attempt for domain "+referer +" failed ");
  	        						EmailUser emailUser1 = new EmailUser();
  	                	        	emailUser1.setKey("");
  	                	        	emailUser1.setKeyDetails("");
  	                	        	
  	                	        	Map<String, String> parameterMap1 = new HashMap<String, String>();
  	                  	       		parameterMap1.put("username", emailUser1.getKey());
  	                 	        	parameterMap1.put("password", emailUser1.getKeyDetails());
  	                 	        	parameterMap1.put("grant_type", "password");
  	                 	        	this.dofilterNext(parameterMap1, request, response, filterChain);
  	        					}
  	        				} else {
  	        					logger.error("V5 AD login attempt failled, wrong domain attempted");
  	        					EmailUser emailUser1 = new EmailUser();
  	            	        	emailUser1.setKey("");
  	            	        	emailUser1.setKeyDetails("");
  	            	        	
  	            	        	Map<String, String> parameterMap1 = new HashMap<String, String>();
  	              	       		parameterMap1.put("username", emailUser1.getKey());
  	             	        	parameterMap1.put("password", emailUser1.getKeyDetails());
  	             	        	parameterMap1.put("grant_type", "password");
  	             	        	this.dofilterNext(parameterMap1, request, response, filterChain);
  	        				}
  	        				break;
  	        			}
  	        		  }
        		} else {
        			//Not valid Request
        			logger.error("V5 AD login attempt failled, Not valid domain provided in request");
        			EmailUser emailUser1 = new EmailUser();
      	        	emailUser1.setKey("");
      	        	emailUser1.setKeyDetails("");
      	        	
      	        	Map<String, String> parameterMap1 = new HashMap<String, String>();
        	       		parameterMap1.put("username", emailUser1.getKey());
       	        	parameterMap1.put("password", emailUser1.getKeyDetails());
       	        	parameterMap1.put("grant_type", "password");
       	        	this.dofilterNext(parameterMap1, request, response, filterChain);
        		}
        		String adToken = request.getHeader("adToken");
        		if(adToken != null) {
        			String[] split_string = adToken.split("\\.");
        			if(split_string != null && split_string.length>2) {
        				String base64EncodedHeader = split_string[0];
            	        String base64EncodedBody = split_string[1];
            	        String base64EncodedSignature = split_string[2];
            	        
            	        Base64 base64Url = new Base64(true);
            	        String header = new String(base64Url.decode(base64EncodedHeader));
            	        EmailUser emailUser = null;
            	        //TODO
            	        // Validate token time and signature
            	        if(!this.validateADToken(base64EncodedHeader, base64EncodedBody, base64EncodedSignature,adToken)) {
            	        	emailUser = new EmailUser();
            	        	emailUser.setKey("");
            	        	emailUser.setKeyDetails("");
            	        } else {
            	        	String body = new String(base64Url.decode(base64EncodedBody));
                	        Map<String, String> bodyMap = new ObjectMapper().readValue(body, Map.class);
                	        try {
                	        	String userEmail = bodyMap.get("preferred_username");
                	        	ApplicationContext ctx = WebApplicationContextUtils
                	        		      .getRequiredWebApplicationContext(request.getServletContext());
                	        		    this.omssService = ctx.getBean(Omss40ServiceImpl.class);
                	        	emailUser = omssService.getDecryptPwd(userEmail);
                	        } catch(Exception ex) {
                	        	logger.error("V5 AD login failed - Exception is "+ex.getMessage());
                	        	emailUser = new EmailUser();
                	        	emailUser.setKey("");
                	        	emailUser.setKeyDetails("");
                	        	ex.printStackTrace();
                	        }
            	        }
            	        
            	        Map<String, String> parameterMap = new HashMap<String, String>();
            	       	parameterMap.put("username", emailUser.getKey());
           	        	parameterMap.put("password", emailUser.getKeyDetails());
           	        	parameterMap.put("grant_type", "password");
           	        	this.dofilterNext(parameterMap, request, response, filterChain);
        			}
        		}
        		
        	} else {
			    	InputStream is = request.getInputStream();
			        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			        int nRead;
			        byte[] data = new byte[16384];
			
			        while ((nRead = is.read(data, 0, data.length)) != -1) {
			            buffer.write(data, 0, nRead);
			        }
			        buffer.flush();
			        byte[] json = buffer.toByteArray();
			        Map<String, String> jsonMap = new ObjectMapper().readValue(json, Map.class);
			        this.dofilterNext(jsonMap, request, response, filterChain);
			        
        	}
        	
	        
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void dofilterNext(Map<String, String> jsonMap, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException  {
    	Map<String, String[]> parameters =
                jsonMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                e ->  new String[]{e.getValue()})
                        );
        HttpServletRequest requestWrapper = new RequestWrapper(request, parameters);
        filterChain.doFilter(requestWrapper, response);
    }

    private class RequestWrapper extends HttpServletRequestWrapper {

        private final Map<String, String[]> params;

        RequestWrapper(HttpServletRequest request, Map<String, String[]> params) {
            super(request);
            this.params = params;
        }

        @Override
        public String getParameter(String name) {
            if (this.params.containsKey(name)) {
                return this.params.get(name)[0];
            }
            return "";
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return this.params;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return new Enumerator<>(params.keySet());
        }

        @Override
        public String[] getParameterValues(String name) {
            return params.get(name);
        }
    }
    
    private boolean validateADToken(String header, String payload, String signature, String adToken) {
    	boolean isValid = false;
    	Base64 base64Url = new Base64(true);
    	String decodeHeader = new String(base64Url.decode(header));
    	String decodePayload = new String(base64Url.decode(payload));
    	//		String decodeSignature = new String(base64Url.decode(signature));
    	try {
    		Map<String, String> headerMap = new ObjectMapper().readValue(decodeHeader, Map.class);
    		Map<String, String> payloadMap = new ObjectMapper().readValue(decodePayload,Map.class);
    		String kid = String.valueOf(headerMap.get("kid"));
    		HttpClient httpClient = HttpClients.createDefault();
    		HttpGet httpGet = new HttpGet("https://login.microsoftonline.com/common/.well-known/openid-configuration");
    		HttpResponse response = httpClient.execute(httpGet);

    		if (response.getStatusLine().getStatusCode() == 200) {
    			String jsonConfig = EntityUtils.toString(response.getEntity());
    			Map<String, String> jsonConfigMap = new ObjectMapper().readValue(jsonConfig, Map.class);
    			String jwksURI = jsonConfigMap.get("jwks_uri");
    			JwkProvider provider = new UrlJwkProvider(new URL(jwksURI));
    			RSAPublicKey pubKey = (RSAPublicKey) provider.get(kid).getPublicKey();
    			try {
    				Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(adToken).getBody();
    				logger.info("V5 AD login claims successfull");
    			} catch (SignatureException e) {
    				logger.error("V5 AD login attempt token SignatureException: {}", e.getMessage());
    				throw new Exception("Could not verify JWT token integrity!");
    			} catch (ExpiredJwtException e) {
    				logger.error("V5 AD login attempt token ExpiredJwtException: {}", e.getMessage());
    				throw new Exception("Could not verify JWT token integrity!");
    			} catch (MissingClaimException e) {
    				logger.error("V5 AD login attempt token MissingClaimException: {}", e.getMessage());
    				throw new Exception("Could not verify JWT token integrity!");
    			} catch (IncorrectClaimException e) {
    				logger.error("V5 AD login attempt token IncorrectClaimException: {}", e.getMessage());
    				throw new Exception("Could not verify JWT token integrity!");
    			} catch (Exception e) {
    				logger.error("V5 AD login attempt Exception: {}", e.getMessage());
    				throw new Exception("Could not verify JWT token integrity!");
    			}
    		}
    		isValid = true;
    	} catch (Exception e) {
    		logger.error("V5 AD login attempt Exception: {}", e.getMessage());
    	}
    	return isValid;
    }
}