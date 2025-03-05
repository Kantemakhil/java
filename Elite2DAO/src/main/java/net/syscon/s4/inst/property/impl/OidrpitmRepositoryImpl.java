package net.syscon.s4.inst.property.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inst.property.OidrpitmRepository;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Class OidrpitmRepositoryImpl
 *
 */
@Repository
public class OidrpitmRepositoryImpl extends RepositoryBase implements OidrpitmRepository {
	
	/**
	* Logger object used to print the log in the file
	*/
	private static Logger logger = LogManager.getLogger(OidrpitmRepositoryImpl.class.getName());


	private final Map<String, FieldMapper> offPpIteMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_CODE",              new FieldMapper("profileCode"))
			.put("DISPOSED_TO_CORP_ID",       new FieldMapper("disposedToCorpId"))
			.put("QUANTITY",                  new FieldMapper("quantity"))
			.put("CONFIRM_FLAG",              new FieldMapper("confirmFlag"))
			.put("PROPERTY_DESCRIPTION",      new FieldMapper("propertyDescription"))
			.put("VERIFICATION_FLAG",         new FieldMapper("verificationFlag"))
			.put("TO_PROPERTY_CONTAINER_ID",  new FieldMapper("toPropertyContainerId"))
			.put("SEAL_FLAG",                 new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",           new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME",           new FieldMapper("modifyDatetime"))
			.put("PROFILE_VALUE",             new FieldMapper("profileValue"))
			.put("DISPOSED_TO_PERSON_ID",     new FieldMapper("disposedToPersonId"))
			.put("DESCRIPTION",               new FieldMapper("description"))
			.put("STATUS_CODE",               new FieldMapper("statusCode"))
			.put("PROPERTY_ITEM_SEQ",         new FieldMapper("propertyItemSeq"))
			.put("OFFENDER_BOOK_ID",          new FieldMapper("offenderBookId"))
			.put("COMMENT_TEXT",              new FieldMapper("commentText"))
			.put("CREATE_USER_ID",            new FieldMapper("createUserId"))
			.put("PROPERTY_CONTAINER_TXN_ID", new FieldMapper("propertyContainerTxnId"))
			.put("PROFILE_TYPE",              new FieldMapper("profileType"))
			.put("DISPOSED_TO_PERSON",        new FieldMapper("disposedToPerson"))
			.put("MODIFY_USER_ID",            new FieldMapper("modifyUserId"))
			.put("OLD_TABLE_NAME",            new FieldMapper("oldTableName"))
			.put("PROPERTY_TYPE",             new FieldMapper("propertyType"))
			.put("PROPERTY_CONTAINER_ID",     new FieldMapper("propertyContainerId"))
			.put("MAKE",                      new FieldMapper("make"))
			.put("PROFILE_VALUE_2",           new FieldMapper("profileValue2"))
			.put("COLOR",                     new FieldMapper("color"))
			.put("TO_STATUS_CODE",            new FieldMapper("toStatusCode"))
			.put("CONDITION_CODE",            new FieldMapper("conditionCode"))
			.put("PROPERTY_ITEM_TXN_ID",      new FieldMapper("propertyItemTxnId"))
			.put("AGY_LOC_ID",                new FieldMapper("agyLocId"))
			.put("FROM_STATUS_CODE",          new FieldMapper("fromStatusCode"))
			.put("CREATE_DATE",               new FieldMapper("createDate"))
			.put("DISPOSED_TO_OFFENDER_FLAG", new FieldMapper("disposedToOffenderFlag"))
			.put("RECEIVED_FROM",             new FieldMapper("receivedFrom"))
			.put("SERIAL_NO",                 new FieldMapper("serialNo"))
			.put("VERIFY_FLAG",               new FieldMapper("verifyFlag"))
			.put("EVENT_SEQ",                 new FieldMapper("eventSeq"))
			.put("IMAGE_URL",                 new FieldMapper("imageUrl"))
			.put("PROPERTY_SIZE",             new FieldMapper("propertySize"))
			.put("PROPERTY_VALUE",            new FieldMapper("propertyValue"))
			.build();
	
