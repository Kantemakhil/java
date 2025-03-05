SELECT_TRANSACTIONS{
         SELECT transaction_id
           FROM offender_file_transactions
          WHERE offender_file_seq =:p_offender_file_seq
            AND offender_id =:p_offender_id
            AND confirmed = 'N'
 }
 
 UPDATE_OFFENDER_FILE_TRANSACTIO{
 update offender_file_transactions set confirmed = 'Y', file_trans_comment = 'Cancelled', MODIFY_USER_ID = :modifyUserId , MODIFY_DATETIME = CURRENT_TIMESTAMP where transaction_id =:transaction_id
  }
  
 INSERT_OFFENDER_FILE_TRANSACTIONS{
 insert into offender_file_transactions (transaction_id, offender_file_seq, offender_id, transaction_date, transfer_date, file_trans_type, confirmed, creation_date, creation_user, agy_loc_id_from, agy_loc_id_to, staff_id_from, staff_id_to, non_officer_from, non_officer_to, file_trans_comment, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (NEXTVAL('TRANSACTION_ID'), :offenderFileSeq, :offenderId, CURRENT_TIMESTAMP, :transferDate, :fileTransType, :confirmed, CURRENT_TIMESTAMP, user, :agyLocIdFrom, :agyLocIdTo, :staffIdFrom, :staffIdTo, :nonOfficerFrom, :nonOfficerTo, :fileTransComment, :createUserId, CURRENT_TIMESTAMP , null )
 }
 
 TRANSFER_FILE_GET_MAX_OFFENDER_FILE_SEQ{
 SELECT MAX (offender_file_seq)
           FROM offender_community_files
          WHERE offender_file_num =
                   (SELECT offender_file_num
                      FROM offender_community_files
                     WHERE offender_file_seq = :P_OFFENDER_FILE_SEQ
                       AND offender_id = :P_OFFENDER_ID
                       AND non_officer_status = 'MERGED')
            AND offender_id = :P_OFFENDER_ID
            AND non_officer_status != 'MERGERD'
 }   
 
TRANSFER_FILE_TRAN_CUR{ 
 SELECT 'x'
           FROM offender_file_transactions
          WHERE file_trans_type = 'TRAN'
            AND confirmed = 'N'
            AND offender_id = :P_OFFENDER_ID
            AND offender_file_seq = :P_OFFENDER_FILE_SEQ
}            

TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_ONE{

insert into offender_file_transactions ( transaction_id, offender_file_seq, offender_id, transaction_date, transfer_date, file_trans_type, confirmed, creation_date, creation_user, agy_loc_id_from, agy_loc_id_to, staff_id_from, staff_id_to, non_officer_from, non_officer_to, file_trans_comment, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( nextval('offender_file_transaction_id'), :p_offender_file_seq, :p_offender_id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TRAN', 'Y', CURRENT_TIMESTAMP, user, :p_agy_loc_id_from, :p_agy_loc_id_to, :p_staff_id_from, :p_staff_id_to, null, null, :p_file_trans_comment, :createUserId, CURRENT_TIMESTAMP , null )
  }          
  
  TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_SECOND{

  insert into offender_file_transactions ( transaction_id, offender_file_seq, offender_id, transaction_date, transfer_date, file_trans_type, confirmed, creation_date, creation_user, agy_loc_id_from, agy_loc_id_to, staff_id_from, staff_id_to, non_officer_from, non_officer_to, file_trans_comment, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( nextval('offender_file_transaction_id'), :p_offender_file_seq, :p_offender_id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TRAN', :lv_confirmed, CURRENT_TIMESTAMP, user, :p_agy_loc_id_from, :p_agy_loc_id_to, :p_staff_id_from, null, null, :p_non_officer_to, :p_file_trans_comment, :createUserId, CURRENT_TIMESTAMP , null )
  }                
  TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_THREE{  

  insert into offender_file_transactions ( transaction_id, offender_file_seq, offender_id, transaction_date, transfer_date, file_trans_type, confirmed, creation_date, creation_user, agy_loc_id_from, agy_loc_id_to, staff_id_from, staff_id_to, non_officer_from, non_officer_to, file_trans_comment, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values ( nextval ('offender_file_transaction_id'), :p_offender_file_seq, :p_offender_id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TRAN', 'Y', CURRENT_TIMESTAMP, user, :p_agy_loc_id_from, :p_agy_loc_id_to, null, :p_staff_id_to, :p_non_officer_from, null, :p_file_trans_comment, :createUserId, CURRENT_TIMESTAMP , null )
 } 
 
 TRANSFER_FILE_OFFENDER_FILE_TRANSACTIONS_FOUR{

 insert into offender_file_transactions ( transaction_id, offender_file_seq, offender_id, transaction_date, transfer_date, file_trans_type, confirmed, creation_date, creation_user, agy_loc_id_from, agy_loc_id_to, staff_id_from, staff_id_to, non_officer_from, non_officer_to, file_trans_comment, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (nextval('offender_file_transaction_id'), :p_offender_file_seq, :p_offender_id, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'TRAN', :lv_confirmed, CURRENT_TIMESTAMP, user, :p_agy_loc_id_from, :p_agy_loc_id_to, null, null, :p_non_officer_from, :p_non_officer_to, :p_file_trans_comment, :createUserId, CURRENT_TIMESTAMP , null )
}                  
