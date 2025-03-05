package net.syscon.s4.pkgs.tag_establishment;

import java.util.List;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.AgencyLocations;

public interface TagEstablishmentRepository {

	AgencyLocations selectHousingCur(final String agyLocId);

	String getAgyLocDesc(final String pAgyLocId);

	Integer getCountVsAgycur(String pGlobalCaseloadId);

	AgencyLocations getVsAgyLocCur(String pGlobalCaseloadId);

	String getActiveAgyLocDesc(final String caseloadId);
	
	List<OffenderExternalMovements> lockRecordC(Integer internalLocationId,Integer pNoAdjustments);
	Integer updateAgencyLocations(Integer pNoAdjustments,Integer internalLocationId,String modifyUserId);

}