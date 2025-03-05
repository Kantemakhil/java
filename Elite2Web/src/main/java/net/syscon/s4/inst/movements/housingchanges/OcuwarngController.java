package net.syscon.s4.inst.movements.housingchanges;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OcuwarngController
 */
@EliteController
public class OcuwarngController {
	@Autowired
	private OcuwarngService ocuwarngService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuwarngController.class.getName());

	/**
	 * getting rgAssignmentReason LOV values
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuwarng/allowOverrideQuery", method = RequestMethod.GET)
	public List<String> allowOverrideQuery() {
		List<String> returnList = new ArrayList<String>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			returnList = ocuwarngService.allowOverride(userName);
		} catch (Exception e) {
			logger.error("In method allowOverrideQuery : ", e);
		}
		return returnList;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUWARNG");
	}

}