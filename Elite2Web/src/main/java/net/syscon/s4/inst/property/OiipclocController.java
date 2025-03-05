package net.syscon.s4.inst.property;

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

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Class OiipclocController
 */
@EliteController
public class OiipclocController {
	@Autowired
	private OiipclocService oiipclocService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiipclocController.class.getName());

	/**
	 * getting rgContainerCode LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiipcloc/rgContainerCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgContainerCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiipclocService.rgContainerCodeRecordGroup();
		} catch (Exception e) {
			final ReferenceCodes obj = new ReferenceCodes();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgDescription2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiipcloc/rgDescriptionRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgDescription2RecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<AgencyInternalLocations>();
		try {
			recordList = oiipclocService.rgDescriptionRecordGroup(caseloadId);
		} catch (Exception e) {
			final AgencyInternalLocations obj = new AgencyInternalLocations();
			logger.error(e);
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
	@RequestMapping(value = "/oiipcloc/offConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offConExecuteQuery(final @RequestBody OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			searchBean.setCreateUserId(userName);
			searchResult = oiipclocService.offConExecuteQuery(searchBean);
		} catch (Exception e) {
			final OffenderPptyContainers bean = new OffenderPptyContainers();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}