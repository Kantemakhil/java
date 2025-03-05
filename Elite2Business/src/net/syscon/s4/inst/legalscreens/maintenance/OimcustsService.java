package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatuses;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalCustodyStatusesCommitBean;

public interface OimcustsService {
	
	Integer saveCustodyData(LegalCustodyStatusesCommitBean commitBean);

	List<LegalCustodyStatuses> getLegalsData();

}
