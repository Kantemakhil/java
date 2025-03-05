package net.syscon.s4.inst.casemanagement.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.casemanagement.OcdiplacRepository;
import net.syscon.s4.inst.casemanagement.beans.CasePlanStaff;

@Repository
public class OcdiplacRepositoryImpl extends RepositoryBase implements OcdiplacRepository {

	private static Logger logger = LogManager.getLogger(OcdiplacRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> referenceMapper = new ImmutableMap.Builder<String, FieldMapper>()
			.put("userId", new FieldMapper("createUserId"))
			.put("SUSPENDED_FLAG", new FieldMapper("activeFlag"))
			.put("code", new FieldMapper("code"))
			.put("FIRST_NAME", new FieldMapper("firstName"))
			.put("EXPIRED_DATE", new FieldMapper("expiredDate")).			
			build();

	private final Map<String, FieldMapper> casePlanStaffMember = new ImmutableMap.Builder<String, FieldMapper>()
			.put("staffName", new FieldMapper("staffId")).build();

	@Override
	public List<ReferenceCodes> staffMemebersListByAgyLocId(String agyLocId) {
		String sql = getQuery("OCDIPLANPOP_STAFF_MEMBER_LIST_BY_AGYLOCID");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		final RowMapper<ReferenceCodes> rowMapper = Row2BeanRowMapper.makeMapping(sql, ReferenceCodes.class,
				referenceMapper);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " staffMemebersListByAgyLocId " + e);
		}

		return returnList;
	}

	@Override
	public List<CasePlanStaff> getAllStaffMembersList(Integer offenderBookId, Integer casePlanId) {
		List<CasePlanStaff> returnList = new ArrayList<CasePlanStaff>();
		String sql = getQuery("OCDIPLANPOP_GET_ALL_CASE_PLAN_STAFF_ROLES");
		final RowMapper<CasePlanStaff> rowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlanStaff.class,
				casePlanStaffMember);
		try {
			returnList = namedParameterJdbcTemplate.query(sql,
					createParams("offender_book_id", offenderBookId, "case_plan_id", casePlanId), rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " getAllStaffMembersList " + e);

		}

		return returnList;
	}

	@Override
	public Integer insertCasePlanStaffMemberDetails(List<CasePlanStaff> insertList) {

		String sql = getQuery("OCDIPLANPOP_INSERT_CASE_PLAN_STAFF_ROLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlanStaff casePlanStaffDetails : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(casePlanStaffDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertCasePlanStaffMemberDetails " + e);

		}
		return (insertList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public Integer updateCasePlanStaffMemberDetails(List<CasePlanStaff> updateList) {
		String sql = getQuery("OCDIPLANPOP_UPDATE_CASE_PLAN_STAFF_ROLES");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final CasePlanStaff casePlanStaffDetails : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(casePlanStaffDetails));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " insertCasePlanStaffMemberDetails " + e);

		}
		return (updateList.size() == returnArray.length) ? 1 : 0;
	}

	@Override
	public List<CasePlanStaff> childDataCarry(Integer offenderBookId) {
		List<CasePlanStaff> returnList = new ArrayList<CasePlanStaff>();
		String sql = getQuery("OCDIPLANPOP_CHILD_DATA_CARRYING");
		final RowMapper<CasePlanStaff> rowMapper = Row2BeanRowMapper.makeMapping(sql, CasePlanStaff.class,
				casePlanStaffMember);
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("offenderBookId", offenderBookId),
					rowMapper);
		} catch (Exception e) {
			returnList = Collections.emptyList();
			logger.error(this.getClass().getName() + " childDataCarry " + e);

		}

		return returnList;
	}

}
