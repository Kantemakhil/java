package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.cm.programsservices.maintenance.impl.OcmctoffRepositoryImpl;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.VOffenderCourseAttendances;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpssetRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.ProgramPaySettingsBean;

@Repository
public class OcmpssetRepositoryImpl extends RepositoryBase implements OcmpssetRepository {

	/**
	 * Creates new OcmpssetRepositoryImpl class Object
	 */
	public OcmpssetRepositoryImpl() {

	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmctoffRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description"))
			.put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain"))
			.put("canDisplay", new FieldMapper("canDisplay"))
			.build();
	private final Map<String, FieldMapper> prgSrvSetingMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("ACPATTCODEVAL", new FieldMapper("acpAttCodeVal"))
			.put("INSTACTATTCODEVAL", new FieldMapper("instActAttCodeVal"))
			.build();
	private final Map<String, FieldMapper> offcopurseAttMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("pay_system_unit", new FieldMapper("paySystemUnit"))
			.put("pay_system_rate", new FieldMapper("paySystemRate"))
			.put("pay_hours", new FieldMapper("payHours"))
			.put("pay_actual_amount", new FieldMapper("payActualAmount"))
			.build();
	/**
	 * getting rgSearchType LOV values
	 **
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> iepLevelRecordGroup() {
		final String sql = getQuery("OCMPSSET_IEP_RECORDGROP_DATA");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in iepLevelRecordGroup", e);
		}
		return refList;
	}

	/**
	 * getting rgSearchType LOV values
	 **
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> acpOutcomeCodesRecordGroup() {
		final String sql = getQuery("OCMPSSET_ACP_ATTENDENCE_OUTCOME_CODES");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in acpOutcomeCodesRecordGroup", e);
		}
		return refList;
	}

	@Override
	public List<ProgramPaySettingsBean> progServSettingExecuteQuery() {
		final String sql = getQuery("OCMPSSET_PROG_SERV_SETTING_DATA");
		final RowMapper<ProgramPaySettingsBean> prgsrvsetRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ProgramPaySettingsBean.class, prgSrvSetingMapping);
		List<ProgramPaySettingsBean> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams(), prgsrvsetRowMapper);
		} catch (Exception e) {
			logger.error("Exception in progServSettingExecuteQuery ", e);
		}
		return returnObj;
	}

	/**
	 * This method is used to Insert the data base tables based on
	 *
	 * @param listObj List<OffenderProgServSettingsBean>
	 *
	 *                return Integer
	 */
	public Integer offProgServSetingInsert(final List<ProgramPaySettingsBean> listObj) {
		final String sql = getQuery("OCMPSSET_PROG_SERV_SETTING_INSERT_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramPaySettingsBean object : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to Insert the data base tables based on
	 *
	 * @param listObj List<OffenderProgServSettingsBean>
	 *
	 *                return Integer
	 */
	public Integer offProgServSetingUpdate(final List<ProgramPaySettingsBean> listObj) {
		final String sql = getQuery("OCMPSSET_PROG_SERV_SETTING_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final ProgramPaySettingsBean object : listObj) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (listObj.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public Integer getHours() {
		final String sql = getQuery("OCMPSSET_GET_PRGSRV_SETTING_HOURS");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (final Exception e) {
			logger.error("getHours", e);
		}
		return 0;
	}

	@Override
	public String getProgramServicePayFlag() {
		final String sql = getQuery("OCMPSSET_GET_PRGSRV_PAY_FLAG");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), String.class);
		} catch (final EmptyResultDataAccessException e) {
			logger.error("getProgramServicePayFlag", e);
			return "N";
		}
	}

	public List<VOffenderCourseAttendances> getSchedulePayRate(final BigDecimal offenderBookId, final Integer eventId,
			final String modulename) {
		String sql = getQuery("OCMPSSET_GET_PAY_SCHEDULE_DATA");
		if ("ACP".equals(modulename)) {
			  sql = getQuery("OCMPSSET_GET_PAY_SCHEDULE_DATA_ACP");
		}
		List<VOffenderCourseAttendances> returnObj = new ArrayList<>();
		final RowMapper<VOffenderCourseAttendances> ofcrsAttRowMapper = Row2BeanRowMapper.makeMapping(sql,
				VOffenderCourseAttendances.class, offcopurseAttMapping);
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("offenderBookId", offenderBookId, "event_id", eventId), ofcrsAttRowMapper);
		} catch (Exception e) {
			logger.error("Exception in getSchedulePayRate ", e);
		}
		return returnObj;
	}
	
	
	@Override
	public Integer updateSystemPay(final VOffenderCourseAttendances object) {
		final String sql = getQuery("OCMPSSET_UPDATE_SYSTEM_PAY_RATES");
		try {
			return namedParameterJdbcTemplate.update(sql,
					createParams("eventId", object.getEventId(), "paySystemUnit", object.getPaySystemUnit(),
							"paySystemRate", object.getPaySystemRate(), "payHours", object.getPayHours(),
							"payActualAmount", object.getPayActualAmount(),"modifyUserId",object.getModifyUserId()));
		} catch (final Exception e) {
			logger.error("updateSystemPay", e);
		}
		return 0;
	}

	/**
	 * getting rgSearchType LOV values
	 **
	 * @param referenceCodes
	 * @return List<ReferenceCodes>
	 */
	public List<ReferenceCodes> eliteFinancialsRecordGroup() {
		final String sql = getQuery("OCMPSSET_ELITE_FINANCIALS_DATA");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in eliteFinancialsRecordGroup", e);
		}
		return refList;
	}
}
