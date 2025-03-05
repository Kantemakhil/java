package net.syscon.s4.inst.movements.maintenance;

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
import net.syscon.s4.im.beans.AgyIntLocAmendQuery;
import net.syscon.s4.im.beans.LivingUnits;

@EliteController
public class OumhlhisController {
	@Autowired
	private OumhlhisService oumhlhisService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumhlhisController.class.getName());

	/**
	 * getting livingUnit1Rg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhlhis/livingUnit1RgRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitOneRgRecordGroup(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oumhlhisService.livingUnitOneRgRecordGroup(agyLocId);
		} catch (Exception e) {
			logger.error("Exception : Oumhlhis:", e);
		}
		return recordList;
	}

	/**
	 * getting livingUnit2Rg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhlhis/livingUnit2RgRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitTwoRgRecordGroup(@RequestParam(value = "livingUnitId") final Long livingUnitId,
			@RequestParam(value = "level1Code") final String level1Code) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oumhlhisService.livingUnitTwoRgRecordGroup(livingUnitId, level1Code);
		} catch (Exception e) {
			logger.error("Exception : Oumhlhis:", e);
		}
		return recordList;
	}

	/**
	 * getting livingUnit3Rg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhlhis/livingUnit3RgRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitThreeRgRecordGroup(@RequestParam(value = "livingUnitId") final Long livingUnitId,
			@RequestParam(value = "level2Code") final String level2Code) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oumhlhisService.livingUnitThreeRgRecordGroup(livingUnitId, level2Code);
		} catch (Exception e) {
			logger.error("Exception : Oumhlhis:", e);
		}
		return recordList;
	}

	/**
	 * getting livingUnit4Rg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhlhis/livingUnit4RgRecordGroup", method = RequestMethod.GET)
	public List<LivingUnits> livingUnitFourRgRecordGroup(@RequestParam(value = "livingUnitId") final Long livingUnitId,
			@RequestParam(value = "level3Code") final String level3Code) {
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = oumhlhisService.livingUnitFourRgRecordGroup(livingUnitId, level3Code);
		} catch (Exception e) {
			logger.error("livingUnit4RgRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhlhis/vAgyIntLocAmendExecuteQuery", method = RequestMethod.POST)
	public List<AgyIntLocAmendQuery> vAgyIntLocAmendExecuteQuery(@RequestBody final AgyIntLocAmendQuery searchBean) {
		List<AgyIntLocAmendQuery> searchResult = new ArrayList<AgyIntLocAmendQuery>();
		try {
			searchResult = oumhlhisService.vAgyIntLocAmendExecuteQuery(searchBean);
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
	@RequestMapping(value = "/oumhlhis/getAgyLocIdDescReturn", method = RequestMethod.GET)
	public String getAgyLocIdDescReturn() {
		String searchResult = null;
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			searchResult = oumhlhisService.getAgyLocIdDescReturn(userId);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return searchResult;
	}
	/**
	 * getting livingUnit1Rg LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oumhlhis/getLivingunitId", method = RequestMethod.GET)
	public List<LivingUnits> getLivingunitId(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<LivingUnits> recordList = new ArrayList<>();
		try {
			recordList = oumhlhisService.getLivingunitId(agyLocId);
		} catch (Exception e) {
			logger.error("Exception in getLivingunitId: Oumhlhis:", e);
		}
		return recordList;
	}

}