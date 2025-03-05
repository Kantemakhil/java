package net.syscon.s4.inst.securitythreatgroups;

import java.util.List;

import net.syscon.s4.common.beans.FormAccessibleForms;
import net.syscon.s4.common.beans.FormAccessibleFormsCommitBean;
import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SecurityThreatGroups;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.OffenderStgAffiliationsCommitBean;
import net.syscon.s4.im.beans.OffenderStgDetails;
import net.syscon.s4.im.beans.OffenderStgDetailsCommitBean;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.classification.beans.Assessments;

/**
 * Interface OidmbrdtService
 */
public interface OidmbrdtService {
	OffenderStgDetails offenderStgDetailsPostQuery(OffenderStgDetails paramBean);

	Integer formAccessibleFormsCommit(FormAccessibleFormsCommitBean commitBean);

	List<SecurityThreatGroups> rgStg3RecordGroup();

	Integer offenderAssessmentsCommit(OffenderAssessmentsCommitBean commitBean);

	List<OffenderStgAffiliations> offBkgOnCheckDeleteMaster(OffenderStgAffiliations paramBean);

	Integer offenderStgAffiliationsPreInsert(OffenderStgAffiliations paramBean);

	Integer checkGroupInsert(OffenderStgAffiliations paramBean);

	List<SecurityThreatGroups> rgGroupRecordGroup();

	OmsModules formAccessibleFormsPostQuery(FormAccessibleForms paramBean);

	List<ReferenceCodes> rgReasonRecordGroup();

	List<OffenderStgDetails> offenderStgDetailsExecuteQuery(OffenderStgDetails object);

	List<Integer> checkGroupFlag(OffenderStgAffiliations paramBean);

	List<OffenderStgDetails> offenderStgAffiliationsOnCheckDeleteMaster(OffenderStgDetails paramBean);

	List<FormAccessibleForms> formAccessibleFormsExecuteQuery(String offenderBookId, String offenderId,String userName);

	List<OffenderAssessments> offenderAssessmentsExecuteQuery(OffenderAssessments object);

	List<SecurityThreatGroups> rgStg1RecordGroup();

	List<SecurityThreatGroups> rgStg2RecordGroup();

	Integer offenderStgAffiliationsCommit(OffenderStgAffiliationsCommitBean commitBean);

	List<ReferenceCodes> rgExpReasonRecordGroup();

	Assessments offenderAssessmentsPostQuery(OffenderAssessments paramBean);

	SystemProfiles populateRg();

	List<OffenderStgAffiliations> offenderStgAffiliationsExecuteQuery(OffenderStgAffiliations object);

	SecurityThreatGroups offenderStgAffiliationsPostQuery(OffenderStgAffiliations paramBean);

	Integer offenderStgDetailsCommit(OffenderStgDetailsCommitBean commitBean);

}
