package net.syscon.s4.globalconfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import net.syscon.s4.common.beans.ModuleInsDashboardBean;
import net.syscon.s4.common.beans.ModuleInsDashboardCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;

@EliteController
public class OuminsdbController {
	
	@Autowired
	private OuminsdbService ouminsdbService;
	
	private static Logger logger = LogManager.getLogger(OuminsdbController.class.getName());
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ouminsdb/getInsModDashboard", method = RequestMethod.GET)
	public List<ModuleInsDashboardBean> getInsModDashboard() {
		List<ModuleInsDashboardBean> objList = new ArrayList<ModuleInsDashboardBean>();
		try {
			objList = ouminsdbService.getInsModDashboard(); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getInsModDashboard : {}", e.getMessage());
		}
		return objList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ouminsdb/insightModDashboard", method = RequestMethod.GET)
	public List<ModuleInsDashboardBean> getInsightModDashboardSearchable(@RequestParam final String KEY_CODE) {
		List<ModuleInsDashboardBean> objList = new ArrayList<ModuleInsDashboardBean>();
		try {
			objList = ouminsdbService.getInsModDashboard(); 
//			objList = objList.stream().filter(o->o.getOffenderSpecificFlag().equalsIgnoreCase(isOffenderSpecific)).collect(Collectors.toList());
			
			
			ModuleInsDashboardBean offdashboard = new ModuleInsDashboardBean();
			offdashboard.setCode("APP_DEFAULT")	;
			offdashboard.setOffenderSpecificFlag("Y");
			offdashboard.setModule("APP_DEFAULT");
			offdashboard.setActiveFlag("Y");
			objList.add(offdashboard);
			if(KEY_CODE.equals("INSTLP")) {
				ModuleInsDashboardBean offdashboard1 = new ModuleInsDashboardBean();
				offdashboard1.setCode("OSIOSEAR")	;
				offdashboard1.setModule("OSIOSEAR");
				objList.add(offdashboard1);

				ModuleInsDashboardBean offdashboard2 = new ModuleInsDashboardBean();
				offdashboard2.setCode("OIINAMES")	;
				offdashboard2.setModule("OIINAMES");
				objList.add(offdashboard2);
				
				ModuleInsDashboardBean offdashboard3 = new ModuleInsDashboardBean();
				offdashboard3.setCode("OIISCHED")	;
				offdashboard3.setModule("OIISCHED");
				objList.add(offdashboard3);
				
				ModuleInsDashboardBean offdashboard4 = new ModuleInsDashboardBean();
				offdashboard4.setCode("OIIMYOFF")	;
				offdashboard4.setModule("OIIMYOFF");
				objList.add(offdashboard4);
				
				ModuleInsDashboardBean offdashboard5 = new ModuleInsDashboardBean();
				offdashboard5.setCode("OIIPROLL")	;
				offdashboard5.setModule("OIIPROLL");
				objList.add(offdashboard5);
				
			}else if(KEY_CODE.equals("COMMLP")) {
				ModuleInsDashboardBean offdashboard1 = new ModuleInsDashboardBean();
				offdashboard1.setCode("OSIOSEAR")	;
				offdashboard1.setModule("OSIOSEAR");
				objList.add(offdashboard1);
				
				ModuleInsDashboardBean offdashboard2 = new ModuleInsDashboardBean();
				offdashboard2.setCode("OCINAMES")	;
				offdashboard2.setModule("OCINAMES");
				objList.add(offdashboard2);
				
//				ModuleInsDashboardBean offdashboard3 = new ModuleInsDashboardBean();
//				offdashboard3.setCode("OIISCHED")	;
//				offdashboard3.setModule("OIISCHED");
//				objList.add(offdashboard3);
				
				ModuleInsDashboardBean offdashboard4 = new ModuleInsDashboardBean();
				offdashboard4.setCode("OCIMYOFF")	;
				offdashboard4.setModule("OCIMYOFF");
				objList.add(offdashboard4);
				
				ModuleInsDashboardBean offdashboard5 = new ModuleInsDashboardBean();
				offdashboard5.setCode("OCDORASS")	;
				offdashboard5.setModule("OCDORASS");
				objList.add(offdashboard5);
			}else if(KEY_CODE.equals("OFDSHB")) {
				ModuleInsDashboardBean offdashboard1 = new ModuleInsDashboardBean();
				offdashboard1.setCode("OWHEADER")	;
				offdashboard1.setModule("OWHEADER");
				objList.add(offdashboard1);
				
				ModuleInsDashboardBean offdashboard2 = new ModuleInsDashboardBean();
				offdashboard2.setCode("OSIHRSUM")	;
				offdashboard2.setModule("OSIHRSUM");
				objList.add(offdashboard2);
				
				ModuleInsDashboardBean offdashboard3 = new ModuleInsDashboardBean();
				offdashboard3.setCode("OIIBOOKS")	;
				offdashboard3.setModule("OIIBOOKS");
				objList.add(offdashboard3);
				
				ModuleInsDashboardBean offdashboard4 = new ModuleInsDashboardBean();
				offdashboard4.setCode("OIIOSCED")	;
				offdashboard4.setModule("OIIOSCED");
				objList.add(offdashboard4);
				
				ModuleInsDashboardBean offdashboard5 = new ModuleInsDashboardBean();
				offdashboard5.setCode("OIDCNOTE")	;
				offdashboard5.setModule("OIDCNOTE");
				objList.add(offdashboard5);
				
				ModuleInsDashboardBean offdashboard6 = new ModuleInsDashboardBean();
				offdashboard6.setCode("OCDIPLAN")	;
				offdashboard6.setModule("OCDIPLAN");
				objList.add(offdashboard6);
			}
			
			
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getInsModDashboard : {}", e.getMessage());
		}
		return objList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ouminsdb/InsModDashboardCommit", method = RequestMethod.POST)
	public Integer InsModDashboardCommit(@RequestBody final ModuleInsDashboardCommitBean commitBean) {
		Integer InsModDashboardData = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(userName);
		try {
			InsModDashboardData = ouminsdbService.InsModDashboardCommit(commitBean); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In InsModDashboardCommit : {}", e.getMessage());
		}
		return InsModDashboardData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ouminsdb/getInsDashboardId", method = RequestMethod.POST)
	public ModuleInsDashboardBean getInsDashboardId(@RequestBody final String moduleName) {
		ModuleInsDashboardBean insDashboardId = new ModuleInsDashboardBean();
		try {
			insDashboardId = ouminsdbService.getInsDashboardId(moduleName); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getInsDashboardId : {}", e.getMessage());
		}
		return insDashboardId;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/ouminsdb/getInsDashboardList", method = RequestMethod.GET)
	public List<ReferenceCodes> getInsDashboardList() {
		List<ReferenceCodes> insDashboardList = new ArrayList<ReferenceCodes>();
		try {
			insDashboardList = ouminsdbService.getInsDashboardList(); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getInsDashboardList : {}", e.getMessage());
		}
		return insDashboardList;
	}

}
