package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OtmtfproFmbBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;

public interface OtmtfproRepository {
	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType) ;

	List<TransactionTypes> cgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean);

	Integer csldDpInsertCaseloadDeductionProfiles(OtmtfproFmbBean lstCaseloadDeductionProfiles) ;

	List<Corporates> cgfkchkCsldDpDedprofCorp(Corporates paramBean);

	List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() ;

	List<OtmtfproFmbBean> csldDpExecuteQuery(OtmtfproFmbBean objCaseloadDeductionProfiles) ;

	Integer csldDdInsertCaseloadDeductionDetails(List<OtmtfproFmbBean> lstCaseloadDeductionDetails) ;

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType) ;

	List<Object> cgwhenNewFormInstance(SysDual paramBean);

	List<OtmtfproFmbBean> csldDdExecuteQuery(OtmtfproFmbBean objCaseloadDeductionDetails) ;

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(final String caseloadType) ;

	Integer csldDpUpdateCaseloadDeductionProfiles(List<OtmtfproFmbBean> lstCaseloadDeductionProfiles) ;

	Integer csldDdUpdateCaseloadDeductionDetails(List<OtmtfproFmbBean> lstCaseloadDeductionDetails) ;

	List<DeductionTypes> cgfkchkCsldDpDedprofDedty(DeductionTypes paramBean);

	List<AccountCodes> cgfkchkCsldDpDedprofAcCo(AccountCodes paramBean);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) ;

	String omsUtilsDisplayUserMessage(BigDecimal msgNo, String applnCode);

	List<CaseloadDeductionProfiles> chkDuplicateDedType(String caseloadId, String deductionType);

	BigDecimal getExternalPriorityNo(String caseloadId);

	BigDecimal getCsldddMaxPercentage(String csldDdcaseloadId, String csldDddeductionType);

	Integer updateCsldDdCaseloadDeductionProfiles(BigDecimal receiptPercentage, String csldDdcaseloadId,
			String csldDddeductionType, String userName);

	Integer csldDpDeleteCaseloadDeductionProfiles(List<OtmtfproFmbBean> lstCaseloadDeductionProfiles);

	Integer csldDdDeleteCaseloadDeductionDetails(List<OtmtfproFmbBean> deleteList);

}
