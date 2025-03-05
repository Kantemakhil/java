package net.syscon.s4.inst.movementexternal;

import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.SystemProfiles;

/**
 * Interface OidunctaRepository
 */
public interface OidunctaRepository {
	List<ReferenceCodes> cgfkOffEmMovementReasonCoRecordGroup(String movementType);

	OffenderExternalMovements offEmExecuteQuery(OffenderExternalMovements objOffenderExternalMovements);

	Integer offEmInsertOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	ReferenceCodes cgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffEmToCityRecordGroup();

	AgencyLocations cgfkchkOffEmOffEmAgy(AgencyLocations paramBean);

	OffenderExternalMovements offEmPreInsert(OffenderExternalMovements paramBean);

	List<AgencyLocations> cgfkchkOffEmOffEmAgyLoc();

	List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup();

	MovementReasons cgfkchkOffEmOffEmMoveRs(MovementReasons paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup(String directionCode, String fromAgyLocId);

	ReferenceCodes cgfkchkOffEmOffEmRefFro(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup(String toagyLocId);

	ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffEmOffEmRefTo(ReferenceCodes paramBean);

	String getNextDirectionCode(Integer offBookId);

	String getLastMovementReasonCode(Integer offBookId);

	String getLastFromAgyLocId(Integer offBookId);

	String getLastToAgyLocId(Integer offBookId);

	String getLastMovementCode(Integer offBookId);

	String getLastFromCity(Integer offBookId);

	Date getLastMovementDateTime(Integer offBookId);

	Integer toggleInOutStatus(Long offBookId);

	Integer updateOffenderBookings(List<OffenderExternalMovements> insertList);

	Integer updateExternalMovements(List<OffenderExternalMovements> insertList);

	OffenderExternalMovements getOldRecordOfOffExeMvmt(OffenderExternalMovements offExeM);

	OffenderBookings getOldOffBkgsBean(Long offenderBookId);

}
