package net.syscon.s4.pkgs.merge_log.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.syscon.s4.pkgs.merge_log.MergeLogRepository;
import net.syscon.s4.pkgs.merge_log.MergeLogService;
import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;

@Service
public class MergeLogServiceImpl implements MergeLogService {
	
	@Autowired
	private MergeLogRepository mergeLogRepository;
	
	@Override
	public void traceIn(String pMsg, Long mergeTransactionId, String user) {
		trace(pMsg, mergeTransactionId, user);
	}

	@Override
	public void trace(String pMsg, Long mergeTransactionId, String user) {
		Integer kTraceLevel = 8;
		log(kTraceLevel, pMsg, mergeTransactionId, user);
	}

	@Override
	public void debug(String pMsg, Long mergeTransactionId, String user) {
		Integer kDebugLevel = 16;
		log(kDebugLevel, pMsg, mergeTransactionId, user);	
	} 	
	
	@Override
	public void info(String pMsg, Long mergeTransactionId, String user) {
		Integer kInfoLevel = 4;
		log(kInfoLevel, pMsg, mergeTransactionId, user);
	}

	
	@Override
	public void log(Integer pLevel, String pMsg, Long mergeTransactionId, String user) {
		Long lTransactionId = null;
		String lLoggingLevelTxt = null;
		Integer gLoggingLevel  = 0;
		Integer kTableLog = 1;
		Integer kScreenLog = 2;
		Integer gLoggingOutput = kTableLog + kScreenLog;
		if(pLevel != null) {
			lLoggingLevelTxt = getLoggingLevelTxt(pLevel);
			lTransactionId = mergeTransactionId;	
		}
		if((gLoggingOutput & kTableLog) == kTableLog) {
			insMergeLogs(lTransactionId, lLoggingLevelTxt, pMsg, user);
		}
	}
	
	public String getLoggingLevelTxt(Integer pLoggingLevelNo) {
		String logLevelTxt = null;
		if(pLoggingLevelNo == 1) {
			logLevelTxt = "ERROR";
		}
		if(pLoggingLevelNo == 2) {
			logLevelTxt =  "WARN";
		}
		if(pLoggingLevelNo == 4) {
			logLevelTxt = "INFO";
		}
		if(pLoggingLevelNo == 8) {
			logLevelTxt = "TRACE";
		}
		if(pLoggingLevelNo == 16) {
			logLevelTxt = "DEBUG";
		}
		return logLevelTxt;
	}
	
	
	public void insMergeLogs(Long pTransactionId,String pLogLevel,String pMessage, String user) {
		Integer cMax = 4000;
		double lPartsd;
		Integer lRem = pMessage.length();
		Integer val = lRem / cMax;
		lPartsd = Math.ceil(val);
		Integer lParts = (int) lPartsd;
		Integer lPos = 1;
		java.util.Date lTimestamp = new java.util.Date();
		BigDecimal lPartsB = new BigDecimal(1);
		Long mergeTransactionLogId = getNextLogId();
		if (pTransactionId == null) {
			pTransactionId = 1l;
		}
		List<MergeTransactionLogs> mrgTransLoglist = new ArrayList<>();
		MergeTransactionLogs mrgTrnsLog = new MergeTransactionLogs();
		//for (int i = 1; i < lParts; i++) {
			mrgTrnsLog.setMergeTransactionLogId(mergeTransactionLogId);
			mrgTrnsLog.setMergeTransactionId(pTransactionId);
			mrgTrnsLog.setLogTimestamp(lTimestamp);
			mrgTrnsLog.setLogMsgPart(lPartsB);
			mrgTrnsLog.setLogLevel(pLogLevel);
			//mrgTrnsLog.setLogText(pMessage.substring(lPos, cMax));
			mrgTrnsLog.setLogText(pMessage);
			mrgTrnsLog.setCreateUserId(user);
			lPos = lPos + cMax;
			mrgTransLoglist.add(mrgTrnsLog);
		//}
		mergeLogRepository.saveMrgTransLogs(mrgTransLoglist);
	}
	
	public Long getNextLogId() {
		Long lvId = mergeLogRepository.getId();
		return lvId;
	}

	@Override
	public String getLoggingLevelStr() {
		String lLoggingLevelStr;
		Integer lIndx = 1;
		return null;
	}

	@Override
	public void error(String pMsg, Long mergeTransactionId, String user) {
		Integer kErrorLevel = 1;
		log(kErrorLevel, pMsg, mergeTransactionId, user);
	}
	
}
