package net.syscon.s4.inmate.trust.generalledger;

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
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.GlTransactions;
import net.syscon.s4.inmate.beans.GlTransactionsCommitBean;

@EliteController
public class OtdglirtController {
	@Autowired
	private OtdglirtService otdglirtService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtdglirtController.class.getName());

	/**
	 * getting cgfkGlTxn3ReversalReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/cgfkGlTxnReversalReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkGlTxnReversalReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otdglirtService.cgfkGlTxnReversalReasonRecordGroup();
		} catch (Exception e) {
			logger.error("cgfkGlTxnReversalReasonRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/glTxnExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		searchBean.setCreateUserId(userName);
		try {
			searchResult = otdglirtService.glTxnExecuteQuery(searchBean);
		} catch (Exception e) {
			GlTransactions errorBean = new GlTransactions();
			String error = "Error : " + e.getMessage();
			errorBean.setErrorMessage(error);
			searchResult.add(errorBean);
			logger.error("glTxnExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/glTxnOneExecuteQuery", method = RequestMethod.POST)
	public List<GlTransactions> glTxnOneExecuteQuery(@RequestBody final GlTransactions searchBean) {
		List<GlTransactions> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = otdglirtService.glTxnOneExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("glTxnOneExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otdglirtService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("sysPflExecuteQuery", e);
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
	@RequestMapping(value = "/otdglirt/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otdglirtService.sysPflCommit(commitBean);
		} catch (Exception e) {
			logger.error("sysPflCommit", e);
		}
		return liReturn;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/glTxnCommit", method = RequestMethod.POST)
	public @ResponseBody Integer glTxnCommit(@RequestBody final GlTransactionsCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = otdglirtService.glTxnCommit(commitBean);
		} catch (Exception e) {
			liReturn = Integer.valueOf(e.getMessage());
		}
		return liReturn;
	}

	/**
	 * getting cgfkGlTxn3ReversalReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/txnReversedFlagData", method = RequestMethod.GET)
	public List<GlTransactions> txnReversedFlagData(@RequestParam(value = "txnId") final Long txnId,
			@RequestParam(value = "txnEntrySeq") final Long txnEntrySeq) {
		List<GlTransactions> recordList = new ArrayList<>();
		try {
			recordList = otdglirtService.txnReversedFlagData(txnId, txnEntrySeq);
		} catch (Exception e) {
			logger.error("cgfkGlTxnReversalReasonRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otdglirt/whenNextbuttonClick", method = RequestMethod.POST)
	public Integer whenNextbuttonClick(@RequestBody final GlTransactions searchBean) {
		Integer searchResult = null;
		try {
			searchResult = otdglirtService.whenNextbuttonClick(searchBean);
		} catch (Exception e) {
			searchResult = Integer.valueOf(e.getMessage());
		}
		return searchResult;
	}

}