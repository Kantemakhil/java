package net.syscon.s4.inst.offenderissuestracking.maintenance;

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
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.cm.teamsworkflow.beans.Work;
import net.syscon.s4.cm.teamsworkflow.beans.WorkCommitBean;
import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.globalrbac.OumrolesService;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.im.beans.GrievanceTypesCommitBean;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.inst.workflow.maintenance.OcmcnperController;

@EliteController
public class OimiitpsController {
	
	@Autowired
	private OimiitpsService oimiitpsService;
	@Autowired
	private OumrolesService oumrolesService;
	
	private static Logger logger = LogManager.getLogger(OcmcnperController.class.getName());

	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiitps/grievencePermissionExecuteQuery", method = RequestMethod.POST)
	public List<GrievanceTypes> grievencePermissionExecuteQuery(@RequestBody GrievanceTypes searchBean) {
		List<GrievanceTypes> searchResult = new ArrayList<>();
		try {
			searchResult = oimiitpsService.grievencePermissionExecuteQuery(searchBean);
		} catch (Exception e) {
			GrievanceTypes bean = new GrievanceTypes();
			logger.error("Exception in grievencePermissionExecuteQuery :", e);
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiitps/grievenceTypeRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> grievenceTypeRecordGroup() {
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = oimiitpsService.grievenceTypeRecordGroup();
		} catch (Exception e) {
			logger.error("Exception in grievenceTypeRecordGroup:", e);
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiitps/grievenceReasonRecordGroup", method = RequestMethod.GET)
	public List<ReferenceCodes> grievenceReasonRecordGroup(String grievType) {
		List<ReferenceCodes> returnList = new ArrayList<>();
		try {
			returnList = oimiitpsService.grievenceReasonRecordGroup(grievType);
		} catch (Exception e) {
			logger.error("Exception in grievenceReasonRecordGroup:", e);
		}
		return returnList;
	}
	
	@RequestMapping(value = "/oimiitps/grievencePermissionCommit", method = RequestMethod.POST)
	public @ResponseBody Integer grievencePermissionCommit(@RequestBody GrievanceTypesCommitBean commitBean) {
		int liReturn = 0;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			liReturn = oimiitpsService.grievencePermissionCommit(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " grievencePermissionCommit", e);
		}
		return liReturn;
	}
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimiitps/omsRoleExecuteQuery", method = RequestMethod.POST)
	public List<OmsRoles> omsRoleExecuteQuery(@RequestBody OmsRoles searchBean) {
		List<OmsRoles> searchResult = new ArrayList<>();
		try {
			searchResult = oumrolesService.omsRoleExecuteQuery(searchBean);
		} catch (Exception e) {
			OmsRoles bean = new OmsRoles();
			logger.error("In this method omsRoleExecuteQuery :" + e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
}
