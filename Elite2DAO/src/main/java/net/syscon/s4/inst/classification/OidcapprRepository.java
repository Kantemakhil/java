package net.syscon.s4.inst.classification;

import java.util.List;

import net.syscon.s4.common.beans.OffenderAssessments;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.classification.beans.Assessments;
import net.syscon.s4.inst.classification.beans.VOffassAss;

/**
 * Interface OidcapprRepository
 */
public interface OidcapprRepository {
	ReferenceCodes getInstSupDef(ReferenceCodes paramBean);

	List<ReferenceCodes> cgfkOffAss1ReviewSupLevelRecordGroup(Integer obj);

	List<ReferenceCodes> cgfkOffAss1EvaluationResulRecordGroup();

	List<OffenderAssessments> offAss1ExecuteQuery(OffenderAssessments obj);

	List<ReferenceCodes> cgfkOffAss1ReviewCommitteRecordGroup(String caseLoadType);

	ReferenceCodes getRefCodeDesc(ReferenceCodes paramBean);

	List<AgencyLocations> cgfkOffAss1ReviewPlaceAgyRecordGroup(String caseLoadType);

	List<VOffassAss> offAssExecuteQuery(VOffassAss objVOffassAss);

	Integer offAss1UpdateOffenderAssessments(List<OffenderAssessments> list);

	AgencyLocations cgfkchkOffAss1OffAssAgy(AgencyLocations paramBean);

	Assessments postQueryForOffAss(Assessments paramBean);

	Assessments cgfkchkOffAssOffAssAss(Assessments paramBean);

	ReferenceCodes cgfkchkOffAssOffAssRef(ReferenceCodes paramBean);

	Assessments offAss1PostQuery(Assessments paramBean);

	ReferenceCodes cgfkchkOffAss1OffAssR2(ReferenceCodes paramBean);

	ReferenceCodes getComSupDef(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffAss1OffAssEval(ReferenceCodes paramBean);
	
	List<Assessments> getAssessments(OffenderAssessments paramBean);

}
