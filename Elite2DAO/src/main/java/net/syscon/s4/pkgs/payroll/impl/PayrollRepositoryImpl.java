package net.syscon.s4.pkgs.payroll.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.payroll.PayrollRepository;

@Repository
public class PayrollRepositoryImpl extends RepositoryBase implements PayrollRepository {

	private static Logger logger = LogManager.getLogger(PayrollRepositoryImpl.class.getName());

	final Map<String, FieldMapper> workAssignmentsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OFF_ID", new FieldMapper("offId")).put("CSLD_ID", new FieldMapper("csldId")).build();

	// This method is used to GET_WORK_ASSIGNMENT_ID list
	@Override
	public List<BigDecimal> getWorkAssignmentIdList(final BigDecimal offId, final String csldId) {
		final String sql = getQuery("GET_WORK_ASSIGNMENT_ID");
		List<BigDecimal> resultList = new ArrayList<>();
		try {
			resultList = namedParameterJdbcTemplate.queryForList(sql, createParams("OFF_ID", offId, "CSLD_ID", csldId),
					BigDecimal.class);
		} catch (Exception e) {
			logger.error("getWorkAssignmentId :", e);
		}
		return resultList;
	}

	// This method to GET_WORK_ASSIGNMENT_STATUSES_SEQ
	@Override
	public Long getWorkAssignmentStatusesSeq(final BigDecimal wrkAsgnId) {
		final String sql = getQuery("GET_WORK_ASSIGNMENT_STATUSES_SEQ");
		Long seq = null;
		try {
			seq = namedParameterJdbcTemplate.queryForObject(sql, createParams("WRK_ASGN_ID", wrkAsgnId), Long.class);
		} catch (Exception e) {
			logger.error("Exception :", e);
		}
		return seq;
	}

	// This method to Insert WORK_ASSIGNMENT_STATUSES table
	@Override
	public Integer workAssignmentStatusesInsert(final BigDecimal wrkAsgnId, final BigDecimal nextStsSeq,
			final Date pMovementDate, final String userName) {
		final String sql = getQuery("WORK_ASSIGNMENT_STATUSES_INSERT");
		final Map<String, Object> inParamMap = new HashMap<String, Object>();
		Integer retVal = 0;

		inParamMap.put("WRK_ASGN_ID", wrkAsgnId);
		inParamMap.put("NEXT_STS_SEQ", nextStsSeq);
		inParamMap.put("P_MOVEMENT_DATE", pMovementDate);
		inParamMap.put("createUserId", userName);
		try {
			retVal = namedParameterJdbcTemplate.update(sql, inParamMap);
		} catch (Exception e) {
			retVal = 0;
			logger.error("insertOffenderVisitVisitor", e);
		}
		return retVal;
	}

	// This method to updateWorkAssignments
	@Override
	public Integer updateWorkAssignments(final BigDecimal offId, final String csldId, final Date pMovementDate,
			final String userName) {
		final String sql = getQuery("UPDATE_WORK_ASSIGNMENTS");
		Integer effeRows = 0;
		try {
			effeRows = namedParameterJdbcTemplate.update(sql, createParams("P_MOVEMENT_DATE", pMovementDate, "OFF_ID",
					offId, "CSLD_ID", csldId, "modifyUserId", userName));
		} catch (DataAccessException e) {
			logger.error("updateOffeVisitVisitors :" + e);
		}
		return effeRows;
	}

}
