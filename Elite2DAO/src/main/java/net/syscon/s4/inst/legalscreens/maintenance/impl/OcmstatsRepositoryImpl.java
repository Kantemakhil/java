package net.syscon.s4.inst.legalscreens.maintenance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.LegalUpdateUsages;
import net.syscon.s4.inst.legalscreens.maintenance.OcmstatsRepository;
import net.syscon.s4.inst.legalscreens.maintenance.bean.LegalUpdateReasons;

@Repository
public class OcmstatsRepositoryImpl extends RepositoryBase implements OcmstatsRepository {

	private static Logger logger = LogManager.getLogger(OcmstatsRepositoryImpl.class.getName());

	@Override
	public Integer saveOrdersData(List<LegalUpdateReasons> reasons) {
		final String sql = getQuery("OCMSTATS_INSERT_ORDERS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LegalUpdateReasons offenderExternalMovements : reasons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			getLogMessage("saveOrdersData", e);
			if (e.getMessage() != null && e.getMessage().contains("legal_update_reasons_pk")) {
				return 18;
			}
		}
		if (returnArray!=null && returnArray.length > 0) {
			List<ReferenceCodes>  domainData=getDomainData();
			List<LegalUpdateUsages> usagesList = Collections.checkedList(new ArrayList<LegalUpdateUsages>(),LegalUpdateUsages.class);
			for (LegalUpdateReasons res : reasons) {
				for (ReferenceCodes codes : domainData) {
					LegalUpdateUsages usages=new LegalUpdateUsages();
					usages.setUpdateReasonCode(res.getUpdateReasonCode());
					usages.setCreateUserId(res.getCreateUserId());
					usages.setLegalClass(codes.getCode());
					usages.setActiveFlag(codes.getActiveFlag());
					usagesList.add(usages);
				}
			}
			saveStautesData(usagesList);
		}
		return returnArray.length > 0 ? 1 : 0;
	}

	@Override
	public Integer saveStautesData(List<LegalUpdateUsages> reasons) {
		final String sql = getQuery("OCMSTATS_INSERT_STATUES_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LegalUpdateUsages offenderExternalMovements : reasons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			getLogMessage("saveStautesData", e);
		}
		return returnArray.length > 0 ? 1 : 0;
	}

	@Override
	public List<LegalUpdateReasons> getOrdersData() {
		final String sql = getQuery("OCMSTATS_GET_ORDERS_DATA");
		List<LegalUpdateReasons> ordersData = Collections.checkedList(new ArrayList<LegalUpdateReasons>(),
				LegalUpdateReasons.class);
		try {
			ordersData = namedParameterJdbcTemplate.query(sql, createParams(),
					new RowMapperResultSetExtractor<LegalUpdateReasons>(
							new BeanPropertyRowMapper<LegalUpdateReasons>(LegalUpdateReasons.class)));
		} catch (Exception e) {
			getLogMessage("getOrdersData", e);
		}
		return ordersData;
	}

	@Override
	public List<LegalUpdateUsages> getStatuesData(String updateReasonCode) {
		final String sql = getQuery("OCMSTATS_GET_STATUS_DATA");
		List<LegalUpdateUsages> statuesData = Collections.checkedList(new ArrayList<LegalUpdateUsages>(),
				LegalUpdateUsages.class);
		try {
			statuesData = namedParameterJdbcTemplate.query(sql, createParams("updateReasonCode", updateReasonCode),
					new RowMapperResultSetExtractor<LegalUpdateUsages>(
							new BeanPropertyRowMapper<LegalUpdateUsages>(LegalUpdateUsages.class)));
		} catch (Exception e) {
			getLogMessage("getStatuesData", e);
		}
		return statuesData;
	}

	@Override
	public Integer updateOrdersData(List<LegalUpdateReasons> reasons) {
		final String sql = getQuery("UPDATE_ORDERS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LegalUpdateReasons offenderExternalMovements : reasons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			getLogMessage("updateOrdersData", e);
		}
		return returnArray.length > 0 ? 1 : 0;
	}

	@Override
	public Integer updateStautesData(List<LegalUpdateUsages> reasons) {
		final String sql = getQuery("UPDATE_STATUS_DATA");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (LegalUpdateUsages offenderExternalMovements : reasons) {
			parameters.add(new BeanPropertySqlParameterSource(offenderExternalMovements));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			getLogMessage("updateStautesData", e);
		}
		return returnArray.length > 0 ? 1 : 0;
	}

	private void getLogMessage(String methodName, Exception e) {
		logger.error("Method in " + this.getClass().getName() + " " + methodName, e);
	}

	private List<ReferenceCodes> getDomainData() {
		final String sql = getQuery("GET_DOMAIN_DATA");
		List<ReferenceCodes> domainData = Collections.checkedList(new ArrayList<ReferenceCodes>(),ReferenceCodes.class);
		try {
			domainData = namedParameterJdbcTemplate.query(sql, createParams(),new RowMapperResultSetExtractor<ReferenceCodes>(new BeanPropertyRowMapper<ReferenceCodes>(ReferenceCodes.class)));
		} catch (Exception e) {
			getLogMessage("getDomainData", e);
		}
		return domainData;
	}
}
