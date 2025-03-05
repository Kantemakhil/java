package net.syscon.s4.triggers.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.triggers.OmtoffirRepository;

@Repository
public class OmtoffirRepositoryImpl extends RepositoryBase implements OmtoffirRepository {
	private final Logger logger = LogManager.getLogger(OmtoffirRepositoryImpl.class);

	@Override
	public Integer insertOffenderFilesTrig(final OffenderCommunityFiles offenderCommunityFiles) {
		final String sql = getQuery("OMTOFFIR_OFFENDER_FILES_TRIG");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderCommunityFiles));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Integer insertOffenderFilesTrig1(final OffenderCommunityFiles offenderCommunityFiles) {
		final String sql = getQuery("OMTOFFIR_OFFENDER_FILES_TRIG1");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<>();
		parameters.add(new BeanPropertySqlParameterSource(offenderCommunityFiles));
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (final Exception e) {
			logger.error("insert", e);
		}
		if (returnArray.length == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public OffenderCommunityFiles getOffenderCommunityFiles(final OffenderCommunityFiles offenderCommunityFiles) {
		final String sql = getQuery("OMTOFFIR_OFFENDER_COMMUNITY_FILES");
		try {
			return namedParameterJdbcTemplate.queryForObject(sql,
					createParams("offenderId", offenderCommunityFiles.getOffenderId(), "offenderFileSeq",
							offenderCommunityFiles.getOffenderFileSeq()),
					new BeanPropertyRowMapper<OffenderCommunityFiles>(OffenderCommunityFiles.class));
		} catch (final Exception e) {
			logger.error("getOffenderCommunityFiles", e);
			return null;
		}
	}

}
