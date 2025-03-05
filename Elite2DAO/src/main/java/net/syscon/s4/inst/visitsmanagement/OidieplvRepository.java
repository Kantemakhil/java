package net.syscon.s4.inst.visitsmanagement;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;

public interface OidieplvRepository {

	List<IepLevelBean> getIEPLov();

	List<IepLevelBean> getAllData(Integer offenderBookId);

	Integer saveAllData(List<IepLevelBean> commitBean);

	StaffMembers lvLoginUserStaffName(String userName);

	Integer updateComment(IepLevelBean bean);
	
	List<IepLevelBean> getReviewDaysForIepLevelCode();
	
	Integer getSystemGeneratedStaffId();
	
	Long getSystemStaffId();
	
	Date getNextReviewdate(String description);
}
