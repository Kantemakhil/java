package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_qm_pai.TagQmPaiService;
import net.syscon.s4.triggers.QmPaiRepository;
import net.syscon.s4.triggers.QmPaiService;
import net.syscon.s4.triggers.VQmPai;

@Service
public class QmPaiServiceImpl implements QmPaiService {
	private final Logger logger = LogManager.getLogger(QmPaiServiceImpl.class);
	@Autowired
	QmPaiRepository qmPaiRepository;
	@Autowired
	TagQmPaiService tagQmPaiService;

	@Override
	public Integer qmPaiTgr(final VQmPai vQmPaiNew, final String sqlOperation) {
		VQmPai lrOldRec = new VQmPai();
		VQmPai lrNewRec = new VQmPai();
		final List<VQmPai> qmPaiTgrList = qmPaiRepository.getVQmPai();
		if (!qmPaiTgrList.isEmpty()) {
			try {
				for (final VQmPai vQmPai : qmPaiTgrList) {
					lrOldRec = new VQmPai();
					lrNewRec = new VQmPai();
					BeanUtils.copyProperties(vQmPai, lrOldRec);
					BeanUtils.copyProperties(vQmPaiNew, lrNewRec);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						tagQmPaiService.prIns(lrOldRec, lrNewRec);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
						tagQmPaiService.prUpd(lrOldRec, lrNewRec);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
						tagQmPaiService.prDel(lrOldRec, lrNewRec);
					}
				}
			} catch (final Exception e) {
				logger.error("qmPaiTgr", e);
			}
		}
		return null;
	}

}
