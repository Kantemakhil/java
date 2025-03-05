package net.syscon.s4.inst.legals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;

@EliteController
public class OcipenscController {

	@Autowired
	private OcipenscService ocipenscService;

	private static Logger logger = LogManager.getLogger(OcipenscController.class.getName());

	// @PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocipensc/getPendingSentenceCalcEvents", method = RequestMethod.POST)
	public List<OdynfrmSubmitDataBean> getPendingSentenceCalcEvents(
			@RequestBody final OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		List<OdynfrmSubmitDataBean> obj = new ArrayList<>();
		try {
			obj = ocipenscService.getPendingSentenceCalcEvents(odynfrmSubmitDataBean);
		} catch (Exception e) {
			logger.error("In getPendingSentenceCalcEvnets:", e);
		}
		return obj;
	}
	
	@RequestMapping(value = "/ocipensc/deletePendingSentenceCalcEvents", method = RequestMethod.POST)
	public Integer deletePendingSentenceCalcEvents(
			@RequestBody final String queryData ) {
		Integer returnValue = 0;
		Map<String, Object> deleteObj = null;
		try {
			deleteObj = new ObjectMapper().configure(
				    JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(),true).readValue(queryData, HashMap.class);
			Integer pendingId = 0;
			if(deleteObj.containsKey("pendingId")) {
				pendingId = Integer.valueOf(deleteObj.get("pendingId").toString());
			}
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			returnValue = ocipenscService.deletePendingSentenceCalcEvents(pendingId,userName);
		} catch (Exception e) {
			logger.error("In getPendingSentenceCalcEvnets:", e);
		}
		return returnValue;
	}
}
