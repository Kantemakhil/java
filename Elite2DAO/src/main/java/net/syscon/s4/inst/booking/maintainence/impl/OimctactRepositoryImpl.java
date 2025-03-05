package net.syscon.s4.inst.booking.maintainence.impl;

import java.util.ArrayList;
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
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.booking.beans.ContactPersonTypes;
import net.syscon.s4.inst.booking.maintainence.OimctactRepository;

/**
 * Class OimctactRepositoryImpl
 */
@Repository
public class OimctactRepositoryImpl extends RepositoryBase implements OimctactRepository {

	private static Logger logger = LogManager.getLogger(OimctactRepositoryImpl.class);

	private final Map<String, FieldMapper> staffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LAST_NAM", 							new FieldMapper("lastNam"))
			.put("FIRST_NAME", 							new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", 								new FieldMapper("code"))
			.build();
	private final Map<String, FieldMapper> contactPersonTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CONTACT_TYPE", 						new FieldMapper("contactType"))
			.put("RELATIONSHIP_TYPE", 					new FieldMapper("relationshipType"))
			.put("LIST_SEQ", 						    new FieldMapper("listSeq"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.put("UPDATE_ALLOWED_FLAG", 				new FieldMapper("updateAllowedFlag"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("SEAL_FLAG", 						    new FieldMapper("sealFlag"))
			.build();
	
	/**
	 * Creates new OimctactRepositoryImpl class Object
	 */
	public OimctactRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            ContactPersonTypes
	 *
	 * @return List<ContactPersonTypes>
	 */
	public List<ContactPersonTypes> contactPersonTypesExecuteQuery(final ContactPersonTypes objSearchDao) {
		final String sql = getQuery("OIMCTACT_CONTACTPERSONTYPES_FIND_CONTACT_PERSON_TYPES");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		String preSqlQuery = null;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			if (objSearchDao.getContactType() != null && !"".equals(objSearchDao.getContactType())) {
				sqlQuery.append(" CONTACT_TYPE  = :contactType " + " AND ");
				params.addValue("contactType", objSearchDao.getContactType());
			}
			if (objSearchDao.getRelationshipType() != null && !"".equals(objSearchDao.getRelationshipType())) {
				sqlQuery.append(" RELATIONSHIP_TYPE  = :relationshipType " + " AND ");
				params.addValue("relationshipType", objSearchDao.getRelationshipType());
			}
			if (objSearchDao != null && objSearchDao.getListSeq() != null) {
				sqlQuery.append(" LIST_SEQ  = :listSeq " + " AND ");
				params.addValue("listSeq", objSearchDao.getListSeq());
			}
			if (objSearchDao.getActiveFlag() != null) {
				if ("true".equals(objSearchDao.getActiveFlag())) {
					objSearchDao.setActiveFlag("Y");
				} else {
					objSearchDao.setActiveFlag("N");
				}
				sqlQuery.append(" ACTIVE_FLAG  = :activeFlag " + " AND ");
				params.addValue("activeFlag", objSearchDao.getActiveFlag());
			}
			if (objSearchDao.getExpiryDate() != null) {
				sqlQuery.append(" EXPIRY_DATE  = :expiryDate " + " AND ");
				params.addValue("expiryDate", objSearchDao.getExpiryDate());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preSqlQuery = preparedSql.concat(" ORDER BY CONTACT_TYPE, RELATIONSHIP_TYPE ");
		final RowMapper<ContactPersonTypes> conPersRowMapper = Row2BeanRowMapper.makeMapping(preSqlQuery,
				ContactPersonTypes.class, contactPersonTypesMapping);
		List<ContactPersonTypes> returnList = new ArrayList<>();

		try {
			returnList = namedParameterJdbcTemplate.query(preSqlQuery, params, conPersRowMapper);
		} catch (Exception e) {
			logger.error("contactPersonTypesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstContactPersonTypes
	 *            List<ContactPersonTypes>
	 *
	 * @return List<Integer>
	 */
	@Override
	public Integer contactPersonTypesInsertContactPersonTypes(final List<ContactPersonTypes> lstContPersTyp) {
		final String sql = getQuery("OIMCTACT_CONTACTPERSONTYPES_INSERT_CONTACT_PERSON_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ContactPersonTypes contPerTypes : lstContPersTyp) {
			parameters.add(new BeanPropertySqlParameterSource(contPerTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstContPersTyp.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstContactPersonTypes
	 *            List<ContactPersonTypes>
	 */
	public Integer contactPersonTypesUpdateContactPersonTypes(final List<ContactPersonTypes> lstContPersTyp) {
		final String sql = getQuery("OIMCTACT_CONTACTPERSONTYPES_UPDATE_CONTACT_PERSON_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ContactPersonTypes contPerTypes : lstContPersTyp) {
			parameters.add(new BeanPropertySqlParameterSource(contPerTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstContPersTyp.size() == returnArray.length) {
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
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup() {
		final String sql = getQuery("OIMCTACT_FIND_RGRELATIONSHIPTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgRelationshipTypeRecordGroup", e);
		}
		return returnList;

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		final String sql = getQuery("OIMCTACT_FIND_RGCONTACTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		List<ReferenceCodes> returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgContactTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * contactPersonTypesPostQuery
	 *
	 * @param params
	 *
	 */
	public List<StaffMembers> contactPersonTypesPostQuery(final StaffMembers paramBean) {
		final String sql = getQuery("c");
		final RowMapper<StaffMembers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				staffMembersMapping);
		List<StaffMembers> returnList = new ArrayList<StaffMembers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("contactPersonTypesPostQuery", e);
		}
		return returnList;
	}

}
