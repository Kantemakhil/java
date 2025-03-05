package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderCaseCommitBean;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.Sentences;

@EliteController
public class OcdlegstController {

	@Autowired
	private OcdlegstService ocdlegstService;

	private static Logger logger = LogManager.getLogger(OcuccideController.class.getName());

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdlegst/updateSetenceReason", method = RequestMethod.POST)
	public Integer updateSetenceReason(@RequestBody List<Sentences> sentence) {
		List<Sentences> Result = new ArrayList<Sentences>();
		Integer result = 0;
		try {
			result = ocdlegstService.updateSetenceReason(sentence);
		} catch (Exception e) {
			logger.error("populateUpdateReason", e.getMessage());
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdlegst/populateCondition", method = RequestMethod.POST)
	public List<Condition> populateConditionData(@RequestBody Sentences sentence) {

		List<Condition> conditionList = new ArrayList<Condition>();
		try {
			conditionList = ocdlegstService.populateConditionData(sentence);
		} catch (Exception e) {
			logger.error("populateConditionData", e.getMessage());
		}
		return conditionList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdlegst/updateConditionData", method = RequestMethod.POST)
	public Integer updateConditionData(@RequestBody List<Condition> condition) {
		List<Condition> Result = new ArrayList<Condition>();
		Integer result = 0;
		try {
			result = ocdlegstService.updateConditionData(condition);
		} catch (Exception e) {
			logger.error("updateConditionData", e.getMessage());
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdlegst/getConditionsLov", method = RequestMethod.GET)
	public List<Condition> getConditionsLov() {
		List<Condition> result = new ArrayList<Condition>();
		try {
			result = ocdlegstService.getConditionLov();
		} catch (Exception e) {
			logger.error("getConditionsLov", e.getMessage());
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdlegst/populateConditionStatus", method = RequestMethod.GET)
	public List<Condition> populateCaseStatus() {
		List<Condition> condStatusList = new ArrayList<Condition>();
		try {
			condStatusList = ocdlegstService.populateCaseStatus();
		} catch (Exception e) {
			logger.error("populateCaseStatus", e.getMessage());
		}
		return condStatusList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdlegst/statusReasonUpdateLov", method = RequestMethod.GET)
	public List<Sentences> statusReasonUpdateLov(@RequestParam String sentenceCalcType) {
		List<Sentences> result = new ArrayList<Sentences>();
		try {
			result = ocdlegstService.statusReasonUpdateLov(sentenceCalcType);
		} catch (Exception e) {
			logger.error("statusReasonUpdateLov", e.getMessage());
		}
		return result;
	}

	/**
	 * To update court case
	 * 
	 * @param courtCaseCommit
	 * @return
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdlegst/updateCaseReason", method = RequestMethod.POST)
	public @ResponseBody Integer newcourtCase(@RequestBody OffenderCaseCommitBean courtCaseCommit) {
		int liReturn = 0;
		try {
			liReturn = ocdlegstService.updateCaseReason(courtCaseCommit);

		} catch (Exception e) {
			logger.error("newcourtCase", e);
		}
		return liReturn;
	}
}
