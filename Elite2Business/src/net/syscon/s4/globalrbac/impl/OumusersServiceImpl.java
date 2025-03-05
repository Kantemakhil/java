package net.syscon.s4.globalrbac.impl;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.Utilities;
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.Images;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.UserCreation;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalconfiguration.OumucreatService;
import net.syscon.s4.globalrbac.OumusersRepository;
import net.syscon.s4.globalrbac.OumusersService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.CaseloadAgencyLocationsCommitBean;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.im.beans.PersonnelIdentifications;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.im.beans.StaffAccessibleCaseloadsCommitBean;
import net.syscon.s4.im.beans.StaffMemberRolesCommitBean;
import net.syscon.s4.im.beans.StaffMembersCommitBean;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsService;
import net.syscon.s4.triggers.OmtsmService;
import net.syscon.s4.triggers.StaffAccessibleCaseloadsT1Service;

/**
 * Class OumusersServiceImpl
 */
@Service
public class OumusersServiceImpl extends BaseBusiness implements OumusersService {

	@Autowired
	private OumusersRepository oumuRepository;

	@Autowired
	private OmsUtilsService omsUtilsService;
	@Autowired
	private OmtsmService omtsmService;
	@Autowired
	private StaffAccessibleCaseloadsT1Service staffAccessibleCaseloadsT1Service;
	@Autowired
	OumsysetService oumsysetService;
	@Autowired
	OumucreatService oumucreatService;

	RestTemplate restTemplate = new RestTemplate();

	private static Logger logger = LogManager.getLogger(OumusersServiceImpl.class);

	public static final String CONSTANTVALUET = "2";
	public static final String CONSTANTVALUETH = "3";
	public static final String CONSTANTVALUEF = "4";
	public static final String CONSTANTVALUEFI = "5";
	public static final String VALID = "valid";
	public static final String SLASH = "/";
	public static final String API = "api";
	public static final String KEY_CODE = "KEY_CODE";
	public static final String VALUE = "VALUE";
	public static final String USERS = "users";
	public static final String GROUPS =  "groups";

