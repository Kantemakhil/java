package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;

public interface OcmstatsRepository {
	
	Integer saveOrdersData(List<LegalUpdateReasons> reasons);
	
	Integer saveStautesData(List<LegalUpdateUsages> reasons);
	
	Integer updateOrdersData(List<LegalUpdateReasons> reasons);
	
	Integer updateStautesData(List<LegalUpdateUsages> reasons);
	
	List<LegalUpdateReasons> getOrdersData();
	
	List<LegalUpdateUsages> getStatuesData(String updateReasonCode);

}
