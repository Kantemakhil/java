package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.property.bean.PropertySettings;

public interface OimprostService {

	List<PropertySettings> getPropertySettingData(PropertySettings propertySettings);

	List<ReferenceCodes> getPropertySettingLovValues();

	Integer updatePropertySettingData(PropertySettings propertySettings);

}
