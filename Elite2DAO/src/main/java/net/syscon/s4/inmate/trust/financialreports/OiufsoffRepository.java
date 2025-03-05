package net.syscon.s4.inmate.trust.financialreports;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.im.beans.OmsModules;

public interface OiufsoffRepository {
	List<OiufsoffGetGeneralOffenders> vOffBkgExecuteQuery(OiufsoffGetGeneralOffenders objOiufsoffGetGeneralOffenders);

	Integer vOffBkgInsertOiufsoffGetGeneralOffenders(List<OiufsoffGetGeneralOffenders> lstOiufsoffGetGeneralOffenders);

	List<LivingUnits> cgfkHousingLevelThreeRecordGroup(String agyLocId, BigDecimal parentLivingUnitId);

	List<LivingUnits> cgfkHousingLevelOneRecordGroup(String agyLocId);

	List<AgencyLocations> cgfkAgyLocIdRecordGroup(String caseloadId);

	List<OmsModules> createFormGlobals(OmsModules paramBean);

	String vOffBkgPostQuery(String caseloadId, String agyLocId, BigDecimal rootOffenderId);

	Integer vOffBkgUpdateOiufsoffGetGeneralOffenders(List<OiufsoffGetGeneralOffenders> lstOiufsoffGetGeneralOffenders);

	List<LivingUnits> cgfkHousingLevelTwoRecordGroup(String agyLocId, BigDecimal parentLivingUnitId);

}
