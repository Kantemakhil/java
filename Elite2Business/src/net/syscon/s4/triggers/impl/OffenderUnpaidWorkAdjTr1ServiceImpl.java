package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.cm.programsservices.OffenderUnpaidWorkAdj;
import net.syscon.s4.genericservices.CustomException;
import net.syscon.s4.triggers.OffenderUnpaidWorkAdjTr1Repository;
import net.syscon.s4.triggers.OffenderUnpaidWorkAdjTr1Service;

@Service
public class OffenderUnpaidWorkAdjTr1ServiceImpl implements OffenderUnpaidWorkAdjTr1Service {
	private final Logger logger = LogManager.getLogger(OffenderUnpaidWorkAdjTr1ServiceImpl.class.getName());
	@Autowired
	OffenderUnpaidWorkAdjTr1Repository offenderUnpaidWorkAdjTr1Repository;

	@Override
	public Integer offenderUnpaidWorkAdjTr1Tgr(final List<OffenderUnpaidWorkAdj> offenderUnpaidWorkAdjList)
			throws CustomException {
		if (!offenderUnpaidWorkAdjList.isEmpty()) {
			for (final OffenderUnpaidWorkAdj newObj : offenderUnpaidWorkAdjList) {
				final OffenderUnpaidWorkAdj old = offenderUnpaidWorkAdjTr1Repository.getOffenderUnpaidWorkAdj(newObj);
				if (newObj.getSealFlag() == null || (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
					final Integer vNumrows = offenderUnpaidWorkAdjTr1Repository.vNumrows(newObj);
					if (vNumrows == 0) {
						logger.info("vNumrows :" + vNumrows);
						throw new CustomException("Unpaid Work Adjustment has invalid reason code");
					}
				}
			}
		}
		return null;
	}

}
