package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OmsModules;

/**
 * Interface OidbutabRepository
 * 
 */
public interface OidbutabRepository {
	List<VHeaderBlock> vhbExecuteQuery(VHeaderBlock objVHeaderBlock);

	List<LivingUnits> rgLuLevel1RecordGroup(String agyLocId);

	List<MovementReasons> rgReasonRecordGroup();

	AgencyLocations qryBlkWhenNewBlockInstance(AgencyLocations paramBean);

	OffenderExternalMovements vhbPostQuery(OffenderExternalMovements paramBean);

	List<LivingUnits> rgLuLevel2RecordGroup(String agyLocId, String livingUnitId);

	List<AgencyLocations> rgInstitutionRecordGroup(String caseloadId);

	List<AgencyLocations> rgActiveAgencyRecordGroup(String agyLocId);

	List<AgencyLocations> rgActiveAgencyRecordGroupForGrid(String agyLocId);

	Integer vhbUpdateVHeaderBlock(List<VHeaderBlock> lstVHeaderBlock);

	List<LivingUnits> rgLuLevel3RecordGroup(String agyLocId, String livingUnitId);

	OffenderExternalMovements hasLaterMovement(OffenderExternalMovements paramBean);

	OmsModules createFormGlobalsCREATE_FORM_GLOBALS(OmsModules paramBean);

	SystemProfiles setOffIdDisplayPrompt(SystemProfiles paramBean);

	List<ReferenceCodes> rgCityRecordGroup();

	OffenderExternalMovements oidbutabKeyCommit(OffenderExternalMovements paramBean);

	Integer updateOffenderBookings(OffenderBookings offBookObj);

	Integer updateOffenderExternalMovements(OffenderBookings offBookObj);

	Integer insertOffenderExternalMovements(OffenderExternalMovements offExMovObj);

	Integer insertOffenderExternalMovements(List<OffenderExternalMovements> lstoffExMovResults);

	OffenderExternalMovements generateMovementSeq(OffenderExternalMovements offExMovObj);

	Integer whenValidateItem(VNameSearch vnamesearch);

	OffenderExternalMovements getlastMoveAndLoc(OffenderExternalMovements offExMovObj);
	
	OffenderBookings getOldOffenderBookingsData(OffenderBookings bean);
	
	List<OffenderExternalMovements> getOldDataOfExternalMovements(Long offenderBookId);

}
