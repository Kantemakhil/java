package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActions;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActionsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryRecords;
import net.syscon.s4.im.beans.OffenderMilitaryRecordsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecs;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecsCommitBean;
import net.syscon.s4.im.beans.OffenderMilitaryWarZones;
import net.syscon.s4.im.beans.OffenderMilitaryWarZonesCommitBean;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OidmhistService
 */

public interface OidmhistService {
	Integer disActCommit(OffenderMilitaryDiscActionsCommitBean CommitBean);

	List<OffenderMilitaryDiscActions> disActExecuteQuery(OffenderMilitaryDiscActions objOffenderMilitaryDiscActions);

	List<ReferenceCodes> rgDisciplinaryActionRecordGroup();

	Integer techSpecCommit(OffenderMilitaryTechSpecsCommitBean CommitBean);

	List<ReferenceCodes> rgMltyTechRecordGroup();

	List<OffenderMilitaryTechSpecs> techSpecExecuteQuery(OffenderMilitaryTechSpecs objOffenderMilitaryTechSpecs);

	List<OffenderMilitaryRecords> offMrExecuteQuery(OffenderMilitaryRecords objOffenderMilitaryRecords);

	ReferenceCodes offMrPostQuery(ReferenceCodes paramBean);

	OffenderMilitaryWarZones offMrOnCheckDeleteMaster(OffenderMilitaryWarZones paramBean);

	ReferenceCodes disActPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgMilitaryRankRecordGroup();

	List<Dual> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderMilitaryRecords> offBkgOnCheckDeleteMaster(OffenderMilitaryRecords paramBean);

	List<ReferenceCodes> rgMilitaryBranchRecordGroup();

	List<ReferenceCodes> rgWarZoneRecordGroup();

	ReferenceCodes cgfkchkOffMrOffMrRef(ReferenceCodes paramBean);

	ReferenceCodes warZonesPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgMilitaryDischargeRecordGroup();

	Integer offMrCommit(OffenderMilitaryRecordsCommitBean CommitBean);

	Integer warZonesCommit(OffenderMilitaryWarZonesCommitBean CommitBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	ReferenceCodes techSpecPostQuery(ReferenceCodes paramBean);

	List<OffenderMilitaryWarZones> warZonesExecuteQuery(OffenderMilitaryWarZones objOffenderMilitaryWarZones);

	List<ReferenceCodes> rgHighstRankRecordGroup();

}
