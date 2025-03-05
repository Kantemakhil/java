package net.syscon.s4.inst.securitythreatgroups.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.ProfileTypes;
import net.syscon.s4.im.beans.StgIdentifiers;
import net.syscon.s4.im.beans.StgIdentifyingWords;
import net.syscon.s4.inst.securitythreatgroups.OidstgidRepository;

@Repository
public class OidstgidRepositoryImpl extends RepositoryBase implements OidstgidRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidstgidRepositoryImpl.class.getName());
 
	private final Map<String, FieldMapper> stgIdentifiersMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1)",							 new FieldMapper("1)"))
			.put("NVL(MAX(IDENTIFIER_SEQ+1)",    new FieldMapper("nvl(max(identifierSeq+1)"))
			.put("STG_ID", 						 new FieldMapper("stgId"))
			.put("IDENTIFIER_SEQ", 				 new FieldMapper("identifierSeq"))
			.put("PROFILE_TYPE", 				 new FieldMapper("profileType"))
			.put("DETAIL", 						 new FieldMapper("detail"))
			.put("CREATE_DATETIME", 			 new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", 				 new FieldMapper("createUserId"))
			.put("IMAGE_DATA", 					 new FieldMapper("imageData"))
			.put("IMAGE_SIZE", 					 new FieldMapper("imageSize"))
			.put("MODIFY_DATETIME", 			 new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 				 new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 					 new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> stgIdentifyingWordsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("1)",							 new FieldMapper("1) "))
			.put("NVL(MAX(WORD_SEQ+1)", 		 new FieldMapper(" nvl(max(wordSeq+1)"))
			.put("STG_ID", 						 new FieldMapper("stgId"))
			.put("WORD_SEQ", 				     new FieldMapper("wordSeq"))
			.put("CODE", 				         new FieldMapper("code"))
			.put("DESCRIPTION", 				 new FieldMapper("description"))
			.put("CREATE_USER_ID", 				 new FieldMapper("createUserId"))
			.put("CREATE_DATETIME", 			 new FieldMapper("createDatetime"))
			.put("MODIFY_DATETIME", 			 new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 				 new FieldMapper("modifyUserId"))
			.put("SEAL_FLAG", 					 new FieldMapper("sealFlag"))
			.build();
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROFILE_TYPE", 				 new FieldMapper("profileType"))
			.put("STG_ID", 						 new FieldMapper("stgId"))
			.put("CODE", 				         new FieldMapper("code"))
			.put("DESCRIPTION", 				 new FieldMapper("description"))
			.build();
	private final Map<String, FieldMapper> imagesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", 					new FieldMapper("imageId"))
			.put("CAPTURE_DATE", 			    new FieldMapper("captureDate"))
			.put("IMAGE_OBJECT_TYPE",			new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", 			new FieldMapper("imageObjectId"))
			.put("IMAGE_OBJECT_SEQ",  			new FieldMapper("imageObjectSeq"))
			.put("IMAGE_VIEW_TYPE",  			new FieldMapper("imageViewType"))
			.put("IMAGE_THUMBNAIL", 			new FieldMapper("imageThumbnail"))
			.put("ACTIVE_FLAG",  				new FieldMapper("activeFlag"))
			.put("ORIENTATION_TYPE", 			new FieldMapper("orientationType"))
			.put("SEAL_FLAG",  					new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", 			new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID",  			new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", 		    new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", 			    new FieldMapper("modifyUserId"))
			.build();
	
	private final Map<String, FieldMapper> omsModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 				 new FieldMapper("description"))
			.build();

	/**
	 * Creates new OidstgidRepositoryImpl class Object
	 */
	public OidstgidRepositoryImpl() {
	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 * @return List<StgIdentifiers>
	 */
	public List<StgIdentifiers> stgIdentifiersExecuteQuery(final StgIdentifiers objSearchDao) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFIERS_FIND_STG_IDENTIFIERS");
		final RowMapper<StgIdentifiers> StgIdentifiersRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgIdentifiers.class, stgIdentifiersMapping);
		List<StgIdentifiers> returnList = new ArrayList<StgIdentifiers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", objSearchDao.getStgId()),
					StgIdentifiersRowMapper);
		} catch (Exception e) {
			logger.error("stgIdentifiersExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * on stgIdentifiersInsertStgIdentifiers
	 * 
	 * @param lstStgIdentifiers
	 *            List<StgIdentifiers>
	 * @return List<Integer>
	 */
	public Integer stgIdentifiersInsertStgIdentifiers(final List<StgIdentifiers> lstStgIdentifiers) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFIERS_INSERT_STG_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgIdentifiers stgIdentifiers : lstStgIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(stgIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * stgIdentifiersUpdateStgIdentifiers
	 * 
	 * @param lstStgIdentifiers
	 * @return Integer
	 */
	public Integer stgIdentifiersUpdateStgIdentifiers(final List<StgIdentifiers> lstStgIdentifiers) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFIERS_UPDATE_STG_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgIdentifiers stgIdentifiers : lstStgIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(stgIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * stgIdentifiersDeleteStgIdentifiers
	 * 
	 * @param lstStgIdentifiers
	 *            List<StgIdentifiers>
	 */
	public Integer stgIdentifiersDeleteStgIdentifiers(final List<StgIdentifiers> lstStgIdentifiers) {

		final String sql = getQuery("OIDSTGID_STGIDENTIFIERS_DELETE_STG_IDENTIFIERS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgIdentifiers stgIdentifiers : lstStgIdentifiers) {
			parameters.add(new BeanPropertySqlParameterSource(stgIdentifiers));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgIdentifiers.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table image1ExecuteQuery
	 * 
	 * @param objSearchDao
	 * @return List<Images>
	 */
	public List<Images> image1ExecuteQuery(final Long stgId,final Long identifierSeq) {
		final String sql = getQuery("OIDSTGID_IMAGE1_FIND_IMAGES");
		final RowMapper<Images> ImagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		List<Images> returnList = new ArrayList<Images>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("STGID", stgId, "IDENTIFIERSEQ", identifierSeq), ImagesRowMapper);
		} catch (Exception e) {
			logger.error("image1ExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * 
	 * @param lstImages
	 * @return Integer
	 */
	public Integer image1InsertImages(final List<Images> lstImages) {
		final String sql = getQuery("OIDSTGID_IMAGE1_INSERT_IMAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Images images : lstImages) {
			parameters.add(new BeanPropertySqlParameterSource(images));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * 
	 * @param lstImages
	 * @return Integer
	 */
	public Integer image1DeleteImages(final List<Images> lstImages) {
		final String sql = getQuery("OIDSTGID_IMAGE1_DELETE_IMAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final Images images : lstImages) {
			parameters.add(new BeanPropertySqlParameterSource(images));
		}
		try {
			String tableName = "IMAGES";
			batchUpdatePreDeletedRows(tableName, null, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstImages.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Fetch the records from database table
	 * 
	 * @param objSearchDao
	 *            StgIdentifyingWords
	 * @return List<StgIdentifyingWords>
	 */
	public List<StgIdentifyingWords> stgIdentifyingWordsExecuteQuery(final StgIdentifyingWords objectSearchDao) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFYINGWORDS_FIND_STG_IDENTIFYING_WORDS");
		final RowMapper<StgIdentifyingWords> StgIdentifyingWordsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgIdentifyingWords.class, stgIdentifyingWordsMapping);
		List<StgIdentifyingWords> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", objectSearchDao.getStgId()),
					StgIdentifyingWordsRowMapper);
		} catch (Exception e) {
			logger.error("stgIdentifyingWordsExecuteQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is used to insert the records in the data base tables based
	 * stgIdentifyingWordsInsertStgIdentifyingWords
	 * 
	 * @param lstStgIdentifyingWords
	 * @return Integer
	 */

	public Integer stgIdentifyingWordsInsertStgIdentifyingWords(
			final List<StgIdentifyingWords> lstStgIdentifyingWords) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFYINGWORDS_INSERT_STG_IDENTIFYING_WORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgIdentifyingWords stgIdentifyingWords : lstStgIdentifyingWords) {
			parameters.add(new BeanPropertySqlParameterSource(stgIdentifyingWords));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgIdentifyingWords.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to update the data base tables based on
	 * stgIdentifyingWordsUpdateStgIdentifyingWords
	 * 
	 * @param lstStgIdentifyingWords
	 * @return Integer
	 */
	public Integer stgIdentifyingWordsUpdateStgIdentifyingWords(
			final List<StgIdentifyingWords> lstStgIdentifyingWords) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFYINGWORDS_UPDATE_STG_IDENTIFYING_WORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgIdentifyingWords stgIdentifyingWords : lstStgIdentifyingWords) {
			parameters.add(new BeanPropertySqlParameterSource(stgIdentifyingWords));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgIdentifyingWords.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * This method is used to delete records from data base tables based on
	 * stgIdentifyingWordsDeleteStgIdentifyingWords
	 * 
	 * @param lstStgIdentifyingWords
	 * @return Integer
	 */
	public Integer stgIdentifyingWordsDeleteStgIdentifyingWords(
			final List<StgIdentifyingWords> lstStgIdentifyingWords) {
		final String sql = getQuery("OIDSTGID_STGIDENTIFYINGWORDS_DELETE_STG_IDENTIFYING_WORDS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final StgIdentifyingWords stgIdentifyingWords : lstStgIdentifyingWords) {
			parameters.add(new BeanPropertySqlParameterSource(stgIdentifyingWords));
		}
		try {
			String tableName = "STG_IDENTIFYING_WORDS";
			String whereCondition = "STG_ID=:stgId and WORD_SEQ=:wordSeq";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (lstStgIdentifyingWords.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Used to capture results from select query rgProfileTypeRecordGroup
	 * 
	 * @param stgId
	 * @return List<ProfileTypes>
	 */
	public List<ProfileTypes> rgProfileTypeRecordGroup(final Long stgId) {
		final String sql = getQuery("OIDSTGID_FIND_RGPROFILETYPE");
		final RowMapper<ProfileTypes> mRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class, mMapping);
		List<ProfileTypes> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", stgId), mRowMapper);
		} catch (Exception e) {
			logger.error("rgProfileTypeRecordGroup", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * stgIdentifiersPreInsert
	 * 
	 * @return List<StgIdentifiers>
	 * @param params
	 */
	public List<StgIdentifiers> stgIdentifiersPreInsert(final StgIdentifiers paramBean) {
		final String sql = getQuery("OIDSTGID_STG_IDENTIFIERS_PREINSERT");
		final RowMapper<StgIdentifiers> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, StgIdentifiers.class,
				stgIdentifiersMapping);
		List<StgIdentifiers> returnList = new ArrayList<StgIdentifiers>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgIdentifiersPreInsert", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * stgIdentifiersPostQuery
	 * 
	 * @return List<ProfileTypes>
	 * @param params
	 */
	public List<ProfileTypes> stgIdentifiersPostQuery(final ProfileTypes paramBean) {
		final String sql = getQuery("OIDSTGID_STG_IDENTIFIERS_POSTQUERY");
		final RowMapper<ProfileTypes> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, ProfileTypes.class,
				mMapping);
		List<ProfileTypes> returnList = new ArrayList<ProfileTypes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgIdentifiersPostQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * stgIdentifiersPostQuery
	 * 
	 * @return List<Images>
	 * @param params
	 */
	public List<Images> stgIdentifiersPostQuery(final Images paramBean) {
		final String sql = getQuery("OIDSTGID_STG_IDENTIFIERS_POSTQUERY");
		final RowMapper<Images> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		List<Images> returnList = new ArrayList<Images>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgIdentifiersPostQuery", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * stgIdentifyingWordsPreInsert
	 * 
	 * @param params
	 * @return List<StgIdentifyingWords>
	 */
	public List<StgIdentifyingWords> stgIdentifyingWordsPreInsert(final StgIdentifyingWords paramBean) {
		final String sql = getQuery("OIDSTGID_STG_IDENTIFYING_WORDS_PREINSERT");
		final RowMapper<StgIdentifyingWords> columnRowMapper = Row2BeanRowMapper.makeMapping(sql,
				StgIdentifyingWords.class, stgIdentifyingWordsMapping);
		List<StgIdentifyingWords> returnList = new ArrayList<StgIdentifyingWords>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("stgIdentifyingWordsPreInsert", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * createFormGlobals
	 * 
	 * @param params
	 * @return List<OmsModules>
	 */
	public List<OmsModules> createFormGlobals(final OmsModules paramBean) {
		final String sql = getQuery("OIDSTGID_CREATE_FORM_GLOBALS");
		final RowMapper<OmsModules> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, OmsModules.class,
				omsModulesMapping);
		List<OmsModules> returnList = new ArrayList<OmsModules>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
		} catch (Exception e) {
			logger.error("createFormGlobals", e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * identifierSeqData
	 * 
	 * @return Long
	 * @param stgId
	 */
	@Override
	public Long identifierSeqData(final Long stgId) {
		Long returnObj = null;
		final String sql = getQuery("OIDSTGID_STG_IDENTIFIERS_PREINSERT");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), Long.class);
		return returnObj;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * wordSeqData
	 * 
	 * @return Long
	 * @param stgId
	 */
	public Long wordSeqData(final Long stgId) {
		Long returnObj = null;
		final String sql = getQuery("OIDSTGID_STG_IDENTIFYING_WORDS_PREINSERT");
		returnObj = namedParameterJdbcTemplate.queryForObject(sql, createParams("STGID", stgId), Long.class);
		return returnObj;
	}

	/**
	 * @return String
	 * @param stgId
	 */
	public String oidstgidGetGlobalStgDescription(final BigDecimal stgId) {
		final String sql = getQuery("OIDSTGID_GET_GLOBAL_STG_DESCRIPTION");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("stgId", stgId), String.class);
		} catch (final Exception e) {
			logger.error("oidstgidGetGlobalStgDescription", e);
			return "";
		}
	}
	
	@Override
	public List<StgIdentifiers> getAllStgIndentifiersData(Long stgId,String profileType) {
		final String sql = getQuery("OIDSTGID_FIND_STG_INDENTIFIERS_DATA_ONE");
		final RowMapper<StgIdentifiers> mRowMapper = Row2BeanRowMapper.makeMapping(sql, StgIdentifiers.class, stgIdentifiersMapping);
		List<StgIdentifiers> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("STGID", stgId,"PROFILE_TYPE",profileType), mRowMapper);
		} catch (Exception e) {
			logger.error("rgProfileTypeRecordGroup", e);
		}
		return returnList;
	}

}
