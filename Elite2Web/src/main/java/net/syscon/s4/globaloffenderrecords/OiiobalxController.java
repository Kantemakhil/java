package net.syscon.s4.globaloffenderrecords;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inmate.beans.OffExternalAccountBalances;

@EliteController
public class OiiobalxController {
	private static Logger logger = LogManager.getLogger(OiiobalxController.class.getName());

	@Autowired
	private OiiobalxService oiiobalxService;
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/Oiiobalx/getOffExternalAccountBalances", method = RequestMethod.POST)
	public List<OffExternalAccountBalances> getOffExternalAccountBalances(@RequestBody final OffExternalAccountBalances searchBean) {
		List<OffExternalAccountBalances> searchResult = new ArrayList<OffExternalAccountBalances>();
		try {
			searchResult = oiiobalxService.getOffExternalAccountBalances(searchBean);
			if(searchResult == null) {
				searchResult=new ArrayList<OffExternalAccountBalances>();
			}
		} catch (Exception e) {
			logger.error("getOffExternalAccountBalances", e);
		}
		return searchResult;
	}
	
	
	@RequestMapping(value = "/Oiiobalx/MaintainOffenderExternalBalance", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> MaintainOffenderExternalBalance(@RequestBody String data) {
		String response=null;
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("InsertOffenderExternalBalance"+data);
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		try {
			response=oiiobalxService.saveOffExternalAccountBalances(data,userName);
			result.put("message", response);
		} catch (Exception e) {
			logger.error("Oiiobalx::  error in InsertOffenderExternalBalance " + e.getMessage());
			result.put("message", "failed");
		}

		return result;

	}
	
	@RequestMapping(value = "/Oiiobalx/getLastUpdatedDate", method = RequestMethod.GET)
	public @ResponseBody Date getLastUpdatedDate() {
		Date response=null;
		try {
			response=oiiobalxService.getLastUpdatedDate();
		} catch (Exception e) {
			logger.error("Oiiobalx::  error in getLastUpdatedDate " + e.getMessage());
		}

		return response;

	}
}
