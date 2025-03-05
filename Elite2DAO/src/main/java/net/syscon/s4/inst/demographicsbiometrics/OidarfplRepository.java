package net.syscon.s4.inst.demographicsbiometrics;

import java.util.List;

import net.syscon.s4.common.beans.FindFacilityExecuteQueryBean;
import net.syscon.s4.common.beans.FindHousingExecuteQueryBean;
import net.syscon.s4.im.beans.OffenderAttributes;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.im.beans.OmuavbedLivUnitsQuery;
import net.syscon.s4.im.beans.ProfileCodes;

public interface OidarfplRepository {

	List<OmuavbedLivUnitsQuery> findFacilityExecuteQuery(FindFacilityExecuteQueryBean findFacilityExecuteQueryBean);

	List<OffenderAttributes> offenderCaseDetails(Long offenderBookId);

	List<ProfileCodes> getMovementReasonCode();

	List<ProfileCodes> getOffenderPersonalAtt(Long offenderBookId);

	List<OffenderAttributes> offenderAttributeExecuteQuery(Long offenderId,Long offenderBookId);

	List<OffenderAttributes> offenderSentenceDetails(Long offenderBookId);

	List<OmuavbedLivUnitsQuery> offenderHousingExecuteQuery(FindHousingExecuteQueryBean findHousingExecuteQueryBean, List<OmuavbedLivUnitsQuery> list);
	
	
	List<Offenders> offenderPersonalInformation(String profileCategory,Integer offenderBookId,String caseloadType);
	
	List<Offenders> offenderPersonalInformationText(String profileCategory,Integer offenderBookId,String caseloadType);
	

}
