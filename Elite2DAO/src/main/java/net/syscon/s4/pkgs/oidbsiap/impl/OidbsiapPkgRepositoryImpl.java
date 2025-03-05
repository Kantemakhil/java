package net.syscon.s4.pkgs.oidbsiap.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oidbsiap.OidbsiapPkgRepository;

@Repository
public class OidbsiapPkgRepositoryImpl extends RepositoryBase implements OidbsiapPkgRepository {

	@Override
	public String getDesc(final BigDecimal offBkgId,String userName) {
		final String sql = getQuery("DESCP_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_bkg_id", offBkgId,"userId",userName),
				String.class);
	}
}