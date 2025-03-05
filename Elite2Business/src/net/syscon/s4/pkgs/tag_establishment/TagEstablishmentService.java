package net.syscon.s4.pkgs.tag_establishment;

import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.im.beans.AgencyLocations;

public interface TagEstablishmentService {

	AgencyLocations getHousingLabels(final String agyLocId);

	String getAgyLocDesc(final String pAgyLocId);

	String defaultAgency(final String pGlobalCaseloadId);

	String getActiveAgyLocDesc(final String caseloadId);
	OffenderExternalMovements adjustOccupants(final Integer internalLocationId,final Integer pNoAdjustments,String user);
}