package net.syscon.s4.inst.casemanagement;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstances;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamples;
import net.syscon.s4.inst.property.bean.Corporate;

/**
 * Interface OidstestRepository
 */
public interface OidstestRepository {
	List<ReferenceCodes> rgTestedByRecordGroup(String extAgencyFlag);

	List<ReferenceCodes> rgSubTesRsnRecordGroup();

	List<ReferenceCodes> rgSubTesDispRecordGroup();

	List<ReferenceCodes> rgSubTesTypeRecordGroup();

	Integer subTestUpdateOffenderSampleSubstances(List<OffenderSampleSubstances> list);

	Corporate subSamplPostQueryCorporateId(OffenderSamples paramBean);

	List<StaffMembers> rgWitnessRecordGroup(String takenStaffId);

	List<ReferenceCodes> rgSubTesRsltRecordGroup();

	Integer offSamplInsertOffenderSamples(List<OffenderSamples> object);

	Integer offSamplUpdateOffenderSamples(List<OffenderSamples> object);

	Integer offSamplDeleteOffenderSamples(List<OffenderSamples> object);

	Integer subTestDeleteOffenderSampleSubstances(List<OffenderSampleSubstances> list);

	List<ReferenceCodes> rgSubstanceRecordGroup();

	List<OffenderSamples> subSamplExecuteQuery(OffenderSamples obj);

	List<ReferenceCodes> setPositiveFlag(OffenderSamples paramBean);

	List<OffenderSampleSubstances> offenderSampleSubstancesExecuteQuery(
			OffenderSampleSubstances obj);

	StaffMembers subSamplPostQueryStaffId(OffenderSamples paramBean);

	List<StaffMembers> rgTakenByRecordGroup(String staffId);

	Integer subTestInsertOffenderSampleSubstances(List<OffenderSampleSubstances> list);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	BigDecimal getStaffId();

	Integer getTheCountOfOffenderSampleSubstances(OffenderSamples obj);
	
	Integer setPositiveTestedFlag(final OffenderSamples paramBean);


}
