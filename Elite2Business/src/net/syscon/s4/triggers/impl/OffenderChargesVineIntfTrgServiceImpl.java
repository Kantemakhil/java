package net.syscon.s4.triggers.impl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.OffenderBookings;
import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.im.beans.Offenders;
import net.syscon.s4.inst.legalscreens.maintenance.bean.OffenderCharges;
import net.syscon.s4.triggers.ChAudit;
import net.syscon.s4.triggers.OffenderChargesVineIntfTrgRepository;
import net.syscon.s4.triggers.OffenderChargesVineIntfTrgService;

@Service
public class OffenderChargesVineIntfTrgServiceImpl implements OffenderChargesVineIntfTrgService {
	private static Logger logger = LogManager.getLogger(OffenderChargesVineIntfTrgServiceImpl.class);
	@Autowired
	OffenderChargesVineIntfTrgRepository OffenderChargesVineIntfTrgRepository;

	@Override
	public Integer OffenderChargesVineIntfTrg(final OffenderCharges offenderCharges, final String operationType) {
		Offenders vOffRec = new Offenders();
		OffenderBookings vBookRec = null;
		ChAudit chAudit;
		RecordGroup curOffDesc = new RecordGroup();
		Integer result = 0;
		final OffenderCharges offenderChargeOld = OffenderChargesVineIntfTrgRepository
				.getOldOffenderCharge(offenderCharges.getOffenderChargeId());
		if (operationType.equals("INSERTING")) {
			vBookRec = OffenderChargesVineIntfTrgRepository.curActBook(offenderCharges.getOffenderBookId());
			if (Optional.ofNullable(vBookRec).isPresent()
					&& Optional.ofNullable(vBookRec.getOffenderId()).isPresent()) {
				vOffRec = OffenderChargesVineIntfTrgRepository.curOff(vBookRec.getOffenderId().longValue());
				curOffDesc = OffenderChargesVineIntfTrgRepository.curOffDesc(offenderCharges.getOffenceCode(),
						offenderCharges.getStatuteCode());
			}
			if (Optional.ofNullable(vBookRec).isPresent()) {
				chAudit = new ChAudit();
				chAudit.setActionType("A");
				chAudit.setDescription(curOffDesc.getDescription());
				chAudit.setCreateDatetime(new Date());
				chAudit.setCreateUserId(offenderCharges.getCreateUserId());
				chAudit.setModifyDatetime(new Date());
				caAuditDataMapping(offenderCharges, vOffRec, chAudit, vBookRec);
				try {
					result = OffenderChargesVineIntfTrgRepository.inserting(chAudit);
				} catch (final Exception e) {
					logger.error("tag_error.handle(p_log => TRUE);", e);
				}
			}
		}
//		 ELSIF UPDATING THEN
		else if (operationType.equals("UPDATING")) {
			if (Optional.ofNullable(offenderCharges).isPresent()
					&& (offenderCharges.getOffenceCode().equals(offenderChargeOld.getOffenceCode()))
					|| (!offenderCharges.getOffenceType().equals(offenderChargeOld.getOffenceType()))
					|| (!offenderCharges.getChargeStatus().equals(offenderChargeOld.getChargeStatus()))
					|| (!offenderCharges.getStatuteCode().equals(offenderChargeOld.getStatuteCode()))
					|| (!offenderCharges.getOffenceDate().equals(offenderChargeOld.getOffenceDate()))) {
				if (Optional.ofNullable(vBookRec).isPresent()) {
					chAudit = new ChAudit();
					chAudit.setActionType("U");
					chAudit.setModifyDatetime(new Date());
					chAudit.setDescription(curOffDesc.getDescription());
					chAudit.setModifyUserId(offenderCharges.getModifyUserId());
					caAuditDataMapping(offenderCharges, vOffRec, chAudit, vBookRec);
					try {
						result = OffenderChargesVineIntfTrgRepository.updating(chAudit);
//				IF SQL%NOTFOUND THEN
						result = OffenderChargesVineIntfTrgRepository.inserting(chAudit);
					} catch (final Exception e) {
						logger.error("tag_error.handle(p_log => TRUE);", e);
					}
				}
			}
		}
//			ELSIF DELETING THEN
		else if (operationType.equals("DELETING")) {
			final OffenderBookings vBookRecd = OffenderChargesVineIntfTrgRepository
					.curActBook(offenderChargeOld.getOffenderBookId());

			if (Optional.ofNullable(vBookRecd).isPresent()
					&& Optional.ofNullable(vBookRec.getOffenderId()).isPresent()) {
				vOffRec = OffenderChargesVineIntfTrgRepository.curOff(vBookRec.getOffenderId().longValue());
				curOffDesc = OffenderChargesVineIntfTrgRepository.curOffDesc(offenderChargeOld.getOffenceCode(),
						offenderChargeOld.getStatuteCode());
				chAudit = new ChAudit();
				chAudit.setActionType("D");
				chAudit.setDescription(curOffDesc.getDescription());
				caAuditDataMapping(offenderChargeOld, vOffRec, chAudit, vBookRecd);

				try {
					result = OffenderChargesVineIntfTrgRepository.deleting(chAudit);
				} catch (final Exception e) {
					logger.error("tag_error.handle(p_log => TRUE);", e);
				}
			}
		}

		return result;
	}

	private void caAuditDataMapping(final OffenderCharges offenderCharges, final Offenders vOffRec,
			final ChAudit chAudit, final OffenderBookings vBookRec) {
		chAudit.setAgyLocId(vBookRec.getAgyLocId());
		chAudit.setOffenderIdDisplay(vOffRec.getOffenderIdDisplay());
		chAudit.setBookingNo(vBookRec.getBookingNo());
		chAudit.setOffenceCode(offenderCharges.getOffenceCode());
		chAudit.setInitialCounts(1.0);
		chAudit.setOffenceType(offenderCharges.getOffenceType());
		chAudit.setOffenderChargeId(offenderCharges.getOffenderChargeId());
		chAudit.setChargeStatus(offenderCharges.getChargeStatus());
		chAudit.setStatuteCode(offenderCharges.getStatuteCode());
		chAudit.setOffenceDate(offenderCharges.getOffenceDate());
		chAudit.setModifiedDate(new Date());
		chAudit.setOffenderBookId(offenderCharges.getOffenderBookId());
	}

}
