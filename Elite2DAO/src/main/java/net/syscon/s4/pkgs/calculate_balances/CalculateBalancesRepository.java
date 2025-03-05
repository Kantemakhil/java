package net.syscon.s4.pkgs.calculate_balances;

import java.util.Date;

import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.OffBalanceCalcCustodyDtl;

public interface CalculateBalancesRepository {

	OffBalanceCalcCustodyDtl lvCalcBalCur(Long pOffBalCalcId, Date pAdmDate, Date pReleaseDate);
	
}