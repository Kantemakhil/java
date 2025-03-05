package net.syscon.s4.inst.visitsmanagement;

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
import net.syscon.s4.im.beans.PersonProfiles;
import net.syscon.s4.im.beans.PersonProfilesCommitBean;
import net.syscon.s4.im.beans.ProfileCodes;

/**
 * Class OcuperprController
 */
@EliteController
public class OcuperprController {
	@Autowired
	private OcuperprService ocuperprService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuperprController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuperpr/profilesExecuteQuery", method = RequestMethod.POST)
	public List<PersonProfiles> profilesExecuteQuery(@RequestBody final PersonProfiles searchBean) {
		List<PersonProfiles> searchResult = new ArrayList<>();
		try {
			searchResult = ocuperprService.profilesExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("profilesExecuteQuery",e);
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
	@RequestMapping(value = "/ocuperpr/profilesCommit", method = RequestMethod.POST)
	public @ResponseBody Integer profilesCommit(@RequestBody final PersonProfilesCommitBean commitBean) {
		int liReturn = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			liReturn = ocuperprService.profilesCommit(commitBean);
		} catch (Exception e) {

			logger.error(e);
		}
		return liReturn;
	}

	/**
	 * getting rgProfileCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuperpr/rgProfileCodeRecordGroup", method = RequestMethod.GET)
	public List<ProfileCodes> rgProfileCodeRecordGroup(@RequestParam (value="profileType") final String profileType) {
		List<ProfileCodes> recordList = new ArrayList<ProfileCodes>();
		try {
			recordList = ocuperprService.rgProfileCodeRecordGroup(profileType);
		} catch (Exception e) {
			logger.error("rgProfileCodeRecordGroup",e);
		}
		return recordList;
	}
	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocuperpr/insertProfilesTypes", method = RequestMethod.GET)
	public List<PersonProfiles> insertProfilesTypes(@RequestParam (value="personId") final Integer personId) {
		List<PersonProfiles> searchResult = new ArrayList<>();
		try {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchResult = ocuperprService.insertProfilesTypes(personId,userName);
		} catch (Exception e) {
			logger.error("profilesExecuteQuery",e);
		}
		return searchResult;
	}

}