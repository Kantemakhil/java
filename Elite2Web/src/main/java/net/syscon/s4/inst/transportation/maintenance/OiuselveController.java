package net.syscon.s4.inst.transportation.maintenance;

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
import net.syscon.s4.inst.transportation.beans.SelectVehiclesV1;
import net.syscon.s4.pkgs.common.CommonService;



@EliteController
public class OiuselveController {
	@Autowired
	private OiuselveService oiuselveService;
	@Autowired
	private CommonService commonService;
	
	private static Logger logger = LogManager.getLogger(OiuselveController.class.getName());
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value="/oiuselve/selectVehiclesExecuteQuery", method=RequestMethod.POST)
	public List<SelectVehiclesV1> selectVehiclesExecuteQuery(@RequestBody SelectVehiclesV1 searchBean) {
		List<SelectVehiclesV1> searchResult = new ArrayList<>();
		if (!checkCallFormAccess("read")) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		try {
			searchResult = oiuselveService.selectVehiclesExecuteQuery(searchBean);
		} catch (Exception e) {
			SelectVehiclesV1 bean = new SelectVehiclesV1();
			logger.error("Exception :",e);
			bean.setErrorMessage(e.getMessage());
			searchResult.add(bean);
		}
		return searchResult;
	}

	private Boolean checkCallFormAccess(String role) {
		String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return commonService.checkCallFormAccess(userId, role, "OIUSELVE");
	}
}
