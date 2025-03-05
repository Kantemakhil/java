package net.syscon.s4.inst.offenderissuestracking.maintenance.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.GrievanceReasons;
import net.syscon.s4.im.beans.GrievanceTxns;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.inst.offenderissuestracking.maintenance.OimissueRepository;

/**
 * Class OimissueRepositoryImpl
 */
@Repository
public class OimissueRepositoryImpl extends RepositoryBase implements OimissueRepository {

	/**
	 * s Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OimissueRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> grivenTxnMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("GRIEV_TYPE", new FieldMapper("grievType")).put("TXN_TYPE", new FieldMapper("txnType"))
			.put("OFF_RSP_FLAG", new FieldMapper("offRspFlag")).put("IG_RSP_FLAG", new FieldMapper("igRspFlag"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("DOC_RSP_FLAG", new FieldMapper("docRspFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("DAYS_RESPOND", new FieldMapper("daysRespond")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("CHECKFLAG", new FieldMapper("checkFlag")).put("OFFFLAG", new FieldMapper("offFlag"))
			.put("AGYFLAG", new FieldMapper("agyFlag")).put("SUPFLAG", new FieldMapper("supFlag")).build();
	private final Map<String, FieldMapper> grievanceReasonsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFIED_USER_ID", new FieldMapper("modifiedUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("GRIEV_REASON_CODE", new FieldMapper("grievReasonCode"))
			.put("GRIEV_TYPE", new FieldMapper("grievType")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MODIFIED_DATETIME", new FieldMapper("modifiedDatetime"))
			.put("CHECKFLAG", new FieldMapper("checkFlag")).build();
	private final Map<String, FieldMapper> grievanceTypesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("MODIFIED_USER_ID", new FieldMapper("modifiedUserId"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId")).put("DESCRIPTION", new FieldMapper("description"))
			.put("GRIEV_TYPE", new FieldMapper("grievType")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("STAFF_INVOLVED_FLAG", new FieldMapper("staffInvolvedFlag"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime")).put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("MODIFIED_DATETIME", new FieldMapper("modifiedDatetime")).build();

	/**
	 * Creates new OimissueRepositoryImpl class Object
	 */
	public OimissueRepositoryImpl() {
		// OimissueRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GrievanceTypes
	 *
	 * @return List<GrievanceTypes>
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTypes> grievanceTypesExecuteQuery(final GrievanceTypes objSearchDao) {
		final String sql = getQuery("OIMISSUE_GRIEVANCETYPES_FIND_GRIEVANCE_TYPES");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getGrievType() != null && !objSearchDao.getGrievType().isEmpty()
					&& !objSearchDao.getGrievType().trim().equals("")) {
				pSql.append(" GRIEV_TYPE LIKE :grievType  AND ");
				param.addValue("grievType", objSearchDao.getGrievType().trim());
			}
			if (objSearchDao.getDescription() != null && !objSearchDao.getDescription().isEmpty()
					&& !objSearchDao.getDescription().trim().equals("")) {
				pSql.append(" DESCRIPTION LIKE :description AND ");
				param.addValue("description", objSearchDao.getDescription().trim());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by LIST_SEQ ");
		final RowMapper<GrievanceTypes> HoCodesRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceTypes.class,
				grievanceTypesMapping);
		ArrayList<GrievanceTypes> returnList = new ArrayList<>();
		try {
			returnList = (ArrayList<GrievanceTypes>) namedParameterJdbcTemplate.query(preparedSql, param,
					HoCodesRowMapper);
		} catch (Exception e) {
			logger.error("grievanceTypesExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstGrievanceTypes
	 *            List<GrievanceTypes>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceTypesInsertGrievanceTypes(final List<GrievanceTypes> lstGrievanceTypes) {
		final String sql = getQuery("OIMISSUE_GRIEVANCETYPES_INSERT_GRIEVANCE_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceTypes grievanceTypes : lstGrievanceTypes) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGrievanceTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstGrievanceTypes
	 *            List<GrievanceTypes>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceTypesUpdateGrievanceTypes(final List<GrievanceTypes> lstGrievanceTypes) {
		final String sql = getQuery("OIMISSUE_GRIEVANCETYPES_UPDATE_GRIEVANCE_TYPES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceTypes grievanceTypes : lstGrievanceTypes) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceTypes));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGrievanceTypes.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GrievanceReasons
	 *
	 * @return List<GrievanceReasons>
	 *
	 * @throws SQLException
	 */
	public List<GrievanceReasons> grievanceReasonsExecuteQuery(final GrievanceReasons objSearchDao) {
		final String sql = getQuery("OIMISSUE_GRIEVANCEREASONS_FIND_GRIEVANCE_REASONS");
		final RowMapper<GrievanceReasons> grivnceRsnRowMpr = Row2BeanRowMapper.makeMapping(sql, GrievanceReasons.class,
				grievanceReasonsMapping);
		final ArrayList<GrievanceReasons> returnList = (ArrayList<GrievanceReasons>) namedParameterJdbcTemplate
				.query(sql, createParams("grievType", objSearchDao.getGrievType()), grivnceRsnRowMpr);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstGrievanceReasons
	 *            List<GrievanceReasons>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceReasonsInsertGrievanceReasons(final List<GrievanceReasons> lstGrievanceReasons) {
		final String sql = getQuery("OIMISSUE_GRIEVANCEREASONS_INSERT_GRIEVANCE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceReasons grievanceReasons : lstGrievanceReasons) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGrievanceReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstGrievanceReasons
	 *            List<GrievanceReasons>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceReasonsUpdateGrievanceReasons(final List<GrievanceReasons> lstGrievanceReasons) {
		final String sql = getQuery("OIMISSUE_GRIEVANCEREASONS_UPDATE_GRIEVANCE_REASONS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		for (final GrievanceReasons grievanceReasons : lstGrievanceReasons) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceReasons));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGrievanceReasons.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstGrievanceTxns
	 *            List<GrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceReasonsDeleteGrievanceReasons(final List<GrievanceReasons> lstGrievanceTxns) {

		final String sql = getQuery("OIMISSUE_GRIEVANCEREASONS_DELETE_GRIEVANCE_REASONS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceReasons grievanceTxns : lstGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceTxns));
		}
		try {
			batchUpdatePreDeletedRows("GRIEVANCE_REASONS", "GRIEV_TYPE=:grievType AND GRIEV_REASON_CODE=:grievReasonCode", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in grievanceReasonsDeleteGrievanceReasons"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            GrievanceTxns
	 *
	 * @return List<GrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public List<GrievanceTxns> grievanceTxnsExecuteQuery(final GrievanceTxns objSearchDao) {
		final String sql = getQuery("OIMISSUE_GRIEVANCETXNS_FIND_GRIEVANCE_TXNS");
		final RowMapper<GrievanceTxns> grvnceTxnRowMpr = Row2BeanRowMapper.makeMapping(sql, GrievanceTxns.class,
				grivenTxnMapping);
		final ArrayList<GrievanceTxns> returnList = (ArrayList<GrievanceTxns>) namedParameterJdbcTemplate.query(sql,
				createParams("grievType", objSearchDao.getGrievType()), grvnceTxnRowMpr);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkMovementReasons
	 *
	 * @param params
	 */

	public Integer cgrichkMovementReasonsScheduleCheck(final GrievanceTxns grievenceTxns) {
		final String sql = getQuery("OIMISSUE_GRIEVANCE_TXNS_KEYDELREC");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("grievType", grievenceTxns.getGrievType(), "txnType", grievenceTxns.getTxnType()),
				Integer.class);
		return returnValue;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgrichkMovementReasons
	 *
	 * @param params
	 */

	public Integer onDeleteReasons(final GrievanceReasons grievenceTxns) {
		final String sql = getQuery("OIMISSUE_GRIEVANCE_REASONS_KEYDELREC");
		Integer returnValue = 0;
		returnValue = namedParameterJdbcTemplate.queryForObject(sql, createParams("grievType",
				grievenceTxns.getGrievType(), "grievReasonCode", grievenceTxns.getGrievReasonCode()), Integer.class);
		return returnValue;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstGrievanceTxns
	 *            List<GrievanceTxns>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceTxnsInsertGrievanceTxns(final List<GrievanceTxns> lstGrievanceTxns) {

		final String sql = getQuery("OIMISSUE_GRIEVANCETXNS_INSERT_GRIEVANCE_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceTxns grievanceTxns : lstGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstGrievanceTxns
	 *            List<GrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceTxnsUpdateGrievanceTxns(final List<GrievanceTxns> lstGrievanceTxns) {

		final String sql = getQuery("OIMISSUE_GRIEVANCETXNS_UPDATE_GRIEVANCE_TXNS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceTxns grievanceTxns : lstGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceTxns));
		}
		
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstGrievanceTxns
	 *            List<GrievanceTxns>
	 *
	 * @throws SQLException
	 */
	public Integer grievanceTxnsDeleteGrievanceTxns(final List<GrievanceTxns> lstGrievanceTxns) {

		final String sql = getQuery("OIMISSUE_GRIEVANCETXNS_DELETE_GRIEVANCE_TXNS");

		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final GrievanceTxns grievanceTxns : lstGrievanceTxns) {
			parameters.add(new BeanPropertySqlParameterSource(grievanceTxns));
		}
		try {
			batchUpdatePreDeletedRows("GRIEVANCE_TXNS", "GRIEV_TYPE=:grievType AND TXN_TYPE=:txnType", parameters);
		} catch (Exception e) {
			logger.error("batchUpdatePreDeletedRows in grievanceTxnsDeleteGrievanceTxns"+e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));

		if (lstGrievanceTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * grievanceTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<GrievanceReasons> grievanceTypesOnCheckDeleteMaster(final GrievanceReasons paramBean) {
		final String sql = getQuery("OIMISSUE_GRIEVANCE_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<GrievanceReasons> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceReasons.class,
				grievanceReasonsMapping);
		return (ArrayList<GrievanceReasons>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * grievanceTypesOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<GrievanceTxns> grievanceTypesOnCheckDeleteMaster(final GrievanceTxns paramBean) {
		final String sql = getQuery("OIMISSUE_GRIEVANCE_TYPES_ONCHECKDELETEMASTER");
		final RowMapper<GrievanceTxns> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, GrievanceTxns.class,
				grivenTxnMapping);
		return (ArrayList<GrievanceTxns>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

	/**
	 * to check the timeslot seqs to insert in visit slots for seq id
	 */
	public String getTabSecuityEnable(final String inparam) {
		Map<String, Object> returnObject = null;
		String returnValue = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[3];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_MODULE_NAME", Types.VARCHAR),
				new SqlOutParameter("RETURN_VALUE", Types.VARCHAR) };
		final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TAG_SECURITY").withFunctionName("GET_GROUP_PRIVILEGE")
				.declareParameters(sqlParameters);
		inParamMap.put("P_MODULE_NAME", inparam);
		final MapSqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			returnValue = (String) returnObject.get("RETURN_VALUE");
		} catch (Exception e) {
			logger.error("getTabSecuityEnable" + e);
			returnValue = null;
		}
		return returnValue;
	}

}
