package net.syscon.s4.inst.demographicsbiometrics;

import java.math.BigDecimal;
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
import net.syscon.s4.common.beans.CaseloadAgencyLocations;
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.OffenderExternalMovementsCommitBean;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.WorkflowScreens;
import net.syscon.s4.common.beans.dao.OidadmisCommitBean;
import net.syscon.s4.globaloffenderrecords.OsiosearService;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.AgencyBillingProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.BedAssignmentHistories;
import net.syscon.s4.im.beans.CaseloadAdmAlertProfiles;
import net.syscon.s4.im.beans.CaseloadAdmOtherProfiles;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SystemMessages;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.im.beans.VHeaderBlock2;
import net.syscon.s4.inst.movements.housingchanges.OidchlocService;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * Class OidadmisController
 */
@EliteController
public class OidadmisController {

	@Autowired
	private OidadmisService oidadmisService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OsiosearService osiosearService;
	
	@Autowired
	private OidchlocService oidchlocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidadmisService.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offbkgExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock2> offbkgExecuteQuery(@RequestBody final VHeaderBlock2 searchBean) {
		List<VHeaderBlock2> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidadmisService.offbkgExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("offbkgExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOffEmDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkOffEmDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmDspDescriptionRecordGroup(
			@RequestParam("systemMode") final String systemMode) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.cgfkOffEmDspDescriptionRecordGroup(systemMode);
		} catch (Exception e) {
			logger.error("oidadmisCgfkOffEmDspDescriptionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkBedAhDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkBedAhDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> oidadmisCgfkBedAhDspDescriptionRecordGroup(
			@RequestParam("const0") final String const0) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.cgfkBedAhDspDescriptionRecordGroup(const0);
		} catch (Exception e) {
			logger.error("cgfkBedAhDspDescriptionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmDspDescription6 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkOffEmDspDescriptionAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmDspDescriptionAgyLocIdRecordGroup() {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.cgfkOffEmDspDescriptionAgyLocIdRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionAgyLocIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmDspDescription4 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkOffEmDspDescriptionMrRecordGroup", method = RequestMethod.GET)
	public List<MovementReasons> cgfkOffEmDspDescriptionMrRecordGroup(
			@RequestParam("movementReasonCode") final String movementReasonCode) {
		List<MovementReasons> recordList = new ArrayList<MovementReasons>();
		try {
			recordList = oidadmisService.cgfkOffEmDspDescriptionMrRecordGroup(movementReasonCode);
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionMrRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkOffEmDspDescriptionRGroup", method = RequestMethod.GET)
	public @ResponseBody List<AgencyLocations> cgfkOffEmDspDescriptionRGroup() {
		List<AgencyLocations> listOfRecords = new ArrayList<>();
		try {
			listOfRecords = oidadmisService.cgfkOffEmDspDescriptionRGroup();
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionRGroup: ", e);
		}
		return listOfRecords;
	}

	/**
	 * getting cgfkOffEmDspDescription5 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkOffEmDspDescriptionCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkOffEmDspDescriptionCaseloadIdRecordGroup(
			@RequestParam("caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.cgfkOffEmDspDescriptionCaseloadIdRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionCaseloadIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmDspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkOffEmDspDescriptionRcRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffEmDspDescriptionRcRecordGroup(@RequestParam("const0") final String const0) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			final String systemMode = const0;
			recordList = oidadmisService.cgfkOffEmDspDescriptionRcRecordGroup(systemMode);
		} catch (Exception e) {
			logger.error("cgfkOffEmDspDescriptionRcRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offBkgsExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offbkgsExecuteQuery(@RequestBody final OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = oidadmisService.offBkgsSearchOffenderBookings(searchBean);
		} catch (Exception e) {
			logger.error("offbkgExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offemExecuteQuery", method = RequestMethod.POST)
	public List<OffenderExternalMovements> offemExecuteQuery(@RequestBody final OffenderExternalMovements searchBean) {
		List<OffenderExternalMovements> searchResult = new ArrayList<>();
		try {
			searchResult = oidadmisService.offEmSearchOffenderExternalMovements(searchBean);
		} catch (Exception e) {
			logger.error("offemExecuteQuery", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oidadmis/oidadmisCommit", method = RequestMethod.POST)
	public @ResponseBody Integer oidadmisCommit(@RequestBody final OidadmisCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = oidadmisService.oidadmisCommit(commitBean);
		} catch (Exception e) {
			logger.error("oidadmisCommit", e);
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
	@RequestMapping(value = "/oidadmis/offemCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderBookings offemCommit(
			@RequestBody final OffenderExternalMovementsCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		OffenderBookings offenderBookings = null;
		String userName = "";
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
				commitBean.setAuthorization(authorization);
			}
			offenderBookings = oidadmisService.offemCommit(commitBean);
			OffenderBookingsCommitBean offenderBookingsCommitBean = new OffenderBookingsCommitBean();
			List<OffenderBookings> OffenderBookingsList = new ArrayList<OffenderBookings>();
			if(commitBean!=null && commitBean.getInsertList()!=null && !commitBean.getInsertList().isEmpty()) {
				offenderBookings.setNotification(commitBean.getInsertList().get(0).getNotification());
				offenderBookings.setIntCorrelationId(osiosearService.getCorrelationId());
				offenderBookings.setModuleName("OIDADMIS");
				offenderBookings.setNewBookingFlag(commitBean.getInsertList().get(0).isNewBookingFlag()? "Y":"N");
			}
			OffenderBookingsList.add(offenderBookings);
			offenderBookingsCommitBean.setInsertList(OffenderBookingsList);
			if(offenderBookings != null) {
				oidadmisService.updateCustodyStatus(offenderBookings.getOffenderBookId(), commitBean.getInsertList().get(0).isNewBookingFlag(), userName);
				prosmainService.enableTriggers(offenderBookingsCommitBean, authorization, "1");
			}
		} catch (Exception e) {
			logger.error("offemCommit: ", e);
		}
		return offenderBookings;
	}

	/*
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/bedahExecuteQuery", method = RequestMethod.POST)
	public List<BedAssignmentHistories> bedahExecuteQuery(@RequestBody final BedAssignmentHistories searchBean) {
		List<BedAssignmentHistories> searchResult = new ArrayList<>();
		try {
			searchResult = oidadmisService.oidadmisBedAhSearchBedAssignmentHistories(searchBean);
		} catch (Exception e) {
			logger.error("bedahExecuteQuery", e);
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
	@RequestMapping(value = "/oidadmis/offBookingCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offBookingCommit(@RequestBody final VHeaderBlock2 commitBean) {
		int liReturn = 0;
		String userName = "";
		try {
			if (commitBean != null) {
				userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setModifyUserId(userName);
			}
			liReturn = oidadmisService.offBookingUpdateOffenderExternalMovements(commitBean);
		} catch (Exception e) {

			logger.error("offBookingCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offtxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offtxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = oidadmisService.offTxnSearchOffenderTransactions(searchBean);
		} catch (Exception e) {
			logger.error("offtxnExecuteQuery", e);
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
	@RequestMapping(value = "/oidadmis/offtxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer offtxnCommit(@RequestBody final OffenderTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data->{
					data.setCreateUserId(commitBean.getCreateUserId());
				});
				liReturn = oidadmisService.offtxnInsertOffenderTransactions(commitBean.getInsertList());
			}
		} catch (Exception e) {
			logger.error("offtxnCommit", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/syspflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> syspflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = oidadmisService.sysPflSearchSystemProfiles(searchBean);
		} catch (Exception e) {
			logger.error("syspflExecuteQuery", e);
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
	@RequestMapping(value = "/oidadmis/syspflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer syspflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			
			if (commitBean.getInsertList() != null && commitBean.getInsertList().size() > 0) {
				commitBean.getInsertList().forEach(data->{
					data.setCreateUserId(commitBean.getCreateUserId());
				});
				liReturn = oidadmisService.syspflInsertSystemProfiles(commitBean.getInsertList());
			}
			if (commitBean.getDeleteList() != null && commitBean.getDeleteList().size() > 0) {
				liReturn = oidadmisService.sysPflDeleteSystemProfiles(commitBean.getDeleteList());
			}
		} catch (Exception e) {
			logger.error("syspflCommit", e);
		}
		return liReturn;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/dspDescriptionWhenValidateItemgetCapacityCur", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> dspDescriptionWhenValidateItemgetCapacityCur(
			@RequestParam(value = "const0") final String const0) {
		final LivingUnits dataObj = new LivingUnits();
		dataObj.setLivingUnitId(new BigDecimal(const0));
		List<LivingUnits> returnList = new ArrayList<>();
		try {
			returnList = oidadmisService.dspDescriptionWhenValidateItemgetCapacityCur(dataObj);
		} catch (Exception e) {
			logger.error("dspDescriptionWhenValidateItemgetCapacityCur", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/dspDescriptionWhenValidateItemlivDesc", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> dspDescriptionWhenValidateItemlivDesc(
			@RequestParam(value = "const0") final String const0) {
		final LivingUnits dataObj = new LivingUnits();
		dataObj.setLivingUnitId(new BigDecimal(const0));
		List<LivingUnits> returnList = new ArrayList<>();
		try {
			returnList = oidadmisService.dspDescriptionWhenValidateItemlivDesc(dataObj);
		} catch (Exception e) {
			logger.error("dspDescriptionWhenValidateItemlivDesc", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/nbtActiveFlagWhenValidateItembookNoCrsr", method = RequestMethod.GET)
	public @ResponseBody OffenderBookings nbtActiveFlagWhenValidateItembookNoCrsr(
			@RequestParam(value = "nbtActiveFlag2") final String nbtActiveFlag2,
			@RequestParam(value = "offenderBookId") final String offenderBookId) {
		OffenderBookings dataObj = new OffenderBookings();
		dataObj.setBookingNo(nbtActiveFlag2);
		dataObj.setOffenderBookId(new Long(offenderBookId));
		try {
			dataObj = oidadmisService.nbtActiveFlagWhenValidateItembookNoCrsr(dataObj);
		} catch (Exception e) {
			logger.error("nbtActiveFlagWhenValidateItembookNoCrsr", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offEmWhenNewBlockInstanceoffAlertCur", method = RequestMethod.GET)
	public @ResponseBody String offEmWhenNewBlockInstanceoffAlertCur(
			@RequestParam(value = "number") final Integer number) {
		String dataObj = null;
		try {
			dataObj = oidadmisService.offEmWhenNewBlockInstanceoffAlertCur(number);
		} catch (Exception e) {
			logger.error("offEmWhenNewBlockInstanceoffAlertCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offEmWhenNewBlockInstancecasAgyCur", method = RequestMethod.GET)
	public @ResponseBody Integer offEmWhenNewBlockInstancecasAgyCur(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		Integer listOfRecords = 0;
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		bean.setCaseloadId(caseloadId);
		try {
			listOfRecords = oidadmisService.offEmWhenNewBlockInstancecasAgyCur(bean);
		} catch (Exception e) {
			logger.error("offEmWhenNewBlockInstancecasAgyCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offEmWhenNewBlockInstance", method = RequestMethod.POST)
	public @ResponseBody SystemMessages offEmWhenNewBlockInstance(@RequestParam(value = "const0") final String const0) {
		SystemMessages dataObj = new SystemMessages();
		dataObj.setMessageNumber(new Integer(const0));
		try {
			dataObj = oidadmisService.offEmWhenNewBlockInstance(dataObj);
		} catch (Exception e) {
			logger.error("offEmWhenNewBlockInstance", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offEmWhenNewRecordInstancegetCapacityCur", method = RequestMethod.GET)
	public @ResponseBody LivingUnits offEmWhenNewRecordInstancegetCapacityCur(
			@RequestParam(value = "const0") final String const0) {
		LivingUnits dataObj = new LivingUnits();
		dataObj.setLivingUnitId(new BigDecimal(const0));
		try {
			dataObj = oidadmisService.offEmWhenNewRecordInstancegetCapacityCur(dataObj);
		} catch (Exception e) {
			logger.error("offEmWhenNewRecordInstancegetCapacityCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offEmWhenNewRecordInstanceadm", method = RequestMethod.GET)
	public @ResponseBody OffenderExternalMovements offEmWhenNewRecordInstanceadm(
			@RequestParam(value = "const0") final String const0) {
		OffenderExternalMovements dataObj = new OffenderExternalMovements();
		dataObj.setOffenderBookId(Long.valueOf(const0));
		try {
			dataObj = oidadmisService.offEmWhenNewRecordInstanceadm(dataObj);
		} catch (Exception e) {
			logger.error("offEmWhenNewRecordInstanceadm", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/offEmWhenNewRecordInstance", method = RequestMethod.POST)
	public @ResponseBody Integer offEmWhenNewRecordInstance(@RequestParam(value = "const0") final String const0) {
		CaseloadAgencyLocations dataObj;
		final int returnValue = 0;
		try {
			dataObj = new CaseloadAgencyLocations();
			dataObj.setCaseloadId(const0);
			dataObj = oidadmisService.offEmWhenNewRecordInstance(dataObj);
		} catch (Exception e) {
			logger.error("offEmWhenNewRecordInstance", e);
		}
		return returnValue;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/dspDescriptionWhenValidateItemvsLcdCur", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> dspDescriptionWhenValidateItemvsLcdCur(
			@RequestParam(value = "const0") final String const0) {
		List<LivingUnits> listOfRecords = new ArrayList<>();
		final LivingUnits bean = new LivingUnits();
		bean.setAgyLocId(const0);
		try {
			listOfRecords = oidadmisService.dspDescriptionWhenValidateItemvsLcdCur(bean);
		} catch (Exception e) {
			logger.error("dspDescriptionWhenValidateItemvsLcdCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffBkgsOffBkgStafc", method = RequestMethod.GET)
	public @ResponseBody StaffMembers cgfkchkOffBkgsOffBkgStafc(
			@RequestParam(value = "assignedStaffId") final String assignedStaffId) {
		StaffMembers bean = new StaffMembers();
		bean.setStaffId(new Integer(assignedStaffId));
		try {
			bean = oidadmisService.cgfkchkOffBkgsOffBkgStafc(bean);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgsOffBkgStafc", e);
		}
		return bean;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffEmOffEmRefCodc", method = RequestMethod.GET)
	public @ResponseBody ReferenceCodes cgfkchkOffEmOffEmRefCodc(
			@RequestParam(value = "arrestAgencyLocId") final String arrestAgencyLocId) {
		ReferenceCodes dataObj = new ReferenceCodes();
		dataObj.setCode(arrestAgencyLocId);
		try {
			dataObj = oidadmisService.cgfkchkOffEmOffEmRefCodc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmRefCodc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkkpOffEmOffEmRefCodc", method = RequestMethod.GET)
	public @ResponseBody ReferenceCodes cgfkkpOffEmOffEmRefCodc(
			@RequestParam(value = "dspDescription") final String dspDescription) {
		ReferenceCodes dataObj = new ReferenceCodes();
		try {
			dataObj.setDescription(dspDescription);
			dataObj = oidadmisService.cgfkkpOffEmOffEmRefCodc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkkpOffEmOffEmRefCodc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffEmOffEmRefc", method = RequestMethod.GET)
	public @ResponseBody ReferenceCodes cgfkchkOffEmOffEmRefc(
			@RequestParam(value = "escortCode") final String escortCode) {
		ReferenceCodes dataObj = new ReferenceCodes();
		try {
			dataObj = oidadmisService.cgfkchkOffEmOffEmRefc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmRefc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkkpOffEmOffEmRefc", method = RequestMethod.GET)
	public @ResponseBody ReferenceCodes cgfkkpOffEmOffEmRef2c(
			@RequestParam(value = "dsp_description3") final String dsp_description3) {
		final ReferenceCodes dataObj = new ReferenceCodes();
		try {
		} catch (Exception e) {
			logger.error("cgfkkpOffEmOffEmRefc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffEmOffEmMoveRsc", method = RequestMethod.GET)
	public @ResponseBody MovementReasons cgfkchkOffEmOffEmMoveRsc(
			@RequestParam(value = "movement_reason_code") final String movement_reason_code) {
		MovementReasons dataObj = new MovementReasons();
		try {
			dataObj.setMovementReasonCode(movement_reason_code);
			dataObj = oidadmisService.cgfkchkOffEmOffEmMoveRsc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmMoveRsc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkkpOffEmOffEmMoveRsc", method = RequestMethod.GET)
	public @ResponseBody MovementReasons cgfkkpOffEmOffEmMoveRsc(
			@RequestParam(value = "dsp_description4") final String dsp_description4,
			@RequestParam(value = "dsp_description41") final String dsp_description41,
			@RequestParam(value = "record_status") final String record_status,
			@RequestParam(value = "admission_type") final String admission_type,
			@RequestParam(value = "admission_type4") final String admission_type4,
			@RequestParam(value = "admission_type5") final String admission_type5,
			@RequestParam(value = "record_status6") final String record_status6) {
		MovementReasons dataObj = new MovementReasons();
		try {
			dataObj = oidadmisService.cgfklkpOffEmOffEmMoveRsc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkkpOffEmOffEmMoveRsc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffEmOffEmAgyLocc", method = RequestMethod.GET)
	public @ResponseBody AgencyLocations cgfkchkOffEmOffEmAgyLocc(
			@RequestParam(value = "to_agy_loc_id") final String to_agy_loc_id,
			@RequestParam(value = "caseload_id") final String caseload_id) {
		AgencyLocations dataObj = new AgencyLocations();
		try {
			dataObj.setAgyLocId(to_agy_loc_id);
			dataObj = oidadmisService.cgfkchkOffEmOffEmAgyLocc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmAgyLocc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkkpOffEmOffEmAgyLocc", method = RequestMethod.GET)
	public @ResponseBody AgencyLocations cgfkkpOffEmOffEmAgyLocc(
			@RequestParam(value = "dsp_description5") final String dsp_description5,
			@RequestParam(value = "dsp_description51") final String dsp_description51,
			@RequestParam(value = "caseload_id") final String caseload_id) {
		AgencyLocations dataObj = new AgencyLocations();
		try {
			dataObj = oidadmisService.cgfklkpOffEmOffEmAgyLocc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkkpOffEmOffEmAgyLocc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffEmOffEmAgyc", method = RequestMethod.GET)
	public @ResponseBody AgencyLocations cgfkchkOffEmOffEmAgyc(
			@RequestParam(value = "from_agy_loc_id") final String from_agy_loc_id) {
		AgencyLocations dataObj = new AgencyLocations();
		try {
			dataObj = oidadmisService.cgfkchkOffEmOffEmAgyc(dataObj);
		} catch (Exception e) {
			logger.error("cgfkchkOffEmOffEmAgyc", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkkpOffEmOffEmAgy2c", method = RequestMethod.GET)
	public @ResponseBody AgencyLocations cgfkkpOffEmOffEmAgy2c(
			@RequestParam(value = "dsp_description6") final String dsp_description6,
			@RequestParam(value = "dsp_description61") final String dsp_description61) {
		final AgencyLocations dataObj = new AgencyLocations();
		try {
		} catch (Exception e) {
			logger.error("cgfkkpOffEmOffEmAgy2c", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkBedAhBedAhLivUnic", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> cgfkchkBedAhBedAhLivUnic(
			@RequestParam(value = "living_unit_id") final String living_unit_id) {
		final LivingUnits dataObj = new LivingUnits();
		List<LivingUnits> returnList = new ArrayList<>();
		try {
			dataObj.setLivingUnitId(new BigDecimal(living_unit_id));
			returnList = oidadmisService.cgfkchkBedAhBedAhLivUnic(dataObj);
		} catch (Exception e) {
			logger.error("cgfkkpBedAhBedAhLivUnic", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkkpBedAhBedAhLivUnic", method = RequestMethod.GET)
	public @ResponseBody LivingUnits cgfkkpBedAhBedAhLivUnic(
			@RequestParam(value = "dspescription") final String dspDescription) {
		final LivingUnits dataObj = new LivingUnits();
		LivingUnits returnList = new LivingUnits();
		try {
			returnList = oidadmisService.cgfklkpBedAhBedAhLivUnic(dataObj);
		} catch (Exception e) {
			logger.error("cgfkkpBedAhBedAhLivUnic", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/cgfkchkOffBkgsOffBkgRef", method = RequestMethod.GET)
	public @ResponseBody ReferenceCodes cgfkchkOffBkgsOffBkgRef(
			@RequestParam(value = "bookingStatus") final String bookingStatus) {
		ReferenceCodes dataObj = new ReferenceCodes();
		dataObj.setCode(bookingStatus);
		try {
			dataObj = oidadmisService.cgfkchkOffBkgsOffBkgRef(dataObj);
		} catch (Exception e) {
			logger.error("cgfkchkOffBkgsOffBkgRef", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/insertMasterRec", method = RequestMethod.GET)
	public @ResponseBody TransactionTypes insertMasterRecTransTypes(
			@RequestParam(value = "const0") final String const0) {
		TransactionTypes dataObj = new TransactionTypes();
		try {
			dataObj = oidadmisService.insertMasterRecTransTypes(dataObj);
		} catch (Exception e) {
			logger.error("insertMasterRec", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/storGlobalsgetCrAccount", method = RequestMethod.GET)
	public @ResponseBody TransactionOperation storGlobalsgetCrAccount(
			@RequestParam(value = "const0") final String const0) {
		TransactionOperation listOfRecords = new TransactionOperation();
		final TransactionOperation bean = new TransactionOperation();
		try {
			listOfRecords = oidadmisService.storGlobalsgetCrAccount(bean);
		} catch (Exception e) {
			logger.error("storGlobalsgetCrAccount", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/storGlobalsAc", method = RequestMethod.POST)
	public @ResponseBody List<AccountCodes> storGlobalsAc(@RequestParam(value = "const0") final String const0) {
		final List<AccountCodes> listOfRecords = new ArrayList<>();
		try {
		} catch (Exception e) {
			logger.error("storGlobalsAc", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/storGlobals", method = RequestMethod.POST)
	public @ResponseBody TransactionTypes storGlobals(@RequestParam(value = "const0") final String const0) {
		TransactionTypes dataObj = new TransactionTypes();
		try {
			dataObj = oidadmisService.storGlobals(dataObj);
		} catch (Exception e) {
			logger.error("storGlobals", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/checkActiveYnactCurr", method = RequestMethod.GET)
	public @ResponseBody List<String> checkActiveYnactCurr(@RequestBody final OffenderExternalMovements paramBean) {
		List<String> dataObj = new ArrayList<>();
		try {
			dataObj = oidadmisService.checkActiveYnactCurr(paramBean);
		} catch (Exception e) {
			logger.error("checkActiveYnactCurr", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/checkPrevBkgClosedchekCrsr", method = RequestMethod.GET)
	public @ResponseBody OffenderBookings checkPrevBkgClosedchekCrsr(
			@RequestParam(value = "offender_book_id") final String offender_book_id) {
		OffenderBookings dataObj = new OffenderBookings();
		try {
			dataObj = oidadmisService.checkPrevBkgClosedchekCrsr(dataObj);
		} catch (Exception e) {
			logger.error("checkPrevBkgClosedchekCrsr", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/callThePreInsertc", method = RequestMethod.GET)
	public @ResponseBody Long callThePreInsertc(
			@RequestParam(value = "offender_book_id") final String offender_book_id) {
		Long listOfRecords = null;
		final BedAssignmentHistories bean = new BedAssignmentHistories();
		try {
			listOfRecords = oidadmisService.callThePreInsertc(bean);
		} catch (Exception e) {
			logger.error("callThePreInsertc", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/checkCapacityvsLcdCur", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> checkCapacityvsLcdCur(
			@RequestParam(value = "to_agy_loc_id") final String to_agy_loc_id,
			@RequestParam(value = "caseload_id") final String caseload_id,
			@RequestParam(value = "to_agy_loc_id2") final String to_agy_loc_id2) {
		LivingUnits dataObj = new LivingUnits();
		List<LivingUnits> returnList = new ArrayList<>();
		try {
			dataObj = new LivingUnits();
			returnList = oidadmisService.checkCapacityvsLcdCur(dataObj);
		} catch (Exception e) {
			logger.error("checkCapacityvsLcdCur", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/populateGlobalCapacityvsLcdCur", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> populateGlobalCapacityvsLcdCur(
			@RequestParam(value = "caseload_id") final String caseload_id) {
		LivingUnits dataObj = new LivingUnits();
		List<LivingUnits> returnList = new ArrayList<>();
		try {
			dataObj = new LivingUnits();
			returnList = oidadmisService.populateGlobalCapacityvsLcdCur(dataObj);
		} catch (Exception e) {
			logger.error("populateGlobalCapacityvsLcdCur", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getCountOfAgyInCasegetCountCur", method = RequestMethod.GET)
	public @ResponseBody Integer getCountOfAgyInCasegetCountCur(
			@RequestParam(value = "caseload_id") final String caseload_id) {
		final Integer listOfRecords = null;
		try {
		} catch (Exception e) {
			logger.error("getCountOfAgyInCasegetCountCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getCountOfAgyInCasegetAgyCur", method = RequestMethod.GET)
	public @ResponseBody CaseloadAdmOtherProfiles getCountOfAgyInCasegetAgyCur(
			@RequestParam(value = "caseload_id") final String caseload_id) {
		CaseloadAdmOtherProfiles dataObj = new CaseloadAdmOtherProfiles();
		try {
			dataObj = oidadmisService.getCountOfAgyInCasegetAgyCur(dataObj);
		} catch (Exception e) {
			logger.error("getCountOfAgyInCasegetAgyCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getCountOfAgyInCaseagyLocCur", method = RequestMethod.GET)
	public @ResponseBody String getCountOfAgyInCaseagyLocCur(
			@RequestParam(value = "caseload_id") final String caseload_id) {
		String listOfRecords = null;
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		try {
			listOfRecords = oidadmisService.getCountOfAgyInCaseagyLocCur(bean);
		} catch (Exception e) {
			logger.error("getCountOfAgyInCaseagyLocCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/populateDspDescriptionRg", method = RequestMethod.GET)
	public @ResponseBody AgencyLocations populateDspDescriptionRg(
			@RequestParam(value = "caseload_id") final String caseload_id,
			@RequestParam(value = "caseload_id1") final String caseload_id1) {
		AgencyLocations listOfRecords = new AgencyLocations();
		try {
			listOfRecords = oidadmisService.populateDspDescriptionRg(listOfRecords);
		} catch (Exception e) {
			logger.error("populateDspDescriptionRg", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/validateMovementDatetimemaxBookId", method = RequestMethod.GET)
	public @ResponseBody Long validateMovementDatetimemaxBookId(
			@RequestParam(value = "root_offender_id") final String root_offender_id) {
		Long dataObj = 0L;
		final OffenderBookings bean = new OffenderBookings();
		try {
			dataObj = oidadmisService.validateMovementDatetimemaxBookId(bean);
		} catch (Exception e) {
			logger.error("validateMovementDatetimemaxBookId", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/validateMovementDatetimemaxDateTime", method = RequestMethod.GET)
	public @ResponseBody Integer validateMovementDatetimemaxDateTime(
			@RequestParam(value = "const0") final String const0) {
		Integer dataObj = 0;
		try {
		} catch (Exception e) {
			logger.error("validateMovementDatetimemaxDateTime", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/validateMovementDatemaxBookId", method = RequestMethod.GET)
	public @ResponseBody Long validateMovementDatemaxBookId(
			@RequestParam(value = "root_offender_id") final String root_offender_id) {
		OffenderBookings dataObj;
		Long returnValue = 0L;
		try {
			dataObj = new OffenderBookings();
			returnValue = oidadmisService.validateMovementDatemaxBookId(dataObj);
		} catch (Exception e) {
			logger.error("validateMovementDatemaxBookId", e);
		}
		return returnValue;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/validateMovementDatemaxDate", method = RequestMethod.GET)
	public @ResponseBody OffenderExternalMovements validateMovementDatemaxDate(
			@RequestParam(value = "offenderBookId") final Integer offenderBookId) {
		OffenderExternalMovements returnValue = new OffenderExternalMovements();
		try {
			returnValue = oidadmisService.validateMovementDatemaxDate(offenderBookId);
		} catch (Exception e) {
			logger.error("validateMovementDatemaxDate", e);
		}
		return returnValue;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getLivingUnitIdgetLivUnitId", method = RequestMethod.GET)
	public @ResponseBody CaseloadAdmOtherProfiles getLivingUnitIdgetLivUnitId(
			@RequestParam(value = "caseload_id") final String caseload_id,
			@RequestParam(value = "to_agy_loc_id") final String to_agy_loc_id) {
		CaseloadAdmOtherProfiles dataObj = new CaseloadAdmOtherProfiles();
		try {
			dataObj = oidadmisService.getLivingUnitIdgetLivUnitId(dataObj);
		} catch (Exception e) {
			logger.error("getLivingUnitIdgetLivUnitId", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/deactivateActiveOffExmRecsetLockCur", method = RequestMethod.GET)
	public @ResponseBody OffenderExternalMovements deactivateActiveOffExmRecsetLockCur(
			@RequestParam(value = "const0") final String const0) {
		OffenderExternalMovements dataObj = new OffenderExternalMovements();
		try {
			dataObj = oidadmisService.deactivateActiveOffExmRecsetLockCur(dataObj);
		} catch (Exception e) {
			logger.error("deactivateActiveOffExmRecsetLockCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/createUpdateOffBillingProfgetAgyProfInfoCur", method = RequestMethod.GET)
	public @ResponseBody AgencyBillingProfiles createUpdateOffBillingProfgetAgyProfInfoCur(
			@RequestParam(value = "const0") final String const0) {
		AgencyBillingProfiles listOfRecords = new AgencyBillingProfiles();
		try {
			listOfRecords = oidadmisService.createUpdateOffBillingProfgetAgyProfInfoCur(listOfRecords);
		} catch (Exception e) {
			logger.error("createUpdateOffBillingProfgetAgyProfInfoCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/createUpdateOffBillingProf", method = RequestMethod.POST)
	public @ResponseBody AgencyBillingProfiles createUpdateOffBillingProf(
			@RequestParam(value = "const0") final String const0) {
		AgencyBillingProfiles dataObj = new AgencyBillingProfiles();
		try {
			dataObj = oidadmisService.createUpdateOffBillingProf(dataObj);
		} catch (Exception e) {
			logger.error("createUpdateOffBillingProf", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/workflowDownFormcountCur", method = RequestMethod.GET)
	public @ResponseBody WorkflowScreens workflowDownFormcountCur(@RequestParam(value = "const0") final String const0) {
		WorkflowScreens listOfRecords = new WorkflowScreens();
		final WorkflowScreens bean = new WorkflowScreens();
		try {
			listOfRecords = oidadmisService.workflowDownFormcountCur(bean);
		} catch (Exception e) {
			logger.error("workflowDownFormcountCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/workflowDownFormseqNoCur", method = RequestMethod.GET)
	public @ResponseBody WorkflowScreens workflowDownFormseqNoCur(@RequestParam(value = "const0") final String const0) {
		WorkflowScreens listOfRecords = new WorkflowScreens();
		final WorkflowScreens bean = new WorkflowScreens();
		try {
			listOfRecords = oidadmisService.workflowDownFormseqNoCur(bean);
		} catch (Exception e) {
			logger.error("workflowDownFormseqNoCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/workflowDownFormcallFormCur", method = RequestMethod.GET)
	public @ResponseBody List<String> workflowDownFormcallFormCur(@RequestParam(value = "const0") final String const0) {
		List<String> dataObj = new ArrayList<>();
		final WorkflowScreens paramBean = new WorkflowScreens();
		try {
			dataObj = oidadmisService.workflowDownFormcallFormCur(paramBean);
		} catch (Exception e) {
			logger.error("workflowDownFormcallFormCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/chkAssignedLochouUnTypeCur", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> chkAssignedLochouUnTypeCur(
			@RequestParam(value = "const0") final String const0) {
		final LivingUnits dataObj = new LivingUnits();
		List<LivingUnits> returnList = new ArrayList<>();
		try {
			returnList = oidadmisService.chkAssignedLochouUnTypeCur(dataObj);
		} catch (Exception e) {
			logger.error("chkAssignedLochouUnTypeCur", e);
		}
		return returnList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/checkBedAhBlkNavcasAgyCur", method = RequestMethod.GET)
	public @ResponseBody String checkBedAhBlkNavcasAgyCur(
			@RequestParam(value = "caseload_id") final String caseload_id) {
		String listOfRecords = null;
		final CaseloadAgencyLocations bean = new CaseloadAgencyLocations();
		try {
			listOfRecords = oidadmisService.checkBedAhBlkNavcasAgyCur(bean);
		} catch (Exception e) {
			logger.error("checkBedAhBlkNavcasAgyCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/validateLivingUnitsvsLcdCur", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> validateLivingUnitsvsLcdCur(
			@RequestParam(value = "const0") final String const0) {
		List<LivingUnits> listOfRecords = new ArrayList<>();
		final LivingUnits bean = new LivingUnits();
		try {
			listOfRecords = oidadmisService.validateLivingUnitsvsLcdCur(bean);
		} catch (Exception e) {
			logger.error("validateLivingUnitsvsLcdCur", e);
		}
		return listOfRecords;
	}

	/**
	 * getting rgReferenceCodesStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/rgReferenceCodesStatus", method = RequestMethod.GET)
	public List<ReferenceCodes> rgReferenceCodesStatus() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.rgReferenceCodesStatus();
		} catch (Exception e) {
			logger.error("rgReferenceCodesStatus", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmDspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getSaffmembersDescription", method = RequestMethod.GET)
	public List<ReferenceCodes> getSaffmembersDescription() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oidadmisService.getSaffmembersDescription();
		} catch (Exception e) {
			logger.error("getSaffmembersDescription", e);
		}
		return recordList;
	}

	/**
	 * method for query callings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/caseloadIdValue", method = RequestMethod.GET)
	public @ResponseBody List<Caseloads> caseloadIdValue(
			@RequestParam(value = "offenderId") final BigDecimal offenderId) {
		List<Caseloads> listOfRecords = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			listOfRecords = oidadmisService.caseloadIdValue(offenderId,userName);
		} catch (Exception e) {
			logger.error("validateLivingUnitsvsLcdCur", e);
		}
		return listOfRecords;
	}

	/**
	 * method for query from procedure
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/dafaultLocationData", method = RequestMethod.GET)
	public @ResponseBody OffenderBookings dafaultLocationData(
			@RequestParam(value = "caseloadId") final String agyLocId) {
		OffenderBookings listOfRecords = new OffenderBookings();
		try {
			listOfRecords = oidadmisService.dafaultLocationData(agyLocId);
		} catch (Exception e) {
			logger.error("validateLivingUnitsvsLcdCur", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getAdmissionType", method = RequestMethod.POST)
	public @ResponseBody String getAdmissionType(@RequestBody final VHeaderBlock2 searchBean) {
		String admType = null;
		try {
			admType = oidadmisService.getAdmissionType(searchBean);
		} catch (Exception e) {
			logger.error("getAdmissionType", e);
		}
		return admType;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getConflictEvent", method = RequestMethod.POST)
	public @ResponseBody OffenderBookings getConflictEvent(@RequestBody final OffenderExternalMovements searchBean) {
		OffenderBookings bkgBean = new OffenderBookings();
		try {
			bkgBean = oidadmisService.getConflictEvent(searchBean);
		} catch (Exception e) {
			logger.error("getConflictEvent", e);
		}
		return bkgBean;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/chkTrustFlag", method = RequestMethod.GET)
	public @ResponseBody String chkTrustFlag(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		String listOfRecords = null;
		try {
			listOfRecords = oidadmisService.chkTrustFlag(caseloadId);
		} catch (Exception e) {
			logger.error("validateLivingUnitsvsLcdCur", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/chkOffenderDeductions", method = RequestMethod.POST)
	public @ResponseBody String chkOffenderDeductions(@RequestBody final OffenderTransactions searchBean) {
		String admType = null;
		try {
			admType = oidadmisService.chkOffenderDeductions(searchBean);
		} catch (Exception e) {
			logger.error("getAdmissionType", e);
		}
		return admType;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/getOffenderAlertMsg", method = RequestMethod.POST)
	public @ResponseBody List<String> getOffenderAlertMsg(@RequestBody final VHeaderBlock2 searchBean) {
		List<String> admType = new ArrayList<>();
		try {
			admType = oidadmisService.getOffenderAlertMsg(searchBean);
		} catch (Exception e) {
			logger.error("getAdmissionType", e);
		}
		return admType;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidadmis/checkAllConficts", method = RequestMethod.POST)
	public BedAssignmentHistories checkAllConficts(@RequestBody final BedAssignmentHistories searchBean) {
		BedAssignmentHistories searchResult = new BedAssignmentHistories();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oidchlocService.checkAllConficts(searchBean);
		} catch (Exception e) {
			logger.error("checkAllConficts", e);
		}
		return searchResult;
	}
	
}