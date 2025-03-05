package net.syscon.s4.inst.visitsmanagement.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jersey.repackaged.com.google.common.collect.ImmutableMap;
import net.syscon.s4.common.beans.StaffMembers;
import net.syscon.s4.common.impl.FieldMapper;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.inst.visitsmanagement.OidieplvRepository;
import net.syscon.s4.inst.visitsmanagement.beans.IepLevelBean;
@Repository
public class OidieplvRepositoryImpl extends RepositoryBase implements OidieplvRepository {
	
	private static Logger logger = LogManager.getLogger(OidieplvRepositoryImpl.class.getName());

	private final Map<String, FieldMapper> visitTypeLimitsMapping = new ImmutableMap.Builder<String, FieldMapper>()
			.put("iepLeveldescription", new FieldMapper("iepLeveldescription"))
			.put("approvedStaff", new FieldMapper("approvedStaff"))
			.put("assigned_date", new FieldMapper("dateAsigned"))
			.put("approved_staff_id", new FieldMapper("staffId"))
			.put("iep_level_seq", new FieldMapper("sequence"))
			.build();

	@Override
	public List<IepLevelBean> getIEPLov(){
		List<IepLevelBean> lovList=new ArrayList<>();
		String sql=getQuery("OIDIEPLV_GET_LOVS");
		RowMapper<IepLevelBean> mapper= Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class, visitTypeLimitsMapping);
		try {
			lovList=namedParameterJdbcTemplate.query(sql, createParams(),mapper);
		}catch (Exception e) {
			logger.error("getIEPLov", e);
		}
		return lovList;
	}
	@Override
	public List<IepLevelBean> getAllData(Integer offenderBookId){
		List<IepLevelBean> iepListData=new ArrayList<>();
		String sql=getQuery("OIDIEPLV_GET_ALL_DATA");
		RowMapper<IepLevelBean> mapper= Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class, visitTypeLimitsMapping);
		try {
			iepListData=namedParameterJdbcTemplate.query(sql, createParams("offenderBookId",offenderBookId),mapper);
		}catch (Exception e) {
			logger.error("getIEPLov", e);
		}
		return iepListData;

	}
	
	@Override
	public Integer saveAllData(List<IepLevelBean> commitBean) {
		String sql=getQuery("OIDIEPLV_INSERT_DATA");
		int [] returnArray=new int[] {};
		List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();

		for (final IepLevelBean ocn : commitBean) {
			parameters.add(new BeanPropertySqlParameterSource(ocn));
		}

		try {
			returnArray= namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error("saveAllData", e);
		}
		if (commitBean.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public StaffMembers lvLoginUserStaffName(String userName) {
		StaffMembers staffName = new StaffMembers();
		final String sql = getQuery("OIDIEPLV_GET_STAFF_DETAILS");
		final RowMapper<StaffMembers> rowMapper = Row2BeanRowMapper.makeMapping(sql, StaffMembers.class,
				visitTypeLimitsMapping);
		try {
			staffName = namedParameterJdbcTemplate.queryForObject(sql, createParams("USER_NAME",userName), rowMapper);
		} catch (Exception e) {
			logger.error("lvLoginUserStaffId", e);
		}
		return staffName;
	}
	
	@Override
	public Integer updateComment(IepLevelBean bean) {
		final String sql = getQuery("OIDIEPLV_UPDATE_COMMENT");
		Integer retVal = 0;
		try {
			retVal = namedParameterJdbcTemplate.update(sql,
					createParams("sequence", bean.getSequence(), "offenderBookId", bean.getOffenderBookId(),
							"reviewComment", bean.getReviewComment(), "modifyUserId", bean.getModifyUserId() ,"nextReviewDate" , bean.getNextReviewDate()));
		} catch (Exception e) {
			logger.error("updateComment", e);
		}
		return retVal;

	}

	@Override
	public List<IepLevelBean> getReviewDaysForIepLevelCode() {
		List<IepLevelBean> list = new ArrayList<>();
		String sql = getQuery("GET_REVIEWCODE_FOR_IEPLEVELCODE");
		RowMapper<IepLevelBean> mapper = Row2BeanRowMapper.makeMapping(sql, IepLevelBean.class, visitTypeLimitsMapping);
		try {
			list = namedParameterJdbcTemplate.query(sql, createParams(), mapper);
		} catch (Exception e) {
			logger.error("getReviewDaysForIepLevelCode", e);
		}
		return list;
	}

	@Override
	public Integer getSystemGeneratedStaffId() {
		String sql = getQuery("GET_SYSTEM_GENERATED_STAFF_ID_USER_NAME");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Integer.class);
		} catch (Exception e) {
			logger.error("getSystemGeneratedStaffId", e);
			return 0;
		}
	}
	
	
	@Override
	public Long getSystemStaffId() {
		String sql = getQuery("OIDIEPLV_GET_SYSTEM_STAFF_ID");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql, createParams(), Long.class);
		} catch (Exception e) {
			logger.error("getSystemGeneratedStaffId", e);
			return null;
		}
	}
	
	public Date getNextReviewdate(String description) {
		String sql = getQuery("OIDADMIS_GET_REVIEW_DAYS");
		return namedParameterJdbcTemplate.queryForObject(sql, createParams("iepLevelCode", description), Date.class);
	}
	
}
