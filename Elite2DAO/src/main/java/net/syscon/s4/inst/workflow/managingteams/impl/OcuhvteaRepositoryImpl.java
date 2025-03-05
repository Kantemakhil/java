package net.syscon.s4.inst.workflow.managingteams.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.workflow.managingteams.OcuhvteaRepository;
import net.syscon.s4.inst.workflow.managingteams.beans.VOffenderTeamAssignHty;

/**
 * Class OcuhvteaRepositoryImpl
 * 
 */
@Repository
public class OcuhvteaRepositoryImpl extends RepositoryBase implements OcuhvteaRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcuhvteaRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> vOffenderTeamAssignHtyMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("TEAM_DESCRIPTION", new FieldMapper("teamDescription"))
			.put("ASSIGN_DATE", new FieldMapper("assignDate"))
			.put("TEAM_ID", new FieldMapper("teamId"))
			.put("FUNCTION_TYPE", new FieldMapper("functionType"))
			.put("TEAM_CODE", new FieldMapper("teamCode"))
			.put("FUNCTION_TYPE_DESC", new FieldMapper("functionTypeDesc"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("OFFENDER_TEAM_ASSIGN_HTY_ID", new FieldMapper("offenderTeamAssignHtyId")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE" , new FieldMapper("code"))
			.put("LIST_SEQ" , new FieldMapper("listSeq"))
			.build();

	/**
	 * Creates new OcuhvteaRepositoryImpl class Object
	 */
	public OcuhvteaRepositoryImpl() {
		// constructor
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderTeamAssignHty
	 *
	 * @return List<VOffenderTeamAssignHty>
	 *
	 * @throws SQLException
	 */
	public List<VOffenderTeamAssignHty> offVteamHtyExecuteQuery(final VOffenderTeamAssignHty objSearchDao) {
		final String sql = getQuery("OCUHVTEA_OFFVTEAMHTY_FIND_V_OFFENDER_TEAM_ASSIGN_HTY");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getOffenderBookId() != null) {
				pSql.append(" offender_book_id = :offenderBookId  AND ");
				param.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getFunctionType() != null && !objSearchDao.getFunctionType().isEmpty()) {
				pSql.append(" function_type LIKE :functionType AND ");
				param.addValue("functionType", objSearchDao.getFunctionType());
			}
			if (objSearchDao.getTeamCode() != null && !objSearchDao.getTeamCode().isEmpty()
					&& !objSearchDao.getTeamCode().trim().equals("")) {
				pSql.append(" team_code LIKE :teamCode AND ");
				param.addValue("teamCode", objSearchDao.getTeamCode().trim());
			}

			if (objSearchDao.getExpiryDate() != null) {
				pSql.append("(TRUNC(expiry_date)  LIKE to_date('"
						+ new java.sql.Date(objSearchDao.getExpiryDate().getTime()) + "','yyyy/MM/dd'))" + " AND ");
			}
			if (objSearchDao.getAssignDate() != null) {
				pSql.append("(TRUNC(assign_date)  LIKE to_date('"
						+ new java.sql.Date(objSearchDao.getAssignDate().getTime()) + "','yyyy/MM/dd'))" + " AND ");
			}

		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by function_type_desc, assign_date";
		final RowMapper<VOffenderTeamAssignHty> vOffendrRowMaper = Row2BeanRowMapper.makeMapping(preparedSql,
				VOffenderTeamAssignHty.class, vOffenderTeamAssignHtyMapping);
		List<VOffenderTeamAssignHty> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, vOffendrRowMaper);
		} catch (Exception e) {
			logger.error("offVteamHtyExecuteQuery :ocuhvtea" + e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgFunctionRecordGroup() {
		final String sql = getQuery("OCUHVTEA_FIND_RGFUNCTION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgFunctionRecordGroup :ocuhvtea" + e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offBkgOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<VOffenderTeamAssignHty> offBkgOnCheckDeleteMaster(final VOffenderTeamAssignHty paramBean) {
		final String sql = getQuery("OCUHVTEA_OFF_BKG_ONCHECKDELETEMASTER");
		final RowMapper<VOffenderTeamAssignHty> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderTeamAssignHty.class, vOffenderTeamAssignHtyMapping);
		return (ArrayList<VOffenderTeamAssignHty>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
	}

}
