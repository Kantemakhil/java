package net.syscon.s4.triggers;

public interface HdcGovernorDecisionsT1Repository {

	Integer update(HdcRequestReferrals hdcRequestReferr);

	Long getLNumber(Long hdcReqReferralId);

	HdcGovernorDecisions getHdcGovernorDecisions(HdcGovernorDecisions hdcGovernorDecis);

}
