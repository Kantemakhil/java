package net.syscon.s4.inst.visitsmanagement.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.visitsmanagement.OmuaprisRepository;
import net.syscon.s4.inst.visitsmanagement.beans.OffenderRestrictions;
import net.syscon.s4.inst.visitsmanagement.beans.VOffenderAuthorisedVisitors;
import oracle.jdbc.OracleTypes;

/**
 * Class OmuaprisRepositoryImpl
 */
@Repository
public class OmuaprisRepositoryImpl extends RepositoryBase implements OmuaprisRepository {

	
	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OmuaprisRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> vOffAuthVisitMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID",               new FieldMapper("offenderBookId"))
			.put("CONTACT_TYPE",                   new FieldMapper("contactType"))
			.put("CONTACT_PERSON_ID",              new FieldMapper("contactPersonId"))
			.put("VISITOR_LAST_NAME",              new FieldMapper("visitorLastName"))
			.put("VISITOR_OFFENDER_ID_DISPLAY",    new FieldMapper("visitorOffenderIdDisplay"))
			.put("RELATIONSHIP_TYPE",              new FieldMapper("relationshipType"))
			.put("CONTACT_ROOT_OFFENDER_ID",       new FieldMapper("contactRootOffenderId"))
			.put("CONTACT_PERSON_TYPE",            new FieldMapper("contactPersonType"))
			.put("VISITOR_FIRST_NAME",             new FieldMapper("visitorFirstName"))
			.put("OFFENDER_CONTACT_PERSON_ID",     new FieldMapper("offenderContactPersonId"))
			.put("AGE", new FieldMapper("age"))
			.build();
	private final Map<String, FieldMapper> vHeadBlkMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FIRST_NAME",                    new FieldMapper(" firstName"))
			.put("LAST_NAME",                     new FieldMapper(" lastName"))
			.put("OFFENDER_ID_DISPLAY",           new FieldMapper(" offenderIdDisplay "))
			.build();
	private final Map<String, FieldMapper> offResMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("EFFECTIVE_DATE",                    new FieldMapper("effectiveDate"))
			.put("RESTRICTION_TYPE",                     new FieldMapper("restrictionType"))
			.build();

