package net.syscon.s4.pkgs.oms_property.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_property.OmsPropertyRepository;

@Repository
public class OmsPropertyRepositoryImpl extends RepositoryBase implements OmsPropertyRepository {

	private static Logger logger = LogManager.getLogger(OmsPropertyRepositoryImpl.class);

	private final Map<String, FieldMapper> ageLocMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("AGY_LOC_ID", new FieldMapper("agyLocId")).put("DESCRIPTION", new FieldMapper("description")).build();

	@Override
	public Integer checkStorageCapacity(final String internalLocId) {
		final String sql = getQuery("CHECK_STORAGE_CAPACITY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_property_storage_id", internalLocId),
				Integer.class);
	}

	@Override
	public Integer getTranRoomStorageId(final String trnToAgyLocId) {
		final String sql = getQuery("GET_TRAN_ROOM_STORAGE_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("p_agy_loc_id", trnToAgyLocId),
				Integer.class);
	}

	// -- To update the status and agency location of transferred items.
	@Override
	public Integer offenderPptyItemsUpdate(final OffenderPptyContainers param) {
		Integer result = null;
		final String sql = getQuery("OFFENDER_PPTY_ITEMS_UPDATE");
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("p_status_code", param.getStatusCode(), "p_agy_loc_id", param.getAgyLocId(),
							"p_property_container_id", param.getPropertyContainerId(), "modifyUserId",
							param.getModifyUserId()));
		} catch (DataAccessException e) {
			logger.error("offenderPptyItemsUpdate :" + e);
			return 0;
		}
		return result;
	}

	// -- To Unseal a container if it is sealed.
	// This method is used to update offender_ppty_containers
	@Override
	public Integer unsealContainerUpdate(final Integer propertyContainerId) {
		final String sql = getQuery("OMS_PROPERTY_UPDATE");
		return namedParameterJdbcTemplate.update(sql, createParams("p_property_container_id", propertyContainerId));
	}

	@Override
	public String getUsrAgyLocSelect() {
		final String sql = getQuery("OMS_PROPERTY_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
	}

	@Override
	public String getConAgyLocSelect(Integer propertyContainerId) {
		final String sql = getQuery("GET_CON_AGY_LOC_SELECT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_property_container_id", propertyContainerId), String.class);
	}

	// This method is used to get old data from OFFENDER_PPTY_ITEMS table
	@Override
	public List<OffenderPptyItems> getOldDataOffenderPptyItems(Integer propertyContainerId) {
		final String sql = getQuery("OMS_OFFENDER_PPTY_ITEMS_GETTING_OLD_DATA");
		List<OffenderPptyItems> returnList = new ArrayList<>();
		final RowMapper<OffenderPptyItems> ageLocRowMapper = Row2BeanRowMapper.makeMapping(sql, OffenderPptyItems.class,
				ageLocMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("property_container_id", propertyContainerId), ageLocRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
}