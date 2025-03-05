package net.syscon.s4.sa.admin.mergeoffenders;

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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.sa.admin.beans.VMergeTransactionProcesses;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionCommitBean;
import net.syscon.s4.sa.recordmaintenance.MergeTransactionProcessesCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

/**
 * @author Vrnda Software Technologies
 * @version 1.0
 */
@EliteController
public class OummerofController {

	@Autowired
	private OummerofService oummerofService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	@Autowired
	private OumtrnbkService oumtrnbkService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OummerofController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/offBooksExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBooksExecuteQuery(@RequestBody OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = oummerofService.offBooksExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderBookings bean = new OffenderBookings();
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
	@RequestMapping(value = "/oummerof/offBooks2ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBooks2ExecuteQuery(@RequestBody OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = oummerofService.offBooksExecuteQuery(searchBean);
		} catch (Exception e) {
			OffenderBookings bean = new OffenderBookings();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/vOffBkgExecuteQuery", method = RequestMethod.POST)
	public List<VHeaderBlock> vOffBkgExecuteQuery(@RequestBody final VHeaderBlock searchBean) {
		List<VHeaderBlock> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = oummerofService.vOffBkgExecuteQuery(searchBean);
		} catch (Exception e) {
			final VHeaderBlock bean = new VHeaderBlock();
			logger.error("In vOffBkgExecuteQuery method : ", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/rgAssignmentReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAssignmentReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oummerofService.rgAssignmentReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In rgAssignmentReasonRecordGroup method : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/manualCreateRequest", method = RequestMethod.POST)
	public @ResponseBody String manualCreateRequest(@RequestBody MergeTransactionBean bean, @RequestHeader HttpHeaders headers) {
		String liReturn = null;
		try {
			if (bean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				bean.setCreateUserId(userName);
				bean.setModifyUserId(userName);
			}
			List<MergeTransactionBean> list = new ArrayList<>();
			list.add(bean);
			MergeTransactionCommitBean commitBean = new MergeTransactionCommitBean();
			commitBean.setInsertList(list);
			liReturn = oummerofService.manualCreateRequest(bean);
			List<String> authorizationList = headers.get("Authorization");
			String authorization = authorizationList.get(0).split(",")[0];
			if(liReturn.equalsIgnoreCase("success")) {
				prosmainService.enableTriggers(commitBean, authorization, "120");
			}
		} catch (Exception e) {

			logger.error("Exception in processTransferTransaction: Oumtrnbk", e);
		}
		return liReturn;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/chkOffendersForTransfer", method = RequestMethod.POST)
	public @ResponseBody Integer chkOffendersForTransfer(@RequestBody MergeTransactionBean bean) {
		Integer liReturn = null;
		try {
			liReturn = oummerofService.chkOffendersForTransfer(bean);
		} catch (Exception e) {

			logger.error("Exception in chkOffendersForTransfer: Oumtrnbk", e);
		}
		return liReturn;
	}
	
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/getNewOffId", method = RequestMethod.GET)
	public Long getNewOffId(@RequestParam(value = "offBookId") final Long offBookId) {
		Long offenderId =null;
		try {
			offenderId = oummerofService.getNewOffId(offBookId);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getNewOffId  : " + e);
		}
		return offenderId;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oummerof/mrgProcExecuteQuery", method = RequestMethod.POST)
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
	@RequestMapping(value = "/oummerof/mrgProcCommit", method = RequestMethod.POST)
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
	@RequestMapping(value = "/oummerof/chkOffendersForTransfer1", method = RequestMethod.POST)
	public @ResponseBody String chkOffendersForTransfer1(@RequestBody MergeTransactionBean bean) {
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
	@RequestMapping(value = "/oummerof/processTransferTransaction", method = RequestMethod.POST)
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
	@RequestMapping(value = "/oummerof/mrgProcExecuteQueryRet", method = RequestMethod.POST)
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