package net.syscon.s4.inst.movementexternal;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.beans.VNameSearch;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.movementexternal.beans.VHeaderBlockCommitBean;

/**
 * Interface OidbutabService
 * 
 * @author Arkin Software Technologies
 * @version 1.0
 */
public interface OidbutabService {
	List<VHeaderBlock> vhbExecuteQuery(VHeaderBlock objVHeaderBlock);

	SystemProfiles setOffIdDisplayPrompt(SystemProfiles paramBean);

	OffenderExternalMovements hasLaterMovement(OffenderExternalMovements paramBean);

	List<LivingUnits> rgLuLevel1RecordGroup(String agyLocId);

	List<MovementReasons> rgReasonRecordGroup();

	List<LivingUnits> rgLuLevel2RecordGroup(String agyLocId, String livingUnitId);

	List<AgencyLocations> rgInstitutionRecordGroup(String caseLoadId);

	OffenderExternalMovements vhbPostQuery(OffenderExternalMovements paramBean);

	List<AgencyLocations> rgActiveAgencyRecordGroup(String agyLocId);

	List<AgencyLocations> rgActiveAgencyRecordGroupForGrid(String agyLocId);

	List<LivingUnits> rgLuLevel3RecordGroup(String agyLocId, String livingUnitId);

	List<AgencyLocations> qryBlkWhenNewBlockInstance(AgencyLocations paramBean);

	OffenderExternalMovements oidbutabKeyCommit(OffenderExternalMovements paramBean);

	Integer vhbCommit(VHeaderBlockCommitBean commitBean);

	List<ReferenceCodes> rgCityRecordGroup();

	OmsModules CreateFormGlobals(OmsModules paramBean);

	Integer whenValidateItem(VNameSearch vnamesearch);

	List<ReferenceCodes> rgDirectionRecordGroup();

}
