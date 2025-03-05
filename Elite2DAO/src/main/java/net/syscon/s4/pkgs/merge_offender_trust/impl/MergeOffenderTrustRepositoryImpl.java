package net.syscon.s4.pkgs.merge_offender_trust.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.merge_offender_trust.MergeOffenderTrustRepository;

@Repository
public class MergeOffenderTrustRepositoryImpl extends RepositoryBase implements MergeOffenderTrustRepository  {

	private static Logger logger = LogManager.getLogger(MergeOffenderTrustRepositoryImpl.class.getName());

	@Override
	public Integer checkForTrustAccountCur(Long cpRootOffenderId) {
		final String sql = getQuery("MERGE_OFFENDER_TRUST_CHECK_FOR_TRUST_ACCOUNT");
		Integer count = 0;
		try {
			count = namedParameterJdbcTemplate.queryForObject(sql,
					createParams("cpRootOffenderId", cpRootOffenderId), Integer.class);
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getOffIdDisplayCur", e);
		}
		return count;
	}

}
