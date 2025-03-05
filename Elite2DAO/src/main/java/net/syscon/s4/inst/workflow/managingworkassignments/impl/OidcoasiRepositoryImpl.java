package net.syscon.s4.inst.workflow.managingworkassignments.impl;

import java.sql.Types;
import java.text.SimpleDateFormat;
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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.OidcoasiOffenderAssignments;
import net.syscon.s4.inst.workflow.managingworkassignments.OidcoasiRepository;
import oracle.jdbc.OracleTypes;

/**
 * Class OidcoasiRepositoryImpl
 */
@Repository
public class OidcoasiRepositoryImpl extends RepositoryBase implements OidcoasiRepository {

	/**
	 * Creates new OidcoasiRepositoryImpl class Object
	 */
	public OidcoasiRepositoryImpl() {
		/*
		 * OidcoasiRepositoryImpl
		 */
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidcoasiRepositoryImpl.class);
	private final Map<String, FieldMapper> mmMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LASTNAME", new FieldMapper("lastName")).put("FIRSTNAME", new FieldMapper("firstName"))
			.put("STAFFID", new FieldMapper("staffId")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code")).put("DESCRIPTION", new FieldMapper("description")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OidcoasiOffenderAssignmentsQuery
	 *
	 * @return List<OidcoasiOffenderAssignmentsQuery>
	 *
	 */
	public List<OidcoasiOffenderAssignments> offAsgnExecuteQuery(final OidcoasiOffenderAssignments objSearchDao) {
		Map<String, Object> returnObject = null;
		final List<OidcoasiOffenderAssignments> lListObj = new ArrayList<>();
		final Map<String, Object> inParamMap = new HashMap<>();
		SqlParameter[] sqlParameters;
		sqlParameters = new SqlParameter[] { new SqlOutParameter("P_RESULT_SET", OracleTypes.CURSOR),
				new SqlParameter("P_AGY_LOC_ID", Types.VARCHAR), new SqlParameter("P_LIVING_UNIT_CODE1", Types.VARCHAR),
				new SqlParameter("P_LIVING_UNIT_CODE2", Types.VARCHAR),
				new SqlParameter("P_LIVING_UNIT_CODE3", Types.VARCHAR),
				new SqlParameter("P_LIVING_UNIT_CODE4", Types.VARCHAR),
				new SqlParameter("P_CURRENT_OFFICER_ID", Types.VARCHAR),
				new SqlParameter("P_UNASSIGNED_FLAG", Types.VARCHAR),
				new SqlParameter("P_OFFENDER_ID_DISPLAY", Types.VARCHAR),
				new SqlParameter("P_CASE_OFFICER_ID", Types.VARCHAR), new SqlParameter("P_CONFIRM_FLAG", Types.VARCHAR),
				new SqlParameter("P_OFF_LAST_NAME", Types.VARCHAR),
				new SqlParameter("P_OFF_FIRST_NAME", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OIDCOASI").withProcedureName("OFFENDER_ASSIGNMENTS_QUERY")
				.declareParameters(sqlParameters);
		inParamMap.put("P_AGY_LOC_ID", objSearchDao.getAgyLocId());
		inParamMap.put("P_LIVING_UNIT_CODE1", objSearchDao.getLivingUnitCodeOne());
		inParamMap.put("P_LIVING_UNIT_CODE2", objSearchDao.getLivingUnitCodeTwo());
		inParamMap.put("P_LIVING_UNIT_CODE3", objSearchDao.getLivingUnitCodethree());
		inParamMap.put("P_LIVING_UNIT_CODE4", objSearchDao.getLivingUnitCodeFour());
		inParamMap.put("P_CURRENT_OFFICER_ID", objSearchDao.getCurrentOfficerStaffId());
		inParamMap.put("P_UNASSIGNED_FLAG", objSearchDao.getUnassignedFlag());
		inParamMap.put("P_OFFENDER_ID_DISPLAY", objSearchDao.getOffenderId());
		inParamMap.put("P_CASE_OFFICER_ID", objSearchDao.getCurrentOfficerStaffId());
		inParamMap.put("P_CONFIRM_FLAG", objSearchDao.getConfirmationAllFlag());
		inParamMap.put("P_OFF_LAST_NAME", objSearchDao.getLastName());
		inParamMap.put("P_OFF_FIRST_NAME", objSearchDao.getFirstName());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.withoutProcedureColumnMetaDataAccess().execute(inParameter);
			final List<Map<String, Object>> list = (List<Map<String, Object>>) returnObject.get("P_RESULT_SET");
			final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");

			for (int i = 0; i < list.size(); i++) {
				final Map<String, Object> childMap = list.get(i);
				final OidcoasiOffenderAssignments bean = new OidcoasiOffenderAssignments();
				if (childMap.get("OFFENDER_ID_DISPLAY") != null) {
					bean.setOffenderId(childMap.get("OFFENDER_ID_DISPLAY").toString());
				}
				if (childMap.get("LAST_NAME") != null) {
					bean.setLastName(childMap.get("LAST_NAME").toString());
				}
				if (childMap.get("FIRST_NAME") != null) {
					bean.setFirstName(childMap.get("FIRST_NAME").toString());
				}
				if (childMap.get("OFFENDER_BOOK_ID") != null) {
					bean.setOffenderBookingId(Long.parseLong(String.valueOf(childMap.get("OFFENDER_BOOK_ID"))));
				}
				if (childMap.get("DESCRIPTION") != null) {
					bean.setAgyLocDescription(childMap.get("DESCRIPTION").toString());
				}
				if (childMap.get("CASE_OFFICER_ID") != null) {
					bean.setCaseOfficerId(Integer.valueOf(String.valueOf(childMap.get("CASE_OFFICER_ID"))));
				}
				if (childMap.get("STAFF_LAST_NAME") != null) {
					bean.setStaffLastName(childMap.get("STAFF_LAST_NAME").toString());
				}
				if (childMap.get("STAFF_FIRST_NAME") != null) {
					bean.setStaffFirstName(childMap.get("STAFF_FIRST_NAME").toString());
				}
				if (childMap.get("CASE_ASSIGNED_DATE") != null) {
					bean.setCaseAssignedDate(dateFormat.parse(childMap.get("CASE_ASSIGNED_DATE").toString()));
				}
				if (childMap.get("CASE_ASSIGNED_TIME") != null) {
					bean.setCaseAssignedTime(dateFormat.parse(childMap.get("CASE_ASSIGNED_TIME").toString()));
				}
				if (childMap.get("CONFIRM_FLAG") != null) {
					bean.setConfirmationFlag(childMap.get("CONFIRM_FLAG").toString());
				}
				if (childMap.get("CASE_AGY_LOC_ID") != null) {
					bean.setAgyLocId(childMap.get("CASE_AGY_LOC_ID").toString());
				}
				lListObj.add(bean);
			}
		} catch (final Exception e) {
			logger.error(objSearchDao, e);
		}
		return lListObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgAgyLocIdRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDCOASI_FIND_RGAGYLOCID");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("CASELOADID", caseloadId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgLivingUnitCodeOneRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDCOASI_FIND_RGLIVINGUNITCODEONE");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgLivingUnitCodeTwoRecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIDCOASI_FIND_RGLIVINGUNITCODETWO");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVINGUNITID", livingUnitId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgLivingUnitCodeThreeRecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIDCOASI_FIND_RGLIVINGUNITCODETHREE");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVINGUNITID", livingUnitId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<AgencyLocations> rgLivingUnitCodeFourRecordGroup(final String agyLocId, final String livingUnitId) {
		final String sql = getQuery("OIDCOASI_FIND_RGLIVINGUNITCODEFOUR");
		final RowMapper<AgencyLocations> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AgencyLocations.class,
				mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId, "LIVINGUNITID", livingUnitId), mRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<MM>
	 */
	public List<StaffMembers> rgStaffIdRecordGroup(final String agyLocId) {
		final String sql = getQuery("OIDCOASI_FIND_RGSTAFFID");
		final RowMapper<StaffMembers> mMRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class, mmMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("AGYLOCID", agyLocId), mMRowMapper);
		} catch (final EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public int getOffenderCaseOfficersCount(final OidcoasiOffenderAssignments offenderAssignments) {
		final String sql = getQuery("OIDCOASI_GET_OFFENDER_CASE_OFFICER");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("P_OFFENDER_BOOK_ID", offenderAssignments.getOffenderBookingId(), "P_CASE_OFFICER_ID",
						offenderAssignments.getCaseOfficerId(), "P_CASE_ASSIGNED_TIME",
						offenderAssignments.getCaseAssignedTime(), "P_CASE_ASSIGNED_DATE",
						offenderAssignments.getCaseAssignedDate()),
				Integer.class);
	}

	@Override
	public int deleteOffenderCaseOfficers(final Long offenderBookingId,String modifyUserId) {
		final String sql = getQuery("OIDCOASI_DELETE_OFFENDER_CASE_OFFICER");
		try {
			String tableName = "OFFENDER_CASE_OFFICERS";
			String whereCondition = "OFFENDER_BOOK_ID = :offenderBookId AND to_date(to_char(CASE_ASSIGNED_DATE, 'YYYY/MM/DD'), 'YYYY/MM/DD')  >= current_date";
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("offenderBookId", offenderBookingId);
			inputMap.put("modifyUserId", modifyUserId);
			updatePreDeletedRow(tableName, whereCondition, inputMap);
		} catch (Exception e) {
			logger.error(e);
		}
		return namedParameterJdbcTemplate.update(sql, createParams("offenderBookId", offenderBookingId));

	}

	@Override
	public int insertOffenderCaseOfficers(final OidcoasiOffenderAssignments offenderAssignments) {
		final String sql = getQuery("OIDCOASI_INSERT_OFFENDER_CASE_OFFICER");
		return namedParameterJdbcTemplate.update(sql,
				createParams("CASE_OFFICER_ID", offenderAssignments.getCaseOfficerId(), "CASE_ASSIGNED_DATE",
						offenderAssignments.getCaseAssignedDate(), "CASE_ASSIGNED_TIME",
						offenderAssignments.getCaseAssignedTime(), "CASE_AGY_LOC_ID", offenderAssignments.getAgyLocId(),
						"OFFENDER_BOOK_ID", offenderAssignments.getOffenderBookingId(), "USERID", offenderAssignments.getUser()));
	}

}
