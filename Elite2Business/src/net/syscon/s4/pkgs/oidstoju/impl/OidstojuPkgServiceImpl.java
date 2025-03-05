package net.syscon.s4.pkgs.oidstoju.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.movementexternal.beans.OffenderPendNotifications;
import net.syscon.s4.inst.schedules.bean.OffenderIndSchedules;
import net.syscon.s4.pkgs.oidstoju.OidstojuPkgRepository;
import net.syscon.s4.pkgs.oidstoju.OidstojuPkgService;

@Service
public class OidstojuPkgServiceImpl implements OidstojuPkgService {

	@Autowired
	private OidstojuPkgRepository oidstojuRepository;

	@Override
	public Integer updNotification(final OffenderIndSchedules offIndSch, final String userName) {
		Integer vOffBookId = null;
		Integer vCount = 0;
		OffenderPendNotifications notiExisCur = null;

		vOffBookId = offIndSch.getOffenderBookId();

		oidstojuRepository.recUpdCur(offIndSch);
		notiExisCur = oidstojuRepository.notiExisCur(offIndSch.getEventId());

		if (notiExisCur != null && notiExisCur.getNotiSeq() > 0) {
			oidstojuRepository.deleteOffenderNotCompletions(notiExisCur.getNotiSeq(), notiExisCur.getNotiMoveSeq(),
					vOffBookId);
			vCount = oidstojuRepository.chNotCur(notiExisCur.getNotiSeq(), notiExisCur.getNotiMoveSeq(), vOffBookId);

			if (vCount > 0) {
				oidstojuRepository.updateOffenderNotCompletions(notiExisCur.getNotiSeq(), notiExisCur.getNotiMoveSeq(),
						userName);
			}
		}
		return vCount;
	}
}
