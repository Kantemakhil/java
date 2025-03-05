package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.HdcBoardDecisions;
import net.syscon.s4.triggers.HdcBoardDecisionsT1Repository;
import net.syscon.s4.triggers.HdcBoardDecisionsT1Service;

@Service
public class HdcBoardDecisionsT1ServiceImpl implements HdcBoardDecisionsT1Service {
	private final Logger logger = LogManager.getLogger(HdcBoardDecisionsT1ServiceImpl.class);
	@Autowired
	HdcBoardDecisionsT1Repository hdcBoardDecisionsT1Repository;

	@Override
	public Integer hdcBoardDecisionsT1Trigger(final HdcBoardDecisions hdcBoardDecisions) {
		Integer result = 0;
		final HdcBoardDecisions hdcBoardDecisionsOld = hdcBoardDecisionsT1Repository
				.getHdcBoardDecisions(hdcBoardDecisions);
		try {
			if (Optional.ofNullable(hdcBoardDecisions.getBoardRecommendation()).isPresent()
					&& hdcBoardDecisionsOld.getBoardRecommendation() == null) {
				final Long lNumber = hdcBoardDecisionsT1Repository
						.getLNumber(hdcBoardDecisions.getHdcRequestReferralId());
//			FOR UPDATE WAIT 1;
				result = hdcBoardDecisionsT1Repository.update(hdcBoardDecisions);
			}
		} catch (final Exception e) {
//			tag_error.raise_app_error( -20951,'Error: This resource is currently locked by another user.', TRUE );
			logger.info("Error: This resource is currently locked by another user.");
			logger.error("hdcBoardDecisionsT1Trigger", e);
		}
		return result;
	}
}
