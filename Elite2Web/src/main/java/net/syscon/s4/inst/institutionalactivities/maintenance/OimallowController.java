package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.AllowancesCommitBean;

@EliteController
public class OimallowController {
	
	@Autowired
	private OimallowService oimallowService;
	
	private static Logger logger = LogManager.getLogger(OimallowController.class.getName());
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimallow/getAllAllowances", method = RequestMethod.GET)
	public List<Allowances> getAllAllowances() {
		List<Allowances> liReturn = new ArrayList<Allowances>();
		try {
			liReturn = oimallowService.getAllAllowances();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getAllAllowances  : " + e);
		}
		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oimallow/saveAllowances", method = RequestMethod.POST)
	public Integer saveAllowances(@RequestBody final AllowancesCommitBean commitBean) {
		Integer liReturn = 0;
		final String user = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		commitBean.setCreateUserId(user);
		try {
			liReturn = oimallowService.saveAllowances(commitBean);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " error in saveAllowances :: " + e);
		}

		return liReturn;
	}
	
	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimallow/getUnit", method = RequestMethod.GET)
	public List<ReferenceCodes> getUnit() {
		List<ReferenceCodes> liReturn = new ArrayList<ReferenceCodes>();
		try {
			liReturn = oimallowService.getUnit();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "error in getUnit  : " + e);
		}
		return liReturn;
	}

}
