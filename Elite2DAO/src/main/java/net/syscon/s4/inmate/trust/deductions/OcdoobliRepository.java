package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.CaseloadDeductionDetails;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcdoobliRepository
 */
public interface OcdoobliRepository {
	Integer offDrInsertOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String user);

	Persons cgfkchkOffBncOffBncPer(Persons paramBean);

	Integer  cgfklkpOffDedCaseNumber(Long rootOffenderId, String informationNumber);

	List<ReferenceCodes> cgfkchkOffDedOffDedRef();

	List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup(String user);

	List<OffenderBeneficiaries> offDedOnCheckDeleteMaster(OffenderBeneficiaries paramBean);

	List<TransactionTypes> cgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean);

	List<ReferenceCodes> cgfkOffDed1AdjustmentReasoRecordGroup();

	List<Persons> cgfkOffBncPersonIdRecordGroup();

	Integer offDrUpdateOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<OffenderDeductions> offBkgOnCheckDeleteMaster(OffenderDeductions paramBean);

	OffenderDeductionReceipts offDedOnCheckDeleteMaster(OffenderDeductionReceipts paramBean);

	ReferenceCodes cgfklkpOffDedOffDedRef(ReferenceCodes paramBean);

	List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objOffenderDeductionReceipts);

	ReferenceCodes cgfkchkOffDed1OffDedRef(ReferenceCodes paramBean);

	Integer offDedDeleteOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	GroupedObligations cgfkchkOffDedGrpId(GroupedObligations paramBean);

	Integer sysPflInsertSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	Integer offBncUpdateOffenderBeneficiaries(List<OffenderBeneficiaries> lstOffenderBeneficiaries);

	Corporates cgfkchkOffBncOffBncCorp(Corporates paramBean);

	Integer offDedUpdateOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	Integer sysPflUpdateSystemProfiles(List<SystemProfiles> lstSystemProfiles);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries objOffenderBeneficiaries);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	CaseloadDeductionProfiles cgfkchkOffDedOffDedCsld(String paramBean,String user);

	List<Corporates> cgfkOffBncCorporateIdRecordGroup();

	Integer offBncDeleteOffenderBeneficiaries(List<OffenderBeneficiaries> lstOffenderBeneficiaries);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer offBncInsertOffenderBeneficiaries(List<OffenderBeneficiaries> lstOffenderBeneficiaries);

	Integer offDrDeleteOffenderDeductionReceipts(List<OffenderDeductionReceipts> lstOffenderDeductionReceipts);

	List<ObligationGroups> cgfkGroupOblGroupIdRecordGroup(String deductionType);

	List<OffenderCases> cgfkOffDedCaseInfoNumberRecordGroup();

	Integer offDedInsertOffenderDeductions(List<OffenderDeductions> lstOffenderDeductions);

	List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup();

	String getDesc(String deductionType);

	String getUnlFlg(Long offenderId);

	OffenderBeneficiaries getFirstlastNamesusflg(BigDecimal personId);

	OffenderBeneficiaries getCorporateNamesusflg(BigDecimal corporateId);

	BigDecimal calcBenPercent(Long offenderDeductionId, BigDecimal priority);

	String getRecieptTxntypeDesc(String receiptTxnType);

	Integer getTCount(Long offenderId, String caseloadId, String deductionType, String deductionPriority);

	String checkCrTpe(String deductionType);

	Integer setJsCondition(Integer caseId);

	Integer omsUtilcomareDateTime(String effectiveDate, String dspEffectiveDate);

	String displayErrorMessage();

	String profilePlanFlag();

	Integer getCheckOne(Long offenderId, String informationNumber, BigDecimal groupId);

	OffenderDeductions getvsDamtCur(Integer deductionId);

	Integer getvsDamtCurVal(Integer deductionId);

	BigDecimal getDeductionAmnt(Integer deductionId);

	Persons getLastFirstNames(Long personId);

	String getLastFirstName(String lastName, String firstName);

	Integer getPerExists(Integer deductionId, Integer personId);

	Integer updateBenficiaryTransactions(OffenderBeneficiaries updateBean);

	Integer getCorpExists(Integer deductionId, Integer corporateId);

	List<OffenderDeductionReceipts> getoffdedRecieptList(Long offenderDeductionId);

	List<OffenderDeductionReceipts> getreciepttxnType(Long offenderDeductionId, String receiptTxnType);

	List<OffenderDeductionReceipts> getRcptPercent(String caseloadId, String deductionType, String receiptTxnType);

	Integer getMonths(Date vEffDate);

	Long getNxtVal();

	List<OffenderDeductions> checkOneList(Long offenderId, String informationNumber, BigDecimal groupId);

	Integer getUnknownIdNextVal();

	List<OffenderBeneficiaries> postInsert(Long offenderDeductionId);

	List<OffenderBeneficiaries>  benAmnt(Long offenderDeductionId, BigDecimal priority);

	void updateOffenderBeneficiaries(Long beneficiaryId, BigDecimal amount,BigDecimal totalAmount,String modifyUserId);

	void updateElseOffenderBeneficiaries(Long beneficiaryId,String modifyUserId);

	Integer getPercetanage(Long offenderDeductionId, BigDecimal priority);

	void updatePercentageInBenficiaries(Integer totPercent, BigDecimal priority, Long offenderDeductionId,String modifyUserId);

	void updateOffenderPayments(Long offenderId, BigDecimal maxTotalAmount, BigDecimal groupId, String string,String modifyUserId);

	List<OffenderDeductions> getcurChildDeductions(String deductionType);

	List<OffenderDeductions> getcurChildCsldDed(String deductionType, String caseloadId);

	Integer insertIntoOffenderDeductions(OffenderDeductions obj);

	Integer insertOffenderBenficiaries(OffenderDeductions obj);

	Integer insertIntoOffenderDeductionsReciepts(OffenderDeductions obj);

	Long getBenfiNextId();

	Integer checkBenficiaryInserted(Long offenderDeductionId);

	Integer insertMultipleBeneficiaries(OffenderBeneficiaries objBeaninsert);

	Integer getvClientWash();

	 List<OffenderBeneficiaries> getMultiBeanData(OffenderDeductions objBeaninsertBean);

	Integer getLvCount(String caseId, String deductionType);

	Integer getLvFlatRate(String caseloadId, String deductionType);
	
	Integer getNonLvFlatRate(String caseloadId, String deductionType);

	Integer insertIntoOffenderBenficiaries(OffenderBeneficiaries objBeaninsertBean);

	Integer checkReceiptTypeInserted(Long offenderDeductionId);

	List<CaseloadDeductionDetails> getDeductionReciepts(OffenderDeductions objBeaninsertBean);

	String getExistFlg(Long offenderDeductionId, String receiptTxnType);

	Integer insertIntoOffenderDeductionreceipts(OffenderDeductionReceipts cslodDedBean);

	Integer updateOffenders(String oblFlg,String modifyUserId, Long rootOffenderId);

	String getDescriptionforReciptType(String code);

	List<OffenderDeductions> getcurChildOffDed(Long offenderDeductionId);



	void updateOffenderDeductions(BigDecimal deductionPriority, String informationNumber, BigDecimal caseId,
			BigDecimal groupId, String deductionStatus, BigDecimal maxTotalAmount, String percentageOfParent,
			BigDecimal deductionAmount, BigDecimal maxMonthlyAmount, BigDecimal maxRecursiveAmount,
			Long offenderDeductionId,String modifyUserID);

	void updateOffenderOffenderBeneficiaries(BigDecimal maxTotalAmount, BigDecimal maxMonthlyAmount,
			BigDecimal maxRecursiveAmount, String percentageOfParent, Long offenderDeductionId,String modifyUserID);

	String getDeductionCategory(String deductionType);

	List<DeductionTypes> cgfkchkOffDedOffDedT(String deductionType, String caseloadId);

	String getPreviousDedTxn(Long offenderId, String deductionType, String informationNumber);

	String checkprevBenRec(Long offenderDeductionId);

	Integer updateOffenderpaymentPlans(OffenderDeductions bean);

	void deleteoffenderObligationHty(Long offenderDeductionId);

	Corporates getCorporateName(Long corporateId);

	BigDecimal getMonthTotalAmntForObligation(Long offenderDeductionId, BigDecimal priority);

	BigDecimal getMaxMonthlyAmountfromDeductions(Long offenderDeductionId);

	Integer checkinformationandDeductionType(Integer offId, String dedType, String info);

	BigDecimal getmaxTotalAmntfromMainFixedObligation(String dedType,String caseloadId);

	BigDecimal getMaxMonthlyfromMainFixedObligation(String dedType,String caseloadId);

	BigDecimal getPercentFromMonthly(BigDecimal monthlyAmount, Long offenderDeductionId, BigDecimal priority);

	BigDecimal getPercentFromMaxTot(BigDecimal maxtotAmount, Long offenderDeductionId, BigDecimal priority);

	String getProfileVal();

	String getDisabledButton(BigDecimal parentId);

	OffenderDeductions offenderDeductionData(Long offenderDeductionId);
	
	OffenderBeneficiaries offenderBeneficiariesData(Long beneficiaryId);
	
	OffenderBeneficiaries offenderBeneficiariesDatabyDeductionId(Long deductionId);
	

}
