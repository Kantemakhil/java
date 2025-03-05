package net.syscon.s4.pkgs.ocmsvacp.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
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
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.im.beans.CourseActivities;
import net.syscon.s4.im.beans.VAddresses;
import net.syscon.s4.pkgs.ocmsvacp.OcmsvacpPkgRepository;
import net.syscon.s4.triggers.VCourseModules;
import net.syscon.s4.triggers.VProgramModules;

@Repository
public class OcmsvacpPkgRepositoryImpl extends RepositoryBase implements OcmsvacpPkgRepository {

	private static Logger logger = LogManager.getLogger(OcmsvacpPkgRepositoryImpl.class.getName());
	
	private final Map<String, FieldMapper> vProgramModulesMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.build();

	@Override
	public String getAccProgram(final BigDecimal programId) {
		final String sql = getQuery("GET_PROG_NAME");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROGRAM_ID", programId), String.class);
	}

	@Override
	public VAddresses ocmsvacpDefaultAddrWAgy(final String providerCode) {
		final String sql = getQuery("OCMSVACP_DEFAULT_ADDR_W_AGY");
		VAddresses returnList = new VAddresses();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROVIDER_CODE", providerCode),
					VAddresses.class);
		} catch (Exception e) {
			logger.error("ocmsvacpDefaultAddrWAgy", e);
		}
		return returnList;

	}

	@Override
	public VAddresses ocmsvacpDefaultAddrWCorp(final Long providerId) {
		final String sql = getQuery("OCMSVACP_DEFAULT_ADDR_W_CORP");
		VAddresses returnList = new VAddresses();
		try {
			returnList = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_PROVIDER_ID", providerId),
					VAddresses.class);
		} catch (Exception e) {
			logger.error("ocmsvacpDefaultAddrWCorp", e);
		}
		return returnList;
	}

	@Override
	public Long ocmsvacpGetCodeUniqueCntCur(final Long providerPartyId, final String providerPartyCode,
			final String pCode, final String programCategory) {
		final String sql = getQuery("OCMSVACP_GET_CODE_UNIQUE_CNT_CUR");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("P_TEAM_ID", providerPartyId, "P_TEAM_CODE",
				providerPartyCode, "P_CODE", pCode, "P_CATEGORY", programCategory), Long.class);
	}

	@Override
	public Integer createCourseModules(final VCourseModules vCourseModules) {
		final String sql = getQuery("V_COURSE_MODULES_INSERT");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(vCourseModules));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("createCourseModules", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public List<VProgramModules> getVprogramModulesCrsMod(Long programPhaseId) {
		List<VProgramModules> returnList = new ArrayList<VProgramModules>();
		final String sql = getQuery("OCMSVACP_PKG_V_PROGRAM_MODULES");
		final RowMapper<VProgramModules> vProgramModulesMapper = Row2BeanRowMapper.makeMapping(sql,
				VProgramModules.class, vProgramModulesMapping);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("programPhaseId", programPhaseId),
					vProgramModulesMapper);
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
		return returnList;
	}

}