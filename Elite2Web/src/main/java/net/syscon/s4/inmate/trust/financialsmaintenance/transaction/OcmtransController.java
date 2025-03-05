package net.syscon.s4.inmate.trust.financialsmaintenance.transaction;

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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.common.beans.TransactionTypesCommitBean;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.TransactionPayees;
import net.syscon.s4.im.beans.TransactionPayeesCommitBean;
import net.syscon.s4.inmate.beans.CaseloadTransactionTypes;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inst.booking.beans.Persons;

@EliteController
public class OcmtransController {
	@Autowired
	private OcmtransService ocmtransService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmtransController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<TransactionTypes>
	 * @Param TransactionTypes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/txnTypeExecuteQuery", method = RequestMethod.POST)
	public List<TransactionTypes> txnTypeExecuteQuery(@RequestBody final TransactionTypes searchBean) {
		List<TransactionTypes> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = ocmtransService.txnTypeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("txnTypeExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @return Integer
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmtrans/txnTypeCommit", method = RequestMethod.POST)
	public @ResponseBody Integer txnTypeCommit(@RequestBody final TransactionTypesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmtransService.txnTypeCommit(commitBean);
		} catch (Exception e) {

			logger.error("txnTypeCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkTxnTypeCreditObligatio LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkTxnTypeCreditObligatioRecordGroup", method = RequestMethod.GET)
	public List<DeductionTypes> cgfkTxnTypeCreditObligatioRecordGroup() {
		List<DeductionTypes> recordList = new ArrayList<DeductionTypes>();
		try {
			recordList = ocmtransService.cgfkTxnTypeCreditObligatioRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnTypeCreditObligatioRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnPayeePayeePersonId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkTxnPayeePayeePersonIdRecordGroup", method = RequestMethod.GET)
	public List<Persons> cgfkTxnPayeePayeePersonIdRecordGroup() {
		List<Persons> recordList = new ArrayList<Persons>();
		try {
			recordList = ocmtransService.cgfkTxnPayeePayeePersonIdRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnPayeePayeePersonIdRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnPayeePayeeCorporate LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkTxnPayeePayeeCorporateRecordGroup", method = RequestMethod.GET)
	public List<Corporates> cgfkTxnPayeePayeeCorporateRecordGroup() {
		List<Corporates> recordList = new ArrayList<Corporates>();
		try {
			recordList = ocmtransService.cgfkTxnPayeePayeeCorporateRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnPayeePayeeCorporateRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldTtCaseloadId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkCsldTtCaseloadIdRecordGroup", method = RequestMethod.GET)
	public List<Caseloads> cgfkCsldTtCaseloadIdRecordGroup() {
		List<Caseloads> recordList = new ArrayList<Caseloads>();
		try {
			recordList = ocmtransService.cgfkCsldTtCaseloadIdRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkCsldTtCaseloadIdRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkTxnTypeTxnUsage LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkTxnTypeTxnUsageRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkTxnTypeTxnUsageRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocmtransService.cgfkTxnTypeTxnUsageRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkTxnTypeTxnUsageRecordGroup: ", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<CaseloadTransactionTypes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/csldTtExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadTransactionTypes> csldTtExecuteQuery(@RequestBody final CaseloadTransactionTypes searchBean) {
		List<CaseloadTransactionTypes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmtransService.csldTtExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("csldTtExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	// @PreAuthorize("hasEliteRole('full')")
	// @RequestMapping(value="/ocmtrans/csldTtCommit",method=RequestMethod.POST)
	// public @ResponseBody Integer csldTtCommit(@RequestBody
	// final CaseloadTransactionTypesCommitBean commitBean) {
	// int liReturn = 0;
	// try {
	// liReturn = ocmtransService.csldTtCommit(commitBean);
	// }catch(Exception e){
	//
	// logger.error("csldTtCommit: ", e);
	// }
	// return liReturn;
	// }

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 * @return List<TransactionPayees>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/txnPayeeExecuteQuery", method = RequestMethod.POST)
	public List<TransactionPayees> txnPayeeExecuteQuery(@RequestBody final TransactionPayees searchBean) {
		List<TransactionPayees> searchResult = new ArrayList<>();
		try {
			searchResult = ocmtransService.txnPayeeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("txnPayeeExecuteQuery: ", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 * @return Integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocmtrans/txnPayeeCommit", method = RequestMethod.POST)
	public @ResponseBody Integer txnPayeeCommit(@RequestBody final TransactionPayeesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocmtransService.txnPayeeCommit(commitBean);
		} catch (Exception e) {

			logger.error("txnPayeeCommit: ", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkOffEmDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkchkTxnPayeeTxnPayeeCorporate", method = RequestMethod.GET)
	public String cgfkchkTxnPayeeTxnPayeeCorporate(@RequestParam("corporateId") final BigDecimal corporateId) {
		String recordList = "";
		try {
			recordList = ocmtransService.cgfkchkTxnPayeeTxnPayee(corporateId);
		} catch (Exception e) {
			logger.error("oidadmisCgfkOffEmDspDescriptionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffEmDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/cgfkchkTxnPayeeTxnPayeePerson", method = RequestMethod.GET)
	public String cgfkchkTxnPayeeTxnPayeePerson(@RequestParam("personId") final Long personId) {
		String recordList = "";
		try {
			recordList = ocmtransService.cgfkchkTxnPayeeTxnPayee(personId);
		} catch (Exception e) {
			logger.error("oidadmisCgfkOffEmDspDescriptionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting txnTypeOnCheckDeleteMaster delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/txnTypeOnCheckDeleteMaster", method = RequestMethod.GET)
	public Integer txnTypeOnCheckDeleteMaster(@RequestParam("txnType") final String txnType) {
		Integer recordList = 0;
		try {
			recordList = ocmtransService.txnTypeOnCheckDeleteMaster(txnType);
		} catch (Exception e) {
			logger.error("oidadmisCgfkOffEmDspDescriptionRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting txnTypeValidation delete validations
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmtrans/txnTypeValidation", method = RequestMethod.GET)
	public String txnTypeValidation(@RequestParam("txnType") final String txnType) {
		String recordList = null;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			recordList = ocmtransService.txnTypeValidation(txnType,userName);
		} catch (Exception e) {
			logger.error("txnTypeValidation", e);
		}
		return recordList;
	}

}