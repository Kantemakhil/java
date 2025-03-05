package net.syscon.s4.cf.offendertransactions.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cf.offendertransactions.OcidoaccRepository;
import net.syscon.s4.cf.offendertransactions.beans.VOffGroupedPaymentPlans;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.AccountCodes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.beans.OffenderSubAccounts;

/**
 * Class OcidoaccRepositoryImpl
 */
@Repository
public class OcidoaccRepositoryImpl extends RepositoryBase implements OcidoaccRepository {


private final Map<String, FieldMapper> accountCodesMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("ACCOUNT_NAME", 						new FieldMapper("accountName"))
.put("TRUST_ACCOUNT_CODE", 					new FieldMapper("trustAccountCode"))
.put("SUB_ACCOUNT_TYPE", 					new FieldMapper("subAccountType"))
.build();
private final Map<String, FieldMapper> offenderSubAccountsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.build();
private final Map<String, FieldMapper> vOffGroupedPaymentPlansMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("MIN_NEXT_PAYMENT_DATE", 						new FieldMapper("minNextPaymentDate"))
.put("MAX_NEXT_PAYMENT_DATE", 						new FieldMapper("maxNextPaymentDate"))
.put("MIN_NEXT_PAYMENT_DATE_TWO", 					new FieldMapper("minNextPaymentDateTwo"))
.put("BALOWING", 									new FieldMapper("balOwing"))
.build();
private final Map<String, FieldMapper> offenderDeductionsMapping = new ImmutableMap.Builder<String, FieldMapper>()
.put("DED_FLAG", 						new FieldMapper("dedFlag"))
.put("TBD_FLAG", 						new FieldMapper("tbdFlag"))
.put("DEDUCTION_DESC", 					new FieldMapper("deductionDesc"))
.build();

	/**
	 * Creates new OcidoaccRepositoryImpl class Object
	 */
	public OcidoaccRepositoryImpl() {
		// OcidoaccRepositoryImpl
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderSubAccounts
	 *
	 * @return List<OffenderSubAccounts>
	 *
	 * @throws SQLException
	 */
	public List<OffenderSubAccounts> offSubaExecuteQuery(OffenderSubAccounts objSearchDao) {
		final String sql = getQuery("OCIDOACC_OFFSUBA_FIND_OFFENDER_SUB_ACCOUNTS");
		final RowMapper<OffenderSubAccounts> OffenderSubAccountsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderSubAccounts.class, offenderSubAccountsMapping);
		return namedParameterJdbcTemplate.query(sql,
				createParams("offenderId", objSearchDao.getOffenderId(), "caseloadId", objSearchDao.getCaseloadId()),
				OffenderSubAccountsRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            VOffGroupedPaymentPlans
	 *
	 * @return List<VOffGroupedPaymentPlans>
	 *
	 * @throws SQLException
	 */
	public List<VOffGroupedPaymentPlans> paySchExecuteQuery(VOffGroupedPaymentPlans objSearchDao) {
		final String sql = getQuery("OCIDOACC_PAYSCH_FIND_V_OFF_GROUPED_PAYMENT_PLANS");
		final RowMapper<VOffGroupedPaymentPlans> VOffGroupedPaymentPlansRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffGroupedPaymentPlans.class, vOffGroupedPaymentPlansMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("offender_id", objSearchDao.getOffenderId()),
				VOffGroupedPaymentPlansRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderDeductions
	 *
	 * @return List<OffenderDeductions>
	 *
	 * @throws SQLException
	 */
	public List<OffenderDeductions> offBncExecuteQuery(OffenderDeductions objSearchDao) {
		final String sql = getQuery("OCIDOACC_OFFBNC_FIND_OFFENDER_DEDUCTIONS");
		final RowMapper<OffenderDeductions> OffenderDeductionsRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OffenderDeductions.class, offenderDeductionsMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("OFFENDER_ID", objSearchDao.getOffenderId(),
				"groupId", objSearchDao.getGroupId(), "informationNumber", objSearchDao.getInformationNumber()),
				OffenderDeductionsRowMapper);
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 * @throws SQLException
	 */
	public Integer sysPflExecuteQuery(final Integer rootOffenderid) {
		final String sql = getQuery("OCIDOACC_SYSPFL_FIND_SYSTEM_PROFILES");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("offender_id", rootOffenderid),
				Integer.class);
	}

	/**
	 * Method getAccountCodesOfTypeAndName
	 * 
	 * @param trustAccountCode
	 * @return AccountCodes
	 */
	public AccountCodes getAccountCodesOfTypeAndName(final Long trustAccountCode) {
		final String sql = getQuery("OCIDOACC_GET_ACCOUNT_CODES_TYPE_NAME");
		AccountCodes returnBean = new AccountCodes();
		final RowMapper<AccountCodes> accountCodesMapper = Row2BeanRowMapper.makeMapping(sql, AccountCodes.class,
				accountCodesMapping);
		try {

			returnBean = namedParameterJdbcTemplate.queryForObject(sql, createParams("accountCode", trustAccountCode),
					accountCodesMapper);
		} catch (EmptyResultDataAccessException e) {
			returnBean = new AccountCodes();
		}
		return returnBean;
	}

	public BigDecimal getMonAmt(OffenderDeductions bean) {
		final String sql = getQuery("OCIDOACC_GET_MON_AMT");
		BigDecimal returnValue;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_deduction_id", bean.getOffenderDeductionId()), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return returnValue;

	}

	public BigDecimal getRecMonth(OffenderDeductions bean) {
		final String sql = getQuery("OCIDOACC_GET_REC_MONTH");
		BigDecimal returnValue;
		try {
			returnValue = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offender_ded_id", bean.getOffenderDeductionId()), BigDecimal.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return returnValue;
	}

	public String getActClosedFlag(final Integer offenderId, final String caseloadid) {
		final String sql = getQuery("OCIDOACC_GET_ACT_CLOSED_FLAG");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("offenderId", offenderId, "caseloadid", caseloadid), String.class);
	}

	public Integer lvNonCompletedSch(final VOffGroupedPaymentPlans bean) {
		final String sql = getQuery("OCIDOACC_LV_NON_COMPLETED_SCH");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("payment_plan_id", bean.getPaymentPlanId()),
				Integer.class);
	}

	public BigDecimal getAmount(final VOffGroupedPaymentPlans bean) {
		final String sql = getQuery("OCIDOACC_GETAMOUNT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("payment_plan_id", bean.getPaymentPlanId(), "dueDate", bean.getDueDate()),
				BigDecimal.class);
	}

	public Date getDaysLate(final BigDecimal planId, final Date nxtPaymentDate) {
		final String sql = getQuery("OCIDOACC_GET_DAYS_LATE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("planId", planId, "next_payment_date", nxtPaymentDate), Date.class);
	}

	public BigDecimal getArrears(final VOffGroupedPaymentPlans bean) {
		final String sql = getQuery("OCIDOACC_GET_ARREARS");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("paymentPlanId", bean.getPaymentPlanId(), "d_date", bean.getTempDate()), BigDecimal.class);
	}

	public Integer getReason(final VOffGroupedPaymentPlans bean) {
		final String sql = getQuery("OCIDOACC_GET_REASON");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("plan_id", bean.getPaymentPlanId(), "tempdate", bean.getTempDate()), Integer.class);
	}

	public BigDecimal getDaysLateQuery(final Date tempDate) {
		final String sql = getQuery("OCIDOACC_DAYS_LATE_QUERY");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("tempDate", tempDate), BigDecimal.class);
	}
}
