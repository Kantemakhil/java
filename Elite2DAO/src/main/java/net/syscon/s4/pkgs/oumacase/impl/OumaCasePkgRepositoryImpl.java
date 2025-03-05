package net.syscon.s4.pkgs.oumacase.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oumacase.OumaCasePkgRepository;

@Repository
public class OumaCasePkgRepositoryImpl extends RepositoryBase implements OumaCasePkgRepository {

	private static Logger logger = LogManager.getLogger(OumaCasePkgRepositoryImpl.class.getName());

	@Override
	public Caseloads caseloadType(final String pAgyLocId) {
		final String sql = getQuery("CASELOAD_TYPE_CASELOAD_CUR");
		Caseloads returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", pAgyLocId),
					new BeanPropertyRowMapper<Caseloads>(Caseloads.class));
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return returnVal;
	}
}
