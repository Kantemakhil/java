GET_COPY_TABLES_CUR{
SELECT table_name, col_name, seq_name, parent_table FROM copy_tables WHERE table_operation_code = 'COP' AND movement_type = :pMoveType AND movement_reason_code = :pMoveReason AND active_flag = 'Y' AND expiry_date IS NULL  ORDER BY list_seq 
}

GET_COLUMN_LIST_CUR{
SELECT DISTINCT upper(column_name) column_name FROM all_tab_columns  WHERE lower(table_name) =lower(:pTableName);

}

GET_WRONG_COLUMN_NAME_FLAG{
SELECT COUNT(*) FROM all_tab_columns WHERE lower(table_name)  = lower(:pTableName) AND lower(column_name) = lower('OFFENDER_BOOKING_ID')
}

GET_COL_SEQ_NAMES{
SELECT DISTINCT col_name, seq_name FROM copy_tables WHERE table_operation_code = 'COP' AND movement_type = :vMoveType AND movement_reason_code = :vMoveReason AND active_flag = 'Y' AND expiry_date IS NULL AND col_name = :columnName 
}

GET_V_PK_COL_COUNT{
SELECT COUNT(*) FROM user_ind_columns a WHERE a.table_name = :ptablename AND a.column_name = :columnname AND EXISTS ( SELECT 1 FROM user_constraints b WHERE b.constraint_name = a.index_name AND b.table_name = a.table_name AND b.constraint_type = 'P' )
}

GET_WORK_FLOW_CUR{
SELECT * FROM work_flows wf WHERE wf.object_id = :p_old_book_id AND wf.object_code = 'ALERT' AND EXISTS ( SELECT 'X' FROM work_flow_logs wfl WHERE wfl.work_action_code = 'ENT' AND wfl.work_flow_id = wf.work_flow_id )
}

GET_ID_CUR{
SELECT NEXTVAL('work_flow_id') FROM dual
}

INSERT_WORK_FLOWS_COPY{
 --INSERT INTO work_flows ( work_flow_id, object_code, object_id, object_seq ) VALUES ( :LV_WORK_FLOW_ID, :OBJECT_CODE, :P_NEW_BOOK_ID, :OBJECT_SEQ )
insert into work_flows ( work_flow_id, object_code, object_id, object_seq , CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:LV_WORK_FLOW_ID, :OBJECT_CODE, :P_NEW_BOOK_ID, :OBJECT_SEQ , :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}

INSERT_WORK_FLOW_LOGS_COPY{
--INSERT INTO WORK_FLOW_LOGS( work_flow_id, work_flow_seq, work_action_code, work_action_date, action_user_id, work_flow_status, create_date, locate_agy_loc_id, create_user_id ) VALUES ( :LV_WORK_FLOW_ID, 1, 'ENT', SYSDATE, NULL, 'DONE', SYSDATE, NULL, USER )
insert into WORK_FLOW_LOGS( work_flow_id, work_flow_seq, work_action_code, work_action_date, action_user_id, work_flow_status, create_date, locate_agy_loc_id, create_user_id , CREATE_DATETIME, MODIFY_DATETIME ) values ( :LV_WORK_FLOW_ID, 1, 'ENT', CURRENT_TIMESTAMP, null, 'DONE', CURRENT_TIMESTAMP, null, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
}





