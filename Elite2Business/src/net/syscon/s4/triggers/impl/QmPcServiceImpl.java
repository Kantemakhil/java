package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_qm_pc.TagQmPcService;
import net.syscon.s4.triggers.QmPcRepository;
import net.syscon.s4.triggers.QmPcService;
import net.syscon.s4.triggers.VQmPc;

@Service
public class QmPcServiceImpl implements QmPcService {
	private final Logger logger = LogManager.getLogger(QmPcServiceImpl.class);
	@Autowired
	QmPcRepository qmPcRepository;
	@Autowired
	TagQmPcService tagQmPcService;

	@Override
	public Integer qmPcTrigger(final VQmPc vqmatNew, final String sqlOperation) {
		final List<VQmPc> vQmPcList = qmPcRepository.getVQmPc();
		VQmPc lrOldRec = new VQmPc();
		VQmPc lrNewRec = new VQmPc();
		Integer result = 0;
		try {
			if (!vQmPcList.isEmpty()) {
				for (final VQmPc old : vQmPcList) {
					lrOldRec = new VQmPc();
					lrNewRec = new VQmPc();
//				-- Capture OLD Values
					BeanUtils.copyProperties(old, lrOldRec);
//				-- Capture NEW Values
					BeanUtils.copyProperties(vqmatNew, lrNewRec);
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						result = tagQmPcService.prIns(lrOldRec, lrNewRec);
					} else if ("UPDATING".equalsIgnoreCase(sqlOperation)) {
						result = tagQmPcService.prUpd(lrOldRec, lrNewRec);
					} else if ("DELETING".equalsIgnoreCase(sqlOperation)) {
						result = tagQmPcService.prDel(lrOldRec, lrNewRec);
					}
				}
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("qmPcTrigger", e);
		}
		return result;
	}

}
