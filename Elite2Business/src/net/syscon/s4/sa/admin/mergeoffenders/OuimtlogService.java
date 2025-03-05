package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;

/**
 * Interface OuimtlogService
 */
public interface OuimtlogService {
	List<MergeTransactionLogs> mergeLogExecuteQuery(MergeTransactionLogs object);

}
