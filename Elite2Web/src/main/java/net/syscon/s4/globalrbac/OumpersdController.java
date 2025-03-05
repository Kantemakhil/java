package net.syscon.s4.globalrbac;

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
import net.syscon.s4.im.beans.StaffMembersCommitBean;

@EliteController
public class OumpersdController {
	@Autowired
	private OumpersdService oumpersdService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumpersdController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumpersd/staffExecuteQuery", method = RequestMethod.GET)
	public List<StaffMembers> staffExecuteQuery(@RequestParam(value = "staffId") final Integer staffId) {
		List<StaffMembers> searchResult = new ArrayList<>();
		try {
			searchResult = oumpersdService.staffExecuteQuery(staffId);
		} catch (final Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @Param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumpersd/staffCommit", method = RequestMethod.POST)
	public @ResponseBody Integer staffCommit(@RequestBody final StaffMembersCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = oumpersdService.staffCommit(commitBean);
		} catch (final Exception e) {

			logger.error("Exception : Oumpersd", e);
		}
		return liReturn;
	}

}