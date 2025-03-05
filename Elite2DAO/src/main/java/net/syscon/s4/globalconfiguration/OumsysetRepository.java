package net.syscon.s4.globalconfiguration;

import java.util.List;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.SystemSettingsBean;

public interface OumsysetRepository {

	SystemSettingsBean getSysSettingData(SystemSettingsBean systemSettingsBean);
	
	Integer updateSysSettingData(SystemSettingsBean systemSettingsBean);
	
	Integer postUpdate(SystemSettingsBean systemSettingsBean);
	
	SystemSettingsBean getSelectedProvider(String settingType);
	
	List<String> getPhoneFormates();
}
