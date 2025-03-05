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
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.OffenderTransactions;

@EliteController
public class OcdbreciController {
	@Autowired
	private OcdbreciService ocdbreciService;

	@Autowired
	private OcdreceiService ocdreceiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcdbreciController.class.getName());

	/**
	 * getting cgfkOffTxn1TxnType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/cgfkOffTxn1TxnTypeRecordGroup", method = RequestMethod.GET)
	public List<TransactionTypes> cgfkOffTxn1TxnTypeRecordGroup() {
		List<TransactionTypes> recordList = new ArrayList<TransactionTypes>();
		try {
		String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

			recordList = ocdbreciService.cgfkOffTxn1TxnTypeRecordGroup(user);
		} catch (Exception e) {
			TransactionTypes obj = new TransactionTypes();
			logger.error(this.getClass().getName()+" cgfkOffTxn1TxnTypeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxnDspInformationN LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/cgfkOffTxnDspInformationNRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroup(
			@RequestParam("offenderBookId") final Long offenderBookId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdbreciService.docketRecordGroup(offenderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkOffTxnDspInformationNRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkOffTxnDspInformationN LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/cgfkOffTxnDspInformationNRecordGroupOne", method = RequestMethod.POST)
	public List<ReferenceCodes> cgfkOffTxnDspInformationNRecordGroupOne(
			@RequestBody final OffenderTransactions searchBean) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocdbreciService.cgfkOffTxnDspInformationNRecordGroupOne(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkOffTxnDspInformationNRecordGroupOne", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')") 
	@RequestMapping(value = "/ocdbreci/offTxn1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxn1ExecuteQuery(@RequestBody OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdbreciService.offTxn1ExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/offTxnExecuteQuery", method = RequestMethod.POST)
	public List<OffenderTransactions> offTxnExecuteQuery(@RequestBody final OffenderTransactions searchBean) {
		List<OffenderTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ocdbreciService.offTxn1ExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error(this.getClass().getName()+" offTxn1ExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
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
	@RequestMapping(value = "/ocdbreci/offTxnCommit", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> offTxnCommit(
			@RequestBody final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn = new ArrayList<>();
		OffenderTransactions obj = new OffenderTransactions();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdbreciService.offTxnCommitTemp(commitBean);
		} catch (Exception e) {
			if (e.getMessage().contains("PROCESS_GL_TRANS_NEW")) {
				obj.setSealFlag("8");
				liReturn.add(obj);
			}
			if (e.getMessage().equals("AMT_CANNOT_GREATER_THAN_BALANCE_OWING")) {
				obj.setSealFlag("11");
				liReturn.add(obj);
			}
			logger.error("Exception :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocdbreciService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			SystemProfiles bean = new SystemProfiles();
			logger.error(this.getClass().getName()+" sysPflExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/whenValidateItem", method = RequestMethod.POST)
	public OffenderTransactions whenValidateItem(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions searchResult = new OffenderTransactions();
		try {
			searchResult = ocdbreciService.whenValidateItem(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error(this.getClass().getName()+" whenValidateItem", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/whenValidateItemAmountInfonumber", method = RequestMethod.POST)
	public OffenderTransactions whenValidateItemAmountInfonumber(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions searchResult = new OffenderTransactions();
		try {
			searchResult = ocdbreciService.whenValidateItemAmountInfonumber(searchBean);
		} catch (Exception e) {
			OffenderTransactions bean = new OffenderTransactions();
			logger.error(this.getClass().getName()+" whenValidateItemAmountInfonumber", e);
			bean.setErrorMessage(e.getMessage());
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/getProfileValueDisableBtn", method = RequestMethod.GET)
	public String omMandatoryGrid() {
		String profileValue = null;
		try {
			profileValue = ocdbreciService.getProfileValueDisableBtn();
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" omMandatoryGrid", e);
		}
		return profileValue;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/offFeeExecuteQuery", method = RequestMethod.POST)
	public List<OffFeeBillTransactions> offFeeExecuteQuery(@RequestBody final OffFeeBillTransactions serachBean) {
		List<OffFeeBillTransactions> returnList = new ArrayList<>();
		try {
			returnList = ocdbreciService.offFeeExecuteQuery(serachBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" offFeeExecuteQuery", e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdbreci/supervisionExpiryDate", method = RequestMethod.GET)
	public Date supervisionExpiryDate(@RequestParam Long offenderBookId) {
		Date lsupervDate = null;
		try {
			lsupervDate = ocdbreciService.longestSuperVisionDate(offenderBookId);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" longestSuperVisionDate", e);
		}
		return lsupervDate;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdbreci/offTxnCommitRecipt", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> offTxnCommitRecipt(
			@RequestBody final OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> liReturn = new ArrayList<>();
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocdbreciService.offTxnCommitRecipt(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" offTxnCommitRecipt", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkOffTxnDspInformationN LOV values
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdbreci/cgfkOffTxnDspInformationNRecordGroupCount", method = RequestMethod.GET)
	public Integer cgfkOffTxnDspInfformationNRecordGroupCount(
			@RequestParam("offenderBookId") final Long offenderBookId) {
		Integer count = null;
		try {
			count = ocdbreciService.cgfkOffTxnDspInformationNRecordGroupCount(offenderBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkOffTxnDspInfformationNRecordGroupCount", e);
		}
		return count;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdrecei/getPaymentPlaneCount", method = RequestMethod.GET)
	public Integer getPaymentPlaneCount(@RequestParam Long offenderId) {
		try {
			return ocdreceiService.getPaymentObligationCount(offenderId);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getPaymentPlaneCount", e);
			return 0;
		}
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdbreci/getFeeCodeRecordGroupOne", method = RequestMethod.GET)
	public List<FeeAccounts> getFeeCodeRecordGroupOne() {
		List<FeeAccounts> returnVal = new ArrayList<FeeAccounts>();
		try {
			returnVal = ocdreceiService.getFeeCodeRecordGroup();
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" getFeeCodeRecordGroup", e);
		}
		return returnVal;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocdbreci/printReportSupv", method = RequestMethod.POST)
	public @ResponseBody List<OffenderTransactions> printReportSupv(
			@RequestBody OffenderTransactionsCommitBean commitBean) {
		List<OffenderTransactions> list = new ArrayList<>();
		try {
			list = ocdbreciService.printReportSupv(commitBean);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" printReportSupv", e);
		}
		return list;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocdbreci/getProdFlagDetails", method = RequestMethod.POST)
	public OffenderTransactions getProdFlagDetails(@RequestBody final OffenderTransactions searchBean) {
		OffenderTransactions obj=new OffenderTransactions();
		try {
			obj= ocdbreciService.getProdFlagDetails(searchBean);
		} catch (final Exception e) {
			logger.error(this.getClass().getName()+" getProdFlagDetails", e);
		}
		return obj;
	}
}
