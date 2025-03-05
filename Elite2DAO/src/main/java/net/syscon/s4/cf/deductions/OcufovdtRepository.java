package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;

public interface OcufovdtRepository {

	List<ReferenceCodes> overrideTypeRecordGroup();

	List<FeeOverrideDetails> feeOverdDetExecuteQuery(FeeOverrideDetails searchBean);

    String getDeductionDesc(String deductionType);

	String getFrequency(String code);

    String getLocation(String location);

    String getUsername();

    Integer insertFeeOvrdDet(List<FeeOverrideDetails> insertList);

	Integer updateFeeOvrdDet(List<FeeOverrideDetails> updateList);

	Integer deleteFeeOvrdDet(List<FeeOverrideDetails> deleteList);

	List<SystemProfiles> sysPflExecuteQuery();

	List<String> getRoleIdList(String userName);
	Long preInsertFeeOverrideId();
	String getAddedByName();

	List<FeeOverrideDetails> feeOverCheckoverLapping(FeeOverrideDetails searchBean);

	Long getAddedByStaffId(FeeOverrideDetails object);

	String getUserID(Long addedByStaffId);

}
