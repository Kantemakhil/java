package net.syscon.s4.cf.deductions;

import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetails;
import net.syscon.s4.cf.deductions.maintenance.beans.FeeOverrideDetailsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;

public interface OcufovdtService {

	List<ReferenceCodes> overrideTypeRecordGroup();

	List<FeeOverrideDetails> feeOverdDetExecuteQuery(FeeOverrideDetails searchBean);

	Integer feeOverdDetCommit(FeeOverrideDetailsCommitBean commitBean);

    FeeAccountProfiles feeActExecuteQuery(FeeAccountProfiles searchBean);

    Integer sysPflExecuteQuery(String userName);
    String getAddedByName();

    FeeOverrideDetails feeOverCheckoverLapping(FeeOverrideDetails searchBean);
}
