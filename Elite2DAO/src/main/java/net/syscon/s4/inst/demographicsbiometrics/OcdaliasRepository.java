package net.syscon.s4.inst.demographicsbiometrics;

import java.math.BigDecimal;
import java.util.List;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.OffenderIdentifier;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.im.beans.Offenders;

/**
 * Class OcdaliasRepository
 * 
 */
public interface OcdaliasRepository {

	List<Offenders> offNameSearchOffenders(Offenders objSearchDao);

	List<OffenderIdentifier> offIdAllSearchOffenderIdentifiers(Offenders searchBean);

	List<OffenderIdentifier> offIdSearchOffenderIdentifiers(Offenders aliasesBean);

	Integer offIdInsertOffenderIdentifiers(List<OffenderIdentifier> aliasesBean);

	Integer offIdUpdateOffenderIdentifiers(List<OffenderIdentifier> aliasesBean);

	Integer offIdDeleteOffenderIdentifiers(List<OffenderIdentifier> aliasesBean);

	Integer offNameInsertOffenders(List<Offenders> listOffenders);

	Integer offNameUpdateOffenders(List<Offenders> listOffenders);

	Integer offNameDeleteOffenders(List<Offenders> lstOffenders);

	String offIdPreInsert(String params);

	VHeaderBlock changeWorkingName(VHeaderBlock lstVHeaderBlock);

	VHeaderBlock getWorkingNameOffenderID(Offenders offBookBean);

	List<ReferenceCodes> getGenderDescription();
	
	Integer offNameOnCheckDeleteMasteroffIdCur( Offenders aliasesBean);

	SystemProfiles vsRangeCursor();

	List<Offenders> gettingOldRecord(Long offenderId);

	OffenderBookings gettingOldData(BigDecimal offenderBookId);

	
}
