package net.syscon.s4.inmate.trust.financialsmaintenance;

import java.util.List;

import net.syscon.s4.im.beans.OffenderLimits;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.SystemProfiles;

/**
 * Interface OtmoflimRepository
 */

public interface OtmoflimRepository {

	List<ReferenceCodes> cgfkchkOffLimOffLimType(ReferenceCodes paramBean);

	Integer offLimUpdateOffenderLimits(List<OffenderLimits> lstOffenderLimits);

	Integer offLimDeleteOffenderLimits(List<OffenderLimits> lstOffenderLimits);

	List<OffenderLimits> offLimExecuteQuery(OffenderLimits objOffenderLimits);

	List<ReferenceCodes> cgfkOffLimLimitTypeRecordGroup();

	Integer offLimInsertOffenderLimits(List<OffenderLimits> lstOffenderLimits);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

}
