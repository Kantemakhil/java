package net.syscon.s4.sa;

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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.StaffAccessibleCaseloads;

/**
 * Class OumcpassController
 */
@EliteController
public class OumcpassController {
	@Autowired
	private OumcpassService oumcpassService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumcpassController.class.getName());
	/**
	 * getting navigationDummy LOV values
	 */

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcpass/staffExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembers> staffExecuteQuery(@RequestBody StaffMembers searchBean) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oumcpassService.staffExecuteQuery(searchBean);
		} catch (Exception e) {
			StaffMembers bean = new StaffMembers();
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
	@RequestMapping(value = "/oumcpass/staffCommit", method = RequestMethod.POST)
	public @ResponseBody Integer staffCommit(@RequestParam(value = "userId") final String userId) {
		int liReturn = 0;
		try {
			liReturn = oumcpassService.staffCommit(userId);
		} catch (Exception e) {

			logger.error("Exception : Oumcpass", e);
		}
		return liReturn;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcpass/staffAcExecuteQuery", method = RequestMethod.POST)
	public List<StaffAccessibleCaseloads> staffAcExecuteQuery(@RequestBody StaffAccessibleCaseloads searchBean) {
		List<StaffAccessibleCaseloads> searchResult = new ArrayList<>();
		try {
			searchResult = oumcpassService.staffAcExecuteQuery(searchBean);
		} catch (Exception e) {
			StaffAccessibleCaseloads bean = new StaffAccessibleCaseloads();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcpass/authenticate", method = RequestMethod.GET)
	public @ResponseBody Boolean authenticate(
			@RequestParam(value = "userName") final String userName,@RequestParam(value = "password") final String password) {
		Boolean listOfRecords = null;
		try {
			listOfRecords = oumcpassService.authenticate(userName,password);
		} catch (Exception e) {
			logger.error("authenticate", e);
		}
		return listOfRecords;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcpass/changePassword", method = RequestMethod.GET)
	public @ResponseBody Integer changePassword(
			@RequestParam(value = "userName") final String userName,@RequestParam(value = "oldPassword") final String oldPassword,
			@RequestParam(value = "newPassword") final String newPassword) {
		Integer listOfRecords = null;
		try {
			String loggedUserName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			listOfRecords = oumcpassService.changePassword(userName,oldPassword,newPassword,loggedUserName);
		} catch (Exception e) {
			logger.error("changePassword", e);
		}
		return listOfRecords;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumcpass/validatePassword", method = RequestMethod.GET)
	public @ResponseBody String validatePassword(@RequestParam(value = "newPassword") final String newPassword) {
		String listOfRecords = null;
		try {
			listOfRecords = oumcpassService.validatePassword(newPassword);
		} catch (Exception e) {
			logger.error("changePassword", e);
		}
		return listOfRecords;
	}
	
}