package net.syscon.s4.inst.legalscreens.maintenance;

import java.util.List;

import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.im.beans.LegalUpdateUsagesCommitBean;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasonsCommitBean;

public interface OcmstatsService {

	Integer saveOrdersData(LegalUpdateReasonsCommitBean commitBean);

	Integer saveStautesData(LegalUpdateUsagesCommitBean commitBean);

	List<LegalUpdateReasons> getOrdersData();

	List<LegalUpdateUsages> getStatuesData(String updateReasonCode);

}
