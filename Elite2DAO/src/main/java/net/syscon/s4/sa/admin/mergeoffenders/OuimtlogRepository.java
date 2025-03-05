package net.syscon.s4.sa.admin.mergeoffenders;

import java.util.List;

import net.syscon.s4.sa.admin.beans.MergeTransactionLogs;

/**
 * Interface OuimtlogRepository
 */
public interface OuimtlogRepository {
	List<MergeTransactionLogs> mergeLogExecuteQuery(MergeTransactionLogs object);

}