	private final Map<String, FieldMapper> refCodeMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("RECEIVED_FROM",             new FieldMapper("receivedFrom"))
			.put("DESCRIPTION",               new FieldMapper(" description "))
			.put("COLOR",                     new FieldMapper("color"))
			.put("CONDITION_CODE",            new FieldMapper("conditionCode"))
			.put("PROPERTY_TYPE",             new FieldMapper("propertyType"))
			.build();
	
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CODE",                     new FieldMapper("code"))
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
	 * Creates new OidrpitmRepositoryImpl class Object
	 */
	public OidrpitmRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
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
		final String sql = getQuery("OIDRPITM_OFFPI_FIND");
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		final RowMapper<OffenderPptyItems> offPptyIteRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offPpIteMap);
		returnList = namedParameterJdbcTemplate.query(
				sql, createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId(),"STATUS_CODE",objSearchDao.getStatusCode(),"CASELOAD_ID",objSearchDao.getAgyLocId()), offPptyIteRowMap);
		if(returnList.size()>0) {
			for (OffenderPptyItems offenderPptyItems : returnList) {
				
				String sqlForImageExistence = getQuery("GET_PROPERTY_IMAGE");
				final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, images);
				final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
				final StringBuffer sqlQuery = new StringBuffer();
				sqlQuery.append(sqlForImageExistence);
				if (offenderPptyItems.getOffenderBookId() != null) {
					inParameterSource.addValue("OFFENDER_BOOK_ID", offenderPptyItems.getOffenderBookId());
				}
				if (offenderPptyItems.getPropertyItemSeq() != null) {
					inParameterSource.addValue("PROPERTY_ITEM_SEQ",offenderPptyItems.getPropertyItemSeq() );
				}
				offenderPptyItems.setImages( namedParameterJdbcTemplate.query(sqlQuery.toString().trim(), inParameterSource, imagesRowMapper));
				
			}
		}
		return returnList;
	}

	/**
	 * @param
	 *
	 * 
	 *
	 */
	public int preInsert() {
		return 0;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on
	 *
	 * @param lstOffenderPptyItems
	 *            List<OffenderPptyItems>
	 *
	 * @return List<Integer>
	 *
	 * 
	 */
	public Integer offpiInsertOffenderPptyItems(final List<OffenderPptyItems> lstOffPpty) {
		final String sql = getQuery("OIDRPITM_OFFPI_INSERT_OFFENDER_PPTY_ITEMS");
		int[] returnArray = new int[] {};
		String urlToBeInserted="";
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItems list : lstOffPpty) {
			parameters.add(new BeanPropertySqlParameterSource(list));
			
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstOffPpty.size() == returnArray.length) {
			for (final OffenderPptyItems list : lstOffPpty) {
				if(list.getImageUrl()!=null && list.getImageUrl().length()>0) {
					Images image = new Images();
					String[] url=list.getImageUrl().split(",");
					 urlToBeInserted = url[1];
					 image.setImageUrl(urlToBeInserted);
					 image.setImageObjectId(new BigDecimal(list.getOffenderBookId()));
					 image.setImageObjectSeq(new BigDecimal(list.getPropertyItemSeq()));
					 insertPropertyImage(image);
				}
				else return 1;
			}
						return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 *
	 * @param lstOffenderPptyItems
	 *            List<OffenderPptyItems>
	 *
	 * 
	 */
	public Integer offPiUpdateOffenderPptyItems(final List<OffenderPptyItems> obj) {
		final String sql = getQuery("OIDRPITM_OFFPI_UPDATE_OFFENDER_PPTY_ITEMS");
		int[] returnArray = new int[] {};
		String urlToBeUpdated="";
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItems offenderPptyItems : obj) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyItems));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (obj.size() == returnArray.length) {
			for (final OffenderPptyItems list : obj) {
				if(list.getImageUrl()!=null && list.getImageUrl().length()>0) {
					String[] url=list.getImageUrl().split(",");
					urlToBeUpdated = url[1];
				boolean imageExists =	checkIfImageExist(list.getOffenderBookId(),list.getPropertyItemSeq(),urlToBeUpdated);
				if(!imageExists) {
					Images image = new Images();
					
					 image.setImageUrl(urlToBeUpdated);
					 image.setImageObjectId(new BigDecimal(list.getOffenderBookId()));
					 image.setImageObjectSeq(new BigDecimal(list.getPropertyItemSeq()));
					 insertPropertyImage(image);
				}}
				else return 1;
			
			}
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 *
	 * @param lstOffenderPptyItems
	 *            List<OffenderPptyItems>
	 *
	 * 
	 */
	public Integer offPiDeleteOffenderPptyItems(final List<OffenderPptyItems> list) {
		final String sql = getQuery("OIDRPITM_OFFPI_DELETE_OFFENDER_PPTY_ITEMS");	
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItems offenderPptyItems : list) {
			parameters.add(new BeanPropertySqlParameterSource(offenderPptyItems));
		}
		try {
			String tableName = "OFFENDER_PPTY_ITEMS";
			String whereCondition = "OFFENDER_BOOK_ID=:offenderBookId AND PROPERTY_ITEM_SEQ=:propertyItemSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (list.size() == returnArray.length) {
			for (OffenderPptyItems offenderPptyItems : list) {
				if(offenderPptyItems.getImages()!=null && offenderPptyItems.getImages().size()>0) {
				deleteImageForProperty(offenderPptyItems.getOffenderBookId(),offenderPptyItems.getPropertyItemSeq());
			}}
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * 
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		return null;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgColorRecordGroup() {
		final String sql = getQuery("OIDRPITM_FIND_RGCOLOR");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> rgCondnRecordGroup() {
		final String sql = getQuery("OIDRPITM_FIND_RGCONDN");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffPiReceivedFromRecordGroup() {
		final String sql = getQuery("OIDRPITM_FIND_CGFKOFFPIRECEIVEDFROM");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	/**
	 * Used to capture results from select query
	 * 
	 * @return List<M>
	 */
	public List<ReferenceCodes> cgfkOffPiPropertyTypeRecordGroup() {
		final String sql = getQuery("OIDRPITM_FIND_CGFKOFFPIPROPERTYTYPE");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
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
	public List<OffenderPptyItems> vPheadOnCheckDeleteMaster(final OffenderPptyItems paramBean) {
		final String sql = getQuery("OIDRPITM_V_PHEAD_ONCHECKDELETEMASTER");
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		final RowMapper<OffenderPptyItems> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				offPpIteMap);
		returnList = namedParameterJdbcTemplate
				.query(sql, createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offPiPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> offPiPostQuerypost(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_PI_POSTQUERY");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMap);
		returnList  = namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * offPiPostQuery
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> offPiPostQuery(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_PI_POSTQUERY");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMap);
		returnList  = namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgwhenNewFormInstance(final SysDual paramBean) {
		return null;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffPiOffPiRef
	 *
	 * @param params
	 *
	 */
	public List<ReferenceCodes> cgfkchkOffPiOffPiRef(final ReferenceCodes paramBean) {
		final String sql = getQuery("OIDRPITM_CGFKCHK_OFF_PI_OFF_PI_REF_3");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				refCodeMap);
		 returnList =  namedParameterJdbcTemplate.query(sql,
				createParams(paramBean), columnRowMapper);
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * oidreleaOffEmPreInsertc
	 *
	 * @param params
	 *
	 */
	public Integer offEmPreInsertc(final Integer paramBean) {
		final String sql = getQuery("OIDRPITM_OFF_EM_PREINSERT_C");
		String obj = null;
		Integer returnval=null;
		try {
		obj = namedParameterJdbcTemplate.queryForObject(sql, createParams("OFFENDER_BOOK_ID", paramBean), String.class);
		if (obj != null) {
			returnval=Integer.parseInt(obj);
		}
		}catch(Exception e) {
			logger.error("offEmPreInsertc",e);
		}
		return returnval;
	}
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * @return Object
	 * @param Dual
	 *            paramBean
	 */
	public List<ReferenceCodes> offRecForm() {
		final String sql = getQuery("OIDRPITM_RECIEVE_FORM");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class, mMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), mRowMapper);
		} catch (EmptyResultDataAccessException e) {
			logger.error("",e);
		}
		return returnList;
	}

	@Override
	public String getDescrption(final String propertyType) {
		final String sql = getQuery("OIDRPITM_GET_DESCRIPTION");
		 String returnList = "";
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("code",propertyType), String.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("getDescrption",e);
		}
		return returnList;
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
				insertImageRecord.setImageObjectType("PROPERTY");
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

//				if (result > 0) {
//					System.out.println(result + " Row Inserted with imageId = " + imageIdToBeInserted);
//				}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}

		return result;
	}
	
	private boolean checkIfImageExist(Integer offenderBookId,Integer propSeq,String imageUrl) {
		boolean imageExist = false;
		Integer imageIdToBeUpdated;
		jdbcTemplate = new JdbcTemplate(dataSource);
		try {
			String sqlForImageExistence = getQuery("IS_IMAGE_EXIST");
			imageIdToBeUpdated = jdbcTemplate.queryForObject(sqlForImageExistence, Integer.class, offenderBookId,propSeq);
			if(imageIdToBeUpdated > 0) {
				imageExist = true;
				int result=1;
				String updateImages = getQuery("UPDATE_IMAGE");
				byte[] bytes = Base64.getDecoder().decode(imageUrl);
				result=jdbcTemplate.update(updateImages,
						new Object[] { bytes, imageIdToBeUpdated});
//				if (result > 0) {
//					System.out.println(result + " Row UPDATED with imageId = " + imageIdToBeUpdated);
//				}
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
	


static java.sql.Date getCurrentDatetime() {
	java.util.Date today = new java.util.Date();
	return new java.sql.Date(today.getTime());
}
private Integer deleteImageForProperty(Integer offenderBookId,Integer propertyItemSeq) {
	final String sql = getQuery("IS_IMAGE_EXIST");
	int result = 0;
	List<Integer> imageIdToBeDeleted;
	imageIdToBeDeleted = jdbcTemplate.queryForList(sql, Integer.class, offenderBookId,propertyItemSeq);
	for (Integer imageId : imageIdToBeDeleted) {
		if(imageId > 0) {
			 result=1;
			String deleteImageOriginals = getQuery("DELETE_IMAGE_ORIGINALS");
			result=jdbcTemplate.update(deleteImageOriginals,
					new Object[] {imageId});
			if (result > 0) {
				//System.out.println(result + " Row Deleted from image_originals with imageId = " + imageId);
				String deleteFromImageTable =getQuery("DELETE_IMAGE");
				result=jdbcTemplate.update(deleteFromImageTable,
						new Object[] {imageId});
				//System.out.println(result + " Row Deleted from images with imageId = " + imageId);
			}
		}
	}
	
	return result;

}}