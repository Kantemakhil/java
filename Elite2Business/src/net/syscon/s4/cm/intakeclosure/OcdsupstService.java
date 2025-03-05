package net.syscon.s4.cm.intakeclosure;

import java.util.Date;
import java.util.List;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.beans.OffenderBookingAgyLocs;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.OffSupervisionStsHty;
import net.syscon.s4.common.beans.OffSupervisionStsHtyCommitBean;
import net.syscon.s4.common.beans.VHeaderBlock;

public interface OcdsupstService {

	List<OffenderBookingAgyLocs> offBkgAgyLocExecuteQuery(OffenderBookingAgyLocs searchBean);

	String getProfileValue();

	ReferenceCodes getBillableFlag(String code);

	Integer suphstyCommit(OffSupervisionStsHtyCommitBean commitBean);

	List<OffSupervisionStsHty> supHistoryExecuteQuery(OffSupervisionStsHty searchBean);

	FeeAccountProfiles getsupStartDate(VHeaderBlock s);

	List<OffSupervisionStsHty> getSupvQueryData(OffSupervisionStsHty searchBean);

	Integer getIntakeRevCount(final VHeaderBlock bean);

	List<FeeAccountProfiles> getFeeAccountsData(Integer offenderBookId);

	void generateAutoFeeAccBills(Integer offenderBookId,String userId,  String moduleName);

	Integer autoCreateFeeAccounts(Integer offenderBookId, String caseloadId, Date startDate, String userName);
	String getfeeBillCaslSeq(String caseloadId);
}
