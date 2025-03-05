package net.syscon.s4.inmate.trust.deductions.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inmate.beans.OffenderAdjustmentTxns;
import net.syscon.s4.inmate.trust.deductions.OtucobwhRepository;

/**
 * Class OtucobwhRepositoryImpl
 */
@Repository
public class OtucobwhRepositoryImpl extends RepositoryBase implements OtucobwhRepository {

	private final Map<String, FieldMapper> offenderAdjustmentTxnsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ADJUSTMENT_REASON_CODE",      new FieldMapper("adjustmentReasonCode"))
			.put("ADJUSTMENT_USER_ID",          new FieldMapper("adjustmentUserId"))
			.put("CREATE_USER_ID",              new FieldMapper("createUserId"))
			.put("OFFENDER_DEDUCTION_ID",       new FieldMapper("offenderDeductionId"))
			.put("MODIFY_USER_ID",              new FieldMapper("modifyUserId"))
			.put("OFFENDER_ID",                 new FieldMapper("offenderId"))
			.put("ADJUSTMENT_AMOUNT",           new FieldMapper("adjustmentAmount"))
			.put("SEAL_FLAG",                   new FieldMapper("sealFlag"))
			.put("CREATE_DATETIME",             new FieldMapper("createDatetime"))
			.put("TXN_ENTRY_SEQ",               new FieldMapper("txnEntrySeq"))
			.put("MODIFY_DATETIME",             new FieldMapper("modifyDatetime"))
			.put("ADJUSTMENT_DATE",             new FieldMapper("adjustmentDate"))
			.put("TXN_ID",                      new FieldMapper("txnId"))
			.put("ADJUSTMENT_TEXT",             new FieldMapper("adjustmentText"))
			.put("TXN_POSTING_TYPE",            new FieldMapper("txnPostingType")).build();
	/**
	 * Creates new OtucobwhRepositoryImpl class Object
	 */
	public OtucobwhRepositoryImpl() {
		// OtucobwhRepositoryImpl
	}


	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderAdjustmentTxns
	 *
	 * @return List<OffenderAdjustmentTxns>
	 *
	 */
	public List<OffenderAdjustmentTxns> cowohExecuteQuery(final OffenderAdjustmentTxns objSearchDao) {
		final String sql = getQuery("OTUCOBWH_COWOH_FIND_OFFENDER_ADJUSTMENT_TXNS");
		String preparedSql = null;
		final StringBuffer sqlQuery = new StringBuffer();
		final MapSqlParameterSource params = new MapSqlParameterSource();
		sqlQuery.append(sql);
		if (objSearchDao != null) {
			sqlQuery.append(" where ");
			if (objSearchDao != null && objSearchDao.getOffenderDeductionId() != null) {
				sqlQuery.append("OFFENDER_DEDUCTION_ID = :OFFENDER_DEDUCTION_ID" + " and ");
				params.addValue("OFFENDER_DEDUCTION_ID", objSearchDao.getOffenderDeductionId());
			}
		}
		preparedSql = sqlQuery.toString().trim();
		if (preparedSql.endsWith("where")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("and")) {
			preparedSql = preparedSql.substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" ORDER BY TXN_ID DESC ");
		final RowMapper<OffenderAdjustmentTxns> offAdjTxnRowMap = Row2BeanRowMapper.makeMapping(sql,
				OffenderAdjustmentTxns.class, offenderAdjustmentTxnsMapping);
		final ArrayList<OffenderAdjustmentTxns> returnList = (ArrayList<OffenderAdjustmentTxns>) namedParameterJdbcTemplate
				.query(preparedSql,params, offAdjTxnRowMap);
		return returnList;
	}

}
