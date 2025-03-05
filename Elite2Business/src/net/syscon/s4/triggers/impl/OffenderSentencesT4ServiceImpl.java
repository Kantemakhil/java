package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.inst.legals.beans.OffenderSentences;
import net.syscon.s4.triggers.OffenderAssocPEventNotifs;
import net.syscon.s4.triggers.OffenderSentencesT4Repository;
import net.syscon.s4.triggers.OffenderSentencesT4Service;

@Service
public class OffenderSentencesT4ServiceImpl implements OffenderSentencesT4Service {
	Logger logger = LogManager.getLogger(OffenderSentencesT4ServiceImpl.class.getName());
	@Autowired
	OffenderSentencesT4Repository offenderSentencesT4Repository;

	@Override
	public Integer offenderSentencesT4Tgr(final List<OffenderSentences> offenderSentencesList) {
		Integer result = 0;
		OffenderAssocPEventNotifs targetObj = new OffenderAssocPEventNotifs();
		if (!offenderSentencesList.isEmpty()) {
			try {
				for (final OffenderSentences newObj : offenderSentencesList) {
					final OffenderSentences old = offenderSentencesT4Repository.getOffenderSentences(newObj);
					if (newObj.getSealFlag() == null
							|| (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
						if ((null == newObj
								|| (Optional.ofNullable(newObj).isPresent() && "A".equals(newObj.getSentenceStatus())))
								&& (null == old || (Optional.ofNullable(old).isPresent()
										&& "P".equals(newObj.getSentenceStatus())))) {
							targetObj = new OffenderAssocPEventNotifs();
							final Long lTrgEventId = offenderSentencesT4Repository.lTrgEventId();
							targetObj.setEventDate(newObj.getCreateDatetime());
							targetObj.setTrgEventId(lTrgEventId);
							targetObj.setOffenderBookId(newObj.getOffenderBookId());
							targetObj.setCreateDatetime(newObj.getCreateDatetime());
							targetObj.setModifyDatetime(newObj.getModifyDatetime());
							targetObj.setCreateUserId(newObj.getCreateUserId());
							result = offenderSentencesT4Repository.insert(targetObj);

						}
					}
				}
			} catch (final Exception e) {
				result = 0;
				logger.error("offenderSentencesT4Tgr", e);
			}
		}

		return result;
	}

}
