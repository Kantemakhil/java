package net.syscon.s4.inst.casemanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.Teams;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcutasatController
 */
@EliteController
public class OcutasatController {
	@Autowired
	private OcutasatService ocutasatService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcutasatController.class.getName());

	/**
	 * getting rgAreaType LOV values
	 */
	// @PreAuthorize("hasRole('ROLE_USER')")
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocutasat/rgAreaTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgAreaTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = ocutasatService.rgAreaTypeRecordGroup();
		} catch (final Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgArea LOV values
	 */
	// @PreAuthorize("hasRole('ROLE_USER')")
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocutasat/rgAreaRecordGroup", method = RequestMethod.GET)
	public List<Areas> rgAreaRecordGroup(@RequestParam(value = "areaType") final String areaType) {
		List<Areas> recordList = new ArrayList<Areas>();
		try {
			recordList = ocutasatService.rgAreaRecordGroup(areaType);
		} catch (final Exception e) {
			final Areas obj = new Areas();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
     * @param searchBean {@link Teams}
     * @return a list of the Teams {@link Teams} for the matching passed data
	 */
	// @PreAuthorize("hasRole('ROLE_USER')")
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocutasat/teamsExecuteQuery", method = RequestMethod.POST)
	public List<Teams> teamsExecuteQuery(@RequestBody final Teams searchBean) {
		List<Teams> searchResult = new ArrayList<>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocutasatService.teamsExecuteQuery(searchBean);
		} catch (final Exception e) {
			final Teams bean = new Teams();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUTASAT");
	}
}