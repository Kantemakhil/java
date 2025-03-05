package net.syscon.s4.globalconfiguration;

import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.Phones;
import net.syscon.s4.common.beans.SystemSettingsBean;
import net.syscon.s4.im.beans.ReferenceCodes;

public interface OumsysetService {
	
	SystemSettingsBean getSysSettingData(SystemSettingsBean systemSettingsBean);
	
	Integer updateSysSettingData(SystemSettingsBean systemSettingsBean); 
	
	String getConfValue(String settingType,String providerCode,String keyCode);
	
	List<Map<String,Object>> getSysData(String settingType,String providerCode);
	
	List<ReferenceCodes> getPhoneFormatTypes();
	
	Map<String, String> getSettingValuesKeyValueMap(String settingType, String providerCode);
	
	List<ReferenceCodes> getServiceBusQueueConf();
	
	SystemSettingsBean getSelectedProvider(String settingType);
	
	List<String> getPhoneFormates();
	
	 Map<String, Object> getAuthTokenBySecretKey(String userEmail);

	SystemSettingsBean getSysSettingPageData(SystemSettingsBean systemSettingsBean);
	
}
