package net.syscon.s4.inst.incidentsoic;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentParties;
import net.syscon.s4.im.incidentsoic.beans.AgencyIncidentPartiesCommitBean;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReport;
import net.syscon.s4.im.incidentsoic.beans.IncidentStaffReportCommitBean;
import net.syscon.s4.im.incidentsoic.beans.OidstfrpCommonCommitBean;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipment;
import net.syscon.s4.im.incidentsoic.beans.StaffEquipmentCommitBean;
import net.syscon.s4.im.incidentsoic.beans.StaffForce;
import net.syscon.s4.im.incidentsoic.beans.StaffForceCommitBean;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidstfrpController {

	@Autowired
	OidstfrpService oidstfrpSerivce;
	
	@Autowired
	private ProsmainService prosmainService;
	
	private static Logger logger = LogManager.getLogger(OidstfrpController.class);
	
	@RequestMapping(value = "oidistrfp/commitStaffReportData", method = RequestMethod.POST)
	public @ResponseBody Integer staffReportCommitData(@RequestBody final IncidentStaffReportCommitBean commitBean,@RequestHeader HttpHeaders headers) {
		
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		int liReturn = 0;
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = oidstfrpSerivce.staffReportCommitData(commitBean);
			if(1 == liReturn) {
				
				prosmainService.enableTriggers(commitBean, authorization, "81");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "oidstfrp/getStaffReportIncident", method = RequestMethod.POST)
	public @ResponseBody List<IncidentStaffReport> staffReportExecuteQuery(@RequestBody final IncidentStaffReport commitBean) {
		List<IncidentStaffReport> resultIncidentStaffReport=null;
		try {
			resultIncidentStaffReport = oidstfrpSerivce.staffReportExecuteQuery(commitBean);
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return resultIncidentStaffReport;
	}
	
	@RequestMapping(value = "ocuincfe/getStaffforceIncident", method = RequestMethod.POST)
	public List<StaffForce> staffforceExecuteQuery(@RequestBody final StaffForce objSearchDao) {
		List<StaffForce> searchResult = new ArrayList<>();
		try {
			searchResult = oidstfrpSerivce.staffforceExecuteQuery( objSearchDao);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsExecuteQuery", e);
		}
		return searchResult;
	}
	
	@RequestMapping(value = "ocuincfe/getStaffEquipementIncident", method = RequestMethod.POST)
	public List<StaffEquipment> staffEquipementExecuteQuery(@RequestBody final StaffEquipment objSearchDao) {
		List<StaffEquipment> searchResult = new ArrayList<>();
		try {
			searchResult = oidstfrpSerivce.staffEquipementExecuteQuery( objSearchDao);
		} catch (Exception e) {
			logger.error("Exception raised in agencyIncidentRepairsExecuteQuery", e);
		}
		return searchResult;
	}
	
	@RequestMapping(value = "ocuincfe/commitStaffforceData", method = RequestMethod.POST)
	public Integer staffforceCommitData(@RequestBody final StaffForceCommitBean commitBean,@RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		int liReturn = 0;
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = oidstfrpSerivce.staffforceCommitData(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "82");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "ocuincfe/commitStaffEquipementData", method = RequestMethod.POST)
	public Integer staffEquipementCommitData(@RequestBody final StaffEquipmentCommitBean commitBean,@RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		int liReturn = 0;
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = oidstfrpSerivce.staffEquipementCommitData(commitBean);
			if(1 == liReturn) {
				prosmainService.enableTriggers(commitBean, authorization, "83");
			}
		} catch (Exception e) {
			logger.error("Exception raised in agyIncPartiesStaffCommit", e);
		}
		return liReturn;
	}
	
	@RequestMapping(value = "oidistrfp/staffReportCommonSave", method = RequestMethod.POST)
	public Integer staffReportCommonSave(@RequestBody final OidstfrpCommonCommitBean commitBean,@RequestHeader HttpHeaders headers) {
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		int liReturn = 0;
		try {
			final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(user);
			liReturn = oidstfrpSerivce.staffReportCommonSave(commitBean);
			if(1 == liReturn) {
				if(commitBean != null) {
					if(commitBean.getstaffReportCommitList() != null) {
						prosmainService.enableTriggers(commitBean.getstaffReportCommitList(), authorization, "81");
					}
					if(commitBean.getstaffForceCommitList() != null) {
						prosmainService.enableTriggers(commitBean.getstaffForceCommitList() , authorization, "82");
					}
					if(commitBean.getstaffEquipmentCommitList() != null) {
						prosmainService.enableTriggers(commitBean.getstaffEquipmentCommitList(), authorization, "83");
					}
				}
			}
			if(liReturn > 0) {
			if (liReturn == commitBean.getstaffReportCommitList().getUpdateList().get(0).getIncidentReportId()) {
				AgencyIncidentPartiesCommitBean agencyIncidentPartiesCommitBean = new AgencyIncidentPartiesCommitBean();
				AgencyIncidentParties bean = new AgencyIncidentParties();
				List<AgencyIncidentParties> list = new ArrayList<>();
				bean.setStaffId(commitBean.getstaffReportCommitList().getUpdateList().get(0).getStaffId());
				bean.setReportType(commitBean.getstaffReportCommitList().getUpdateList().get(0).getReportType());
				bean.setAgencyIncidentId(
						commitBean.getstaffReportCommitList().getUpdateList().get(0).getAgencyIncidentId());
				list.add(bean);
				agencyIncidentPartiesCommitBean.setUpdateList(list);
				if (commitBean.getstaffReportCommitList().getUpdateList().get(0).getLockFlag().equals("N")) {
					prosmainService.enableTriggers(agencyIncidentPartiesCommitBean, authorization, "17");
				}
			}
			}
		} catch (Exception e) {
			logger.error("Exception raised in staffReportCommonSave", e);
		}
		return liReturn;
	}
	
	@PostMapping("/oidistrfp/getCountDownTime")
	public ReferenceCodes getCountDownTime(@RequestBody AgencyIncidentParties agencyIncidentParties) {
		return oidstfrpSerivce.getCountDown(agencyIncidentParties);
	}
	
	@PostMapping("oidistrfp/updateLockFlag")
	public Integer updateLockflag(@RequestBody IncidentStaffReport incidentStaffReport) {
		int retVl=0;
		try {
			retVl= oidstfrpSerivce.updateSaveFlag(incidentStaffReport);
		}
		catch (Exception e) {
			logger.error("error in"+ this.getClass().getName() +" updateLockFlag", e);
		}
		return retVl;
	}
	
}
