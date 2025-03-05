package net.syscon.s4.pkgs.oidstwju.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.oidstwju.OidstwjuPkgRepository;
import net.syscon.s4.pkgs.oidstwju.OidstwjuPkgService;

@Service
public class OidstwjuPkgServiceImpl implements OidstwjuPkgService {

	private static final String Y = "Y";
	@Autowired
	private OidstwjuPkgRepository oidstwjuRepository;

	@Override
	public Integer chkNonAssociation(final OffenderIndSchedules offSch) {
		final Integer count1 = oidstwjuRepository.firstSelect(offSch.getAgyLocId(), offSch.getOffenderBookId());
		final Integer count2 = oidstwjuRepository.secondSelect(offSch.getAgyLocId(), offSch.getOffenderBookId());
		Integer count = count1 + count2;
		if (count > 0) {
			return count;
		}

		String lvCancel = null;
		Integer lvCancelCount = 0;
		final String cancel = oidstwjuRepository.cancelCur(offSch.getAgyLocId(), offSch.getOffenderBookId());
		lvCancel = cancel;
		if (lvCancel != null) {
			lvCancelCount = lvCancelCount + 1;
			lvCancel = null;
		}
		count = 0;
		final Integer scheduleCount = oidstwjuRepository.scheduleNaCur(offSch.getAgyLocId(),
				offSch.getOffenderBookId());
		count = scheduleCount;
		if (scheduleCount == 0) {
			return 0;
		}

		if (count > 0 && count - lvCancelCount != 0)
			return count;
		return count;
	}

	@Override
	public Integer insNotification(final OffenderIndSchedules offSch, final String userName) {
		oidstwjuRepository.dateConversion();
		offSch.getEventId();
		String flag = null;
		String not = null;
		int vcount = 0;
		final List<Object[]> list = oidstwjuRepository.notReqCur(offSch.getMovementType(),
				offSch.getMovementReasonCode());
		for (final Object[] obj : list) {
			flag = offSch.getvNotFlag();
			flag = (String) obj[0];
			not = offSch.getvNotType();
			not = (String) obj[1];
		}
		if (flag != null && flag.equals(Y)) {
			flag = Y;

			final Integer getcount = oidstwjuRepository.getOffPCntCur(offSch.getOffenderBookId(),
					offSch.getMovementType(), offSch.getMovementReasonCode());
			vcount = offSch.getvCount();
			vcount = getcount;
			boolean flag1 = false;
			if (vcount > 0) {
				flag1 = offSch.getvProceedFlg();
				flag1 = false;
			}
			if (flag1 == true) {
				final long getNotCount = oidstwjuRepository.getNotRecCur(offSch.getOffenderBookId(),
						offSch.getMovementType(), offSch.getMovementReasonCode());
				vcount = (int) getNotCount;

				if (vcount > 0) {
					final List<Object[]> getlist = oidstwjuRepository.getRelNotRecCur(offSch.getOffenderBookId(),
							offSch.getMovementType(), offSch.getMovementReasonCode());
					for (final Object[] iterator : getlist) {
						Integer noti = offSch.getvNotiSeq();
						noti = (Integer) iterator[0];
						final Integer notiSeq = oidstwjuRepository.getNextmoveSeqCur(offSch.getOffenderBookId(),
								offSch.getvNotiSeq());
						int mnoti = offSch.getvNotiMSeq();
						mnoti = notiSeq;
						mnoti = mnoti + 1;
						offSch.setCreateUserId(userName);
						oidstwjuRepository.offenderPendNotifications(offSch);
						oidstwjuRepository.offenderNotCompletions(offSch);
					}
				}
			}
		}
		return vcount;
	}

}
