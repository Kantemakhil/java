package net.syscon.s4.inst.institutionalactivities.maintenance;

import java.util.List;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.Allowances;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.AllowancesCommitBean;

public interface OimallowService {

	List<Allowances> getAllAllowances();

	Integer saveAllowances(AllowancesCommitBean commitBean);
	
	List<ReferenceCodes> getUnit();
}
