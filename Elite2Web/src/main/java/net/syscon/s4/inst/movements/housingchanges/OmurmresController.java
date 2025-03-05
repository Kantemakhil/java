package net.syscon.s4.inst.movements.housingchanges;

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
import net.syscon.s4.inst.movements.beans.ReserveBedLocations;
import net.syscon.s4.inst.movements.beans.ReserveBedLocationscommitBean;

/**
 * Class OmurmresController
 * 
 */
@EliteController
public class OmurmresController {
	@Autowired
	private OmurmresService omurmresService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmurmresController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omurmres/resBlExecuteQuery", method = RequestMethod.POST)
	public List<ReserveBedLocations> resBlExecuteQuery(@RequestBody final ReserveBedLocations searchBean) {
		List<ReserveBedLocations> searchResult = new ArrayList<>();
		try {
			if (searchBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				searchBean.setCreateUserId(userName);
			}
			searchResult = omurmresService.resBlExecuteQuery(searchBean);
		} catch (Exception e) {
			final ReserveBedLocations bean = new ReserveBedLocations();
			logger.error("In method resBlExecuteQuery : ", e);
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
	@RequestMapping(value = "/omurmres/resBlCommit", method = RequestMethod.POST)
	public @ResponseBody Integer resBlCommit(@RequestBody final ReserveBedLocationscommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = omurmresService.resBlCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method resBlCommit : ", e);
		}
		return liReturn;
	}

	/**
	 * getting cgfkResBlRemoveReason LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/omurmres/cgfkResBlRemoveReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkResBlRemoveReasonRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = omurmresService.cgfkResBlRemoveReasonRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method cgfkResBlRemoveReasonRecordGroup : ", e);
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
	@RequestMapping(value = "/omurmres/sysPflExecuteQuery", method = RequestMethod.POST)
	public List<SystemProfiles> sysPflExecuteQuery(@RequestBody final SystemProfiles searchBean) {
		List<SystemProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = omurmresService.sysPflExecuteQuery(searchBean);
		} catch (Exception e) {
			final SystemProfiles bean = new SystemProfiles();
			logger.error("In method sysPflExecuteQuery : ", e);
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
	@RequestMapping(value = "/omurmres/sysPflCommit", method = RequestMethod.POST)
	public @ResponseBody Integer sysPflCommit(@RequestBody final SystemProfilesCommitBean commitBean) {
		int liReturn = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = omurmresService.sysPflCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method sysPflCommit : ", e);
		}
		return liReturn;
	}

}