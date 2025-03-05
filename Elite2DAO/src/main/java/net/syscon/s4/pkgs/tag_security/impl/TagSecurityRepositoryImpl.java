package net.syscon.s4.pkgs.tag_security.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.tag_security.TagSecurityRepository;

@Repository
public class TagSecurityRepositoryImpl extends RepositoryBase implements TagSecurityRepository {

	final private static Logger logger = LogManager.getLogger(TagSecurityRepositoryImpl.class.getName());
	private final Map<String, FieldMapper> courtCasesMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	@Override
	public String getGroupPrivilege(final String moduleName) {
		final String sql = getQuery("INTERNAL_LOCATION_ID");
		String accessPrivi = null;
		try {
			accessPrivi = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_module_name", moduleName),
					String.class);
		} catch (Exception e) {
			logger.error("getInternalLocationId :" + e);
		}
		return accessPrivi;
	}

	@Override
	public String getCaseloadId(final String user) {
		final String sql = getQuery("GET_CASE_LOAD_ID");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("userName", user), String.class);
	}

	@Override
	public Boolean checkPrivilegeExists(final String roleCode, final String userName) {
		final String sql = getQuery("CHECK_PRIVILEGE_EXISTS");
		List<Integer> count = null;
		try {
			final RowMapper<Integer> courtCasesRowMapper = Row2BeanRowMapper.makeMapping(sql,Integer.class, courtCasesMapping);
			count = namedParameterJdbcTemplate.query(sql,
					createParams("p_role_code", roleCode, "user_name", userName),courtCasesRowMapper);
		} catch (Exception e) {
			logger.error("getInternalLocationId :" + e);
			return false;
		}
		if (count!=null && count.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getGroupPrivilege(final String moduleName, final String userName) {
		final String sql = getQuery("GET_GROUP_PRIVILEGE");
		String returnVal = null;
		try {
			returnVal = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("p_module_name", moduleName, "user_name", userName), String.class);
		} catch (Exception e) {
			logger.error("getGroupPrivilege :" + e);
		}
		return returnVal;
	}

	@Override
	public String checkPrivilege(final String roleCode, final String userName) {
		final String sql = getQuery("TAG_SECURITY_CHECK_PRIVILEGE");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("p_role_name", roleCode, "user_name", userName), String.class);
	}
}