package net.syscon.s4.global.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.AES;
import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.EliteViewLog;
import net.syscon.s4.common.beans.EmailUser;
import net.syscon.s4.common.beans.HelpMedia;
import net.syscon.s4.common.beans.MenuNode;
import net.syscon.s4.common.beans.MenuSecurities;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.UserRoleInfo;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.WorkFlowFolders;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.core.EliteDateService;
import net.syscon.s4.externalmessage.service.AuditLogInjectorService;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.global.Omss40Repository;
import net.syscon.s4.global.Omss40Service;
import net.syscon.s4.globalconfiguration.OumsysetService;
import net.syscon.s4.globalrbac.OumusersRepository;
import net.syscon.s4.im.beans.ModulePrivileges;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.admin.OumtagreService;

/**
 * Class Omss40ServiceImpl
 */
@Service
public class Omss40ServiceImpl extends BaseBusiness implements Omss40Service {

	private static Logger logger = LogManager.getLogger(Omss40ServiceImpl.class);

	@Autowired
	private Omss40Repository omss40Repository;

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private OumusersRepository oumuRepository;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private OumsysetService oumsysetService;
	
	@Autowired
	private OumtagreService oumtagreService;
	
	@Autowired
	private AuditLogInjectorService auditLogInjectorService;
	
	@Autowired
	private EliteDateService eliteDateService;

