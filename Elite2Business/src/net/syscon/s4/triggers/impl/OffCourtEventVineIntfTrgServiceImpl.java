package net.syscon.s4.triggers.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.schedules.bean.CourtEvents;
import net.syscon.s4.triggers.CeAudit;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgRepository;
import net.syscon.s4.triggers.OffCourtEventVineIntfTrgService;

/* =============================================================
Below comments are copied from OFF_COURT_EVENT_VINE_INTF_TRG Trigger
================================================================ */
/*
MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
--------------------
Person      Date        Comments
---------   ---------   ------------------------------------------
Edward      12-DEC-2008 Initial version.
Edward      06-JAN-2009 Use tag_error package for exception handling.
Edward      08-Jan-2009 Added case_id to ce_audit table.
*/
@Service
public class OffCourtEventVineIntfTrgServiceImpl implements OffCourtEventVineIntfTrgService {
	Logger logger = LogManager.getLogger(OffCourtEventVineIntfTrgServiceImpl.class);
	@Autowired
	OffCourtEventVineIntfTrgRepository offCourtEventVineIntfTrgRepository;

	@Transactional
	@Override
	public Integer offCourtEventVineIntfTrg(final CourtEvents courteventsNewObj, String operation) {
		Integer result = 0;
		List<CeAudit> ceAuditList = new ArrayList<CeAudit>();
		CeAudit targetObject = new CeAudit();
		final CourtEvents oldObj = offCourtEventVineIntfTrgRepository.getCourtEvents(courteventsNewObj.getEventId());
		final OffenderBookings offenderBookings = offCourtEventVineIntfTrgRepository
				.curActBook(courteventsNewObj.getOffenderBookId());
		if ("INSERTING".equals(operation)) {
			if (Optional.ofNullable(offenderBookings).isPresent()) {
				final Offenders offenders = offCourtEventVineIntfTrgRepository
						.curOff(offenderBookings.getOffenderId().longValue());
				final OffenderCases offenderCases = offCourtEventVineIntfTrgRepository
						.curOffCase(courteventsNewObj.getCaseId());
				try {
					CeAudit tarObj = dataMapping(courteventsNewObj, offenderBookings, offenders, offenderCases);
					targetObject = tarObj;
					targetObject.setCreateDatetime(new Date());
					targetObject.setCreateUserId(courteventsNewObj.getCreateUserId());
					targetObject.setModifyDatetime(new Date());
					ceAuditList.add(targetObject);
					result = offCourtEventVineIntfTrgRepository.insertUpdateDelete(ceAuditList);
				} catch (final Exception e) {
					logger.error("tag_error.handle(p_log => TRUE);", e);
					result = 0;
				}

			}
		} else if ("UPDATING".equals(operation)) {
			final SimpleDateFormat df = new SimpleDateFormat("yyyy");
			final Date oldDate = Optional.ofNullable(oldObj).isPresent()
					&& Optional.ofNullable(oldObj.getEventDate()).isPresent() ? oldObj.getEventDate() : new Date();
			final Date newDate = Optional.ofNullable(courteventsNewObj).isPresent()
					&& Optional.ofNullable(courteventsNewObj.getEventDate()).isPresent()
					? courteventsNewObj.getEventDate()
							: new Date();
			final Date oldTime = Optional.ofNullable(oldObj).isPresent()
					&& Optional.ofNullable(oldObj.getStartTime()).isPresent() ? oldObj.getStartTime() : new Date();
			final Date newTime = Optional.ofNullable(courteventsNewObj).isPresent()
					&& Optional.ofNullable(courteventsNewObj.getStartTime()).isPresent()
					? courteventsNewObj.getStartTime()
							: new Date();
			if(oldObj !=null) {
				try {
					if (!df.format(oldDate).equals(df.format(newDate)) || !df.format(newTime).equals(df.format(oldTime))
							||oldObj.getCourtEventType()!=null && !oldObj.getCourtEventType().equals(courteventsNewObj.getCourtEventType())
							|| !courteventsNewObj.getAgyLocId().equals(oldObj.getAgyLocId())
							|| courteventsNewObj.getEventStatus().equals(oldObj.getEventStatus())) {
						if (Optional.ofNullable(offenderBookings).isPresent()) {
							final Offenders offenders = offCourtEventVineIntfTrgRepository
									.curOff(offenderBookings.getOffenderId().longValue());
							final OffenderCases offenderCases = offCourtEventVineIntfTrgRepository
									.curOffCase(courteventsNewObj.getCaseId());
							ceAuditList = new ArrayList<CeAudit>();
							targetObject = new CeAudit();
							CeAudit tarObj = dataMapping(courteventsNewObj, offenderBookings, offenders, offenderCases);
							targetObject = tarObj;
							targetObject.setActionType("U");
							targetObject.setModifyUserId(courteventsNewObj.getCreateUserId());
							ceAuditList.add(targetObject);
							try {
								result = offCourtEventVineIntfTrgRepository.update(ceAuditList);
								if (result == 0) {
									result = offCourtEventVineIntfTrgRepository.insertUpdateDelete(ceAuditList);
								}
							} catch (final Exception e) {
								logger.error("tag_error.handle(p_log => TRUE);", e);
								result = 0;
							}
						}
					}
				}catch (Exception e) {
					logger.error("error ", e);
					result = 0;
				}
			}
		}    
		else if ("DELETING".equals(operation)) {
			final OffenderBookings offenderBookingsOld = offCourtEventVineIntfTrgRepository
					.curActBook(courteventsNewObj.getOffenderBookId());
			if (Optional.ofNullable(offenderBookingsOld).isPresent()) {
				final Offenders offenders = offCourtEventVineIntfTrgRepository
						.curOff(offenderBookingsOld.getOffenderId().longValue());
				final OffenderCases offenderCases = offCourtEventVineIntfTrgRepository
						.curOffCase(courteventsNewObj.getCaseId());
				try {
					CeAudit tarObj = dataMapping(courteventsNewObj, offenderBookingsOld, offenders, offenderCases);
					targetObject = tarObj;
					targetObject.setActionType("D");
					ceAuditList.add(targetObject);
					result = offCourtEventVineIntfTrgRepository.insertUpdateDelete(ceAuditList);
				} catch (final Exception e) {
					logger.error("tag_error.handle(p_log => TRUE);", e);
					result = 0;
				}

			}
		}
		return result;
	}

