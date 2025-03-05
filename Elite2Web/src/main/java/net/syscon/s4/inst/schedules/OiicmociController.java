package net.syscon.s4.inst.schedules;

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
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.legalscreens.bean.VCourtEvents;

@EliteController
public class OiicmociController {
	@Autowired
	private OiicmociService oiicmociService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiicmociController.class.getName());

	/**
	 * getting rgAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicmoci/rgAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgAgyLocIdRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiicmociService.rgAgyLocIdRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("rgAgyLocIdRecordGroup ", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLu1 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicmoci/rgLu1RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLu1RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiicmociService.rgLu1RecordGroup(agyLocId);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLu2 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicmoci/rgLu2RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLu2RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "livingUnit") final Integer livingUnit) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiicmociService.rgLu2RecordGroup(agyLocId, livingUnit);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgLu3 LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicmoci/rgLu3RecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> rgLu3RecordGroup(@RequestParam(value = "agyLocId") final String agyLocId,
			@RequestParam(value = "livingUnit") final Integer livingUnit) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oiicmociService.rgLu3RecordGroup(agyLocId, livingUnit);
		} catch (Exception e) {
			LivingUnits obj = new LivingUnits();
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VCourtEvents}
	 * @return a list of the VCourtEvents {@link VCourtEvents} for the matched
	 *         VCourtEvents
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiicmoci/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VCourtEvents> offSchExecuteQuery(@RequestBody VCourtEvents searchBean) {
		List<VCourtEvents> searchResult = new ArrayList<>();
		try {
			searchResult = oiicmociService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			VCourtEvents bean = new VCourtEvents();
			logger.error(e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

}