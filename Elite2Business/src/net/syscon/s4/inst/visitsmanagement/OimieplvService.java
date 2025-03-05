package net.syscon.s4.inst.visitsmanagement;

import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import java.util.List;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

public interface OimieplvService {

	Integer commitBeanIepLevels(IEPLevelCommitBean commitBean);
	
	List<IepLevelBean> getAllIepLevelCodes();

	String getSystemProfileValue();
	
}
