package net.syscon.s4.inst.property.impl;

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
import net.syscon.s4.common.beans.Caseloads;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.property.OumbundlRepository;
import net.syscon.s4.sa.admin.beans.PropertyBundleItems;
import net.syscon.s4.sa.admin.beans.PropertyBundles;

@Repository
public class OumbundlRepositoryImpl extends RepositoryBase implements OumbundlRepository {

	private static Logger logger = LogManager.getLogger(OumbundlRepositoryImpl.class);
	private final Map<String, FieldMapper> propertyBundlesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("GROUP_ID",				new FieldMapper("groupId"))
			.put("GROUP_NAME", 				new FieldMapper("groupName"))
			.put("PROPERTY_SEQ", 			new FieldMapper("propertySeq"))
			.put("CASELOAD_ID", 			new FieldMapper("caseloadId"))
			.put("ACTIVE_FLAG",				new FieldMapper("activeFlag"))
			.put("EXPIRED_DATE", 			new FieldMapper("expiredDate"))
			.build();
	private final Map<String, FieldMapper> propertyBundlesItemsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROPERTY_ITEM_ID", 		new FieldMapper("propertyItemId"))
			.put("PROPERTY_TYPE_CODE", 		new FieldMapper("propertyTypeCode"))
			.put("GROUP_ID", 				new FieldMapper("groupId"))
			.put("PROPERTY_DESCRIPTION", 	new FieldMapper("propertyDescription"))
			.put("QUANTITY", 				new FieldMapper("quantity"))
			.put("CONDITION_CODE", 			new FieldMapper("conditionCode"))
			.put("SEQUENCE", 				new FieldMapper("sequence"))
			.build();
	 public final Map<String, FieldMapper> caseLoadMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", 			new FieldMapper("description"))
			.put("CODE", 					new FieldMapper("code"))
			.build();

	@Override
	public List<PropertyBundles> getPropertyGroups() {
		final String sql = getQuery("OUMBUNDL_GET_PROPERTY_BUNDLES");
		final RowMapper<PropertyBundles> propertyBundlesRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PropertyBundles.class, propertyBundlesMapping);
		List<PropertyBundles> propertyBundles = namedParameterJdbcTemplate.query(sql, propertyBundlesRowMapper);
		return propertyBundles;
	}

	@Override
	public List<PropertyBundleItems> getPropertyItems(String groupId) {
		final String sql = getQuery("OUMBUNDL_GET_PROPERTY_BUNDLES_ITEMS");
		final RowMapper<PropertyBundleItems> propertyItemsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				PropertyBundleItems.class, propertyBundlesItemsMapping);
		List<PropertyBundleItems> propertyItems = namedParameterJdbcTemplate.query(sql,
				createParams("groupId", groupId), propertyItemsRowMapper);
		return propertyItems;
	}

	@Override
	public Integer insertPropertyBundles(List<PropertyBundles> listPropertyBundles) {
		final String sql = getQuery("OUMBUNDL_INSERT_PROPERTY_BUNDLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PropertyBundles list : listPropertyBundles) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Property Bundles Insert Exception : ", e);
		}
		if (listPropertyBundles.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updatePropertyBundles(List<PropertyBundles> updateList) {
		final String sql = getQuery("OUMBUNDL_UPDATE_PROPERTY_BUNDLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PropertyBundles propertyBundle : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(propertyBundle));
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
	public Integer deletePropertyBundles(List<PropertyBundles> deleteList) {
		final String sql = getQuery("OUMBUNDL_DELETE_PROPERTY_BUNDLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PropertyBundles list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "PROPERTY_GROUPS";
			String whereCondition = "GROUP_ID = :groupId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertPropertyItems(List<PropertyBundleItems> listPropertyItems) {
		final String sql = getQuery("OUMBUNDL_INSERT_PROPERTY_ITEMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PropertyBundleItems list : listPropertyItems) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Property Bundles Insert Exception : ", e);
		}
		if (listPropertyItems.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer updatePropertyItems(List<PropertyBundleItems> updateList) {
		final String sql = getQuery("OUMBUNDL_UPDATE_PROPERTY_ITEMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PropertyBundleItems list : updateList) {
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
	public Integer deletePropertyItems(List<PropertyBundleItems> deleteList) {
		final String sql = getQuery("OUMBUNDL_DELETE_PROPERTY_ITEMS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final PropertyBundleItems list : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(list));
		}
		try {
			String tableName = "PROPERTY_GROUP_ITEMS";
			String whereCondition = "PROPERTY_ITEM_ID = :propertyItemId";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(e);
		}
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Caseloads> getCaseloads(StaffMembers searchBean) {
		final String sql = getQuery("OUMBUNDL_FIND_CASELOAD");
		final RowMapper<Caseloads> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Caseloads.class,
				caseLoadMapping);
		List<Caseloads> returnList = new ArrayList<Caseloads>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("userId", searchBean.getCreateUserId()), columnRowMapper);
		} catch (Exception e) {
			logger.error("", e);
		}
		return returnList;
	}

}
