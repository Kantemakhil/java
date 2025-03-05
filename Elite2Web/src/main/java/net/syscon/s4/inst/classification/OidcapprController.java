package net.syscon.s4.inst.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidcapprController
 */
@EliteController
public class OidcapprController {
	@Autowired
	private OidcapprService oidcapprService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcapprController.class.getName());

	/**
	 * getting cgfkOffAss1ReviewCommitte LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcappr/cgfkOffAss1ReviewCommitteRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffAss1ReviewCommitteRecordGroup(
			@RequestParam(value = "caseLoadType") String caseLoadType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			caseLoadType = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidcapprService.cgfkOffAss1ReviewCommitteRecordGroup(caseLoadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffAss1ReviewPlaceAgy LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcappr/cgfkOffAss1ReviewPlaceAgyRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffAss1ReviewPlaceAgyRecordGroup(
			@RequestParam(value = "caseLoadType") String caseLoadType) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			caseLoadType = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = oidcapprService.cgfkOffAss1ReviewPlaceAgyRecordGroup(caseLoadType);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffAss1ReviewSupLevel LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcappr/cgfkOffAss1ReviewSupLevelRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffAss1ReviewSupLevelRecordGroup(
			@RequestParam(value = "assTypeId") final Integer assTypeId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcapprService.cgfkOffAss1ReviewSupLevelRecordGroup(assTypeId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffAss1EvaluationResul LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcappr/cgfkOffAss1EvaluationResulRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffAss1EvaluationResulRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidcapprService.cgfkOffAss1EvaluationResulRecordGroup();
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcappr/offAssExecuteQuery", method = RequestMethod.POST)
	public List<VOffassAss> offAssExecuteQuery(@RequestBody final VOffassAss searchBean) {
		List<VOffassAss> searchResult = new ArrayList<>();
		try {
			searchResult = oidcapprService.offAssExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidcappr/offAss1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderAssessments> offAss1ExecuteQuery(@RequestBody final OffenderAssessments searchBean) {
		List<OffenderAssessments> searchResult = new ArrayList<>();
		try {
			searchResult = oidcapprService.offAss1ExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidcappr/offAss1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offAss1Commit(@RequestBody final OffenderAssessmentsCommitBean commitBean,
			@RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (Optional.ofNullable(commitBean).isPresent()) {
				final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
						.toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oidcapprService.offAss1Commit(commitBean);
			if (1 == liReturn && commitBean.getUpdateList().get(0).getAssessStatus().equals("A")) {
				AssessmentsCommitBean assessmentsCommitBean = oidcapprService.getAssessmentsCommit(commitBean);
				prosmainService.enableTriggers(assessmentsCommitBean, authorization, "14");
				prosmainService.enableTriggers(commitBean, authorization, "45");
			}
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

}