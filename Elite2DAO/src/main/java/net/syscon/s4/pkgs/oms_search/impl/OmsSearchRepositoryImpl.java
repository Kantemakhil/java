package net.syscon.s4.pkgs.oms_search.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.oms_search.OmsSearchRepository;
import net.syscon.s4.pkgs.tag_programmes.impl.TagProgrammesRepositoryImpl;

@Repository
public class OmsSearchRepositoryImpl extends RepositoryBase implements OmsSearchRepository {

	private static Logger logger = LogManager.getLogger(TagProgrammesRepositoryImpl.class.getName());

	@Override
	public Integer checkOffenderIdDisplay(final String offIdDisplay) {
		final String sql = getQuery("OMS_SEARCH_CHECK_OFFENDER_ID_DISPLAY");
		Integer retVal = null;
		try {
			retVal = namedParameterJdbcTemplate.queryForObject(sql, createParams("p_offender_id_display", offIdDisplay),
					Integer.class);
		} catch (Exception e) {
			logger.error("checkOffenderIdDisplay :" + e);
			retVal = null;
		}
		return retVal;
	}

}