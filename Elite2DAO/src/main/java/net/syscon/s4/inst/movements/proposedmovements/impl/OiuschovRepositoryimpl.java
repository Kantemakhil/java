package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.proposedmovements.OiuschovRepository;
import net.syscon.s4.inst.movements.proposedmovements.beans.VOffSchOverview;
/**
 * class  OiuschovRepositoryimpl
 */
@Repository
public class OiuschovRepositoryimpl extends RepositoryBase implements OiuschovRepository {

	private static Logger logger = LogManager.getLogger(OiuschovRepositoryimpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param searchRecord vOffSchOverview
	 *
	 * @return List<vOffSchOverview>
	 *
	 * @throws SQLException
	 */
	@Override
	public List<VOffSchOverview> vOffSchOverviewExecQuery(VOffSchOverview searchRecord) {
		String sql = getQuery("OIUSCHOV_VOFFSCH_OVERVIEW_DATA");
		if (searchRecord.getModuleName() != null && ("OIDHOUST".equals(searchRecord.getModuleName())
				|| "OIDINPLI".equals(searchRecord.getModuleName()))) {
			sql = sql + "  AND  int_ext in ('INTERNAL','HEARING')  ";
		} else if (searchRecord.getModuleName() != null && ("OIDEXTST".equals(searchRecord.getModuleName())
				|| "OIDEXPLI".equals(searchRecord.getModuleName()))) {
			sql = sql + "  AND  int_ext in ('INTERNAL','EXTERNAL','HEARING') ";
		}
		sql = sql + " order by event_date asc ";
		final ArrayList<VOffSchOverview> returnList = (ArrayList<VOffSchOverview>) namedParameterJdbcTemplate.query(sql,
				createParams("offender_book_id", searchRecord.getOffenderBookId()),
				new BeanPropertyRowMapper<VOffSchOverview>(VOffSchOverview.class));
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param movementReason vOffSchOverview
	 *
	 * @return List<vOffSchOverview>
	 *
	 * @throws SQLException
	 */
	@Override
	public String moveTypeCur(String movementReason) {
		String moveTypeCur = null;
		final String sql = getQuery("OIUSCHOV_MOVETYPE_CUR");
		try {
			moveTypeCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("movement_reason", movementReason), String.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " moveTypeCur() ", e);
		}
		return moveTypeCur;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param movementReason vOffSchOverview
	 *
	 * @return List<vOffSchOverview>
	 *
	 * @throws SQLException
	 */
	@Override
	public String moveReasonCur(String movementReason) {
		String moveReasonCur = null;
		final String sql = getQuery("OIUSCHOV_MOVEREASON_CUR");
		try {
			moveReasonCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("movement_reason", movementReason), String.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " moveReasonCur() ", e);
		}
		return moveReasonCur;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param movementReason vOffSchOverview
	 *
	 * @return List<vOffSchOverview>
	 *
	 * @throws SQLException
	 */
	@Override
	public String moveReasonIntCur(String movementReason) {
		String moveReasonIntCur = null;
		final String sql = getQuery("OIUSCHOV_MOVEREASONINT_CUR");
		try {
			moveReasonIntCur = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("movement_reason", movementReason), String.class);

		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " moveReasonIntCur() ", e);
		}
		return moveReasonIntCur;
	}

}
