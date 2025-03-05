package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;
import net.syscon.s4.sa.admin.beans.MergeTransactions;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;

@EliteController
public class OuimergeController {
	@Autowired
	private OuimergeService ouimergeService;
	
	@Autowired
	private OuimtlogService ouimtlogService;
	
	@Autowired
	private OuimtstpService ouimtstpService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuimergeController.class.getName());

	/**
	 * getting rgStatus LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouimerge/rgStatusRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgStatusRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ouimergeService.rgStatusRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgStatusRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgSource LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouimerge/rgSourceRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSourceRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ouimergeService.rgSourceRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception in rgSourceRecordGroup:", e);
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
	@RequestMapping(value = "/ouimerge/transactionsExecuteQuery", method = RequestMethod.POST)
	public List<MergeTransactions> transactionsExecuteQuery(@RequestBody final MergeTransactions searchBean) {
		List<MergeTransactions> searchResult = new ArrayList<>();
		try {
			searchResult = ouimergeService.transactionsExecuteQuery(searchBean);
		} catch (Exception e) {
			final MergeTransactions bean = new MergeTransactions();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouimerge/mergeLogExecuteQuery", method = RequestMethod.POST)
	public List<MergeTransactionLogs> mergeLogExecuteQuery(@RequestBody final MergeTransactionLogs searchBean) {
		List<MergeTransactionLogs> searchResult = new ArrayList<>();
		try {
			searchResult = ouimtlogService.mergeLogExecuteQuery(searchBean);
		} catch (Exception e) {
			final MergeTransactionLogs bean = new MergeTransactionLogs();
			logger.error("Exception : mergeLogExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value="/ouimerge/mergTxnProcExecuteQuery", method=RequestMethod.POST)
	public List<VMergeTransactionProcesses> mergTxnProcExecuteQuery(@RequestBody final VMergeTransactionProcesses searchBean) {
		List<VMergeTransactionProcesses> searchResult = new ArrayList<>();
		try {
			searchResult = ouimtstpService.mergTxnProcExecuteQuery(searchBean);
		} catch (Exception e) {
			final VMergeTransactionProcesses bean = new VMergeTransactionProcesses();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		for (VMergeTransactionProcesses result : searchResult) {
			if(result.getBeginDate() == null && result.getEndDate() == null && "Y".equals(result.getTimeframeFlag())) {
				result.setTransferFlag("N");
			}
		}
		return searchResult;
	}
}