package net.syscon.s4.inst.legals.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.ApplicationConstants;
import net.syscon.s4.common.beans.OdynfrmSubmitDataBean;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.legals.OcipenscRepository;

@Repository
public class OcipenscRepositoryImpl extends RepositoryBase implements OcipenscRepository {

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcipenscRepositoryImpl.class.getName());

	@Override
	public List<OdynfrmSubmitDataBean> getPendingSentenceCalcEvnets() {
		String sql = getQuery("DYNAMIC_PENDING_SENTENCE_CALC_DATA");
		List<OdynfrmSubmitDataBean> returnList = new ArrayList<OdynfrmSubmitDataBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, new RowMapperResultSetExtractor<OdynfrmSubmitDataBean>(
					new BeanPropertyRowMapper<OdynfrmSubmitDataBean>(OdynfrmSubmitDataBean.class)));
			if (returnList.isEmpty()) {
				return new ArrayList<OdynfrmSubmitDataBean>();
			}
			for (OdynfrmSubmitDataBean obj : returnList) {
				obj.setFormInfoJson(new String(obj.getCalcReason()));
			}
		} catch (Exception e) {
			logger.error("getPendingSentenceCalcEvnets :: ", e.getMessage());
			return Collections.emptyList();
		}
		return returnList;
	}

	@Override
	public List<OdynfrmSubmitDataBean> getOffenderPendingCalcEvent(OdynfrmSubmitDataBean searchBean) {
		String formIdentifier = "'%" + searchBean.getSearchString() + "%'";
		String sql = getQuery("OCIPENSC_GET_OFFENDER_PENDING_EVENT").replace("%formdentifier%",
				formIdentifier.toLowerCase());
		;
		List<OdynfrmSubmitDataBean> returnList = new ArrayList<OdynfrmSubmitDataBean>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, new RowMapperResultSetExtractor<OdynfrmSubmitDataBean>(
					new BeanPropertyRowMapper<OdynfrmSubmitDataBean>(OdynfrmSubmitDataBean.class)));
			if (returnList.isEmpty()) {
				return new ArrayList<OdynfrmSubmitDataBean>();
			}
			for (OdynfrmSubmitDataBean obj : returnList) {
				obj.setFormInfoJson(new String(obj.getFormInfoJsonBlob()));
			}
		} catch (Exception e) {
			logger.error("getOffenderPendingCalcEvent :: ", e.getMessage());
			return Collections.emptyList();
		}

		return returnList;
	}

	@Override
	public Integer insertOcdLeglsPendingData(OdynfrmSubmitDataBean odynfrmSubmitDataBean) {
		final String sql = getQuery("OCIPENSC_INSERT_OCDLEGLS_DATA_PENDING_CALCULATION");
		int[] returnArray = new int[] {};
		try {
			odynfrmSubmitDataBean.setFormInfoJsonBlob(odynfrmSubmitDataBean.getFormInfoJson().getBytes());
			final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
			parameters.add(new BeanPropertySqlParameterSource(odynfrmSubmitDataBean));
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("insertOcdLeglsPendingData :: ", e.getMessage());
		}
		if (1 == returnArray.length) {
			return 2;
		} else {
			return 0;
		}
	}

	@Override
	public Integer deletePendingSentenceCalcEvents(Integer id,String userName) {
		final String sql = getQuery("OCIPENSC_DELETE_OCDLEGLS_DATA_PENDING_CALCULATION");
		try {
			Map<String, Object> inputMap = new HashMap<String, Object>();
			inputMap.put("id", id);
			inputMap.put("modify_user_id", userName);
			updatePreDeletedRow("ocdlegls_data_pending_calculation", "id = :id", inputMap);
		} catch (Exception e) {
			logger.error(e);
		}
		
		try {
			return namedParameterJdbcTemplate.update(sql,createParams("id",id));
		} catch(Exception e) {
			logger.error("deletePendingSentenceCalcEvents :: ", e.getMessage());
		}
		return 0;
	}
}
