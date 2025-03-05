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
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatusesCommitBean;

@EliteController
public class OimcustsController {
	@Autowired
	private OimcustsService oimcustasService;
	
	private static Logger logger = LogManager.getLogger(OimcustsController.class.getName());

	
	@PostMapping("oimcustas/saveCustodyData")
	public Integer saveCustodyData(@RequestBody LegalCustodyStatusesCommitBean commitBean) {
		Integer returnValue=null;
		try {
			if (commitBean != null) {
				commitBean.setCreateUserId(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
			}
			returnValue=oimcustasService.saveCustodyData(commitBean);
		}catch(Exception e) {
			getLogMessage("saveCustodyData",e);
		}
		return returnValue;
	}
	
	
	
	@GetMapping("oimcustas/getCustodyData")
	public List<LegalCustodyStatuses> getLegalsData(){
		List<LegalCustodyStatuses> custodyData=Collections.checkedList(new ArrayList<LegalCustodyStatuses>(), LegalCustodyStatuses.class);
		try {
			custodyData= oimcustasService.getLegalsData();
		}catch (Exception e) {
			getLogMessage("getLegalsData",e);
		}
		return custodyData;
	}
	
	private void getLogMessage(String methodName,Exception e) {
		logger.error("Method in " + this.getClass().getName() +" "+ methodName , e);
	}

}
