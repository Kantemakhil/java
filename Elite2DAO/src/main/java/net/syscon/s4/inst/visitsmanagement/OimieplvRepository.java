package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

public interface OimieplvRepository {

	Integer insertIepLevelRecord(List<IepLevelBean> insertList);

	Integer updateIepLevelRecord(List<IepLevelBean> updateList);
	
	Integer deleteIepLevelRecord(List<IepLevelBean> deleteList);
	
	List<IepLevelBean> getAllIepLevelCodes();

	String getSystemProfileValue();

}
