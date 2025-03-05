package net.syscon.s4.inst.property.impl;

import java.sql.SQLException;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.Images;
import net.syscon.s4.inst.property.OidmpitmRepository;
import net.syscon.s4.inst.property.bean.Group;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItemEvents;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;

/**
 * Class OidrpitmRepositoryImpl
 *
 */
@Repository
public class OidmpitmRepositoryImpl extends RepositoryBase implements OidmpitmRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OidrpitmRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> offPpIteMap = new ImmutableMap.Builder<String, FieldMapper>()
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

	private final Map<String, FieldMapper> groupDataMap = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GROUP_NAMES", new FieldMapper("groupName")).put("GROUP_ID", new FieldMapper("groupId")).build();

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
			.put("VERIFY_FLAG", new FieldMapper("verifyFlag")).put("EVENT_SEQ", new FieldMapper("eventSeq"))
			.put("PROPERTY_SIZE", new FieldMapper("propertySize")).put("PROPERTY_VALUE", new FieldMapper("propertyValue")).build();

	private final Map<String, FieldMapper> images = new ImmutableMap.Builder<String, FieldMapper>()
			.put("IMAGE_ID", new FieldMapper("imageId")).put("CAPTURE_DATE", new FieldMapper("captureDate"))
			.put("IMAGE_OBJECT_TYPE", new FieldMapper("imageObjectType"))
			.put("IMAGE_OBJECT_ID", new FieldMapper("imageObjectId"))
			.put("IMAGE_VIEW_TYPE", new FieldMapper("imageViewType"))
			.put("IMAGE_OBJECT_SEQ", new FieldMapper("imageObjectSeq"))
			.put("IMAGE_THUMBNAIL", new FieldMapper("imageThumbnail")).put("ACTIVE_FLAG", new FieldMapper("activeFlag"))
			.put("ORIENTATION_TYPE", new FieldMapper("orientationType")).put("SEAL_FLAG", new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME", new FieldMapper("createDatetime"))
			.put("CREATE_USER_ID", new FieldMapper("createUserId"))
			.put("MODIFY_DATETIME", new FieldMapper("modifyDatetime"))
			.put("MODIFY_USER_ID", new FieldMapper("modifyUserId")).put("COU", new FieldMapper("cou")).build();

	/**
	 * Creates new OidrpitmRepositoryImpl class Object
	 */
	public OidmpitmRepositoryImpl() {
		super();
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao OffenderPptyItems
	 *
	 * @return List<OffenderPptyItems>
	 *
	 * @throws SQLException
	 */
	public List<OffenderPptyItems> offPiExecuteQuery(final OffenderPptyItems objSearchDao) {
		final String sql = getQuery("FETECH_PROPERTY_DATA");
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		final RowMapper<OffenderPptyItems> offPptyIteRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offPpIteMap);
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", objSearchDao.getOffenderBookId(), "STATUS_CODE",
						objSearchDao.getStatusCode(), "CASELOAD_ID", objSearchDao.getAgyLocId(), "GROUP_CODE",
						objSearchDao.getGroupId()),
				offPptyIteRowMap);
		return returnList;
	}

	@Override
	public List<Group> fetchGroupNames(String caseloadId) {
		final String sql = getQuery("FETECH_GROUP_NAMES");
		List<Group> groupNames = new ArrayList<Group>();
		final RowMapper<Group> groupRowMap = Row2BeanRowMapper.makeMapping(sql, Group.class, groupDataMap);
		try {
			groupNames = namedParameterJdbcTemplate.query(sql, createParams("caseloadId",caseloadId), groupRowMap);
			groupNames.removeIf(group -> getDefaultValuesForSelecteGroup(group.getGroupId()).size() == 0);
			if (groupNames.size() > 0) {
				for (Group group : groupNames) {
					String sqlForGroupImage = getQuery("GET_GROUP_IMAGE");
					final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sql, Images.class, images);
					final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
					final StringBuffer sqlQuery = new StringBuffer();
					sqlQuery.append(sqlForGroupImage);
					if (group.getGroupId() != null) {
						inParameterSource.addValue("GROUP_ID", group.getGroupId());
					}
					group.setImages(namedParameterJdbcTemplate.query(sqlQuery.toString().trim(), inParameterSource,
							imagesRowMapper));

				}
			}
		} catch (Exception e) {
			logger.error("fetchGroupNames", e);
		}
		return groupNames;
	}

	@Override
	public List<OffenderPptyItems> getDefaultValuesForSelecteGroup(String groupId) {
		final String sql = getQuery("GET_DEFAULT_VALUES");
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		final RowMapper<OffenderPptyItems> offPptyIteRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offPpIteMap);
		returnList = namedParameterJdbcTemplate.query(sql, createParams("GROUP_ID", groupId), offPptyIteRowMap);
		return returnList;
	}

	@Override
	public List<OffenderPptyItems> offPiSearchOffenderPptyItemsForcontainer(OffenderPptyContainers container) {
		final String sql = getQuery("OIDMPITM_OFFPI_OFFENDER_PPTY_ITEMS");
		String statusCode = "";
		final RowMapper<OffenderPptyItems> OffenderPptyItemsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderPptyItems.class, offenderPptyItemsMapping);
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		if (container.getStatusCode() != null) {
			statusCode = container.getStatusCode();
		} else {
			statusCode = "STORED";
		}
		returnList = namedParameterJdbcTemplate.query(sql,
				createParams("OFFENDERBOOKID", container.getOffenderBookId(), "CASELOADID", container.getAgyLocId(),
						"STATUSCODE", statusCode, "PROPERTYCONATINERID", container.getPropertyContainerId()),
				OffenderPptyItemsRowMapper);
		try {
			if (returnList != null) {
				String sqlForImageExistence = getQuery("GET_PROPERTY_IMAGE");

				final RowMapper<Images> imagesRowMapper = Row2BeanRowMapper.makeMapping(sqlForImageExistence,
						Images.class, images);
				final MapSqlParameterSource inParameterSource = new MapSqlParameterSource();
				for (OffenderPptyItems offenderPptyItems : returnList) {
					if (offenderPptyItems.getOffenderBookId() != null) {
						inParameterSource.addValue("OFFENDER_BOOK_ID", offenderPptyItems.getOffenderBookId());
					}
					if (offenderPptyItems.getPropertyItemSeq() != null) {
						inParameterSource.addValue("PROPERTY_ITEM_SEQ", offenderPptyItems.getPropertyItemSeq());
					}
					offenderPptyItems.setImages(namedParameterJdbcTemplate.query(sqlForImageExistence.toString().trim(),
							inParameterSource, imagesRowMapper));
				}
			}
		} catch (Exception e) {
			logger.error("Breaking IN offPiSearchOffenderPptyItemsForcontainer" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return returnList;
	}

	@Override
	public boolean isRegisterProOrContainerExist(Integer offenderBookId) {
		boolean result = false;
		final String sql = getQuery("CHECK_REGISTERED_PROPERTY_OR_CONTAINER_ITEMS");
		Integer containerCount = 0;
		try {
			containerCount = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderBookId", offenderBookId), Integer.class);
		} catch (EmptyResultDataAccessException e) {
			logger.error("isRegisterProOrContainerExist", e);
		}
		if (containerCount > 0) {
			return result = true;
		} else {
			return result = false;
		}

	}

	@Override
	public List<OffenderPptyItems> getContainerProps(OffenderPptyItems property) {
		final String sql = getQuery("OIDMPITM_CON_OFFPI_FIND_OFFENDER_PPTY_ITEMS");
		final RowMapper<OffenderPptyItems> offPptyItems = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				offenderPptyItemsMapping);
		List<OffenderPptyItems> returnList = new ArrayList<OffenderPptyItems>();
		returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", property.getOffenderBookId(),
				"propertyContainerId", property.getPropertyContainerId(), "agyLocId", property.getAgyLocId()),
				offPptyItems);
		return returnList;
	}

	@Override
	public Integer getEventSeq() {
		final String sql = getQuery("OIDMPITM_GET_MAX_EVENT_SEQ_FROM_OFFENDER_PPTY_ITEM_EVENTS");
		Integer count = null;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getEventSeq", e);
		}
		return count;
	}

	@Override
	public Integer saveEvent(List<OffenderPptyItemEvents> insertEventList) {
		final String sql = getQuery("OIDMPITM_ITMTX_INSERT_OFFENDER_PPTY_ITEM_EVENTS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItemEvents obj : insertEventList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertEventList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updateProperties(List<OffenderPptyItems> propList) {
		final String sql = getQuery("OIDMPITM_OFFPI_UPDATE_OFFENDER_PPTY_ITEMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyItems obj : propList) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("updateProperties", e);
		}
		if (propList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deactivateContainer(List<OffenderPptyContainers> updateCon) {
		final String sql = getQuery("OIDMPITM_UPDATE_OFFCONT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderPptyContainers obj : updateCon) {
			parameters.add(new BeanPropertySqlParameterSource(obj));
		}
		try {
			
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("deactivateContainer", e);
		}
		if (updateCon.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public OffenderPptyItems updatePropertiesOldData(final OffenderPptyItems offPpty) {
		final String sql = getQuery("OIDMPITM_OFFPI_UPDATE_OFFENDER_PPTY_ITEMS_OIL_DATA");
		OffenderPptyItems bean = new OffenderPptyItems();
		try {
			bean = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_book_id", offPpty.getOffenderBookId(), "property_item_seq",
							offPpty.getPropertyItemSeq()),
					new BeanPropertyRowMapper<OffenderPptyItems>(OffenderPptyItems.class));
		} catch (Exception e) {
			logger.error("updatePropertiesOldData", e);
		}
		return bean;
	}
}
