package net.syscon.s4.inst.offenderspecific;
import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.AgencyLocations;
/**
 * Interface OiiemoveRepository
 */
public interface OiiemoveRepository {
	List<OffenderExternalMovements> offEmExecuteQuery(OffenderExternalMovements objOffExtMov);

	ReferenceCodes cgfkchkOffEm1OffEmMov2(ReferenceCodes paramBean);

	List<ReferenceCodes> rgOffEm1DirectionCodeRecordGroup();

	ReferenceCodes cgfkchkOffEmOffEmRefMov(ReferenceCodes paramBean);

	List<ReferenceCodes> rgOffEmMovementReasonCoRecordGroup();

	List<ReferenceCodes> rgOffEmDirectionCodeRecordGroup();

	List<ReferenceCodes> rgOffEm1MovementTypeRecordGroup();

	OffenderExternalMovements offBkgOnCheckDeleteMaster(OffenderExternalMovements paramBean);

	ReferenceCodes cgfkchkOffEm1OffEmDirect(ReferenceCodes paramBean);

	SystemProfiles getProfileValue(SystemProfiles paramBean);

	List<ReferenceCodes> rgOffEmMovementTypeRecordGroup();

	List<ReferenceCodes> rgOffEm1MovementReasonCRecordGroup();

	ReferenceCodes cgfkchkOffEmOffEmMoveRe(ReferenceCodes paramBean);

	OffenderBookings offEm1PostQuery(OffenderBookings paramBean);

	ReferenceCodes cgfkchkOffEmOffEmDirecti(ReferenceCodes paramBean);

	ReferenceCodes cgfkchkOffEm1OffEmRefMo(ReferenceCodes paramBean);
	
	String agyLocationDescription(String agyLocId);
	
    String getOffenderBookingNo(Long offBookId );
    
    List<AgencyLocations> alAgyLocIdRgRecordGroup();
    
    String getCityDescFromCode(String toCity);
    
    String getFullAddress(BigDecimal addressId);

}
