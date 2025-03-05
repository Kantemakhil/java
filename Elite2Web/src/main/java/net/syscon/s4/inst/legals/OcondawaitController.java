package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.OffenderSentConditions;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.inst.legals.beans.OffenderAllocationsSentences;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.inst.legals.beans.OffenderCondTransferCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcondawaitController {

	private static Logger logger = LogManager.getLogger(OcondawaitController.class.getName());

	@Autowired
	private OcondawaitService ocondawaitService;
	
	@Autowired
	private ProsmainService prosmainService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocondawait/rgLocationRecGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgLocationRecGroup(@RequestParam String caseLoadId) {
		try {
			return ocondawaitService.rgLocationRecGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class rgLocationRecGroup : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocondawait/rgTeamRecGroup", method = RequestMethod.GET)
	public List<Teams> rgTeamRecGroup(@RequestParam String caseLoadId) {
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			return ocondawaitService.rgTeamRecGroup(caseLoadId, userName);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class rgTeamRecGroup : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/getSentenceData", method = RequestMethod.POST)
	public List<OffenderAllocationsSentences> getSentenceData(@RequestBody OffenderAllocationsSentences searchBean) {
		try {
			return ocondawaitService.getSentenceData(searchBean);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getSentenceData : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/getAwaitingConditions", method = RequestMethod.POST)
	public List<OffenderSentConditions> getAwaitingConditions(@RequestBody OffenderAllocationsSentences searchBean) {
		try {
			return ocondawaitService.getAwaitingConditions(searchBean);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getAwaitingConditions : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/offenderCondTransferSaveForm", method = RequestMethod.POST)
	public @ResponseBody List<OffenderCondTransfer> offenderCondTransferSaveForm(
			@RequestBody final OffenderCondTransferCommitBean commitBean , @RequestHeader HttpHeaders headers) {
		List<OffenderCondTransfer> liReturn = new ArrayList<>();
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocondawaitService.offenderCondTransferCommit(commitBean);
			if(liReturn.get(0).getListSeq().intValue()==1) {
				if (commitBean.getInsertList()!=null) {
					if(commitBean.getInsertList().get(0).getModuleId().equals(ApplicationConstants.OCONDAWIT)) {
						prosmainService.enableTriggers(commitBean, authorization, "85");
					}
					if(commitBean.getInsertList().get(0).getModuleId().equals(ApplicationConstants.OCONDTRF)) {
						prosmainService.enableTriggers(commitBean, authorization, "86");
					}
					if(commitBean.getInsertList().get(0).getModuleId().equals(ApplicationConstants.OTKCONDTRF)) {
						prosmainService.enableTriggers(commitBean, authorization, "87");
					}
				}
			}
		} catch (Exception e) {
			final OffenderCondTransfer error = new OffenderCondTransfer();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			liReturn.add(error);
			logger.error("Exception in OcondawaitController class offenderCondTransferSaveForm :", e);

		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocondawait/getStaffDetails", method = RequestMethod.GET)
	public List<ReferenceCodes> getStaffDetails(String caseLoadId) {
		try {
			return ocondawaitService.getStaffDetails(caseLoadId);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getStaffDetails : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocondawait/getTeamMemberDetails", method = RequestMethod.GET)
	public List<ReferenceCodes> getTeamMemberDetails(Integer teamId) {
		try {
			return ocondawaitService.getTeamMemberDetails(teamId);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getTeamMemberDetails : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/getAssignedConditions", method = RequestMethod.POST)
	public List<OffenderCondTransfer> getAssignedConditions(@RequestBody OffenderAllocationsSentences searchBean) {
		try {
			return ocondawaitService.getAssignedConditions(searchBean);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getAwaitingConditions : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/getAssignedCondOffenders", method = RequestMethod.POST)
	public List<OffenderAllocationsSentences> getAssignedCondOffenders(
			@RequestBody OffenderAllocationsSentences searchBean) {
		try {
			return ocondawaitService.getAssignedCondOffenders(searchBean);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getAwaitingConditions : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/getTransferredCondOffenders", method = RequestMethod.POST)
	public List<OffenderAllocationsSentences> getTransferredCondOffenders(
			@RequestBody OffenderAllocationsSentences searchBean) {
		try {
			return ocondawaitService.getTransferredCondOffenders(searchBean);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getTransferredCondOffenders : ", e);
			return Collections.emptyList();
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocondawait/getTransferredConditons", method = RequestMethod.POST)
	public List<OffenderAllocationsSentences> getTransferredConditons(
			@RequestBody OffenderAllocationsSentences searchBean) {
		try {
			return ocondawaitService.getTransferredConditons(searchBean);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class getAwaitingConditions : ", e);
			return Collections.emptyList();
		}
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocondawait/rgTransferTeamRecGroup", method = RequestMethod.GET)
	public List<Teams> rgTransferTeamRecGroup(@RequestParam String caseLoadId) {
		try {
			return ocondawaitService.rgTransferTeamRecGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("Exception in OcondawaitController class rgTransferTeamRecGroup : ", e);
			return Collections.emptyList();
		}
	}

}
