package net.syscon.s4.inst.movementexternal;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.movementexternal.beans.OffenderEscapes;

/**
 * Interface OidescapRepository
 */
public interface OidescapRepository {
	List<ReferenceCodes> cgfkOffEscEscapeEscortCodRecordGroup();

	List<OffenderEscapes> offEscExecuteQuery(OffenderEscapes objOffEscapes);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offEscUpdateOffenderEscapes(List<OffenderEscapes> lstOffEscapes);

	List<ReferenceCodes> cgfkOffEscEscapeCircumstanRecordGroup();

	List<AgencyLocations> cgfkOffEscEscapeAgyLocIdRecordGroup();

	Integer offEscInsertOffenderEscapes(List<OffenderEscapes> lstOffEscapes);

	Integer offEscPreInsert();

	List<ReferenceCodes> cgfkOffEscSecurityBreachCRecordGroup();

	List<ReferenceCodes> cgfkOffEscArrestAgyCodeRecordGroup();

	String getOffenderBookingNo(OffenderEscapes searchRecord);

	Date getMaxEscapeDate(Integer offenderBookId);

	Integer offEscDeleteOffenderEscapes(List<OffenderEscapes> lstOffEsc);

	String getreasonDescPreQuery(String escapeMovementReason);

	String getRecaptureReasonDescPreQuery(OffenderEscapes offEsc);
	
	String getOffenderFromLocation(Integer offenderBookId);

}
