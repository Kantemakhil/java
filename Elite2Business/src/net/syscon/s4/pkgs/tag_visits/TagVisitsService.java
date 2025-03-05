
package net.syscon.s4.pkgs.tag_visits;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocAvailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOcuavlocUnavailable;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisitVisitors;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderVisits;

public interface TagVisitsService {
	Integer getCapacity(final String agyLocId, final Integer internalLocationId);

	String getLocationDesc(final AgencyInternalLocations bean);

	Map<String, Object> getPersonNames(final Long personId, final Date pVisitDate);

	Integer insertOffenderVisitVisitor(final VOffenderVisitVisitors offVisitors, final String userName);

	OffenderVisitVisitors populatedOffenderDetails(final BigDecimal pOffenderBookId,
			final BigDecimal pContactOffenderRootId, final Date pVisitDate, final String userId);

	VOffenderAuthorisedVisitors getConatactOffenderDetails(final BigDecimal contactOffenderRootId,
			final Date visitDate,final String userId);

	Long getNextAgyVisitSlotId();// fuction

	String checkTimeslot(final BigDecimal pIntLocId, final String pWeekDay, final String pAgyLocId,
			final String pStartTime);// function

	List<VOcuavlocAvailable> getOcuavlocAvailable(final VOcuavlocAvailable objSearchDao);

	List<VOcuavlocUnavailable> getOcuavlocUnavailable(final VOcuavlocUnavailable objSearchDao);

	Map<String, Object> visitCalc(final Long pAgyId, final Long pBkgId, final String pSecLvl, final String pVisTp,
			final Date pVisDt);

	Integer completeVisitors(final VOffenderVisits bean, final String userName);

	VOffenderVisits visitCalc(final String pAgyId, final Long pBkgId, final String pSecLvl, final String pVisTp,
			final Date pVisDt);

	Integer getContactOffenderBookId(final BigDecimal rootOffenderId,final String userId);

	BigDecimal getRootOffenderIdFromBook(final BigDecimal offBookId);

	BigDecimal getNextOffVisitId();

	BigDecimal getEventId();

	BigDecimal getNextOffVisitVisitorId();

	BigDecimal getOffenderId(final String vstOffIdDisplay);

	String overlapVisit(final BigDecimal offenderVisitId, final BigDecimal offenderBookId, final Integer personId,
			final Date visitDate, final Date startTime, final Date endTime);

	Map<String, Object> populateVisitorDetails(final Long offenderBookId, final Long personId,
			final Long offContactPersonId, final Date visitsDate);

	Integer cancelVisitors(final Long pOffenderVisitId, final String outcomRreasonCode, final String userName);

	String chkVisitConflicts(final Long offenderBookId, final Long offenderVisitId, final Date visitDate,
			final Date startTime, final Date endTime, final Long visitInternalLocationId);

	VOffenderVisits iepVisitCalc(final String pAgyId, final Long pBkgId, final String iepLevel, final String pVisTp,
			final Date pVisDt);

}