package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.OffenderLimitsCommitBean;
import net.syscon.s4.im.beans.ReferenceCodes;

/**
 * Interface OtmoflimService
 */

public interface OtmoflimService {

	String offLimCommit(OffenderLimitsCommitBean commitBean);

	List<OffenderLimits> offLimExecuteQuery(OffenderLimits objOffenderLimits);

	List<ReferenceCodes> cgfkOffLimLimitTypeRecordGroup();

}
