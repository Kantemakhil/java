package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.MovementReasons;

/**
 * Interface OumeemovRepository
 */
public interface OumeemovRepository {
	Integer offEmInsertOffenderExternalMovements(OffenderExternalMovements lstOffenderExternalMovements);

	ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkOffEmFromAgyLocIdRecordGroup();

	ReferenceCodes cgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean);

	MovementReasons cgfkchkOffEmOffEmMoveRs(MovementReasons paramBean);

	AgencyLocations cgfkchkOffEmOffEmAgy(AgencyLocations paramBean);

	List<ReferenceCodes> cgfkOffEmFromCityRecordGroup();

	List<MovementReasons> cgfkOffEmMovementReasonCoRecordGroup(String movementType);

	Integer offEmUpdateOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	OffenderExternalMovements validateMovementDatetime(OffenderExternalMovements paramBean);

	ReferenceCodes cgfkchkOffEmOffEmRefTo(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffEmMovementTypeRecordGroup();

	AgencyLocations cgfkchkOffEmOffEmAgyLoc(AgencyLocations paramBean);

	ReferenceCodes cgfkchkOffEmOffEmRefFro(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffEmDirectionCodeRecordGroup();

	ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean);

	List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements objOffenderExternalMovements);

	Integer offEmDeleteOffenderExternalMovements(List<OffenderExternalMovements> lstOffenderExternalMovements);

	OffenderExternalMovements validateMovementDate(OffenderExternalMovements paramBean);

	List<AgencyLocations> cgfkOffEmToAgyLocIdRecordGroup();

	List<ReferenceCodes> cgfkOffEmToCityRecordGroup();

	Long offEmPreInsert(OffenderExternalMovements paramBean);

	String validateSeqNum(OffenderExternalMovements paramBean);

	OffenderExternalMovements getMovementType( Long offenderBookId,Long movementSeq);

}
