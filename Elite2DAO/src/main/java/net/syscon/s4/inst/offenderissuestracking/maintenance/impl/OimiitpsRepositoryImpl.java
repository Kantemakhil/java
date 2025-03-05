package net.syscon.s4.inst.offenderissuestracking.maintenance.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.GrievanceTypes;
import net.syscon.s4.inst.offenderissuestracking.maintenance.OimiitpsRepository;
import net.syscon.s4.inst.workflow.maintenance.impl.OcmcnperRepositoryImpl;

@Repository
public class OimiitpsRepositoryImpl extends RepositoryBase implements OimiitpsRepository {

	private static Logger logger = LogManager.getLogger(OcmcnperRepositoryImpl.class.getName());

	@Override
	public List<GrievanceTypes> grievencePermissionExecuteQuery(GrievanceTypes searchBean) {
		final String sql = getQuery("OIMIITPS_GRIEVENCE_PERMISSIONS_EXECUTE_QUERRY");
		List<GrievanceTypes> returnList = new ArrayList<GrievanceTypes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("roleId", searchBean.getRoleId()),
					new BeanPropertyRowMapper<GrievanceTypes>(GrievanceTypes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " grievencePermissionExecuteQuery ");
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> grievenceReasonRecordGroup(String grievType) {
		final String sql = getQuery("OIMIITPS_GRIEVENCE_REASON_RECORD_GROUP");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams("grievType", grievType),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " grievenceReasonRecordGroup ");
		}
		return returnList;
	}

	@Override
	public List<ReferenceCodes> grievenceTypeRecordGroup() {
		final String sql = getQuery("OIMIITPS_GRIEVENCE_TYPE_RECORD_GROUP");
		List<ReferenceCodes> returnList = new ArrayList<ReferenceCodes>();
		try {
			returnList = namedParameterJdbcTemplate.query(sql, createParams(),
					new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + " grievenceTypeRecordGroup ");
		}
		return returnList;
	}

	
	@Override
	public Integer grievencePermissionInseting(GrievanceTypes ob) {
		final String sql = getQuery("OIMIITPS_GRIEVENCE_PERMISSIONS_INSERTING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		
			parameters.add(new BeanPropertySqlParameterSource(ob));
		
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method grievencePermissionInseting error", e);
		}
		return returnArray.length;
	}

	@Override
	public Integer grievencePermissionUpdating(GrievanceTypes ob) {
		final String sql = getQuery("OIMIITPS_GRIEVENCE_PERMISSIONS_UPDATING");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
	
			parameters.add(new BeanPropertySqlParameterSource(ob));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error(this.getClass().getName() + "In method grievencePermissionUpdating error", e);
		}
		return returnArray.length;
	}

}
