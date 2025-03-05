package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.property.OimprostRepository;
import net.syscon.s4.inst.property.OimprostService;
import net.syscon.s4.inst.property.bean.PropertySettings;

@Service
public class OimprostServiceImpl extends BaseBusiness implements OimprostService {

	private static Logger logger = LogManager.getLogger(OimprostServiceImpl.class.getName());

	@Autowired
	private OimprostRepository oimprostRepository;

	@Override
	public List<PropertySettings> getPropertySettingData(PropertySettings propertySettings) {
		List<PropertySettings> propertySettingData = new ArrayList<PropertySettings>();
		try {
			propertySettingData = oimprostRepository.getPropertySettingData(propertySettings);
			if (propertySettingData != null && !propertySettingData.isEmpty()) {
				propertySettingData.forEach(e -> {
					if (e.getSettingValue() != null) {
						e.setSettingValueString(new String(e.getSettingValue()));
					}
				});
			}
		} catch (Exception e) {
			logger.error("Exception in OimprostServiceImpl class getPropertySettingData : ", e);
		}
		return propertySettingData;
	}

	@Override
	public List<ReferenceCodes> getPropertySettingLovValues() {

		ReferenceCodes defReferenceCodes = new ReferenceCodes();
		defReferenceCodes.setCode("DEFAULT");
		defReferenceCodes.setDescription("Default");
		defReferenceCodes.setCanDisplay(true);

		ReferenceCodes noneReferenceCodes = new ReferenceCodes();
		noneReferenceCodes.setCode("NONE");
		noneReferenceCodes.setDescription("None");
		noneReferenceCodes.setCanDisplay(true);

		ReferenceCodes locReferenceCodes = new ReferenceCodes();
		locReferenceCodes.setCode("LOCATION");
		locReferenceCodes.setDescription("Location");
		locReferenceCodes.setCanDisplay(true);

		return Arrays.asList(defReferenceCodes, noneReferenceCodes, locReferenceCodes);
	}

	@Override
	public Integer updatePropertySettingData(PropertySettings propertySettings) {
		Integer returnValue = 0;
		try {
			if (propertySettings.getSettingValueString() != null) {
				propertySettings.setSettingValue(propertySettings.getSettingValueString().getBytes());
				returnValue = oimprostRepository.updatePropertySettingData(propertySettings);
			}
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " updatePropertySettingData and Exception is : {}",
					e.getMessage());
		}
		return returnValue;
	}

}
