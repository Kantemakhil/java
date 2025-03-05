package net.syscon.s4.pkgs.comunity_pkg.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.comunity_pkg.ComunityPkgRepository;

@Repository
public class ComunityPkgRepositoryImpl extends RepositoryBase implements ComunityPkgRepository {

	private static Logger logger = LogManager.getLogger(ComunityPkgRepositoryImpl.class.getName());

	@Override
	public Long poNumCur(final String pAgyLocId, final Long pStaffId, final String pPosition, final String pRole,
			final Date pFromDate) {
		final String sql = getQuery("PO_NUM_CUR");
		Long count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", pAgyLocId,
					"p_from_date", pFromDate, "p_position", pPosition, "p_role", pRole, "p_staff_id", pStaffId),
					Long.class);
		} catch (Exception e) {
			logger.error("poNumCur :", e);
			count = 0l;
		}
		return count;

	}

	@Override
	public Integer curPrimaryOwnPerOfficer(final Integer staffId) {
		final String sql = getQuery("CUR_PRIMARY_OWN_PER_OFFICER");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_STAFF_ID", staffId), Integer.class);

		} catch (Exception e) {
			logger.error("curPrimaryOwnPerOfficer :", e);
		}
		return count;
	}

}