package net.syscon.s4.globalconfiguration.impl;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.SystemSettingsBean;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumsysetRepository;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Class OumsysetServiceImpl
 */
@Service
public class OumsysetServiceImpl extends BaseBusiness implements OumsysetService {

	@Autowired
	private OumsysetRepository oumsysetRepository;
	
	private static Logger logger = LogManager.getLogger(OumsysetServiceImpl.class.getName());
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String GRANT_TYPE = "grant_type";
	public static final String SLASH = "/";
	public static final String API = "api";
	public static final String KEY_CODE = "KEY_CODE";
	public static final String VALUE = "VALUE";
	public static final String USERS = "users";
	
	RestTemplate restTemplate = new RestTemplate();
	/**
	 * Creates new OumsysetServiceImpl class Object
	 */
	public OumsysetServiceImpl() {
	}

	@Override
	public SystemSettingsBean getSysSettingData(SystemSettingsBean systemSettingsBean) {
		SystemSettingsBean systemSettings= oumsysetRepository.getSysSettingData(systemSettingsBean);
		return getSysSettingModifiedData(systemSettings,"REMOVE");
	}
	
	@Override
	public SystemSettingsBean getSysSettingPageData(SystemSettingsBean systemSettingsBean) {
		SystemSettingsBean systemSettings = oumsysetRepository.getSysSettingData(systemSettingsBean);
		return getSysSettingModifiedData(systemSettings,"DECRYPT");
	}
	
    private SystemSettingsBean removeKeyFromJson(SystemSettingsBean systemSettings, String removableKey) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String settingValue = systemSettings.getSettingValue();
        JSONArray listValueData = new JSONArray(settingValue);
        int removedIndex = -1;
        for (int i = 0; i < listValueData.length(); i++) {
            JSONObject jsonObject = listValueData.getJSONObject(i);
            String keyCode = (String) jsonObject.get("KEY_CODE");
            if (StringUtils.isNotBlank(keyCode) && keyCode.equalsIgnoreCase(removableKey)) {
                removedIndex = i;
            }
        }
        if (removedIndex > 0) {
            listValueData.remove(removedIndex);
            systemSettings.setSettingValue(listValueData.toString());
        }
        
