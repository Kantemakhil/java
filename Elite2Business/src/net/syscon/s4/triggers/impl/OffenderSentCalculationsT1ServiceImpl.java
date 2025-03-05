package net.syscon.s4.triggers.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_sentence_calc.TagSentenceCalcService;
import net.syscon.s4.triggers.OffenderSentCalculations;
import net.syscon.s4.triggers.OffenderSentCalculationsT1Service;

@Service
public class OffenderSentCalculationsT1ServiceImpl extends RepositoryBase implements OffenderSentCalculationsT1Service {
	final Logger logger = LogManager.getLogger(OffenderSentCalculationsT1ServiceImpl.class.getName());
	@Autowired
	TagSentenceCalcService tagSentenceCalcService;

	@Override
	public Integer offenderSentCalculationsT1Tgr(final List<OffenderSentCalculations> offenderSentCalculationsList) {
		if (!offenderSentCalculationsList.isEmpty()) {
			try {
				for (final OffenderSentCalculations newObj : offenderSentCalculationsList) {
//				tagSentenceCalcService.createOffenderCurfews(newObj);//Not yet implemented 
				}
			} catch (final Exception e) {
				logger.error("offenderSentCalculationsT1Tgr", e);
			}
		}
		return null;
	}

}
