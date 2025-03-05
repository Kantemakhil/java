package net.syscon.s4.inst.incidentsoic;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.im.incidentsoic.beans.VOffenderOicSanctions;
import net.syscon.s4.im.incidentsoic.beans.VOicHearingResults;
import net.syscon.s4.im.incidentsoic.beans.VOicHearings;
import net.syscon.s4.im.incidentsoic.beans.VOicIncidents;

/**
 * class OiioicusController
 */
@EliteController
public class OiioicusController {

	@Autowired
	private OiioicusService oiioicusService;
	
	@Autowired
	private OidoicusService oidoicusService;
	
	@Autowired
	private OcuoichnService ocuoichnService;
	
	@Autowired
	private OcuoicheService ocuoicheService;
	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiioicusController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<VOicIncidents>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOicInciExecuteQuery", method = RequestMethod.POST)
	public List<VOicIncidents> vOicInciSearchVOicIncidents(@RequestBody final VOicIncidents searchBean) {
		List<VOicIncidents> searchResult = new ArrayList<>();
		try {
			searchResult = oiioicusService.vOicInciSearchVOicIncidents(searchBean);
		} catch (Exception e) {
			logger.error("vOicInciSearchVOicIncidents:", e);
		}
		return searchResult;
	}

	/**
	 * getting rgOicHearingType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/rgOicHearingTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOicHearingTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiioicusService.rgOicHearingTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgOicHearingTypeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting rgIncidentType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/rgIncidentTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIncidentTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiioicusService.rgIncidentTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgIncidentTypeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting rgOffenceType LOV values
	 * 
	 * @return List<ReferenceCodes>
	 * @param oicOffenceObj
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/rgOffenceTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgOffenceTypeRecordGroup(@RequestParam(value = "date") final String date) {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiioicusService.rgOffenceTypeRecordGroup(date);
		} catch (Exception e) {
			logger.error("rgOffenceTypeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * getting rgSanctionCode LOV values
	 * 
	 * @return List<ReferenceCodes>
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/rgSanctionCodeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgSanctionCodeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		try {
			recordList = oiioicusService.rgSanctionCodeRecordGroup();
		} catch (Exception e) {
			logger.error("rgSanctionCodeRecordGroup:", e);
		}
		return recordList;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<VOicHearings>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOicHearSearchVOicHearings", method = RequestMethod.POST)
	public List<VOicHearings> vOicHearSearchVOicHearings(@RequestBody final VOicHearings searchBean) {
		List<VOicHearings> searchResult = new ArrayList<>();
		try {
			searchResult = oiioicusService.vOicHearSearchVOicHearings(searchBean);
		} catch (Exception e) {
			logger.error("vOicHearSearchVOicHearings:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<VOicHearingResults>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOicHearResSearchVOicHearingResults", method = RequestMethod.POST)
	public List<VOicHearingResults> vOicHearResSearchVOicHearingResults(
			@RequestBody final VOicHearingResults searchBean) {
		List<VOicHearingResults> searchResult = new ArrayList<>();
		try {
			searchResult = oiioicusService.vOicHearResSearchVOicHearingResults(searchBean);
		} catch (Exception e) {
			logger.error("vOicHearResSearchVOicHearingResults:", e);
		}
		return searchResult;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<VOffenderOicSanctions>
	 * @Param searchRecord
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOffOicSanctSearchVOffenderOicSanctions", method = RequestMethod.POST)
	public List<VOffenderOicSanctions> vOffOicSanctSearchVOffenderOicSanctions(
			@RequestBody final VOffenderOicSanctions searchBean) {
		List<VOffenderOicSanctions> searchResult = new ArrayList<>();
		try {
			searchResult = oiioicusService.vOffOicSanctSearchVOffenderOicSanctions(searchBean);
		} catch (Exception e) {
			logger.error("vOffOicSanctSearchVOffenderOicSanctions:", e);
		}
		return searchResult;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<String>
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/offBkgOnCheckDeleteMastervOicInciCur", method = RequestMethod.POST)
	public @ResponseBody List<String> offBkgOnCheckDeleteMastervOicInciCur(@RequestBody final VOicIncidents paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {
			dataObj = oiioicusService.offBkgOnCheckDeleteMastervOicInciCur(paramBean);
		} catch (Exception e) {
			logger.error("offBkgOnCheckDeleteMastervOicInciCur", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<String>
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOicInciOnCheckDeleteMastervOicHearCur", method = RequestMethod.POST)
	public @ResponseBody List<String> vOicInciOnCheckDeleteMastervOicHearCur(
			@RequestBody final VOicHearings paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {
			dataObj = oiioicusService.vOicInciOnCheckDeleteMastervOicHearCur(paramBean);
		} catch (Exception e) {
			logger.error("vOicInciOnCheckDeleteMastervOicHearCur:", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<String>
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOicHearOnCheckDeleteMastervOicHearResCur", method = RequestMethod.POST)
	public @ResponseBody List<String> vOicHearOnCheckDeleteMastervOicHearResCur(
			@RequestBody final VOicHearingResults paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {
			dataObj = oiioicusService.vOicHearOnCheckDeleteMastervOicHearResCur(paramBean);
		} catch (Exception e) {
			logger.error("vOicHearOnCheckDeleteMastervOicHearResCur:", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<String>
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/vOicHearResOnCheckDeleteMastervOffOicSanctCur", method = RequestMethod.POST)
	public @ResponseBody List<String> vOicHearResOnCheckDeleteMastervOffOicSanctCur(
			@RequestBody final VOffenderOicSanctions paramBean) {
		List<String> dataObj = new ArrayList<String>();
		try {
			dataObj = oiioicusService.vOicHearResOnCheckDeleteMastervOffOicSanctCur(paramBean);
		} catch (Exception e) {
			logger.error("vOicHearResOnCheckDeleteMastervOffOicSanctCur:", e);
		}
		return dataObj;
	}

	/**
	 * method for query callings
	 * 
	 * @return SystemProfiles
	 * @param paramBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/getProfileValuevsProfvalCur", method = RequestMethod.GET)
	public @ResponseBody SystemProfiles getProfileValuevsProfvalCur(@RequestBody final SystemProfiles paramBean) {
		SystemProfiles dataObj = new SystemProfiles();
		try {
			dataObj = oiioicusService.getProfileValuevsProfvalCur(paramBean);
		} catch (Exception e) {
			logger.error("getProfileValuevsProfvalCur:", e);
		}
		return dataObj;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/getHearingStaffNameList", method = RequestMethod.GET)
	public @ResponseBody List<String> getHearingStaffNameList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiioicusService.getHearingStaffNameList();
		} catch (Exception e) {
			logger.error("getHearingStaffNameList: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/getHearingResultsOicOffenceDes", method = RequestMethod.GET)
	public @ResponseBody List<String> getHearingResultsOicOffenceDes() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiioicusService.getHearingResultsOicOffenceDes();
		} catch (Exception e) {
			logger.error("getHearingResultsType: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/getHearingResultsType", method = RequestMethod.GET)
	public @ResponseBody List<String> getHearingResultsType() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiioicusService.getHearingResultsType();
		} catch (Exception e) {
			logger.error("getHearingResultsType: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/getDiscOicSanctionDes", method = RequestMethod.GET)
	public @ResponseBody List<String> getDiscOicSanctionDes() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiioicusService.getDiscOicSanctionDes();
		} catch (Exception e) {
			logger.error("getDiscOicSanctionDes: ", e);
		}
		return listOfRecords;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/getDiscStatusDes", method = RequestMethod.GET)
	public @ResponseBody List<String> getDiscStatusDes() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oiioicusService.getDiscStatusDes();
		} catch (Exception e) {
			logger.error("getDiscStatusDes: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oiioicus/findLocationList", method = RequestMethod.GET)
	public @ResponseBody List<String> findLocationList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oidoicusService.findLocationList();
		} catch (Exception e) {
			logger.error("findLocationList: ", e);
		}
		return listOfRecords;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiioicus/rgInternalLocationsRecordGroup", method = RequestMethod.GET)
	public List<AgencyInternalLocations> rgInternalLocationsRecordGroup(
			@RequestParam(value = "caseloadId") final String caseloadId) {
		List<AgencyInternalLocations> recordList = new ArrayList<AgencyInternalLocations>();
		final AgencyInternalLocations obj = new AgencyInternalLocations();
		try {
			recordList = ocuoichnService.rgInternalLocationsRecordGroup(caseloadId);
		} catch (Exception e) {
			logger.error("rgInternalLocationsRecordGroup:", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiioicus/rgFindingRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgFindingRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicheService.rgFindingRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiioicus/rgPleaRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgPleaRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final ReferenceCodes obj = new ReferenceCodes();
		try {
			recordList = ocuoicheService.rgPleaRecordGroup();
		} catch (Exception e) {
			logger.error(e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
}
