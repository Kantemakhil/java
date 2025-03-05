package net.syscon.s4.inmate.trust.statements;

import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderBookingsCommitBean;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.ReportBean;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.im.beans.TransStatementBean;
import net.syscon.s4.inmate.beans.AccountCodesCommitBean;
import net.syscon.s4.inmate.beans.Printers;

/**
 * Interface OtstastaService
 */
public interface OtstastaService {
	Printers CgfkchkOmsReqOmsReqPrint(Printers paramBean);

	List<ReferenceCodes> cgfkAcCodeSubAccountTypeRecordGroup();

	List<Printers> cgfkOmsReqPrinterIdRecordGroup();

	Integer acCodeCommit(AccountCodesCommitBean commitBean);

	List<AccountCodes> acCodeExecuteQuery(AccountCodes objAccountCodes);

	List<ReferenceCodes> CgfkchkAcCodeAcSubAcct(ReferenceCodes paramBean);

	Integer offBkg1Commit(OffenderBookingsCommitBean commitBean);

	List<OffenderBookings> offBkg1ExecuteQuery(OffenderBookings objOffenderBookings);

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	ReportBean mainProcess(TransStatementBean paramBean);

	ReportBean superFilese(List<TransStatementBean> paramBean);

}
