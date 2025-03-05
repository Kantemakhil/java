package net.syscon.s4.inst.workflow.managingworkassignments.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.workflow.managingworkassignments.OumstafcRepository;
import net.syscon.s4.sa.usersystemsecurity.beans.StaffSkills;

/**
 * Class OumstafcRepositoryImpl
 */
@Repository
public class OumstafcRepositoryImpl extends RepositoryBase implements OumstafcRepository {
	private static final String SKILL_TYPE = "skillType";
	private static final String DESCRIPTION = "description";
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OumstafcRepositoryImpl.class.getName());

	/**
	 * Creates new OumstafcRepositoryImpl class Object
	 */
	public OumstafcRepositoryImpl() {
	}

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SUB_TYPE", new FieldMapper("code")).put("DSP_DESCRIPTION3", new FieldMapper(DESCRIPTION)).build();
	private final Map<String, FieldMapper> rgStaffSkillMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SKILL_TYPE", new FieldMapper("code")).put("DSP_DESCRIPTION4", new FieldMapper(DESCRIPTION)).build();
	private final Map<String, FieldMapper> programServicesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROG_ID", new FieldMapper("programId")).put("PROG_DESC", new FieldMapper(DESCRIPTION))
			.put("PROG_CODE", new FieldMapper("programCode")).build();
	private final Map<String, FieldMapper> staffSkillsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAFF_ID", new FieldMapper("staffId")).put("SKILL_TYPE", new FieldMapper(SKILL_TYPE))
			.put("SUB_TYPE", new FieldMapper("subType")).put("AS_OF_DATE", new FieldMapper("asOfDate"))
			.put("STSK_COMMENT", new FieldMapper("stskComment"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("PROGRAM_ID", new FieldMapper("programId"))
			.put("STAFF_SKILL_ID", new FieldMapper("staffSkillId")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate")).put("SEAL_FLAG", new FieldMapper("sealFlag")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            StaffSkills
	 *
	 * @return List<StaffSkills>
	 *
	 */
	public List<StaffSkills> stskExecuteQuery(final StaffSkills objSearchDao) {
		final String sql = getQuery("OUMSTAFC_STSK_FIND_STAFF_SKILLS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE");
			if (objSearchDao.getSkillType() != null && !objSearchDao.getSkillType().trim().equals("")) {
				sqlQuery.append(" SKILL_TYPE = :skillType and");
				params.addValue(SKILL_TYPE, objSearchDao.getSkillType());
			}
			if (objSearchDao.getSubType() != null && !objSearchDao.getSubType().trim().equals("")) {
				sqlQuery.append(" SUB_TYPE = :subType and");
				params.addValue("subType", objSearchDao.getSubType());
			}
			if (objSearchDao.getProgramId() != null ) {
				sqlQuery.append(" PROGRAM_ID = :progId and");
				params.addValue("progId", objSearchDao.getProgramId());
			}
			if (objSearchDao.getStskComment() != null && !objSearchDao.getStskComment().trim().equals("")) {
				sqlQuery.append(" STSK_COMMENT = :stskComment and");
				params.addValue("stskComment", objSearchDao.getStskComment());
			}
			if (objSearchDao.getAsOfDate() != null) {
				sqlQuery.append(" AS_OF_DATE = :asOfDate and");
				params.addValue("asOfDate", objSearchDao.getAsOfDate());
			}
			if (objSearchDao.getActiveFlag() != null && !objSearchDao.getActiveFlag().trim().equals("")) {
				sqlQuery.append(" ACTIVE_FLAG = :activeFlag and");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE = :expiryDate and");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
			sqlQuery.append(" STAFF_ID = :staffId");
			params.addValue("staffId", objSearchDao.getStaffId());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<StaffSkills> staffSkillsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				StaffSkills.class, staffSkillsMapping);

		try {
			return namedParameterJdbcTemplate.query(preparedSql, params, staffSkillsRowMapper);
		} catch (final Exception e) {
			logger.error("stskExecuteQuery", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstStaffSkills
	 *            List<StaffSkills>
	 *
	 *
	 */
	public Integer stskInsertStaffSkills(final List<StaffSkills> lstStaffSkills) {
		final String sql = getQuery("OUMSTAFC_STSK_INSERT_STAFF_SKILLS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final StaffSkills staffSkills : lstStaffSkills) {
			parameters.add(new BeanPropertySqlParameterSource(staffSkills));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstStaffSkills
	 *            List<StaffSkills>
	 *
	 */
	public Integer stskUpdateStaffSkills(final List<StaffSkills> lstStaffSkills) {
		final String sql = getQuery("OUMSTAFC_STSK_UPDATE_STAFF_SKILLS");
		int[] returnArray;
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final StaffSkills staffSkills : lstStaffSkills) {
			if (staffSkills != null) {
				parameters.add(new BeanPropertySqlParameterSource(staffSkills));
			}
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffSkills.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgStaffSkillRecordGroup() {
		final String sql = getQuery("OUMSTAFC_FIND_RGSTAFFSKILL");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				rgStaffSkillMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgStaffSkillRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ProgramServices>
	 */
	public List<ProgramServices> rgProgramRecordGroup() {
		final String sql = getQuery("OUMSTAFC_FIND_RGPROGRAM");
		final RowMapper<ProgramServices> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				programServicesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgProgramRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgSubTypeRecordGroup(final String skillType) {
		final String sql = getQuery("OUMSTAFC_FIND_RGSUBTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(SKILL_TYPE, skillType), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgSubTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture next STAFF SKILL ID value from select query
	 * 
	 * @return Long
	 */
	@Override
	public Long oumstafcStaffSkillIdSeq() {
		final String sql = getQuery("OUMSTAFC_STAFF_SKILL_ID_NEXT_VAL");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (final Exception e) {
			logger.error("oumstafcStaffSkillIdSeq", e);
			return 0l;
		}
	}

}
