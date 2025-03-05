package net.syscon.s4.pkgs.merge_log.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.im.beans.OffenderNaDetails;
import net.syscon.s4.im.beans.OffenderNonAssociations;
import net.syscon.s4.legalorders.OffenderCommunityFiles;
import net.syscon.s4.pkgs.merge_log.MergeLogRepository;
import net.syscon.s4.pkgs.merge_process.impl.MergeProcessRepositoryImpl;
import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;

@Repository
public class MergeLogRepositoryImpl extends RepositoryBase implements MergeLogRepository{

	private static Logger logger = LogManager.getLogger(MergeProcessRepositoryImpl.class.getName());
	
	@Override
	public Long getId() {
			final String sql = getQuery("MERGE_LOG_GET_MERGE_TRANSACTION_LOG_ID");
			Long mrgTrnsLogId = null;;
			try {
				 mrgTrnsLogId = namedParameterJdbcTemplate.queryForObject(sql, createParams(),(Long.class));
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method getId", e);
			}
		return mrgTrnsLogId;
	}

	@Override
	public Integer saveMrgTransLogs(List<MergeTransactionLogs> mrgTransLogs) {
		final String sql = getQuery("MERGE_LOG_INSERT_MERGE_TRNS_LOGS");
		int[] returnArray = new int[] {};
		final List<SqlParameterSource> parameters = new ArrayList<SqlParameterSource>();
		for (final MergeTransactionLogs sentenceTerms : mrgTransLogs) {
			parameters.add(new BeanPropertySqlParameterSource(sentenceTerms));
		}
		try {
			returnArray = namedParameterJdbcTemplate.batchUpdate(sql, parameters.toArray(new SqlParameterSource[0]));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method saveMrgTransLogs", e);
		}
		if (mrgTransLogs.size() == returnArray.length) {
			return 1;
		} else {
			return 0;
		}
	}
}
