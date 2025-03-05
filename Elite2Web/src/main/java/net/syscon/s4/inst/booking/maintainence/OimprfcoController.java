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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.ProfileCodes;
import net.syscon.s4.im.beans.ProfileCodesCommitBean;
import net.syscon.s4.im.beans.ProfileTypes;

@EliteController
public class OimprfcoController {
	@Autowired
	private OimprfcoService oimprfcoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimprfcoController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprfco/pflTypeExecuteQuery", method = RequestMethod.POST)
	public List<ProfileTypes> pflTypeExecuteQuery(@RequestBody ProfileTypes searchBean) {
		List<ProfileTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimprfcoService.pflTypeExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprfco/pflCodeExecuteQuery", method = RequestMethod.POST)
	public List<ProfileCodes> pflCodeExecuteQuery(@RequestBody ProfileCodes searchBean) {
		List<ProfileCodes> searchResult = new ArrayList<>();
		try {
			searchResult = oimprfcoService.pflCodeExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oimprfco/pflCodeCommit", method = RequestMethod.POST)
	public @ResponseBody List<ProfileCodes> pflCodeCommit(@RequestBody ProfileCodesCommitBean commitBean) {
		List<ProfileCodes> returnList = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			returnList = oimprfcoService.pflCodeCommit(commitBean);
		} catch (Exception e) {
			final ProfileCodes error = new ProfileCodes();
			final String errorMsg = "Error : " + e.getMessage();
			error.setErrorMessage(errorMsg.toUpperCase());
			returnList.add(error);
			logger.error("Exception : Oimprfco", e);
		}
		return returnList;
	}

	/**
	 * Check profile codes.
	 *
	 * @param profileCodes
	 *            the profile codes
	 * @return the integer
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimprfco/checkProfileCodes", method = RequestMethod.GET)
	public @ResponseBody Integer checkProfileCodes(@RequestParam String profileCode) {
		int liReturn = 0;
		try {
			liReturn = oimprfcoService.checkProfileCodes(profileCode);
		} catch (Exception e) {

			logger.error("Exception : Oimprfco", e);
		}
		return liReturn;
	}

}