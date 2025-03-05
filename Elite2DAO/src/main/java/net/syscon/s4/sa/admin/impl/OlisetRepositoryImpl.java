package net.syscon.s4.sa.admin.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.SystemLables;
import net.syscon.s4.sa.admin.OlisetRepository;

@Repository
public class OlisetRepositoryImpl extends RepositoryBase implements OlisetRepository {

    private static Logger logger = LogManager.getLogger(OlisetRepositoryImpl.class.getName());

   
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
   
    private final Map<String, FieldMapper> systemLableMapping = new ImmutableMap.Builder<String, FieldMapper>()
			 .put("LABEL_ID"    ,  new FieldMapper("labelId"))
			 .put("MODULE_NAME" ,  new FieldMapper ("moduleName"))
			 .put("MSG_KEY",    	new FieldMapper("msgKey"))
			 .put("MSG_VALUE",    new FieldMapper ("msgValue"))
			 .put("MSG_TYPE", 		new FieldMapper("msgType")) 
			 .put("CREATE_DATETIME", new FieldMapper("createDateTime"))   
			 .put("CREATE_USER_ID",   new FieldMapper("createUserId"))  
			 .put("MODIFY_DATETIME",  new FieldMapper ("modifyDateTime"))  
			 .put("MODIFY_USER_ID",   new FieldMapper ("modifyUserId")).build();

    /**
     * Creates new OumrestaRepositoryImpl class Object
     */
    
    public OlisetRepositoryImpl() {
    }

    /**
     * Fetch the records from database table
     *
     * @param objSearchDao OmsModules
     * @return List<OmsModules>
     * @throws SQLException
     */

	
	static java.sql.Date getCurrentDatetime() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	
	@Override
	public List<SystemLables> labelExecuteQuery(String codeInput) {
		 final String sql = getQuery("OUMSYLAB_EXECUTE_SYSTEM_LABEL");
	        final RowMapper<SystemLables> rowMap = Row2BeanRowMapper.makeMapping(sql, SystemLables.class, systemLableMapping);
	        return namedParameterJdbcTemplate.query(sql,createParams("moduleName",codeInput), rowMap);
	}
	

	@Override
	public int updateSystemlabel(List<SystemLables> updateList) {
		final String sql = getQuery("OUMSYLAB_LABEL_UPDATE_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final SystemLables list : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int insertContainerImg(Images images) {
		final String sql = getQuery("OUMSYLAB_IMAGES_INSERT_IMAGES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(images));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
				return 10;
			} catch (Exception e) {
				logger.error("returnArray.length +",returnArray.length+"Exception"+e);
				e.printStackTrace();
			}
			if (returnArray.length>0) {
				return 1;
			} else {
				return 0;
			}
	}

	@Override
	public List<Images> imagesExecuteQuery(Images searchBean) {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OUMSYLAB_IMAGES_FIND_IMAGES"),imagesMapping ).build();
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		String preparedSql = null;
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);

		if (searchBean.getImageId() != null) {
			sqlQuery.append("I.IMAGE_ID = :IMAGE_ID" + " AND ");
			inParameterSource.addValue("IMAGE_ID", searchBean.getImageId());
		}
		if (searchBean.getImageObjectId() != null) {
			sqlQuery.append("I.IMAGE_OBJECT_ID = :IMAGE_OBJECT_ID" + " AND ");
			inParameterSource.addValue("IMAGE_OBJECT_ID", searchBean.getImageObjectId());
		}
		if (searchBean.getImageObjectType() != null) {
			sqlQuery.append("I.IMAGE_OBJECT_TYPE = :IMAGE_OBJECT_TYPE" + " AND ");
			inParameterSource.addValue("IMAGE_OBJECT_TYPE", searchBean.getImageObjectType());
		}
		if (searchBean.getActiveFlag() != null) {
			sqlQuery.append("I.ACTIVE_FLAG = :ACTIVE_FLAG");
			inParameterSource.addValue("ACTIVE_FLAG", searchBean.getActiveFlag());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		return namedParameterJdbcTemplate.query(preparedSql, inParameterSource, imagesRowMapper);

	}

	@Override
	public String inactiveImage(Images searchBean) {
		final String sql = getQuery("OUMSYLAB_IMAGE_INACTIVE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(searchBean));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			} catch (DataIntegrityViolationException | UncategorizedSQLException e) {
				return "";
			} catch (Exception e) {
				logger.error("returnArray.length +",returnArray.length+"Exception"+e);
				e.printStackTrace();
			}
			if (returnArray.length>0) {
				return "SUCCESS";
			} else {
				return "FAIL";
			}
	}
	
	@Override
	public List<Images> getLoginLogo() {
		final String sql = queryBuilderFactory.getQueryBuilder(getQuery("OUMSYLAB_IMAGES_FIND_IMAGES"),imagesMapping ).build();
		final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, imagesMapping);
		String preparedSql = null;
		List<Images> im =new ArrayList<>();
		Images  searchBean= new Images();
		searchBean.setImageObjectId(new BigDecimal(1));
		searchBean.setImageObjectType("LOGO");
		searchBean.setActiveFlag("Y");
		searchBean.setImageObjectSeq(new BigDecimal(1));
		final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
		final StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append(sql);

		if (searchBean.getImageId() != null) {
			sqlQuery.append("I.IMAGE_ID = :IMAGE_ID" + " AND ");
			inParameterSource.addValue("IMAGE_ID", searchBean.getImageId());
		}
		if (searchBean.getImageObjectId() != null) {
			sqlQuery.append("I.IMAGE_OBJECT_ID = :IMAGE_OBJECT_ID" + " AND ");
			inParameterSource.addValue("IMAGE_OBJECT_ID", searchBean.getImageObjectId());
		}
		if (searchBean.getImageObjectType() != null) {
			sqlQuery.append("I.IMAGE_OBJECT_TYPE = :IMAGE_OBJECT_TYPE" + " AND ");
			inParameterSource.addValue("IMAGE_OBJECT_TYPE", searchBean.getImageObjectType());
		}
		if (searchBean.getActiveFlag() != null) {
			sqlQuery.append("I.ACTIVE_FLAG = :ACTIVE_FLAG");
			inParameterSource.addValue("ACTIVE_FLAG", searchBean.getActiveFlag());
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		im= namedParameterJdbcTemplate.query(preparedSql, inParameterSource, imagesRowMapper);
		return im;

	}



}
