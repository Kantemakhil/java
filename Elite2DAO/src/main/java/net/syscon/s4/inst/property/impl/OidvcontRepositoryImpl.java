package net.syscon.s4.inst.property.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
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
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.property.OidvcontRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Class OidvcontRepositoryImpl
 */
@Repository
public class OidvcontRepositoryImpl extends RepositoryBase implements OidvcontRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidvcontRepositoryImpl.class);
	
	private final Map<String, FieldMapper> offenderPptyItemTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISPOSED_TO_CORP_ID", 				new FieldMapper("disposedToCorpId"))
			.put("VERIFICATION_FLAG", 					new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", 			new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("DISPOSED_TO_PERSON_ID", 				new FieldMapper("disposedToPersonId"))
			.put("PROPERTY_ITEM_SEQ", 					new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", 			new FieldMapper("propertyContainerTxnId"))
			.put("DISPOSED_TO_PERSON", 					new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("PROPERTY_CONTAINER_ID", 				new FieldMapper("propertyContainerId"))
			.put("MAKE", 								new FieldMapper("make"))
			.put("COLOR", 								new FieldMapper("color"))
			.put("TO_STATUS_CODE", 						new FieldMapper("toStatusCode"))
			.put("PROPERTY_ITEM_TXN_ID", 				new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
			.put("FROM_STATUS_CODE", 					new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", 						new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", 			new FieldMapper("disposedToOffenderFlag"))
			.put("SERIAL_NO", 							new FieldMapper("serialNo"))
			.put("VERIFY_FLAG", 						new FieldMapper("verifyFlag"))
			.put("EVENT_SEQ", 							new FieldMapper("eventSeq"))
			.build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DSP_PROPERTY_TYPE", 					new FieldMapper("dspPropertyType"))
			.put("ACTION_CODE", 						new FieldMapper("actionCode"))
			.put("SEAL_MARK,", 							new FieldMapper("sealMark,"))
			.put("DSP_CONDITION_CODE", 					new FieldMapper("dspConditionCode"))
			.put("QUANTITY", 							new FieldMapper("quantity"))
			.put("CONDITION_CODE", 						new FieldMapper("conditionCode"))
			.put("MODE", 								new FieldMapper("mode"))
			.put("CONTAINER_CODE", 						new FieldMapper("containerCode"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("PROPERTY_DESCRIPTION", 				new FieldMapper("propertyDescription"))
			.put("PROPERTY_ITEM_SEQ", 					new FieldMapper("propertyItemSeq"))
			.put("CODE", 								new FieldMapper("code"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("COLOR", 								new FieldMapper("color"))
			.put("PROPERTY_TYPE", 						new FieldMapper("propertyType"))
			.put("SEQVALUE", 							new FieldMapper("seqValue"))
			.put("ACTIVE_FLAG", 						new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("INTERNALLOCATIONID", 					new FieldMapper("internalLocationId"))
			.build();
	private final Map<String, FieldMapper> offenderPptyItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",						new FieldMapper("profileCode"))
			.put("DISPOSED_TO_CORP_ID", 				new FieldMapper("disposedToCorpId"))
			.put("QUANTITY", 							new FieldMapper("quantity"))
			.put("CONFIRM_FLAG", 						new FieldMapper("confirmFlag"))
			.put("PROPERTY_DESCRIPTION", 				new FieldMapper("propertyDescription"))
			.put("VERIFICATION_FLAG", 					new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", 			new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", 						new FieldMapper("profileValue"))
			.put("DISPOSED_TO_PERSON_ID", 				new FieldMapper("disposedToPersonId"))
			.put("DESCRIPTION", 						new FieldMapper("description"))
			.put("STATUS_CODE", 						new FieldMapper("statusCode"))
			.put("PROPERTY_ITEM_SEQ", 					new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID",			new FieldMapper("propertyContainerTxnId"))
			.put("PROFILE_TYPE", 						new FieldMapper("profileType"))
			.put("DISPOSED_TO_PERSON", 					new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", 						new FieldMapper("oldTableName"))
			.put("PROPERTY_TYPE", 						new FieldMapper("propertyType"))
			.put("PROPERTY_CONTAINER_ID", 				new FieldMapper("propertyContainerId"))
			.put("MAKE", 								new FieldMapper("make"))
			.put("PROFILE_VALUE_2", 					new FieldMapper("profileValue2"))
			.put("COLOR", 								new FieldMapper("color"))
			.put("TO_STATUS_CODE", 						new FieldMapper("toStatusCode"))
			.put("CONDITION_CODE", 						new FieldMapper("conditionCode"))
			.put("PROPERTY_ITEM_TXN_ID", 				new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
			.put("FROM_STATUS_CODE", 					new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", 						new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", 			new FieldMapper("disposedToOffenderFlag"))
			.put("RECEIVED_FROM", 						new FieldMapper("receivedFrom"))
			.put("SERIAL_NO", 							new FieldMapper("serialNo"))
			.put("VERIFY_FLAG", 						new FieldMapper("verifyFlag"))
			.put("EVENT_SEQ", 							new FieldMapper("eventSeq"))
			.build();
	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
			.put("OFFENDER_BOOK_ID", 					new FieldMapper("offenderBookId"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
			.put("INTERNAL_LOCATION_ID", 				new FieldMapper("internalLocationId"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
			.put("DISPOSED_TO_NAME", 					new FieldMapper("disposedToName"))
			.put("PROPERTY_STORAGE_ID", 				new FieldMapper("propertyStorageId"))
			.put("PROPOSED_DISPOSAL_DATE", 				new FieldMapper("proposedDisposalDate"))
			.put("EXPIRY_DATE", 						new FieldMapper("expiryDate"))
			.put("TRN_FROM_AGY_LOC_ID", 				new FieldMapper("trnFromAgyLocId"))
			.build();
	private final Map<String, FieldMapper> offenderPptyConTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("COMMENT_TEXT", 						new FieldMapper("commentText"))
			.put("PROPERTY_CONTAINER_TXN_ID", 			new FieldMapper("propertyContainerTxnId"))
			.put("CREATE_USER_ID", 						new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", 						new FieldMapper("modifyUserId"))
			.put("PROPERTY_CONTAINER_ID", 				new FieldMapper("propertyContainerId"))
			.put("ACTION_CODE", 						new FieldMapper("actionCode"))
			.put("SEAL_MARK", 							new FieldMapper("sealMark"))
			.put("TRN_TO_AGY_LOC_ID", 					new FieldMapper("trnToAgyLocId"))
			.put("SEAL_FLAG", 							new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", 							new FieldMapper("agyLocId"))
			.put("CREATE_DATETIME", 					new FieldMapper("createDateTime"))
			.put("INTERNAL_LOCATION_ID", 				new FieldMapper("internalLocationId"))
			.put("MODIFY_DATETIME", 					new FieldMapper("modifyDateTime"))
			.put("CREATE_DATE", 						new FieldMapper("createDate"))
			.put("TRN_FROM_AGY_LOC_ID", 				new FieldMapper("trnFromAgyLocId"))
			.build();

	/**
	 * Creates new OidvcontRepositoryImpl class Object
	 */
	public OidvcontRepositoryImpl() {
		// OidvcontRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyContainers
	 *
	 * @return List<OffenderPptyContainers>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers objSearchDao) {
		final String sql = getQuery("OIDVCONT_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS");
		final RowMapper<OffenderPptyContainers> OffenderPptyContainersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		List<OffenderPptyContainers> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "agyLocId", objSearchDao.getAgyLocId()),
				OffenderPptyContainersRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyConTxns
	 *
	 * @return List<OffenderPptyConTxns>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyConTxns> conTxExecuteQuery(final OffenderPptyConTxns objSearchDao) {
		final String sql = getQuery("OIDVCONT_CONTX_FIND_OFFENDER_PPTY_CON_TXNS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getPropertyContainerId() != null) {
				sqlQuery.append("PROPERTY_CONTAINER_ID =  :propertyContainerId " + " and "
						+ " OFFENDER_PPTY_CON_TXNS.ACTION_CODE IN ( 'VC','VS','VB' )");
			//	sqlQuery.append("PROPERTY_CONTAINER_ID =  :propertyContainerId ");
				valuesList.addValue("propertyContainerId", objSearchDao.getPropertyContainerId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY CREATE_DATE DESC ");
		final RowMapper<OffenderPptyConTxns> OffenderPptyConTxnsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderPptyConTxns.class, offenderPptyConTxnsMapping);
		List<OffenderPptyConTxns> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, OffenderPptyConTxnsRowMapper);
		String userId = null;
		String userName = null;
		for (OffenderPptyConTxns list : returnList) {
			userId = list.getCreateUserId();
			userName = this.getUserName(userId);
			list.setCreateUserId(userName);
		}
		return returnList;
	}
	
	
	public String getUserName(String userId) {
		final String sql = getQuery("OIDVCONT_GET_USER_NAME");
		
		 return namedParameterJdbcTemplate.queryForObject(sql, createParams("userId", userId), String.class);

	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderPptyConTxns
	 *            List<OffenderPptyConTxns>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer conTxInsertOffenderPptyConTxns(final List<OffenderPptyConTxns> lstOffenderPptyConTxns) {
		int insertCount = 0;
		String sql = getQuery("OIDVCONT_CONTX_INSERT_OFFENDER_PPTY_CON_TXNS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		for (int i = 0; i < returnArray.length; i++) {
		}
		if (lstOffenderPptyConTxns.size() == insertCount) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyItemTxns
	 *
	 * @return List<OffenderPptyItemTxns>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(final OffenderPptyItemTxns objSearchDao) {
		final String sql = getQuery("OIDVCONT_ITMTX_FIND_OFFENDER_PPTY_ITEM_TXNS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getPropertyContainerId() != null) {
				sqlQuery.append("PROPERTY_CONTAINER_ID =  :propertyContainerId " + " and ");
				valuesList.addValue("propertyContainerId", objSearchDao.getPropertyContainerId());
			}
			if (objSearchDao.getPropertyContainerTxnId() != null) {
				sqlQuery.append("PROPERTY_CONTAINER_TXN_ID =  :propertyContainerTxnId " + " and ");
				valuesList.addValue("propertyContainerTxnId", objSearchDao.getPropertyContainerTxnId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<OffenderPptyItemTxns> OffenderPptyItemTxnsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderPptyItemTxns.class, offenderPptyItemTxnsMapping);
		List<OffenderPptyItemTxns> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, OffenderPptyItemTxnsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderPptyItemTxns
	 *            List<OffenderPptyItemTxns>
	 *
	 * @return List<Integer>
	 *
	 * @throws SQLException
	 */
	public Integer itmTxInsertOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		String sql = getQuery("OIDVCONT_ITMTX_INSERT_OFFENDER_PPTY_ITEM_TXNS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPptyItemTxns offenderPptyItemTxns : lstOffenderPptyItemTxns) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyItemTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPptyItemTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Fetch the records from database table
	 */
	public Integer getPptyTxnId() {
		final String sql = getQuery("OIDVCONT_PROPERTY_ITEM_TXN_ID");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyItemTxns
	 *            List<OffenderPptyItemTxns>
	 *
	 * @throws SQLException
	 */
	public Integer itmTxUpdateOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		String sql = getQuery("OIDVCONT_ITMTX_UPDATE_OFFENDER_PPTY_ITEM_TXNS");
		int[] returnArray = new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPptyItemTxns offenderPptyItemTxns : lstOffenderPptyItemTxns) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyItemTxns));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPptyItemTxns.size() == returnArray.length) {
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
	public List<ReferenceCodes> cgfkConTxActionCodeRecordGroup(String propertyContainerId) {
		final String sql = getQuery("OIDVCONT_FIND_CGFKCONTXACTIONCODE");
		final RowMapper<ReferenceCodes> referenceCodesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);

		try {
			return namedParameterJdbcTemplate.query(sql, createParams("PROPERTYCONTAINERID",(propertyContainerId != null && !propertyContainerId.equals("undefined")) ? new BigDecimal(propertyContainerId):null),
					referenceCodesRowMapper);
		} catch (Exception e) {
			logger.error("cgfkConTxActionCodeRecordGroup:"+e);
			return Collections.emptyList();
		}
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vPheadOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		final String sql = getQuery("OIDVCONT_V_PHEAD_ONCHECKDELETEMASTER");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		final List<OffenderPptyContainers> returnList = namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * itmTxPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes itmTxPostQuery(final String code) {
		final String sql = getQuery("OIDVCONT_ITM_TX_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("COLOR", code),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * itmTxPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes itmTxPostQueryColor(final String code) {
		final String sql = getQuery("OIDVCONT_ITM_TX_POSTQUERY_COLOR");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("COLOR", code),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * itmTxPostQuery
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes getDescriptionOfConditionCode(final String code) {
		final String sql = getQuery("OIDVCONT_ITM_TX_CONDITION_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("DSPCONDITIONCODE", code), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConPpty
	 *
	 * @param params
	 *
	 */
	public AgencyInternalLocations cgfkchkOffConOffConPpty(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIDVCONT_CGFKCHK_OFF_CON_OFF_CON_PPTY");
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		AgencyInternalLocations returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffConOffConRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffConOffConRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDVCONT_CGFKCHK_OFF_CON_OFF_CON_REF_C");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CONTAINERCODE", paramBean.getCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkConTxConTxnRefCo
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkConTxConTxnRefCo(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDVCONT_CGFKCHK_CON_TX_CON_TXN_REF_CO");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkItmTxItmTxOffPi
	 *
	 * @param params
	 *
	 */
	public OffenderPptyItems cgfkchkItmTxItmTxOffPi(final OffenderPptyItems paramBean) {
		final String sql = getQuery("OIDVCONT_CGFKCHK_ITM_TX_ITM_TX_OFF_PI_");
		final RowMapper<OffenderPptyItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				offenderPptyItemsMapping);
		OffenderPptyItems returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkItmTxItmTxOffPi
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkItmTxItmTxOffPi(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDVCONT_CGFKCHK_ITM_TX_ITM_TX_OFF_PI_");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * Fetching the record from database table
	 * 
	 * @return Integer
	 * @Param lstOffenderAlerts1
	 */
	@Override
	public Integer offPptyConInsertOffenderAlerts(final List<OffenderPptyConTxns> lstOffenderPptyConTxns) {
		int pptyContTxsId = 0;
		pptyContTxsId = preInsert();
		final String sql = getQuery("OIDVCONT_CONTX_INSERT_OFFENDER_PPTY_CON_TXNS");
		int[] returnList = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyConTxns list : lstOffenderPptyConTxns) {
			list.setPropertyContainerTxnId(pptyContTxsId);
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnList = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("OffenderPptyConTxns Insert Exception : ", e);
		}
		if (lstOffenderPptyConTxns.size() == returnList.length) {
			return pptyContTxsId;
		} else {
			return 0;
		}
	}

	public Integer preInsert() {
		final String sql = getQuery("OIDVCONT_PROPERTY_CONTAINER_TXN_ID");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyItems
	 *
	 * @return List<OffenderPptyItems>
	 *
	 * @throws SQLException
	 */
	public OffenderPptyItems offPiExecuteQuery(final OffenderPptyItems objSearchDao) {
		final String sql = getQuery("OIDRPITM_OFFPI_FIND_OFFENDER_PPTY_ITEMS");
		final RowMapper<OffenderPptyItems> OffenderPptyItemsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		OffenderPptyItems returnList = new OffenderPptyItems();
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDERBOOKID",
				objSearchDao.getOffenderBookId(), "propertyItemSeq", objSearchDao.getPropertyItemSeq()),
				OffenderPptyItemsRowMapper);
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyItems
	 *
	 * @return List<OffenderPptyItems>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItems> offPItemExecuteQuery(final OffenderPptyItems objSearchDao) {
		final String sql = getQuery("OIDRPITM_OFFPI_GET_OFFENDER_PPTY_ITEMS");
		final RowMapper<OffenderPptyItems> OffenderPptyItemsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		List<OffenderPptyItems> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("propertyContainerId", objSearchDao.getPropertyContainerId()), OffenderPptyItemsRowMapper);
		return returnList;
	}

	/**
	 * To get description of propertycode param code
	 */
	public ReferenceCodes getDescriptionOfPropertyCode(final String code) {
		final String sql = getQuery("OIDVCONT_PRINT_PROPERTY_TYPE_DESCRIPTION");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("propertyType", code), columnRowMapper);
		return returnObj;
	}

	/**
	 * Update offender_ppty_containers
	 */
	@Override
	public Integer updateOffenderPptyContainers(final OffenderPptyContainers offenderPptyContainers) {
		final String sql = getQuery("OIDVCONT_UPDATE_OFFENDER_PPTY_CONTAINERS");
		Integer returnArray = null;
		returnArray = namedParameterJdbcTemplate.update(sql,
				new BeanPropertySqlParameterSource(offenderPptyContainers));
		return returnArray;
	}

}
