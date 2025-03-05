package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.intake.beans.MovementReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettings;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalSettingsCommitBean;

@EliteController
public class OcmlesetController {
	@Autowired
	private OcmlesetService ocmlesetService;
	
	private static Logger logger = LogManager.getLogger(OcmlesetController.class.getName());

	
	@PostMapping("/ocmleset/updateLegalsData")
	public Integer updateLegalsData(@RequestBody LegalSettingsCommitBean commitBean) {
		Integer returnValue=null;
		try {
			if (commitBean != null) {
				commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
			returnValue=ocmlesetService.updateLegalsData(commitBean);
		}catch(Exception e) {
			getLogMessage("updateLegalsData",e);
		}
		return returnValue;
	}
	
	@GetMapping("/ocmleset/getLegalsData")
	public List<LegalSettings> getLegalsData(){
		List<LegalSettings> statusData=Collections.checkedList(new ArrayList<LegalSettings>(), LegalSettings.class);
		try {
			statusData= ocmlesetService.getLegalsData();
		}catch (Exception e) {
			getLogMessage("getLegalsData",e);
		}
		return statusData;
	}
	
	@GetMapping("ocmleset/fetchMovementTypes")
	public List<MovementReasons> fetchMovementTypes() {
		List<MovementReasons> movementData=Collections.checkedList(new ArrayList<MovementReasons>(), MovementReasons.class);
		try {
			movementData= ocmlesetService.fetchMovementTypes();
		}catch (Exception e) {
			getLogMessage("fetchMovementTypes",e);
		}
		return movementData;
	}
	
	@GetMapping("ocmleset/fetchRoles")
	public List<ReferenceCodes> fetchRoles(){
		List<ReferenceCodes> lovValues=Collections.checkedList(new ArrayList<ReferenceCodes>(), ReferenceCodes.class);
		try {
			lovValues= ocmlesetService.fetchRoles();
		}catch (Exception e) {
			getLogMessage("fetchRoles",e);
		}
		return lovValues;
	}
	
	private void getLogMessage(String methodName,Exception e) {
		logger.error("Method in " + this.getClass().getName() +" "+ methodName , e);
	}

}
