package net.syscon.s4.pkgs.tag_qm_pai.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_qm_pai.TagQmPaiRepository;
import net.syscon.s4.triggers.QmActivitiesIns;

@Repository
public class TagQmPaiRepositoryImpl extends RepositoryBase implements TagQmPaiRepository {
	private final Logger logger = LogManager.getLogger(TagQmPaiRepositoryImpl.class);

	@Override
	public Integer prUpd(QmActivitiesIns amActivitiesIns) {
		Integer returnValue = 0;
		final String sql = getQuery("QM_ACTIVITIES_INS_PRUPD");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		parameters.add(new BeanPropertySqlParameterSource(amActivitiesIns));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
			if (1 == returnArray.length) {
				returnValue = 1;
			}
		} catch (final Exception e) {
			returnValue = 0;
			logger.error("prUpd ", e);
		}
		return returnValue;

	}

}
