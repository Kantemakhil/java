package net.syscon.s4.pkgs.oms_intake.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oms_intake.OmsIntakeRepository;
@Repository
public class OmsIntakeRepositoryImpl extends RepositoryBase implements OmsIntakeRepository {

	private static Logger logger = LogManager.getLogger(OmsIntakeRepositoryImpl.class.getName());

	@Override
	public String refCur(final String pSupLevel, final String pDomain) {
		final String sql = getQuery("OMS_INTAKE_REF_CUR");
		String result = null;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, createParams("lvDomain",pDomain,"pSupLevel",pSupLevel), String.class);
		} catch (Exception e) {
			logger.error("refCur" + e);
		}
		return result;
	}

//	@Override
//	public SystemProfiles sysCur() {
//		final String sql = getQuery("OMS_INTAKE_SYS_CUR");
//		SystemProfiles bean = new SystemProfiles();
//		try {
//			bean = namedParameterJdbcTemplate.queryForObject(sql, createParams(),
//					new BeanPropertyRowMapper<SystemProfiles>(SystemProfiles.class));
//		} catch (Exception e) {
//			logger.error("sysCur" + e);
//		}
//		return bean;
//	}

}
