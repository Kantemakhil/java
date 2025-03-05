package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.OiddpropRepository;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.Corporate;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.Printer;

/**
 * Class OiddpropRepositoryImpl
 */
@Repository
public class OiddpropRepositoryImpl extends RepositoryBase implements OiddpropRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OiddpropRepositoryImpl.class.getName());

	public static final String MODE = "ENTER-QUERY";

	private final Map<String, FieldMapper> offenderPptyItemTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISPOSED_TO_CORP_ID", new FieldMapper("disposedToCorpId"))
			.put("VERIFICATION_FLAG", new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("DISPOSED_TO_PERSON_ID", new FieldMapper("disposedToPersonId"))
			.put("PROPERTY_ITEM_SEQ", new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", new FieldMapper("propertyContainerTxnId"))
			.put("DISPOSED_TO_PERSON", new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId")).put("MAKE", new FieldMapper("make"))
			.put("COLOR", new FieldMapper("color")).put("TO_STATUS_CODE", new FieldMapper("toStatusCode"))
			.put("PROPERTY_ITEM_TXN_ID", new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("FROM_STATUS_CODE", new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", new FieldMapper("disposedToOffenderFlag"))
			.put("SERIAL_NO", new FieldMapper("serialNo")).put("VERIFY_FLAG", new FieldMapper("verifyFlag"))
			.put("EVENT_SEQ", new FieldMapper("eventSeq")).build();

	private final Map<String, FieldMapper> personsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISPOSED_TO_PERSON", new FieldMapper("dspName")).put("PERSON_ID", new FieldMapper("personId"))
			.put("FIRST_NAME", new FieldMapper("firstName")).put("1", new FieldMapper("1 ")).build();

	private final Map<String, FieldMapper> offenderPptyItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("DISPOSED_TO_PERSON", new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("PROPERTY_TYPE", new FieldMapper("propertyType"))
			.put("DISPOSED_TO_CORP_ID", new FieldMapper("disposedToCorpId"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId")).put("MAKE", new FieldMapper("make"))
			.put("QUANTITY", new FieldMapper("quantity")).put("CONFIRM_FLAG", new FieldMapper("confirmFlag"))
			.put("COLOR", new FieldMapper("color")).put("PROPERTY_DESCRIPTION", new FieldMapper("propertyDescription"))
			.put("CONDITION_CODE", new FieldMapper("conditionCode")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("RECEIVED_FROM", new FieldMapper("receivedFrom"))
			.put("DISPOSED_TO_OFFENDER_FLAG", new FieldMapper("disposedToOffenderFlag"))
			.put("SERIAL_NO", new FieldMapper("serialNo"))
			.put("DISPOSED_TO_PERSON_ID", new FieldMapper("disposedToPersonId"))
			.put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("PROPERTY_ITEM_SEQ", new FieldMapper("propertyItemSeq"))
			.put("PROPERTY_ONLY_FLAG", new FieldMapper("propertyOnlyFlag")).put("CONTDES", new FieldMapper("contdes"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("PROPOSED_DISPOSAL_DATE", new FieldMapper("proposedDisposalDate"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("offInternalLocationId"))
			.put("CONTAINER_CODE", new FieldMapper("containerCode")).put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("SEAL_MARK", new FieldMapper("sealMark"))
			.put("TRN_FROM_AGY_LOC_ID", new FieldMapper("trnFromAgyLocId"))
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("DISPOSED_TO", new FieldMapper("disposedTo"))
			.put("DISPOSED_TO_NAME", new FieldMapper("disposedToName")).put("LOCDES", new FieldMapper("locdes"))
			.put("PROPERTY_SIZE", new FieldMapper("propertySize"))
			.build();

	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("FROM_STATUS_CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).put("COLOR", new FieldMapper("color"))
			.put("MODE", new FieldMapper("mode")).put("'1'", new FieldMapper("  '1' "))
			.put("MAKE", new FieldMapper("make")).build();

	private final Map<String, FieldMapper> ageLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", new FieldMapper("listSeq")).put("PPTY_AGY_LOC_ID", new FieldMapper("pptyAgyLocId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("TRN_TO_AGY_LOC_ID", new FieldMapper("trnToAgyLocId"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PPTY_AGY_LOC_ID", new FieldMapper("pptyAgyLocId"))
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId"))
			.put("GET_REF_DESCP('PPTY_CNTNR'", new FieldMapper("getRefDescp('pptyCntnr'"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("CONTAINER_DESC", new FieldMapper("dspDescription"))
			.put("CONTAINER_CODE", new FieldMapper("containerCode"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId")).put("CODE", new FieldMapper("code"))
			.put("DESCRIPTION", new FieldMapper("description")).build();

	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("PROFILE_VALUE", new FieldMapper(" profileValue "))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description)) "))
			.put("PRINT_FORMAT_CODE", new FieldMapper("printFormatCode")).build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode")).put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("LTRIM(RTRIM(DESCRIPTION))", new FieldMapper(" ltrim(rtrim(description)) "))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue")).put("DESCRIPTION", new FieldMapper("description"))
			.put("PRINT_FORMAT_CODE", new FieldMapper(" printFormatCode ")).build();

	private final Map<String, FieldMapper> corporateMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_ID", new FieldMapper("corporateId"))
			.put("CORPORATE_NAME)", new FieldMapper("corporateName")).put(" CASELOAD_ID", new FieldMapper("caseloadId"))
			.build();

	private final Map<String, FieldMapper> printerMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();
	private final Map<String, FieldMapper> refCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", 		new FieldMapper("code"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag")).build();

	/**
	 * Creates new OiddpropRepositoryImpl class Object
	 */
	public OiddpropRepositoryImpl() {
		// OiddpropRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPptyItemTxns
	 *
	 * @return List<OffenderPptyItemTxns>
	 *
	 */
	public List<OffenderPptyItemTxns> itmTxExecuteQuery(final OffenderPptyItemTxns objSearchDao) {
		final String sql = getQuery("OIDDPROP_ITMTX_FIND_OFFENDER_PPTY_ITEM_TXNS");
		final StringBuffer sqlQuery = new StringBuffer();
		String preparedSql;
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" Where ");
			if (objSearchDao.getPropertyItemTxnId() != null && objSearchDao.getPropertyItemTxnId() != 0) {
				sqlQuery.append("PROPERTY_ITEM_TXN_ID = :PROPERTY_ITEM_TXN_ID" + " and ");
				params.addValue("PROPERTY_ITEM_TXN_ID", objSearchDao.getPropertyItemTxnId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<OffenderPptyItemTxns> offPptyItemTxns = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItemTxns.class, offenderPptyItemTxnsMapping);
		final ArrayList<OffenderPptyItemTxns> returnList = (ArrayList<OffenderPptyItemTxns>) namedParameterJdbcTemplate
				.query(preparedSql, params, offPptyItemTxns);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderPptyItemTxns List<OffenderPptyItemTxns>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer itmTxInsertOffenderPptyItemTxns(final List<OffenderPptyItemTxns> offPptyItemTxns) {
		final String sql = getQuery("OIDDPROP_ITMTX_INSERT_OFFENDER_PPTY_ITEM_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItemTxns obj : offPptyItemTxns) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
			System.out.println(obj);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		System.out.print(returnArray);
		logger.info("DISPOSED->stepIn2" + offPptyItemTxns.size() + "" + returnArray.length);
		if (offPptyItemTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<AgencyLocations>
	 */
	public List<ReferenceCodes> cgfkOffConTrnToAgyLocIdRecordGroup(final String agyLocId) {
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(getQuery("OIDDPROP_GET_CORP_LIST"));
		final String sql = sqlQuery.toString().trim();
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> ageLocRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), ageLocRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyItemTxns List<OffenderPptyItemTxns>
	 *
	 */
	public Integer itmTxUpdateOffenderPptyItemTxns(final List<OffenderPptyItemTxns> offPptyItemTxns) {
		final String sql = getQuery("OIDDPROP_ITMTX_UPDATE_OFFENDER_PPTY_ITEM_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItemTxns obj : offPptyItemTxns) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (offPptyItemTxns.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPptyItems
	 *
	 * @return List<OffenderPptyItems>
	 *
	 */
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems objSearchDao) {
		final String sql = getQuery("OIDDPROP_OFFPI_FIND_OFFENDER_PPTY_ITEMS");
		final StringBuffer sqlQuery = new StringBuffer();
		String preparedSql = null;
		sqlQuery.append(sql);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID = :OFFENDER_BOOK_ID" + " and ");
				params.addValue("OFFENDER_BOOK_ID", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getStatusCode() != null) {
				sqlQuery.append("STATUS_CODE = :STATUS_CODE" + " and ");
				params.addValue("STATUS_CODE", objSearchDao.getStatusCode());
			}
			if (objSearchDao.getPropertyContainerId() != null) {
				sqlQuery.append("PROPERTY_CONTAINER_ID = :PROPERTY_CONTAINER_ID" + " and ");
				params.addValue("PROPERTY_CONTAINER_ID", objSearchDao.getPropertyContainerId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by  PROPERTY_ITEM_SEQ ");
		final RowMapper<OffenderPptyItems> offPptyItems = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				offenderPptyItemsMapping);
		final ArrayList<OffenderPptyItems> returnList = (ArrayList<OffenderPptyItems>) namedParameterJdbcTemplate
				.query(preparedSql, params, offPptyItems);
		return returnList;
	}

	public List<OffenderPptyItems> offConPiExecuteQuery(final OffenderPptyItems objSearchDao) {
		final String sql = getQuery("OIDDPROP_CON_OFFPI_FIND_OFFENDER_PPTY_ITEMS");
		final RowMapper<OffenderPptyItems> offPptyItems = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				offenderPptyItemsMapping);
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",
				objSearchDao.getOffenderBookId(), "agyLocId", objSearchDao.getAgyLocId()), offPptyItems);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyItems List<OffenderPptyItems>
	 *
	 */
	public Integer offPiUpdateOffenderPptyItems(final List<OffenderPptyItems> offPptyItems) {
		final String sql = getQuery("OIDDPROP_OFFPI_UPDATE_OFFENDER_PPTY_ITEMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItems obj : offPptyItems) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("", e);
		}
		logger.info("DISPOSED->stepIn3" + offPptyItems.size() + "" + returnArray.length);
		if (offPptyItems.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OIDDPROP_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> sysProRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams(), sysProRowMapper);
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OffenderPptyContainers>
	 */
	public List<OffenderPptyContainers> cgfkItmTxPropertyContainerRecordGroup(final String caseloadId,
			final Integer offenderBookId) {
		final String sql = getQuery("OIDDPROP_FIND_CGFKITMTXPROPERTYCONTAINER");
		List<OffenderPptyContainers> returnList = new ArrayList<>();
		final RowMapper<OffenderPptyContainers> offPptyConMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "OFFENDERBOOKID", offenderBookId), offPptyConMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<OffenderPptyContainers(selectCrp.corporateNameCrp.caseloadId>
	 */
	public List<Corporate> rgDspCorporateNameRecordGroup() {
		final String sql = getQuery("OIDDPROP_FIND_RGDSPCORPORATENAME");
		List<Corporate> returnList = new ArrayList<>();
		final RowMapper<Corporate> corpoRowMapper = Row2BeanRowMapper.makeMapping(sql, Corporate.class,
				corporateMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), corpoRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<Persons>
	 */
	public List<ReferenceCodes> rgDisposedToPersonRecordGroup() {
		final String sql = getQuery("OIDDPROP_FIND_RGDISPOSEDTOPERSON");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> personsRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), personsRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<PersonsStatusCode>
	 */
	public List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup() {
		final String sql = getQuery("OIDDPROP_FIND_CGFKITMTXFROMSTATUSCODE");
		List<ReferenceCodes> returnList = new ArrayList<>();
		final RowMapper<ReferenceCodes> refCodeMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("MODE", MODE), refCodeMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkItmTxItmTxOffCon
	 *
	 * @param params
	 *
	 */
	public OffenderPptyContainers cgfkchkItmTxItmTxOffCon(final OffenderPptyContainers paramBean) { // List<Object>
		final String sql = getQuery("OIDDPROP_CGFKCHK_ITM_TX_ITM_TX_OFF_CON");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		final OffenderPptyContainers returnObj = namedParameterJdbcTemplate
				.queryForObject(sql,
						createParams("PROPERTYCONTAINERID", paramBean.getPropertyContainerId(), "PPTYAGYLOCID",
								paramBean.getAgyLocId(), "OFFENDERBOOKID", paramBean.getOffenderBookId()),
						columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkItmTxItmTxRefCod
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkItmTxItmTxRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDDPROP_CGFKCHK_ITM_TX_ITM_TX_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffPiOffPiOffCon
	 *
	 * @param params
	 *
	 */
	public OffenderPptyContainers cgfkchkOffPiOffPiOffCon(final OffenderPptyContainers paramBean) {
		final String sql = getQuery("OIDDPROP_CGFKCHK_OFF_PI_OFF_PI_OFF_CON");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		final OffenderPptyContainers returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROPERTYCONTAINERID", paramBean.getPropertyContainerId()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffPiOffPiRefCod
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffPiOffPiRefCod(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDDPROP_CGFKCHK_OFF_PI_OFF_PI_REF_COD");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffPiOffPiRef
	 *
	 * @param params
	 *
	 */
	public ReferenceCodes cgfkchkOffPiOffPiRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDDPROP_CGFKCHK_OFF_PI_OFF_PI_REF_2");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams(paramBean),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * printReport
	 *
	 * @param params
	 *
	 */
	public OmsModules printReportOldgetPrintFormatCur(final OmsModules paramBean) {
		final String sql = getQuery("OIDDPROP_PRINT_REPORT_OLD_GET_PRINT_FORMAT_CUR");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		final OmsModules returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("MODULE_NAME", paramBean.getModuleName()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Printer
	 * @param CaseloadFormatPrinter paramBean
	 */
	public Printer printReportOldgetPrinterNameCur(final CaseloadFormatPrinter paramBean) {
		final String sql = getQuery("OIDDPROP_PRINT_REPORT_OLD_GET_PRINTER_NAME_CUR");
		final RowMapper<Printer> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Printer.class, printerMapping);
		final Printer returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD_ID",
				paramBean.getCaseloadId(), "PRINT_FORMAT_CODE", paramBean.getPrintFormatCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Printer
	 * @param CaseloadFormatPrinter paramBean
	 */
	public Printer printReportgetPrinterNameCur(final CaseloadFormatPrinter paramBean) {
		final String sql = getQuery("OIDDPROP_PRINT_REPORT_GET_PRINTER_NAME_CUR");
		final RowMapper<Printer> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Printer.class, printerMapping);
		final Printer returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CASELOAD_ID",
				paramBean.getCaseloadId(), "PRINT_FORMAT_CODE", paramBean.getPrintFormatCode()), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return SystemProfiles
	 * @param SystemProfiles paramBean
	 */
	public SystemProfiles printReportroleCur(final SystemProfiles paramBean) {
		final String sql = getQuery("OIDDPROP_PRINT_REPORT_ROLE_CUR");
		final RowMapper<SystemProfiles> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, SystemProfiles.class,
				systemProfilesMapping);
		final SystemProfiles returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROFILE_TYPE", paramBean.getProfileType(), "PROFILE_CODE", paramBean.getProfileCode()),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param pProfileCode
	 * @param pProfileType
	 */
	public Object printReportlSystemProfilesDescCur(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDDPROP_PRINT_REPORT_L_SYSTEM_PROFILES_DESC_CUR");
		Object returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForList(sql, createParams("PROFILE_TYPE",
					systemProfiles.getProfileType(), "PROFILE_CODE", systemProfiles.getProfileCode()), Object.class);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;

	}

	/**
	 * To get description of color param code
	 */
	public ReferenceCodes getDescriptionOfColor(final String code) {
		final String sql = getQuery("OIDDPROP_PRINT_COLOR_DESCRIPTION");
		ReferenceCodes returnObj = new ReferenceCodes();
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", code), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new ReferenceCodes();
		}
		return returnObj;
	}

	/**
	 * To get description of Condition param code
	 */
	public ReferenceCodes getDescriptionOfConditionCode(final String code) {
		final String sql = getQuery("OIDDPROP_PRINT_CONDITION_DESCRIPTION");
		ReferenceCodes returnObj = null;
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("CODE", code), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new ReferenceCodes();

		} catch (Exception e) {
			logger.error("", e);
		}
		return returnObj;
	}

	/**
	 * Method used to max(event_seq) from Offender_ppty_item_events table
	 */
	public Integer getEventSeq(Integer OffenderBookId) {
		final String sql = getQuery("OIDDPROP_GET_MAX_EVENT_SEQ_FROM_OFFENDER_PPTY_ITEM_EVENTS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderBookId", OffenderBookId), Integer.class);
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param OffenderPptyItemEvents List<offPptyItemEvents>
	 *
	 * @return List<Integer>
	 *
	 */
	public Integer itmTxInsertOffenderPptyItemEvents(final List<OffenderPptyItemEvents> offPptyItemEvents) {
		final String sql = getQuery("OIDDPROP_ITMTX_INSERT_OFFENDER_PPTY_ITEM_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItemEvents obj : offPptyItemEvents) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (offPptyItemEvents.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Method is called when disposedToOffenderFlag is Y. This method is used to
	 * update the records in the data base tables based on
	 *
	 * @param OffenderPptyContainers List<OffenderPptyContainers>
	 *
	 * @return Integer
	 *
	 */
	public Integer updateOffenderPptyContainers(final List<OffenderPptyContainers> updateList) {
		final String sql = getQuery("OIDDPROP_UPDATE_OFFCONT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyContainers obj : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		logger.info("DISPOSED->stepIn4" + updateList.size() + "" + returnArray.length);
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public List<OffenderPptyItems> getOffenderPptyDetails(final OffenderPptyContainers offenderPptyContainers) {
		final String sql = getQuery("OIDDPROP_GET_OFFPPTY_DETAILS");
		List<OffenderPptyItems> returnList = new ArrayList<>();
		final RowMapper<OffenderPptyItems> offPptyItemsMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(
					sql, createParams("OFFENDERBOOKID", offenderPptyContainers.getOffenderBookId(),
							"PROPERTYCONTAINERID", offenderPptyContainers.getPropertyContainerId()),
					offPptyItemsMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;

	}

	@Override
	public OffenderPptyItems getOldDateOfOffenderPptyItems(final OffenderPptyItems bean) {
		final String sql = getQuery("OIDDPROP_OFFPI_UPDATE_OFFENDER_PPTYITEMS_OLD_DATA");
		OffenderPptyItems returnList = null;
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROPERTY_ITEM_SEQ", bean.getPropertyItemSeq(), "OFFENDER_BOOK_ID",
							bean.getOffenderBookId()),
					new BeanPropertyRowMapper<OffenderPptyItems>(OffenderPptyItems.class));
		} catch (Exception e) {
			logger.error("getOldDateOfOffenderPptyItems", e);
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> getDisposedToPersonsGroup(Integer offenderBookId) {
		final String sql = getQuery("GET_OFFENDER_CONATCT_PERSONS");
	List<ReferenceCodes> returnList = new ArrayList<>();
	final RowMapper<ReferenceCodes> personsRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
			refCodesMapping);
	try {
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId), personsRowMapper);
	} catch (Exception e) {
		logger.error("", e);
	}
	return returnList;}
}
