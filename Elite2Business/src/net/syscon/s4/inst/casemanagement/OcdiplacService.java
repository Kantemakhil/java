package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaffCommitBean;

public interface OcdiplacService {

	List<ReferenceCodes> staffMemebersListByAgyLocId(String agyLocId);

	List<CasePlanStaff> getAllStaffMembersList(Integer offenderBookId, Integer casePlanId);

	Integer insertUpdateCasePlanStaffMemberDetails(CasePlanStaffCommitBean commitBean);

	List<CasePlanStaff> childDataCarry(Integer offenderBookId);

}
