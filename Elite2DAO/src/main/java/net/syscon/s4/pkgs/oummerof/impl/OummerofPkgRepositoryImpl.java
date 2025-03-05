package net.syscon.s4.pkgs.oummerof.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oummerof.OummerofPkgRepository;

@Repository
public class OummerofPkgRepositoryImpl extends RepositoryBase implements OummerofPkgRepository {

	@Override
	public Integer getActiveRecordCount(BigDecimal rootOffenderId) {
		final String sql = getQuery("OUMMEROF_GET_ROOT_OFFENDER_ID_RECORD_COUNT");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("rootOffenderId",
				rootOffenderId), Integer.class);
		return returnList;
	}

}
