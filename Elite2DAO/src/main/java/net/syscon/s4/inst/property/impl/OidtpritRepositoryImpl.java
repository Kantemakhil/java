package net.syscon.s4.inst.property.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ItemTransactionStatii;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.inst.property.OidtpritRepository;
import net.syscon.s4.inst.property.bean.CaseloadFormatPrinter;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Class OidtpritRepositoryImpl
 */
@Repository
public class OidtpritRepositoryImpl extends RepositoryBase implements OidtpritRepository {
	private static Logger logger = LogManager.getLogger(OidtpritRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offenderPptyItemTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("DISPOSED_TO_CORP_ID", new FieldMapper("disposedToCorpId"))
			.put("QUANTITY", new FieldMapper("quantity")).put("CONFIRM_FLAG", new FieldMapper("confirmFlag"))
			.put("PROPERTY_DESCRIPTION", new FieldMapper("propertyDescription"))
			.put("VERIFICATION_FLAG", new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("DISPOSED_TO_PERSON_ID", new FieldMapper("disposedToPersonId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("PROPERTY_ITEM_SEQ", new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", new FieldMapper("propertyContainerTxnId"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("DISPOSED_TO_PERSON", new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROPERTY_TYPE", new FieldMapper("propertyType"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId")).put("MAKE", new FieldMapper("make"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("COLOR", new FieldMapper("color"))
			.put("TO_STATUS_CODE", new FieldMapper("toStatusCode"))
			.put("CONDITION_CODE", new FieldMapper("conditionCode"))
			.put("PROPERTY_ITEM_TXN_ID", new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("FROM_STATUS_CODE", new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", new FieldMapper("disposedToOffenderFlag"))
			.put("RECEIVED_FROM", new FieldMapper("receivedFrom")).put("SERIAL_NO", new FieldMapper("serialNo"))
			.put("VERIFY_FLAG", new FieldMapper("verifyFlag")).put("EVENT_SEQ", new FieldMapper("eventSeq")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_STATUS_CODE", new FieldMapper("fromStatusCode"))
			.put("DESCRIPTION", new FieldMapper("description")).put("MODE", new FieldMapper("mode"))
			.put("REF_CODE.LIST_SEQ", new FieldMapper("refCode.listSeq"))
			.put("PROPERTY_TYPE", new FieldMapper("propertyType"))
			.put("TO_STATUS_CODE", new FieldMapper("itemStatusTo")).put("DSP_LIST_SEQ", new FieldMapper("listSeq"))
			.put("ITEM_STATUS_TO", new FieldMapper("itemToStatus")).build();

	private final Map<String, FieldMapper> offenderpptycontainersCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId"))
			.put("DSP_AGY_LOC_ID2", new FieldMapper("agyLocId")).put("DSP_ACTIVE_FLAG2", new FieldMapper("activeFlag"))
			.put("DSP_OFFENDER_BOOK_ID2", new FieldMapper("offenderBookId"))
			.put("DSP_PROPERTY_STORAGE_ID2", new FieldMapper("propertyStorageId"))
			.put("DSP_CONTAINER_CODE2", new FieldMapper("containerCode"))
			.put("CONTAINER_DESC", new FieldMapper("containerCode"))
			.put("DSP_DESCRIPTION2", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("CONTAINER_DESCRIPTION", new FieldMapper("itemToStatus")).build();

	private final Map<String, FieldMapper> systemProfilesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("DISPOSED_TO_CORP_ID", new FieldMapper("disposedToCorpId"))
			.put("QUANTITY", new FieldMapper("quantity")).put("CONFIRM_FLAG", new FieldMapper("confirmFlag"))
			.put("PROPERTY_DESCRIPTION", new FieldMapper("propertyDescription"))
			.put("VERIFICATION_FLAG", new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("DISPOSED_TO_PERSON_ID", new FieldMapper("disposedToPersonId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("PROPERTY_ITEM_SEQ", new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", new FieldMapper("propertyContainerTxnId"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("DISPOSED_TO_PERSON", new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROPERTY_TYPE", new FieldMapper("propertyType"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId")).put("MAKE", new FieldMapper("make"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("COLOR", new FieldMapper("color"))
			.put("TO_STATUS_CODE", new FieldMapper("toStatusCode"))
			.put("CONDITION_CODE", new FieldMapper("conditionCode"))
			.put("PROPERTY_ITEM_TXN_ID", new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("FROM_STATUS_CODE", new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", new FieldMapper("disposedToOffenderFlag"))
			.put("RECEIVED_FROM", new FieldMapper("receivedFrom")).put("SERIAL_NO", new FieldMapper("serialNo"))
			.put("VERIFY_FLAG", new FieldMapper("verifyFlag")).put("EVENT_SEQ", new FieldMapper("eventSeq")).build();
	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFF_CON4.PROPERTY_STORAGE_ID", new FieldMapper("offCon4.propertyStorageId"))
			.put("PPTY_STG6.DESCRIPTION", new FieldMapper("pptyStg6.description"))
			.put("PPTY_AGY_LOC_ID", new FieldMapper("pptyAgyLocId"))
			.put("PPTY_STG7.DESCRIPTION", new FieldMapper("pptyStg7.description"))
			.put("OFF_CON2.CONTAINER_CODE --", new FieldMapper("offCon2.containerCode --"))
			.put("PPTY_STG3.DESCRIPTION", new FieldMapper("pptyStg3.description"))
			.put("OFF_CON3.CONTAINER_CODE --", new FieldMapper("offCon3.containerCode --"))
			.put("OFF_CON2.PROPERTY_STORAGE_ID", new FieldMapper("offCon2.propertyStorageId"))
			.put("SEAL_MARK", new FieldMapper("sealMark"))
			.put("OFF_CON3.PROPERTY_STORAGE_ID", new FieldMapper("offCon3.propertyStorageId"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId"))
			.put("OFF_CON3.AGY_LOC_ID", new FieldMapper("offCon3.agyLocId"))
			.put("OFF_CON3.ACTIVE_FLAG", new FieldMapper("offCon3.activeFlag"))
			.put("OFF_CON3.OFFENDER_BOOK_ID", new FieldMapper("offCon3.offenderBookId"))
			.put("DSP_AGY_LOC_ID2", new FieldMapper("dspAgyLocId2"))
			.put("OFF_CON2.ACTIVE_FLAG", new FieldMapper("offCon2.activeFlag"))
			.put("OFF_CON2.OFFENDER_BOOK_ID", new FieldMapper("offCon2.offenderBookId"))
			.put("OFF_CON2.AGY_LOC_ID", new FieldMapper("offCon2.agyLocId"))
			.put("TO_PROPERTY_CONTAINER_ID", new FieldMapper("toPropertyContainerId")).build();

	private final Map<String, FieldMapper> itemTransactionStatiiMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("FROM_STATUS_CODE", new FieldMapper("fromStatusCode"))
			.put("TO_STATUS_CODE", new FieldMapper("toStatusCode"))
			.put("ITM_STS.LIST_SEQ", new FieldMapper("itmSts.listSeq")).build();

	private final Map<String, FieldMapper> offenderPptyItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE", new FieldMapper("profileCode"))
			.put("DISPOSED_TO_CORP_ID", new FieldMapper("disposedToCorpId"))
			.put("QUANTITY", new FieldMapper("quantity")).put("CONFIRM_FLAG", new FieldMapper("confirmFlag"))
			.put("PROPERTY_DESCRIPTION", new FieldMapper("propertyDescription"))
			.put("VERIFICATION_FLAG", new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", new FieldMapper("sealFlag")).put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE", new FieldMapper("profileValue"))
			.put("DISPOSED_TO_PERSON_ID", new FieldMapper("disposedToPersonId"))
			.put("DESCRIPTION", new FieldMapper("description")).put("STATUS_CODE", new FieldMapper("statusCode"))
			.put("PROPERTY_ITEM_SEQ", new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", new FieldMapper("commentText")).put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", new FieldMapper("propertyContainerTxnId"))
			.put("PROFILE_TYPE", new FieldMapper("profileType"))
			.put("DISPOSED_TO_PERSON", new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME", new FieldMapper("oldTableName"))
			.put("PROPERTY_TYPE", new FieldMapper("propertyType"))
			.put("PROPERTY_CONTAINER_ID", new FieldMapper("propertyContainerId")).put("MAKE", new FieldMapper("make"))
			.put("PROFILE_VALUE_2", new FieldMapper("profileValue2")).put("COLOR", new FieldMapper("color"))
			.put("TO_STATUS_CODE", new FieldMapper("toStatusCode"))
			.put("CONDITION_CODE", new FieldMapper("conditionCode"))
			.put("PROPERTY_ITEM_TXN_ID", new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("FROM_STATUS_CODE", new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", new FieldMapper("disposedToOffenderFlag"))
			.put("RECEIVED_FROM", new FieldMapper("receivedFrom")).put("SERIAL_NO", new FieldMapper("serialNo"))
			.put("VERIFY_FLAG", new FieldMapper("verifyFlag")).put("EVENT_SEQ", new FieldMapper("eventSeq")).build();

	/**
	 * Creates new OidtpritRepositoryImpl class Object
	 */
	public OidtpritRepositoryImpl() {
		// OidtpritRepositoryImpl();
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkItmTxFromStatusCodeRecordGroup() {
		final String sql = getQuery("OIDTPRIT_FIND_CGFKITMTXFROMSTATUSCODE");
		String enterQuery = "QUERY";
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql, createParams("MODE", enterQuery), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param itemTransactionStatii
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkItmTxToStatusCodeRecordGroup(final ItemTransactionStatii itemTransactionStatii) {
		final String sql = getQuery("OIDTPRIT_FIND_CGFKITMTXTOSTATUSCODE");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("FROMSTATUSCODE", itemTransactionStatii.getItemStatusFrom()), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param offenderPptyContainers
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> cgfkItmTxPropertyContainerRecordGroup(final String caseloadId,
			final Integer offenderBookId) {
		final String sql = getQuery("OIDTPRIT_FIND_CGFKITMTXPROPERTYCONTAINER");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, offenderpptycontainersCodesMapping);
		try {
			return namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId, "OFFENDERBOOKID", offenderBookId), referenceCodeRowMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param offenderPptyItemTxns
	 *
	 * @return List<OffenderPptyItemTxns>
	 *
	 * @
	 */
	public List<OffenderPptyItemTxns> itmTxSearchOffenderPptyItemTxns(final OffenderPptyItemTxns offenderPptyItemTxns) {
		final String sql = getQuery("OIDTPRIT_ITMTX_FIND_OFFENDER_PPTY_ITEM_TXNS");
		String preparedSql = null;
		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);
		final MapSqlParameterSource params = new MapSqlParameterSource();
		if (offenderPptyItemTxns != null) {
			sqlQuery.append(" where ");
			if (offenderPptyItemTxns.getPropertyItemTxnId() != null) {
				sqlQuery.append("PROPERTY_ITEM_TXN_ID =:propertyItemTxnID " + " and ");
				params.addValue("propertyItemTxnID", offenderPptyItemTxns.getPropertyItemTxnId());
			}
			if (offenderPptyItemTxns.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID =:offenderBookID " + " and ");
				params.addValue("offenderBookID", offenderPptyItemTxns.getOffenderBookId());
			}
			if (offenderPptyItemTxns.getPropertyItemSeq() != null) {
				sqlQuery.append("PROPERTY_ITEM_SEQ = ? " + " and ");
			}
			if (offenderPptyItemTxns.getEventSeq() != null) {
				sqlQuery.append("EVENT_SEQ = ? " + " and ");
			}
			if (offenderPptyItemTxns.getFromStatusCode() != null) {
				sqlQuery.append("FROM_STATUS_CODE = :fromStatusCode " + " and ");
				params.addValue("fromStatusCode", offenderPptyItemTxns.getFromStatusCode());
			}
			if (offenderPptyItemTxns.getToStatusCode() != null) {
				sqlQuery.append("TO_STATUS_CODE = ? " + " and ");
			}
			if (offenderPptyItemTxns.getCreateDate() != null) {
				sqlQuery.append("CREATE_DATE = ? " + " and ");
			}
			if (offenderPptyItemTxns.getCreateUserId() != null) {
				sqlQuery.append("CREATE_USER_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getCommentText() != null) {
				sqlQuery.append("COMMENT_TEXT = ? " + " and ");
			}
			if (offenderPptyItemTxns.getAgyLocId() != null) {
				sqlQuery.append("AGY_LOC_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getDisposedToOffenderFlag() != null) {
				sqlQuery.append("DISPOSED_TO_OFFENDER_FLAG = ? " + " and ");
			}
			if (offenderPptyItemTxns.getVerificationFlag() != null) {
				sqlQuery.append("VERIFICATION_FLAG = ? " + " and ");
			}
			if (offenderPptyItemTxns.getVerifyFlag() != null) {
				sqlQuery.append("VERIFY_FLAG = ? " + " and ");
			}
			if (offenderPptyItemTxns.getDisposedToCorpId() != null) {
				sqlQuery.append("DISPOSED_TO_CORP_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getDisposedToPerson() != null) {
				sqlQuery.append("DISPOSED_TO_PERSON = ? " + " and ");
			}
			if (offenderPptyItemTxns.getPropertyContainerId() != null) {
				sqlQuery.append("PROPERTY_CONTAINER_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getPropertyContainerTxnId() != null) {
				sqlQuery.append("PROPERTY_CONTAINER_TXN_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getToPropertyContainerId() != null) {
				sqlQuery.append("TO_PROPERTY_CONTAINER_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getDisposedToPersonId() != null) {
				sqlQuery.append("DISPOSED_TO_PERSON_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getColor() != null) {
				sqlQuery.append("COLOR = ? " + " and ");
			}
			if (offenderPptyItemTxns.getMake() != null) {
				sqlQuery.append("MAKE = ? " + " and ");
			}
			if (offenderPptyItemTxns.getSerialNo() != null) {
				sqlQuery.append("SERIAL_NO = ? " + " and ");
			}
			if (offenderPptyItemTxns.getCreateDatetime() != null) {
				sqlQuery.append("CREATE_DATETIME = ? " + " and ");
			}
			if (offenderPptyItemTxns.getModifyDatetime() != null) {
				sqlQuery.append("MODIFY_DATETIME = ? " + " and ");
			}
			if (offenderPptyItemTxns.getModifyUserId() != null) {
				sqlQuery.append("MODIFY_USER_ID = ? " + " and ");
			}
			if (offenderPptyItemTxns.getSealFlag() != null) {
				sqlQuery.append("SEAL_FLAG = ? " + " and ");
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		final RowMapper<OffenderPptyItemTxns> OffenderPptyItemTxnsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItemTxns.class, offenderPptyItemTxnsMapping);
		final ArrayList<OffenderPptyItemTxns> returnList = (ArrayList<OffenderPptyItemTxns>) namedParameterJdbcTemplate
				.query(sql, params, OffenderPptyItemTxnsRowMapper);
		return returnList;
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based on
	 *
	 * @param lstOffenderPptyItemTxns
	 *
	 * @return Integer
	 *
	 * @
	 */
	public Integer itmTxInsertOffenderPptyItemTxns(List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		String sql = getQuery("OIDTPRIT_ITMTX_INSERT_OFFENDER_PPTY_ITEM_TXNS");
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
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyItemTxns
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer itmTxUpdateOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		final String sql = getQuery("OIDTPRIT_ITMTX_UPDATE_OFFENDER_PPTY_ITEM_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItemTxns offenderPptyItemTxns : lstOffenderPptyItemTxns) {
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
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderPptyItemTxns
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer itmTxDeleteOffenderPptyItemTxns(final List<OffenderPptyItemTxns> lstOffenderPptyItemTxns) {
		final String sql = getQuery("OIDTPRIT_ITMTX_DELETE_OFFENDER_PPTY_ITEM_TXNS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItemTxns offenderPptyItemTxns : lstOffenderPptyItemTxns) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyItemTxns));
		}
		try {
			String tableName = "OFFENDER_PPTY_ITEM_TXNS";
			String whereCondition = "PROPERTY_ITEM_TXN_ID = :propertyItemTxnId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
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
	 *
	 * @param offenderPptyItems
	 *
	 * @return List<OffenderPptyItems>
	 *
	 * @
	 */
	public List<OffenderPptyItems> offPiSearchOffenderPptyItems(final OffenderPptyItems offenderPptyItems) {
		final String sql = getQuery("OIDTPRIT_OFFPI_FIND_OFFENDER_PPTY_ITEMS");
		final RowMapper<OffenderPptyItems> OffenderPptyItemsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", offenderPptyItems.getOffenderBookId(), "CASELOADID",
						offenderPptyItems.getAgyLocId(), "STATUSCODE", offenderPptyItems.getStatusCode(),
						"PROPERTYCONATINERID", offenderPptyItems.getPropertyContainerId()),
				OffenderPptyItemsRowMapper);
		return returnList;
	}

	public List<OffenderPptyItems> offPiPropertyContainerIdOffenderPptyItems(
			final OffenderPptyItems offenderPptyItems) {
		final String sql = getQuery("OIDTPRIT_OFFPI_OFFENDER_PPTY_ITEMS");
		final RowMapper<OffenderPptyItems> OffenderPptyItemsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", offenderPptyItems.getOffenderBookId(), "CASELOADID",
						offenderPptyItems.getAgyLocId(), "STATUSCODE", offenderPptyItems.getStatusCode(),
						"PROPERTYCONATINERID", offenderPptyItems.getPropertyContainerId()),
				OffenderPptyItemsRowMapper);
		return returnList;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyItems
	 * 
	 * @return Integer
	 *
	 * @
	 */
	public Integer offPiUpdateOffenderPptyItems(final List<OffenderPptyItems> lstOffenderPptyItems) {
		final String sql = getQuery("OIDTPRIT_OFFPI_UPDATE_OFFENDER_PPTY_ITEMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItems offenderPptyItems : lstOffenderPptyItems) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyItems));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPptyItems.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param systemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @
	 */
	public List<SystemProfiles> sysPflSearchSystemProfiles(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDTPRIT_SYSPFL_FIND_SYSTEM_PROFILES");
		final RowMapper<SystemProfiles> SystemProfilesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				SystemProfiles.class, systemProfilesMapping);
		final ArrayList<SystemProfiles> returnList = (ArrayList<SystemProfiles>) namedParameterJdbcTemplate.query(sql,
				createParams("profileType", systemProfiles.getProfileType(), "profileCode",
						systemProfiles.getProfileCode()),
				SystemProfilesRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 *
	 * @param offenderPptyItemTxns
	 * @return List<Object>
	 *
	 */
	public List<Object> vPheadOnCheckDeleteMasteritmTxCur(final OffenderPptyItemTxns offenderPptyItemTxns) {
		final String sql = getQuery("OIDTPRIT_V_PHEAD_ONCHECKDELETEMASTER_ITM_TX_CUR");
		final ArrayList<Object> returnList = (ArrayList<Object>) namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDERBOOKID", offenderPptyItemTxns.getOffenderBookId()), Object.class);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param OffenderPptyContainers
	 * @return OffenderPptyContainers
	 */
	public OffenderPptyContainers oidtpritItmTxWhenValidateRecordregItems(
			final OffenderPptyContainers OffenderPptyContainers) {
		final String sql = getQuery("OIDTPRIT_ITM_TX_WHENVALIDATERECORD_REG_ITEMS");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		OffenderPptyContainers returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROPERTYCONTAINERID", OffenderPptyContainers.getPropertyContainerId(), "DSPAGYLOCID2",
						OffenderPptyContainers.getAgyLocId()),
				columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @param offenderPptyContainers
	 * @return OffenderPptyContainers
	 *
	 */
	public OffenderPptyContainers itmTxWhenValidateRecordregItems(final OffenderPptyContainers offenderPptyContainers) {
		OffenderPptyContainers returnObj = new OffenderPptyContainers();
		final String sql = getQuery("OIDTPRIT_ITM_TX_WHENVALIDATERECORD_REG_ITEMS1");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		try {
			returnObj = (OffenderPptyContainers) namedParameterJdbcTemplate.queryForObject(sql,
					createParams("PROPERTYCONTAINERID", offenderPptyContainers.getPropertyContainerId(), "AGYLOCID",
							offenderPptyContainers.getAgyLocId()),
					columnRowMapper);
		} catch (Exception e) {

		}
		if (returnObj == null) {
			returnObj = new OffenderPptyContainers();
			;
		}
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 * @param referenceCodes
	 *
	 */
	public Object offPiPostQuerycPropertyTypeDesc(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OIDTPRIT_OFF_PI_POSTQUERY_C_PROPERTY_TYPE_DESC");
		Object returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("CODE", referenceCodes.getCode()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param offenderPptyContainers
	 *
	 */
	public List<OffenderPptyContainers> cgfkchkItmTxItmTxOffConc(final OffenderPptyContainers offenderPptyContainers) {
		final String sql = getQuery("OIDTPRIT_CGFKCHK_ITM_TX_ITM_TX_OFF_CON_C");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		List<OffenderPptyContainers> returnObj = new ArrayList<>();
		returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param offenderPptyContainers
	 *
	 */
	public List<OffenderPptyContainers> oidtpritCgfkchkItmTxItmTxOff2c(
			final OffenderPptyContainers offenderPptyContainers) {
		final String sql = getQuery("OIDTPRIT_CGFKCHK_ITM_TX_ITM_TX_OFF_2_C");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		List<OffenderPptyContainers> returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return ItemTransactionStatii
	 * @param itemTransactionStatii
	 *
	 */
	public ItemTransactionStatii oidtpritCgfkchkItmTxItmTxItmTs(final ItemTransactionStatii itemTransactionStatii) {
		final String sql = getQuery("OIDTPRIT_CGFKCHK_ITM_TX_ITM_TX_ITM_TS__C");
		final RowMapper<ItemTransactionStatii> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ItemTransactionStatii.class, itemTransactionStatiiMapping);
		ItemTransactionStatii returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams(itemTransactionStatii), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param referenceCodes
	 *
	 */
	public List<Object> cgfkchkItmTxItmTxRefCodc(final ReferenceCodes referenceCodes) {
		final String sql = getQuery("OIDTPRIT_CGFKCHK_ITM_TX_ITM_TX_REF_COD_C");
		String enterQuery = "ENTER-QUERY";
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("FROMSTATUSCODE", referenceCodes.getCode(), "MODE", enterQuery), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param offenderPptyContainers
	 *
	 */
	public List<OffenderPptyContainers> cgfkchkOffPiOffPiOffConc(final OffenderPptyContainers offenderPptyContainers) {
		final String sql = getQuery("OIDTPRIT_CGFKCHK_OFF_PI_OFF_PI_OFF_CON_C");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		List<OffenderPptyContainers> returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param omsModules
	 *
	 */
	public List<Object> printReportOldgetPrintFormatCur(final OmsModules omsModules) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_OLD_GET_PRINT_FORMAT_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("NAME", omsModules.getModuleName()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param caseloadFormatPrinters
	 *
	 */
	public List<Object> printReportOldgetPrinterNameCur(final CaseloadFormatPrinter caseloadFormatPrinters) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_OLD_GET_PRINTER_NAME_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("CASELOADID",
				caseloadFormatPrinters.getCaseloadId(), "PRINTFORMATCODE", caseloadFormatPrinters.getPrintFormatCode()),
				Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param systemProfiles
	 *
	 */
	public List<Object> runReportOnTheWebroleCur(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDTPRIT_RUN_REPORT_ON_THE_WEB_002_ROLE_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return OmsModules
	 * @param omsModules
	 *
	 */
	public List<Object> runReportOnTheWebgetPrintFormatCur(final OmsModules omsModules) {
		final String sql = getQuery("OIDTPRIT_RUN_REPORT_ON_THE_WEB_002_GET_PRINT_FORMAT_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql,
				createParams("RNAME", omsModules.getModuleName()), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param omsModules
	 *
	 */
	public List<Object> printReportgetPrintFormatCur(final OmsModules omsModules) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_GET_PRINT_FORMAT_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param caseloadFormatPrinters
	 *
	 */
	public List<Object> printReportgetPrinterNameCur(final CaseloadFormatPrinter caseloadFormatPrinters) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_GET_PRINTER_NAME_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("CASELOADID",
				caseloadFormatPrinters.getCaseloadId(), "PRINTFORMATCODE", caseloadFormatPrinters.getPrintFormatCode()),
				Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param systemProfiles
	 *
	 */
	public List<Object> printReportroleCur(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_ROLE_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param systemProfiles
	 *
	 */
	public List<Object> printReportrolePwd(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_ROLE_PWD");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param systemProfiles
	 *
	 */
	public List<Object> printReportlReportAlias(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_L_REPORT_ALIAS");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams(), Object.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return List<Object>
	 * @param systemProfiles
	 *
	 */
	public List<Object> printReportlSystemProfilesDescCur(final SystemProfiles systemProfiles) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT_L_SYSTEM_PROFILES_DESC_CUR");
		List<Object> returnObj = namedParameterJdbcTemplate.queryForList(sql, createParams("P_PROFILE_CODE",
				systemProfiles.getProfileCode(), "P_PROFILE_TYPE", systemProfiles.getProfileType()), Object.class);
		return returnObj;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @param params
	 * @return List<RecordGroup>
	 */
	public List<ReferenceCodes> cgfkItmTxPropertyLocationRgroup(final String caseloadid, final Integer offenderbookid) {
		final String sql = getQuery("OIDTPRIT_PRINT_REPORT");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				systemProfilesMapping);
		List<ReferenceCodes> returnObj = namedParameterJdbcTemplate.query(sql,
				createParams("CASELOADID", caseloadid, "OFFENDERBOOKID", offenderbookid), columnRowMapper);
		return returnObj;
	}

	/**
	 * To get description of propertycode param code
	 */
	public ReferenceCodes getDescriptionOfPropertyCode(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_PROPERTY_TYPE_DESCRIPTION");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("propertyType", code),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("Breaking From getDescriptionOfPropertyCode " + e.getLocalizedMessage());
		}
		return returnObj;
	}

	/**
	 * To get description of color param code
	 */
	public ReferenceCodes getDescriptionOfColor(final String code) {
		String sql = getQuery("OIDTPRIT_ITM_TX_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);

		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("color", code),
				columnRowMapper);

		return returnObj;
	}

	/**
	 * To get description of conditioncode param code
	 */
	public ReferenceCodes getDescriptionOfConditionCode(final String code) {
		final String sql = getQuery("OIDTPRIT_ITM_TX_CONDITION_POSTQUERY");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		ReferenceCodes returnObj = null;
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("conditionCode", code),
					columnRowMapper);
		} catch (Exception e) {
			logger.error("Breaking Inside of getDescriptionOfConditionCode " + e.getLocalizedMessage());
		}

		return returnObj;
	}

	/**
	 * To get description of propertycode param code
	 */
	public ReferenceCodes getCodeOfPropertyCode(final String description) {
		final String sql = getQuery("OIDTPRIT_PRINT_PROPERTY_TYPE_DESCRIPTION_CODE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("propertyType", description), columnRowMapper);
		return returnObj;
	}

	/**
	 * To get description of conditioncode param code
	 */
	public ReferenceCodes getCodeOfConditionCode(final String description) {
		final String sql = getQuery("OIDTPRIT_ITM_TX_CONDITION_POSTQUERY_CODE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("conditionCode", description), columnRowMapper);
		return returnObj;
	}

	/**
	 * To get description of color param code
	 */
	public ReferenceCodes getCodeOfColor(final String description) {
		final String sql = getQuery("OIDTPRIT_ITM_TX_POSTQUERY_CODE");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("color", description), columnRowMapper);
		return returnObj;
	}

	@Override
	public OffenderPptyItems offPiUpdateOffenderPptyItems(OffenderPptyItems bean) {
		final String sql = getQuery("OIDTPRIT_OFFPI_UPDATE_OFFENDER_PPTY_ITEMS_OLD_DATA");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID",bean.getOffenderBookId(),"PRIORITY_SEQ",bean.getPropertyItemSeq()),
				new BeanPropertyRowMapper<OffenderPptyItems>(OffenderPptyItems.class));
	}
}
