package net.syscon.s4.inst.property.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CorporateAddressV;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.OiiptranRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Class OiiptranRepositoryImpl
 */
@Repository
public class OiiptranRepositoryImpl extends RepositoryBase implements OiiptranRepository {
	private static Logger logger = LogManager.getLogger(OiiptranRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> offenderPptyItemTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DISPOSED_TO_CORP_ID", 			new FieldMapper("disposedToCorpId"))
			.put("VERIFICATION_FLAG", 				new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID", 		new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG", 						new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 				new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 				new FieldMapper("modifyDatetime"))
			.put("DISPOSED_TO_PERSON_ID", 			new FieldMapper("disposedToPersonId"))
			.put("PROPERTY_ITEM_SEQ", 				new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT", 					new FieldMapper("commentText"))
			.put("CREATE_USER_ID", 					new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", 		new FieldMapper("propertyContainerTxnId"))
			.put("DISPOSED_TO_PERSON", 				new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID", 					new FieldMapper("modifyUserId"))
			.put("PROPERTY_CONTAINER_ID", 			new FieldMapper("propertyContainerId"))
			.put("MAKE", 							new FieldMapper("make"))
			.put("COLOR", 							new FieldMapper("color"))
			.put("TO_STATUS_CODE", 					new FieldMapper("toStatusCode"))
			.put("PROPERTY_ITEM_TXN_ID", 			new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("FROM_STATUS_CODE", 				new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE", 					new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", 		new FieldMapper("disposedToOffenderFlag"))
			.put("SERIAL_NO", 						new FieldMapper("serialNo"))
			.put("VERIFY_FLAG", 					new FieldMapper("verifyFlag"))
			.put("EVENT_SEQ", 						new FieldMapper("eventSeq"))
			.put("DISPOSED_TO_CORP_NAME", 			new FieldMapper("disposedToCorpName"))
			.build();
	private final Map<String, FieldMapper> corporateAddressVMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CORPORATE_NAME", 					new FieldMapper(" corporateName "))
			.put("DISPOSED_TO_CORP_ID", 			new FieldMapper("disposedToCorpId")).build();
	private final Map<String, FieldMapper> offenderPptyItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", 						new FieldMapper("agyLocId"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("RECEIVED_FROM", 					new FieldMapper("receivedFrom"))
			.put("DISPOSED_TO_OFFENDER_FLAG", 		new FieldMapper("disposedToOffenderFlag"))
			.put("SERIAL_NO", 						new FieldMapper("serialNo"))
			.put("PROPERTY_SIZE", 					new FieldMapper("propertySize"))
			.put("PROPERTY_VALUE", 					new FieldMapper("propertyValue"))
			.build();
	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_ITEM_SEQ", 				new FieldMapper("propertyItemSeq"))
			.put("PROPERTY_CONTAINER_ID", 			new FieldMapper("propertyContainerId"))
			.put("OFFENDER_BOOK_ID", 				new FieldMapper("offenderBookId"))
			.put("INTERNAL_LOCATION_ID", 			new FieldMapper("internalLocationId")).build();
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("LIST_SEQ", 						new FieldMapper("listSeq"))
			.put("FROM_STATUS_CODE", 				new FieldMapper("code"))
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("COLOR", 							new FieldMapper("color"))
			.put("MODE", 							new FieldMapper("mode"))
			.put("'1'", 							new FieldMapper("  '1' "))
			.put("MAKE",							new FieldMapper("make"))
			.put("CODE", 							new FieldMapper("toStatusCode"))
			.build();
	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 					new FieldMapper("description"))
			.put("INTERNALLOCATIONID", 				new FieldMapper("internalLocationId")).build();
	private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_OBJECT_SEQ", 		new FieldMapper("imageObjectSeq"))
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("IMAGE_OBJECT_TYPE", 		new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 		new FieldMapper("imageObjectId"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.build();
	
	/**
	 * Creates new OiiptranRepositoryImpl class Object
	 */
	public OiiptranRepositoryImpl() {
		// OiiptranRepositoryImpl
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
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems objSearchDao) {
		final String sql = getQuery("OIIPTRAN_OFFPI_FIND_OFFENDER_PPTY_ITEMS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID =  :offenderBookId " + " and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			if (objSearchDao.getStatusCode() != null) {
				sqlQuery.append("STATUS_CODE =  :statusCode " + " and ");
				valuesList.addValue("statusCode", objSearchDao.getStatusCode());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by PROPERTY_ITEM_SEQ DESC";
		final RowMapper<OffenderPptyItems> offenderRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		List<OffenderPptyItems> refList = new ArrayList<>();
		refList = namedParameterJdbcTemplate.query(preparedSql, valuesList, offenderRowMapper);
		return refList;
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
		final String sql = getQuery("OIIPTRAN_ITMTX_FIND_OFFENDER_PPTY_ITEM_TXNS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource valuesList = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao.getPropertyItemSeq() != null) {
				sqlQuery.append("PROPERTY_ITEM_SEQ =  :propertyItemSeq " + " and ");
				valuesList.addValue("propertyItemSeq", objSearchDao.getPropertyItemSeq());
			}
			if (objSearchDao.getOffenderBookId() != null) {
				sqlQuery.append("OFFENDER_BOOK_ID =  :offenderBookId " + " and ");
				valuesList.addValue("offenderBookId", objSearchDao.getOffenderBookId());
			}
			sqlQuery.append(" VERIFY_FLAG = 'N' ");
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql + " order by CREATE_DATE DESC ";
		final RowMapper<OffenderPptyItemTxns> OffenderPptyItemTxnsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				OffenderPptyItemTxns.class, offenderPptyItemTxnsMapping);
		List<OffenderPptyItemTxns> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.query(preparedSql, valuesList, OffenderPptyItemTxnsRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * vPheadOnCheckDeleteMaster
	 *
	 * @param params
	 *
	 */
	public List<OffenderPptyItems> vPheadOnCheckDeleteMaster(final OffenderPptyItems paramBean) {
		final String sql = getQuery("OIIPTRAN_V_PHEAD_ONCHECKDELETEMASTER");
		final RowMapper<OffenderPptyItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				offenderPptyItemsMapping);
		List<OffenderPptyItems> returnObj = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkItmTxItmTxCorpF
	 *
	 * @param params
	 *
	 */
	public List<CorporateAddressV> cgfkchkItmTxItmTxCorp(final CorporateAddressV paramBean) {
		final String sql = getQuery("OIIPTRAN_CGFKCHK_ITM_TX_ITM_TX_CORP_F1");
		final RowMapper<CorporateAddressV> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, CorporateAddressV.class,
				corporateAddressVMapping);
		final ArrayList<CorporateAddressV> returnList = (ArrayList<CorporateAddressV>) namedParameterJdbcTemplate
				.query(sql, createParams(), columnRowMapper);
		return returnList;
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
		final String sql = getQuery("OIIPTRAN_CGFKCHK_OFF_PI_OFF_PI_OFF_CON");
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		logger.info("OFFENDERBOOKID"+ paramBean.getOffenderBookId()+" "+"PROPERTYCONTAINERID"+
				paramBean.getPropertyContainerId()+" "+"PROPERTYITEMSEQ" +paramBean.getPropertyItemSeq());
		OffenderPptyContainers returnObj=null;
		try {
		 returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("OFFENDERBOOKID", paramBean.getOffenderBookId(), "PROPERTYCONTAINERID",
						paramBean.getPropertyContainerId(), "PROPERTYITEMSEQ", paramBean.getPropertyItemSeq()),
				columnRowMapper);
		}catch (Exception e) {
			logger.info("Error While Noresult found in cgfkchkOffPiOffPiOffCon"+e.getLocalizedMessage());
		}
		return returnObj;
	}

	/**
	 * To get description of tostatus param code
	 */
	public ReferenceCodes getDescriptionOfToStatus(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_TO_STATUS_DESCRIPTION");
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceCodesMapping);
		final ReferenceCodes returnObj = namedParameterJdbcTemplate.queryForObject(sql,
				createParams("toStatusCode", code), columnRowMapper);
		return returnObj;
	}

	/**
	 * To get description of received from param code
	 */
	public ReferenceCodes getDescriptionOfReceivedForm(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_RECEIVED_FORM_DESCRIPTION");
		ReferenceCodes returnObj = new ReferenceCodes();
		try {
			final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodesMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("receivedFrom", code), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " getDescriptionOfReceivedForm: ", e);
		}
		return returnObj;
	}

	/**
	 * To get description of propertycode param code
	 */
	public ReferenceCodes getDescriptionOfPropertyCode(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_PROPERTY_TYPE_DESCRIPTION");
		ReferenceCodes returnObj = new ReferenceCodes();
		try {
			final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodesMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("propertyType", code), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " getDescriptionOfPropertyCode: ", e);
		}
		return returnObj;
	}

	/**
	 * To get description of color param code
	 */
	public ReferenceCodes getDescriptionOfColor(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_COLOR_DESCRIPTION");
		ReferenceCodes returnObj = new ReferenceCodes();
		try { 
			final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodesMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("color", code),
					columnRowMapper);
		} catch(Exception e) {
			logger.error(this.getClass().getName(), " getDescriptionOfColor: ", e);
		}
		return returnObj;
	}

	/**
	 * To get description of conditioncode param code
	 */
	public ReferenceCodes getDescriptionOfConditionCode(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_CONDITION_CODE_DESCRIPTION");
		ReferenceCodes returnObj = new ReferenceCodes();
		try {
			final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodesMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("conditionCode", code), columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " getDescriptionOfConditionCode: ", e);
		}
		return returnObj;
	}

	/**
	 * To get description of agylocid param code
	 */
	public ReferenceCodes getDescriptionOfAgyLocId(final String code) {
		final String sql = getQuery("OIIPTRAN_PRINT_AGY_LOC_ID_DESCRIPTION");
		ReferenceCodes returnObj = new ReferenceCodes();
		try {
			final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
					referenceCodesMapping);
			returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("agyLocId", code),
					columnRowMapper);
		} catch (Exception e) {
			logger.error(this.getClass().getName(), " getDescriptionOfAgyLocId: ", e);
		}
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
	 * Fetch the records from database table
	 */
	public List<String> findReceivedList() {
		final String sql = getQuery("OIIPTRAN_PPTY_REC_FRM_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findTypeList() {
		final String sql = getQuery("OIIPTRAN_PPTY_TYPE_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findColorList() {
		final String sql = getQuery("OIIPTRAN_PPTY_COLOR_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findConditionList() {
		final String sql = getQuery("OIIPTRAN_PPTY_CONDIT_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findFacilityList() {
		final String sql = getQuery("OIIPTRAN_FACILITY_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams(), String.class);
	}

	/**
	 * Fetch the records from database table
	 */
	public List<String> findagyLocationList(String caseLoadId) {
		final String sql = getQuery("OIIPTRAN_AGY_LOCATION_LIST");
		return namedParameterJdbcTemplate.queryForList(sql, createParams("caseLoadId", caseLoadId), String.class);
	}

	public List<Images> offPiImagesExecuteQuery(final Images searchBean) {
		final String sql = getQuery("OIIPTRAN_IMAGES");
		List<Images> returnList = new ArrayList<Images>();
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class,
				imagesMapping);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", searchBean.getImageObjectId(),
				"imageObjectType",searchBean.getImageObjectType()), columnRowMapper);
		return returnList;
	}
	public List<String> getImageFlag(final Integer offenderBookId, final Integer propertyItemSeq) {
		List<String> listString=new ArrayList<String>();
		final String sql = getQuery("OIIPTRAN_GET_IMAGE_FLAG");
		try{
		listString= namedParameterJdbcTemplate.queryForList(sql, createParams("offenderBookId",offenderBookId,"pptySeq",propertyItemSeq), String.class);
		}catch(Exception e){
			logger.error("getImageFlag",e);
		}
		return listString;
	}
}
