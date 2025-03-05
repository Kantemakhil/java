package net.syscon.s4.pkgs.otdclina.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OffenderExternalMovements;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTrustAccounts;
import net.syscon.s4.inmate.beans.OffenderTrustAccountsTemp;
import net.syscon.s4.pkgs.otdclina.OtdclinaPkgRepository;

@Repository
public class OtdclinaPkgRepositoryImpl extends RepositoryBase implements OtdclinaPkgRepository {

	private static Logger logger = LogManager.getLogger(OtdclinaPkgRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public Integer offenderTrustAccountsTemp(final Long psessionid, final String pcaseloadid) {
		final String sql = getQuery("OFFENDER_TRUST_ACCOUNTS_TEMP");
		Integer result = null;
		try {
			result = namedParameterJdbcTemplate.update(sql,
					createParams("p_session_id", psessionid,"p_caseload_id", pcaseloadid));
		} catch (Exception e) {
			logger.error("offenderTrustAccountsTemp", e);
		}
		return result;
	}

	@Override
	public List<OffenderTrustAccounts> allC(final OffenderTrustAccountsTemp searchRecord) {
		final String sql = getQuery("All_C");
		final RowMapper<Object[]> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, mMapping);
		List<OffenderTrustAccounts> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("P_CASELOAD_ID",
					searchRecord.getCaseloadId()),new BeanPropertyRowMapper<OffenderTrustAccounts>(OffenderTrustAccounts.class));
		} catch (Exception e) {
			logger.error("allC", e);
		}
		return returnList;

	}
	
	@Override
	public List<OffenderExternalMovements> maxMovementC(final OffenderTrustAccountsTemp searchRecord) {
		final String sql = getQuery("Max_Movement_C");
		final RowMapper<Object[]> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, mMapping);
		List<OffenderExternalMovements> returnList = new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_OFFENDER_ID", searchRecord.getOffenderId(), "offender_book_id",
							searchRecord.getOffenderBookId(), "P_CASELOAD_ID", searchRecord.getCaseloadId(),
							"P_FROM_DATE", searchRecord.getCreateDateTime(), "P_TO_DATE", searchRecord.getModifyDateTime()),
					new BeanPropertyRowMapper<OffenderExternalMovements>(OffenderExternalMovements.class));
		} catch (Exception e) {
			logger.error("Max_Movement_C", e);
		}
		return returnList;
	}

	@Override
	public List<OffenderTrustAccounts> zeroC(final OffenderTrustAccountsTemp searchRecord) {
		final String sql = getQuery("ZERO_C");
		final RowMapper<Object[]> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, mMapping);
		List<OffenderTrustAccounts> returnList = new ArrayList<OffenderTrustAccounts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_CASELOAD_ID", searchRecord.getCaseloadId()),new BeanPropertyRowMapper<OffenderTrustAccounts>(OffenderTrustAccounts.class));
		} catch (Exception e) {
			logger.error("zeroC", e);
		}
		return returnList;
	}

	@Override
	public List<OffenderTrustAccounts> notifyC(final OffenderTrustAccountsTemp searchRecord) {
		final String sql = getQuery("NOTIFY_C");
		final RowMapper<Object[]> columnRowMapper = Row2BeanRowMapper.makeMapping(sql, Object[].class, mMapping);
		List<OffenderTrustAccounts> returnList = new ArrayList<OffenderTrustAccounts>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("P_CASELOAD_ID", searchRecord.getCaseloadId()), new BeanPropertyRowMapper<OffenderTrustAccounts>(OffenderTrustAccounts.class));
		} catch (Exception e) {
			logger.error("notifyC", e);
		}
		return returnList;
	}

	@Override
	public Integer agyLocIDExistsC(final String pcaseloadid, final String pagylocid) {
		final String sql = getQuery("AGY_LOC_ID_EXISTS_C");
		int retval = 0;
		try {
			retval = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_caseload_id", pcaseloadid, "p_caseload_id", pagylocid), Integer.class);
		} catch (Exception e) {
			logger.error("agyLocIDExistsC", e);
		}
		return retval;
	}

	@Override
	public Integer offenderTrustAccountsTemp(final OffenderTrustAccountsTemp searchRecord) {
		final String sql = getQuery("OFFENDER_TRUST_ACCOUNTS_TEMP1");
		int retval = 0;
		try {
			/*
			 * retval = namedParameterJdbcTemplate.update(sql, createParams("caseload_id",
			 * searchRecord.getCaseloadId(), "offender_id", searchRecord.getOffenderId(),
			 * "session_id", searchRecord.getSessionId(), "account_closed_flag",
			 * searchRecord.getAccountClosedFlag(), "hold_balance",
			 * searchRecord.getHoldBalance(), "current_balance",
			 * searchRecord.getCurrentBalance(), "modify_date",
			 * searchRecord.getModifyDate(), "modify_user_id",
			 * searchRecord.getModifyUserId(), "notify_date", searchRecord.getModifyDate(),
			 * "offender_book_id", searchRecord.getOffenderBookId(), "last_name",
			 * searchRecord.getLastName(), "first_name", searchRecord.getFirstName(),
			 * "movement_date", searchRecord.getMovementDate(), "agy_loc_id",
			 * searchRecord.getAgyLocId(), "Offender_Id_Display",
			 * searchRecord.getOffenderIdDisplay(), "createUserId",
			 * searchRecord.getCreateUserId()));
			 */
			retval=namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(searchRecord));
		} catch (Exception e) {
			logger.error("offenderTrustAccountsTemp", e);
		}
		return retval;
	}

}