	/**
	 * Creates new Omss40Business class Object
	 */
	public Omss40ServiceImpl() {
		// Omss40ServiceImpl
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<SystemProfiles> preFormPtypeClient(final SystemProfiles paramBean) {
		return omss40Repository.preFormPtypeClient(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<SystemProfiles> preFormPtypeSys(final SystemProfiles paramBean) {
		return omss40Repository.preFormPtypeSys(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<MenuSecurities> whenNewFormInstanceMenuNameCur(final MenuSecurities paramBean) {
		return omss40Repository.whenNewFormInstanceMenuNameCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public List<WorkFlowFolders> buildworkFlowmenurgmainWorkflowCur(final WorkflowScreens paramBean) {
		return omss40Repository.buildworkFlowmenurgmainWorkflowCur(paramBean);
	}

	/**
	 * This method is execute a Dao class method when trigger event is fired
	 *
	 * @param params
	 *
	 * @throws Exception
	 */
	public WorkflowScreens buildworkFlowmenurgsubWorkflowCur(final WorkflowScreens paramBean) {
		return omss40Repository.buildworkFlowmenurgsubWorkflowCur(paramBean);
	}

	/**
	 * @param pUser
	 *
	 * @throws Exception
	 */
	public String getCurrentCaseload(final String pUser) {
		String workingCaseLoad;
		workingCaseLoad=omss40Repository.getCurrentCaseload(pUser);
		if(workingCaseLoad == null) {
			List<StaffAccessibleCaseloads> caseLoadList=oumuRepository.staffCaseLoadData(pUser);
			if(caseLoadList!=null && !caseLoadList.isEmpty()) {
				workingCaseLoad=caseLoadList.get(0).getCaseloadId();
			}

		}

		return workingCaseLoad;
	}

	/**
	 *
	 *
	 * @param paramBean
	 *
	 * @throws Exception
	 */
	public List<MenuSecurities> mainpopList(final MenuSecurities paramBean) {
		return omss40Repository.mainpopList(paramBean);
	}

	/**
	 * This method is used to execute a record group
	 *
	 * @throws SQLException
	 */
	public List<ReferenceCodes> cgfkStaffDspDescriptionRecordGroup() {
		return omss40Repository.cgfkStaffDspDescriptionRecordGroup();

	}

	/**
	 * This method is used to execute a getCaseloads
	 *
	 * @throws SQLException
	 */
	public List<Caseloads> getCaseloads(final StaffMembers searchBean) {
		return omss40Repository.getCaseloads( searchBean);
	}

	public Map<String, List<String>> getCaseLoadAgencies(String userId) {
		List<VHeaderBlock> data = omss40Repository.getCaseLoadAgencies(userId);
		Map<String, List<String>> result = new HashMap<>();
		for(VHeaderBlock hb: data) {
			List<String> agencies = null;
			if(result.containsKey(hb.getCaseLoadId())) {
				agencies = result.get(hb.getCaseLoadId());
			}else {
				agencies = new ArrayList<>();
				result.put(hb.getCaseLoadId(), agencies);
			}
			agencies.add(hb.getAgyLocId());
		}
		return result;
	}

	public String changeWorkingCaseLoad(final String lvCaseloadId,final  String userName) {
//	return omss40Repository.changeWorkingCaseLoad(lvCaseloadId);
		return commonService.changeWorkingCaseload(lvCaseloadId,userName);

	}

	public UserRoleInfo getUserRoleInfo(final String userId) {
		UserRoleInfo info = this.getUserRoleInfoCache().get(userId);
		info = new UserRoleInfo();
		List<MenuNode> result = new ArrayList<>();
		List<MenuNode> nodes = omss40Repository.getMenus(userId);
		Map<String, String> roles = new HashMap<>();

		List<ModulePrivileges> userRoles = omss40Repository.getUserRolesInfo(userId);
		if (userRoles != null && !userRoles.isEmpty())
		{
			for (ModulePrivileges pre : userRoles)
			{
				if(pre.getAccessPrivilege().equals("A") || pre.getAccessPrivilege().equals("V"))
					roles.put(pre.getModuleName(), "full");
				else
					roles.put(pre.getModuleName(), "read");
			}
		}
		getMenuNodes(nodes, 0, Long.valueOf(1), result);
		System.out.println(result);
		removeUnauthorizedNodes(result, roles);
		info.setUserMenus(result);
		info.setUserRoles(roles);
		this.getUserRoleInfoCache().put(userId, info);
		return info;
	}

	private void removeUnauthorizedNodes(List<MenuNode> result, Map<String, String> roles) {
		Iterator<MenuNode> menuNodeIterator = result.iterator();
		while (menuNodeIterator.hasNext())
		{
			MenuNode menuNode = menuNodeIterator.next();
			if (menuNode.getChildren() != null) {
				removeUnauthorizedNodes(menuNode.getChildren(), roles);
			}
			if (menuNode.getHref() != null && !menuNode.getHref().isEmpty()	&& !roles.containsKey(menuNode.getHref().replaceAll("/", "")))
			{
				menuNodeIterator.remove();
			}
			if ((menuNode.getChildren() == null || menuNode.getChildren().isEmpty()) && menuNode.getHref() == null)
				menuNodeIterator.remove();  // remove parent node with no children and null href.

		}
	}

	private int getMenuNodes(List<MenuNode> nodes, int index, Long parantid, List<MenuNode> result) {
		while (index < nodes.size()) {
			final MenuNode node = nodes.get(index);
			if (node.getParentId().equals(parantid)) {
				List<MenuNode> children = new ArrayList<>();
				int newIndex = getMenuNodes(nodes, index + 1, node.getId(), children);
				if (!children.isEmpty()) {
					index = newIndex;
					node.setChildren(children);
				} else {
					index++;
				}
				if (node.getHref() != null && !node.getHref().isEmpty()) {
					if(node.getDynamicForm() == null && node.getInsDashboard() == null) {
						node.setHref("/" + node.getHref());
					} else {
						System.out.println(node.getHref());
					}
				}
				result.add(node);
			} else {
				return index;
			}
		}
		return index;
	}

	private Cache<String, UserRoleInfo> getUserRoleInfoCache() {
		return cacheManager.getCache("userRoleInfo", String.class, UserRoleInfo.class);
	}

	@Override
	public List<ProfileCodes> searchProfileCodes(List<String> profileType) {
		return omss40Repository.searchProfileCodes(profileType);
	}

	public List<VHeaderBlock> getAssignedOffenderList(final String userId, final String currentCaseLoadType) {
		return omss40Repository.getAssignedOffenderList(userId,currentCaseLoadType);

	}
	@Override
	public List<VHeaderBlock> updateRecentOffenderList(VHeaderBlock paramBean) {
		return omss40Repository.updateRecentOffenderList(paramBean);
	}
	@Override
	public List<VHeaderBlock> getRecentOffenderList(String userId,String caseLoadId ,Integer count) {
		return omss40Repository.getRecentOffenderList(userId, caseLoadId, count);
	}

	@Override
	public String getServerTime() {
		return omss40Repository.getServerTime();
	}

	@Override
	public String getCurrentCaseloadType(final String userId) {
		return omss40Repository.getCurrentCaseloadType(userId);
	}

	@Override
	public StaffMembers getCurrentStaffDetail(final String userId) {
		return omss40Repository.getCurrentStaffDetail(userId);
	}

	@Override
	public List<HelpMedia> getHelpMedia(String moduleName) {
		return omss40Repository.getHelpMedia(moduleName);
	}

	public int iwpOnScreen(String moduleName) {
		return omss40Repository.iwpOnScreen(moduleName);
	}

	
	@Override
	public EmailUser getDecryptPwd(String mailId) {
		AES aes = new AES();
		EmailUser emailUser = new EmailUser();
		String userId = omss40Repository.getUserId(mailId);
		if(userId != null) {
			String encryptPwd = omss40Repository.getEncryPassword(userId);
			Base64.Decoder decoder = Base64.getDecoder();  // decoding password
			String decodedPass = new String(decoder.decode(encryptPwd));
			emailUser.setKey(userId);
			emailUser.setKeyDetails(decodedPass);
		}		
		return emailUser;
	}
	
	
	@Override
	public String getUserStatus(final String userId) {
		String status = omss40Repository.getStatus(userId);
		return status;
//		if (status.equalsIgnoreCase("INACT") || status.equals("")) {
//			return false;
//		} else {
//			return true;
//		}
	}

	@Override
	public String getmailId(String userId) {
		return  omss40Repository.getmailId(userId);
	}

	@Override
	public List<ProfileCodes> specficSystemProfile() {
		// TODO Auto-generated method stub
		
		return omss40Repository.specficSystemProfile();
	}
	
	@Override
	public String getAutomationUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "AUT_SER_URL");
	}
	@Override
	public String getSentenceUrl() {
		return oumsysetService.getConfValue("serverConfig", "PRODUCT", "SCAL_SER_URL");
	}

	@Override
	public Map<String, Object> getApplicationStatus() {
		Map<String, Object> applicationHealth=new HashMap<>();
		String automationEngineUrl = this.getAutomationUrl()+"/engine-rest/task/count/";
		String sentenceEngineUrl = this.getSentenceUrl()+"/sentence/sentences";
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		try {
			ResponseEntity<Object> automationResponse = restTemplate.getForEntity(automationEngineUrl, Object.class);
			if(automationResponse.getStatusCode().value() == 200) {
				applicationHealth.put(ApplicationConstants.AUTOMATION_ENGINE_STATUS, "U");
			} else {
				logger.info("getApplicationStatus :: Automation Engine Url {} :", automationEngineUrl);
				logger.info("getApplicationStatus :: Automation Status Code {} :", automationResponse.getStatusCode());
				applicationHealth.put(ApplicationConstants.AUTOMATION_ENGINE_STATUS, "D");
			}
		}catch (Exception e) {
		logger.error("Error in connecting Automation Engine :: " + e.getMessage());
		applicationHealth.put(ApplicationConstants.AUTOMATION_ENGINE_STATUS, "D");
		}
		try {
			ResponseEntity<Object> sentenceResponse =  restTemplate.postForEntity(sentenceEngineUrl, null, Object.class);
			if(sentenceResponse.getStatusCode().value() == 200) {
				applicationHealth.put(ApplicationConstants.SENTENCE_ENGINE_STATUS, "U");
			} else {
				applicationHealth.put(ApplicationConstants.SENTENCE_ENGINE_STATUS, "D");
			}
		}catch (Exception e) {
		logger.error("Error in connecting Sentence Engine :: " + e.getMessage());
		applicationHealth.put(ApplicationConstants.SENTENCE_ENGINE_STATUS, "D");
		}
		return applicationHealth;
	}
	
	// Override timeouts in the request factory
	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(20000);
		clientHttpRequestFactory.setReadTimeout(20000);
		return clientHttpRequestFactory;
	}
	
	@Override
	public Integer auditPage(EliteViewLog eliteViewLog) {
		if (eliteViewLog.getModuleName() != null) {
			String viewAuditFlag = oumtagreService.getViewAuditFlag(eliteViewLog.getModuleName());
			logger.info("auditPage eliteViewLog: {} viewAuditFlag:  {}  ", eliteViewLog.toString(),viewAuditFlag);
			try {
				if (viewAuditFlag.equalsIgnoreCase(ApplicationConstants.YES)) {
					eliteViewLog.setAuditTime(eliteDateService.getDBTime());
					String messagePayload = new ObjectMapper().writeValueAsString(eliteViewLog);
					auditLogInjectorService.produceMessage(messagePayload);
				} else {
					logger.info("This module is not included in view audit flag {}",viewAuditFlag);
				}
			} catch (Exception e) {
				logger.error("Exception in Omss40ServiceImpl in auditPage :: {} " + e.getMessage());
				logger.error("Omss40ServiceImpl in auditPage method  Payload :: {} " + eliteViewLog.toString());
			}
		} else {
			logger.info("Module name is not provided in Omss40ServiceImpl in auditPage ::{} ", eliteViewLog.toString());
		}
		return null;
	}
	}
