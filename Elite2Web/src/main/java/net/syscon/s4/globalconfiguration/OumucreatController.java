package net.syscon.s4.globalconfiguration;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.UserCreation;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OumucreatController {

	@Autowired
	private OumucreatService oumucreatService;
	@Autowired
	private CommonService commonService;
	private static Logger logger = LogManager.getLogger(OumucreatController.class.getName());
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumucreat/createUser", method = RequestMethod.POST)
	public Integer createUser(@RequestBody UserCreation userDeatils) {
		Integer result = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			userDeatils.setCreateUserId(userName);
			result=oumucreatService.createUser(userDeatils);
		} catch (Exception e) {
			logger.error("createUser", e);
			result = 0;
		}
		return result;
	}

	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumucreat/getUserDetails", method = RequestMethod.GET)
	public Integer getUserDetails(@RequestParam String userName) {
		Integer result = null;
		try {
			result=oumucreatService.getUserDetails(userName);
		} catch (Exception e) {
			logger.error("createUser", e);
			result = 0;
		}
		return result;
	}
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumucreat/verifyEmailId", method = RequestMethod.GET)
	public Integer verifyEmailId(@RequestParam String emailId) {
		Integer result = null;
		try {
			result=oumucreatService.verifyEmailId(emailId);
		} catch (Exception e) {
			logger.error("createUser", e);
			result = 0;
		}
		return result;
	}
	
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumucreat/migrateADUser", method = RequestMethod.GET)
	public Integer migrateADUSer() {
		Integer result = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			oumucreatService.migrateADUser(userName);
			result =1;
		} catch (Exception e) {
			logger.error("migrateADUSer error ", e);
			result = 0;
		}
		return result;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumucreat/rgInsightsUserGroups", method = RequestMethod.GET)
	public List<ReferenceCodes> rgQueryKeyRecordGroup() {
		List<ReferenceCodes> recordList = new ArrayList<ReferenceCodes>();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			recordList = oumucreatService.getInsightsUserGroups();
		} catch (Exception e) {
			ReferenceCodes obj = new ReferenceCodes();
			logger.error("Exception :", e);
			obj.setErrorMessage(e.getMessage());
			recordList.add(obj);
		}
		return recordList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumucreat/resetADUser", method = RequestMethod.GET)
	public Integer resetADUSer() {
		Integer result = null;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			oumucreatService.resetADUser(userName);
			result =1;
		} catch (Exception e) {
			logger.error("resetADUSer error ", e);
			result = 0;
		}
		return result;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OUMUCREAT");
	}
}
