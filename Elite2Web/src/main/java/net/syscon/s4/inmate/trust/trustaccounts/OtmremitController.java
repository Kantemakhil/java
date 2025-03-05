package net.syscon.s4.inmate.trust.trustaccounts;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.inmate.beans.Remitters;
import net.syscon.s4.inmate.beans.RemittersCommitBean;

/**
 * Class OtmremitController
 */
@EliteController
public class OtmremitController {
	@Autowired
	private OtmremitService otmremitService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OtmremitController.class.getName());

	/**
	 * getting cgfkRem1DspDescription LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmremit/cgfkRemDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkRemDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmremitService.cgfkRemDspDescriptionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method cgfkRemDspDescriptionRecordGroup : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting cgfkRem1DspDescription3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmremit/cgfkRemitDspDescriptionRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkRemitDspDescriptionRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmremitService.cgfkRemitDspDescriptionRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method cgfkRemDspDescriptionRecordGroup : ", e);
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
	@RequestMapping(value = "/otmremit/remExecuteQuery", method = RequestMethod.POST)
	public List<Remitters> remExecuteQuery(@RequestBody final Remitters searchBean) {
		List<Remitters> searchResult = new ArrayList<>();
		try {
			searchResult = otmremitService.remExecuteQuery(searchBean);
		} catch (Exception e) {
			final Remitters bean = new Remitters();
			logger.error(this.getClass().getName()+"In method remExecuteQuery : ", e);
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
	@RequestMapping(value = "/otmremit/remCommit", method = RequestMethod.POST)
	public @ResponseBody Integer remCommit(@RequestBody final RemittersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmremitService.remCommit(commitBean);
		} catch (Exception e) {

			logger.error(this.getClass().getName()+"In method remCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmremit/remitExecuteQuery", method = RequestMethod.POST)
	public Remitters remitExecuteQuery(@RequestBody final Remitters searchBean) {
		Remitters searchResult = new Remitters();
		try {
			searchResult = otmremitService.remitExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method remitExecuteQuery : ", e);
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
	@RequestMapping(value = "/otmremit/rem1Commit", method = RequestMethod.POST)
	public @ResponseBody Integer rem1Commit(@RequestBody final RemittersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = otmremitService.rem1Commit(commitBean);
		} catch (Exception e) {

			logger.error(this.getClass().getName()+"In method rem1Commit : ", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmremit/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = otmremitService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error(this.getClass().getName()+"In method sysPflExecuteQuery : ", e);
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
	@RequestMapping(value = "/otmremit/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			liReturn = otmremitService.sysPflCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+"In method sysPflCommit : ", e);
		}
		return liReturn;
	}
	
	/**
	 * getting getCodes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/otmremit/getCodes", method = RequestMethod.GET)
	public List<ReferenceCodes> getCodes() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = otmremitService.getCodes();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(this.getClass().getName()+"In method getCodes : ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
}