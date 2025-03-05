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
import org.springframework.web.server.ResponseStatusException;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.casemanagement.beans.StaffMembersV2;
import net.syscon.s4.pkgs.common.CommonService;

@EliteController
public class OcuaoffiController {
	@Autowired
	private OcuaoffiService ocuaoffiService;
	@Autowired
	private CommonService commonService;

	private static Logger logger = LogManager.getLogger(OcuaoffiController.class.getName());

	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/ocuaoffi/addStaffExecuteQuery", method = RequestMethod.POST)
	public List<StaffMembersV2> addStaffExecuteQuery(@RequestBody final StaffMembersV2 searchBean) {
		if(!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		List<StaffMembersV2> searchResult = new ArrayList<>();
		try {
			searchResult = ocuaoffiService.addStaffExecuteQuery(searchBean);
		} catch (Exception e) {
			logger.error("Exception in addStaffExecuteQuery Ocuaoffi:", e);

		}
		return searchResult;
	}
	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role,"OCUAOFFI");
	}

}