package net.syscon.s4.sa.admin;

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
import net.syscon.s4.of.payroll.maintenance.SystemEvents;
import net.syscon.s4.of.payroll.maintenance.SystemEventsCommitBean;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompens;
import net.syscon.s4.sa.admin.beans.CaseloadGrpHolCompensCommitBean;
import net.syscon.s4.sa.admin.beans.CaseloadWorkGroups;

/**
 * @version 1.0
 */
@EliteController
public class OymholidController {
	@Autowired
	private OymholidService oymholidService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OymholidController.class.getName());

	/**
	 * getting cgfkCsldGhcCompensationCod LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oymholid/cgfkCsldGhcCompensationCodRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCsldGhcCompensationCodRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oymholidService.cgfkCsldGhcCompensationCodRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkCsldGhcWorkGroupId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oymholid/cgfkCsldGhcWorkGroupIdRecordGroup", method = RequestMethod.GET)
	public List<CaseloadWorkGroups> cgfkCsldGhcWorkGroupIdRecordGroup(final @RequestParam String caseloadType) {
		List<CaseloadWorkGroups> recordList = new ArrayList<CaseloadWorkGroups>();
		try {
			recordList = oymholidService.cgfkCsldGhcWorkGroupIdRecordGroup(caseloadType);
		} catch (Exception e) {
			final CaseloadWorkGroups obj = new CaseloadWorkGroups();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oymholid/sysEventExecuteQuery", method = RequestMethod.POST)
	public List<SystemEvents> sysEventExecuteQuery(final @RequestBody SystemEvents searchBean) {
		List<SystemEvents> searchResult = new ArrayList<>();
		try {
			searchResult = oymholidService.sysEventExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemEvents bean = new SystemEvents();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oymholid/sysEventCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysEventCommit(final @RequestBody SystemEventsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oymholidService.sysEventCommit(commitBean);
		} catch (Exception e) {
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
	@RequestMapping(value = "/oymholid/csldGhcExecuteQuery", method = RequestMethod.POST)
	public List<CaseloadGrpHolCompens> csldGhcExecuteQuery(final @RequestBody CaseloadGrpHolCompens searchBean) {
		List<CaseloadGrpHolCompens> searchResult = new ArrayList<>();
		try {
			searchResult = oymholidService.csldGhcExecuteQuery(searchBean);
		} catch (Exception e) {
			final CaseloadGrpHolCompens bean = new CaseloadGrpHolCompens();
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oymholid/csldGhcCommit", method = RequestMethod.POST)
	public @ResponseBody Integer csldGhcCommit(final @RequestBody CaseloadGrpHolCompensCommitBean commitBean) {
		int liReturn = 0;
		
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oymholidService.csldGhcCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
}