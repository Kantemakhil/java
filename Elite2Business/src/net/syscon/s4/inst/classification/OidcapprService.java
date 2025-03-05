package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.OffenderAssessmentsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.AssessmentsCommitBean;
import net.syscon.s4.inst.classification.beans.VOffassAss;
import net.syscon.s4.inst.classification.beans.VOffassAssCommitBean;

/**
 * Interface OidcapprService
 */
public interface OidcapprService {
	ReferenceCodes getInstSupDef(ReferenceCodes paramBean);

	ReferenceCodes getRefCodeDesc(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffAss1ReviewSupLevelRecordGroup(Integer obj);

	Integer offAss1Commit(OffenderAssessmentsCommitBean commitBean);

	List<ReferenceCodes> cgfkOffAss1EvaluationResulRecordGroup();

	List<OffenderAssessments> offAss1ExecuteQuery(OffenderAssessments obj);

	List<ReferenceCodes> cgfkOffAss1ReviewCommitteRecordGroup(String caseLoadType);

	Assessments postQueryForOffAss(Assessments paramBean);

	Integer offAssCommit(VOffassAssCommitBean commitBean);

	Assessments offAss1PostQuery(Assessments paramBean);

	ReferenceCodes cgfkchkOffAss1OffAssR2(ReferenceCodes paramBean);

	Assessments cgfkchkOffAssOffAssAss(Assessments paramBean);

	List<AgencyLocations> cgfkOffAss1ReviewPlaceAgyRecordGroup(String caseLoadType);

	List<VOffassAss> offAssExecuteQuery(VOffassAss objVOffassAss);

	AgencyLocations cgfkchkOffAss1OffAssAgy(AgencyLocations paramBean);

	ReferenceCodes getComSupDef(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffAssOffAssRef(ReferenceCodes paramBean);
	
	List<Assessments> getAssessments(OffenderAssessments paramBean);
	
	AssessmentsCommitBean getAssessmentsCommit(OffenderAssessmentsCommitBean commitBean);

}
