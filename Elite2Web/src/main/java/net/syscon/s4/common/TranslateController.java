
package net.syscon.s4.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.syscon.s4.common.beans.EmailUser;
import net.syscon.s4.common.beans.LoginMsgResponse;
import net.syscon.s4.common.beans.SelectOption;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.SystemLables;
import net.syscon.s4.sa.admin.OlisetService;
import net.syscon.s4.sa.admin.OumsylabService;

@EliteController
public class TranslateController {

	private static Logger logger = LogManager.getLogger(TranslateController.class.getName());

	private static String DEFAULT_LANGUAGE = "en-US";  // This is used only when the value in appconfig.properties is invalid (last resort)
	
	private static String PROP_FILE_EXT = ".properties";
	
	private static String BACK_SLASH = "/";
	
	private static String I10N_FOLDER = "l10n";

	private static String APP_PROP_FILE_NAME = "appmsg";
	
	private static String LOGIN_PROP_FILE_NAME = "loginmsg";
	
	private static String SYSTEM_PROFILE = "system-profile.";
	 
	public static Map<String,String> systemLabel=new HashMap<>();
	
	public static Map<String,String> loginSystemLabel=new HashMap<>();
	
	private static int NUM_ZERO = 0;
	
	private static int NUM_ONE = 1;
	
	private static int NUM_TEN_THOUSAND = 10000;

	@Autowired
	private Omss40Service omss40Service;
	
	@Autowired
	private OlisetService olisetService;
	
	@Autowired
	private Environment env;
	
	 @Autowired
	    private OumsylabService oumsylabService;
	 
	 @Autowired
	 private OumsysetService oumsysetService;

