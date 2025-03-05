package net.syscon.s4.pkgs.merge_process.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.syscon.s4.common.beans.SentenceTerms;
import net.syscon.s4.common.impl.RepositoryBase;
import net.syscon.s4.common.impl.Row2BeanRowMapper;
import net.syscon.s4.pkgs.MergeProcessRules;
import net.syscon.s4.pkgs.merge_process.MergeProcessRepository;
import net.syscon.s4.sa.recordmaintenance.MergeProcesses;

@Repository
public class MergeProcessRepositoryImpl extends RepositoryBase implements MergeProcessRepository{

	private static Logger logger = LogManager.getLogger(MergeProcessRepositoryImpl.class.getName());
	
	@Override
	public Long getStartProcessId(String transactionType) {
		final String sql = getQuery("MERGE_PROCESS_GET_START_PROCESS_ID");
		Long processId = null;
		try {
			processId = namedParameterJdbcTemplate.queryForObject(sql, createParams("transactionType",transactionType), Long.class);	
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getStartProcessId", e);
		}
		return processId;
	}

	@Override
	public MergeProcesses getMergeProcessesInfo(Long processId) {
		final String sql = getQuery("MERGE_PROCESS_GET_MERGE_PROCESSES_INFO");
		MergeProcesses mergeProcessesInfo = new MergeProcesses();
		try {
		mergeProcessesInfo = namedParameterJdbcTemplate.queryForObject(sql, createParams("processId",processId), 
				new BeanPropertyRowMapper<MergeProcesses>(MergeProcesses.class));
		}catch(Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getMergeProcessesInfo", e);
		}
		return mergeProcessesInfo;
	}

	@Override
	public Long checkTransferFlag(Long mergeTransactionId, Long processId) {
		final String sql = getQuery("MERGE_PROCESS_CHECK_TRANSFER_FLAG");
		Long count = null;
		try {
			  count = namedParameterJdbcTemplate.queryForObject(sql, createParams("mergeTransactionId", mergeTransactionId ,"processId", processId ),
					(Long.class));
		} catch (Exception e) {
			logger.error("Exception occured in " + this.getClass().getName() + " in method getMergeProcessesInfo", e);
		}
		return count;
	}

	@Override
	public MergeProcessRules getMergeProcessRulesInfo(Long ruleId) {
		final String sql = getQuery("MERGE_PROCESS_GET_MERGE_PROCESS_RULES_INFO");
		MergeProcessRules mergeProcessRulesRow = new MergeProcessRules();
		if (ruleId == 9999 || ruleId == 300 || ruleId == 230 || ruleId == 500 || ruleId == 1000 || ruleId == 2000) {
			try {
				mergeProcessRulesRow = namedParameterJdbcTemplate.queryForObject(sql, createParams("ruleId", ruleId),
						new BeanPropertyRowMapper<MergeProcessRules>(MergeProcessRules.class));
			} catch (Exception e) {
				logger.error("Exception occured in " + this.getClass().getName() + " in method getMergeProcessRulesInfo", e);
			}
		}
		return mergeProcessRulesRow;

	}
}
