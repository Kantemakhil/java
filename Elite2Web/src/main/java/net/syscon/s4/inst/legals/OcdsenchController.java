package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;

@EliteController
public class OcdsenchController {
	
	@Autowired
	private OcdsenchService ocdsenchService;
	
	private static Logger logger = LogManager.getLogger(OcdsenchController.class.getName());
	
	//@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocdsench/getSentenceHisData", method = RequestMethod.POST)
	public List<OdynfrmSubmitDataBean> getSentenceHistoryData(@RequestBody final OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		List<OdynfrmSubmitDataBean> obj = new ArrayList();
		try {
			obj = ocdsenchService.getSentenceHistoryData(odynfrmSubmitDataBean); 
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return obj;
	}

}
