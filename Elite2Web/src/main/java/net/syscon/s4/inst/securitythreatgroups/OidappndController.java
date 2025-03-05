package net.syscon.s4.inst.securitythreatgroups;

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
import net.syscon.s4.im.beans.StgCaseNotes;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OidappndController {
	@Autowired
	private OidappndService oidappndService;
	@Autowired
	private CommonService commonService;
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidappndController.class.getName());

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oidappnd/stgCommit", method = RequestMethod.POST)
	public Integer stgCommit(@RequestBody final StgCaseNotes commitBean) {
		int liReturn = 0;
		if (!checkCallFormAccess("full")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			if (commitBean != null) {
				String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
				commitBean.setModifyUserId(userName);
			}
			liReturn = oidappndService.stgCommit(commitBean);
		} catch (Exception e) {
			logger.error("Exception : oidappnd : StgCommit :", e);
		}
		return liReturn;
	}
	
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIDAPPND");
	}
}