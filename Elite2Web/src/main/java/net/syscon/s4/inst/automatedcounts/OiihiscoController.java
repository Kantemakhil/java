package net.syscon.s4.inst.automatedcounts;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocationCounts;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCountTypes;
import net.syscon.s4.inst.automatedcounts.beans.AgencyCounts;

/**
 * Class OiihiscoController
 */
@EliteController
public class OiihiscoController {
	@Autowired
	private OiihiscoService oiihiscoService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiihiscoController.class.getName());

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiihisco/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiihiscoService.cgfkAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkCountTypes LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiihisco/cgfkCountTypesRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> cgfkCountTypesRecordGroup(@RequestParam(value = "location") final String location) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiihiscoService.cgfkCountTypesRecordGroup(location);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * getting cgfkSchTime LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiihisco/cgfkSchTimeRecordGroup", method = RequestMethod.GET)
	public List<AgencyCountTypes> cgfkSchTimeRecordGroup(
			@RequestParam(value = "countTypeCode") final String countTypeCode,
			@RequestParam(value = "agylocId") final String agylocId) {
		List<AgencyCountTypes> recordList = new ArrayList<AgencyCountTypes>();
		try {
			recordList = oiihiscoService.cgfkSchTimeRecordGroup(countTypeCode, agylocId);
		} catch (Exception e) {
			logger.error(e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiihisco/agencyCountsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyCounts> agencyCountsExecuteQuery(final @RequestBody AgencyCounts searchBean) {
		List<AgencyCounts> searchResult = new ArrayList<>();
		try {
			searchResult = oiihiscoService.agencyCountsExecuteQuery(searchBean);
		} catch (Exception e) {
			AgencyCounts bean = new AgencyCounts();
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
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiihisco/agencyLocationCountsExecuteQuery", method = RequestMethod.POST)
	public List<AgencyLocationCounts> agencyLocationCountsExecuteQuery(
			final @RequestBody AgencyLocationCounts searchBean) {
		List<AgencyLocationCounts> searchResult = new ArrayList<>();
		try {
			searchResult = oiihiscoService.agencyLocationCountsExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error(e);
		}
		return searchResult;
	}

}