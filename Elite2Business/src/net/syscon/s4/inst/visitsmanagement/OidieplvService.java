package net.syscon.s4.inst.visitsmanagement;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.visitsmanagement.beans.IEPLevelCommitBean;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

public interface OidieplvService {

	List<IepLevelBean> getIEPLov();

	List<IepLevelBean> getAllData(Integer offenderBookId);

	StaffMembers lvLoginUserStaffName(String userName);

	Integer saveAllData(IEPLevelCommitBean commitBean);
	
	List<IepLevelBean> getReviewDaysForIepLevelCode();

}
