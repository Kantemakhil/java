package net.syscon.s4.globalconfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.syscon.s4.common.EliteController;
import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.SystemSettingsBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * class OumsysetController
 */
@EliteController
public class OumsysetController {
	
	@Autowired
	private OumsysetService oumsysetService;

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumsysetController.class.getName());
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsyset/getSysSettingData", method = RequestMethod.POST)
	public SystemSettingsBean getSysSettingData(@RequestBody final SystemSettingsBean systemSettingsBean) {
		SystemSettingsBean obj = new SystemSettingsBean();
		try {
			if(systemSettingsBean.getSettingProviderCode()!=null && systemSettingsBean.getSettingType()!=null) {
				obj = oumsysetService.getSysSettingData(systemSettingsBean); 
			}else {
				return null;
			}
			
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getSysSettingData : {}", e.getMessage());
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('full')")
	@RequestMapping(value = "/oumsyset/getSysSettingPageData", method = RequestMethod.POST)
	public SystemSettingsBean getSysSettingPageData(@RequestBody final SystemSettingsBean systemSettingsBean) {
		SystemSettingsBean obj = new SystemSettingsBean();
		try {
			if(systemSettingsBean.getSettingProviderCode()!=null && systemSettingsBean.getSettingType()!=null) {
				obj = oumsysetService.getSysSettingPageData(systemSettingsBean); 
			}else {
				return null;
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getSysSettingPageData : {}", e.getMessage());
		}
		return obj;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsyset/updateSysSettingData", method = RequestMethod.POST)
	public Integer updateSysSettingData(@RequestBody final SystemSettingsBean systemSettingsBean) {
		Integer sysSettingData = 0;
		String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		systemSettingsBean.setModifyUserId(userName);
		try {
			sysSettingData = oumsysetService.updateSysSettingData(systemSettingsBean); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In updateSysSettingData : {}", e.getMessage());
		}
		return sysSettingData;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsyset/getPhoneFormatTypes", method = RequestMethod.GET)
	public List<ReferenceCodes>  getPhoneFormatTypes() {
		List<ReferenceCodes>  returnList=null;
		try {
			returnList=oumsysetService.getPhoneFormatTypes();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getPhoneFormatTypes : {}"+ e.getMessage());
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsyset/getServiceBusQueueConf", method = RequestMethod.GET)
	public List<ReferenceCodes>  getServiceBusQueueConf() {
		List<ReferenceCodes>  returnList=null;
		try {
			returnList=oumsysetService.getServiceBusQueueConf();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getServiceBusQueueConf : {}"+ e.getMessage());
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsyset/getSelectedProvider", method = RequestMethod.POST)
	public SystemSettingsBean getSelectedProvider(@RequestBody final String settingType) {
		SystemSettingsBean obj = new SystemSettingsBean();
		try {
			obj = oumsysetService.getSelectedProvider(settingType); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getSelectedProvider : {}", e.getMessage());
		}
		return obj;
	}
	
	
	@GetMapping("oumsyset/getPhoneFormates")
	public List<String> getPhoneFormates() {
		List<String>  returnList=Collections.checkedList(new ArrayList<String>(),String.class);
		try {
			returnList=oumsysetService.getPhoneFormates();
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getPhoneFormates : {}"+ e.getMessage());
		}
		return returnList;
	}
	
	@PreAuthorize("hasEliteRole('no')")
	@RequestMapping(value = "/oumsyset/getInsSecrToken", method = RequestMethod.GET)
	public Map<String, Object> getInsSecretToken(@RequestParam String emailId) {
		Map<String, Object> obj = null;
		try {
			obj = oumsysetService.getAuthTokenBySecretKey(emailId); 
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In getSelectedProvider : {}", e.getMessage());
		}
		return obj;
	}
}
