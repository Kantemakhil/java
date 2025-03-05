package net.syscon.s4.inmate.trust.trustaccounts;

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

import com.azure.core.util.Context;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OtrdreceReportBean;
import net.syscon.s4.common.beans.OtrreceiReportBean;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.ChkFreezeDisbursements;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.TransactionOperation;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Class OtdrdtfuController
 */
@EliteController
public class OtdrdtfuController {
	@Autowired
	private OtdrdtfuService otdrdtfuService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdrdtfuController.class.getName());

	/**
	 * getting cgfkOffTxnPayeePersonId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/cgfkOffTxnPayeePersonIdRecordGroup", method = RequestMethod.GET)
	public List<Persons> cgfkOffTxnPayeePersonIdRecordGroup() {
		List<Persons> recordList = new ArrayList<Persons>();
		try {
			recordList = otdrdtfuService.cgfkOffTxnPayeePersonIdRecordGroup();
		} catch (Exception e) {
			final Persons obj = new Persons();
			logger.error("In method cgfkOffTxnPayeePersonIdRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxnTxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/cgfkOffTxnTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = otdrdtfuService.cgfkOffTxnTxnTypeRecordGroup(caseloadId, userName);
		} catch (Exception e) {
			final TransactionTypes obj = new TransactionTypes();
			logger.error("In method cgfkOffTxnTxnTypeRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxnPayeeCorporateI LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/cgfkOffTxnPayeeCorporateIRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkOffTxnPayeeCorporateIRecordGroup() {
		List<Corporates> recordList = new ArrayList<>();
		try {
			recordList = otdrdtfuService.cgfkOffTxnPayeeCorporateIRecordGroup();
		} catch (Exception e) {
			final Corporates obj = new Corporates();
			logger.error("In method cgfkOffTxnPayeeCorporateIRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdrdtfuService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In method sysPflExecuteQuery : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * getting cgfkOffTxnPayeeCorporateI LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/txnTypeValidation", method = RequestMethod.GET)
	public TransactionOperation txnTypeValidation(@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "caseloadId") final String caseloadId) {
		TransactionOperation returnObj = new TransactionOperation();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			returnObj = otdrdtfuService.txnTypeValidation(txnType, caseloadId, userName);
		} catch (Exception e) {
			logger.error("In method txnTypeValidation :", e);
		}
		return returnObj;
	}

	/**
	 * getting cgfkOffTxnPayeeCorporateI LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/checkCaseloadValidation", method = RequestMethod.GET)
	public Integer checkCaseloadValidation(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "agyLocId") final String agyLocId) {
		Integer returnObj = null;
		try {
			returnObj = otdrdtfuService.checkCaseloadValidation(caseloadId, agyLocId);
		} catch (Exception e) {
			logger.error("In method checkCaseloadValidation :", e);
		}
		return returnObj;
	}
	
	/**
	 * getting otdrdtfuModlibValidationHWhenValidateItemSystemProfileC account blur values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/otdrdtfuModlibValidationHWhenValidateItemSystemProfileC", method = RequestMethod.GET)
	public SystemProfiles otdrdtfuModlibValidationHWhenValidateItemSystemProfileC() {
		SystemProfiles returnObj = new SystemProfiles();
		try {
			returnObj = otdrdtfuService.otdrdtfuModlibValidationHWhenValidateItemSystemProfileC();
		} catch (Exception e) {
			logger.error("In method otdrdtfuModlibValidationHWhenValidateItemSystemProfileC :", e);
			returnObj.setErrorMessage(e.getMessage());
		}
		return returnObj;
	}
	
	/**
	 * getting otdrdtfuModlibValidationHWhenValidateItemSystemProfileC account blur values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType", method = RequestMethod.GET)
	public List<OffenderDeductions> otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(Long offenderId,
			String caseloadId, String transType) {
		List<OffenderDeductions> returnObj = new ArrayList<>();
		try {
			returnObj = otdrdtfuService.otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType(offenderId, caseloadId, transType);
		} catch (Exception e) {
			logger.error("In method otdrdtfuModlibValidationHWhenValidateItemGetTxnFeeType :", e);
			OffenderDeductions bean = new OffenderDeductions();
			bean.setErrorMessage(e.getMessage());
			returnObj.add(bean);
		}
		return returnObj;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/onAmountBlurValidation", method = RequestMethod.POST)
	public String onAmountBlurValidation(@RequestBody final OffenderTransactions paramBean)  {
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			paramBean.setCreateUserId(userName);
			return otdrdtfuService.onAmountBlurValidation(paramBean);
		} catch (Exception e) {
			logger.error("onAmountBlurValidation : ", e);
			return e.getMessage();
		}
	}
	
//	chk_disbursement_freeze
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/chkDisbursementFreeze", method = RequestMethod.POST)
	public ChkFreezeDisbursements chkDisbursementFreeze(@RequestBody final ChkFreezeDisbursements chkFreezeDisbursements) {
		ChkFreezeDisbursements retrunObj = new ChkFreezeDisbursements();
		try{	
			retrunObj = otdrdtfuService.chkDisbursementFreeze(chkFreezeDisbursements);
		} catch(Exception e) {
			logger.error("Error in chkDisbursementFreeze", e);
			retrunObj.setErrorMessage(e.getMessage());
		}
		return retrunObj;
		}
	
//	trust.chk_account_status
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/chkAccountStatus", method = RequestMethod.GET)
	public String chkAccountStatus(@RequestParam(value = "caseloadId") final String caseloadId, @RequestParam(value = "offenderId") final Long offenderId  ) {
		try {
			return otdrdtfuService.chkAccountStatus(caseloadId, offenderId);
		} catch(Exception e) {
			logger.error("Method chkAccountStatus : ", e);
			}
		return "";
	}
// reopen offener trust account	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdrdtfu/reopenOffenerTrustAccount", method = RequestMethod.GET)
	public Integer reopenOffenerTrustAccount(@RequestParam(value = "caseloadId") final String caseloadId, @RequestParam(value = "offenderId") final Long offenderId  ) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			return otdrdtfuService.reopenOffenerTrustAccount(caseloadId, offenderId, userName);
		} catch(Exception e) {
			logger.error("reopenOffenerTrustAccount : ", e);
			return 0;
		}
	}
	
//  Generates OTRDRECE Reports	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdrdtfu/otdrdtfugenerateotrdrecereport", method = RequestMethod.POST)
	public ReportBean otdrdtfugenerateotrdrecereport(@RequestBody final OtrdreceReportBean paramBean) {
		try {
			return otdrdtfuService.otdrdtfugenerateotrdrecereport(paramBean);
		} catch(Exception e) {
			logger.error("otdrdtfugenerateotrdrecereport : ", e);
			return null;
		}
		
	}
//	Generates OTRDRECE Reports	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdrdtfu/otdrdtfugenerateOtrreceireport", method = RequestMethod.POST)
	public ReportBean otdrdtfugenerateOtrreceireport(@RequestBody OtrreceiReportBean paramBean) {
		try {
			return otdrdtfuService.otdrdtfugenerateOtrreceireport(paramBean);
		} catch(Exception e) {
			logger.error("otdrdtfugenerateotrdrecereport : ", e);
			return null;
		}
	}
//	Generates OTRDRECE Reports	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdrdtfu/mainProcess", method = RequestMethod.POST)
	public List<OffenderTransactions> mainProcess(@RequestBody OffenderTransactions paramBean) {
		List<OffenderTransactions> returnList = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		paramBean.setCreateUserId(userName);
		try {
			returnList =  otdrdtfuService.mainProcess(paramBean);
		} catch(Exception e) {
			OffenderTransactions errorBean = new OffenderTransactions();
			errorBean.setErrorMessage(e.getMessage());
			returnList.add(errorBean);
			logger.error("mainProcess : ", e);
		}
		return returnList;
	}	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/mainProcessAutoSubmitting", method = RequestMethod.GET)
	public String mainProcessAutoSubmitting() {
		try {
			return otdrdtfuService.mainProcessAutoSubmitting();
		}catch (Exception e) {
			logger.error("mainProcessAutoSubmitting : ", e);
			return null;
		}
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdrdtfu/deductionsChkOffenderDeductions", method = RequestMethod.GET)
	public @ResponseBody String deductionsChkOffenderDeductions(@RequestParam(value = "caseloadId") final String caseloadId,
			@RequestParam(value = "offenderId") final Long offenderId,
			@RequestParam(value = "txnType") final String txnType,
			@RequestParam(value = "shadowId") final Integer shadowId) {
		try {
			return otdrdtfuService.deductionsChkOffenderDeductions(caseloadId, offenderId, txnType, shadowId);
		}catch (Exception e) {
			logger.error("deductionsChkOffenderDeductions : ", e);
			return null;
		}
	}
	
	
	}
