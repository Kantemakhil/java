package net.syscon.s4.cm.intakeclosure.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.intakeclosure.OciintrrRepository;
import net.syscon.s4.common.beans.OffIntakeReviewQueue;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import oracle.jdbc.OracleTypes;
@Repository
public class OciintrrRepositoryImpl extends RepositoryBase implements OciintrrRepository {

	private final Map<String, FieldMapper> intakeReviewMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFFENDER_ID_DISPLAY", new FieldMapper("offenderIdDisplay"))
			.put("BIRTH_DATE", new FieldMapper("birthDate"))
			.put("AGY_LOC_ID", new FieldMapper("agyLocId"))
			.put("LAST_NAME", new FieldMapper("lastName"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("MIDDLE_NAME", new FieldMapper("middleName"))
			.put("BOOKING_NO", new FieldMapper("bookingNo"))
			.put("QUEUE_DATETIME", new FieldMapper("queuedDate"))
			.put("AGY_LOC_ID_FROM", new FieldMapper("agyLocIdFrom"))
			.put("AGY_LOC_ID_TO", new FieldMapper("agyLocIdTo"))
			.put("INTAKE_REASON", new FieldMapper("intakeReason"))
			.put("INTAKE_DATE", new FieldMapper("intakeDate"))
			.put("OFFENDER_BOOKID", new FieldMapper("offenderBookId"))
			.put("SUP_STATUS", new FieldMapper("supStatus"))
			.put("SUP_STATUS_DATETIME", new FieldMapper("supStatusDatetime"))
			.put("ACCEPTED_FLAG", new FieldMapper("acceptedFlag"))
			.put("ACCEPTED_DATE", new FieldMapper("acceptedDate"))
			.put("BILLABLE_FLAG", new FieldMapper("billableFlag"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.build();

	@Override
	public List<OffIntakeReviewQueue> offIntakeReiewQuExecuteQuery(final OffIntakeReviewQueue searchBean) {
		final String sql = getQuery("OCIINTRR_GET_INATKE_REVIEW_DATA");
		final RowMapper<OffIntakeReviewQueue> IntakeRevQRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffIntakeReviewQueue.class, intakeReviewMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("USERID",searchBean.getCreateUserId()), IntakeRevQRowMapper);
	}

	@Transactional
	public Integer offIntakeRevAccept(final OffIntakeReviewQueue bean) {
		final String sql = getQuery("OCIINTRR_INTAKEREVIEW_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(bean));
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	/**
	 * This method will update the transaction in the table
	 * 
	 * @param offTrans
	 * @return String
	 */
	public String checkAccountSatus(final OffIntakeReviewQueue bean) {
		String openAnAccount = null;
		Map<String, Object> returnObject = null;
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		SqlParameter[] sqlParameters = new SqlParameter[10];
		sqlParameters = new SqlParameter[] { new SqlParameter("P_CSLD_ID", OracleTypes.VARCHAR),
				new SqlParameter("P_OFFENDER_ID", OracleTypes.NUMBER),
				new SqlOutParameter("P_OPEN_AN_ACCOUNT", OracleTypes.VARCHAR), };
		SimpleJdbcCall simpleJDBCCall = new SimpleJdbcCall(dataSource).withSchemaName("OMS_OWNER")
				.withCatalogName("TRUST").withProcedureName("CHK_ACCOUNT_STATUS").declareParameters(sqlParameters);
		inParamMap.put("P_CSLD_ID", bean.getCaseloadId());
		inParamMap.put("P_OFFENDER_ID", bean.getOffenderId());
		final SqlParameterSource inParameter = new MapSqlParameterSource(inParamMap);
		try {
			returnObject = simpleJDBCCall.execute(inParameter);
			openAnAccount = String.valueOf(returnObject.get("P_OPEN_AN_ACCOUNT"));
		} catch (Exception e) {
//			logger.error("checkAccountSatus :" + e);
		}
		return openAnAccount;
	}


}
