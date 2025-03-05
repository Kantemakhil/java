package net.syscon.s4.inst.property;

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
import net.syscon.s4.inst.property.bean.PropertySettings;

@EliteController
public class OimprostController {

	private static Logger logger = LogManager.getLogger(OimprostController.class.getName());

	@Autowired
	private OimprostService oimprostService;

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprost/getPropertySettingData", method = RequestMethod.POST)
	public List<PropertySettings> getPropertySettingData(@RequestBody final PropertySettings propertySettings) {
		List<PropertySettings> propertySettingData = new ArrayList<>();
		try {
			propertySettingData = oimprostService.getPropertySettingData(propertySettings);
		} catch (Exception e) {
			logger.error("Exception in OimprostController class getPropertySettingData : ", e);
		}
		return propertySettingData;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprost/getPropertySettingLovValues", method = RequestMethod.GET)
	public List<ReferenceCodes> getPropertySettingLovValues() {
		List<ReferenceCodes> propertySettingLovValues = new ArrayList<>();
		try {
			propertySettingLovValues = oimprostService.getPropertySettingLovValues();
		} catch (Exception e) {
			logger.error("Exception in OimprostController class getPropertySettingLovValues : ", e);
		}
		return propertySettingLovValues;
	}

	@PreAuthorize("hasEliteRole('read')")
	@RequestMapping(value = "/oimprost/updatePropertySettingData", method = RequestMethod.POST)
	public Integer updatePropertySettingData(@RequestBody final PropertySettings propertySettings) {
		Integer returnValue = 0;
		try {
			String userName = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
			propertySettings.setModifyUserId(userName);
			returnValue = oimprostService.updatePropertySettingData(propertySettings);
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " In updatePropertySettingData : {}", e.getMessage());
		}
		return returnValue;
	}

}
