package net.syscon.s4.pkgs.merge_log;

public interface MergeLogService {

	public void traceIn(String pMsg , Long mergeTransactionId, String user);
	
	public void trace(String pMsg,Long mergeTransactionId, String user);
	
	public void debug(String pMsg,Long mergeTransactionId, String user);
	
	public void info(String pMsg,Long mergeTransactionId, String user);
	
	public void log(Integer pLevel, String pMsg, Long mergeTransactionId, String user);
	
	public String getLoggingLevelStr();
	
	public void error(String pMsg , Long mergeTransactionId, String user);
}
