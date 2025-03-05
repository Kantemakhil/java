package net.syscon.s4.triggers.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.triggers.HdcGovernorDecisions;
import net.syscon.s4.triggers.HdcGovernorDecisionsT1Repository;
import net.syscon.s4.triggers.HdcGovernorDecisionsT1Service;
import net.syscon.s4.triggers.HdcRequestReferrals;

@Service
public class HdcGovernorDecisionsT1ServiceImpl implements HdcGovernorDecisionsT1Service {
	private final Logger logger = LogManager.getLogger(HdcGovernorDecisionsT1ServiceImpl.class);
	@Autowired
	HdcGovernorDecisionsT1Repository hdcGovernorDecisionsT1Repository;

	@Override
	public Integer hdcGovernorDecisionsT1(final HdcGovernorDecisions hdcGovernorDecisions, final String sqlOperation) {
		final HdcRequestReferrals hdcRequestReferr = new HdcRequestReferrals();
		Integer result = 0;
		final HdcGovernorDecisions hdcBoardDecisionsOld = hdcGovernorDecisionsT1Repository
				.getHdcGovernorDecisions(hdcGovernorDecisions);
		try {
			if (("INSERTING".equals(sqlOperation)
					&& Optional.ofNullable(hdcGovernorDecisions.getGovernorsDecision()).isPresent()
					|| Optional.ofNullable(hdcGovernorDecisions.getReferralTo()).isPresent())
					|| (("UPDATING".equals(sqlOperation)
							&& Optional.ofNullable(hdcGovernorDecisions.getGovernorsDecision()).isPresent()
							|| Optional.ofNullable(hdcGovernorDecisions.getReferralTo()).isPresent())
							&& ((hdcBoardDecisionsOld != null && hdcBoardDecisionsOld.getGovernorsDecision() == null
									|| hdcGovernorDecisions.getReferralTo() == null)
									|| hdcBoardDecisionsOld == null))) {
				final Long lNumber = hdcGovernorDecisionsT1Repository
						.getLNumber(hdcGovernorDecisions.getHdcRequestReferralId());
//			FOR UPDATE WAIT 1;
				hdcRequestReferr.setCreateDatetime(hdcGovernorDecisions.getCreateDatetime());
				hdcRequestReferr.setHdcRequestReferralId(hdcGovernorDecisions.getHdcRequestReferralId());
				result = hdcGovernorDecisionsT1Repository.update(hdcRequestReferr);
			}
		} catch (final Exception e) {
//			tag_error.raise_app_error( -20951,'Error: This resource is currently locked by another user.', TRUE );
			logger.info("Error: This resource is currently locked by another user.");
			logger.error("hdcGovernorDecisionsT1", e);
		}
		return result;
	}

}