	/**
	 * method To Read Properties file Data for I18N after login
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/getAppMsgResources", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, String> getAppMsgResources(@RequestParam(value = "lang") final String lang) {
		Map<String, String> propertiesMap = new HashMap<>();
		propertiesMap = getAppMsgProperties(lang);
		return propertiesMap;
	}

	/**
	 * method To Read Properties file Data for I18N before login
	 */
	@RequestMapping(value = "/getLoginMsgResources", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody LoginMsgResponse getLoginMsgResources(@RequestParam(value = "lang") final String lang) {
		LoginMsgResponse response = null;
		try {
			response = this.getLoginMsgResponse(lang);
		}catch(Exception e) {
			logger.error(e);
		}
		return response;
	}
	
	@RequestMapping(value = "/getLoginLogo", method = RequestMethod.GET)
	public @ResponseBody List<Images> getLoginLogo() {
		List<Images> response = null;
		try {
			response = olisetService.getLoginLogo();
		}catch(Exception e) {
			logger.error(e);
		}
		return response;
	}
	
	@RequestMapping(value="/configuration" ,method = RequestMethod.GET)
	public @ResponseBody List<ProfileCodes>  specficSystemProfile() {
		
		List<ProfileCodes> profileCodesList = omss40Service.specficSystemProfile();
		
        return profileCodesList;
	}
	
	@RequestMapping(value = "/updateLabelCache", method = RequestMethod.GET)
	public @ResponseBody Integer updateLabelCache() {
		Integer result=NUM_ZERO;
		try {
			loadAppMsg();
			result=NUM_ONE;
			
		}catch(Exception e) {
			logger.error(e);
		}
		return result;
	}
	
	
     @PostConstruct
	public void loadAppMsg(){
		int start=NUM_ONE;
		int end=NUM_TEN_THOUSAND;
		for(;;) {
			List<SystemLables> systemLables = this.getPropertiesFromDb(start,end);
			for (final SystemLables sysLables : systemLables) {
				if(!sysLables.getModuleName().equals("LOGIN")) {
				final String value =sysLables.getMsgValue();
				String formatedValueue = value!=null?value.trim():"";
				systemLabel.put(sysLables.getMsgKey(), formatedValueue);
				}else if(sysLables.getModuleName().equals("LOGIN")) {
					String value = sysLables.getMsgValue()!=null? sysLables.getMsgValue().trim():"";
					loginSystemLabel.put(sysLables.getMsgKey(), value);	
					}
			}
			
			if(systemLables!=null && !systemLables.isEmpty()) {
				start=end+NUM_ONE;
				end=end+NUM_TEN_THOUSAND;
			}else {
				break;
			}
			
		}
		
	}
	
	

	public Map<String, String> getAppMsgProperties(final String language) {
		InputStream is = null;
		String lang = language;
		final Map<String, String> propertiesMap = new HashMap<>();
		Properties properties;
		try {
			if (lang == null || (lang != null && lang.isEmpty())) {
				lang = TranslateController.DEFAULT_LANGUAGE;
			}
			String filename = TranslateController.BACK_SLASH + TranslateController.I10N_FOLDER + 
					TranslateController.BACK_SLASH + lang + 
					TranslateController.BACK_SLASH + TranslateController.APP_PROP_FILE_NAME + TranslateController.PROP_FILE_EXT;
			is = this.getClass().getResourceAsStream(filename);
			if (is == null) {
				filename = TranslateController.BACK_SLASH + TranslateController.I10N_FOLDER + 
						TranslateController.BACK_SLASH + TranslateController.DEFAULT_LANGUAGE +
						TranslateController.BACK_SLASH + TranslateController.APP_PROP_FILE_NAME + TranslateController.PROP_FILE_EXT;
				is = this.getClass().getResourceAsStream(filename);
			}

			properties = new Properties();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			properties.load(isr);
			
			
			List<String> profileCode = new ArrayList<String>();
			profileCode.add("LABEL");
			List<ProfileCodes> profileCodesList = omss40Service.searchProfileCodes(profileCode);
			if(systemLabel!=null && !systemLabel.isEmpty()) {
				return systemLabel;
			}
			List<SystemLables> systemLables = this.getPropertiesFromDb();
			for (final SystemLables sysLables : systemLables) {
				if(!sysLables.getModuleName().equals("LOGIN")) {
				final String value =sysLables.getMsgValue();
				String formatedValueue = value!=null?value.trim():"";
				propertiesMap.put(sysLables.getMsgKey(), formatedValueue);
				}
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return propertiesMap;
	}
	
	public List<SystemLables> getPropertiesFromDb(){
		List<SystemLables> sysLabel= null;
		try {
			sysLabel= oumsylabService.getPropertiesFromDb();
		}catch(Exception e){
			logger.info("Error While loading Labels "+ e);
		}
		
		return sysLabel;
	}
	
	public List<SystemLables> getPropertiesFromDb(int startIndex,int endIndex){
		List<SystemLables> sysLabel= null;
		try {
			sysLabel= oumsylabService.getPropertiesFromDb(startIndex,endIndex);
		}catch(Exception e){
			logger.info("Error While loading Labels "+ e);
		}
		
		return sysLabel;
	}
	
	private LoginMsgResponse getLoginMsgResponse(final String language) {
		LoginMsgResponse res = new LoginMsgResponse();
		InputStream is = null;
		InputStreamReader isr = null;
		String lang = language;
		String defaultLanguage = null;
		final Map<String, String> propertiesMap = new HashMap<>();
		Properties properties;
		try {
			//
			// Load the language options first in case the default language is not provided
			//
			String basePath = this.getClass().getResource(TranslateController.BACK_SLASH + TranslateController.I10N_FOLDER).getPath();
			File baseFile = new File(basePath);
			if(baseFile.isDirectory()) {
				File[] folders = baseFile.listFiles();
				Properties prop = new Properties();
				List<SelectOption> options = new ArrayList<>();
				for(File folder : folders) {
					if(folder.isDirectory()) {
						if(!"dev".equalsIgnoreCase(folder.getName())) {
						File loginFile = new File(folder.getPath() + TranslateController.BACK_SLASH + TranslateController.LOGIN_PROP_FILE_NAME + TranslateController.PROP_FILE_EXT);
						if(loginFile.exists()) {
							logger.debug("i10n (Adding language):" + folder.getName());
							
							InputStream isl = this.getClass().getResourceAsStream(TranslateController.BACK_SLASH + TranslateController.I10N_FOLDER + 
									TranslateController.BACK_SLASH + folder.getName() +
									TranslateController.BACK_SLASH + TranslateController.LOGIN_PROP_FILE_NAME + TranslateController.PROP_FILE_EXT);
							InputStreamReader isrl = new InputStreamReader(isl, "UTF-8");
							
							prop.clear();
							prop.load(isrl);
							SelectOption option = new SelectOption();						
							option.setId(folder.getName());
							option.setText(prop.getProperty("lang-value"));
							options.add(option);
							}
						}
					}
				}
				res.setLanguages(options);
			}
			
			if(res.getLanguages() == null) {
				logger.error("i10n No languages found at:" + basePath);
			}

			//
			// Load application properties
			//
			is = this.getClass().getResourceAsStream(TranslateController.BACK_SLASH+"appconfig.properties");
			isr = null;
			if(is != null) {			
				isr = new InputStreamReader(is, "UTF-8");
			}
			properties = new Properties();
			if(isr != null) {
				properties.load(isr);
				propertiesMap.put("common.syscon.version", properties.getProperty("app.syscon.version"));
				propertiesMap.put("common.elite.version", properties.getProperty("app.elite.version"));
				
				defaultLanguage = properties.getProperty("app.language.default");
			}
			
			if(defaultLanguage == null) {
				defaultLanguage = TranslateController.DEFAULT_LANGUAGE;
			}

			if (lang == null || (lang != null && lang.isEmpty())) {
				lang = defaultLanguage;
			}
			
			//
			// Verify the specified language is present, otherwise fall back to the default or first language loaded
			//
			res.setLang(null);
			for (SelectOption i : res.getLanguages()) {
				if(i.getId().equals(lang)) {
					res.setLang(i.getId());
				}
			}
			if(res.getLang() == null) {
				for (SelectOption i : res.getLanguages()) {
					if(i.getId().equals(defaultLanguage)) {
						res.setLang(i.getId());
					}
				}
			}
			if(res.getLang() == null) {
				logger.warn("i10n Selected language not found:" + lang);
				// Use the first language in the list
				res.setLang(res.getLanguages().get(0).getId());
				logger.warn("i10n Using language:" + res.getLang());
			}
			
			//
			// Load the language file
			//
			String filename = TranslateController.BACK_SLASH + TranslateController.I10N_FOLDER + 
					TranslateController.BACK_SLASH + res.getLang() + 
					TranslateController.BACK_SLASH + TranslateController.LOGIN_PROP_FILE_NAME + TranslateController.PROP_FILE_EXT;
			is = this.getClass().getResourceAsStream(filename);
			isr = new InputStreamReader(is, "UTF-8");	

			properties.load(isr);
			if(loginSystemLabel!=null && !loginSystemLabel.isEmpty()) {
				propertiesMap.putAll(loginSystemLabel);
			}else {
			
			List<SystemLables> systemLables = this.getPropertiesFromDb();
			for (SystemLables systemLables2 : systemLables) {
				if(systemLables2.getModuleName().equals("LOGIN")) {
				String value = systemLables2.getMsgValue()!=null? systemLables2.getMsgValue().trim():"";
				propertiesMap.put(systemLables2.getMsgKey(), value);	
				}
			}
			}
//			for (final String key : properties.stringPropertyNames()) {
//				final String value = properties.getProperty(key);
//			}
			
			//
			// Load System Profiles
			//
			List<String> profileCode = new ArrayList<String>();
			profileCode.add("DISPLAY");
			profileCode.add("FORMAT");
			profileCode.add("CLIENT");
			List<ProfileCodes> profileCodesList = omss40Service.searchProfileCodes(profileCode);
			if (profileCodesList != null && profileCodesList.size() > 0) {
				for (ProfileCodes profileObj : profileCodesList) {
					if(profileObj.getProfileValue() != null && profileObj.getProfileCode() !=  null) {
						propertiesMap.put(TranslateController.SYSTEM_PROFILE+profileObj.getProfileCode().toLowerCase().replaceAll("_", "-"), profileObj.getProfileValue());
					}
				}
			}
			
			res.setMsgs(propertiesMap);
		} catch (IOException e) {
			logger.error(e);
		}		
		return res;
	}
	@GetMapping("/profile/propertyType")
	public List<ProfileCodes>  getProfileCodesForProfirType(@RequestParam(value = "profileType") final String profileType) {
		Map<String, String> profileCodeValue = new HashMap<>();
		List<String> profileCode = new ArrayList<String>();
		profileCode.add(profileType);
		List<ProfileCodes> profileCodesList = omss40Service.searchProfileCodes(profileCode);
		
        return profileCodesList;
	}
	
	@GetMapping("/schprofile")
	public List<ProfileCodes>  getScheduledProfileCodesForProfirType() {
		List<String> profileCode = new ArrayList<String>();
		profileCode.add("DISPLAY");
		List<ProfileCodes> profileCodesList = omss40Service.searchProfileCodes(profileCode);
		
        return profileCodesList;
	}
	
	@GetMapping("/profile/propertyType/profileCode")
	public ProfileCodes  getProfileCodesForProfileCode(@RequestParam(value = "profileType") final String profileType, @RequestParam(value = "profileCode") final String profileCode) {
		List<String> profileCodes = new ArrayList<String>();
		profileCodes.add(profileType);
		List<ProfileCodes> profileCodesList = omss40Service.searchProfileCodes(profileCodes);
		if (profileCodesList != null && profileCodesList.size() > 0) {
			for (ProfileCodes profileObj : profileCodesList) {
				if(profileObj.getProfileCode().equalsIgnoreCase(profileCode)) {
					return profileObj;
				}
			}
		}
        return null;
	}
	
	@GetMapping("/iwp/getProperties")
	public Map<String, String> getTrimProperties(){
		Map<String, String> trimPropertyMap = new HashMap<>();
        for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof CompositePropertySource) {
               String[] propertyNames = ((CompositePropertySource) propertySource).getPropertyNames();
               AES aes = new AES();
               for (String property : propertyNames) {
				  if("api.user".equals(property) || "api.pwd".equals(property)){
					  trimPropertyMap.put(property, aes.encrypt(env.getProperty(property))); 
				  }
			   }
               
	        }
        }
        return trimPropertyMap;
	}
	
	@GetMapping("/app/version")
	public String getAppVersion(){
		String appVersion = "";
		Map<String, String> trimPropertyMap = new HashMap<>();
		return env.getProperty("app.elite.version");
        /*for(Iterator it = ((AbstractEnvironment) env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof CompositePropertySource) {
               String[] propertyNames = ((CompositePropertySource) propertySource).getPropertyNames();
               AES aes = new AES();
               for (String property : propertyNames) {
				  if("api.user".equals(property) || "api.pwd".equals(property)){
					  trimPropertyMap.put(property, aes.encrypt(env.getProperty(property))); 
				  }
			   }
               
	        }
        }
        return trimPropertyMap;*/
	}

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	private TokenStore tokenStore;

	@PostMapping("/logout/revoke")
	@ResponseStatus(code = HttpStatus.OK)
	public boolean revokeToken() {
	    final OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
	    final String token = tokenStore.getAccessToken(auth).getValue();
	    boolean response = tokenServices.revokeToken(token);
	    return response;
	}
	
	
	@RequestMapping(value = "/getSysSetConfData", method = RequestMethod.GET)
	public List<Map<String,Object>> getSysSetConfData(@RequestParam (value = "settingType") final String settingType,@RequestParam(value = "providerCode")  final String providerCode) {
		List<Map<String,Object>> returnList = null;
		try {
			returnList = oumsysetService.getSysData(settingType,providerCode); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getSysSetConfData : {}", e.getMessage());
		}
		return returnList;
	}
	
	@RequestMapping(value = "/getSysSetConfADData", method = RequestMethod.GET)
	public List<Map<String,Object>> getSysSetConfADData() {
		List<Map<String,Object>> returnList = null;
		try {
			returnList = oumsysetService.getSysData("AD","AZUREAD"); 
			for(Map<String,Object> adMaps: returnList) {
				for (Map.Entry<String,Object> entry : adMaps.entrySet())  {
					if(entry.getKey().equals("VALUE")) {
						String value = entry.getValue().toString();
						entry.setValue(Base64.getEncoder().encodeToString(value.getBytes()));
					}
				}
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getSysSetConfData : {}", e.getMessage());
		}
		return returnList;
	}
	
	@GetMapping("/userinfo/getemail")
	public String getmailId() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userId = authentication.getName();
		return omss40Service.getmailId(userId);
	}

}
