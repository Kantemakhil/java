package net.syscon.s4.inst.institutionalactivities.maintenance;

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
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCommitBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationCommitBean;

@EliteController
public class OcmpspayController {
	@Autowired
	private OcmpspayService ocmpspayService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmpspayController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpspay/rgCompensationTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCompensationTypeRecorGroup(@RequestParam String programCategory) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpspayService.rgCompensationTypeRecorGroup(programCategory);
		} catch (final Exception e) {
			logger.error("Exception in rgCompensationTypeRecorGroup :", e);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpspay/listOfProgServices", method = RequestMethod.GET)
	public List<ProgramServices> listOfProgServices() {
		List<ProgramServices> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpspayService.listOfProgServices();
		} catch (final Exception e) {
			logger.error("Exception in listOfProgServices :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpspay/rgCompensationCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgCompensationCodeRecordGroup(@RequestParam Integer programId) {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpspayService.rgCompensationCodeRecorGroup(programId);
		} catch (final Exception e) {
			logger.error("Exception in rgCompensationCodeRecorGroup :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpspay/prgCategoryExecuteQuery", method = RequestMethod.GET)
	public List<programsPayBean> prgCategoryExecuteQuery() {
		List<programsPayBean> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpspayService.prgCategoryExecuteQuery();
		} catch (final Exception e) {
			logger.error("Exception in prgCategoryExecuteQuery :", e);
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
	@RequestMapping(value = "/ocmpspay/prgCategoryCommit", method = RequestMethod.POST)
	public @ResponseBody Integer prgCategoryCommit(@RequestBody final programsPayCommitBean commitBean) {
		Integer liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmpspayService.prgCategoryCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in prgCategoryCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpspay/prgCampensationExecuteQuery", method = RequestMethod.POST)
	public List<programsPayCompensationBean> prgCampensationExecuteQuery(@RequestBody programsPayBean obj) {
		List<programsPayCompensationBean> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpspayService.prgCampensationExecuteQuery(obj);
		} catch (final Exception e) {
			logger.error("Exception in prgCategoryExecuteQuery :", e);
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
	@RequestMapping(value = "/ocmpspay/prgCampensationCommit", method = RequestMethod.POST)
	public @ResponseBody Integer prgCampensationCommit(
			@RequestBody final programsPayCompensationCommitBean commitBean) {
		Integer liReturn = null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocmpspayService.prgCampensationCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception in prgCampensationCommit :", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocmpspay/rgUnitRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgUnitRecordGroup() {
		List<ReferenceCodes> searchResult = new ArrayList<>();
		try {
			searchResult = ocmpspayService.rgUnitRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception in rgUnitRecordGroup :", e);
		}
		return searchResult;
	}
}
