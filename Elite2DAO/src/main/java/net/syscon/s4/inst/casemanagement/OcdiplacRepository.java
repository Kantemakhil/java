package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;

public interface OcdiplacRepository {

	List<ReferenceCodes> staffMemebersListByAgyLocId(String agyLocId);

	List<CasePlanStaff> getAllStaffMembersList(Integer offenderBookId, Integer casePlanId);

	Integer insertCasePlanStaffMemberDetails(List<CasePlanStaff> insertList);

	Integer updateCasePlanStaffMemberDetails(List<CasePlanStaff> updateList);

	List<CasePlanStaff> childDataCarry(Integer offenderBookId);
}
