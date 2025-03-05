package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.im.beans.OffenderCases;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.triggers.CaAudit;
import net.syscon.s4.triggers.OffenderCasesVineIntfTrgRepository;
import net.syscon.s4.triggers.OffenderCasesVineIntfTrgService;

@Service
public class OffenderCasesVineIntfTrgServiceImpl implements OffenderCasesVineIntfTrgService {
	private static Logger logger = LogManager.getLogger(OffenderCasesVineIntfTrgServiceImpl.class);
	@Autowired
	OffenderCasesVineIntfTrgRepository offenderCasesVineIntfTrgRepository;

	@Override
	public Integer offenderCasesVineIntfTrg(final OffenderCases offenderCases) {
		Offenders vOffRec = new Offenders();
		CaAudit caAudit;
		Integer result = 0;
		final OffenderCases offenCasesOld = offenderCasesVineIntfTrgRepository
				.getOldOffenderCases(offenderCases.getCaseId());
		// IF INSERTING THEN
		final OffenderBookings vBookRec = offenderCasesVineIntfTrgRepository
				.curActBook(offenderCases.getOffenderBookId());
		if (Optional.ofNullable(vBookRec).isPresent() && Optional.ofNullable(vBookRec.getOffenderId()).isPresent()) {
			vOffRec = offenderCasesVineIntfTrgRepository.curOff(vBookRec.getOffenderId().longValue());
		}
		if (Optional.ofNullable(vBookRec).isPresent()) {
			caAudit = new CaAudit();
			caAudit.setActionType("A");
			caAudit.setCreateDatetime(new Date());
			caAudit.setCreateUserId(offenderCases.getCreateUserId());
			caAudit.setModifyDatetime(new Date());
			caAuditDataMapping(offenderCases, vOffRec, caAudit, vBookRec);
			try {
				result = offenderCasesVineIntfTrgRepository.inserting(caAudit);
			} catch (final Exception e) {
				logger.error("tag_error.handle(p_log => TRUE);", e);
			}
		}
		
//		 ELSIF UPDATING THEN
		if (Optional.ofNullable(offenderCases).isPresent()
				&& (offenderCases.getCaseInfoNumber().equals(offenCasesOld.getCaseInfoNumber()))
				|| (!offenderCases.getCaseStatus().equals(offenCasesOld.getCaseStatus()))) {
			if (Optional.ofNullable(vBookRec).isPresent()) {
				caAudit = new CaAudit();
				caAudit.setActionType("U");
				caAudit.setModifyDatetime(new Date());
				caAudit.setModifyUserId(offenderCases.getModifyUserId());
				caAuditDataMapping(offenderCases, vOffRec, caAudit, vBookRec);
				try {
					result = offenderCasesVineIntfTrgRepository.updating(caAudit);
//				IF SQL%NOTFOUND THEN
					result = offenderCasesVineIntfTrgRepository.inserting(caAudit);
				} catch (final Exception e) {
					logger.error("tag_error.handle(p_log => TRUE);", e);
				}
			}
//			ELSIF DELETING THEN
			final OffenderBookings vBookRecd = offenderCasesVineIntfTrgRepository
					.curActBook(offenCasesOld.getOffenderBookId());
			if (Optional.ofNullable(vBookRec).isPresent()
					&& Optional.ofNullable(vBookRec.getOffenderId()).isPresent()) {
				vOffRec = offenderCasesVineIntfTrgRepository.curOff(vBookRec.getOffenderId().longValue());
				caAudit = new CaAudit();
				caAudit.setActionType("D");
				caAuditDataMapping(offenCasesOld, vOffRec, caAudit, vBookRecd);

				try {
					result = offenderCasesVineIntfTrgRepository.deleting(caAudit);
				} catch (final Exception e) {
					logger.error("tag_error.handle(p_log => TRUE);", e);
				}
			}

		}
		return result;
	}

	private void caAuditDataMapping(final OffenderCases offenderCases, final Offenders vOffRec, final CaAudit caAudit,
			final OffenderBookings vBookRec) {
		caAudit.setAgyLocId(vBookRec.getAgyLocId());
		caAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
		caAudit.setBookingNo(vBookRec.getBookingNo());
		caAudit.setCaseInfoNumber(offenderCases.getCaseInfoNumber());
		caAudit.setFirstName(vOffRec.getFirstName());
		caAudit.setLastName(vOffRec.getLastName());
		caAudit.setCaseStatus(offenderCases.getCaseStatus());
		caAudit.setCaseId(offenderCases.getCaseId());
		caAudit.setModifiedDate(new Date());
		caAudit.setOffenderBookId(offenderCases.getOffenderBookId());
	}

}
