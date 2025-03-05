package net.syscon.s4.inst.movements.proposedmovements.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.LivingUnits;
import net.syscon.s4.inst.casemanagement.beans.InternalScheduleReasons;
import net.syscon.s4.inst.movements.proposedmovements.OidphuncRepository;
import net.syscon.s4.inst.movements.proposedmovements.beans.OffenderProposedIntlocs;
/**
 * class  OidphuncRepositoryImpl
 */
@Repository
public class OidphuncRepositoryImpl extends RepositoryBase implements OidphuncRepository {

	private static Logger logger = LogManager.getLogger(OidphuncRepositoryImpl.class.getName());

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderProposedIntlocs
	 *
	 * @return List<OffenderProposedIntlocs>
	 *
	 * @throws SQLException
	 */
	public List<OffenderProposedIntlocs> propMoveExecuteQuery(OffenderProposedIntlocs objSearchDao) {
		final String sql = getQuery("OIDPHUNC_PROPMOVE_FIND_OFFENDER_PROPOSED_INTLOCS");
		ArrayList<OffenderProposedIntlocs> returnList = null;
		try {
			returnList = (ArrayList<OffenderProposedIntlocs>) namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", objSearchDao.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderProposedIntlocs>(OffenderProposedIntlocs.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " propMoveExecuteQuery in error ");
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderProposedIntlocs List<OffenderProposedIntlocs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer propMoveInsertOffenderProposedIntlocs(
			final List<OffenderProposedIntlocs> lstOffenderProposedIntlocs) {
		String sql = getQuery("OIDPHUNC_PROPMOVE_INSERT_OFFENDERPROPOSEDINTLOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (OffenderProposedIntlocs offenderProposedIntlocs : lstOffenderProposedIntlocs) {
				parameters.add(new BeanPropertySqlParameterSource(offenderProposedIntlocs));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " propMoveInsertOffenderProposedIntlocs in error ");
		}
		if (lstOffenderProposedIntlocs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderProposedIntlocs List<OffenderProposedIntlocs>
	 *
	 * @throws SQLException
	 */
	public Integer propMoveUpdateOffenderProposedIntlocs(
			final List<OffenderProposedIntlocs> lstOffenderProposedIntlocs) {
		String sql = getQuery("OIDPHUNC_PROPMOVE_UPDATE_OFFENDERPROPOSEDINTLOCS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		try {
			for (OffenderProposedIntlocs offenderProposedIntlocs : lstOffenderProposedIntlocs) {
				parameters.add(new BeanPropertySqlParameterSource(offenderProposedIntlocs));
			}
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " propMoveUpdateOffenderProposedIntlocs in error ");
		}
		if (lstOffenderProposedIntlocs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgFromLivUnitRecordGroup() {
		final String sql = getQuery("OIDPHUNC_FIND_RGFROMLIVUNIT");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLevelOneRecordGroup(String agyLocId) {
		final String sql = getQuery("OIDPHUNC_FIND_RGLEVEL_ONE");
		List<LivingUnits> recordList = new ArrayList<LivingUnits>();
		try {
			recordList = namedParameterJdbcTemplate.query(sql, createParams("agy_loc_id", agyLocId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return recordList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLevelTwoRecordGroup(BigDecimal levelOneId) {
		final String sql = getQuery("OIDPHUNC_FIND_RGLEVEL_TWO");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("LEVEL_ONE_ID",levelOneId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLevelThreeRecordGroup(BigDecimal levelTwoId) {
		final String sql = getQuery("OIDPHUNC_FIND_RGLEVEL_THREE");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("LEVEL_TWO_ID",levelTwoId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<LivingUnits>
	 */
	public List<LivingUnits> rgLevelFourRecordGroup(BigDecimal levelThreeId) {
		final String sql = getQuery("OIDPHUNC_FIND_RGLEVEL_FOUR");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("LEVEL_THREE_ID",levelThreeId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	
	
	@Override
	public List<InternalScheduleReasons> rgTypeLivUnitRecordGroup() {
		final String sql = getQuery("OIDPHUNC_FIND_RGLEVEL_TYPE");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<InternalScheduleReasons>(InternalScheduleReasons.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<InternalScheduleReasons> rgReasonLivUnitRecordGroup(String internalScheduleType) {
		final String sql = getQuery("OIDPHUNC_FIND_RGLEVEL_REASON");

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MOVEMENT_TYPE" ,internalScheduleType ),
					new BeanPropertyRowMapper<InternalScheduleReasons>(InternalScheduleReasons.class));
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
	

	@Override
	public List<LivingUnits> getNodesCur(Long toLivingUnitId) {
		final String sql = getQuery("OIDPHUNC_GET_NODES_CUR");
		ArrayList<LivingUnits> returnList = new ArrayList<LivingUnits>();
		try {
			returnList = (ArrayList<LivingUnits>) namedParameterJdbcTemplate.query(sql,
					createParams("to_living_unit_id", toLivingUnitId),
					new BeanPropertyRowMapper<LivingUnits>(LivingUnits.class));
		} catch (DataAccessException e) {
			logger.error(this.getClass().getName() + " propMoveExecuteQuery in error ");
		}
		return returnList;
	}

	

}
