
OUIMTSTP_MERGTXNPROC_FIND_V_MERGE_TRANSACTION_PROCESSES {
 	SELECT MERGE_TRANSACTION_ID ,TRANSACTION_TYPE ,PROCESS_ID ,PROCESS_NAME ,PROCESS_DESCRIPTION ,TRANSFER_FLAG ,TIMEFRAME_FLAG ,BEGIN_DATE ,BEGIN_TIME ,END_DATE ,END_TIME ,MANDATORY_ON_FLAG ,DEFAULT_ON_FLAG   FROM V_MERGE_TRANSACTION_PROCESSES  where  merge_transaction_id =:mergeTransactionId
}

OUIMTSTP_CREATE_FORM_GLOBALS {
	SELECT DESCRIPTION INTO V_FORM_DESC FROM OMS_MODULES WHERE MODULE_NAME = V_FORM_NAME
}
