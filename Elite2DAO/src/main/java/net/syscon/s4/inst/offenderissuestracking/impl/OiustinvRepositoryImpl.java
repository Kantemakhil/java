package net.syscon.s4.inst.offenderissuestracking.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.offenderissuestracking.OiustinvRepository;
import net.syscon.s4.inst.offenderissuestracking.beans.OffenderGrievStaffs;

/**
 * Class OiustinvRepositoryImpl
 */
@Repository
public class OiustinvRepositoryImpl extends RepositoryBase implements OiustinvRepository {
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiustinvRepositoryImpl.class.getName());

	/**
	 * Creates new OiustinvRepositoryImpl class Object
	 */
	public OiustinvRepositoryImpl() {
		
	}

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("USER_ID", new FieldMapper("userId")).put("LAST_NAME", new FieldMapper("lastName"))
			.put("STAFF_ID", new FieldMapper("staffId")).put("FIRST_NAME", new FieldMapper("firstName"))
			.put("CODE", new FieldMapper("code")).put("MIDDLE_NAME", new FieldMapper("middleName")).build();
	private final Map<String, FieldMapper> offenderGrievancesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GRIEVANCE_ID", new FieldMapper("grievanceId")).put("STAFF_ID", new FieldMapper("staffId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GRIEVANCE_ID", new FieldMapper("grievanceId")).put("'X'", new FieldMapper(" 'x' ")).build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderGrievStaffs
	 *
	 * @return List<OffenderGrievStaffs>
	 *
	 * @throws SQLException
	 */
	public List<OffenderGrievStaffs> offenderGrievStaffsExecuteQuery(final OffenderGrievStaffs objSearchDao) {
		final String sql = getQuery("OIUSTINV_OFFENDERGRIEVSTAFFS_FIND_OFFENDER_GRIEV_STAFFS");
		final RowMapper<OffenderGrievStaffs> offenderGrievStaffsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderGrievStaffs.class, offenderGrievancesMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("GRIEVANCE_ID", objSearchDao.getGrievanceId()),
				offenderGrievStaffsRowMapper);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderGrievStaffs
	 *            List<OffenderGrievStaffs>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievStaffsInsertOffenderGrievStaffs(
			final List<OffenderGrievStaffs> lstOffenderGrievStaffs) {
		final String sql = getQuery("OIUSTINV_OFFENDERGRIEVSTAFFS_INSERT_OFFENDER_GRIEV_STAFFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderGrievStaffs offenderGrievStaffs : lstOffenderGrievStaffs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievStaffs));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievStaffs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
		} catch (final Exception e) {
					logger.error("Exception :", e);
					return null;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderGrievStaffs
	 *            List<OffenderGrievStaffs>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievStaffsUpdateOffenderGrievStaffs(
			final List<OffenderGrievStaffs> lstOffenderGrievStaffs) {
		final String sql = getQuery("OIUSTINV_OFFENDERGRIEVSTAFFS_UPDATE_OFFENDER_GRIEV_STAFFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderGrievStaffs offenderGrievStaffs : lstOffenderGrievStaffs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievStaffs));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievStaffs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderGrievStaffs
	 *            List<OffenderGrievStaffs>
	 *
	 * @throws SQLException
	 */
	public Integer offenderGrievStaffsDeleteOffenderGrievStaffs(
			final List<OffenderGrievStaffs> lstOffenderGrievStaffs) {
		final String sql = getQuery("OIUSTINV_OFFENDERGRIEVSTAFFS_DELETE_OFFENDER_GRIEV_STAFFS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final OffenderGrievStaffs offenderGrievStaffs : lstOffenderGrievStaffs) {
			parameters.add(new BeanPropertySqlParameterSource(offenderGrievStaffs));
		}
		try {
			batchUpdatePreDeletedRows("OFFENDER_GRIEV_STAFFS", "GRIEVANCE_ID = :grievanceId AND STAFF_ID = :staffId", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in offenderGrievStaffsDeleteOffenderGrievStaffs"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderGrievStaffs.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgStaffRecordGroup() {
		final String sql = getQuery("OIUSTINV_FIND_RGSTAFF");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				staffMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (final Exception e) {
			logger.error("rgStaffRecordGroup :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offenderGrievStaffsPostQuery
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> offenderGrievStaffsPostQuery(final StaffMembers paramBean) {
		final String sql = getQuery("OIUSTINV_OFFENDER_GRIEV_STAFFS_POSTQUERY");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("STAFFID", paramBean.getStaffId()),
					columnRowMapper);
		} catch (final Exception e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobalsCREATE_FORM_GLOBALS
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIUSTINV_CREATE_FORM_GLOBALS_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (final Exception e) {
			logger.error("Exception :", e);
			return Collections.emptyList();
		}
	}

}
