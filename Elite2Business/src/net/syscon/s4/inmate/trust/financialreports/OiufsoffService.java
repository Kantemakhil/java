package net.syscon.s4.inmate.trust.financialreports;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OffenderTrustAccounts;

public interface OiufsoffService {
	List<OiufsoffGetGeneralOffenders> vOffBkgExecuteQuery(OiufsoffGetGeneralOffenders objOiufsoffGetGeneralOffenders);

	List<LivingUnits> cgfkHousingLevelThreeRecordGroup(String agyLocId, BigDecimal parentLivingUnitId);

	List<OffenderTrustAccounts> vOffBkgPostQuery(OffenderTrustAccounts paramBean);

	List<LivingUnits> cgfkHousingLevelOneRecordGroup(String agyLocId);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(String caseloadId);

	List<LivingUnits> cgfkHousingLevelTwoRecordGroup(String agyLocId, BigDecimal parentLivingUnitId);

}
