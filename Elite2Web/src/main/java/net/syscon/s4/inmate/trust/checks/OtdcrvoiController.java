package net.syscon.s4.inmate.trust.checks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import net.syscon.s4.im.beans.BankChequeData;
import net.syscon.s4.inmate.beans.BankChequeRegisters;
import net.syscon.s4.inmate.beans.BankChequeRegistersCommitBean;

/**
 * Class OtdcrvoiController
 */
@EliteController
public class OtdcrvoiController {
	@Autowired
	private OtdcrvoiService otdcrvoiService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdcrvoiController.class.getName());

	/**
	 * getting cgfkBankCrChequeStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdcrvoi/cgfkBankCrChequeStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkBankCrChequeStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdcrvoiService.cgfkBankCrChequeStatusRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in cgfkBankCrChequeStatusRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkBankCrAccountCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdcrvoi/cgfkBankCrAccountCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkBankCrAccountCodeRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			recordList = otdcrvoiService.cgfkBankCrAccountCodeRecordGroup(caseloadId,userName);
		} catch (Exception e) {
			logger.error("Exception in cgfkBankCrAccountCodeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link BankChequeRegisters}
	 * @return a list of the BankChequeRegisters {@link BankChequeRegisters} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdcrvoi/bankCrExecuteQuery", method = RequestMethod.POST)
	public List<BankChequeRegisters> bankCrExecuteQuery(@RequestBody final BankChequeRegisters searchBean) {
		List<BankChequeRegisters> searchResult = new ArrayList<>();
		try {
			searchResult = otdcrvoiService.bankCrExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in bankCrExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 *
	 * @param commitBean {@link BankChequeRegistersCommitBean}
	 * @return success/failure of the insert/update {@link String} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otdcrvoi/bankCrCommit", method = RequestMethod.POST)
	public @ResponseBody String bankCrCommit(@RequestBody final BankChequeRegistersCommitBean commitBean) {
		String liReturn = "0";
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otdcrvoiService.bankCrCommit(commitBean);
		} catch (Exception e) {
			liReturn = e.getMessage();
			logger.error("Exception bankCrCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param searchBean {@link BankChequeData}
	 * @return a list of the BankChequeData {@link BankChequeData} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdcrvoi/bnkCdExecuteQuery", method = RequestMethod.POST)
	public List<BankChequeData> bnkCdExecuteQuery(@RequestBody final BankChequeData searchBean) {
		List<BankChequeData> searchResult = new ArrayList<>();
		try {
			searchResult = otdcrvoiService.bnkCdExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in bnkCdExecuteQuery:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param txnId {@link BigDecimal}
	 * @return a mapping list of the data {@link Map} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdcrvoi/whenValidatingItem", method = RequestMethod.GET)
	public Map<String, Object> whenValidatingItem(@RequestParam(value = "txnId") final BigDecimal txnId) {
		Map<String, Object> searchResult = new HashMap<String, Object>();
		try {
			searchResult = otdcrvoiService.whenValidatingItem(txnId);
		} catch (Exception e) {
			logger.error("Exception in whenValidatingItem:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @param txnId {@link BigDecimal}
	 * @return a list of the String {@link String} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdcrvoi/verifyTxnTypeCount", method = RequestMethod.GET)
	public List<String> verifyTxnTypeCount(@RequestParam(value = "txnId") final BigDecimal txnId) {
		List<String> searchResult = new ArrayList<>();
		;
		try {
			searchResult = otdcrvoiService.verifyTxnTypeCount(txnId);
		} catch (Exception e) {
			logger.error("Exception in whenValidatingItem:", e);
		}
		return searchResult;
	}

}