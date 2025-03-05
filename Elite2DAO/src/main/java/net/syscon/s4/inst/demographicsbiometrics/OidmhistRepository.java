package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.im.beans.OffenderMilitaryDiscActions;
import net.syscon.s4.im.beans.OffenderMilitaryRecords;
import net.syscon.s4.im.beans.OffenderMilitaryTechSpecs;
import net.syscon.s4.im.beans.OffenderMilitaryWarZones;
import net.syscon.s4.im.beans.SysDual;

/**
 * Interface OidmhistRepository
 */

public interface OidmhistRepository {
	List<OffenderMilitaryDiscActions> disActExecuteQuery(OffenderMilitaryDiscActions objOffenderMilitaryDiscActions);

	Integer warZonesDeleteOffenderMilitaryWarZones(List<OffenderMilitaryWarZones> lstOffenderMilitaryWarZones);

	ReferenceCodes techSpecPostQuery(ReferenceCodes paramBean);

	ReferenceCodes disActPostQuery(ReferenceCodes paramBean);

	OffenderMilitaryWarZones offMrOnCheckDeleteMaster(OffenderMilitaryWarZones paramBean);

	Integer disActInsertOffenderMilitaryDiscActions(List<OffenderMilitaryDiscActions> lstOffenderMilitaryDiscActions);

	List<ReferenceCodes> rgDisciplinaryActionRecordGroup();

	List<ReferenceCodes> rgMltyTechRecordGroup();

	Integer techSpecDeleteOffenderMilitaryTechSpecs(List<OffenderMilitaryTechSpecs> lstOffenderMilitaryTechSpecs);

	Integer warZonesInsertOffenderMilitaryWarZones(List<OffenderMilitaryWarZones> lstOffenderMilitaryWarZones);

	List<OffenderMilitaryTechSpecs> techSpecExecuteQuery(OffenderMilitaryTechSpecs objOffenderMilitaryTechSpecs);

	List<Dual> cgwhenNewFormInstance(SysDual paramBean);

	List<OffenderMilitaryRecords> offMrExecuteQuery(OffenderMilitaryRecords objOffenderMilitaryRecords);

	Integer disActUpdateOffenderMilitaryDiscActions(List<OffenderMilitaryDiscActions> lstOffenderMilitaryDiscActions);

	OffenderMilitaryDiscActions offMrOnCheckDeleteMaster(OffenderMilitaryDiscActions paramBean);

	ReferenceCodes cgfkchkOffMrOffMrRef(ReferenceCodes paramBean);

	List<OffenderMilitaryRecords> offBkgOnCheckDeleteMaster(OffenderMilitaryRecords paramBean);

	Integer disActDeleteOffenderMilitaryDiscActions(List<OffenderMilitaryDiscActions> lstOffenderMilitaryDiscActions);

	List<ReferenceCodes> rgMilitaryRankRecordGroup();

	List<ReferenceCodes> rgMilitaryBranchRecordGroup();

	Integer techSpecInsertOffenderMilitaryTechSpecs(List<OffenderMilitaryTechSpecs> lstOffenderMilitaryTechSpecs);

	Integer warZonesUpdateOffenderMilitaryWarZones(List<OffenderMilitaryWarZones> lstOffenderMilitaryWarZones);

	List<ReferenceCodes> rgWarZoneRecordGroup();

	OffenderMilitaryTechSpecs offMrOnCheckDeleteMaster(OffenderMilitaryTechSpecs paramBean);

	List<ReferenceCodes> rgMilitaryDischargeRecordGroup();

	ReferenceCodes warZonesPostQuery(ReferenceCodes paramBean);

	Integer offMrInsertOffenderMilitaryRecords(List<OffenderMilitaryRecords> lstOffenderMilitaryRecords);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offMrUpdateOffenderMilitaryRecords(List<OffenderMilitaryRecords> lstOffenderMilitaryRecords);

	List<OffenderMilitaryWarZones> warZonesExecuteQuery(OffenderMilitaryWarZones objOffenderMilitaryWarZones);

	ReferenceCodes offMrPostQuery(ReferenceCodes paramBean);

	List<ReferenceCodes> rgHighstRankRecordGroup();

	Integer offMrDeleteOffenderMilitaryRecords(List<OffenderMilitaryRecords> lstOffenderMilitaryRecords);

	Integer techSpecUpdateOffenderMilitaryTechSpecs(List<OffenderMilitaryTechSpecs> lstOffenderMilitaryTechSpecs);

	Integer offmrPreInsertc(Long paramBean);

	Integer offdisActPreInsertc(Long paramBean);

	Integer offtechSpecPreInsertc(Long paramBean);

	Integer offwarZonesPreInsertc(Long paramBean);

}