        return systemSettings;
    }
	
	@Override
	public Integer updateSysSettingData(SystemSettingsBean systemSettingsBean) {
		int updateResult = 0;
		SystemSettingsBean systemSettings = new SystemSettingsBean();
		systemSettings=getSysSettingModifiedData(systemSettingsBean,"ENCRYPT");
		if ("eMail".equals(systemSettingsBean.getSettingType())) {
			systemSettings.setSelectedProvider("Y");
		}
		systemSettings.setModifyUserId(systemSettingsBean.getModifyUserId());
		updateResult = oumsysetRepository.updateSysSettingData(systemSettings);
		if (updateResult == 1 && "eMail".equals(systemSettingsBean.getSettingType())) {
			updateResult = oumsysetRepository.postUpdate(systemSettingsBean);
		}
		return updateResult;
	}
	
	private SystemSettingsBean getSysSettingModifiedData(SystemSettingsBean systemSettingsBean,String action) {
		SystemSettingsBean systemSettings = new SystemSettingsBean();
		String [] params1 = {"serverConfig", "AUTOMATION_USER", "AUTOMATION_ELITE_PASSWORD"};
		String [] params2 = {"eMail", "CLICKSEND", "MAIL_PWD"};
		String [] params3 = {"SMS", "CLICKSEND_SMS", "POST_SMS_PWD"};
		String [] params4 = {"INSIGHTS", "AUTH", "PASSWORD"};
		String [] params5 = {"AddressConfig", "ADDRESSIFY_URL", "ADDRSSIFY_APIKEY"};
		String [] params6 = {"eMail", "SMTP", "SMTP_PWD"};
		if (systemSettingsBean.getSettingType().equals("serverConfig") && systemSettingsBean.getSettingProviderCode().equals("AUTOMATION_USER")) {
			systemSettings = modifyPasswordInfo(systemSettingsBean, params1,action);
		} else if (systemSettingsBean.getSettingType().equals("eMail") && systemSettingsBean.getSettingProviderCode().equals("CLICKSEND")) {
			systemSettings = modifyPasswordInfo(systemSettingsBean, params2,action);
		} else if (systemSettingsBean.getSettingType().equals("SMS") && systemSettingsBean.getSettingProviderCode().equals("CLICKSEND_SMS")) {
			systemSettings = modifyPasswordInfo(systemSettingsBean, params3,action);
		} else if (systemSettingsBean.getSettingType().equals("INSIGHTS") && systemSettingsBean.getSettingProviderCode().equals("AUTH")) {
			systemSettings = modifyPasswordInfo(systemSettingsBean, params4,action);
		} else if (systemSettingsBean.getSettingType().equals("AddressConfig") && systemSettingsBean.getSettingProviderCode().equals("ADDRESSIFY_URL")) {
			systemSettings = modifyPasswordInfo(systemSettingsBean, params5,action);
		} else if (systemSettingsBean.getSettingType().equals("eMail") && systemSettingsBean.getSettingProviderCode().equals("SMTP")) {
			systemSettings = modifyPasswordInfo(systemSettingsBean, params6,action);
		} else {
			systemSettings = systemSettingsBean;
		}
		return systemSettings;
	}
	
	
	private SystemSettingsBean modifyPasswordInfo(SystemSettingsBean systemSettings, String [] params,String action) {
		List<Map<String, Object>> modifiedSettingValues = new ArrayList<Map<String,Object>>();
		try {
			if(systemSettings.getSettingType().equals(params[0]) && systemSettings.getSettingProviderCode().equals(params[1])) {
				List<Map<String, Object>> settingValues = new ObjectMapper().readValue(systemSettings.getSettingValue(), List.class);
				for(Map<String, Object> settingValue : settingValues) {
					if(settingValue.get("KEY_CODE").equals(params[2])) {
						String value = null;
						if(action.equals("DECRYPT")) {
							value = decryptPassword(settingValue.get("VALUE").toString());
						}else if(action.equals("ENCRYPT")) {
							value = encryptPassword(settingValue.get("VALUE").toString());
						}else if(action.equals("REMOVE")) {
							value = "";
						}
						
						settingValue.put("VALUE", value);
					}
					modifiedSettingValues.add(settingValue);
				}
				
				List<JSONObject> jsonObj = new ArrayList<JSONObject>();
				for(Map<String, Object> data : modifiedSettingValues) {
				    JSONObject obj = new JSONObject(data);
				    jsonObj.add(obj);
				}
				JSONArray jsonArray = new JSONArray(jsonObj);
				
				systemSettings.setSettingValue(jsonArray.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemSettings;
	}
	
	

	private String encryptPassword(String pwd) {
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedPass = encoder.encodeToString(pwd.getBytes());
		return encodedPass;
	}
	
	private String decryptPassword(String pwd) {
		Base64.Decoder decoder = Base64.getDecoder();
		String decryptpwd = new String(decoder.decode(pwd));
		return decryptpwd;
	}

	@Override
	public List<Map<String, Object>> getSysData(String settingType,String providerCode) {
		List<Map<String,Object>> returnList = null;
		SystemSettingsBean searchBean=new SystemSettingsBean();
		searchBean.setSettingType(settingType);
		searchBean.setSettingProviderCode(providerCode);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			SystemSettingsBean obj=oumsysetRepository.getSysSettingData(searchBean);
			if(obj!=null && obj.getSettingValue()!=null) {
				String value=obj.getSettingValue().toString();
		          returnList=objectMapper.readValue(value, new TypeReference<List<Map<String,Object>>>(){});
					
			}
			
         
		} catch (Exception e) {
			logger.error("exception in getSysData with setting Type:"+searchBean.getSettingType(),e.getMessage());
			return null;
		} 
		return returnList;
	}

	

	@Override
	public String getConfValue(String settingType, String providerCode, String keyCode) {
		List<Map<String,Object>> returnList = null;
		String confValue=null;
		SystemSettingsBean searchBean=new SystemSettingsBean();
		searchBean.setSettingType(settingType);
		searchBean.setSettingProviderCode(providerCode);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			SystemSettingsBean obj=oumsysetRepository.getSysSettingData(searchBean);
			if(obj!=null && obj.getSettingValue()!=null) {
				String value=obj.getSettingValue().toString();
		          returnList=objectMapper.readValue(value, new TypeReference<List<Map<String,Object>>>(){});
		          
		          if(returnList!=null && !returnList.isEmpty()) {
		 			 for(Map<String,Object> object:returnList) {
		 	        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null && object.get("KEY_CODE").equals(keyCode) &&  object.containsKey("VALUE") && object.get("VALUE")!=null) {
		 	        		 confValue=object.get("VALUE").toString();
		 					}
		 	          }
		 		 }
					
			}
			
         
		} catch (Exception e) {
			logger.error("exception in getConfValue with setting Type:"+searchBean.getSettingType()+"KeyCide:"+keyCode,e.getMessage());
			return null;
		} 
		return confValue;
	}

	@Override
	public List<ReferenceCodes> getPhoneFormatTypes() {
		List<ReferenceCodes> formatList=new ArrayList<>();
		List<Map<String,Object>> returnList=getSysData("PHONEFORMAT", "PHONEMASKING");
		 if(returnList!=null && !returnList.isEmpty()) {
 			 for(Map<String,Object> object:returnList) {
 				ReferenceCodes referenceObj=new ReferenceCodes();
 				 if(object.containsKey("formatStatus") &&  object.get("formatStatus").equals(false)) {
	        			referenceObj.setCanDisplay(false);
	        		 }
 	        		  if(object.containsKey("maskingCode") && object.get("maskingCode")!=null ) {
 	        			 referenceObj.setCode(object.get("maskingCode").toString());
 	        		  }
 	        		 if(object.containsKey("maskingDescription") && object.get("maskingDescription")!=null ) {
 	        			referenceObj.setDescription(object.get("maskingDescription").toString());
	        		  }
 	        		 if(object.containsKey("maskFormat") && object.get("maskFormat")!=null ) {
  	        			referenceObj.setFormat(object.get("maskFormat").toString());
 	        		  }
 	        		 if(object.containsKey("sequence") && object.get("sequence")!=null ) {
   	        			referenceObj.setListSeq(new BigDecimal(object.get("sequence").toString()));
  	        		  }
 	        		 formatList.add(referenceObj);
// 					}
 	        	
 	          }
 			formatList.sort((o1, o2) 
 	                -> o1.getListSeq().compareTo(
 	                    o2.getListSeq()));
 		 }
		return formatList;
	}
	
    @Override
    public Map<String, String> getSettingValuesKeyValueMap(String settingType, String providerCode) {
        Map<String, String> resultMap = new HashMap<>();

        List<Map<String, Object>> returnList = this.getSysData(settingType, providerCode);

        if (returnList != null && !returnList.isEmpty()) {

            returnList.stream().forEach((entry) -> {
                Object key = entry.get("KEY_CODE");
                Object value = entry.get("VALUE");
                if (value instanceof String && key instanceof String) {
                    resultMap.put((String) key, (String) value);
                }
            });
        }

        return resultMap;
    }

	@Override
	public List<ReferenceCodes> getServiceBusQueueConf() {
		List<ReferenceCodes> confList=new ArrayList<>();
		 List<Map<String,Object>> returnList=getSysData("SBINTEGRATION","ASB");
		 if(returnList!=null && !returnList.isEmpty()) {
			 for(Map<String,Object> object:returnList) {
				 ReferenceCodes obj=new ReferenceCodes();
	        	  if(object.containsKey("KEY_CODE") && object.get("KEY_CODE")!=null ) {
	        		  obj.setCode(object.get("KEY_CODE").toString());
					}
	        	  if(object.containsKey("KEY_DESC") && object.get("KEY_DESC")!=null ) {
	        		  obj.setDescription(object.get("KEY_DESC").toString());
					}
	        	  confList.add(obj);
	          }
		 }	
		 return confList;
	}

	@Override
	public SystemSettingsBean getSelectedProvider(String settingType) {
		return oumsysetRepository.getSelectedProvider(settingType);
	}
	
	
	@Override
	public List<String> getPhoneFormates() {
		return oumsysetRepository.getPhoneFormates();
	}
	
	
	
	@Override
	public Map<String, Object> getAuthTokenBySecretKey(String userEmail) {
		String authToken = "";
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String insAuthToken = "";
		String user = "";
		String password = "";
		String grantType = "";
		String embedSecret = "";
		Map<String, Object> responseMap = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			List<Map<String,Object>> responseList=getSysData("INSIGHTS","INSIGHTS");
			if (responseList != null && !responseList.isEmpty()) {
				for (Map<String, Object> object : responseList) {
					if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INSI_PRIV_URL") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insSerUrl = object.get(VALUE).toString();
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_SITE_IDF") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						siteId = object.get(VALUE).toString();
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_AUTH_TOKEN") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insAuthToken = object.get(VALUE).toString();
					} else if(object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_EMB_SEC") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						embedSecret = object.get(VALUE).toString();
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insAuthToken;
			JSONObject jsonObject = new JSONObject();
			if (userEmail!=null && !"".equals(userEmail)) {
				user = userEmail;
			} else {
				List<Map<String,Object>> returnList=getSysData("INSIGHTS","AUTH");
				if (returnList != null && !returnList.isEmpty()) {
					for (Map<String, Object> object : returnList) {
						if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
								&& object.get(KEY_CODE).equals("USERNAME") && object.containsKey(VALUE)
								&& object.get(VALUE) != null) {
							user = object.get(VALUE).toString();
						} 
					}
				}
			}
			grantType = "embed_secret";
			logger.info(this.getClass().getName() + " getAuthToken and url: {}", url);
			logger.info(this.getClass().getName() + " getAuthToken and USERNAME: {}, PASSWORD: {},  GRANT_TYPE: {}", user, embedSecret, grantType);
			jsonObject.put(USERNAME, user);
			jsonObject.put("embed_secret", embedSecret);
			jsonObject.put(GRANT_TYPE, grantType);
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			for(HttpMessageConverter<?> obj : restTemplate.getMessageConverters()) {
				logger.info(this.getClass().getName() + " restTemplateMessageConverters : {}", obj.getClass());
			}
			HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
			logger.info(this.getClass().getName() + " getAuthToken and Request : {}", request);
			HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.POST, request, byte[].class);
			logger.info(this.getClass().getName() + " getAuthToken and Response : {}", response);
			String responseString  = StringUtils.toEncodedString(response.getBody(), StandardCharsets.UTF_16LE);
			responseMap = new ObjectMapper().readValue(responseString, HashMap.class);
			logger.info(this.getClass().getName() + " getAuthToken and AuthToken : {}", responseMap);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getAuthToken and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return responseMap;
	}
	
}
