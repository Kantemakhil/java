package net.syscon.s4.sa.admin.mergeoffenders;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionProcessesCommitBean;

/**
 * Class OumtrnbkController
 */
@EliteController
public class OumtrnbkController {
	@Autowired
	private OumtrnbkService oumtrnbkService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumtrnbkController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumtrnbk/mrgProcExecuteQuery", method = RequestMethod.POST)
	public List<VMergeTransactionProcesses> mrgProcExecuteQuery(@RequestBody MergeTransactionBean searchBean) {
		List<VMergeTransactionProcesses> searchResult = new ArrayList<>();
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(user);
			searchResult = oumtrnbkService.mrgProcExecuteQuery(searchBean);
		} catch (Exception e) {
			VMergeTransactionProcesses bean = new VMergeTransactionProcesses();
			logger.error("Exception in mrgProcExecuteQuery :", e);
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
	@RequestMapping(value = "/oumtrnbk/mrgProcCommit", method = RequestMethod.POST)
	public @ResponseBody Integer mrgProcCommit(@RequestBody MergeTransactionProcessesCommitBean commitBean) {
		int liReturn = 0;
		
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = oumtrnbkService.mrgProcCommit(commitBean);
		} catch (Exception e) {

			logger.error("Exception in mrgProcCommit: Oumtrnbk", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumtrnbk/chkOffendersForTransfer", method = RequestMethod.POST)
	public @ResponseBody String chkOffendersForTransfer(@RequestBody MergeTransactionBean bean) {
		Integer val = null;
		String result = null;
		try {
			val = oumtrnbkService.chkOffendersForTransfer(bean);
		} catch (Exception e) {
			logger.error("Exception in chkOffendersForTransfer: Oumtrnbk", e);
		}
		if (val == 0) {
			result = "success";
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumtrnbk/processTransferTransaction", method = RequestMethod.POST)
	public @ResponseBody String processTransferTransaction(@RequestBody MergeTransactionBean bean) {
		String liReturn = null;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
				bean.setModifyUserId(userName);
			}
			liReturn = oumtrnbkService.processTransferTransaction(bean);
		} catch (Exception e) {

			logger.error("Exception in processTransferTransaction: Oumtrnbk", e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumtrnbk/mrgProcExecuteQueryRet", method = RequestMethod.POST)
	public List<VMergeTransactionProcesses> mrgProcExecuteQueryRet(@RequestBody MergeTransactionBean searchBean) {
		List<VMergeTransactionProcesses> searchResult = new ArrayList<>();
		try {
			searchResult = oumtrnbkService.mrgProcExecuteQueryRet(searchBean);
		} catch (Exception e) {
			logger.error("Exception in mrgProcExecuteQueryRet :", e);
		}
		return searchResult;
	}
}