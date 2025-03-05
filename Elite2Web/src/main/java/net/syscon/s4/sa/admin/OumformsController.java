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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.sa.admin.beans.AccessibleFormTables;
import net.syscon.s4.sa.admin.beans.AccessibleFormTablesCommitBean;
import net.syscon.s4.sa.admin.beans.AllTabColumns;

/**
 * class OumformsController
 * 
 */
@EliteController
public class OumformsController { 
	@Autowired
	private OumformsService oumformsService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumformsController.class.getName());

	/**
	 * getting rgModuleName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumforms/rgModuleNameRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgModuleNameRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = oumformsService.rgModuleNameRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oumforms:", e);
		}
		return recordList;
	}

	/**
	 * getting rgTableName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumforms/rgTableNameRecordGroup", method = RequestMethod.GET)
	public List<AllTabColumns> rgTableNameRecordGroup() {
		List<AllTabColumns> recordList = new ArrayList<>();
		try {
			recordList = oumformsService.rgTableNameRecordGroup();
		} catch (final Exception e) {
			logger.error("Exception : Oumforms:", e);
		}
		return recordList;
	}

	/**
	 * getting rgDestinationForm LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumforms/rgDestinationFormRecordGroup", method = RequestMethod.GET)
	public List<OmsModules> rgDestinationFormRecordGroup() {
		List<OmsModules> recordList = new ArrayList<OmsModules>();
		try {
			recordList = oumformsService.rgDestinationFormRecordGroup();
		} catch (Exception e) {
			logger.error("rgDestinationFormRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumforms/omsModExecuteQuery", method = RequestMethod.POST)
	public List<OmsModules> omsModExecuteQuery(@RequestBody final OmsModules searchBean) {
		List<OmsModules> searchResult = new ArrayList<>();
		try {
			searchResult = oumformsService.omsModExecuteQuery(searchBean);
		} catch (final Exception e) {
			final OmsModules bean = new OmsModules();
			logger.error("Exception :/OUMFORMS_fmb", e);
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
	@RequestMapping(value = "/oumforms/fafExecuteQuery", method = RequestMethod.POST)
	public List<FormAccessibleForms> fafExecuteQuery(@RequestBody final FormAccessibleForms searchBean) {
		List<FormAccessibleForms> searchResult = new ArrayList<>();
		try {
			searchResult = oumformsService.fafExecuteQuery(searchBean);
		} catch (final Exception e) {
			final FormAccessibleForms bean = new FormAccessibleForms();
			logger.error("Exception :/OUMFORMS_fmb", e);
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
	@RequestMapping(value = "/oumforms/fafCommit", method = RequestMethod.POST)
	public @ResponseBody Integer fafCommit(@RequestBody final FormAccessibleFormsCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumformsService.fafCommit(commitBean); 
		} catch (final Exception e) {

			logger.error("Exception", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumforms/accessTabExecuteQuery", method = RequestMethod.POST)
	public List<AccessibleFormTables> accessTabExecuteQuery(@RequestBody final AccessibleFormTables searchBean) {
		List<AccessibleFormTables> searchResult = new ArrayList<>();
		try {
			searchResult = oumformsService.accessTabExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("Exception", e);
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
	@RequestMapping(value = "/oumforms/accessTabCommit", method = RequestMethod.POST)
	public @ResponseBody AccessibleFormTables accessTabCommit(
			@RequestBody final AccessibleFormTablesCommitBean commitBean) {
		AccessibleFormTables liReturn = new AccessibleFormTables();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumformsService.accessTabCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Oumforms", e);
		}
		return liReturn;
	}

}