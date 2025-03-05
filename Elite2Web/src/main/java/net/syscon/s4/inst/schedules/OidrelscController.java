package net.syscon.s4.inst.schedules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.WorkFlowLogsCommitBean;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.inst.demographicsbiometrics.OcdalertService;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetailsCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * OidrelscController
 *
 */
@EliteController
public class OidrelscController {
	@Autowired
	private OidrelscService oidrelscService;
	@Autowired
	private ProsmainService prosmainService;
	@Autowired
	private OsiosearService osiosearService;
	@Autowired
	private OcdalertService ocdalertsevice;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrelscController.class.getName());

	/**
	 * getting rgAgyLocations LOV values
	 * @param caseloadId {@link String}
	 * @return a list of the AgencyLocations {@link AgencyLocations} for the matched caseloadId
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/rgAgyLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocationsRecordGroup(@RequestParam("caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oidrelscService.rgAgyLocationsRecordGroup(caseloadId);
		} catch (Exception e) {
			final AgencyLocations obj = new AgencyLocations();
			logger.error("Exception : Oidrelsc:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDateType LOV values
	 * @return a list of the ReferenceCodes {@link ReferenceCodes}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/rgDateTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgDateTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidrelscService.rgDateTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : Oidrelsc:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgMovementReasons LOV values
	 * @return a list of the MovementReasons {@link MovementReasons}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/rgMovementReasonsRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> rgMovementReasonsRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oidrelscService.rgMovementReasonsRecordGroup();
		} catch (Exception e) {
			final MovementReasons obj = new MovementReasons();
			logger.error("Exception : Oidrelsc:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderReleaseDetails}
	 * @return a list of the OffenderReleaseDetails {@link OffenderReleaseDetails} for the matched OffenderReleaseDetails
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/offRelDetExecuteQuery", method = RequestMethod.POST)
	public List<OffenderReleaseDetails> offRelDetExecuteQuery(@RequestBody final OffenderReleaseDetails searchBean) {
		List<OffenderReleaseDetails> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidrelscService.offRelDetExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderReleaseDetails bean = new OffenderReleaseDetails();
			logger.error("Exception : offRelDetExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link OffenderReleaseDetailsCommitBean}
	 * @return a list of the InternalScheduleReasons {@link OffenderReleaseDetails} for the matched OffenderReleaseDetailsCommitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelsc/offRelDetCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderReleaseDetails> offRelDetCommit(
			@RequestBody final OffenderReleaseDetailsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		List<OffenderReleaseDetails> liReturn = new ArrayList<>();
		try {
			List<String> authorizationList = headers.get("Authorization");
			String authorization = authorizationList.get(0).split(",")[0];
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = oidrelscService.offRelDetCommit(commitBean);
			
			if(liReturn.get(0).getReturnValue() == 1) {
				prosmainService.enableTriggers(commitBean, authorization, "100");
			}
			
		} catch (Exception e) {
			final OffenderReleaseDetails bean = new OffenderReleaseDetails();
			logger.error("Exception : offRelDetCommit", e);
			bean.setErrorMessage(e.getMessage().toUpperCase());
			liReturn.add(bean);
			logger.error("Exception : Oidrelsc", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/validateOffenderDisplayId", method = RequestMethod.POST)
	public OffenderReleaseDetails validateOffenderDisplayId(@RequestBody final OffenderReleaseDetails searchBean) {
		Long searchResult = null;
		final OffenderReleaseDetails returnObject = new OffenderReleaseDetails();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidrelscService.ctlWhenValidateRecord(searchBean);
			returnObject.setOffenderBookId(searchResult);
		} catch (Exception e) {

			logger.error("Exception :", e);

		}
		return returnObject;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/InsertWorkFlowId", method = RequestMethod.POST)
	public List<WorkFlows> insertWorkFlowId(@RequestBody final WorkFlows searchBean) {
		List<WorkFlows> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidrelscService.insWorkFlows(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/verifyButton", method = RequestMethod.POST)
	public List<WorkFlows> verifyButton(@RequestBody final WorkFlows searchBean) {
		List<WorkFlows> searchResult = new ArrayList<>();
		try {
			searchResult = oidrelscService.verificationButton(searchBean);
		} catch (Exception e) {

			logger.error("Exception :", e);

		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")  
	@RequestMapping(value = "/oidrelsc/getOffenderList", method = RequestMethod.POST)
	public @ResponseBody List<VHeaderBlock> getOffenderList(@RequestBody final VHeaderBlock paramBean) {
		List<VHeaderBlock> receOffenderList = new ArrayList<VHeaderBlock>();
		try {
			if (paramBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				paramBean.setCreateUserId(userName);
			}
			receOffenderList = oidrelscService.getOffenderList(paramBean);
		} catch (Exception e) {
			logger.error("", e);
		}
		return receOffenderList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/offRelDetLegalExecuteQuery", method = RequestMethod.POST)
	public List<OffenderReleaseDetails> offRelDetLegalExecuteQuery(@RequestBody final OffenderReleaseDetails searchBean) {
		List<OffenderReleaseDetails> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oidrelscService.offRelDetLegalExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderReleaseDetails bean = new OffenderReleaseDetails();
			logger.error("Exception : offRelDetExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/getKeyDatesDataLovData", method = RequestMethod.GET)
	public List<ReferenceCodes> getKeyDatesDataLovData(@RequestParam(value = "domainName") final String domainName) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidrelscService.getKeyDatesDataLovData(domainName);
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception : getKeyDatesDataLovData:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/rgKeyDatesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgKeyDatesRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidrelscService.rgKeyDatesRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	@GetMapping("/oidrelsc/getReleaseDate")
	public Date getReleaseDate(Long offenderBookId) {
		Date date=null;
		try {
			date = oidrelscService.getReleaseDate(offenderBookId);
		} catch (Exception e) {
			logger.error("in " + this.getClass().getName() + " in getReleaseDate ", e);
		}
		return date;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/offbkgGlobalQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> offbkgGlobalQuery(@Valid @RequestBody final VHeaderBlock searchBean, @RequestHeader HttpHeaders headers) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		final VHeaderBlock bean = new VHeaderBlock();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchBean.setCreateUserId(userName);
			searchResult = osiosearService.offbkgGlobalQuery(searchBean);
			if(searchResult == null){
				searchResult = new ArrayList<>();
				bean.setErrorMessage("Agency Location Type  Not Provided");
				searchResult.add(bean);
				return searchResult;
			}
		} catch (Exception e) {
			logger.error("Exception in offbkgGlobalQuery", e);
			searchResult = new ArrayList<>();
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelsc/workFlCommit", method = RequestMethod.POST)
	public @ResponseBody Integer workFlCommit(@RequestBody final WorkFlowLogsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocdalertsevice.workFlCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method workFlCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelsc/getErdHideShowValue", method = RequestMethod.GET)
	public String getErdHideShowValue(@RequestParam("code") final String code) {
		String returnValue=null;
		try {
			returnValue = oidrelscService.getErdHideShowValue(code);
		} catch (Exception e) {
			logger.error("Exception : getErdHideShowValue:", e);
			
		}
		return returnValue;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelsc/updatecommenttext", method = RequestMethod.POST)
	public  Integer updateCommentText(@RequestBody OffenderReleaseDetails offenderReleaseDetails) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			offenderReleaseDetails.setModifyUserId(userName);
			liReturn = oidrelscService.updateCommentText(offenderReleaseDetails);
		} catch (Exception e) {
			logger.error("In method updateCommentText ", e);
		}
		return liReturn;
	}
	
}