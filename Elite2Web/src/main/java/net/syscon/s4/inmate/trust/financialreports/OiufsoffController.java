package net.syscon.s4.inmate.trust.financialreports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;

@Controller
@EliteController
public class OiufsoffController {
	@Autowired
	private OiufsoffService oiufsoffService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiufsoffController.class.getName());

	/**
	 * getting cgfkAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiufsoff/cgfkAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyLocIdRecordGroup(@RequestParam(value = "caselaodId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiufsoffService.cgfkAgyLocIdRecordGroup(caseloadId);
		} catch (final Exception e) {
			logger.error("cgfkAgyLocIdRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiufsoff/cgfkHousingLevelOneRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevelOneRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiufsoffService.cgfkHousingLevelOneRecordGroup(agyLocId);
		} catch (final Exception e) {
			logger.error("cgfkHousingLevelOneRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiufsoff/cgfkHousingLevelTwoRecordGroup", method = RequestMethod.GET)
	public @ResponseBody List<LivingUnits> cgfkHousingLevelTwoRecordGroup(
			@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "parentLivingUnitId") final BigDecimal parentLivingUnitId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiufsoffService.cgfkHousingLevelTwoRecordGroup(agyLocId, parentLivingUnitId);
		} catch (final Exception e) {
			logger.error("cgfkHousingLevelTwoRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting cgfkHousingLevel3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiufsoff/cgfkHousingLevelThreeRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> cgfkHousingLevelThreeRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "parentLivingUnitId") final BigDecimal parentLivingUnitId) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oiufsoffService.cgfkHousingLevelThreeRecordGroup(agyLocId, parentLivingUnitId);
		} catch (final Exception e) {
			logger.error("cgfkHousingLevelThreeRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link OiufsoffGetGeneralOffenders}
	 * @return a list of the OiufsoffGetGeneralOffenders {@link OiufsoffGetGeneralOffenders} for the matched OiufsoffGetGeneralOffenders
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiufsoff/vOffBkgExecuteQuery", method = RequestMethod.POST)
	public List<OiufsoffGetGeneralOffenders> vOffBkgExecuteQuery(
			@RequestBody final OiufsoffGetGeneralOffenders searchBean) {
		List<OiufsoffGetGeneralOffenders> searchResult = new ArrayList<>();
		try {
			searchResult = oiufsoffService.vOffBkgExecuteQuery(searchBean);
		} catch (final Exception e) {
			logger.error("vOffBkgExecuteQuery", e);
		}
		return searchResult;
	}

}