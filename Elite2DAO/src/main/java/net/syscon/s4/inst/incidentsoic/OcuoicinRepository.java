package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.AgyIncInvStatements;
import net.syscon.s4.im.beans.AgyIncInvestigations;

/**
 * Interface OcuoicinRepository
 */
public interface OcuoicinRepository {
	List<ReferenceCodes> rgStatementTypeRecordGroup();

	List<StaffMembers> rgAgyIncpStaffIdRecordGroup(String caseloadId);

	Integer oicInvestInsertAgyIncInvestigations(List<AgyIncInvestigations> lstAgyIncInvestigations);

	Integer oicInvestUpdateAgyIncInvestigations(List<AgyIncInvestigations> lstAgyIncInvestigations);

	List<Object> oicInvestOnCheckDeleteMasteroicInvestStaCur(String investgation);

	Integer oicInvestStaDeleteAgyIncInvStatements(List<AgyIncInvStatements> lstAgyIncInvStatements);

	Integer oicInvestDeleteAgyIncInvestigations(List<AgyIncInvestigations> lstAgyIncInvestigations);

	Integer oicInvestStaInsertAgyIncInvStatements(List<AgyIncInvStatements> lstAgyIncInvStatements);

	Integer oicInvestStaUpdateAgyIncInvStatements(List<AgyIncInvStatements> lstAgyIncInvStatements);

	List<AgyIncInvestigations> oicInvestSearchAgyIncInvestigations(AgyIncInvestigations objAgyIncInvestigations);

	List<AgyIncInvStatements> oicInvestStaSearchAgyIncInvStatements(AgyIncInvStatements objAgyIncInvStatements);

	Object agyIncInvestmentsPreInsertcDAO();

	Object agyIncInvestigationsPreInsertcDAO();

}
