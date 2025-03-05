package net.syscon.s4.inst.booking.maintainence;

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
import net.syscon.s4.common.beans.ProfileCategory;
import net.syscon.s4.im.beans.ProfileCategoriesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.ProfileTypesCommitBean;

@EliteController
public class OimprfcaController {
	@Autowired
	private OimprfcaService oimprfcaService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimprfcaController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprfca/pflCatExecuteQuery", method = RequestMethod.POST)
	public List<ProfileCategory> pflCatExecuteQuery(@RequestBody final ProfileCategory searchBean) {
		List<ProfileCategory> searchResult = new ArrayList<>();
		try {
			searchResult = oimprfcaService.pflCatExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update into
	 * the database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimprfca/pflCatCommit", method = RequestMethod.POST)
	public @ResponseBody List<ProfileCategory> pflCatCommit(@RequestBody final ProfileCategoriesCommitBean commitBean) {
		List<ProfileCategory> returnList = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			returnList = oimprfcaService.pflCatCommit(commitBean);
		} catch (Exception e) {
			final ProfileCategory error = new ProfileCategory();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg);
			error.setSealFlag("0");
			returnList.add(error);
			logger.error("Exception  : oimprfca", e);
		}
		return returnList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprfca/pflTypeExecuteQuery", method = RequestMethod.POST)
	public List<ProfileTypes> pflTypeExecuteQuery(@RequestBody final ProfileTypes searchBean) {
		List<ProfileTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimprfcaService.pflTypeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
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
	@RequestMapping(value = "/oimprfca/pflTypeCommit", method = RequestMethod.POST)
	public @ResponseBody List<ProfileTypes> pflTypeCommit(@RequestBody final ProfileTypesCommitBean commitBean) {
		List<ProfileTypes> returnList = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			returnList = oimprfcaService.pflTypeCommit(commitBean);
		} catch (Exception e) {
			final ProfileTypes error = new ProfileTypes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			returnList.add(error);
			logger.error("Exception  : oimprfca", e);
		}
		return returnList;
	}

}