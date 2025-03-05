package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.cf.deductions.beans.FeeAccountProfiles;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffFeeAccountProfileT1AndT2Repository;
@Repository
public class OffFeeAccountProfileT1AndT2RepositoryImpl extends RepositoryBase implements OffFeeAccountProfileT1AndT2Repository {
	
	
private static Logger logger = LogManager.getLogger(OffFeeAccountProfileT1AndT2RepositoryImpl.class.getName());

	
	public Integer insertAccountProfiles(FeeAccountProfiles profiles) {
		List<SqlParameterSource> parameters = new ArrayList<>();
		int[] returnValue = null;
		try {
			parameters.add(new BeanPropertySqlParameterSource(profiles));
			returnValue=namedParameterJdbcTemplate.batchUpdate(profiles.getCommentText()!=null?getQuery("OFF_FEE_ACCOUNT_PROFILE_HTY_FIRST"):getQuery("OFF_FEE_ACCOUNT_PROFILE_HTY_SECOND"),parameters.toArray(new SqlParameterSource[0]));
		}catch (Exception e) {
			logger.error(this.getClass().getName()+"Error in insertAccountProfiles", e);
		}
		if (returnValue.length > 0) {
			return 1;
		} else {
			return 0;
		}
	}

}
