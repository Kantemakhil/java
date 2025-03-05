package net.syscon.s4.inst.property;

import java.util.List;

import net.syscon.s4.inst.property.bean.PropertySettings;

public interface OimprostRepository {

	List<PropertySettings> getPropertySettingData(PropertySettings propertySettings);

	Integer updatePropertySettingData(PropertySettings propertySettings);

}
