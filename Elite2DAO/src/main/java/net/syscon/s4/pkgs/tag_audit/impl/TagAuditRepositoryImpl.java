package net.syscon.s4.pkgs.tag_audit.impl;

import java.util.ArrayList;
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
import net.syscon.s4.pkgs.tag_audit.TagAuditRepository;
import net.syscon.s4.sa.audit.maintenance.AllAuditPolicies;

@Repository
public class TagAuditRepositoryImpl extends RepositoryBase implements TagAuditRepository {

	private static Logger logger = LogManager.getLogger(TagAuditRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> allAdtMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("CRS_ACTY_ID", new FieldMapper("crsActyId")).build();

	private final Map<String, FieldMapper> mMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("OBJECT_NAME", new FieldMapper("objectName")).build();

	@Override
	public List<AllAuditPolicies> allAuditPoliciesSelectOperation(final String pPolicyName) {
		final String sql = getQuery("ALL_AUDIT_POLICIES_SELECT_OPERATION");
		final RowMapper<AllAuditPolicies> rowMapper = Row2BeanRowMapper.makeMapping(sql, AllAuditPolicies.class,
				allAdtMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_policy_name", pPolicyName), rowMapper);
	}

	@Override
	public List<AllAuditPolicies> allAuditPolicies(final String pPolicyName) {
		final String sql = getQuery("ALL_AUDIT_POLICIES_SELECT_OPERATION_ONE");

		final RowMapper<AllAuditPolicies> rowMapper = Row2BeanRowMapper.makeMapping(sql, AllAuditPolicies.class,
				allAdtMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_policy_name", pPolicyName), rowMapper);
	}

	@Override
	public List<AllAuditPolicies> dropAllSelectOperation(final String pPolicyName) {
		final String sql = getQuery("ALL_AUDIT_POLICIES_SELECT_OPERATION_TWO");

		final RowMapper<AllAuditPolicies> rowMapper = Row2BeanRowMapper.makeMapping(sql, AllAuditPolicies.class,
				allAdtMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("p_policy_name", pPolicyName), rowMapper);
	}

	@Override
	public List<AllAuditPolicies> getObjNames(final String pOwner) {
		final String sql = getQuery("LIST_TAB");
		final RowMapper<AllAuditPolicies> mRowMapper = Row2BeanRowMapper.makeMapping(sql, AllAuditPolicies.class,
				mMapping);
		return namedParameterJdbcTemplate.query(sql, createParams("P_OWNER", pOwner), mRowMapper);

	}

}