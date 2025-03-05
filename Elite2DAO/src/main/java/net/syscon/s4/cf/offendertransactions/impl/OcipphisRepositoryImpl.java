package net.syscon.s4.cf.offendertransactions.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcipphisRepository;
import net.syscon.s4.cf.offendertransactions.beans.VPaymentPlanHistories;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.trust.deductions.impl.OcdoobliRepositoryImpl;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;

/**
 * Class OcipphisRepositoryImpl
 */
@Repository
public class OcipphisRepositoryImpl extends RepositoryBase implements OcipphisRepository {
	
	private static Logger logger = LogManager.getLogger(OcdoobliRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> vPayPlaHisMaping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("END_DATE", new FieldMapper("endDate"))
			.put("PAYMENT_DATE", new FieldMapper("paymentDate"))
			.put("GROUP_ID", new FieldMapper("groupId"))
			.put("FREQUENCY", new FieldMapper("frequency"))
			.put("INFORMATION_NUMBER", new FieldMapper("informationNumber"))
			.put("GROUP_CODE", new FieldMapper("groupCode"))
			.put("PAID_AMOUNT", new FieldMapper("paidAmount"))
			.put("OFFENDER_ID", new FieldMapper("offenderId"))
			.put("PAYMENT_AMOUNT", new FieldMapper("paymentAmount"))
			.put("START_DATE", new FieldMapper("startDate"))
			.put("TRANSACTION_DATE", new FieldMapper("transactionDate"))
			.put("PAYMENT_PLAN_SEQ", new FieldMapper("paymentPlanSeq"))
			.put("PAYMENT_CLOSED_DATE", new FieldMapper("paymentClosedDate"))
			.put("PAYMENT_PLAN_ID", new FieldMapper("paymentPlanId")).build();

	/**
	 * Creates new OcipphisRepositoryImpl class Object
	 */
	public OcipphisRepositoryImpl() {
		// constructor
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VPaymentPlanHistories
	 *
	 * @return List<VPaymentPlanHistories>
	 *
	 * @throws SQLException
	 */
	public List<VPaymentPlanHistories> vPaymentPlanHistoriesExecuteQuery(final VPaymentPlanHistories objSearchDao) {
		final String sql = getQuery("OCIPPHIS_VPAYMENTPLANHISTORIES_FIND_V_PAYMENT_PLAN_HISTORIES");
		final StringBuffer pSql = new StringBuffer(sql);
		String preparedSql = "";
		final MapSqlParameterSource param = new MapSqlParameterSource();
		if (objSearchDao != null) {
			pSql.append(" WHERE ");
			if (objSearchDao.getOffenderId() != null) {
				pSql.append(" OFFENDER_ID = :offenderId  AND ");
				param.addValue("offenderId", objSearchDao.getOffenderId());
			}
			if (objSearchDao.getInformationNumber() != null && !objSearchDao.getInformationNumber().isEmpty() && !objSearchDao.getInformationNumber().trim().equals("")) {
				pSql.append(" INFORMATION_NUMBER LIKE :informationNumber AND ");
				param.addValue("informationNumber", objSearchDao.getInformationNumber().trim());
			}
			if (objSearchDao.getGroupId() != null) {
				pSql.append(" GROUP_ID  = :groupId AND ");
				param.addValue("groupId", objSearchDao.getGroupId());
			}
			if (objSearchDao.getGroupCode() != null && !objSearchDao.getGroupCode().isEmpty() && !objSearchDao.getGroupCode().trim().equals("")) {
				pSql.append(" GROUP_CODE LIKE :groupCode AND ");
				param.addValue("groupCode", objSearchDao.getGroupCode().trim());
			}
			if (objSearchDao.getStartDate() != null) {
				pSql.append("START_DATE = :startDate AND ");
				param.addValue("startDate", objSearchDao.getStartDate());
			}
			if (objSearchDao.getEndDate() != null) {
				pSql.append("END_DATE = :endDate AND ");
				param.addValue("endDate", objSearchDao.getEndDate());
			}
			if (objSearchDao.getFrequency() != null && !objSearchDao.getFrequency().isEmpty() && !objSearchDao.getFrequency().trim().equals("")) {
				pSql.append(" FREQUENCY LIKE :frequency AND ");
				param.addValue("frequency", objSearchDao.getFrequency().trim());
			}
			if (objSearchDao.getPaymentClosedDate() != null) {
				pSql.append("PAYMENT_CLOSED_DATE = :paymentClosedDate AND ");
				param.addValue("paymentClosedDate", objSearchDao.getPaymentClosedDate());
			}
			if (objSearchDao.getPaymentDate() != null) {
				pSql.append("PAYMENT_DATE = :paymentDate AND ");
				param.addValue("paymentDate", objSearchDao.getPaymentDate());
			}
			if (objSearchDao.getTransactionDate() != null) {
				pSql.append("TRANSACTION_DATE ::date = :transactionDate AND ");
				param.addValue("transactionDate", objSearchDao.getTransactionDate());
			}
			if (objSearchDao.getPaymentAmount() != null) {
				pSql.append(" PAYMENT_AMOUNT = :paymentAmount AND ");
				param.addValue("paymentAmount", objSearchDao.getPaymentAmount());
			}
			if (objSearchDao.getPaidAmount() != null) {
				pSql.append(" PAID_AMOUNT = :paidAmount AND ");
				param.addValue("paidAmount", objSearchDao.getPaidAmount());
			}
		}
		preparedSql = pSql.toString().trim();

		if (preparedSql.endsWith("WHERE")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 5);
		}
		if (preparedSql.endsWith("AND")) {
			preparedSql = preparedSql.trim().substring(0, preparedSql.length() - 3);
		}
		preparedSql = preparedSql.concat(" order by PAYMENT_CLOSED_DATE DESC, INFORMATION_NUMBER DESC, GROUP_ID ASC, START_DATE ASC ");
		
		final RowMapper<VPaymentPlanHistories> LegalUpdateReasonsRowMapper = Row2BeanRowMapper.makeMapping(preparedSql,
				VPaymentPlanHistories.class, vPayPlaHisMaping);
		List<VPaymentPlanHistories> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(preparedSql, param, LegalUpdateReasonsRowMapper);
		} catch (Exception e) {
			logger.error(e);
		}
		return returnList;
	}
	
	
	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgwhenNewFormInstance
	 *
	 * @param params
	 *
	 */
	public List<Object> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OCIPPHIS_CGWHEN_NEW_FORM_INSTANCE");
		final RowMapper<Object> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object.class, vPayPlaHisMaping);
		return (ArrayList<Object>) namedParameterJdbcTemplate.query(sql, createParams(), columnRowMapper);
	}

}
