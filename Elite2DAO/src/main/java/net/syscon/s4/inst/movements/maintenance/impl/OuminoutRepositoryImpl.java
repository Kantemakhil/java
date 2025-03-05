package net.syscon.s4.inst.movements.maintenance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.inst.movements.maintenance.OuminoutRepository;

@Repository
public class OuminoutRepositoryImpl extends RepositoryBase implements OuminoutRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuminoutRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> movementReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IN_MOVEMENT_REASON_CODE", new FieldMapper("inMovementReasonCode"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("MOVEMENT_TYPE", new FieldMapper("movementType"))
			.put("CREATE_DATETIME", new FieldMapper("createDateTime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDateTime"))
			.put("TRN_P_LIST_FLAG", new FieldMapper("trnpListFlag"))
			.build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("IN_MOVEMENT_TYPE", new FieldMapper("inMovementType"))
			.build();
	/**
	 * Creates new OuminoutRepositoryImpl class Object
	 */
	public OuminoutRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            MovementReasons
	 *
	 * @return List<MovementReasons>
	 *
	 * @throws SQLException
	 */
	public List<MovementReasons> moveRsnExecuteQuery(final MovementReasons objSearchDao) {
		final String sql = getQuery("OUMINOUT_MOVERSN_FIND_MOVEMENT_REASONS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();

		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);

		if (objSearchDao != null) {
			sqlQuery.append(" AND ");

			if (objSearchDao.getInMovementReasonCode() != null && !objSearchDao.getInMovementReasonCode().isEmpty()) {
				sqlQuery.append(" IN_MOVEMENT_REASON_CODE  = :inMovementReasonCode " + "AND ");
				params.addValue("inMovementReasonCode", objSearchDao.getInMovementReasonCode());
			}

			if (objSearchDao.getEscRecapFlag() != null && !objSearchDao.getEscRecapFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getEscRecapFlag())) {
					objSearchDao.setEscRecapFlag("Y");
				} else {
					objSearchDao.setEscRecapFlag("N");
				}
				sqlQuery.append(" ESC_RECAP_FLAG  = :escRecapFlag " + " AND ");
				params.addValue("escRecapFlag", objSearchDao.getEscRecapFlag());

			}

			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().isEmpty()) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag " + " AND ");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());

			}

			if (objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ  = :listSeq " + "AND ");
				params.addValue("listSeq", objSearchDao.getListSeq());

			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}

		final RowMapper<MovementReasons> MovementReasonsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				MovementReasons.class, movementReasonsMapping);

		return namedParameterJdbcTemplate.query(preparedSql, params, MovementReasonsRowMapper);
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstMovementReasons
	 *            List<MovementReasons>
	 *
	 * @throws SQLException
	 */
	public Integer moveRsnUpdateMovementReasons(final List<MovementReasons> listObj) {
		final String sql = getQuery("OUMINOUT_MOVERSN_UPDATE_MOVEMENT_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final MovementReasons bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstMovementReasons
	 *            List<MovementReasons>
	 *
	 * @throws SQLException
	 */
	public Integer moveRsnDeleteMovementReasons(final List<MovementReasons> listObj) {
		final String sql = getQuery("OUMINOUT_MOVERSN_DELETE_MOVEMENT_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MovementReasons bean : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(bean));
		}
		try {
			batchUpdatePreDeletedRows("MOVEMENT_REASONS", "MOVEMENT_TYPE = :movementType AND MOVEMENT_REASON_CODE = :movementReasonCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in moveRsnDeleteMovementReasons"+e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName() + " error in moveRsnDeleteMovementReasons " + e);
			if(e!=null && e.getMessage()!=null && e.getMessage().contains("off_em_move_rsn_f1")) {
				return 18;
			}
		}
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkMoveRsnInMovementReasRecordGroup() {
		final String sql = getQuery("OUMINOUT_FIND_CGFKMOVERSNINMOVEMENTREAS");
		final RowMapper<ReferenceCodes> MRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), MRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("Exception in cgfkMoveRsnInMovementReasRecordGroup: Ouminout:", e);
			return Collections.emptyList();
		}
	}

	/**
	 * 
	 * 
	 * checking validation before deletion
	 */
	@Override
	public Integer cgrichkMovementReasonsMovements(final MovementReasons movementReasons) {
		final String sql = getQuery("OUMINOUT_CGRICHK_MOVEMENT_REASONS_MOVEMENTS");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("movementType",
				movementReasons.getMovementType(), "movementReasonCode", movementReasons.getMovementReasonCode()),
				Integer.class);
		return returnValue;
	}

	/**
	 * 
	 * checking validation before deletion
	 * 
	 */
	@Override
	public Integer cgrichkMovementReasonsBooking(final MovementReasons movementReasons) {
		final String sql = getQuery("OUMINOUT_CGRICHK_MOVEMENT_REASONSS_BOOKING_EVENTS");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("movementType",
				movementReasons.getMovementType(), "movementReasonCode", movementReasons.getMovementReasonCode()),
				Integer.class);
		return returnValue;
	}

}
