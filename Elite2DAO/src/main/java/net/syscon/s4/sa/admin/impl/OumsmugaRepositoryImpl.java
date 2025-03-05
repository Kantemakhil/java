package net.syscon.s4.sa.admin.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.StaffMemberRoles;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsRoles;
import net.syscon.s4.sa.admin.OumsmugaRepository;

/**
 * Class OumsmugaRepositoryImpl
 * @version 1.0 
 */
@Repository
public class OumsmugaRepositoryImpl extends RepositoryBase implements OumsmugaRepository{
	
	private static Logger logger = LogManager.getLogger(OumsmugaRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("CODE", 						new FieldMapper("code"))
		.put("DESCRIPTION", 					new FieldMapper("description"))
		.put("USER_ID", 						new FieldMapper("userId"))
		.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
		.put("LAST_NAME", 						new FieldMapper("lastName"))
		.put("FIRST_NAME", 						new FieldMapper("firstName"))
		.put("STAFF_ID", 						new FieldMapper("staffId"))
		.build();
	private final Map<String, FieldMapper> staffMemberRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("STAFF_ID", 						new FieldMapper("staffId"))
		.put("ROLE_ID", 						new FieldMapper("roleId"))
		.put("ROLE_CODE",						new FieldMapper("roleCode"))
		.put("CREATE_DATETIME",					new FieldMapper("createDatetime"))
		.put("CREATE_USER_ID",					new FieldMapper("createUserId"))
		.put("MODIFY_DATETIME",					new FieldMapper("modifyDatetime"))
		.build();
	private final Map<String, FieldMapper> omsRolesMapping = new ImmutableMap.Builder<String, FieldMapper>()
		.put("ROLE_ID", 						new FieldMapper("roleId"))
		.put("ROLE_NAME", 						new FieldMapper("roleName"))
		.put("ROLE_CODE",						new FieldMapper("roleCode"))
		.build();

	/**
	 * Creates new OumsmugaRepositoryImpl class Object
	 */
	public OumsmugaRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao OmsRoles
	 * @return List<OmsRoles>
	 */
	public List<OmsRoles> omsRoleExecuteQuery(final OmsRoles objSearchDao) {
		final String sql = getQuery("OUMSMUGA_OMSROLE_FIND_OMS_ROLES");
		final RowMapper<OmsRoles> OmsRolesRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsRoles.class,
				omsRolesMapping);
		final List<OmsRoles> returnList = namedParameterJdbcTemplate.query(sql, createParams(), OmsRolesRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao StaffMemberRoles
	 * @return List<StaffMemberRoles>
	 */
	public List<StaffMemberRoles> staffRoleExecuteQuery(final StaffMemberRoles objSearchDao) {
		final String sql = getQuery("OUMSMUGA_STAFFROLE_FIND_STAFF_MEMBER_ROLES");
		final RowMapper<StaffMemberRoles> StaffMemberRolesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StaffMemberRoles.class, staffMemberRolesMapping);
		final List<StaffMemberRoles> returnList = namedParameterJdbcTemplate.query(sql,
				createParams("roleId", objSearchDao.getRoleId()), StaffMemberRolesRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 * 
	 * @param lstStaffMemberRoles List<StaffMemberRoles>
	 * @return List<Integer>
	 */
	public Integer staffRoleInsertStaffMemberRoles(final List<StaffMemberRoles> lstStaffMemberRoles) {
		String sql = getQuery("OUMSMUGA_STAFFROLE_INSERT_STAFF_MEMBER_ROLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMemberRoles staffMemberRoles : lstStaffMemberRoles) {
			parameters.add(new BeanPropertySqlParameterSource(staffMemberRoles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMemberRoles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * 
	 * @param lstStaffMemberRoles List<StaffMemberRoles>
	 */
	public Integer staffRoleUpdateStaffMemberRoles(final List<StaffMemberRoles> lstStaffMemberRoles) {
		String sql = getQuery("OUMSMUGA_STAFFROLE_UPDATE_STAFF_MEMBER_ROLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMemberRoles staffMemberRoles : lstStaffMemberRoles) {
			parameters.add(new BeanPropertySqlParameterSource(staffMemberRoles));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMemberRoles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstStaffMemberRoles List<StaffMemberRoles>
	 */
	public Integer staffRoleDeleteStaffMemberRoles(final List<StaffMemberRoles> lstStaffMemberRoles) {
		final String sql = getQuery("OUMSMUGA_STAFFROLE_DELETE_STAFF_MEMBER_ROLES");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StaffMemberRoles staffMemberRoles : lstStaffMemberRoles) {
			parameters.add(new BeanPropertySqlParameterSource(staffMemberRoles));
		}
		try {
			String tableName = "STAFF_MEMBER_ROLES";
			String whereClause = "STAFF_ID = :staffId AND ROLE_ID = :roleId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method staffRoleDeleteStaffMemberRoles", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStaffMemberRoles.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkStaffRoleDspUserIdRecordGroup() {
		final String sql = getQuery("OUMSMUGA_FIND_CGFKSTAFFROLEDSPUSERID");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> result = new ArrayList<ReferenceCodes>();
		try {
			result = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
			return result;
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<StaffMembers> getStaffUserId(final int staffId) {
		final String sql = getQuery("OUMSMUGA_GET_STAFF_USER_ID");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMemberRolesMapping);
		return (ArrayList<StaffMembers>) namedParameterJdbcTemplate.query(sql, createParams("staffId", staffId),
				columnRowMapper);
	}

	@Override
	public Long getStaffId(final String userId) {
		final String sql = getQuery("OUMSMUGA_GET_STAFF_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("DSPUSERID",userId),Long.class);
	}
}
