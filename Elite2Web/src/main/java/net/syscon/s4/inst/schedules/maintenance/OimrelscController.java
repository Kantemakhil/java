package net.syscon.s4.inst.schedules.maintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.inst.schedules.maintenance.bean.FinalSubmitBeanReleaseScheduleSetting;
import net.syscon.s4.inst.schedules.maintenance.bean.ReleaseSchedulesSettingsBean;

@EliteController
public class OimrelscController {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimrelscController.class.getName());
	@Autowired
	private OimrelscService oimrelscService;
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimrelsc/retrieveGridData", method = RequestMethod.POST)
	public List<ReleaseSchedulesSettingsBean> retrieveGridData(@RequestBody final ReleaseSchedulesSettingsBean searchBean) {
		List<ReleaseSchedulesSettingsBean> obj = new ArrayList<ReleaseSchedulesSettingsBean>();
		try {
			obj = oimrelscService.retrieveGridData(searchBean); 
		} catch (Exception e) {
			logger.error("In retrieveGridData:", e);
		}
		return obj;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimrelsc/submitFormData", method = RequestMethod.POST)
	public Integer submitFormData(@RequestBody final FinalSubmitBeanReleaseScheduleSetting odynfrmSubmitDataBean, @RequestHeader HttpHeaders headers) {
		Integer formBuilderData = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		odynfrmSubmitDataBean.setCreateUserId(userName);
		odynfrmSubmitDataBean.setModifyUserId(userName);
		try {
			formBuilderData = oimrelscService.submitFormData(odynfrmSubmitDataBean); 
		} catch (Exception e) {
			logger.error("In commitformData:", e);
		}
		return formBuilderData;
	}
	
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oimrelsc/retrieveAlertGridData", method = RequestMethod.POST)
	public ReleaseSchedulesSettingsBean retrieveAlertGridData(@RequestBody final ReleaseSchedulesSettingsBean searchBean) {
		ReleaseSchedulesSettingsBean obj = new ReleaseSchedulesSettingsBean();
		try {
			obj = oimrelscService.retrieveAlertGridData(searchBean); 
		} catch (Exception e) {
			logger.error("In retrieveGridData:", e);
		}
		return obj;
	}
	
}
