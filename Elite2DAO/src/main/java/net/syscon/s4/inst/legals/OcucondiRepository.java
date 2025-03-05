package net.syscon.s4.inst.legals;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.inst.casemanagement.beans.OffenderCaseConditions;
import net.syscon.s4.inst.legals.beans.BondType;
import net.syscon.s4.inst.legals.beans.Category;
import net.syscon.s4.inst.legals.beans.CommonLov;
import net.syscon.s4.inst.legals.beans.Condition;
import net.syscon.s4.inst.legals.beans.OffenderCondTransfer;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;

public interface OcucondiRepository {
	List<BondType> getCategory();

	List<BondType> getUnit();

	List<CommonLov> getProgram();

	List<OffenderSentConditions> getConditionTypeGrid(OffenderSentConditions condition);

	List<Condition> getConditionGrid(Condition condition);

	List<CommonLov> getConditionLov(Condition condition);

	List<CommonLov> getConditionLov(String condition, String catogry);

	Integer getPreInsertSentSentenseId();

	List<Condition> populateCaseStatus();

	String fetchOrderTypeDesc(String conditionType);

	Condition populateProgramComment(Condition condition);

	List<Category> populateSentencesCategory();

	List<CommonLov> populateProhibitedLov();

	Long getProgramId(String program);

	String getProgramCode(Long id);

	String updateoffsentConData(List<OffenderSentConditions> updatetList);

	String deleteOffSenConditionData(List<OffenderSentConditions> deleteList);

	String insertoffSentConditionData(List<OffenderSentConditions> insertList);

	Integer isDuplicateDetailAcp(OffenderSentConditions condition);

	List<OffenderSentConditions> getDefaultConditions(OffenderSentConditions sentConBean);

	Integer getLvCnt(OffenderSentConditions bean);

	String chkAccountStatus(String caseloadId, Long Long);

	String getLvCaseInfoNumber(OffenderSentConditions bean);

	Integer createConditionDeductions(OffenderSentConditions bean);

	Integer getLvRootoffenderId(OffenderSentConditions bean);

	String deleteTypeConditionData(List<OffenderSentConditions> typedeleteList);

	List<OffenderSentConditions> getCompleteConditionGrid(OffenderSentConditions condition);
	
	Integer postInsertIntoOffenderCondTrf(List<OffenderCondTransfer> insertList);
	
	long preInsertGetSeq();

	List<OffenderCondTransfer> getCondTransferData(BigDecimal offenderSentConditionId);

	String deleteOffederCondTransfer(List<OffenderCondTransfer> deleteList);

	String deleteCasePlanConditions(List<OffenderCaseConditions> offenderCaseConditionList);

	List<OffenderCaseConditions> getCasePlanConditions(BigDecimal offSentConditionId);

	String findOffenderAgyLocId(long offenderBookId);
}
