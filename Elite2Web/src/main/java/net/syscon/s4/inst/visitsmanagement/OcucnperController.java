package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.booking.beans.PersonsCommitBean;

/**
 * Class OcucnperController
 */
@EliteController
public class OcucnperController {
	@Autowired
	private OcucnperService ocucnperService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucnperController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucnper/personsExecuteQuery", method = RequestMethod.POST)
	public List<Persons> personsExecuteQuery(@RequestBody final Persons searchBean) {
		List<Persons> searchResult = new ArrayList<>();
		try {
			searchResult = ocucnperService.personsExecuteQuery(searchBean);
		} catch (Exception e) {
			final Persons bean = new Persons();
			logger.error("In method personsExecuteQuery", e);
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocucnper/personsCommit", method = RequestMethod.POST)
	public @ResponseBody Integer personsCommit(@RequestBody final PersonsCommitBean commitBean) {
		int liReturn = 0;
		String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(username);
		try {
			liReturn = ocucnperService.personsCommit(commitBean);
		} catch (Exception e) {
			logger.error("In method personsCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgSexCode LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocucnper/rgSexCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSexCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = ocucnperService.rgSexCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error("In method rgSexCodeRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}