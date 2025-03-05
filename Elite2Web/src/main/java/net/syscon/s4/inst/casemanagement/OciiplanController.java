package net.syscon.s4.inst.casemanagement;

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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.casemanagement.beans.CasePlans;

@EliteController
public class OciiplanController {
	@Autowired
	private OciiplanService ociiplanService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OciiplanController.class.getName());

	/**
	 * getting rgInstAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociiplan/rgInstAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> rgInstAgyLocRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ociiplanService.rgInstAgyLocRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("rgInstAgyLocRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * getting rgVerifiedFilter LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociiplan/rgVerifiedFilterRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgVerifiedFilterRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<>();
		try {
			recordList = ociiplanService.rgVerifiedFilterRecordGroup();
		} catch (Exception e) {
			logger.error("rgVerifiedFilterRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting comInstAgyLoc LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociiplan/comInstAgyLocRecordGroup", method = RequestMethod.GET)
	public List<AgencyLocations> comInstAgyLocRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<AgencyLocations> recordList = new ArrayList<AgencyLocations>();
		try {
			recordList = ociiplanService.comInstAgyLocRecordGroup(caseLoadId);
		} catch (Exception e) {
			AgencyLocations obj = new AgencyLocations();
			logger.error("comInstAgyLocRecordGroup", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CasePlans}
	 * @return a list of the CasePlans {@link CasePlans} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociiplan/casePlansExecuteQuery", method = RequestMethod.POST)
	public List<CasePlans> casePlansExecuteQuery(@RequestBody final CasePlans searchBean) {
		List<CasePlans> searchResult = new ArrayList<>();
		try {
			searchResult = ociiplanService.casePlansExecuteQuery(searchBean);
		} catch (Exception e) {
			CasePlans bean = new CasePlans();
			logger.error("casePlansExecuteQuery", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ociiplan/ociiplanTagMainGetOffender", method = RequestMethod.GET)
	public Boolean ociiplanTagMainGetOffender(@RequestParam(value = "caseLoadId") final String caseLoadId
												,@RequestParam(value = "caseLoadType") final String caseLoadType
												,@RequestParam(value = "offenderIdDisplay") final String offenderIdDisplay) {
		Boolean value = false;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			value = ociiplanService.ociiplanTagMainGetOffender(caseLoadId,caseLoadType,offenderIdDisplay,userName);
		} catch (Exception e) {
			logger.error("comInstAgyLocRecordGroup", e);
		}
		return value;
	}

}