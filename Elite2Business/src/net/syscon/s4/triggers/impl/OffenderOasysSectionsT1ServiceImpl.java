package net.syscon.s4.triggers.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.OffenderOasysSections;
import net.syscon.s4.triggers.OffenderOasysSectionsT1Repository;
import net.syscon.s4.triggers.OffenderOasysSectionsT1Service;

@Service
public class OffenderOasysSectionsT1ServiceImpl implements OffenderOasysSectionsT1Service {
	private final Logger logger = LogManager.getLogger(OffenderOasysSectionsT1ServiceImpl.class);
	@Autowired
	OffenderOasysSectionsT1Repository offenderOasysSectionsT1Repository;

	@Override
	public List<OffenderOasysSections> offenderOasysSectionsT1Tgr(final List<OffenderOasysSections> offenrOasysSectList) {
		if (!offenrOasysSectList.isEmpty()) {
			for (final OffenderOasysSections newObj : offenrOasysSectList) {
				if (Optional.ofNullable(newObj).isPresent()) {
					final OffenderOasysSections oldObj = offenderOasysSectionsT1Repository.getOffenderOasysSections(
							newObj.getOffenderBookId(), newObj.getPlanSeq(), newObj.getSectionCode());
					if (null == newObj.getSealFlag() || (newObj.getSealFlag() == oldObj.getSealFlag())) {
						if (Optional.ofNullable(newObj.getRawScore()).isPresent()) {
							try {
								final Integer weightedScore = offenderOasysSectionsT1Repository
										.weightedScore(newObj.getSectionCode(), newObj.getRawScore());
								newObj.setWeightedScore(weightedScore);
							} catch (final Exception e) {
								logger.error("Raw score Out of allowable range", e);
							}
							final Double summaryRatio = offenderOasysSectionsT1Repository
									.summaryRatio(newObj.getWeightedScore(), newObj.getSectionCode());
							newObj.setSummaryRatio(summaryRatio);
							if (summaryRatio <= 0.5) {
								newObj.setCentrelineGroup("1");
							} else {
								newObj.setCentrelineGroup("2");
							}
						} else {
							newObj.setWeightedScore(null);
							newObj.setSummaryRatio(0.0);
							newObj.setCentrelineGroup("0");
						}
					}
				}
			}
		}
		return offenrOasysSectList;
	}

}
