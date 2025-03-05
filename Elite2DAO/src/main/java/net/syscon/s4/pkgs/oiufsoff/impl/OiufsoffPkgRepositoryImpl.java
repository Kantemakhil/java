package net.syscon.s4.pkgs.oiufsoff.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.OiufsoffGetGeneralOffenders;
import net.syscon.s4.common.beans.VHeaderBlock;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.oiufsoff.OiufsoffPkgRepository;

@Repository
public class OiufsoffPkgRepositoryImpl extends RepositoryBase implements OiufsoffPkgRepository {

	private static final Logger logger = LogManager.getLogger(OiufsoffPkgRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> vMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).build();

	@Override
	public String getCaseloadType(final String caseLodId) {
		final String sql = getQuery("GET_CASELOAD_TYPE");
		String caseLoadId = null;
		try {
			caseLoadId = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_caseload_id", caseLodId),
					String.class);
		} catch (Exception e) {
			logger.error("getCaseloadType :" + e);
		}
		return caseLoadId;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getInstitutionOffenders(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_INSTITUTION_OFFENDERS");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_living_unit_id",
							bean.getLvLivingUnitId(), "lv_caseload_type", bean.getLvcaseLoadType(), "lv_active_flag",
							bean.getLvActiveFlag(),"USERID",bean.getCreateUserId(),"p_caseload_id", bean.getpCaseloadId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getInstitutionOffenders :" + e);
			list = null;
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getCommunityOffenders(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_COMMUNITY_OFFENDERS");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_living_unit_id",
							bean.getLvLivingUnitId(), "lv_caseload_type", bean.getLvcaseLoadType(), "lv_active_flag",
							bean.getLvActiveFlag(), "p_caseload_id", bean.getpCaseloadId(),"USERID",bean.getCreateUserId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getCommunityOffenders :" + e);
			list = null;
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getTrustOffenders(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_TRUST_OFFENDERS");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_caseload_type",
							bean.getLvcaseLoadType(), "lv_active_flag", bean.getLvActiveFlag(), "p_caseload_id",
							bean.getpCaseloadId(),"lv_living_unit_id",bean.getLvLivingUnitId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getTrustOffenders :" + e);
			list = null;
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getPayrollOffenders(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_PAYROLL_OFFENDERS");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_living_unit_id",
							bean.getLvLivingUnitId(), "lv_caseload_type", bean.getLvcaseLoadType(), "lv_active_flag",
							bean.getLvActiveFlag(), "p_caseload_id", bean.getpCaseloadId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getTrustOffenders :" + e);
			list = null;
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getPayrollOffendersOne(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_PAYROLL_OFFENDERS_ONE");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_living_unit_id",
							bean.getLvLivingUnitId(), "lv_caseload_type", bean.getLvcaseLoadType(), "lv_active_flag",
							bean.getLvActiveFlag(), "p_caseload_id", bean.getpCaseloadId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getPayrollOffendersOne :" + e);
			list = null;
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getPayrollOffendersTwo(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_PAYROLL_OFFENDERS_TWO");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql,
				OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_living_unit_id",
							bean.getLvLivingUnitId(), "lv_caseload_type", bean.getLvcaseLoadType(), "lv_active_flag",
							bean.getLvActiveFlag(), "p_caseload_id", bean.getpCaseloadId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getPayrollOffendersTwo :" + e);
			list = null;
		}
		return list;
	}

	@Override
	public List<OiufsoffGetGeneralOffenders> getCommissaryOffenders(final OiufsoffGetGeneralOffenders bean) {
		final String sql = getQuery("GET_COMMISSARY_OFFENDERS");
		List<OiufsoffGetGeneralOffenders> list = new ArrayList<OiufsoffGetGeneralOffenders>();
		final RowMapper<OiufsoffGetGeneralOffenders> mRowMapper = Row2BeanRowMapper.makeMapping(sql, OiufsoffGetGeneralOffenders.class, vMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql,
					createParams("p_last_name", bean.getpLastName(), "p_first_name", bean.getpFirstName(),
							"p_middle_name", bean.getpMiddleName(), "p_offender_id_display",
							bean.getpOffenderIdDisplay(), "p_agy_loc_id", bean.getpAgyLocId(), "lv_caseload_type",
							bean.getLvcaseLoadType(), "lv_active_flag", bean.getLvActiveFlag(), "p_caseload_id",
							bean.getpCaseloadId()),
					mRowMapper);
		} catch (Exception e) {
			logger.error("getCommissaryOffenders :" + e);
			list = null;
		}
		return list;
	}

}