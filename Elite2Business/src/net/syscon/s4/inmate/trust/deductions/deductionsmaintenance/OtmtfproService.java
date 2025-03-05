package net.syscon.s4.inmate.trust.deductions.deductionsmaintenance;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OtmtfproFmbBean;
import net.syscon.s4.common.beans.OtmtfproFmbBeanCommitBean;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.common.beans.TransactionTypes;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.Corporates;
import net.syscon.s4.im.beans.SystemProfiles;
import net.syscon.s4.inmate.beans.CaseloadDeductionProfiles;
import net.syscon.s4.inmate.beans.DeductionTypes;

public interface OtmtfproService  {
	List<DeductionTypes> cgfkCsldDpDeductionTypeRecordGroup(String caseloadType) ;

	List<DeductionTypes> CgfkchkCsldDpDedprofDedty(DeductionTypes paramBean)  ;

	Integer sysPflCommit(SystemProfilesCommitBean commitBean) ;

	List<Object> CgwhenNewFormInstance()  ;

	List<Corporates> cgfkCsldDpPayeeCorporateIRecordGroup() ;

	List<OtmtfproFmbBean> csldDpExecuteQuery(OtmtfproFmbBean objCaseloadDeductionProfiles) ;

	List<Corporates> CgfkchkCsldDpDedprofCorp(Corporates paramBean)  ;

	List<AccountCodes> cgfkCsldDpAccountCodeRecordGroup(String caseloadType) ;

	List<TransactionTypes> CgfkchkCsldDdCsldDdTxnty(TransactionTypes paramBean)  ;

	Integer csldDdCommit(OtmtfproFmbBeanCommitBean CommitBean) ;

	List<AccountCodes> CgfkchkCsldDpDedprofAcCo(AccountCodes paramBean)  ;

	Integer csldDpCommit(OtmtfproFmbBeanCommitBean commitBean) ;

	List<OtmtfproFmbBean> csldDdExecuteQuery(OtmtfproFmbBean objCaseloadDeductionDetails) ;

	List<TransactionTypes> cgfkCsldDdReceiptTxnTypeRecordGroup(String caseloadType) ;

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles) ;

	String omsUtilsDisplayUserMessage(BigDecimal msgNo, String applnCode);
	
	List<CaseloadDeductionProfiles> chkDuplicateDedType(String caseloadId, String deductionType);

}
