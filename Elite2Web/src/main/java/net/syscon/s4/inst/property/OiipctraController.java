package net.syscon.s4.inst.property;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;

/**
 * Class OiipctraController
 */
@EliteController
public class OiipctraController {
	@Autowired
	private OiipctraService oiipctraService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiipctraController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiipctra/offConExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyContainers> offConExecuteQuery(@RequestBody final OffenderPptyContainers searchBean) {
		List<OffenderPptyContainers> searchResult = new ArrayList<>();
		final OffenderPptyContainers bean = new OffenderPptyContainers();
		try {
			searchResult = oiipctraService.offConExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
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
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiipctra/conTxExecuteQuery", method = RequestMethod.POST)
	public List<OffenderPptyConTxns> conTxExecuteQuery(@RequestBody final OffenderPptyConTxns searchBean) {
		List<OffenderPptyConTxns> searchResult = new ArrayList<>();
		final OffenderPptyConTxns bean = new OffenderPptyConTxns();
		try {
			searchResult = oiipctraService.conTxExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	/**
	 * getting rgLocationAll LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiipctra/rgLocationAllRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgLocationAllRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<>();
		try {
			recordList = oiipctraService.rgLocationAllRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgLocationAllRecordGroup: ", e);
			logger.error("", e);
		}
		return recordList;
	}

}