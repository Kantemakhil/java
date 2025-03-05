package net.syscon.s4.pkgs.non_association;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.syscon.s4.cm.programsservices.VOffenderPrgObligations;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.AgyIntLocProfiles;
import net.syscon.s4.common.beans.OffenderAssessment;
import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderStgAffiliations;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.movements.beans.OffRec;

public interface NonAssociationRepository {

	List<AgencyInternalLocations> parentLocationCur(final Integer pInternalLocId, final String flag);

	List<AgyIntLocProfiles> naTypeCur(final Integer internalLocationId, final String flag);

	List<Offenders> nonAssCur(final Long internalLocationId, final String intLocProfileCode, final Long pOffenderId);

	List<Offenders> checkEnemyCur(final Long internalLocationId, final String string, final Long pOffenderBookId);

	List<Offenders> getNonAssCur(final Long offenderId, final Long crsActyId, final Date offenderStartDate, final Date offenderEndDate);

	List<OffRec> getGetStgNaPrgSrv(final Long offenderBookId, final Long crsActyId);

	Integer getOffBkgCntCur(final Integer pLivingUnitId);

	Integer getRevBedCntCur(final Integer pLivingUnitId);

	public Long getLastSeq();

	public Integer insertProgramNonAssocTemp(final Long lvSeq, final BigDecimal pOffenderId, final BigDecimal pOffenderBookId, final BigDecimal pPrgId, final Long pCrsActyId, final String userName);

	List<Offenders> naCurSelectOperation(final BigDecimal offenderId);

	Boolean chkNaLivUnitConflict(final BigDecimal livingUnitId, final Long offenderId, final String nameType);

	BigDecimal offenderCur(final BigDecimal offId);

	List<AgencyInternalLocations> parentLocationCur(final BigDecimal livingUnitId);

	BigDecimal restrictionsCur(final Integer internalLocationId);

	List<Offenders> checkEnemyCur(final BigDecimal offBookId, final BigDecimal parentLocationId);

	Integer deleteFmprogNonAssocTmp(String modifyUserId);

	Integer programsNonAssocTmp(final VOffenderPrgObligations vOff);

	BigDecimal lOffenderCur(final BigDecimal offenderId);

	List<AgencyInternalLocations> lParentLocationCur(final BigDecimal livingUnitId);

	Integer lRestrictionsCur(final Integer internalLocationId);

	String lCheckEnemyCur(final Integer lParentLocationId, final BigDecimal lOffenderBookId);

	 List<OffenderAssessment> getRevSupLevelCur(final BigDecimal offenderBookId);

	Integer checkLivUnitSecurityCur(final BigDecimal livingUnitId, final String lvSupLevel);
	
	String chkNaBetweenOffendersOne(final Long pOffenderBookId, final Long pVisOffenderBookId);

	String chkNaBetweenOffendersTwo(final Long pOffenderBookId, final Long pVisOffenderBookId);
	
	public List<OffenderNaDetails> getNonAssociationOffenderList(Integer offenderBookId);
	
	public List<AgyIntLocProfiles> getNonAssociationConfigForLocation(final BigDecimal locationId);
	
	public List<AgyIntLocProfiles> getNonAssociationConfigForLocationCode(final String locationCode);
	
	public List<OffenderNaDetails> getNonAssociationOffenders(final BigDecimal offenderBookId, final List<AgyIntLocProfiles> profileCodeList);
	
	public List<OffenderStgAffiliations> getOffendersOfNonAssociationGroup(BigDecimal offenderBookId);
	
	public List<String> getOffendersDetailsByLoc(BigDecimal offenderId,Integer livingUnitId);
	
	public List<String> getOffendersDetailsForLocationExhange(BigDecimal offenderId,Integer livingUnitId,BigDecimal offenderList);
	
	List<OffenderBookings> checkAgyNonAssociationNaCur(Long offenderId);
	
	String chkAgyConflict(Long offenderBookId);
	
	List<OffenderBookings> lOffenderCur(Long offenderId);
	
	Integer offNaDetCur(Long rootOffenderId);
	
	Integer stgNonAssoCur(BigDecimal offenderBookId);
	
	List<String> getGangConflit(BigDecimal livingUnitId, BigDecimal offenderBookId);
	
	List<String> getGangConflitDataForExchange(BigDecimal offenderId, Integer livingUnitId, BigDecimal exhangeOffenderId);
}
