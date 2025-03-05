package net.syscon.s4.inmate.trust.statements;

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
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TransStatementBean;
import net.syscon.s4.inmate.beans.AccountCodesCommitBean;
import net.syscon.s4.inmate.beans.Printers;

/**
 * class OtstastaController
 */
@EliteController
public class OtstastaController {
	@Autowired
	private OtstastaService otstastaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtstastaController.class.getName());

	/**
	 * getting cgfkOmsReqPrinterId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otstasta/cgfkOmsReqPrinterIdRecordGroup", method = RequestMethod.GET)
	public List<Printers> cgfkOmsReqPrinterIdRecordGroup() {
		List<Printers> recordList = new ArrayList<Printers>();
		try {
			recordList = otstastaService.cgfkOmsReqPrinterIdRecordGroup();
		} catch (Exception e) {
			final Printers obj = new Printers();
			logger.error(e);
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkAcCodeSubAccountType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otstasta/cgfkAcCodeSubAccountTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otstastaService.cgfkAcCodeSubAccountTypeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link AccountCodes}
	 * @return a list of the AccountCodes {@link AccountCodes} for the matched AccountCodes
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otstasta/acCodeExecuteQuery", method = RequestMethod.POST)
	public List<AccountCodes> acCodeExecuteQuery(@RequestBody final AccountCodes searchBean) {
		List<AccountCodes> searchResult = new ArrayList<>();
		try {
			searchResult = otstastaService.acCodeExecuteQuery(searchBean);
		} catch (Exception e) {
			final AccountCodes bean = new AccountCodes();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otstasta/acCodeCommit", method = RequestMethod.POST)
	public @ResponseBody Integer acCodeCommit(@RequestBody final AccountCodesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otstastaService.acCodeCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OffenderBookings}
	 * @return a list of the OffenderBookings {@link OffenderBookings} for the matched OffenderBookings
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otstasta/offBkg1ExecuteQuery", method = RequestMethod.POST)
	public List<OffenderBookings> offBkg1ExecuteQuery(@RequestBody final OffenderBookings searchBean) {
		List<OffenderBookings> searchResult = new ArrayList<>();
		try {
			searchResult = otstastaService.offBkg1ExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderBookings bean = new OffenderBookings();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Perfomring basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otstasta/offBkg1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer offBkg1Commit(@RequestBody final OffenderBookingsCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = otstastaService.offBkg1Commit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link SystemProfiles}
	 * @return a list of the SystemProfiles {@link SystemProfiles} for the matched SystemProfiles
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otstasta/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otstastaService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otstasta/mainProcess", method = RequestMethod.POST)
	public ReportBean mainProcess(@RequestBody TransStatementBean paramBean) {
		ReportBean returnList = new ReportBean();
		try {
			returnList = otstastaService.mainProcess(paramBean);
		} catch (Exception e) {
			returnList.setErrorMessage("Exception : " + e.getMessage());
			logger.error("mainProcess : ", e);
		}
		return returnList;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/otstasta/mainReprtProcess", method = RequestMethod.POST)
	public ReportBean mainReprtProcess(@RequestBody List<TransStatementBean> paramBean) {
		ReportBean returnList = new ReportBean();
		try {
			returnList = otstastaService.superFilese(paramBean);
		} catch (Exception e) {
			returnList.setErrorMessage("Exception : " + e.getMessage());
			logger.error("mainReprtProcess : ", e);
		}
		return returnList;
	}

}