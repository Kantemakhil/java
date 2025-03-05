package net.syscon.s4.cf.offendertransactions;

import java.util.ArrayList;
import java.util.Date;
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

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.maintenance.beans.FeeAccounts;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OffenderTransactionsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderTransactions;

@EliteController
public class OcdreceiController {
	@Autowired
	private OcdreceiService ocdreceiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdreceiController.class.getName());

	/**
	 * getting cgfkOffTxnDspInformationN LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/cgfkOffTxnDspInformationNRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(Long offenderBookId) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdreceiService.cgfkOffTxnDspInformationNRecordGroup(offenderBookId);
			logger.info("cgfkOffTxnDspInformationNRecordGroup response" + recordList);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " cgfkOffTxnDspInformationNRecordGroup error :: ", e);
		}
		return recordList;
	}
	/**
	 * getting cgfkOffTxnDspInformationN LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/getDocketLovData", method = RequestMethod.POST)
	public List<ReferenceCodes> getDocketLovData(@RequestBody OffenderTransactions offenderTransactions) {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ocdreceiService.cgfkOffTxnDspInformationNRecordGroup(offenderTransactions.getOffenderBookId());
			logger.info("cgfkOffTxnDspInformationNRecordGroup response" + recordList);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getDocketLovData error :: ", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxnDspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/cgfkOffTxnTxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffTxnTxnTypeRecordGroup() {
		List<TransactionTypes> recordList = new ArrayList<>();
		try {
			recordList = ocdreceiService.cgfkOffTxnTxnTypeRecordGroup(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			logger.info("cgfkOffTxnTxnTypeRecordGroup response" + recordList);
		} catch (final Exception e) {
			final TransactionTypes obj = new TransactionTypes();
			logger.error("Error in Class " + this.getClass().getName() + " cgfkOffTxnTxnTypeRecordGroup error :: ", e);
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
	@RequestMapping(value = "/ocdrecei/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdreceiService.offTxnExecuteQuery(searchBean);
			logger.info("offTxnExecuteQuery response" + searchResult);
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error("Error in Class " + this.getClass().getName() + " offTxnExecuteQuery error :: ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/ocdrecei/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody OffenderTransactions offTxnCommit(@RequestBody OffenderTransactions commitBean) {
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			commitBean = ocdreceiService.offTxnCommit(commitBean);
			logger.info("offTxnCommit response" + commitBean);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " offTxnCommit error :: ", e);
		}
		return commitBean;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/whenValidateItem", method = RequestMethod.POST)
	public OffenderTransactions whenValidateItem(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions searchResult = new OffenderTransactions();
		try {
			searchResult = ocdreceiService.whenValidateItem(searchBean);
			logger.info("whenValidateItem response" + searchResult);
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			bean.setErrorMessage(e.getMessage());
			logger.error("Error in Class " + this.getClass().getName() + " whenValidateItem error :: ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/whenNewRecInstance", method = RequestMethod.POST)
	public OffenderTransactions whenNewRecInstance(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions searchResult = new OffenderTransactions();
		try {
			searchResult = ocdreceiService.preCommit(searchBean);
			logger.info("preCommit response" + searchResult);
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			bean.setErrorMessage(e.getMessage());
			logger.error("Error in Class " + this.getClass().getName() + " whenNewRecInstance error :: ", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/whenNewBlockInstance", method = RequestMethod.POST)
	public OffenderTransactions whenNewBlockInstance(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions searchResult = new OffenderTransactions();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setModifyUserId(userName);
			}
			searchResult = ocdreceiService.whenNewBlockInstance(searchBean);
			logger.info("whenNewBlockInstance response" + searchResult);
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error("Error in Class " + this.getClass().getName() + " whenNewBlockInstance error :: ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/validateDspInfoNumber", method = RequestMethod.POST)
	public Long validateDspInfoNumber(@RequestBody final OffenderTransactions searchBean) {
		Long offenderId = 0L;
		try {
			offenderId = ocdreceiService.validateDspInfoNumber(searchBean);
			logger.info("validateDspInfoNumber response" + offenderId);
		} catch (final Exception e) {
			final OffenderTransactions bean = new OffenderTransactions();
			logger.error("Error in Class " + this.getClass().getName() + " validateDspInfoNumber error :: ", e);
			bean.setErrorMessage(e.getMessage());
		}
		return offenderId;
	}
	
	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/keyCommitTwo", method = RequestMethod.POST)
	public @ResponseBody OffenderTransactions keyCommitTwo(@RequestBody OffenderTransactionsCommitBean commitBean) {
		OffenderTransactions returnBean = new OffenderTransactions();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			returnBean = ocdreceiService.offenderReceiptsCommit(commitBean);
			logger.info("offenderReceiptsCommit response" + returnBean);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " keyCommitTwo error :: ", e);
			if (e.getMessage().contains("PRINT_RECEIPTS_TMP")) {
				returnBean.setSealFlag("4");
			} else if (e.getMessage().contains("TRUST.CHK_ACCOUNT_STATUS")) {
				returnBean.setSealFlag("5");
			} else if (e.getMessage().contains("TRUST.GET_SUB_ACT_TYPE")) {
				returnBean.setSealFlag("6");
			} else if (e.getMessage().contains("GET_PROD_FLAG_DETAILS")) {
				returnBean.setSealFlag("7");
			} else if (e.getMessage().contains("PROCESS_GL_TRANS_NEW")) {
				returnBean.setSealFlag("8");
			} else if (e.getMessage().contains("UPDATE_OFFENDER_BALANCE")) {
				returnBean.setSealFlag("9");
			} else if (e.getMessage().contains("DO_DEDUCTIONS_FINANCIAL")) {
				returnBean.setSealFlag("10");
			} else if (e.getMessage().contains("BILL_DECREASED")) {
				returnBean.setSealFlag("12");
			} else if (e.getMessage() != null) {
				returnBean.setSealFlag("0");
			}
		}
		return returnBean;
	}
	
	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/printReport", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> printReport(@RequestBody OffenderTransactions commitBean) {
		List<OffenderTransactions> list = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			list = ocdreceiService.printReport(commitBean);
			logger.info("printReport response" + list);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " printReport error :: ", e);
		}
		return list;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/getSystemProfileValue", method = RequestMethod.GET)
	public @ResponseBody String getSystemProfileValue() {
		String returnVal = null;
		try {
			returnVal = ocdreceiService.getSystemProfileValue();
			logger.info("getSystemProfileValue response" + returnVal);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getSystemProfileValue error :: ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/getCfPaymentSystemProfileValue", method = RequestMethod.GET)
	public @ResponseBody String getCfPaymentSystemProfileValue() {
		String returnVal = null;
		try {
			returnVal = ocdreceiService.getCfPaymentSystemProfileValue();
			logger.info("getCfPaymentSystemProfileValue response" + returnVal);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " getCfPaymentSystemProfileValue error :: ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/getOffenederFeeSectionQuery", method = RequestMethod.GET)
	public List<OffFeeBillTransactions> getOffenederFeeSectionQuery(@RequestParam String offenderIdDisplay) {
		List<OffFeeBillTransactions> returnList = new ArrayList<OffFeeBillTransactions>();
		try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnList = ocdreceiService.getOffenederFeeSectionQuery(offenderIdDisplay,userName);
			logger.info("getOffenederFeeSectionQuery response" + returnList);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getOffenederFeeSectionQuery error :: ", e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/getLongestSupervisionExpireDate", method = RequestMethod.GET)
	public Date getLongestSupervisionExpireDate(@RequestParam Long offenderBookId) {
		Date returnList = null;
		try {
			returnList = ocdreceiService.getLongestSupervisionExpireDate(offenderBookId);
			logger.info("getLongestSupervisionExpireDate response" + returnList);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getLongestSupervisionExpireDate error :: ", e);
		}
		return returnList;
	}

	/**
	 * getting cgfkOffTxnDspInformationN LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/getPaymentObligationCount", method = RequestMethod.GET)
	public Integer getPaymentObligationCount(@RequestParam Long offenderId) {
		try {
			return ocdreceiService.getPaymentObligationCount(offenderId);
		} catch (final Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getPaymentObligationCount error :: ", e);
			return 0;
		}
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/printReportSupv", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> printReportSupv(@RequestBody OffenderTransactions commitBean) {
		List<OffenderTransactions> list = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			list = ocdreceiService.printReportSupv(commitBean);
			logger.info("printReportSupv response" + list);
		} catch (final Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " printReportSupv error :: ", e);
		}
		return list;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdrecei/getFeeCodeRecordGroup", method = RequestMethod.GET)
	public List<FeeAccounts> getFeeCodeRecordGroup() {
		List<FeeAccounts> returnVal = new ArrayList<FeeAccounts>();
		try {
			returnVal = ocdreceiService.getFeeCodeRecordGroup();
			logger.info("getFeeCodeRecordGroup response" + returnVal);
		} catch (Exception e) {

			logger.error("Error in Class " + this.getClass().getName() + " getFeeCodeRecordGroup error :: ", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/getbillEndDayPfVal", method = RequestMethod.GET)
	public String getbillEndDayPfVal() {
		String returnVal = null;
		try {
			returnVal = ocdreceiService.getbillEndDayPfVal();
			logger.info("getbillEndDayPfVal response" + returnVal);
		} catch (Exception e) {
			logger.error("Error in Class " + this.getClass().getName() + " getbillEndDayPfVal error :: ", e);
		}
		return returnVal;
	}

}