	/**
	 * Creates new OumusersServiceImpl class Object
	 */
	public OumusersServiceImpl() {
		// OumusersServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public Caseloads cgfkchkStaffAcStaffAcCsl(final Caseloads paramBean) {
		return (Caseloads) oumuRepository.cgfkchkStaffAcStaffAcCsl(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 */
	public List<OmsRoles> cgfkchkStaffMemberRolesSt(final OmsRoles paramBean) {
		return (List<OmsRoles>) oumuRepository.cgfkchkStaffMemberRolesSt(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * 
	 */
	public List<PersonnelIdentifications> cgrichkStaffMembers(final PersonnelIdentifications paramBean) {
		return (List<PersonnelIdentifications>) oumuRepository.cgrichkStaffMembers(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param paramBean
	 *
	 * 
	 */
	public List<AgencyLocations> cgfkchkCalCsldAlAgyLoc(final AgencyLocations paramBean) {
		return (List<AgencyLocations>) oumuRepository.cgfkchkCalCsldAlAgyLoc(paramBean);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<StaffMembers> staffExecuteQuery(final StaffMembers searchRecord) {
		return oumuRepository.staffExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * 
	 */
	@Transactional
	public String staffCommit(final StaffMembersCommitBean commitBean) {
		String liReturn = null;
		StaffMembers staffMem = null;
		if (commitBean.getUpdateList() != null && !commitBean.getUpdateList().isEmpty()) {
			for (StaffMembers bean : commitBean.getUpdateList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
				if (!VALID.equals(validateuserId(bean))) {
					return validateuserId(bean);
				}
			}
			for (StaffMembers bean : commitBean.getUpdateList()) {
				staffMem = oumuRepository.triggerStaffExceQuery(bean.getStaffId());
				omtsmService.getOmtsam(bean, staffMem, "Updating");
			}
			liReturn = oumuRepository.staffUpdateStaffMembers(commitBean.getUpdateList());

		}
		if (commitBean.getDeleteList() != null && !commitBean.getDeleteList().isEmpty()) {
			for (StaffMembers bean : commitBean.getDeleteList()) {
				bean.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumuRepository.staffDeleteStaffMembers(commitBean.getDeleteList());
		}
		return liReturn;
	}

	public String validateuserId(final StaffMembers bean) {
		if ((bean.getUserId() != null && !bean.getUserId().equals(bean.getUserIdVal())) || bean.getStaffId() == 0) {
			Integer checkUser = oumuRepository.userIdCheckCur(bean);
			if (checkUser > 0) {
				return CONSTANTVALUET;
			}
			String sysUsers = oumuRepository.cSysUsers(bean);
			if (sysUsers != null) {
				return CONSTANTVALUETH;
			}
			Integer chars = oumuRepository.validateuseridChar(bean);
			if (chars == 0) {
				return CONSTANTVALUEF;
			}

			omsUtilsService.validateUserid(bean);

		}
		return VALID;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord StaffAccessibleCaseloads
	 *
	 * @return List<StaffAccessibleCaseloads>
	 * 
	 */
	public List<StaffAccessibleCaseloads> staffAcExecuteQuery(final StaffAccessibleCaseloads searchRecord) {
		final List<StaffAccessibleCaseloads> lstStaffAccCl = oumuRepository.staffAcExecuteQuery(searchRecord);
		Caseloads objCaseloads = null;
		final List<StaffAccessibleCaseloads> lstStaffAccCls = new ArrayList<StaffAccessibleCaseloads>();
		Caseloads objClsDesc = null;
		if (lstStaffAccCl != null && lstStaffAccCl.size() > 0) {
			for (final StaffAccessibleCaseloads objStaffCls : lstStaffAccCl) {
				objCaseloads = createCaseLoads(objStaffCls);
				objClsDesc = cgfkchkStaffAcStaffAcCsl(objCaseloads);
				if (objClsDesc != null) {
					objStaffCls.setDescription(objClsDesc.getDescription());
					lstStaffAccCls.add(objStaffCls);
				}
			}
		}
		return lstStaffAccCls;
	}

	/**
	 * This method is used to create new Caseloads object
	 * 
	 * @param objStaffCls StaffAccessibleCaseloads
	 * 
	 * @return AgencyLocations
	 */

	private Caseloads createCaseLoads(final StaffAccessibleCaseloads objStaffCls) {
		final Caseloads objCaseloads = new Caseloads();
		objCaseloads.setCaseloadId(objStaffCls.getCaseloadId());
		return objCaseloads;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * 
	 */
	@Transactional
	public Integer staffAcCommit(final StaffAccessibleCaseloadsCommitBean commitBean) {
		int liReturn = 0;
		Integer valueAssignedCount=0;
		Integer valueWorkingCount=0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (StaffAccessibleCaseloads obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumuRepository.staffAcInsertStaffAccessibleCaseloads(commitBean.getInsertList());
		}
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {
			for (StaffAccessibleCaseloads obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumuRepository.staffAcUpdateStaffAccessibleCaseloads(commitBean.getUpdateList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (StaffAccessibleCaseloads obj : commitBean.getDeleteList()) {
				valueAssignedCount = oumuRepository.getValueAssignedCountForStaff(obj);
				if (valueAssignedCount > 0) {
					return 2;
				}

				valueWorkingCount = oumuRepository.getValueWorkingCountForStaff(obj);
				if(valueWorkingCount > 0) {
					return 3;
				}
				obj.setModifyUserId(commitBean.getCreateUserId());
				staffAccessibleCaseloadsT1Service.staffAccessibleCaseloadsT1(obj);
			}
			liReturn = oumuRepository.staffAcDeleteStaffAccessibleCaseloads(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord
	 *
	 * 
	 */
	public List<StaffMemberRoles> staffMemberRolesExecuteQuery(final StaffMemberRoles searchRecord) {
		return oumuRepository.staffMemberRolesExecuteQuery(searchRecord);

	}

	/**
	 * Insert the records from database table
	 *
	 * @param commitBean
	 *
	 * 
	 */
	@Transactional
	public Integer staffMemberRolesCommit(final StaffMemberRolesCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
			for (StaffMemberRoles obj : commitBean.getInsertList()) {
				obj.setCreateUserId(commitBean.getCreateUserId());
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumuRepository.staffMemberRolesInsertStaffMemberRoles(commitBean.getInsertList());
		}
		if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
			for (StaffMemberRoles obj : commitBean.getDeleteList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumuRepository.staffMemberRolesDeleteStaffMemberRoles(commitBean.getDeleteList());
		}
		return liReturn;
	}

	/**
	 * This method is used to create new BeanPropertySqlParameterSource
	 * 
	 * @param objCaseLoads CaseloadAgencyLocations
	 * 
	 * @return AgencyLocations
	 */
	private AgencyLocations createAgencyLoc(final CaseloadAgencyLocations objCaseLoads) {
		final AgencyLocations objAgy = new AgencyLocations();
		objAgy.setAgyLocId(objCaseLoads.getAgyLocId());
		return objAgy;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord CaseloadAgencyLocations
	 *
	 * @return List<CaseloadAgencyLocations>
	 */
	public List<CaseloadAgencyLocations> calExecuteQuery(final CaseloadAgencyLocations searchRecord) {
		final List<CaseloadAgencyLocations> lstCaseLoadAgy = oumuRepository.calExecuteQuery(searchRecord);

		AgencyLocations objAgy = null;
		final List<CaseloadAgencyLocations> lstAgency = new ArrayList<CaseloadAgencyLocations>();
		List<AgencyLocations> lstAgencyLoc = new ArrayList<AgencyLocations>();
		if (lstCaseLoadAgy != null && lstCaseLoadAgy.size() > 0) {
			for (final CaseloadAgencyLocations objCaseLoads : lstCaseLoadAgy) {
				objAgy = createAgencyLoc(objCaseLoads);
				lstAgencyLoc = cgfkchkCalCsldAlAgyLoc(objAgy);
				for (final AgencyLocations objAgyLoc : lstAgencyLoc) {
					objCaseLoads.setDescription(objAgyLoc.getDescription());
					objCaseLoads.setDspAgencyLocationType(objAgyLoc.getAgencyLocationType());
					lstAgency.add(objCaseLoads);
				}
			}
		}
		return lstAgency;
	}

	/**
	 * Insert the records from database table
	 *
	 * @param lstCAL
	 *
	 * 
	 */
	@Transactional
	public Integer calCommit(final CaseloadAgencyLocationsCommitBean commitBean) {
		int liReturn = 0;
		if (commitBean.getUpdateList() != null && commitBean.getUpdateList().size() > 0) {

			for (CaseloadAgencyLocations obj : commitBean.getUpdateList()) {
				obj.setModifyUserId(commitBean.getCreateUserId());
			}
			liReturn = oumuRepository.calUpdateCaseloadAgencyLocations(commitBean.getUpdateList());
		}
		return liReturn;
	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<RecordGroup> rgStaffAssignedCaseloadRecordGroup() {
		return oumuRepository.rgStaffAssignedCaseloadRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<OmsRoles> rgStaffMemberRolesRoleRecordGroup() {
		return oumuRepository.rgStaffMemberRolesRoleRecordGroup();

	}

	/**
	 * This method is used to execute a record group
	 *
	 * 
	 */
	public List<Caseloads> rgStaffAcCaseloadIdRecordGroup() {
		return oumuRepository.rgStaffAcCaseloadIdRecordGroup();

	}

	public List<Images> imageExecuteQuery(final StaffMembers bean) {
		return oumuRepository.imageExecuteQuery(bean);

	}

	@Override
	public List<UserCreation> insightsExecuteQuery(StaffMembers searchBean) {
		UserCreation userCreation = new UserCreation();
		List<UserCreation> userCreationList = new ArrayList<UserCreation>();
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String insSearchUser = "";
		try {
			HttpHeaders headers = new HttpHeaders();
			String authToken = oumucreatService.getAuthToken();
			headers.set("Authorization", authToken);
			List<Map<String, Object>> returnList = oumsysetService.getSysData("INSIGHTS", "INSIGHTS");
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
							&& object.get(KEY_CODE).equals("INS_SEARCH_USER") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insSearchUser = object.get(VALUE).toString();
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insSearchUser + SLASH + searchBean.getMailId();
			URI uri = new URI(url);
			HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
			HttpEntity<byte[]> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, byte[].class);
			String responseString = StringUtils.toEncodedString(response.getBody(), StandardCharsets.UTF_8);
			Map<String, Object> responseMap = new ObjectMapper().readValue(responseString, HashMap.class);
//			userCreation.setMailId((String) responseMap.get("Email"));
//			userCreation.setStaffName((String) responseMap.get("DisplayName"));
//			staffMembers.setUserId(String.valueOf(responseMap.get("UserId")));
			if ((boolean) responseMap.get("IsActive")) {
				userCreation.setStatus("Active");
			} else {
				userCreation.setStatus("Inactive");
			}
			List<String> groupIdList = getGroupIdList(searchBean.getMailId());
			userCreation.setInsightsGropId(groupIdList);
			userCreationList.add(userCreation);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userCreationList;
	}

	@Override
	public List<String> getGroupIdList(String userId) {
		List<String> groupIdList = new ArrayList<>();
		String groupsUrl = "";
		String insSerUrl = "";
		String siteId = "";
		String insSearchUser = "";
		try {
			HttpHeaders headers = new HttpHeaders();
			String authToken = oumucreatService.getAuthToken();
			headers.set("Authorization", authToken);
			List<Map<String, Object>> returnList = oumsysetService.getSysData("INSIGHTS", "INSIGHTS");
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
							&& object.get(KEY_CODE).equals("INS_SEARCH_USER") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insSearchUser = object.get(VALUE).toString();
					}
				}
			}
			groupsUrl = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insSearchUser + SLASH + userId + SLASH + GROUPS;
			URI groupsUri = new URI(groupsUrl);
			HttpEntity<String> groupsRequestEntity = new HttpEntity<>(null, headers);
			HttpEntity<byte[]> groupsResponse = restTemplate.exchange(groupsUri, HttpMethod.GET, groupsRequestEntity,
					byte[].class);
			String groupsResponseString = StringUtils.toEncodedString(groupsResponse.getBody(), StandardCharsets.UTF_8);
			Map<String, Object> groupsResponseMap = new ObjectMapper().readValue(groupsResponseString, HashMap.class);
			List<Map<String, Object>> groupList = (List<Map<String, Object>>) groupsResponseMap.get("GroupList");
			for (Map<String, Object> group : groupList) {
				groupIdList.add(group.get("Id").toString());
			}
		} catch (Exception e) {
			logger.error("In ResetPassword method : ", e);
			e.printStackTrace();
		}
		return groupIdList;
	}



	@Override
	public Integer deleteUserFromGroups(List<String> groupIdList, int userId) {
		int liReturn = 0;
		int statusCode = 0;
		int count = 0;
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String groups = "";
		try {
			logger.info(this.getClass().getName() + " deleteUserFromGroups and groupIdList, user : {}", groupIdList, userId);
			String authToken = oumucreatService.getAuthToken();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", authToken);
			List<Map<String, Object>> returnList = oumsysetService.getSysData("INSIGHTS", "INSIGHTS");
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
					} else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null &&
					  object.get(KEY_CODE).equals("INS_USER_GROUPS") && object.containsKey(VALUE)
					  && object.get(VALUE) != null) { 
						groups = object.get(VALUE).toString();
					}
				}
			}
			List<Integer> userList = new ArrayList<Integer>();
			userList.add(userId);
			for(String groupId : groupIdList) {
				url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + groups + SLASH + groupId + SLASH + USERS;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("Id", userList);
				if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
					restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
				}
				logger.info(this.getClass().getName() + " deleteUserFromGroups and Input : " + jsonObject.toString());
				logger.info(this.getClass().getName() + " deleteUserFromGroups and headers : {}" + headers);
				HttpEntity<String> request = new HttpEntity<String>(jsonObject.toString(), headers);
				HttpEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.DELETE, request, byte[].class);
				statusCode = ((ResponseEntity<byte[]>) response).getStatusCode().value();
				if (statusCode == 204) {
					count++;
				}
				logger.info(this.getClass().getName() + " deleteUserFromGroups and Response : {}", response);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " deleteUserFromGroups and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		if(count == groupIdList.size()) {
			liReturn = 1;
		} 
		return liReturn;
	}
	
	@Transactional
	public StaffMembers resetPassword(StaffMembers objStaffMembers) {
		Integer result = 0;
		String pwd;
		String encryptPwd = null;
		objStaffMembers.setPassword(createPassword());
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedPass = encoder.encodeToString(objStaffMembers.getPassword().getBytes());
		result = oumuRepository.staffPasswordReset(objStaffMembers, encodedPass);
		objStaffMembers.setPasswordReturnVal(result);
		return objStaffMembers;
	}

	private String createPassword() {
		String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String smallCaseLetters = "abcdefghijklmnopqrstuvwxyz";
		String specialCharacters = "@$!%*?&";
		String numbers = "1234567890";
		String combinedChars = capitalCaseLetters + smallCaseLetters + specialCharacters + numbers;
		Random random = new Random();
		char[] password = new char[6];

		password[0] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
		password[1] = smallCaseLetters.charAt(random.nextInt(smallCaseLetters.length()));
		password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
		password[3] = numbers.charAt(random.nextInt(numbers.length()));

		for (int i = 4; i < 6; i++) {
			password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
		}
		String str = String.valueOf(password);
		return str;
	}

	@Override
	public Integer removeInsightUser(UserCreation userDeatils) {
		int liReturn = 0;
		String url = "";
		String insSerUrl = "";
		String siteId = "";
		String insUserId = "";
		int userId = 0;
		try {
			logger.info(this.getClass().getName() + " removeInsightUser and User Deatils : {}", userDeatils);
			String authToken = oumucreatService.getAuthToken();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", authToken);
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
					}
					else if (object.containsKey(KEY_CODE) && object.get(KEY_CODE) != null
							&& object.get(KEY_CODE).equals("INS_USER_ID") && object.containsKey(VALUE)
							&& object.get(VALUE) != null) {
						insUserId= object.get(VALUE).toString();
					}
				}
			}
			url = insSerUrl + SLASH + API + SLASH + siteId + SLASH + insUserId + SLASH + userDeatils.getMailId();
			if(!Utilities.hasJackson2HttpMessageConverter(restTemplate)) {
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			}
			logger.info(this.getClass().getName() + " removeInsightUser and headers : {}"+ headers);
			HttpEntity<String> request = new HttpEntity<String>(null, headers);
			HttpEntity<byte[]> response =  restTemplate.exchange(url, HttpMethod.DELETE, request, byte[].class);
			liReturn = ((ResponseEntity<byte[]>) response).getStatusCode().value();	
			logger.info(this.getClass().getName() + " removeInsightUser and Response : {}", response);
		} catch (HttpClientErrorException e) {
			liReturn = e.getRawStatusCode();
			logger.error(this.getClass().getName() + " removeInsightUser and HttpClientErrorException is : {}", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " removeInsightUser and Exception is : {}", e.getMessage());
			e.printStackTrace();
		}
		return liReturn;
	}
	
}