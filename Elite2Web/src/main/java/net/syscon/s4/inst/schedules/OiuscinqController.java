package net.syscon.s4.inst.schedules;

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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.schedules.bean.VOffenderAllSchedules;
import net.syscon.s4.pkgs.common.CommonService;

/**
 * Class OiuscinqController
 */
@EliteController
public class OiuscinqController {
	@Autowired
	private OiuscinqService oiuscinqService;
	
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiuscinqController.class.getName());

	/**
	 * Fetching the record from database table
	 *
	 * @param searchBean {@link VOffenderAllSchedules}
	 * @return a list of the VOffenderAllSchedules {@link VOffenderAllSchedules} for the matched VOffenderAllSchedules
	 */
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oiuscinq/offSchExecuteQuery", method = RequestMethod.POST)
	public List<VOffenderAllSchedules> offSchExecuteQuery(@RequestBody final VOffenderAllSchedules searchBean) {
		List<VOffenderAllSchedules> searchResult = new ArrayList<>();
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if(!checkAccess("read",userName)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN); 
		}
		try {
			searchResult = oiuscinqService.offSchExecuteQuery(searchBean);
		} catch (Exception e) {
			final VOffenderAllSchedules bean = new VOffenderAllSchedules();
			logger.error("In method offSchExecuteQuery",e);
			searchResult.add(bean);
		}
		return searchResult;
	}

	private Boolean checkAccess(String role, String userId) {
		return commonService.checkCallFormAccess(userId, role, "OIUSCINQ");
	}
}