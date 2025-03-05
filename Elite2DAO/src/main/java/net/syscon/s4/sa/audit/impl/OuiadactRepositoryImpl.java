package net.syscon.s4.sa.audit.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;


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
import net.syscon.s4.cm.teamsworkflow.beans.TeamMembers;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.TagAuditFormGettabledetail;
import net.syscon.s4.sa.audit.OuiadactRepository;
import oracle.jdbc.OracleTypes;
/**
 * Class OuiadactRepositoryImpl
 * 
 */
@Repository
public class OuiadactRepositoryImpl extends RepositoryBase implements OuiadactRepository {

	/**
	 * Creates new OuiadactRepositoryImpl class Object
	 */
	public OuiadactRepositoryImpl() {
	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OuiadactRepositoryImpl.class);
	private final Map<String, FieldMapper> omsOwnerstaffMembersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DB_USERNAME", new FieldMapper("dbUsername"))
			.put("INITCAP(LAST_NAM", new FieldMapper("initcap(lastNam"))
			.put("'||FIRST_NAME", new FieldMapper("'||firstName")).build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OBJECT_NAME", new FieldMapper("objectName")).build();
	
	private final Map<String, FieldMapper> tagAuditMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("STAMP", new FieldMapper("stamp"))
			.put("SESSION_ID", new FieldMapper("sessionId"))
			.put("OS_USER", new FieldMapper("osUser"))
			.put("DB_USER", new FieldMapper("dbUser"))
			.put("CLIENTIP", new FieldMapper("clientip"))
			.build();

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            TagAuditFormGettabledetail
	 *
	 * @return List<TagAuditFormGettabledetail>
	 *
	 * @throws SQLException
	 */
	public List<TagAuditFormGettabledetail> getTableDetailExecuteQuery(final TagAuditFormGettabledetail objSearchDao) {
		 List<TagAuditFormGettabledetail> lListObjTemp = new ArrayList<TagAuditFormGettabledetail>();
		final String sql = getQuery("OUIADACT_FIND_DETAILS");
		final RowMapper<TagAuditFormGettabledetail> TeamsRowMapper = Row2BeanRowMapper.makeMapping(sql, TagAuditFormGettabledetail.class, tagAuditMaping);
		lListObjTemp = (ArrayList<TagAuditFormGettabledetail>) namedParameterJdbcTemplate.query(sql,
				createParams("P_TABLENAME", objSearchDao.getpTableName(), "P_DATEFROM", objSearchDao.getpDateFrom(),
						"P_DATETO", objSearchDao.getpDateTo(), "P_TIMEFROM", objSearchDao.getpTimeFrom(),
						"P_TIMETO", objSearchDao.getpTimeTo()), TeamsRowMapper);	
		
		List<TagAuditFormGettabledetail> unique = lListObjTemp.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(TagAuditFormGettabledetail::getSessionId)).descendingSet()),
                                           ArrayList::new));
		return unique;
	}


	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgTableNameRecordGroup() {
		final String sql = getQuery("OUIADACT_FIND_RGTABLENAME");
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * createFormGlobals
	 *
	 * @param params
	 *
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OUIADACT_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class, mMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams(), columnRowMapper);
	}
	
	public String getStaffName(final TagAuditFormGettabledetail object) {
		final String sql = getQuery("OUIADACT_GET_STAFF_NAME");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("dbUser", object.getDbUser()),
				String.class);

	}

}
