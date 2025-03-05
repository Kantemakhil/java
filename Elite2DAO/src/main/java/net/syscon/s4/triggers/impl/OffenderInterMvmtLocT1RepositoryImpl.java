package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.inst.movements.beans.OffenderInterMvmtLocations;
import net.syscon.s4.triggers.OffenderInterMvmtLocT1Repository;

@Repository
public class OffenderInterMvmtLocT1RepositoryImpl extends RepositoryBase implements OffenderInterMvmtLocT1Repository {
	Logger logger = LogManager.getLogger(OffenderInterMvmtLocT1RepositoryImpl.class.getName());

	@Override
	public Integer update(final List<OffenderInterMvmtLocations> offenderInterMvmtLocationsList) {
		Integer returnValue = 0;
		final String sql = getQuery("OFFENDER_INTER_MVMT_LOC_T1_UPDATE");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final OffenderInterMvmtLocations offenderCaseNotes : offenderInterMvmtLocationsList) {
			parameters.add(new BeanPropertySqlParameterSource(offenderCaseNotes));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (offenderInterMvmtLocationsList.size() == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("update ", e);
		}
		return returnValue;

	}

}