	/**
	 * Creates new OmuaprisRepositoryImpl class Object
	 */
	public OmuaprisRepositoryImpl() {
		// OmuaprisRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffenderAuthorisedVisitors
	 *
	 * @return List<VOffenderAuthorisedVisitors>
	 *
	 */
	public List<VOffenderAuthorisedVisitors> vOffAuthVisExecuteQuery(final VOffenderAuthorisedVisitors objSearchDao) {
		final String sql = getQuery("OMUAPRIS_VOFFAUTHVIS_FIND_V_OFFENDER_AUTHORISED_VISITORS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" WHERE ");
			sqlQuery.append("CONTACT_PERSON_TYPE = 'OFF' " + " AND ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " AND ");
				params.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getVisitorOffenderIdDisplay() != null) {
				sqlQuery.append("VISITOR_OFFENDER_ID_DISPLAY = :VISITOR_OFFENDER_ID_DISPLAY" + " AND ");
				params.addValue("VISITOR_OFFENDER_ID_DISPLAY", objSearchDao.getVisitorOffenderIdDisplay());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<VOffenderAuthorisedVisitors> vOffAuthVisitMapper = Row2BeanRowMapper
				.makeMapping(sql, VOffenderAuthorisedVisitors.class, vOffAuthVisitMap);
		final ArrayList<VOffenderAuthorisedVisitors> returnList = (ArrayList<VOffenderAuthorisedVisitors>) namedParameterJdbcTemplate
				.query(preparedSql, params, vOffAuthVisitMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstVOffenderAuthorisedVisitors
	 *            List<VOffenderAuthorisedVisitors>
	 *
	 */
	public Integer vOffAuthVisUpdateVOffenderAuthorisedVisitors(
			final List<VOffenderAuthorisedVisitors> list) {
		final String sql = getQuery("OMUAPRIS_VOFFAUTHVIS_UPDATE_V_OFFENDER_AUTHORISED_VISITORS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final VOffenderAuthorisedVisitors obj : list) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffenderNames
	 *
	 * @param paramBean
	 *
	 */
	public List<VHeaderBlock> getOffenderNames(final VHeaderBlock paramBean) {
		final String sql = getQuery("OMUAPRIS_GET_OFFENDER_NAMES");
		final RowMapper<VHeaderBlock> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, VHeaderBlock.class,
				vHeadBlkMap);
		final ArrayList<VHeaderBlock> returnList = (ArrayList<VHeaderBlock>) namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}
	public VOffenderAuthorisedVisitors getContactOffenderDetails(final VOffenderAuthorisedVisitors paramBean) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Map<String, Object> returnObject = null;
		SqlParameter[] sqlParameters = new SqlParameter[10];
		final VOffenderAuthorisedVisitors returnList = new VOffenderAuthorisedVisitors();
		sqlParameters = new SqlParameter[] {
				new SqlParameter("P_CONTACT_OFFENDER_ROOT_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_ID_DISPLAY", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LAST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_FIRST_NAME", OracleTypes.VARCHAR),
				new SqlOutParameter("P_LOCATION", OracleTypes.VARCHAR),
				new SqlOutParameter("P_RESTRICTION_TYPE", OracleTypes.VARCHAR),
				new SqlOutParameter("P_CONTACT_OFFENDER_BOOK_ID", OracleTypes.NUMBER),
				new SqlParameter("P_VISIT_DATE", OracleTypes.DATE),
				};
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withProcedureName("GET_CONTACT_OFFENDER_DETAILS")
				.declareParameters(sqlParameters);
		inParamMap.put("P_CONTACT_OFFENDER_ROOT_ID", paramBean.getContactRootOffenderId());
		inParamMap.put("P_VISIT_DATE", paramBean.getVisitDate());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnList.setLocation(returnObject.get("P_LOCATION") != null ? String.valueOf(returnObject.get("P_LOCATION")) : null);
			returnList.setRestriction(returnObject.get("P_RESTRICTION_TYPE") != null ? String.valueOf(returnObject.get("P_RESTRICTION_TYPE")) : null);
			returnList.setVisitorLastName(returnObject.get("P_LAST_NAME") != null ? String.valueOf(returnObject.get("P_LAST_NAME")) : null);
			returnList.setVisitorFirstName(returnObject.get("P_FIRST_NAME") != null ? String.valueOf(returnObject.get("P_FIRST_NAME")) : null);
		} catch(Exception e) {
			logger.error("getContactOffenderDetails:",e);
		}
		return returnList;
	}
	/**
	 * To get offenderId from DB by using GET_OFFENDER_ID function
	 * @param vstOffIdDisplay
	 * @returnInteger
	 */
	public BigDecimal getOffenderId(final String vstOffIdDisplay) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		BigDecimal offenderId;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER),
				new SqlParameter("P_OFFENDER_ID_DISPLAY", OracleTypes.VARCHAR)
				 };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_VISITS").withFunctionName("GET_OFFENDER_ID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFFENDER_ID_DISPLAY", vstOffIdDisplay);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		offenderId = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return offenderId;
	}
	/**
	 * To get offenderBookId from DB by using GETOFFBOOKID function
	 * @param vstOffIdDisplay
	 * @returnInteger
	 */
	public BigDecimal getOffenderBookId(final String vstOffIdDisplay) {
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		BigDecimal offenderBookId;
		SqlParameter[] sqlParameters = new SqlParameter[2];
		sqlParameters = new SqlParameter[] { new SqlInOutParameter("RETURN_VALUE", OracleTypes.NUMBER),
				new SqlParameter("P_OFF_ID_DISP", OracleTypes.VARCHAR)
				 };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("OSINAMES").withFunctionName("GETOFFBOOKID")
				.declareParameters(sqlParameters);
		inParamMap.put("P_OFF_ID_DISP", vstOffIdDisplay);
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		offenderBookId = simpleJDBCCall.executeFunction(BigDecimal.class, inParameter);
		return offenderBookId;
	}
		/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * getOffenderRestrcitions
	 *
	 * @params offBookId,visitdDate
	 *
	 */
	public List<OffenderRestrictions> getOffenderRestrcitions(final Integer offBookId, final Date visitdDate) {
		final String sql = getQuery("OMUAPRIS_GET_OFFENDER_RESTRICTIONS");
		final RowMapper<OffenderRestrictions> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderRestrictions.class,
				offResMapping);
		final ArrayList<OffenderRestrictions> returnList = (ArrayList<OffenderRestrictions>) namedParameterJdbcTemplate.query(sql,
				createParams("P_OFFENDER_BOOK_ID",offBookId,"P_VISIT_DATE",visitdDate), columnRowMapper);
		return returnList;
	}
}
