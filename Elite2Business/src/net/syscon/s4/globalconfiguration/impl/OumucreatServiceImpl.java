package net.syscon.s4.globalconfiguration.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.syscon.s4.sa.usersystemsecurity.beans.OmsUsersList;
import net.syscon.s4.triggers.OmtsmService;
import net.syscon.s4.triggers.StaffMembersT2Service;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.AES;
import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.ADUser;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.UserCreation;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalconfiguration.OumucreatRepository;
import net.syscon.s4.globalconfiguration.OumucreatService;
import net.syscon.s4.globalrbac.OumpersoRepository;
import net.syscon.s4.globalrbac.OumusersRepository;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.sa.usersystemsecurity.beans.OmsUsersList;
import net.syscon.s4.triggers.OmtsmService;
import net.syscon.s4.triggers.StaffMembersT2Service;

@Service
public class OumucreatServiceImpl implements OumucreatService {
	@Autowired
	private OumucreatRepository oumucreatRepository;

	@Autowired
	private OumpersoRepository oumpRepository;

	@Autowired
	private OumusersRepository oumuRepository;
	@Autowired
	private StaffMembersT2Service staffMembersT2Service;
	@Autowired
	private OmtsmService omtsmService;
	@Autowired
	OumsysetService oumsysetService;
	
	public static final String AUTHORIZATION = "Authorization";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String GRANT_TYPE = "grant_type";
	public static final String SLASH = "/";
	public static final String API = "api";
	public static final String KEY_CODE = "KEY_CODE";
	public static final String VALUE = "VALUE";
	public static final String USERS = "users";
	
	private static Logger logger = LogManager.getLogger(OumucreatServiceImpl.class.getName());

	/**
	 * Creates new UserCreationServiceImpl class Object
	 */
	public OumucreatServiceImpl() {
		// UserCreationServiceImpl
	}

	@Transactional
	public Integer createUser(UserCreation userDeatils) {
		int result = 0;
		int caseLoadInsert = 0;
		int staffRoleAccess = 0;
		int insightsUserResp = 0;
		String pwd;
		if(userDeatils.getUserType().equals("AD")) {
			pwd = createPassword();
			userDeatils.setPassWord(pwd);
		}
		int count  = 0;
		OmsUsersList inputBean = new OmsUsersList();

		// Encrypting passowrd with Base64
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedPass = encoder.encodeToString(userDeatils.getPassWord().getBytes());

		inputBean.setPassword(encodedPass);
		inputBean.setUserId(userDeatils.getUserName());
		inputBean.setCreateUserId(userDeatils.getCreateUserId());
		count = oumucreatRepository.insertOmsUsersList(inputBean);
		logger.info(this.getClass().getName() + " createUser and count : {}", count);
		if (count > 0) {
			result = saveStaffDetals(userDeatils);
			logger.info(this.getClass().getName() + " createUser and result : {}", result);
			if (result > 0) {
				caseLoadInsert = saveUserCaseLoadData(userDeatils);
				logger.info(this.getClass().getName() + " createUser and caseLoadInsert : {}", caseLoadInsert);
				if(caseLoadInsert > 0) {
					staffRoleAccess=saveUserRoleAccess(userDeatils);
					logger.info(this.getClass().getName() + " createUser and staffRoleAccess : {}", staffRoleAccess);
					if(staffRoleAccess > 0 && userDeatils.getInsightUserFlag() != null && userDeatils.getInsightUserFlag().equals("Y")) { 
						Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(userDeatils.getMailId());
						if(matcher.matches()) {
							insightsUserResp = createInsightsUser(userDeatils);
						}
					}
					if(staffRoleAccess > 0 && insightsUserResp != 200) {
						staffRoleAccess = 2;
					}
				}
			}
		}
		return staffRoleAccess;
	}
	
	private String createPassword() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String specialCharacters = "!@#$";
	    String numbers = "1234567890";
	    String combinedChars = capitalCaseLetters + specialCharacters + numbers;
	    Random random = new Random();
	    char[] password = new char[8];

