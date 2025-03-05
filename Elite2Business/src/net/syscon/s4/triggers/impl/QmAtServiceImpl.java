package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_qm_at.TagQmAtService;
import net.syscon.s4.triggers.QmAtRepository;
import net.syscon.s4.triggers.QmAtService;
import net.syscon.s4.triggers.VQmAt;

@Service
public class QmAtServiceImpl implements QmAtService {
	private final Logger logger = LogManager.getLogger(QmAtServiceImpl.class);
	@Autowired
	QmAtRepository qmAtRepository;
	@Autowired
	TagQmAtService tagQmAtService;

	@Override
	public Integer qmAtTrigger(final VQmAt vqmatNew, final String sqlOperation) {
		final List<VQmAt> vQmAtList = qmAtRepository.getVQmAt();
		VQmAt lrOldRec = new VQmAt();
		VQmAt lrNewRec = new VQmAt();
		Integer result = 0;
		try {
			if (!vQmAtList.isEmpty()) {
				for (final VQmAt old : vQmAtList) {
					lrOldRec = new VQmAt();
					lrNewRec = new VQmAt();
//				-- Capture OLD Values
					BeanUtils.copyProperties(old, lrOldRec);
//				-- Capture NEW Values
					BeanUtils.copyProperties(vqmatNew, lrNewRec);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						result = tagQmAtService.prIns(lrOldRec, lrNewRec);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
						result = tagQmAtService.prUpd(lrOldRec, lrNewRec);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
						result = tagQmAtService.prDel(lrOldRec, lrNewRec);
					}
				}
			}
		} catch (final Exception e) {
			logger.error("qmAtTrigger", e);
		}
		return result;
	}

}
