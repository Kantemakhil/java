package net.syscon.s4.inst.casemanagement;

import java.util.List;

import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.im.beans.CasePlans;
import net.syscon.s4.im.beans.CasePlansCommitBean;

public interface OcustfasService {
	List<CasePlans> casePlansExecuteQuery(CasePlans objCasePlans);

	Integer casePlansPreInsert(CasePlans paramBean);

	List<StaffMembers> rgStaffNameRecordGroup(String agyLocId);

	Integer casePlansCommit(CasePlansCommitBean commitBean);

	String agencyLocations(String agyLocId);

}