	    password[0] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	    password[1] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	    password[2] = numbers.charAt(random.nextInt(numbers.length()));
	   
	    for(int i = 3; i< 8 ; i++) {
	       password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	    }
	    return password.toString();
	}
	
	private Integer saveUserRoleAccess(UserCreation userDeatils) {
		Integer liReturn = 0;
		try {
			if(userDeatils.getRoleAccessList()!=null && !userDeatils.getRoleAccessList().isEmpty()) {
				for(StaffMemberRoles obj:userDeatils.getRoleAccessList()) {
					obj.setStaffId(userDeatils.getStaffId());
					obj.setCreateUserId(userDeatils.getCreateUserId());
				}
				liReturn=oumuRepository.staffMemberRolesInsertStaffMemberRoles(userDeatils.getRoleAccessList());
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " saveUserRoleAccess and Exception is : {}", e.getMessage());
		}
		return liReturn;
		
	}

	private Integer saveUserCaseLoadData(UserCreation userDeatils) {
		List<StaffAccessibleCaseloads> caseLoadList = new ArrayList<>();
		Integer liReturn = 0;
		if (userDeatils.getCaseLoadList() != null && !userDeatils.getCaseLoadList().isEmpty()) {
			for (String obj : userDeatils.getCaseLoadList()) {
				StaffAccessibleCaseloads caseLoadObj = new StaffAccessibleCaseloads();
				caseLoadObj.setStaffId(userDeatils.getStaffId());
				caseLoadObj.setCaseloadId(obj);
				caseLoadObj.setUpdateAllowedFlag("Y");
				caseLoadObj.setCreateUserId(userDeatils.getCreateUserId());
				caseLoadList.add(caseLoadObj);

			}
			liReturn = oumuRepository.staffAcInsertStaffAccessibleCaseloads(caseLoadList);

		}

		return liReturn;

	}

	private Integer saveStaffDetals(UserCreation userDeatils) {
		Integer liReturn = 0;
		String staffId;
		Integer staffSequence = null;
		Object objPreInsert = null;
		List<StaffMembers> insertList = new ArrayList<>();
		StaffMembers staffDetails = new StaffMembers();
		staffDetails.setMailId(userDeatils.getMailId());
		staffDetails.setLastName(userDeatils.getLastName());
		staffDetails.setFirstName(userDeatils.getFirstName());
		staffDetails.setPersonnelType(userDeatils.getPersonnelType());
		staffDetails.setAssignedCaseloadId(userDeatils.getAssignedCaseloadId());
		staffDetails.setUserId(userDeatils.getUserName());
		staffDetails.setUpdateAllowedFlag("N");
		staffDetails.setSuspendedFlag("N");
		staffDetails.setStatus(userDeatils.getStatus());
		if(userDeatils.getUserType().equals("AD")) {
			staffDetails.setAdUser("Y");
		}
		objPreInsert = oumpRepository.staffPreInsert();
		staffSequence = Integer.parseInt(objPreInsert.toString());
		staffDetails.setStaffId(staffSequence);
		if (userDeatils.getCaseLoadList() != null && !userDeatils.getCaseLoadList().isEmpty()) {
			staffDetails.setWorkingCaseloadId(userDeatils.getAssignedCaseloadId());
			
			
		}
		staffDetails.setCreateUserId(userDeatils.getCreateUserId());
		userDeatils.setStaffId(staffSequence);
		insertList.add(staffDetails);
		Integer lvProfileValue = staffMembersT2Service.getProfileValueFromSystemProfiles();
		staffDetails.setQueueClusterId((staffDetails.getStaffId()/lvProfileValue)+1);
		staffDetails=omtsmService.getOmtsam(staffDetails,null,"Inserting");
		staffId = oumpRepository.staffInsertStaffMembers(insertList);
		if (staffId != null && Integer.parseInt(staffId) > 0) {
			liReturn = 1;
		}
		return liReturn;
	}

	@Override
	public Integer getUserDetails(String userName) {
		return oumucreatRepository.getUserDeatils(userName);
	}

	@Override
	public Integer verifyEmailId(String emailId) {
		return oumucreatRepository.verifyEmailId(emailId);
	}

	@Override
	public List<StaffMembers> verifyEmailIdbyStaffId(String emailId, Integer staffId) {
		return oumucreatRepository.verifyEmailIdbyStaffId(emailId, staffId);
	}

	RestTemplate restTemplate = new RestTemplate();
	@Override
	public Integer createInsightsUser(UserCreation userDeatils) {
		int liReturn = 0;
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String insCreateUser = "";
		int userId = 0;
		try {
			logger.info(this.getClass().getName() + " createInsightsUser and User Deatils : {}", userDeatils);
			String authToken = getAuthToken();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(AUTHORIZATION, authToken);
			List<Map<String,Object>> returnList=oumsysetService.getSysData("INSIGHTS","INSIGHTS");
			if (returnList != null && !returnList.isEmpty()) {
				for (Map<String, Object> object : returnList) {
					if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INSI_PRIV_URL") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insSerUrl = object.get(VALUE).toString();
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_SITE_IDF") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						siteId = object.get(VALUE).toString();
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_CREATE_USER") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insCreateUser = object.get(VALUE).toString();
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insCreateUser;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("Email", userDeatils.getMailId().toLowerCase());
			jsonObject.put("FirstName", userDeatils.getFirstName());
			jsonObject.put("Lastname", userDeatils.getLastName());
			if (userDeatils.getPassWord() != null && userDeatils.getPassWord() != "") {
				jsonObject.put("Password", userDeatils.getPassWord());
			} else {
				String encryptedpwd = oumucreatRepository.getpwd(userDeatils.getMailId());
				Base64.Decoder decoder  = Base64.getDecoder();
				String decryptpwd = new String(decoder.decode(encryptedpwd));
				jsonObject.put("Password", decryptpwd);
			}
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			logger.info(this.getClass().getName() + " createInsightsUser and Input : "+ jsonObject.toString());
			logger.info(this.getClass().getName() + " createInsightsUser and headers : {}"+ headers);
			HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
			HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.POST, request, byte[].class);
			liReturn = ((ResponseEntity<byte[]>) response).getStatusCode().value();
			if (liReturn == 200) {
				userId = getInsightsUserId(userDeatils.getMailId().toLowerCase());
			}
			if (userId != 0) {
				addUserToGroup(userDeatils.getInsightsGropId(), userId);
			}
			logger.info(this.getClass().getName() + " createInsightsUser and Response : {}", response);
		} catch (HttpClientErrorException e) {
			liReturn = e.getRawStatusCode();
			logger.error(this.getClass().getName() + " createInsightsUser and HttpClientErrorException is : {}", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " createInsightsUser and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return liReturn;
	}
	
	@Override
	public String getAuthToken() {
		String authToken = "";
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String insAuthToken = "";
		String user = "";
		String password = "";
		String grantType = "";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			List<Map<String,Object>> responseList=oumsysetService.getSysData("INSIGHTS","INSIGHTS");
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
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insAuthToken;
			JSONObject jsonObject = new JSONObject();
			List<Map<String,Object>> returnList=oumsysetService.getSysData("INSIGHTS","AUTH");
			if (returnList != null && !returnList.isEmpty()) {
				for (Map<String, Object> object : returnList) {
					if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("USERNAME") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						user = object.get(VALUE).toString();
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("PASSWORD") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						String encryptPwd = object.get(VALUE).toString();
						Base64.Decoder decoder = Base64.getDecoder();
						password = new String(decoder.decode(encryptPwd));
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("GRANT_TYPE") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						grantType = object.get(VALUE).toString();
					}
				}
			}
			logger.info(this.getClass().getName() + " getAuthToken and USERNAME, PASSWORD,  GRANT_TYPE: {}", user, password, grantType);
			jsonObject.put(USERNAME, user);
			jsonObject.put(PASSWORD, password);
			jsonObject.put(GRANT_TYPE, grantType);
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
			HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.POST, request, byte[].class);
			logger.info(this.getClass().getName() + " getAuthToken and Response : {}", response);
			String responseString  = StringUtils.toEncodedString(response.getBody(), StandardCharsets.UTF_16LE);
			Map<String, Object> responseMap = new ObjectMapper().readValue(responseString, HashMap.class);
			authToken = responseMap.get("token_type") + " " + (String) responseMap.get("access_token");
			logger.info(this.getClass().getName() + " getAuthToken and AuthToken : {}", authToken);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getAuthToken and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return authToken;
	}


	@Override
	public void migrateADUser(String loggedInUser) {
		/*
		 * Get ALL User from AD_USER Table
		 * Iterate them and decrypt the AES password
		 * Encrypt the password again using base64
		 * Update those users in OMS_USER_LIST table
		 * 
		 */
		AES aes = new AES();
		List<ADUser> adusrs = oumucreatRepository.getADUserDeatils();
		adusrs.forEach((ADUser aduser)-> {
			//gger.info
			try {
				String decryptpwd = aes.decrypt(aduser.getPassword());
				logger.info("Migartion User "+aduser.getUserId() + aduser.getPassword());
				Base64.Encoder encoder = Base64.getEncoder();
				String encodedPass = encoder.encodeToString(decryptpwd.getBytes());
				OmsUsersList omsUserList = new OmsUsersList();
				omsUserList.setUserId(aduser.getUserId());
				omsUserList.setModifyUserId(loggedInUser);
				omsUserList.setPassword(encodedPass);
				oumucreatRepository.UpdateOmsUser(omsUserList);
			} catch(Exception ex) {
				logger.error("Migration Failed for user :{} exception :{}",aduser.getUserId(), ex.getMessage());
			}
			//Call update oumuserlist api
		});
	}
	
	@Override
	public List<ReferenceCodes> getInsightsUserGroups() {
		List<ReferenceCodes> groups = new ArrayList<ReferenceCodes>();
		String url;
		String insSerUrl = "";
		String siteId = "";
		String insUserGroups = "";
		try {
			String authToken = getAuthToken();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(AUTHORIZATION, authToken);
			List<Map<String,Object>> responseList=oumsysetService.getSysData("INSIGHTS","INSIGHTS");
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
							&& object.get(KEY_CODE).equals("INS_USER_GROUPS") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insUserGroups = object.get(VALUE).toString();
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insUserGroups;
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			logger.info(this.getClass().getName() + " getInsightsUserGroups and headers : {}"+ headers);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
			String responseString  = StringUtils.toEncodedString(response.getBody(), StandardCharsets.UTF_8);
			Map<String, Object> responseMap = new ObjectMapper().readValue(responseString, HashMap.class);
			List<Map<String, Object>> groupList = (List<Map<String, Object>>) responseMap.get("GroupList");
			for(Map<String, Object> group : groupList) {
				if(!"System Administrator".equals(group.get("Name").toString())) {
					ReferenceCodes referenceCodes = new ReferenceCodes();
					referenceCodes.setCode(group.get("Id").toString());
					referenceCodes.setDescription(group.get("Name").toString());
					groups.add(referenceCodes);
				}
			}
			logger.info(this.getClass().getName() + " getInsightsUserGroups and Response : {}", response);
		} catch (HttpClientErrorException e) {
			logger.error(this.getClass().getName() + " getInsightsUserGroups and HttpClientErrorException is : {}", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getInsightsUserGroups and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return groups;
	}
	
	@Override
	public Integer getInsightsUserId(String mailId) {
		int liReturn = 0;
		String url;
		String insSerUrl = "";
		String siteId = "";
		String insUserId = "";
		try {
			String authToken = getAuthToken();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(AUTHORIZATION, authToken);
			List<Map<String,Object>> responseList=oumsysetService.getSysData("INSIGHTS","INSIGHTS");
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
							&& object.get(KEY_CODE).equals("INS_USER_ID") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insUserId = object.get(VALUE).toString();
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insUserId + SLASH + mailId;
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			logger.info(this.getClass().getName() + " createInsightsUser and headers : {}"+ headers);
			HttpEntity<String> request = new HttpEntity<String>(headers);
			HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
			String responseString  = StringUtils.toEncodedString(response.getBody(), StandardCharsets.UTF_8);
			Map<String, Object> responseMap = new ObjectMapper().readValue(responseString, HashMap.class);
			liReturn =  (int) responseMap.get("UserId");
			logger.info(this.getClass().getName() + " getInsightsUserId and Response : {}", response);
		} catch (HttpClientErrorException e) {
			logger.error(this.getClass().getName() + " getInsightsUserId and HttpClientErrorException is : {}", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " getInsightsUserId and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return liReturn;
	}
	
	@Override
	public Integer addUserToGroup(List<String> groupIdList, int userId) {
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String insAddUser = "";
		int count = 0;
		int lireturn = 0;
		try {
			logger.info(this.getClass().getName() + " addUserToGroup and GroupId User Id : {}", groupIdList, userId);
			String authToken = getAuthToken();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set(AUTHORIZATION, authToken);
			List<Map<String,Object>> responseList=oumsysetService.getSysData("INSIGHTS","INSIGHTS");
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
							&& object.get(KEY_CODE).equals("INS_ADD_USER_TO_GRP") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insAddUser = object.get(VALUE).toString();
					}
				}
			}
			for(String groupId : groupIdList) {
				url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insAddUser +SLASH + Integer.parseInt(groupId) + SLASH + USERS;
				JSONObject jsonObject = new JSONObject();
				ArrayList<Integer> userList = new ArrayList<Integer>();
				userList.add(userId);
				jsonObject.put("Id", userList);
				if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
					restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
				}
				logger.info(this.getClass().getName() + " addUserToGroup and Input : "+ jsonObject.toString());
				logger.info(this.getClass().getName() + " addUserToGroup and headers : {}"+ headers);
				HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
				HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.POST, request, byte[].class);
				logger.info(this.getClass().getName() + " addUserToGroup and Response : {}", response);
				int ststusCode = ((ResponseEntity<byte[]>) response).getStatusCode().value();
				if(ststusCode == 200) {
					count++;
				}
			}
		} catch (HttpClientErrorException e) {
			lireturn = 0;
			logger.error(this.getClass().getName() + " addUserToGroup and HttpClientErrorException is : {}", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			lireturn = 0; 
			logger.error(this.getClass().getName() + " addUserToGroup and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		if(count == groupIdList.size()) {
			lireturn = 1;
		} else {
			lireturn = 0; 
		}
		return lireturn;
	}
	
	@Override
	public int resetADUser(String loggedInUser) {
		int success = 0;
		// TODO 
		// Fetch ALL AD USER
		try {
			List<OmsUsersList> resetADUSerList = new ArrayList<OmsUsersList>();
			OmsUsersList omsUserList ;
			List<ADUser> adusrs = oumucreatRepository.getADUserDeatils();
			if(adusrs != null) {
				for (ADUser adUser: adusrs) {
					omsUserList = new OmsUsersList();
					String pwd = createPassword();
					Base64.Encoder encoder = Base64.getEncoder();
					String encodedPass = encoder.encodeToString(pwd.getBytes());
					
					omsUserList.setUserId(adUser.getUserId());
					omsUserList.setModifyUserId(loggedInUser);
					omsUserList.setPassword(encodedPass);
					resetADUSerList.add(omsUserList);
				}
				oumucreatRepository.ResetADUser(resetADUSerList);
			}
		} catch(Exception ex) {
			logger.error(this.getClass().getName() + " resetADUser and Exception is : {}", ex.getMessage());
			ex.printStackTrace();
			success = 1;
		}
			
			
		return success;
	}
}
