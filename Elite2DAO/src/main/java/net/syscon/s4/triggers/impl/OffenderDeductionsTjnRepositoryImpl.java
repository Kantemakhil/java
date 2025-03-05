package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderDeductionsJn;
import net.syscon.s4.triggers.OffenderDeductionsTjnRepository;

@Repository
public class OffenderDeductionsTjnRepositoryImpl extends RepositoryBase implements OffenderDeductionsTjnRepository{
	
	private static final Logger logger = LogManager.getLogger(OffenderDeductionsTjnRepositoryImpl.class);

	@Override
	public Integer insertOffenderDeductionsJn(OffenderDeductionsJn searchBean) {
		try{
			final String sql = getQuery("OFFENDER_DEDUCTIONS_TJN_INSERT_OFFENDER_DEDUCTIONS_JN");
			return namedParameterJdbcTemplate.update(sql,new BeanPropertySqlParameterSource(searchBean));	
		}catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
