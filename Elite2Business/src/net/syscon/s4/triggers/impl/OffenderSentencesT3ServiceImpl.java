package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.im.beans.OffenderPrgObligations;
import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.triggers.OffenderSentencesT3Repository;
import net.syscon.s4.triggers.OffenderSentencesT3Service;

@Service
public class OffenderSentencesT3ServiceImpl implements OffenderSentencesT3Service {
	Logger logger = LogManager.getLogger(OffenderSentencesT3ServiceImpl.class);
	@Autowired
	OffenderSentencesT3Repository offenderSentencesT3Repository;

	@Override
	public Integer OffenderSentencesT3Tgr(final OffenderSentences newObj, final String sqlOperation) {
		Integer result = 0;
		OffenderPrgObligations inputObj = new OffenderPrgObligations();
		final OffenderSentences oldObj = offenderSentencesT3Repository.getOffenderSentences(newObj);
		if ("1991".equals(newObj.getSentenceCategory()) && "IND".equals(newObj.getSentenceLevel())) {
			final String isDrrCur = offenderSentencesT3Repository.isDrrCur(newObj.getSentenceCategory(),
					newObj.getSentenceCalcType());
			try {
				if (Optional.ofNullable(isDrrCur).isPresent()) {
					if ("INSERTING".equalsIgnoreCase(sqlOperation)) {
						inputObj = new OffenderPrgObligations();
						inputObj.setOffenderBookId(newObj.getOffenderBookId());
						inputObj.setOffenderSentConditionId(newObj.getSentenceSeq());
						inputObj.setReferralDate(newObj.getStartDate());
						inputObj.setEndDate(newObj.getEndDate());
						inputObj.setEventType("DRR");
						inputObj.setCreateDatetime(newObj.getCreateDatetime());
						inputObj.setCreateUserId(newObj.getCreateUserId());
						inputObj.setModifyDatetime(newObj.getModifyDatetime());
						result = offenderSentencesT3Repository.insert(inputObj);
					} else {
						result = offenderSentencesT3Repository.update(newObj);
					}
				}
			} catch (final Exception e) {
				result = 0;
				logger.error("offenderSentencesT3Tgr", e);
			}
		}
		return result;
	}

}
