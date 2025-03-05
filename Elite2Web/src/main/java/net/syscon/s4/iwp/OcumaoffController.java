package net.syscon.s4.iwp;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.pkgs.common.CommonService;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRoles;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffLocationRolesCommitBean;

/**
 * @version 1.0
 */
@EliteController
public class OcumaoffController {
	@Autowired
	private OcumaoffService ocumaoffService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcumaoffController.class.getName());

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocumaoff/addOfficerExecuteQuery", method = RequestMethod.POST)
	public List<StaffLocationRoles> addOfficerExecuteQuery(@RequestBody final StaffLocationRoles searchBean) {
		List<StaffLocationRoles> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = ocumaoffService.addOfficerExecuteQuery(searchBean);
		} catch (Exception e) {
			StaffLocationRoles bean = new StaffLocationRoles();
			logger.error("Exception :", e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocumaoff/updateSupervosor", method = RequestMethod.POST)
	public @ResponseBody Integer updateSupervosor(@RequestBody final StaffLocationRolesCommitBean commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			commitBean.setCreateUserId(userName);
			liReturn = ocumaoffService.updateSupervosor(commitBean);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return liReturn;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OCUMAOFF");
	}


}