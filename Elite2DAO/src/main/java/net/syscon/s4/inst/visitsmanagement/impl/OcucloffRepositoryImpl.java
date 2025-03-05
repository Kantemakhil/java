package net.syscon.s4.inst.visitsmanagement.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VDistinctLinkedOffenders;
import net.syscon.s4.inst.visitsmanagement.OcucloffRepository;

/**
 * Class OcucloffRepositoryImpl
 */

@Repository
public class OcucloffRepositoryImpl extends RepositoryBase implements OcucloffRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcucloffRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vDistinctLinkedOffendersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", 			new FieldMapper("offenderIdDisplay"))
			.put("CONTACT_TYPE", 					new FieldMapper("contactType"))
			.put("CONTACT_TYPE_DESCRIPTION", 		new FieldMapper("contactTypeDescription"))
			.put("RELATIONSHIP_TYPE_DESCRIPTION", 	new FieldMapper("relationshipTypeDescription"))
			.put("ROOT_OFFENDER_ID", 				new FieldMapper("rootOffenderId"))
			.put("RELATIONSHIP_TYPE", 				new FieldMapper("relationshipType"))
			.put("MIDDLE_NAME", 					new FieldMapper("middleName"))
			.put("PERSON_ID", 						new FieldMapper("personId"))
			.put("LAST_NAME", 						new FieldMapper("lastName"))
			.put("FIRST_NAME", 						new FieldMapper("firstName"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.build();

	/**
	 * Creates new OcucloffRepositoryImpl class Object
	 */
	public OcucloffRepositoryImpl() {
		// OcucloffRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VDistinctLinkedOffenders
	 *
	 * @return List<VDistinctLinkedOffenders>
	 *
	 * @throws SQLException
	 */
	public List<VDistinctLinkedOffenders> contactsExecuteQuery(final VDistinctLinkedOffenders objSearchDao) {
		final String sql = getQuery("OCUCLOFF_CONTACTS_FIND_V_DISTINCT_LINKED_OFFENDERS");
		final RowMapper<VDistinctLinkedOffenders> VDistinctLinkedOffendersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VDistinctLinkedOffenders.class, vDistinctLinkedOffendersMapping);
		List<VDistinctLinkedOffenders> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("personId", objSearchDao.getPersonId()),
				VDistinctLinkedOffendersRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgRelationshipTypeRecordGroup() {
		final String sql = getQuery("OCUCLOFF_FIND_RGRELATIONSHIPTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgRelationshipTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> rgContactTypeRecordGroup() {
		final String sql = getQuery("OCUCLOFF_FIND_RGCONTACTTYPE");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (Exception e) {
			logger.error("rgContactTypeRecordGroup", e);
			return Collections.emptyList();
		}
	}

}
