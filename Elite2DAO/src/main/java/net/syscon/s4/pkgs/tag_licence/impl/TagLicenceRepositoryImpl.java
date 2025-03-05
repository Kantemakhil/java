package net.syscon.s4.pkgs.tag_licence.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.pkgs.tag_licence.TagLicenceRepository;

@Repository
public class TagLicenceRepositoryImpl extends RepositoryBase implements TagLicenceRepository {

	private static Logger logger = LogManager.getLogger(TagLicenceRepositoryImpl.class.getName());

	@Override
	public String getRequirement(final String pCommConditionCode, final String pCommConditionType,
			final String pCategoryType) {
		final String sql = getQuery("GET_REQUIREMENT");
		String desc = null;
		try {
			desc = namedParameterJdbcTemplate.queryForObject(sql, createParams("P_COMM_CONDITION_CODE",
					pCommConditionCode, "P_COMM_CONDITION_TYPE", pCommConditionType, "P_CATEGORY_TYPE", pCategoryType),
					String.class);
		} catch (Exception e) {
			logger.error("getRequirement :" + e);
		}
		return desc;
	}

}