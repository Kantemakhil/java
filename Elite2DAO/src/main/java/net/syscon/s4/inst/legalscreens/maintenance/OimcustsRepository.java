package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;

public interface OimcustsRepository {
	
	Integer saveCustodyData(List<LegalCustodyStatuses> reasons);
	
	Integer updateCustodyData(List<LegalCustodyStatuses> reasons);
	
	List<LegalCustodyStatuses> getLegalsData();

}
