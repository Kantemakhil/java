package net.syscon.s4.cf.deductions;

import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.OffFeeBillTransactions;
import net.syscon.s4.cf.deductions.beans.OffFeeBills;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.inmate.beans.GlTransactions;

public interface OcutrdetRepository {

	List<ReferenceCodes> overrideTypeRecordGroup();

	List<OffFeeBills> billDetailsExecuteQuery(OffFeeBills searchBean);

    String getDeductionDesc(String deductionType);

	String getFrequency(String code);

    String getLocation(String location);

	String getFrequencyType(String code);

	String feeActStatusDescription(String feeActStatus);
	
	List<OffFeeBillTransactions> billTransDetailsExecuteQuery(OffFeeBillTransactions searchBean);
	
	String getTxnTypeDescription(String billTxnType);
	
	String getBillStatusForBillId(final String billId);

	List<OffFeeBillTransactions> getBillTransDetails(String billId);
	Integer offFeeBillTransInsertQuery(List<OffFeeBillTransactions> ofFeeBillBean);
	String getBillStatusDesc(final String code);
	GlTransactions getGlTransData(Integer trustTxnId,Integer trustTxnEntrySeq);
	OffenderTransactions getOffTransData(Integer trustTxnId,Integer trustTxnEntrySeq);
	List<SystemProfiles> sysPflBillAdjusExecuteQuery();
	List<SystemProfiles> sysPflBillStatusExecuteQuery();
	Date getBillDateForBillId(final String billId);
	String getBillDetailsCommentValue(final String billId);
}
