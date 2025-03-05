package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 * Interface OidmbrdtRepository
 */
public interface OidmbrdtRepository {
	SecurityThreatGroups offenderStgAffiliationsPostQuery(OffenderStgAffiliations paramBean);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	Integer offenderStgDetailsUpdateOffenderStgDetails(List<OffenderStgDetails> object);

	Integer offenderStgDetailsInsertOffenderStgDetails(List<OffenderStgDetails> object);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<OffenderStgDetails> offenderStgDetailsExecuteQuery(OffenderStgDetails object);

	List<Integer> checkGroupFlag(OffenderStgAffiliations paramBean);

	Integer offenderStgAffiliationsInsert(List<OffenderStgAffiliations> object);

	List<OffenderStgDetails> offenderStgAffiliationsOnCheckDeleteMaster(OffenderStgDetails paramBean);

	List<FormAccessibleForms> formAccessibleFormsExecuteQuery(String userName);

	List<OffenderAssessments> offenderAssessmentsExecuteQuery(OffenderAssessments object);

	List<SecurityThreatGroups> rgStg1RecordGroup();

	List<SecurityThreatGroups> rgStg2RecordGroup();

	Integer offenderStgAffiliationsPreInsert(OffenderStgAffiliations paramBean);

	Integer checkGroupInsert(OffenderStgAffiliations paramBean);

	Integer offenderStgAffiliationsUpdateOffenderStgAffiliations(List<OffenderStgAffiliations> object);

	StaffMembers offenderAssessmentsPostQuery(OffenderAssessments paramBean);

	List<OffenderAssessments> offBkgOnCheckDeleteMaster(OffenderAssessments paramBean);

	List<SecurityThreatGroups> rgStg3RecordGroup();

	List<OffenderStgAffiliations> offBkgOnCheckDeleteMaster(OffenderStgAffiliations paramBean);

	List<SecurityThreatGroups> rgGroupRecordGroup();

	Assessments offenderAssessmentsPostQueryGetDesc(OffenderAssessments paramBean);

	OffenderStgDetails offenderStgDetailsPostQuery(OffenderStgDetails paramBean);

	OmsModules formAccessibleFormsPostQuery(FormAccessibleForms paramBean);

	SystemProfiles populateRg();

	List<ReferenceCodes> rgExpReasonRecordGroup();

	List<OffenderStgAffiliations> offenderStgAffiliationsExecuteQuery(OffenderStgAffiliations object);

	String checkDataAvailable(String destinationForm, String offenderBookId, String offenderId);

}
