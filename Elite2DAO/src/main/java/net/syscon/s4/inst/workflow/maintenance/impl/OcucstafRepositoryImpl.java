package net.syscon.s4.inst.workflow.maintenance.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Areas;
import net.syscon.s4.im.beans.ReferenceCodes;
import net.syscon.s4.im.beans.TagSearchGetPartialRecords;
import net.syscon.s4.inst.legals.beans.StaffDetails;
import net.syscon.s4.inst.legalscreens.maintenance.bean.HoCodes;
import net.syscon.s4.inst.workflow.maintenance.OcucstafRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OcucstafRepositoryImpl
 * 
 * 
 */
@Repository
public class OcucstafRepositoryImpl extends RepositoryBase implements OcucstafRepository {

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("DISTINCT", new FieldMapper("distinct"))
			.build();
	private final Map<String, FieldMapper> staffMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("last_name", new FieldMapper("lastName"))
			.put("first_name", new FieldMapper("firstName"))
			.put("position", new FieldMapper("position"))
			.put("role", new FieldMapper("role"))
			.put("from_date", new FieldMapper("fromDate"))
			.put("to_date", new FieldMapper("toDate"))
			.put("staff_id", new FieldMapper("staffId"))
			.build();

	/**
	 * Creates new OcucstafRepositoryImpl class Object
	 */
	public OcucstafRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucstafRepositoryImpl.class);

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OcucstafGetStaffDetails
	 *
	 * @return List<OcucstafGetStaffDetails>
	 *
	 * @throws SQLException
	 */
	public List<StaffDetails> staffDetailsExecuteQuery(final StaffDetails objSearchDao) {
		final String sql = getQuery("OCUCSTAF_FIND_DETAILS");
		final MapSqlParameterSource param = new MapSqlParameterSource();
		
		final RowMapper<StaffDetails> HoCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffDetails.class,
				staffMapping);
		ArrayList<StaffDetails> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<StaffDetails>) namedParameterJdbcTemplate.query(sql, param,
					HoCodesRowMapper);
		} catch (Exception e) {
			logger.error("staffDetailsExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgAgencyTypeRecordGroup() {
		final String sql = getQuery("OCUCSTAF_FIND_RGAGENCYTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAgencyTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<Areas> rgAreaRecordGroup(final String areaType) {
		final String sql = getQuery("OCUCSTAF_FIND_RGAREA");
		final RowMapper<Areas> mRowMapper = Row2BeanRowMapper.makeMapping(sql, Areas.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("areaType", areaType), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgAreaRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgStaffStatusRecordGroup() {
		final String sql = getQuery("OCUCSTAF_FIND_RGSTAFFSTATUS");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgStaffStatusRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<AgencyLocations> rgLocationRecordGroup(final String areaCode,final String areaType) {
		final String sql = getQuery("OCUCSTAF_FIND_RGLOCATION");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("areaCode", areaCode, "areaType", areaType),
					mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgLocationRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgRoleRecordGroup() {
		final String sql = getQuery("OCUCSTAF_FIND_RGROLE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgRoleRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgPositionRecordGroup() {
		final String sql = getQuery("OCUCSTAF_FIND_RGPOSITION");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("rgPositionRecordGroup", e);
			return Collections.emptyList();
		}
	}

}
