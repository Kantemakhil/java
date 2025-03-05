package net.syscon.s4.pkgs.calculate_balances;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.syscon.s4.inst.legals.au.VCbSentTerms;
import net.syscon.s4.inst.legals.au.VOffBalCals;

public interface CalculateBalancesService {

	List<VCbSentTerms> setOffenderId(final VOffBalCals bean);

	Map<String, Object> getCalBalDtls(final Long pOffBalCalcId, final Date pAdmDate, final Date pReleaseDate);

}