	private CeAudit dataMapping(final CourtEvents courteventsNewObj, final OffenderBookings offenderBookings,
			final Offenders offenders, final OffenderCases offenderCases) {
		CeAudit targetObject;
		targetObject = new CeAudit();
		targetObject.setActionType("A");
		targetObject.setAgyLocId(offenderBookings.getAgyLocId());
		targetObject.setOffenderIdDisplay(offenders.getOffenderIdDisplay());
		targetObject.setBookingNo(offenderBookings.getBookingNo());
		targetObject.setCaseInfoNumber(offenderCases != null ? offenderCases.getCaseInfoNumber() : null);
		targetObject.setFirstName(offenders.getFirstName());
		targetObject.setLastName(offenders.getLastName());
		targetObject.setEventDate(courteventsNewObj.getEventDate());
		targetObject.setEventTime(courteventsNewObj.getStartTime());
		targetObject.setMovementType("CRT");
		targetObject.setMovementReasonCode(courteventsNewObj.getCourtEventType());
		targetObject.setSchAgyLocId(courteventsNewObj.getAgyLocId());
		targetObject.setEventId(Long.valueOf(courteventsNewObj.getEventId()));
		targetObject.setEventStatus(courteventsNewObj.getEventStatus());
		targetObject
				.setCaseId(courteventsNewObj.getCaseId() == null ? null : courteventsNewObj.getCaseId().longValue());
		targetObject.setModifiedDate(new Date());
		targetObject.setOffenderBookId(Long.valueOf(courteventsNewObj.getOffenderBookId()));
		return targetObject;
	}

}
