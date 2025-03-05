package net.syscon.s4.triggers.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.legals.beans.OffenderSentConditions;
import net.syscon.s4.triggers.OffenderSentConditionsT2Repository;
import net.syscon.s4.triggers.OffenderSentConditionsT2Service;

@Service
public class OffenderSentConditionsT2ServiceImpl implements OffenderSentConditionsT2Service {
	Logger logger = LogManager.getLogger(OffenderSentConditionsT2ServiceImpl.class);
	@Autowired
	OffenderSentConditionsT2Repository offenderSentConditionsT2Repository;

	@Override
	public Integer OffenderSentConditionsT2Tgr(final OffenderSentConditions offenderSentConditions) {
		/*
		 * Lines added by GJC 21/12/2007 to check if the trigger code should be executed
		 * or not IF SYS_CONTEXT('NOMIS','AUDIT_MODULE_NAME',50) = 'MERGE' THEN RETURN;
		 * END IF
		 */
		Integer result = 0;
		final OffenderPrgObligations ofenderPrgObligations = new OffenderPrgObligations();
		OffenderPrgObligations obj = new OffenderPrgObligations();
		Long lvCntCC = offenderSentConditionsT2Repository.lvCntCommunityConditions(
				offenderSentConditions.getCommConditionType(), offenderSentConditions.getCommConditionCode());
		try {
			if (lvCntCC > 0) {
				lvCntCC = offenderSentConditionsT2Repository.lvCntOffePrgOblig(
						offenderSentConditions.getOffenderSentConditionId(),
						offenderSentConditions.getOffenderBookId());
				if (lvCntCC > 0) {
					final String vFound = offenderSentConditionsT2Repository.oblCur(
							BigDecimal.valueOf(offenderSentConditions.getOffenderBookId()),
							offenderSentConditions.getOffenderSentConditionId());
					if ("Y".equals(vFound)) {
						ofenderPrgObligations.setLength(Long.valueOf(offenderSentConditions.getLength().toString()));
						ofenderPrgObligations.setLengthUnit(offenderSentConditions.getLengthUnit());
						result = offenderSentConditionsT2Repository.update(ofenderPrgObligations, vFound);
					} else {
						final String vPrgMethod = offenderSentConditionsT2Repository.prgMethodCur(
								offenderSentConditions.getCategoryType(), offenderSentConditions.getCommConditionCode(),
								offenderSentConditions.getCategoryType());
						obj = new OffenderPrgObligations();
						obj.setOffenderBookId(offenderSentConditions.getOffenderBookId());
						obj.setOffenderSentConditionId(offenderSentConditions.getOffenderSentConditionId());
						obj.setSentenceSeq(offenderSentConditions.getSentenceSeq().longValue());
						obj.setLength(offenderSentConditions.getLength().longValue());
						obj.setLengthUnit(offenderSentConditions.getLengthUnit());
						obj.setEventType(vPrgMethod);
						obj.setProgramId(offenderSentConditions.getProgramId().longValue());
						result = offenderSentConditionsT2Repository.insert(obj);
					}
				}
			}
		} catch (final Exception e) {
			result = 0;
			logger.error("OffenderSentConditionsT2Tgr", e);
		}
		return result;
	}

}
