package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.inst.legals.beans.OffenderSentConditionsCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;

public interface OcucondiService {
	List<BondType> getCategory();

	List<BondType> getUnit();

	List<CommonLov> getProgram();

	List<OffenderSentConditions> getConditionTypeGrid(OffenderSentConditions condition);

	List<Condition> getConditionGrid(Condition condition);

	List<CommonLov> getConditionLov(Condition condition);

	List<CommonLov> getConditionLov(String condition, String catogry);

	List<LegalUpdateReasons> populateCaseStatus(String ordreType);

	String fetchOrderTypeDesc(String conditionType);

	Condition populateProgramComment(Condition condition);

	List<Category> populateSentencesCategory();

	List<CommonLov> populateProhibitedLov();

	String offSentConCommit(OffenderSentConditionsCommitBean commitBean);

	Integer isDuplicateDetailAcp(OffenderSentConditions bean);

	List<OffenderSentConditions> getDefaultConditions(OffenderSentConditions sentConBean);

	String postInsert(OffenderSentConditions bean);

	List<OffenderSentConditions> getCompleteConditionGrid(OffenderSentConditions condition);
}
