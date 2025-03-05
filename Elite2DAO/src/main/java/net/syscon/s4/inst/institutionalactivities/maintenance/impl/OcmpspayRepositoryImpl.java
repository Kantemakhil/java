package net.syscon.s4.inst.institutionalactivities.maintenance.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import net.syscon.s4.inst.casemanagement.beans.ProgramServices;
import net.syscon.s4.inst.institutionalactivities.maintenance.OcmpspayRepository;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayBean;
import net.syscon.s4.inst.institutionalactivities.maintenance.beans.programsPayCompensationBean;

@Repository
public class OcmpspayRepositoryImpl extends RepositoryBase implements OcmpspayRepository {

	/**
	 * Creates new OcmpspayRepositoryImpl class Object
	 */
	public OcmpspayRepositoryImpl() {

	}

	/**
	 * Logger object used to print the log in the file
	 */
	private static Logger logger = LogManager.getLogger(OcmctoffRepositoryImpl.class);
	private final Map<String, FieldMapper> referenceCodeMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("DESCRIPTION", new FieldMapper("description")).put("CODE", new FieldMapper("code"))
			.put("DOMAIN", new FieldMapper("domain")).put("canDisplay", new FieldMapper("canDisplay")).build();
	private final Map<String, FieldMapper> categoryMapping = new ImmutableMap.Builder<String, FieldMapper>().build();
	private final Map<String, FieldMapper> compensationMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("PROGRAM_ID", new FieldMapper("programId")).build();
	private final Map<String, FieldMapper> prgSrvNMapping = new ImmutableMap.Builder<String, FieldMapper>().build();

	@Override
	public List<ReferenceCodes> rgCompensationCodeRecorGroup(Integer programId) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_CODE_RECORDGROUP");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("PROGRAM_ID", programId),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in rgCompensationCodeRecorGroup", e);
		}
		return refList;
	}

	@Override
	public List<ReferenceCodes> rgCompensationTypeRecorGroup(String programCategory) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_TYPE_RECORDGROUP_QUERY");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams("program_category", programCategory),
					referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in rgCompensationTypeRecorGroup", e);
		}
		return refList;
	}

	@Override
	public List<programsPayBean> prgCategoryExecuteQuery() {
		final String sql = getQuery("OCMPSPAY_CATEGORY_EXECUTEQUERY");
		final RowMapper<programsPayBean> compensationRowMapper = Row2BeanRowMapper.makeMapping(sql,
				programsPayBean.class, categoryMapping);
		List<programsPayBean> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql, createParams(), compensationRowMapper);
		} catch (Exception e) {
			logger.error("Exception in prgCategoryExecuteQuery ", e);
		}
		return returnObj;
	}

	@Override
	public Integer categoryInsert(List<programsPayBean> insertList) {
		final String sql = getQuery("OCMPSPAY_CATEGORY_INSERT_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final programsPayBean object : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer categoryUpdate(List<programsPayBean> updateList) {
		final String sql = getQuery("OCMPSPAY_CATEGORY_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final programsPayBean object : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<programsPayCompensationBean> prgCampensationExecuteQuery(programsPayBean beanObj) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_EXECUTEQUERY");
		final RowMapper<programsPayCompensationBean> compensationRowMapper = Row2BeanRowMapper.makeMapping(sql,
				programsPayCompensationBean.class, compensationMapping);
		List<programsPayCompensationBean> returnObj = new ArrayList<>();
		try {
			returnObj = namedParameterJdbcTemplate.query(sql,
					createParams("PROGRAM_CATEGORY", beanObj.getProgramCategory()), compensationRowMapper);
		} catch (Exception e) {
			logger.error("Exception in prgCampensationExecuteQuery ", e);
		}
		return returnObj;
	}

	@Override
	public Integer campensationInsert(List<programsPayCompensationBean> insertList) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_INSERT_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final programsPayCompensationBean object : insertList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (insertList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer campensationUpdate(List<programsPayCompensationBean> updateList) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_UPDATE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final programsPayCompensationBean object : updateList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (updateList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer campensationDelete(List<programsPayCompensationBean> deleteList) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_DELETE_QUERY");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final programsPayCompensationBean object : deleteList) {
			parameters.add(new BeanPropertySqlParameterSource(object));
		}
		try {
			String tableName = "programs_pay_compensation";
			String whereClause = "program_category=:programCategory and program_id =:programId and coalesce(crs_acty_id,0) =:crsActyId";
			batchUpdatePreDeletedRows(tableName, whereClause , parameters);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method campensationDelete", e);
		}
		returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		if (deleteList.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<ProgramServices> listOfProgServices() {
		final String sql = getQuery("OCMPSPAY_PROGRAM_SERVICES_QUERY");
		final RowMapper<ProgramServices> prgSrvRowMapper = Row2BeanRowMapper.makeMapping(sql, ProgramServices.class,
				prgSrvNMapping);
		List<ProgramServices> refList = new ArrayList<>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams(), prgSrvRowMapper);
		} catch (Exception e) {
			logger.error("Exception in listOfProgServices", e);
		}
		return refList;
	}

	@Override
	public Integer compensatiponPreInsert(List<String> programCategory, List<Integer> programId,
			List<Integer> crsActyId) {
		final String sql = getQuery("OCMPSPAY_COMPENSATION_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql,
				createParams("PROGRAM_CATEGORY", programCategory, "program_id", programId, "crsActyId", crsActyId),
				Integer.class);
	}

	@Override
	public Integer categoryPreInsert(List<String> programCategory) {
		final String sql = getQuery("OCMPSPAY_CATEGORY_PRE_INSERT");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("PROGRAM_CATEGORY", programCategory),
				Integer.class);
	}

	@Override
	public List<ReferenceCodes> rgUnitRecordGroup() {
		final String sql = getQuery("OCMPSPAY_UNIT_RECORDGROUP_QUERY");
		final RowMapper<ReferenceCodes> referenceCodeRowMapper = Row2BeanRowMapper.makeMapping(sql,
				ReferenceCodes.class, referenceCodeMapping);
		List<ReferenceCodes> refList = new ArrayList<ReferenceCodes>();
		try {
			refList = namedParameterJdbcTemplate.query(sql, createParams(), referenceCodeRowMapper);
		} catch (Exception e) {
			logger.error("Exception in rgUnitRecordGroup", e);
		}
		return refList;
	}

}
