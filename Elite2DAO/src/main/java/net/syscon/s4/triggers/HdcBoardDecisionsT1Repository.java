package net.syscon.s4.triggers;

public interface HdcBoardDecisionsT1Repository {
	HdcBoardDecisions getHdcBoardDecisions(HdcBoardDecisions hdcBoardDecisions);

	Long getLNumber(Long hdcReqReferralId);

	Integer update(HdcBoardDecisions hdcBoardDecisions);
}
