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
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.VOicIncidents;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;

@EliteController
public class OidoicusController {
	@Autowired
	private OidoicusService oidoicusService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidoicusController.class.getName());

	/**
	 * Fetching the record from database table
	 * 
	 * @return List<VOicIncidents>
	 * @Param searchBean
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicus/vOicInciExecuteQuery", method = RequestMethod.POST)
	public List<VOicIncidents> vOicInciSearchVOicIncidents(@RequestBody final VOicIncidents searchBean) {
		final VOicIncidents bean = new VOicIncidents();
		List<VOicIncidents> searchResult = new ArrayList<>();
		try {
			searchResult = oidoicusService.vOicInciSearchVOicIncidents(searchBean);
		} catch (Exception e) {
			logger.error("vOicInciSearchVOicIncidents:",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	/**
	 * @return List<ReferenceCodes> getting rgIncType LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicus/rgIncTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> rgIncTypeRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		final VOicIncidents obj = new VOicIncidents();
		try {
			recordList = oidoicusService.rgIncTypeRecordGroup();
		} catch (Exception e) {
			logger.error("rgIncTypeRecordGroup:",e);
			obj.setErrorMessage(e.getMessage());
		}
		return recordList;
	}

	/**
	 * method for query callings
	 * 
	 * @return List<Object>
	 * @Param offenderbookid
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicus/offBkgOnCheckDeleteMastervOicInciCur", method = RequestMethod.POST)
	public @ResponseBody List<Object> offBkgOnCheckDeleteMastervOicInciCur(
			@RequestParam(value = "offenderbookid") final String offenderbookid) {
		List<Object> dataObj = null;
		try {
			dataObj = oidoicusService.offBkgOnCheckDeleteMastervOicInciCur(offenderbookid);
		} catch (Exception e) {
			logger.error("offBkgOnCheckDeleteMastervOicInciCur:",e);

		}
		return dataObj;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidoicus/findLocationList", method = RequestMethod.GET)
	public @ResponseBody List<String> findLocationList() {
		List<String> listOfRecords = null;
		try {
			listOfRecords = oidoicusService.findLocationList();
		} catch (Exception e) {
			logger.error("findLocationList: ", e);
		}
		return listOfRecords;
	}
	
	@RequestMapping(value = "oidstfrppop/getStaffReportsHistory", method = RequestMethod.POST)
		public @ResponseBody List<IncidentStaffReport>  staffReportsData(@RequestBody final VOicIncidents searchBean) {
			List<IncidentStaffReport> listOfRecords = null;
			try {
				listOfRecords = oidoicusService.staffReportsData(searchBean);
			} catch (Exception e) {
				logger.error("findLocationList: ", e);
			}
			return listOfRecords;
		}

}