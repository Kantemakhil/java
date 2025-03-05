package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderSentCondActs;
import net.syscon.s4.triggers.OffenderSentCondActsT2Repository;
import net.syscon.s4.triggers.OffenderSentCondActsT2Service;

@Service
public class OffenderSentCondActsT2ServiceImpl implements OffenderSentCondActsT2Service {
	Logger logger = LogManager.getLogger(OffenderSentCondActsT2ServiceImpl.class.getName());
	@Autowired
	OffenderSentCondActsT2Repository offenderSentCondActsT2Repository;

	@Override
	public Integer offenderSentCondActsT2Tgr(final List<OffenderSentCondActs> offenderSentCondActsList) {
		Integer result = 0;
		if (!offenderSentCondActsList.isEmpty()) {
			try {
				for (final OffenderSentCondActs newObj : offenderSentCondActsList) {
					final OffenderSentCondActs old = offenderSentCondActsT2Repository.getOffenderSentCondActs(newObj);
					if (newObj.getSealFlag() == null
							|| (old != null && (newObj.getSealFlag().equals(old.getSealFlag())))) {
						if (old.getProgramId() == null && Optional.ofNullable(newObj.getProgramId()).isPresent()) {
							result = offenderSentCondActsT2Repository.insert(newObj);
						}
					}
				}
			} catch (final Exception e) {
				logger.error("offenderSentCondActsT2Tgr", e);
			}
		}
		return result;
	}

}
