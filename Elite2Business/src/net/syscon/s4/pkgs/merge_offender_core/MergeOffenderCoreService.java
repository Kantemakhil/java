package net.syscon.s4.pkgs.merge_offender_core;

public interface MergeOffenderCoreService {

	public Boolean mergeOffenders(Long mergeTransactionId, Long fromRootOffenderId, Long fromOffenderBookId,
			Long toRootOffenderId, Long toOffenderBookId, String mergeType, String transactionType, String user);

	public void mergeApplnRecords(String string, Long lvRootOffIdFrom, Long lvRootOffIdTo);
	
	public void mergeOffenderGeneric(String tableName, String objectClass, Long lvRootOffIdFrom, Long lvRootOffIdTo, Long seqNumber );

}
