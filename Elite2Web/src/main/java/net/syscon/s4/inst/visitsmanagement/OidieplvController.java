package net.syscon.s4.inst.visitsmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
import net.syscon.s4.inst.visitsmanagement.maintenance.OimvlimtController;
import net.syscon.s4.sa.recordmaintenance.ProsmainService;

@EliteController
public class OidieplvController {
	@Autowired
	private OidieplvService oidieplvService;
	
	@Autowired
	private ProsmainService prosmainService;
	
	private static Logger logger = LogManager.getLogger(OimvlimtController.class.getName());

	
	@GetMapping("/oidieplv/getIEPLOvs")
	public List<IepLevelBean> getIEPLov(){
		List<IepLevelBean> lovList=new ArrayList<>();
		try {
			lovList= oidieplvService.getIEPLov();
		}catch (Exception e) {
		   logger.error("error in"+this.getClass().getName(), e);
		}
		return lovList;
	}
	
	//@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidieplv/getAllData", method = RequestMethod.GET)
	public List<IepLevelBean> getAllData(@RequestParam Integer offenderBookId){
		List<IepLevelBean> allDataList=new ArrayList<>();
        try {
        	allDataList=oidieplvService.getAllData(offenderBookId);
        }catch (Exception e) {
 		   logger.error("error in"+this.getClass().getName(), e);
		}
		return allDataList;

	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oidieplv/lvLoginUserStaffId", method = RequestMethod.GET)
	public StaffMembers rgOutcomeRecordGroup() {
		StaffMembers staffName = new StaffMembers();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			staffName = oidieplvService.lvLoginUserStaffName(userName);
		} catch (Exception e) {
			logger.error("lvLoginUserStaffId :", e);
		}
		return staffName;
	}
	
	
	@RequestMapping(value = "/oidieplv/saveData", method = RequestMethod.POST)
	public Integer saveAllData( @RequestBody  IEPLevelCommitBean commitBean, @RequestHeader HttpHeaders headers) {
		Integer retVal=null;
		List<String> authorizationList = headers.get("Authorization");
		String authorization = authorizationList.get(0).split(",")[0];
		try {
			if (commitBean != null ) {
				commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
				retVal = oidieplvService.saveAllData(commitBean);
			}
			if (1 == retVal) {
				prosmainService.enableTriggers(commitBean, authorization, "115");
			}
		}catch (Exception e) {
			logger.error("updateComment",e);
		}
		return retVal;
	}
	
	
	@RequestMapping(value = "/oidieplv/getReviewDaysForIepLevelCode", method = RequestMethod.GET)
	public List<IepLevelBean> getReviewDaysForIepLevelCode() {
		List<IepLevelBean> list = new ArrayList<>();
		try {
			list = oidieplvService.getReviewDaysForIepLevelCode();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getReviewDaysForIepLevelCode  : ", e);
		}
		return list;
	}



}
