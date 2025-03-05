package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.casemanagement.beans.CommunityConditions;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditionsCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.OcmcondiService;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcucondiController {

	private static Logger logger = LogManager.getLogger(OcucondiController.class.getName());

	@Autowired
	OcucondiService conditionService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OcmcondiService ocmcondiService;
	
	@Autowired
	private CommonService commonService;

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getCategory", method = RequestMethod.GET)
	public List<BondType> getCategory() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<BondType> result = new ArrayList<>();
		try {
			result = conditionService.getCategory();
		} catch (Exception e) {
			logger.error("getCategory", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "ocucondi/fetchOrderTypeDesc", method = RequestMethod.GET)
	public String fetchOrderTypeDesc(@RequestParam String conditionType) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String desc = null;
		try {
			desc = conditionService.fetchOrderTypeDesc(conditionType);
		} catch (Exception e) {
			logger.error("fetchOrderTypeDesc", e);
		}
		return desc;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getUnit", method = RequestMethod.GET)
	public List<BondType> getUnit() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<BondType> result = new ArrayList<>();
		try {
			result = conditionService.getUnit();
		} catch (Exception e) {
			logger.error("getUnit", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getProgram", method = RequestMethod.GET)
	public List<CommonLov> getProgram() {
		List<CommonLov> result = new ArrayList<>();
		try {
			result = conditionService.getProgram();
		} catch (Exception e) {
			logger.error("getProgram", e);
		}
		return result;
	}

	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucondi/getConditionTypeGrid", method = RequestMethod.POST)
	public List<OffenderSentConditions> getConditionTypeGrid(@RequestBody OffenderSentConditions condition) {
		List<OffenderSentConditions> result = new ArrayList<>();
		try {
			result = conditionService.getConditionTypeGrid(condition);
		} catch (Exception e) {
			logger.error("getConditionTypeGrid", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getConditionGrid", method = RequestMethod.POST)
	public List<Condition> getConditionGrid(@RequestBody Condition condition) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<Condition> result = new ArrayList<>();
		try {
			result = conditionService.getConditionGrid(condition);
		} catch (Exception e) {
			logger.error("getConditionGrid", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getCompleteConditionGrid", method = RequestMethod.POST)
	public List<OffenderSentConditions> getCompleteConditionGrid(@RequestBody OffenderSentConditions condition) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderSentConditions> result = new ArrayList<>();
		try {
			result = conditionService.getCompleteConditionGrid(condition);
		} catch (Exception e) {
			logger.error("getConditionGrid", e);
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getConditionLov", method = RequestMethod.POST)
	public List<CommonLov> getConditionLov(@RequestBody Condition condition) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CommonLov> result = new ArrayList<>();
		try {
			result = conditionService.getConditionLov(condition);
		} catch (Exception e) {
			logger.error("getConditionLov", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getConditionsLov", method = RequestMethod.GET)
	public List<CommonLov> getConditionsLov(@RequestParam(value = "condition") String condition,
			@RequestParam(value = "category") String category) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CommonLov> result = new ArrayList<>();
		try {
			result = conditionService.getConditionLov(condition, category);
		} catch (Exception e) {
			logger.error("getConditionsLov", e);
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/populateConditionStatus", method = RequestMethod.GET)
	public @ResponseBody List<LegalUpdateReasons> populateConditionStatus(String orderType) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<LegalUpdateReasons> condStatusList = new ArrayList<>();
		try {
			condStatusList = conditionService.populateCaseStatus(orderType);
		} catch (Exception e) {
			logger.error("populateConditionStatus", e);
		}
		return condStatusList;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/populateConditionComment", method = RequestMethod.POST)
	public Condition populateConditionComment(@RequestBody Condition condition) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		Condition condStatusList = new Condition();
		try {
			condStatusList = conditionService.populateProgramComment(condition);
		} catch (Exception e) {
			logger.error("populateConditionComment", e);
		}
		return condStatusList;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/populateSentencesCategory", method = RequestMethod.GET)
	public List<Category> populateSentencesCategory() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<Category> sentencesCategoryList = new ArrayList<>();

		try {
			sentencesCategoryList = conditionService.populateSentencesCategory();
		} catch (Exception e) {
			logger.error("populateSentencesCategory", e);
		}
		return sentencesCategoryList;

	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/populateProhibitedLov", method = RequestMethod.GET)
	public List<CommonLov> populateProhibitedLov() {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CommonLov> lovList = new ArrayList<>();

		try {
			lovList = conditionService.populateProhibitedLov();
		} catch (Exception e) {
			logger.error("populateProhibitedLov", e);
		}
		return lovList;

	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/offSentConCommit", method = RequestMethod.POST)
	public @ResponseBody String offSentConCommit(@RequestBody OffenderSentConditionsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		if(!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		String getResult = null;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(user);
		try {
			getResult = conditionService.offSentConCommit(commitBean);
			if(getResult != null) {
				prosmainService.enableTriggers(commitBean, authorization, "101");
			}
		} catch (Exception e) {
			logger.error("updateConditionData", e);
		}
		return getResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/getDefaultConditions", method = RequestMethod.POST)
	public List<OffenderSentConditions> getDefaultConditions(@RequestBody OffenderSentConditions sentConBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<OffenderSentConditions> returnList = new ArrayList<>();
		try {
			returnList = conditionService.getDefaultConditions(sentConBean);
		} catch (Exception e) {
			logger.error("getDefaultConditions", e);
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucondi/comCondFilteredData", method = RequestMethod.POST)
	public List<CommunityConditions> comCondFilteredData(@RequestBody final OffenderSentConditions searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<CommunityConditions> searchResult = new ArrayList<>();
		try {
			searchResult = ocmcondiService.comCondFilteredData(searchBean);
		} catch (Exception e) {
			final CommunityConditions bean = new CommunityConditions();
			logger.error("Exception :comCondFilteredData", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUCONDI");
	}

}
