package net.syscon.s4.inst.casemanagement;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstances;
import net.syscon.s4.inst.casemanagement.beans.OffenderSampleSubstancesCommitBean;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamples;
import net.syscon.s4.inst.casemanagement.beans.OffenderSamplesCommitBean;

/**
 * Interface OidstestService
 * 
 */
public interface OidstestService {
	List<ReferenceCodes> rgTestedByRecordGroup(String extAgencyFlag);

	List<ReferenceCodes> rgSubTesRsnRecordGroup();

	List<ReferenceCodes> rgSubTesDispRecordGroup();

	List<ReferenceCodes> rgSubTesTypeRecordGroup();

	List<ReferenceCodes> rgSubstanceRecordGroup();

	List<OffenderSamples> subSamplExecuteQuery(OffenderSamples obj);

	List<OffenderSampleSubstances> offenderSampleSubstancesExecuteQuery(
			OffenderSampleSubstances obj);

	List<StaffMembers> rgTakenByRecordGroup(String staffId);

	Integer offSamplCommit(OffenderSamplesCommitBean object);

	List<StaffMembers> rgWitnessRecordGroup(String takenStaffId);

	List<ReferenceCodes> rgSubTesRsltRecordGroup();

	Integer subTestCommit(OffenderSampleSubstancesCommitBean commitBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	BigDecimal getStaffId(String userId);

	Boolean checkDocumentDependency(Integer offenderBookId, String screenId, String displayNo);

}
