package net.syscon.s4.pkgs.calculate_balances.impl;

import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legalscreens.sentenceadministration.beans.OffBalanceCalcCustodyDtl;
import net.syscon.s4.pkgs.calculate_balances.CalculateBalancesRepository;

@Repository
public class CalculateBalancesRepositoryImpl extends RepositoryBase implements CalculateBalancesRepository {

	@Override
	public OffBalanceCalcCustodyDtl lvCalcBalCur(final Long pOffBalCalcId, final Date pAdmDate,
			final Date pReleaseDate) {
		final String sql = getQuery("LV_CALC_BAL_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFF_BAL_CALC_ID", pOffBalCalcId, "P_ADM_DATE", pAdmDate, "P_RELEASE_DATE",
						pReleaseDate),
				new BeanPropertyRowMapper<OffBalanceCalcCustodyDtl>(OffBalanceCalcCustodyDtl.class));
	}

}
