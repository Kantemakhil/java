package net.syscon.s4.triggers.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.triggers.OffenderEmploymentsT1Repository;

@Repository
public class OffenderEmploymentsT1RepositoryImpl extends RepositoryBase implements OffenderEmploymentsT1Repository {
	private static Logger log = LogManager.getLogger(OffenderEmploymentsT1RepositoryImpl.class.getName());
	@Override
	public Integer updateReleasePlans(Long offenderBookId) {

		String sql = getQuery("UPDATE_RELEASE_PLANS");
		try {			
			return namedParameterJdbcTemplate.update(sql, createParams("offenderBookId", offenderBookId));
		} catch (Exception e) {
			log.error("updateReleasePlans" +e);
			return 0;
		}
	}

}
