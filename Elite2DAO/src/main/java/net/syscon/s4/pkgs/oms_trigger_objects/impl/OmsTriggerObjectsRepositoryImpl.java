package net.syscon.s4.pkgs.oms_trigger_objects.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderPptyConTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItemTxns;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.pkgs.oms_trigger_objects.OmsTriggerObjectsRepository;
import net.syscon.s4.pkgs.oms_utils.OmsUtilsRepository;

@Repository
public class OmsTriggerObjectsRepositoryImpl extends RepositoryBase implements OmsTriggerObjectsRepository {
	private final Logger logger = LogManager.getLogger(OmsTriggerObjectsRepositoryImpl.class);

	@Autowired
	OmsUtilsRepository omsUtilsRepository;

	@Override
	public Integer createItemTransaction(final OffenderPptyItemTxns offenderPptyItemTxns) {
		Integer returnValue = 0;
		final String sql = getQuery("OMS_TRIGGER_OBJECTS_CREATE_ITEM_TRANSACTION");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderPptyItemTxns));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("createItemTransaction ", e);
			omsUtilsRepository.getSystemMsg(10, "OMS");
		}
		return returnValue;

	}

	@Override
	public Integer createContainerTransaction(OffenderPptyConTxns offenderPptyConTxns) {
		Integer returnValue = 0;
		final String sql = getQuery("OMS_TRIGGER_OBJECTS_CREATE_CONTAINER_TRANSACTION");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderPptyConTxns));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("createContainerTransaction ", e);
			omsUtilsRepository.getSystemMsg(10, "OMS");
		}
		return returnValue;

	}

	@Override
	public Integer deleteItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns) {
		Integer returnValue = 0;
		final String sql = getQuery("OMS_TRIGGER_OBJECTS_DELETE_ITEM_TRANSACTION");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderPptyItemTxns));
		try {
			String tableName = "OFFENDER_PPTY_ITEM_TXNS";
			String whereCondition = "offender_book_id = :offenderBookId AND property_item_seq = :propertyItemSeq  AND to_status_code = :toStatusCode";
			batchUpdatePreDeletedRows(tableName, whereCondition, parameters);
		} catch (Exception e) {
			logger.error(e);
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("deleteItemTransaction ", e);
			omsUtilsRepository.getSystemMsg(10, "OMS");
		}
		return returnValue;

	}

	@Override
	public Integer changeItemsAgencyLocation(OffenderPptyItems offenderPptyItems) {
		Integer returnValue = 0;
		final String sql = getQuery("OMS_TRIGGER_OBJECTS_CHANGE_ITEMS_AGENCY_LOCATION");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderPptyItems));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("changeItemsAgencyLocation ", e);
		}
		return returnValue;

	}

	@Override
	public Integer updateItemTransaction(OffenderPptyItemTxns offenderPptyItemTxns) {
		Integer returnValue = 0;
		final String sql = getQuery("OMS_TRIGGER_OBJECTS_UPDATE_ITEM_TRANSACTION");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(offenderPptyItemTxns));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("updateItemTransaction ", e);
			omsUtilsRepository.getSystemMsg(10, "OMS");
		}
		return returnValue;

	}

}
