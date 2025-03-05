package net.syscon.s4.inst.casemanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.VPrisonStatusCount;
import net.syscon.s4.inst.casemanagement.beans.VPrisonTotal;

@EliteController
public class OiiiscouController {
	@Autowired
	private OiiiscouService oiiiscouService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiiiscouController.class.getName());

	/**
	 * getting cgfkAgyLocAgyLocId LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiiscou/cgfkAgyLocAgyLocIdRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> cgfkAgyLocAgyLocIdRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = oiiiscouService.cgfkAgyLocAgyLocIdRecordGroup(caseloadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("cgfkAgyLocAgyLocIdRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param agyLocId {@link String}
	 * @return a list of the VPrisonStatusCount {@link VPrisonStatusCount} for the
	 *         matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiiscou/vPrisnCtExecuteQuery", method = RequestMethod.GET)
	public List<VPrisonStatusCount> vPrisnCtExecuteQuery(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VPrisonStatusCount> searchResult = new ArrayList<>();
		try {
			searchResult = oiiiscouService.vPrisnCtExecuteQuery(agyLocId);
		} catch (Exception e) {
			VPrisonStatusCount bean = new VPrisonStatusCount();
			logger.error("cgfkAgyLocAgyLocIdRecordGroup", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param agyLocId {@link String}
	 * @return a list of the VPrisonTotal {@link VPrisonTotal} for the matched
	 *         agency location Id
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiiscou/vPrisnTotExecuteQuery", method = RequestMethod.GET)
	public List<VPrisonTotal> vPrisnTotExecuteQuery(@RequestParam(value = "agyLocId") final String agyLocId) {
		List<VPrisonTotal> searchResult = new ArrayList<>();
		try {
			searchResult = oiiiscouService.vPrisnTotExecuteQuery(agyLocId);
		} catch (Exception e) {
			VPrisonTotal bean = new VPrisonTotal();
			logger.error("cgfkAgyLocAgyLocIdRecordGroup", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @return a list of the ReferenceCodes {@link ReferenceCodes}
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiiiscou/CgfdgetVPrisnCtDrvImpris", method = RequestMethod.GET)
	public List<ReferenceCodes> CgfdgetVPrisnCtDrvImpris() {

		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiiiscouService.CgfdgetVPrisnCtDrvImpris(null);
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("CgfdgetVPrisnCtDrvImpris", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

}