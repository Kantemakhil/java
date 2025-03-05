
INSERT_DGOCE_EMIL{
  /*    INSERT INTO adhoc_email 
	  (offender_book_id, work_id, email_subject, email_body ,email_sender, email_from, to_list, cc_list, bcc_list)
      VALUES                         
      (:offender_book_id,:work_id,:email_subject,:EMPTY_CLOB(),:email_sender,:email_from,:email_from,:to_list,:cc_list,:bcc_list)  */
insert into adhoc_email (offender_book_id, work_id, email_subject, email_body , email_sender, email_from, to_list, cc_list, bcc_list, CREATE_USER_ID, CREATE_DATETIME, MODIFY_DATETIME ) values (:offender_book_id, :work_id, :email_subject, :EMPTY_CLOB(), :email_sender, :email_from, :email_from, :to_list, :cc_list, :bcc_list, :createUserId, CURRENT_TIMESTAMP , CURRENT_TIMESTAMP)
 }
 
 
SELECT_TEAM_MEMBERS{
    SELECT staff_id FROM TEAM_MEMBERS
     WHERE team_member_id =:team_member_id      
 }       