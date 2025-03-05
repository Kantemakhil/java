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
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.im.beans.LegalUpdateUsagesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasonsCommitBean;

@EliteController
public class OcmstatsController {
	@Autowired
	private OcmstatsService ocmstatsService;
	private static Logger logger = LogManager.getLogger(OcmstatsController.class.getName());

	
	@PostMapping("/ocmstats/offreasondata")
	public Integer saveOrdersData(@RequestBody LegalUpdateReasonsCommitBean commitBean) {
		Integer returnValue=null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnValue=ocmstatsService.saveOrdersData(commitBean);
		}catch(Exception e) {
			getLogMessage("saveOrdersData",e);
		}
		return returnValue;
	}
	
	
	@PostMapping("/ocmstats/offstatuesdata")
	public Integer saveStautesData(@RequestBody LegalUpdateUsagesCommitBean commitBean) {
		Integer returnValue=null;
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setCreateUserId(userName);
			}
			returnValue=ocmstatsService.saveStautesData(commitBean);
		}catch(Exception e) {
			getLogMessage("saveStautesData",e);
		}
		return returnValue;
	}
	@GetMapping("/ocmstats/getOrdersData")
	public List<LegalUpdateReasons> getOrdersData() {
		List<LegalUpdateReasons> ordersData=Collections.checkedList(new ArrayList<LegalUpdateReasons>(), LegalUpdateReasons.class);
		try {
			ordersData= ocmstatsService.getOrdersData();
		}catch (Exception e) {
			getLogMessage("getOrdersData",e);
		}
		return ordersData;
	}
	
	@GetMapping("/ocmstats/getStatuesData")
	public List<LegalUpdateUsages> getStatuesData(@RequestParam String updateReasonCode) {
		List<LegalUpdateUsages> statusData=Collections.checkedList(new ArrayList<LegalUpdateUsages>(), LegalUpdateUsages.class);
		try {
			statusData= ocmstatsService.getStatuesData(updateReasonCode);
		}catch (Exception e) {
			getLogMessage("getStatuesData",e);
		}
		return statusData;
	}
	private void getLogMessage(String methodName,Exception e) {
		logger.error("Method in " + this.getClass().getName() +" "+ methodName , e);
	}

}
