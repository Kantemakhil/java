package net.syscon.s4.inst.casemanagement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CasePlansCommitBean;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OcustfasController {
	@Autowired
	private OcustfasService ocustfasService;
	@Autowired
	private ProsmainService prosmainService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcustfasController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link CasePlans}
	 * @return a list of the CasePlans {@link CasePlans} for the matching passed data
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocustfas/casePlansExecuteQuery", method = RequestMethod.POST)
	public List<CasePlans> casePlansExecuteQuery(@RequestBody final CasePlans searchBean) {
		List<CasePlans> searchResult = new ArrayList<>();
		try {
			searchResult = ocustfasService.casePlansExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("casePlansExecuteQuery", e);
		}
		return searchResult;
	}

	/**
	 * Performing basic Oracle form functions i.e. insert,delete, update int the
	 * database table
	 * 
	 * @param commitBean
	 */
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/ocustfas/casePlansCommit", method = RequestMethod.POST)
	public @ResponseBody Integer casePlansCommit(@RequestBody final CasePlansCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		int liReturn = 0;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = ocustfasService.casePlansCommit(commitBean);
			if(1==liReturn) {
				for(CasePlans casePlans : commitBean.getInsertList()) {
					List<Map<String, Object>> staffList = new ArrayList<Map<String,Object>>();
					for(CasePlanStaff casePlanStaff : casePlans.getList()) {
						ObjectMapper objectMapper = new ObjectMapper();
						Map<String,Object> staffDetails = objectMapper.convertValue(casePlanStaff, Map.class);
						staffList.add(staffDetails);
					}
					JsonArray staffObj = new JsonArray();
					staffObj = new Gson().fromJson(new ObjectMapper().writeValueAsString(staffList), JsonArray.class);
					casePlans.setCasePlanStaff(staffObj.toString());
				}
				for(CasePlans casePlans : commitBean.getUpdateList()) {
					List<Map<String, Object>> staffList = new ArrayList<Map<String,Object>>();
					for(CasePlanStaff casePlanStaff : casePlans.getList()) {
						ObjectMapper objectMapper = new ObjectMapper();
						Map<String,Object> staffDetails = objectMapper.convertValue(casePlanStaff, Map.class);
						staffList.add(staffDetails);
					}
					JsonArray staffObj = new JsonArray();
					staffObj = new Gson().fromJson(new ObjectMapper().writeValueAsString(staffList), JsonArray.class);
					casePlans.setCasePlanStaff(staffObj.toString());
				}
				prosmainService.enableTriggers(commitBean, authorization, "36");
			}
		} catch (Exception e) {
			logger.error("casePlansCommit", e);
		}
		return liReturn;
	}

	/**
	 * getting rgStaffName LOV values
	 */
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ocustfas/rgStaffNameRecordGroup", method = RequestMethod.GET)
	public List<StaffMembers> rgStaffNameRecordGroup(@RequestParam(value = "caseLoadId") final String caseLoadId) {
		List<StaffMembers> recordList = new ArrayList<StaffMembers>();
		try {
			recordList = ocustfasService.rgStaffNameRecordGroup(caseLoadId);
		} catch (Exception e) {
			logger.error("rgStaffNameRecordGroup", e);
		}
		return recordList;
	}

	/**
	 * getting rgStaffName LOV values
	 */
	@RequestMapping(value = "/ocustfas/agencyLocations", method = RequestMethod.GET)
	public String agencyLocations(@RequestParam(value = "agyLocId") final String agyLocId) {
		String recordList = null;
		try {
			recordList = ocustfasService.agencyLocations(agyLocId);
		} catch (Exception e) {
			logger.error("agencyLocations", e);
		}
		return recordList;
	}

}