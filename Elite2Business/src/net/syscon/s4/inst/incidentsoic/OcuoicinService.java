package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgyIncInvStatements;
import net.syscon.s4.im.beans.AgyIncInvStatementsCommitBean;
import net.syscon.s4.im.beans.AgyIncInvestigations;
import net.syscon.s4.im.beans.AgyIncInvestigationsCommitBean;

/**
 * Interface OcuoicinService
 */
public interface OcuoicinService {
	Integer oicInvestInsertAgyIncInvestigations(AgyIncInvestigationsCommitBean commitBean);

	Integer oicInvestUpdateAgyIncInvestigations(List<AgyIncInvestigations> lstAgyIncInvestigations);

	Integer oicInvestStaDeleteAgyIncInvStatements(List<AgyIncInvStatements> lstAgyIncInvStatements);

	Integer oicInvestDeleteAgyIncInvestigations(List<AgyIncInvestigations> lstAgyIncInvestigations);

	Integer oicInvestStaInsertAgyIncInvStatements(AgyIncInvStatementsCommitBean commitBean);

	Integer oicInvestStaUpdateAgyIncInvStatements(List<AgyIncInvStatements> lstAgyIncInvStatements);

	List<AgyIncInvestigations> oicInvestSearchAgyIncInvestigations(AgyIncInvestigations objAgyIncInvestigations);

	List<Object> oicInvestOnCheckDeleteMasteroicInvestStaCur(String investgation);

	List<AgyIncInvStatements> oicInvestStaSearchAgyIncInvStatements(AgyIncInvStatements objAgyIncInvStatements);

	List<ReferenceCodes> rgStatementTypeRecordGroup();

	List<StaffMembers> rgAgyIncpStaffIdRecordGroup(String caseloadId);

}
