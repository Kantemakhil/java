package net.syscon.s4.inst.movementexternal;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapesCommitBean;

/**
 * Interface OidescapService
 */
public interface OidescapService {
	List<Object> cgwhenNewFormInstance();

	OffenderEscapes offBkgOnCheckDeleteMaster(OffenderEscapes paramBean);

	List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup();

	Integer offEscCommit(OffenderEscapesCommitBean commitBean);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ReferenceCodes> cgfkOffEscEscapeEscortCodRecordGroup();

	List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSearchDao);

	List<OffenderEscapes> offEscExecuteQuery(OffenderEscapes searchBean);

	List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup();

	List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup();

	Date getMaxEscapeDate(Integer offenderBookId);

}
