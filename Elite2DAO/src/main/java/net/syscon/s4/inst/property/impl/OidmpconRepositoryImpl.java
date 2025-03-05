package net.syscon.s4.inst.property.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.core.EliteDateRepository;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.OidmpconRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.sa.admin.beans.ModuleTables;

/**
 * Class OidmpconRepositoryImpl
 * 
 */
@Repository
public class OidmpconRepositoryImpl extends RepositoryBase implements OidmpconRepository {

	@Autowired
	private EliteDateRepository dateRepository;
	
	private static Logger logger = LogManager.getLogger(OidmpconRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> referenceCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE", new FieldMapper("code"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("DESCRIPTION", new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> internalLocationUsagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentInternalLocationId"))
			.put("USAGE_LOC", new FieldMapper("usageLoc"))
			.put("CAPACITY", new FieldMapper("capacity"))
			.put("CONTAINERS", new FieldMapper("noOfOccupant"))
			.put("CODE", new FieldMapper("code"))
			.put("LIST_SEQ", new FieldMapper("listSeq"))
			.put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.build();
	private final Map<String, FieldMapper> offenderPptyItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.build();
	private final Map<String, FieldMapper> agencyInternalLocationsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("INTERNAL_LOCATION_CODE", new FieldMapper("internalLocationCode"))
			.put("PARENT_INTERNAL_LOCATION_ID", new FieldMapper("parentInternalLocationId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.build();
	private final Map<String, FieldMapper> offenderPptyContainersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("INTERNAL_LOCATION_ID", new FieldMapper("internalLocationId"))
			.put("DISPOSED_TO_NAME", new FieldMapper("disposedToName"))
			.put("PROPERTY_STORAGE_ID", new FieldMapper("propertyStorageId"))
			.put("PROPOSED_DISPOSAL_DATE", new FieldMapper("proposedDisposalDate"))
			.put("EXPIRY_DATE", new FieldMapper("expiryDate"))
			.put("CONTDES", new FieldMapper("containerDescription")) 
			.put("IMAGE_URL",new FieldMapper("imageUrl"))
			.put("OFFENDER_BOOK_ID", new FieldMapper("offenderBookId"))
			.put("PROPERTY_ITEM_SEQ", new FieldMapper("propertyContainerId"))
			.put("LOCDES", new FieldMapper("locationDescription"))
			.build();
	private final Map<String, FieldMapper> images = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", 				new FieldMapper("imageId"))
			.put("CAPTURE_DATE", 			new FieldMapper("captureDate"))
			.put("IMAGE_OBJECT_TYPE", 		new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 		new FieldMapper("imageObjectId"))
			.put("IMAGE_VIEW_TYPE", 		new FieldMapper("imageViewType"))
			.put("IMAGE_OBJECT_SEQ", 		new FieldMapper("imageObjectSeq"))
			.put("IMAGE_THUMBNAIL", 		new FieldMapper("imageThumbnail"))
			.put("ACTIVE_FLAG", 			new FieldMapper("activeFlag"))
			.put("ORIENTATION_TYPE", 		new FieldMapper("orientationType"))
			.put("SEAL_FLAG", 				new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 		new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			new FieldMapper("modifyUserId"))
			.put("COU", 					new FieldMapper("cou")).build();
	private JdbcTemplate jdbcTemplate;
	/**
	 * Creates new OidmpconRepositoryImpl class Object
	 */
	public OidmpconRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderPptyContainers
	 *
	 * @return List<OffenderPptyContainers>
	 *
	 * @
	 */
	public List<OffenderPptyContainers> offConExecuteQuery(final OffenderPptyContainers objSearchDao) {
		final String sql = getQuery("OIDMPCON_OFFCON_FIND_OFFENDER_PPTY_CONTAINERS");
		List<OffenderPptyContainers> returnList = new ArrayList<>();
		final RowMapper<OffenderPptyContainers> OffenderPptyContainersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("offenderBookId", objSearchDao.getOffenderBookId(), "agyLocId", objSearchDao.getAgyLocId() ), OffenderPptyContainersRowMapper);
		
		String sqlForImageExistence = "SELECT IMAGE_ID,IMAGE_THUMBNAIL FROM IMAGES WHERE IMAGE_OBJECT_ID=:OBJECT_ID  AND IMAGE_OBJECT_TYPE = 'PPTY_CONT' ORDER BY ACTIVE_FLAG DESC";
		
		for (OffenderPptyContainers offenderPptyContainers : returnList) {
					
					final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sqlForImageExistence, Images.class, images);
					final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
					final StringBuffer sqlQuery = new StringBuffer();
					sqlQuery.append(sqlForImageExistence);
					
					if (offenderPptyContainers.getPropertyContainerId() != null) {	
						inParameterSource.addValue("OBJECT_ID", offenderPptyContainers.getPropertyContainerId());
					}
					
					/*if (offenderPptyContainers.getPropertyContainerId() != null) {
						inParameterSource.addValue("PROPERTY_ITEM_SEQ",offenderPptyContainers.getPropertyContainerId() );
					}*/
					List<Images> imageList = namedParameterJdbcTemplate.query(sqlQuery.toString().trim(), inParameterSource, imagesRowMapper);
					offenderPptyContainers.setImages(imageList);
		}
		
		
		return returnList;
	}
	
	
	public List<Images> getImageForVirtualContainers(Object offenderBookId , Integer propertyContainerId ) {
		// @formatter:off
		String sqlForImageExistence = "SELECT IMAGE_ID,IMAGE_THUMBNAIL FROM IMAGES WHERE IMAGE_OBJECT_ID=:OFFENDER_BOOK_ID AND IMAGE_OBJECT_SEQ=:PROPERTY_ITEM_SEQ AND IMAGE_OBJECT_TYPE = 'PPTY_CONT'";
		// @formatter:on
		
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sqlForImageExistence, Images.class, images);
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();

		inParameterSource.addValue("OFFENDER_BOOK_ID", offenderBookId);
		inParameterSource.addValue("PROPERTY_ITEM_SEQ", propertyContainerId );

		return namedParameterJdbcTemplate.query(sqlForImageExistence, inParameterSource, imagesRowMapper);
	}

	/**
	 * @param
	 *
	 * @
	 *
	 */
	public Integer preInsert() {
		final String sql = getQuery("OIDMPCON_PROPERTY_CONTAINER_ID_NXT");
		Integer returnList = 0;
		returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderPptyContainers
	 *            List<OffenderPptyContainers>
	 *
	 * @return List<Integer>
	 *
	 * @
	 */
	@Override
	public Integer offConInsertOffenderPptyContainers(final List<OffenderPptyContainers> lstOffenderPptyContainers) {
		final String sql = getQuery("OIDMPCON_OFFCON_INSERT_OFFENDER_PPTY_CONTAINERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyContainers obj : lstOffenderPptyContainers) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffenderPptyContainers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	public AgencyInternalLocations getInternalLocationId(final AgencyInternalLocations paramBean) {
		final String sql = getQuery("OIDMPCON_GET_INTERNAL_LOCATION_ID");
		AgencyInternalLocations returnObj = new AgencyInternalLocations();
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("INTERNALLOCATIONCODE", paramBean.getInternalLocationCode(),"agyLocId",paramBean.getAgyLocId()), columnRowMapper);
		} catch (Exception e) {
			returnObj = new AgencyInternalLocations();
		}
		return returnObj;
	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyContainers
	 *            List<OffenderPptyContainers>
	 *
	 * @
	 */
	public Integer offConUpdateOffenderPptyContainers(final List<OffenderPptyContainers> lstOffenderPptyContainers) {
		final String sql = getQuery("OIDMPCON_OFFCON_UPDATE_OFFENDER_PPTY_CONTAINERS");
		final AgencyInternalLocations agencyObj = new AgencyInternalLocations();
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyContainers obj : lstOffenderPptyContainers) {
			obj.setCreateDateTime((obj.getCreateDateTime() == null) ? dateRepository.getDBTime()
					: obj.getCreateDateTime());
			agencyObj.setInternalLocationCode(obj.getDescription());
			agencyObj.setAgyLocId(obj.getCaseLoadId());
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
			return 10;
		} catch (Exception e) {
			logger.error("",e);
			
		}
			
		if (lstOffenderPptyContainers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<ReferenceCodesIntLocUsageLocationsInternalLocationUsages>
	 */
	public List<AgencyInternalLocations> rgLocationAllRecordGroup(final String caseloadId) {
		final String sql = getQuery("OIDMPCON_FIND_RGLOCATIONALL");
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyInternalLocations> internalLocUsageMapping = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, internalLocationUsagesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), internalLocUsageMapping);
		} catch (Exception e) {
			logger.error("", e);
		}
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
	public List<OffenderPptyContainers> vPheadOnCheckDeleteMaster(final OffenderPptyContainers paramBean) {
		final String sql = getQuery("OIDMPCON_V_PHEAD_ONCHECKDELETEMASTER");
		final ArrayList<OffenderPptyContainers> returnList;
		final RowMapper<OffenderPptyContainers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		returnList = (ArrayList<OffenderPptyContainers>) namedParameterJdbcTemplate.query(sql, createParams(),
				columnRowMapper);
		return returnList;
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
		final String sql = getQuery("OIDMPCON_CGFKCHK_OFF_CON_OFF_CON_PPTY");
		AgencyInternalLocations returnObj = new AgencyInternalLocations();
		final RowMapper<AgencyInternalLocations> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try {
			returnObj = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("INTERNALLOCATIONID", paramBean.getInternalLocationId()), columnRowMapper);
		} catch (EmptyResultDataAccessException e) {
			returnObj = new AgencyInternalLocations();
		}
		return returnObj;
	}
	
	@Override
	public List<String> findRgContainerCode() {
		final String sql = getQuery("OIDMPCON_TYPE_LIST");
		 return namedParameterJdbcTemplate.queryForList(sql,createParams(),String.class);
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * AgencyIncidentPreInsertcDAO
	 *
	 *
	 */
	public Integer checkStorageLocation(final String internalLocId) {
		final String sql = getQuery("OIDMPCON_CHECK_STORAGE_CAPACITY");
		Integer returnObj;
		try{
		 returnObj =  namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROPERTYSTORAGEID",internalLocId), Integer.class);
		} catch(EmptyResultDataAccessException e) {
			returnObj= 0;
			
		}
		return returnObj;
//			final Map<String, Object> inParamMap = new HashMap<String, Object>();
//			SqlParameter[] sqlParameters = new SqlParameter[1];
//			sqlParameters = new SqlParameter[] {new SqlParameter("P_PROPERTY_STORAGE_ID", OracleTypes.VARCHAR) , new SqlInOutParameter("RETURN_VALUE", OracleTypes.BOOLEAN)};
//			final SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
//					.withCatalogName("OMS_PROPERTY").withFunctionName("CHECK_STORAGE_CAPACITY")
//					.declareParameters(sqlParameters);
//			inParamMap.put("P_PROPERTY_STORAGE_ID", internalLocId);
//			inParamMap.put("RETURN_VALUE", true);
//			final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
//			final Integer valueBoolean = simpleJDBCCall.executeFunction(Integer.class, inParameter);
//		return valueBoolean;
	}
	public List<String> checkPptyItems(final Integer propertyConId) {
		final String sql = getQuery("OIDMPCON_CHECK_PPTY_ITEMS");
		final List<String> returnObj =  namedParameterJdbcTemplate.queryForList(sql,
				createParams("PROPERTYCONTAINERID",propertyConId), String.class);
		return returnObj;
	}
	
	public Integer checkContainerEmptyValue(final Integer propertyConId) {
		final String sql = getQuery("OIDMPCON_CHECK_CONTAINER_EMPTY");
		Integer returnObj;
		try{
		 returnObj =  namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROPERTYCONTAINERID",propertyConId), Integer.class);
		} catch(EmptyResultDataAccessException e) {
			returnObj = 0;
			
		}
		return returnObj;
	}

	public OffenderPptyContainers getSealMarkValueOfpropertyConId(final Integer propertyConId) {
		final String sql = getQuery("OIDMPCON_GET_SEAL_MARK_VALUE");
		OffenderPptyContainers returnObj = null;
		final RowMapper<OffenderPptyContainers> OffenderPptyContainersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyContainers.class, offenderPptyContainersMapping);
		try{
		 returnObj =  (OffenderPptyContainers) namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROPERTYCONTAINERID",propertyConId), OffenderPptyContainersRowMapper);
		} catch(EmptyResultDataAccessException e) {
//			returnObj = null;
			
		}
		return returnObj;
	}
	/*
	 * This method is used to get location value
	 * param agyLocId
	 */
	public AgencyInternalLocations getLocationValue(final String agyLocId) {
		final String sql = getQuery("OIDMPCON_GET_LOCATION_VALUE");
		AgencyInternalLocations returnObj = null;
		final RowMapper<AgencyInternalLocations> agencyIntLocRowMapper = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, agencyInternalLocationsMapping);
		try{
		 returnObj =   namedParameterJdbcTemplate.queryForObject(sql,
				createParams("AGYLOCID",agyLocId), agencyIntLocRowMapper);
		} catch(EmptyResultDataAccessException e) {
			returnObj = new AgencyInternalLocations();
			
		}
		return returnObj;
	}

	/**
	 * Method is used to get agyLocId
	 * @param offBkgId, intLocId
	 * @return String
	 */
	public String lvGetAgyLoc(final String offBkgId, final String intLocId) {
		final String sql = getQuery("OIDMPCON_LV_GET_AGY_LOC");
		String returnVal = null;
		List<String> returnList = new ArrayList<>();
		returnList = namedParameterJdbcTemplate.queryForList(sql,
				createParams("OFFENDER_BOOK_ID", offBkgId, "INTERNAL_LOCATION_ID", intLocId), String.class);
		if (returnList.size() > 0) {
			returnVal = returnList.get(0);
		} else {
			returnVal = null;
		}
		return returnVal;

	}
	/**
	 * Method is used to get the Record group values.
	 * @param agyLocId
	 * @return returnList
	 */
	public List<AgencyInternalLocations> rgStoreLocation(final String agyLocId) {
		final String sql = getQuery("OIDMPCON_FIND_RGSTORELOCATION");
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyInternalLocations> internalLocUsageMapping = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, internalLocationUsagesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("AGYLOCID", agyLocId), internalLocUsageMapping);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}
	/**
	 * Method is used to get the Record group values.
	 * @param caseLoadId
	 * @return returnList
	 */
	public List<AgencyInternalLocations> rgDescription(final String caseLoadId) {
		final String sql = getQuery("OIDMPCON_FIND_RGDESCRIPTION");
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyInternalLocations> internalLocUsageMapping = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, internalLocationUsagesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseLoadId), internalLocUsageMapping);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}
	
	@Override
	public Integer insertContainerImg(OffenderPptyContainers offenderPptyContainers) {
		int imageResult=0;
		String[] url=offenderPptyContainers.getImageUrl().split(",");
		String urlToBeUpdated=url[1];
		boolean isImageExist=checkIfImageExist(offenderPptyContainers.getOffenderBookId(),offenderPptyContainers.getPropertyContainerId(),urlToBeUpdated);
		//if(isImageExist) {
			
			//UPDATE EXISTING IMAGE
			
			//String updateImages = "UPDATE IMAGES SET IMAGE_THUMBNAIL=? where IMAGE_OBJECT_ID=? AND IMAGE_OBJECT_SEQ=?";
			//byte[] bytes = Base64.getDecoder().decode(offenderPptyContainers.getImageUrl().split(",")[1]);
			//int result = jdbcTemplate.update(updateImages, bytes, offenderPptyContainers.getOffenderBookId(), offenderPptyContainers.getPropertyContainerId());
			
			//if(result > 0) {
			//	imageResult=1;
			//	logger.info(" Image Updated Successfully");
			//}else {
			//	logger.warn(" Image Not Updated");
			//}
		//}else {
			if((!isImageExist) && offenderPptyContainers.getImageUrl()!=null && offenderPptyContainers.getImageUrl().length()>0) {
				Images image = new Images();
				image.setImageUrl(url[1]);
				image.setImageObjectId(new BigDecimal(offenderPptyContainers.getOffenderBookId()));
				if(offenderPptyContainers.getPropertyContainerId()!=null) {
				image.setImageObjectSeq(new BigDecimal(offenderPptyContainers.getPropertyContainerId()));
				}
				imageResult = insertPropertyImage(image);
				
				logger.info(" Image Inserted Successfully");
			}
		//}
		return imageResult;
	}
	private boolean checkIfImageExist(Integer offenderBookId,Integer propSeq,String imageUrl) {
		boolean imageExist = false;
		Integer imageIdToBeUpdated;
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			String sqlForImageExistence = getQuery("IS_IMAGE_EXISTS");
			imageIdToBeUpdated = jdbcTemplate.queryForObject(sqlForImageExistence, Integer.class, offenderBookId,propSeq);
			if(imageIdToBeUpdated > 0) {
				imageExist = true;
				int result=1;
				String updateImages = getQuery("UPDATE_IMAGE");
				byte[] bytes = Base64.getDecoder().decode(imageUrl);
				result=jdbcTemplate.update(updateImages,
						new Object[] { bytes, imageIdToBeUpdated});
				if (result > 0) {
					logger.info(" Image Updated Successfully with imageId = " + imageIdToBeUpdated);
				}
			}
				
			
		}
		catch (EmptyResultDataAccessException e)
		{
			imageExist = false;
		}
		catch (Exception e) {
			e.printStackTrace();
			imageExist = false;
		}
		return imageExist;
		
	}
	private Integer insertPropertyImage(Images image) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		Integer imageId;
		int result=1;
		try {
			 byte[] bytes = Base64.getDecoder().decode(image.getImageUrl());
				//byte[] bytes = IOUtils.toByteArray(image.getImageUrl());
				String sqlForImage = getQuery("MAX_IMAGE_ID");
				imageId = jdbcTemplate.queryForObject(sqlForImage, Integer.class);
				Long imageIdToBeInserted = new Long(imageId + 1);
				Date currentDate = getCurrentDatetime();
				Date captureDate = Date.valueOf(java.time.LocalDate.now());

				Images insertImageRecord = new Images();
				insertImageRecord.setImageId(imageIdToBeInserted);
				insertImageRecord.setCaptureDate(captureDate);
				insertImageRecord.setImageObjectType("PPTY_CONT");
				insertImageRecord.setImageObjectId(image.getImageObjectId());
				insertImageRecord.setImageObjectSeq(image.getImageObjectSeq());
				insertImageRecord.setImageViewType("");
				insertImageRecord.setImageThumbnail(bytes);
				insertImageRecord.setActiveFlag("N");
				insertImageRecord.setOrientationType("");
				insertImageRecord.setSealFlag(null);
				insertImageRecord.setCreateDatetime(currentDate);
				insertImageRecord.setCreateUserId("");
				insertImageRecord.setModifyDatetime(currentDate);
				insertImageRecord.setModifyUserId("oms_owner");
				String sqlImages = getQuery("INSERT_IMAGE");

				result = jdbcTemplate.update(sqlImages,
						new Object[] { insertImageRecord.getImageId(), insertImageRecord.getCaptureDate(),
								insertImageRecord.getImageObjectType(), insertImageRecord.getImageObjectId(),
								insertImageRecord.getImageObjectSeq(), insertImageRecord.getImageViewType(),
								insertImageRecord.getImageThumbnail(), insertImageRecord.getActiveFlag(),
								insertImageRecord.getOrientationType(), insertImageRecord.getSealFlag(),
								insertImageRecord.getCreateDatetime(), insertImageRecord.getCreateUserId(),
								insertImageRecord.getModifyDatetime(), insertImageRecord.getModifyUserId() });

				if (result > 0) {
					System.out.println(result + " Row Inserted with imageId = " + imageIdToBeInserted);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		return result;
	}
	static java.sql.Date getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	@Override
	public int offConUpdateSeal(OffenderPptyContainers commitBean) {
		final String sql = getQuery("OIDMPCON_OFFCON_UPDATE_SEAL_MARK_FLAG");
		final AgencyInternalLocations agencyObj = new AgencyInternalLocations();
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			parameters.add(new BeanPropertySqlParameterSource(commitBean));
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
			return 10;
		} catch (Exception e) {
			logger.error("",e);
			
		}
			
		if (returnArray.length>0) {
			return 1;
		} else {
			return 0;
		}

	}
	
	@Override
	public Integer getPropertyContainerId() {
		final String sql = getQuery("GET_PROPERTY_CONTAINER_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
	}
	
	@Override
	public int offConUpdateMultipleSeal(List<OffenderPptyContainers> commitBean) {
		final String sql = getQuery("OIDMPCON_OFFCON_UPDATE_SEAL_MARK_FLAG");
		final AgencyInternalLocations agencyObj = new AgencyInternalLocations();
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (OffenderPptyContainers offenderPptyContainers : commitBean) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyContainers));
		}
		try {
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
			return 10;
		} catch (Exception e) {
			logger.error("",e);
			
		}
			
		if (returnArray.length==commitBean.size()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateContainerLocation(OffenderPptyContainers obj) {
		 final String sql = getQuery("UPDATE_CONATINER_LOCATION");
			int result = 0 ;
			SqlParameterSource sqlParam = new BeanPropertySqlParameterSource(obj);
			try {
				result = namedParameterJdbcTemplate.update(sql, sqlParam);
			} catch (Exception e) {
				logger.error("OidmpconRepositoryImpl::  error in updateContainerLocations  "+ e.getMessage());
				
			}
			
			
			return result;
	}
	
	public List<AgencyInternalLocations> getAllLocations(final String caseloadId) {
		final String sql = getQuery("OIDMPCON_FIND_RGLOCATION_BASED_ON_CASELOAD");
		List<AgencyInternalLocations> returnList = new ArrayList<>();
		final RowMapper<AgencyInternalLocations> internalLocUsageMapping = Row2BeanRowMapper.makeMapping(sql,
				AgencyInternalLocations.class, internalLocationUsagesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("CASELOADID", caseloadId), internalLocUsageMapping);
		} catch (Exception e) {
			logger.error("error in getAllLocations"+ e.getMessage());
		}
		return returnList;
	}
	
}
