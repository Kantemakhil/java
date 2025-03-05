package net.syscon.s4.inmate.trust.deductions;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.GroupedObligations;
import net.syscon.s4.im.beans.ObligationGroups;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.OffenderDeductionReceipts;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderBeneficiaries;
import net.syscon.s4.inmate.beans.OffenderBeneficiariesCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductionReceiptsCommitBean;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderDeductionsCommitBean;
import net.syscon.s4.inst.booking.beans.Persons;

/**
 * Interface OcdoobliService
 */
public interface OcdoobliService {
	Corporates cgfkchkOffBncOffBncCorp(Corporates paramBean);

	List<DeductionTypes> cgfkOffDedDeductionTypeRecordGroup(String userName);

	ReferenceCodes cgfkchkOffDed1OffDedRef(ReferenceCodes paramBean);

	List<TransactionTypes> cgfkOffDrReceiptTxnTypeRecordGroup(String user);

	Persons cgfkchkOffBncOffBncPer(Persons paramBean);

	List<ReferenceCodes> cgfkOffDed1AdjustmentReasoRecordGroup();

	List<Persons> cgfkOffBncPersonIdRecordGroup();

	List<OffenderDeductions> offBkgOnCheckDeleteMaster(OffenderDeductions paramBean);

	List<OffenderDeductionReceipts> offDrExecuteQuery(OffenderDeductionReceipts objOffenderDeductionReceipts);

	 String offDrCommit(OffenderDeductionReceiptsCommitBean commitBean);

	List<ReferenceCodes> cgfkchkOffDedOffDedRef();

	ReferenceCodes cgfklkpOffDedOffDedRef(ReferenceCodes paramBean);

	List<TransactionTypes> cgfkchkOffDrOffDrTxnTyp(TransactionTypes paramBean);

	List<OffenderBeneficiaries> offBncExecuteQuery(OffenderBeneficiaries objOffenderBeneficiaries);

	List<OffenderDeductions> offDedExecuteQuery(OffenderDeductions objOffenderDeductions);

	// Integer offDed1Commit(OffenderDeductionsCommitBean commitBean);

	List<OffenderBeneficiaries> offDedOnCheckDeleteMaster(OffenderBeneficiaries paramBean);

	GroupedObligations cgfkchkOffDedGrpId(GroupedObligations paramBean);

	 Integer offDedCommit(OffenderDeductionsCommitBean commitBean);

	 String offBncCommit(OffenderBeneficiariesCommitBean commitBean);

	List<Corporates> cgfkOffBncCorporateIdRecordGroup();

	Integer  cgfklkpOffDedCaseNumber(Long rootOffenderId, String informationNumber);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	List<ObligationGroups> cgfkGroupOblGroupIdRecordGroup(String deductionType);

	List<OffenderCases> cgfkOffDedCaseInfoNumberRecordGroup();

	List<ReferenceCodes> cgfkOffDedDspDescriptionRecordGroup();

	CaseloadDeductionProfiles cgfkchkOffDedOffDedCsld(String searchBean,String user);

	Integer getTCount(Long offenderId, String caseloadId, String deductionType, String deductionPriority);

	String checkCrTpe(String deductionType);

	Integer setJsCondition(Integer caseId);

	Integer omsUtilcomareDateTime(String effectiveDate, String dspEffectiveDate);

	String displayErrorMessage();

	String profilePlanFlag();

	Integer getCheckOne(Long offenderId, String informationNumber,  BigDecimal groupId);

	OffenderDeductions getvsDamtCur(Integer deductionId);

	BigDecimal getDeductionAmnt(Integer deductionId);

	Persons getLastFirstNames(Long personId);

	Integer getPerExists(Integer deductionId, Integer personId);

	Integer updateBenficiaryTransactions(OffenderBeneficiaries updateBean);

	Integer getCorpExists(Integer deductionId, Integer corporateId);

	List<OffenderDeductionReceipts> getreciepttxnType(OffenderDeductionReceipts searchBean);

	Integer getMonths(Date vEffDate);

	Integer updateOffenders(String oblFlg,String userName , Long rootOffenderId);

	String getDescriptionforReciptType(String code);

	List<DeductionTypes> cgfkchkOffDedOffDedT(String deductionType, String caseloadId);

	List<OffenderTransactions> checkprevDedTxnAndCheckpreviousBenrcvied(OffenderDeductions bean);

	Corporates getCorporateName(Long corporateId);

	Integer checkinformationandDeductionType(Integer offId, String dedType, String info);

	String getDisabledButton(BigDecimal parentId);

}
