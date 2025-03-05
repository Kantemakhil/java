package net.syscon.s4.inst.institutionalactivities;

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
import net.syscon.s4.inst.institutionalactivities.beans.VSchdPrisonActivities;
import net.syscon.s4.pkgs.common.CommonService;


@EliteController
public class OidschacController {
	@Autowired
	private OidschacService oidschacService;
	@Autowired
	private CommonService commonService;
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidschacController.class.getName());
	/**
	 *Fetching the record from database table
	 *@Param searchRecord
	*/
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/oidschac/schActExecuteQuery", method=RequestMethod.POST)
	public List<VSchdPrisonActivities> schActExecuteQuery(@RequestBody final VSchdPrisonActivities searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<VSchdPrisonActivities> searchResult = new ArrayList<>();
		try {
			searchResult = oidschacService.schActExecuteQuery(searchBean);
		} catch (Exception e) {
			final VSchdPrisonActivities bean = new VSchdPrisonActivities();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OIDSCHAC");
	}

}