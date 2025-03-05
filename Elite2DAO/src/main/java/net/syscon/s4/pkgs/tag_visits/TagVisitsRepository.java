package net.syscon.s4.pkgs.tag_visits;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.VNameSearch2;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.booking.beans.OffenderContactPersons;
import net.syscon.s4.inst.booking.beans.Persons;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VisitCycleLimits;
import net.syscon.s4.inst.visitsmanagement.beans.VisitTypeLimits;

public interface TagVisitsRepository {
	Integer getCapacity(final String agyLocId, final Integer internalLocationId);

	String getLocationDesc(final AgencyInternalLocations bean);

	Persons getNamesCur(final Long personId);

	String getParentCodeGlRestrictionCur(final Long personId, final Date pVisitDate);

	Integer getAgeForPersons(final Date pVisitDate);

	Integer insertOffenderVisitVisitor(final VOffenderVisitVisitors offVisitors, final String userName);

	public OffenderContactPersons typrCursor(final OffenderContactPersons bean);

	public ReferenceCodes offenderContactRefCur(final BigDecimal offenderBookId, final Date vPositionDate);

	public Offenders birthDateCur(final BigDecimal offenderBookId);

	public VNameSearch2 offenderContactCur(final BigDecimal contactOffenderRootId,final String userId);

	public String contactOffenderRestCur(final BigDecimal contactOffenderRootId, final Date visitDate);

	Long getNextAgyVisitSlotId();

	String checkTimeslot(BigDecimal pIntLocId, String pWeekDay, String pAgyLocId, String pStartTime);

	public List<VOcuavlocAvailable> getOcuAvailable(final VOcuavlocAvailable objSearchDao);

	public List<VOcuavlocUnavailable> getOcuUnavailable(final VOcuavlocUnavailable bean);

	VisitCycleLimits getVisitCycleLimits(final Long pAgyId, final String pSecLvl, final Date pVisDt);

	Map<String, Object> getUsedVisCur(final Long pAgyId, final String pSecLvl, Long pBkgId, Date pCycStart,
			Date pCycEnd, String pVisTp);

	VisitTypeLimits getTotTypeCur(final Long pLimId, final String pVisTp);

	Integer updateOffeVisitVisitors(final BigDecimal pOffenderVisitId, final String pVisitStatus,
			final String userName);

	VisitCycleLimits cycEndCur(final Date pVisDt, final String pAgyId, final String pSecLvl);

	Object[] usedVisCur(final Long pBkgId, final String pAgyId, final String pSecLvlfinal, final String vVisTp,
			final Date pCycStart, final Date pCycEnd);

	VisitTypeLimits totTypeCur(final Long pLimId, final String pVisTp);

	Integer getContactOffenderBookId(final BigDecimal rootOffenderId,String userId);

	BigDecimal getRootOffenderIdFromBook(final BigDecimal offBookId);

	BigDecimal getNextOffVisitId();

	BigDecimal getEventId();

	BigDecimal getNextOffVisitVisitorId();

	BigDecimal getOffenderId(final String vstOffIdDisplay);

	BigDecimal getlOffenderBookId(final BigDecimal offenderVisitId, final Integer personId, final Date visitDate,
			final Date startTime, final Date endTime);

	BigDecimal getlOffenderBookIdOne(final BigDecimal offenderVisitId, final BigDecimal offenderBookId,
			final Date visitDate, final Date startTime, final Date endTime);

	String getOverlapDetails(final BigDecimal lOffenderBookId);

	Persons getPersonCur(final Long personId);

	ReferenceCodes restrictionCur(final Long offenderBookId, final Long personId, final Date visitsDate);

	ReferenceCodes glRestrictionCur(final Long offenderBookId, final Long personId, final Date visitsDate);

	List<OffenderContactPersons> typeCur(final Long offenderBookId, final Long personId);

	Long getpAge(final Date visitDate, final Date birthDate);

	Integer updateOffenderVisitVisitors(final Long p_offenderVisitId, final String outcomRreasonCode,
			final String userName);

	Integer updateOffenderVisits(final Long p_offenderVisitId, final String userName);

	List<Offenders> otherVisitsOffsCur(final Long offenderBookId, final Long offenderVisitId, final Date visitDate,
			final Date startTime, final Date endTime, final Long visitInternalLocationId);

	List<Offenders> visitVisitingOffsCur(final Long offenderVisitId);

	List<Persons> visitPersonsCur(final Long offenderVisitId);

	List<Persons> otherVisitsPersonsCur(final Long offenderBookId, final Long offenderVisitId, final Date visitDate,
			final Date startTime, final Date endTime, final Long visitInternalLocationId);

	String isVictimCur(final Long offenderBookId, final Long personId);

	String visitedOffenderCur(final Long offenderBookId);

	String lvNonAssocWarningMessageagess(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName);

	String lvNonAssocWarningMessageagessChk2(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName);

	String lvNonAssocWarningMessageagessChk3(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName);

	String lvNonAssocWarningMessageagessChk4(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName);

	String lvNonAssocWarningMessageagessChk5(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName);

	String lvNonAssocWarningMessageagessChk6(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName, final String lvRelationshiDesc);

	String lvNonAssocWarningMessageagessChk7(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName, final String lvRelationshiDesc);

	String lvNonAssocWarningMessageagessChk8(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName, final String lvRelationshiDesc);

	String lvNonAssocWarningMessageagessChk9(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName, final String lvRelationshiDesc);

	String lvNonAssocWarningMessageagessChk10(final String lvNonAssocWarning, final String tbOtherVisitsOffsOffName,
			final String lvVisitedOffName, final String lvRelationshiDesc);

	String warningMsg(final String lvNonAssociatedWar);

	String lVictimWarningMsg(final String lvNonAssociatedWar);

	String lVictimWarningMsg1(final String lvNonAssociatedWar, final String pWarningMsges);

	VisitCycleLimits cycEndCurIep(final Date pVisDt, final String pAgyId, final String iepLevel);

	Object[] usedVisCurIep(final Long pBkgId, final String pAgyId, final String iepLevel, final String vVisTp,
			final Date pCycStart, final Date pCycEnd);

	VisitTypeLimits totTypeCurIep(final Long pLimId, final String pVisTp);
	
	Object[] usedVisCurIepTotalUsed(final Long pBkgId, final String pAgyId,Date startDate,Date endDate);

}