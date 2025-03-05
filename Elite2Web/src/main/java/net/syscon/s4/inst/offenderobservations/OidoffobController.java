package net.syscon.s4.inst.offenderobservations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodChecks;
import net.syscon.s4.inst.careinplacement.beans.OffObsPeriodCheckscommitBean;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriods;
import net.syscon.s4.inst.offenderobservations.beans.OffenderObservationPeriodsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristics;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffObsCharacteristicsCommitBean;
import net.syscon.s4.inst.offenderobservations.maintenance.beans.OffenderObservationTypes;

@EliteController
public class OidoffobController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidoffobController.class.getName());
	
	@Autowired
	private OidoffobService oidoffobService;
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getObservatioTypeData", method = RequestMethod.POST)
	public List<OffenderObservationTypes> getObservatioTypeData(@RequestBody final OffenderObservationTypes searchBean) {
		List<OffenderObservationTypes> recordList = new ArrayList<OffenderObservationTypes>();
		final OffenderObservationTypes bean = new OffenderObservationTypes();
		try {
			recordList = oidoffobService.getObservatioTypeData(searchBean);
		} catch (Exception e) {
			final OffenderObservationTypes error = new OffenderObservationTypes();
			logger.error("In rgSuffixRecordGroup method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getOffenderPeriodExecuteQuery", method = RequestMethod.POST)
	public List<OffenderObservationPeriods> getOffenderPeriodExecuteQuery(@RequestBody final OffenderObservationPeriods searchBean) {
		List<OffenderObservationPeriods> recordList = new ArrayList<OffenderObservationPeriods>();
		try {
			recordList = oidoffobService.getOffenderPeriodExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderObservationPeriods error = new OffenderObservationPeriods();
			logger.error("In getOffenderPeriodExecuteQuery method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoffob/offenderObservationPeriodDataCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderObservationPeriods> offenderObservationPeriodDataCommit(@RequestBody final OffenderObservationPeriodsCommitBean commitBean) {
		List<OffenderObservationPeriods> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidoffobService.offenderObservationPeriodDataCommit(commitBean);
		} catch (Exception e) {
			final OffenderObservationPeriods error = new OffenderObservationPeriods();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : offenderObservationPeriodDataCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getOffenderPeriodCheckExecuteQuery", method = RequestMethod.POST)
	public List<OffObsPeriodChecks> getOffenderPeriodCheckExecuteQuery(@RequestBody final OffObsPeriodChecks searchBean) {
		List<OffObsPeriodChecks> recordList = new ArrayList<OffObsPeriodChecks>();
		try {
			recordList = oidoffobService.getOffenderPeriodCheckExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffObsPeriodChecks error = new OffObsPeriodChecks();
			logger.error("In getOffenderPeriodExecuteQuery method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoffob/offenderObservationCheckDataCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffObsPeriodChecks> offenderObservationCheckDataCommit(@RequestBody final OffObsPeriodCheckscommitBean commitBean) {
		List<OffObsPeriodChecks> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidoffobService.offenderObservationCheckDataCommit(commitBean);
		} catch (Exception e) {
			final OffObsPeriodChecks error = new OffObsPeriodChecks();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception : offenderObservationPeriodDataCommit", e);
		}
		return liReturn;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/additionalCheckCharxecuteQuery", method = RequestMethod.POST)
	public List<OffObsCharacteristics> additionalCheckCharxecuteQuery(@RequestBody final OffObsCharacteristics searchBean) {
		List<OffObsCharacteristics> recordList = new ArrayList<OffObsCharacteristics>();
		try {
			recordList = oidoffobService.additionalCheckCharxecuteQuery(searchBean);
		} catch (Exception e) {
			final OffObsCharacteristics error = new OffObsCharacteristics();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoffob/saveAdditionalCharecterData", method = RequestMethod.POST)
	public @ResponseBody List<OffObsCharacteristics> saveAdditionalCharecterData(@RequestBody final OffObsCharacteristicsCommitBean commitBean) {
		List<OffObsCharacteristics> liReturn = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidoffobService.saveAdditionalCharecterData(commitBean);
		} catch (Exception e) {
			final OffObsCharacteristics error = new OffObsCharacteristics();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			liReturn.add(error);
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidoffob/saveOffenderObservationCheckComment", method = RequestMethod.POST)
	public @ResponseBody Integer saveOffenderObservationCheckComment(@RequestBody final OffObsPeriodCheckscommitBean commitBean) {
		Integer liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oidoffobService.saveOffenderObservationCheckComment(commitBean);
		} catch (Exception e) {
			final OffObsPeriodChecks error = new OffObsPeriodChecks();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			logger.error("Exception : offenderObservationPeriodDataCommit", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getCommentExecuteQuery", method = RequestMethod.POST)
	public List<OffObsPeriodChecks> getCommentExecuteQuery(@RequestBody final OffObsPeriodChecks searchBean) {
		List<OffObsPeriodChecks> recordList = new ArrayList<OffObsPeriodChecks>();
		try {
			recordList = oidoffobService.getCommentExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffObsPeriodChecks error = new OffObsPeriodChecks();
			logger.error("In getCommentExecuteQuery method : ", e);
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			recordList.add(error);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/cellCondiLinkDomainRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cellCondiLinkDomainRecordGroup(@RequestParam(value = "observationType") final String observationType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidoffobService.cellCondiLinkDomainRecordGroup(observationType);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/activityLinkDomainRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> activityLinkDomainRecordGroup(@RequestParam(value = "observationType") final String observationType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidoffobService.activityLinkDomainRecordGroup(observationType);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/commDetailLinkDomainRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> commDetailLinkDomainRecordGroup(@RequestParam(value = "observationType") final String observationType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidoffobService.commDetailLinkDomainRecordGroup(observationType);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/notInLinkDomainRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> notInLinkDomainRecordGroup(@RequestParam(value = "observationType") final String observationType) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oidoffobService.notInLinkDomainRecordGroup(observationType);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getOffenderLivningUnitIdCount", method = RequestMethod.GET)
	public Integer notInLinkDomainRecordGroup(@RequestParam(value = "offenderBookId") final BigDecimal offenderBookId) {
		Integer count = 0;
		try {
			count = oidoffobService.getOffenderLivningUnitIdCount(offenderBookId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getObservationTypeRecordGroup", method = RequestMethod.GET)
	public List<OffenderObservationTypes> rgCatRecordGroup() {
		List<OffenderObservationTypes> recordList = new ArrayList<OffenderObservationTypes>();
		try {
			recordList = oidoffobService.getObservationTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getOffenderLivningUnitIdCountNotInLocation", method = RequestMethod.GET)
	public Integer getOffenderLivningUnitIdCountNotInLocation(@RequestParam(value = "offenderBookId") final BigDecimal offenderBookId, @RequestParam(value = "agyLocId") final String agyLocId) {
		Integer count = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			count = oidoffobService.getOffenderLivningUnitIdCountNotInLocation(offenderBookId, userName,agyLocId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoffob/getCurrentStaffId", method = RequestMethod.GET)
	public Integer getCurrentStaffId() {
		Integer count = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			count = oidoffobService.getCurrentStaffId(userName);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return count;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/oidoffob/sysPflExecuteQuery", method=RequestMethod.GET)
	public List<SystemProfiles> sysPflExecuteQuery() {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidoffobService.sysPflExecuteQuery();
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}
