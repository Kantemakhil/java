package net.syscon.s4.pkgs.omkcopy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;
import net.syscon.s4.inst.casemanagement.beans.WorkFlows;
import net.syscon.s4.pkgs.omkcopy.OmkcopyRepository;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;

@Repository("OmkcopyRepositoryImpl_pg")
public class OmkcopyRepositoryImpl extends RepositoryBase implements OmkcopyRepository {

	private static Logger logger = LogManager.getLogger(OmkcopyRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> copTabMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	private final Map<String, FieldMapper> mapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ORDER_PROPOSAL_CONDITION_ID", new FieldMapper("orderProposalConditionId")).build();

	@Override
	public List<CopyTables> copyTablesCur(final String pMoveType, final String pMoveReason, final String vParent) {
		final String sql = getQuery("GET_COPY_TABLES_CUR");
		List<CopyTables> copTab = new ArrayList<CopyTables>();

		final RowMapper<CopyTables> rowMapper = Row2BeanRowMapper.makeMapping(sql, CopyTables.class, copTabMapping);
		try {
			copTab = namedParameterJdbcTemplate.query(sql,
					createParams("pMoveType", pMoveType, "pMoveReason", pMoveReason, "vParent", vParent), rowMapper);
		} catch (Exception e) {
			logger.error("copyTablesCur", e);
		}
		return copTab;
	}

	@Override
	public List<AllTabColumns> columnListCur(final String pTableName) {
		final String sql = getQuery("GET_COLUMN_LIST_CUR");
		List<AllTabColumns> allTabCol = new ArrayList<AllTabColumns>();

		final RowMapper<AllTabColumns> rowMapper = Row2BeanRowMapper.makeMapping(sql, AllTabColumns.class,
				copTabMapping);
		try {
			allTabCol = namedParameterJdbcTemplate.query(sql, createParams("pTableName", pTableName), rowMapper);
		} catch (Exception e) {
			logger.error("columnListCur", e);
		}
		return allTabCol;

	}

	@Override
	public String getWrongColumnNameFlag(final String pTableName) {
		final String sql = getQuery("GET_WRONG_COLUMN_NAME_FLAG");
		String retVal = "N";
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql, createParams("pTableName", pTableName),
					Integer.class);
		} catch (Exception e) {
			logger.error("getWrongColumnNameFlag", e);
			retVal = "N";
		}
		if (count > 0) {
			retVal = "Y";
		}
		return retVal;
	}

	@Override
	public List<CopyTables> getColSeqNames(final String columnName, final String vMoveType, final String vMoveReason) {
		final String sql = getQuery("GET_COL_SEQ_NAMES");
		final RowMapper<CopyTables> rowMapper = Row2BeanRowMapper.makeMapping(sql, CopyTables.class, mapping);
		List<CopyTables> retList = new ArrayList<CopyTables>();
		try {
			retList = namedParameterJdbcTemplate.query(sql,
					createParams("columnName", columnName, "vMoveType", vMoveType, "vMoveReason", vMoveReason),
					rowMapper);
		} catch (Exception e) {
			logger.error("getColSeqNames", e);
			retList = null;
		}
		return retList;
	}

	@Override
	public Integer getVpkColCount(final String columnName, final String pTableName) {
		final String sql = getQuery("GET_V_PK_COL_COUNT");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("pTableName", pTableName, "columnName", columnName), Integer.class);
		} catch (Exception e) {
			logger.error("getVpkColCount", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer vSelectStringOne(final String columnName, final String pParentTable, final Long vOldBookId,
			final Long vNewBookId) {
		Integer retVal = null;
		final String preparedSql = "SELECT nvl ( MIN(t2.:column_name),0) - nvl(MIN(t1.:column_name ),0 ) FROM :p_parent_table t1, :p_parent_table t2 "
				+ "WHERE t1.offender_book_id = :v_old_book_id AND t2.offender_booking_id = :v_new_book_id";

		try {
			retVal = namedParameterJdbcTemplate.queryForObject(preparedSql, createParams("column_name", columnName,
					"p_parent_table", pParentTable, "v_old_book_id", vOldBookId, "v_new_book_id", vNewBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error("vSelectStringOne", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public Integer vSelectStringTwo(final String columnName, String pParentTable, final Long vOldBookId,
			final Long vNewBookId) {
		Integer retVal = null;
		final String preparedSql = "SELECT nvl(MIN(t2.:column_name), 0) - nvl(MIN(t1.:column_name), 0) FROM :p_parent_table t1, :p_parent_table t2 "
				+ "WHERE t1.offender_book_id = :v_old_book_id AND t2.offender_book_id = :v_new_book_id;";

		try {
			retVal = namedParameterJdbcTemplate.queryForObject(preparedSql, createParams("column_name", columnName,
					"p_parent_table", pParentTable, "v_old_book_id", vOldBookId, "v_new_book_id", vNewBookId),
					Integer.class);
		} catch (Exception e) {
			logger.error("vSelectStringTwo", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void insertSqlOne(final String vInsColList, final String vSelColList, final String lvActiveOnly,
			final String pTableName, final Long vNewBookId, final Long vOldBookId) {
		final String sql = "INSERT INTO :p_table_name (" + vInsColList + ") SELECT " + vSelColList
				+ " FROM :p_table_name WHERE offender_booking_id = :old_book_id " + lvActiveOnly;

		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("NEW_BOOK_ID", vNewBookId);
		inParamMap.put("P_TABLE_NAME", pTableName);
		inParamMap.put("OLD_BOOK_ID", vOldBookId);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertSqlOne", e);
		}

	}

	@Override
	public void insertSqlTwo(final String vInsColList, final String vSelColList, final String lvActiveOnly,
			final String pTableName, final Long vNewBookId, final Long vOldBookId) {
		final String sql = "INSERT INTO "+pTableName + "(" + vInsColList + ") SELECT " + vSelColList
				+ " FROM " +pTableName+" WHERE offender_book_id = :OLD_BOOK_ID " + lvActiveOnly;
		

		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("new_book_id", vNewBookId);
		inParamMap.put("P_TABLE_NAME", pTableName);
		inParamMap.put("OLD_BOOK_ID", vOldBookId);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertSqlTwo", e);
		}
	}

	@Override
	public List<WorkFlows> workFlowCur(final Long pOldBookId) {
		final String sql = getQuery("GET_WORK_FLOW_CUR");
		List<WorkFlows> workFlow = new ArrayList<WorkFlows>();

		final RowMapper<WorkFlows> rowMapper = Row2BeanRowMapper.makeMapping(sql, WorkFlows.class, copTabMapping);
		try {
			workFlow = namedParameterJdbcTemplate.query(sql, createParams("p_old_book_id", pOldBookId), rowMapper);
		} catch (Exception e) {
			logger.error("workFlowCur", e);
		}
		return workFlow;
	}

	@Override
	public Long getIdCur() {
		final String sql = getQuery("GET_ID_CUR");
		Long retVal = 0l;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getIdCur", e);
			retVal = null;
		}
		return retVal;
	}

	@Override
	public void insertWorkFlows(final Long lvWorkFlowId, final String objectCode, final Long pNewBookId,
			final BigDecimal objectSeq, final String userName) {
		final String sql = getQuery("INSERT_WORK_FLOWS_COPY");

		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("LV_WORK_FLOW_ID", lvWorkFlowId);
		inParamMap.put("OBJECT_CODE", objectCode);
		inParamMap.put("P_NEW_BOOK_ID", pNewBookId);
		inParamMap.put("OBJECT_SEQ", objectSeq);
		inParamMap.put("createUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertWorkFlows", e);
		}
	}

	@Override
	public void insertworkFlowLogs(final Long lvWorkFlowId, final String userName) {
		final String sql = getQuery("INSERT_WORK_FLOW_LOGS_COPY");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("LV_WORK_FLOW_ID", lvWorkFlowId);
		inParamMap.put("createUserId", userName);
		try {
			namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			logger.error("insertworkFlowLogs", e);
		}
	}

}
