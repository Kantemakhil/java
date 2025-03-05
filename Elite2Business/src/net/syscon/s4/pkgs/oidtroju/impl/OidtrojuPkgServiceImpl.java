package net.syscon.s4.pkgs.oidtroju.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.MovementReason;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.inst.legalscreens.releasenotification.OffenderReleaseNotis;
import net.syscon.s4.inst.movementexternal.beans.OffenderNotCompletions;
import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.pkgs.oidtroju.OidtrojuPkgRepository;
import net.syscon.s4.pkgs.oidtroju.OidtrojuPkgService;

/*
 * Below comments are copied from package OIDTROJU
 *  CREATE OR REPLACE PACKAGE "OMS_OWNER"."OIDTROJU" 
  IS
--
-- Purpose: Used by online module OIDTROJU
--
-- MODIFICATION HISTORY
-- Person     Date         Comments
-- ---------  -----        ------------------------------------------
-- GJC        14-Oct-2006  Removed DBMS_OUTPUT calls
-- Claus      09-Nov-2005  Tr+ 833. Moved DML from pll to package.
*/
@Service
public class OidtrojuPkgServiceImpl implements OidtrojuPkgService {

	@Autowired
	private OidtrojuPkgRepository oidtrojuRepository;

	private static final String Y = "Y";
	final static Logger logger = LogManager.getLogger(OidtrojuPkgServiceImpl.class.getName());

// This procedure is used for Purpose: Used by online module OIDTROJU
	@Override
	public Integer insNotification(final OffenderExternalMovements offExtMov, final String userName) {

		try {
			// This procedure is used to get_notif_info_cur
			final MovementReason movRsn = oidtrojuRepository.getNotifInfoCur(offExtMov);
			// This procedure is used to get_notif_info_cur
			final Long lvCount = oidtrojuRepository.getCntPendCur(offExtMov);
			// This procedure is used to check_for_notif_rec_cur
			final Long vCount = oidtrojuRepository.getCheckforNotifCur(offExtMov);
			if (movRsn != null && movRsn.getNotificationFlag().equals(Y)) {
				if (movRsn.getNotificationType().equals(Y)) {
					if (lvCount > 0) {
						// return null;
					}
					if (vCount < 1) {
						// return null;
					} else {
						// RAISE no_notifications;
					}
					// This procedure is used to get_all_notif_rec_cur
					final List<OffenderReleaseNotis> list = oidtrojuRepository.getAllNotifRecCur(offExtMov);
					list.forEach(bo -> {
						// This procedure is used to get_next_noti_move_seq_cur
						final Long vnMovCur = oidtrojuRepository.getNextNotiMovSeqCur(bo.getOffenderBookId(),
								bo.getNotiSeq());
						final OffenderPendNotifications bean = new OffenderPendNotifications();
						bean.setOffenderBookId(bo.getOffenderBookId());
						bean.setNotiSeq(bo.getNotiSeq());
						bean.setNotiMoveSeq(vnMovCur);
						bean.setMovementType(offExtMov.getMovementType());
						bean.setMovementReasonCode(offExtMov.getMovementReasonCode());
						bean.setMovementDate(offExtMov.getMovementDate());
						bean.setCreateUserId(userName);
						// -- Populate offender_pend_notifications,offender_not_completions
						oidtrojuRepository.insertOffenderPendNotification(bean);
					});

					list.forEach(bo -> {
						// This procedure is used to get_next_noti_move_seq_cur
						final Long vnMovCur = oidtrojuRepository.getNextNotiMovSeqCur(bo.getOffenderBookId(),
								bo.getNotiSeq());
						final OffenderNotCompletions offNtCom = new OffenderNotCompletions();
						offNtCom.setOffenderBookId(bo.getOffenderBookId());
						offNtCom.setNotiSeq(bo.getNotiSeq());
						offNtCom.setNotiMoveSeq(vnMovCur);
						offNtCom.setNotiAgencyPartyCode(bo.getNotiAgencyPartyCode());
						offNtCom.setNotiCorpId(BigDecimal.valueOf(bo.getNotiCorpId()));
						offNtCom.setNotiPersonId(BigDecimal.valueOf(bo.getNotiPersonId()));
						offNtCom.setCreateUserId(userName);
						// Populate offender_not_completions
						oidtrojuRepository.insertOffenderNotCompletion(offNtCom);

					});
				}
			}
		} catch (Exception e) {
			logger.error("insNotification :" + e);
			return 0;
		}
		return 1;
	}
}
