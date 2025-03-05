package net.syscon.s4.inst.movementexternal;

import java.util.ArrayList;
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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.Dual;
//import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.schedules.OidrelscService;
import net.syscon.s4.inst.schedules.bean.OffenderReleaseDetails;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;


@EliteController
public class OidreleaController {

	@Autowired
	private OidreleaService oidreleaService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OidrelscService oidrelscService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidreleaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/offEmExecuteQuery", method = RequestMethod.POST)
	public List<OffenderExternalMovements> offEmSearchOffenderExternalMovements(
			@RequestBody final OffenderExternalMovements searchBean) {
		List<OffenderExternalMovements> searchResult = new ArrayList<>();
		try {
			searchResult = oidreleaService.offEmSearchOffenderExternalMovements(searchBean);
		} catch (Exception e) {
			logger.error("offEmSearchOffenderExternalMovements", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelea/offEmCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offEmCommit(@RequestBody final OffenderExternalMovementsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidreleaService.offEmInsertOffenderExternalMovements(commitBean);
			if(1==liReturn) {
				oidreleaService.updateCustodyStatus(commitBean.getInsertList().get(0).getOffenderBookId(), userName);
				prosmainService.enableTriggers(commitBean, authorization, "2");
			}
		} catch (Exception e) {
			logger.error("offEmCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkOffEmMovementReasonCo LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/cgfkOffEmMovementReasonCoRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup() {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oidreleaService.cgfkOffEmMovementReasonCoRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmMovementReasonCoRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgMovementReasonCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/rgMovementReasonCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgMovementReasonCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidreleaService.rgMovementReasonCodeRgroup();
		} catch (Exception e) {
			logger.error("rgMovementReasonCodeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflSearchSystemProfiles(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidreleaService.sysPflSearchSystemProfiles(searchBean);
		} catch (Exception e) {
			logger.error("sysPflSearchSystemProfiles", e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/offempreinsertc", method = RequestMethod.POST)
	public @ResponseBody String offempreinsertc(@RequestBody final OffenderExternalMovements paramBean) {
		String listOfRecords = null;
		try {
			listOfRecords = oidreleaService.offEmPreInsertc(paramBean);
		} catch (Exception e) {
			logger.error("offempreinsertc", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/cgfkchkOffEmOffEmRefMovc", method = RequestMethod.POST)
	public List<Object> cgfkchkOffEmOffEmRefMovc(@RequestBody final ReferenceCodes paramBean) {
		List<Object> recordList = new ArrayList<Object>();
		try {
			recordList = oidreleaService.cgfkchkOffEmOffEmRefMovc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmRefMovc", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/cgfkchkOffEmOffEmMoveRsc", method = RequestMethod.POST)
	public List<Object> cgfkchkOffEmOffEmMoveRsc(@RequestBody final MovementReasons paramBean) {
		List<Object> recordList = new ArrayList<Object>();
		try {
			recordList = oidreleaService.cgfkchkOffEmOffEmMoveRsc(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmMoveRsc", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/cgwhenNewFormInstancec", method = RequestMethod.POST)
	public @ResponseBody List<Dual> cgwhenNewFormInstancec(@RequestBody final Dual paramBean) {
		List<Dual> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidreleaService.cgwhenNewFormInstancec(paramBean);
		} catch (Exception e) {
			logger.error("cgwhenNewFormInstancec", e);
		}
		return listOfRecords;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/cgfkchkOffEmOffEmMovec", method = RequestMethod.POST)
	public List<MovementReasons> cgfkchkOffEmOffEmMovec(@RequestBody final MovementReasons paramBean) {
		List<MovementReasons> recordList = null;
		try {
			recordList = oidreleaService.cgfkchkOffEmOffEmMovec(paramBean);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmMovec", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/callToShowFingerprint", method = RequestMethod.GET)
	public @ResponseBody List<SystemProfiles> callToShowFingerprint(@RequestBody final SystemProfiles paramBean) {
		List<SystemProfiles> dataObj = new ArrayList<SystemProfiles>();
		try {
			dataObj = oidreleaService.callToShowFingerprint(paramBean);
		} catch (Exception e) {
			logger.error("callToShowFingerprint", e);
		}
		return dataObj;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelea/offBookingCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBookingCommit(@RequestBody final VHeaderBlock commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidreleaService.offBookingUpdateOffenderExternalMovements(commitBean);
		} catch (Exception e) {
			logger.error("offBookingCommit", e);
		}
		return liReturn;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelea/offBkgCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBkgCommit(@RequestBody final VHeaderBlock commitBean) {
		int liReturn = 0;
		try {
			final String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			commitBean.setModifyUserId(userName);
			liReturn = oidreleaService.offBkgUpdateOffenderExternalMovements(commitBean);
		} catch (Exception e) {

			logger.error("offBookingCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/omsMovementsCheckActiveSentence", method = RequestMethod.POST)
	public @ResponseBody Integer omsMovementsCheckActiveSentence(
			@RequestBody final OffenderExternalMovements offExmBean) {
		int liReturn = 0;
		try {
			liReturn = oidreleaService.omsMovementsCheckActiveSentence(offExmBean);
		} catch (Exception e) {
			logger.error("In method omsMovementsCheckActiveSentence :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/offExtMvntsReleaseDateCheck", method = RequestMethod.POST)
	public OffenderExternalMovements offExtMvntsReleaseDateCheck(
			@RequestBody final OffenderExternalMovements paramBean) {
		OffenderExternalMovements beanObj = new OffenderExternalMovements();
		try {
			beanObj = oidreleaService.offExtMvntsReleaseDateCheck(paramBean);
		} catch (Exception e) {
			logger.error("In method offExtMvntsReleaseDateCheck", e);
		}
		return beanObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/omsMovementsCheckActiveCases", method = RequestMethod.POST)
	public String omsMovementsCheckActiveCases(@RequestBody final OffenderExternalMovements paramBean) {
		String beanObj = null;
		try {
			beanObj = oidreleaService.omsMovementsCheckActiveCases(paramBean);
		} catch (Exception e) {
			logger.error("In method omsMovementsCheckActiveCases", e);
		}
		return beanObj;
	}

	/**
	 * getting rgProposedEmployment LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/movementDateComparison", method = RequestMethod.GET)
	public List<OffenderExternalMovements> movementDateComparison(
			@RequestParam("offenderBookId") final Long offenderBookId) {
		List<OffenderExternalMovements> recordList = new ArrayList<OffenderExternalMovements>();
		try {
			recordList = oidreleaService.movementDateComparison(offenderBookId);
		} catch (Exception e) {
			logger.error("movementDateComparison", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/gettingProfileValue", method = RequestMethod.GET)
	public String gettingProfileValue() {
		String beanObj = null;
		try {
			beanObj = oidreleaService.gettingProfileValue();
		} catch (Exception e) {
			logger.error("In method gettingProfileValue", e);
		}
		return beanObj;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/getClosedFlag", method = RequestMethod.GET)
	public String getClosedFlag(@RequestParam("movementCode") final String movementCode) {
		String beanObj = null;
		try {
			beanObj = oidreleaService.getClosedFlag(movementCode);
		} catch (Exception e) {
			logger.error("In method getClosedFlad", e);
		}
		return beanObj;
	}
	
	/**
	 * getting OffenderreleaseSchedule details
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/getOffenderreleaseSchedule", method = RequestMethod.GET)
	public List<OffenderReleaseDetails> getOffenderreleaseSchedule(@RequestParam("offenderBookId") final Long offenderBookId) {
		List<OffenderReleaseDetails> recordList = new ArrayList<OffenderReleaseDetails>();
		try {
			recordList = oidreleaService.getOffenderreleaseSchedule(offenderBookId);
		} catch (Exception e) {
			logger.error("cgfkOffEmMovementReasonCoRecordGroup", e);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidrelea/getoffendercommenttext", method = RequestMethod.GET)
	public String getOffenderCommentText(@RequestParam("offenderBookId") final Integer offenderBookId) {
		String commentText = null;
		try {
			commentText = oidreleaService.getOffenderCommentText(offenderBookId);
		} catch (Exception e) {
			logger.error("In method getClosedFlad", e);
		}
		return commentText;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidrelea/updatecommenttext", method = RequestMethod.POST)
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