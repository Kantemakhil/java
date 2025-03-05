package net.syscon.s4.pkgs.merge_log;

import java.util.List;

import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;

public interface MergeLogRepository {

	public Long getId();
	
	public Integer saveMrgTransLogs(List<MergeTransactionLogs> mrgTransLogs);
}
