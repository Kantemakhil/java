package net.syscon.s4.inmate.trust.deductions.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.OffenderTransactions;
import net.syscon.s4.im.beans.SysDual;
import net.syscon.s4.inmate.beans.DeductionTypes;
import net.syscon.s4.inmate.beans.OffenderDeductions;
import net.syscon.s4.inmate.trust.deductions.OtiopinqRepository;
@Repository
public class OtiopinqRepositoryImpl extends RepositoryBase implements OtiopinqRepository{



	private static Logger logger = LogManager.getLogger(OtiopinqRepositoryImpl.class.getName());



	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderDeductions
	 *
	 * @return List<OffenderDeductions>
	 *
	 * 
	 */
	public List<OffenderDeductions> offDedExecuteQuery(final OffenderDeductions objSearchDao) {
		final String sql = getQuery("OTIOPINQ_OFFDED_FIND_OFFENDER_DEDUCTIONS");
		List<OffenderDeductions> returnList=new ArrayList<>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql,createParams("offenderId", objSearchDao.getOffenderId(), "caseloadId", objSearchDao.getCaseloadId()),new RowMapperResultSetExtractor<OffenderDeductions>(new BeanPropertyRowMapper<OffenderDeductions>(OffenderDeductions.class)));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" offDedExecuteQuery"+e);
		}		
		return returnList;
	}

	/**
	 * @param
	 *
	 *
	 * 
	 */
	public int PRE_INSERT() {
		return 0;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            OffenderTransactions
	 *
	 * @return List<OffenderTransactions>
	 *
	 * 
	 */
	public List<OffenderTransactions> offTxnExecuteQuery(final OffenderTransactions objSearchDao) {
		final String sql = getQuery("OTIOPINQ_OFFTXN_FIND_OFFENDER_TRANSACTIONS");
		List<OffenderTransactions> returnList=Collections.checkedList(new ArrayList<>(), OffenderTransactions.class);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,createParams("caseloadId", objSearchDao.getCaseloadId(), "offenderId", objSearchDao.getOffenderId(),"deductionType", objSearchDao.getDeductionType(), "infoNumber", objSearchDao.getInfoNumber()),new RowMapperResultSetExtractor<OffenderTransactions>(new BeanPropertyRowMapper<OffenderTransactions>(OffenderTransactions.class)));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" offTxnExecuteQuery"+e);
		}		
		return returnList;
	}

	/**
	 * Fetch the records from database table
	 *
	 * @param objSearchDao
	 *            SystemProfiles
	 *
	 * @return List<SystemProfiles>
	 *
	 */
	public List<SystemProfiles> sysPflExecuteQuery(final SystemProfiles objSearchDao) {
		final String sql = getQuery("OTIOPINQ_SYSPFL_FIND_SYSTEM_PROFILES");
		List<SystemProfiles> returnList=Collections.checkedList(new ArrayList<>(), SystemProfiles.class);
		try {
			returnList =  namedParameterJdbcTemplate.query(sql,createParams(), new RowMapperResultSetExtractor<SystemProfiles>(new BeanPropertyRowMapper<SystemProfiles>(SystemProfiles.class)));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" sysPflExecuteQuery"+e);
		}
		return returnList;
	}

	/**
	 * This method is execute a sql query when trigger event is called
	 * 
	 * cgfkchkOffDedOffDedDed
	 *
	 * @param params
	 *
	 */
	public List<DeductionTypes> cgfkchkOffDedOffDedDed() {
		final String sql = getQuery("OTIOPINQ_CGFKCHK_OFF_DED_OFF_DED_DED_T");
		List<DeductionTypes> returnList=Collections.checkedList(new ArrayList<>(), DeductionTypes.class);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), new RowMapperResultSetExtractor<DeductionTypes>(new BeanPropertyRowMapper<DeductionTypes>(DeductionTypes.class)));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkchkOffDedOffDedDed"+e);
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
	public List<SysDual> cgwhenNewFormInstance(final SysDual paramBean) {
		final String sql = getQuery("OTIOPINQ_CGWHEN_NEW_FORM_INSTANCE");
		List<SysDual> returnList=Collections.checkedList(new ArrayList<>(), SysDual.class);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), new RowMapperResultSetExtractor<SysDual>(new BeanPropertyRowMapper<SysDual>(SysDual.class)));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+" cgfkchkOffDedOffDedDed"+e);
		}
		return returnList;
	}

	@Override
	public String chkDedCat(final String deductionType) {
		final String sql = getQuery("OTIOPINQ_CHK_DED_CAT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("deductionType", deductionType),String.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" chkDedCat"+e);
			return null;
		}
	}

	@Override
	public BigDecimal otiopinqSubMaxTotDedctAmt(final Long offenderId, final String caseloadId,
			final String deductionType, final BigDecimal deductionPriority) {
		final String sql = getQuery("OTIOPINQ_SUB_MAX_TOT_DEDCT_AMT");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderId", offenderId, "caseloadId",caseloadId, "deductionType", deductionType, "deductionPriority", deductionPriority),	BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" otiopinqSubMaxTotDedctAmt"+e);
			return null;
		}
	}

	@Override
	public BigDecimal getDedAmtOnDate(final Long offenderDeductionId) {
		final String sql = getQuery("OTIOPINQ_GET_DED_AMT_ON_DATE");
		BigDecimal returnData = null;
		try {
			returnData = namedParameterJdbcTemplate.queryForObject(sql, createParams("offenderDeductionId", offenderDeductionId),BigDecimal.class);
		} catch(Exception e) {
			logger.error(this.getClass().getName()+" otiopinqSubMaxTotDedctAmt"+e);
		}
		return returnData;
	}

	@Override
	public BigDecimal adjustmentAmountC(final Long offenderDeductionId) {
		final String sql = getQuery("OTIOPINQ_ADJUSTMENT_AMOUNT_C");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,createParams("offenderDeductionId", offenderDeductionId), BigDecimal.class);
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" adjustmentAmountC"+e);
			return null;
		}
	}

	@Override
	public Map<String, Object> lvTotalAmount(final Long offenderDeductionId) {
		final String sql = getQuery("OTIOPINQ_LV_TOTAL_AMOUNT");
		try {
			return namedParameterJdbcTemplate.queryForMap(sql,createParams("offenderDeductionId", offenderDeductionId));
		} catch (Exception e) {
			logger.error(this.getClass().getName()+" lvTotalAmount"+e);
			return null;
		}
	}

}
