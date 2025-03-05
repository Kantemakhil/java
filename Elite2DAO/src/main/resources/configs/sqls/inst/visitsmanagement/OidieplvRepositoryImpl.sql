
OIDIEPLV_GET_LOVS{
select iep_level_code code,iep_level_description description,review_days,active_flag from incentives_earn_privs 
}
OIDIEPLV_GET_ALL_DATA{
select iep_level_code iep_Level_Code,(select iep_level_description from incentives_earn_privs iep where iep.iep_level_code =oiep.iep_level_code )as iepLeveldescription , (select STAFF.LAST_NAME || ', ' || STAFF.FIRST_NAME STAFF_NAME from STAFF_MEMBERS STAFF where STAFF.staff_id =oiep.approved_staff_id )as approvedStaff , oiep.assigned_date, oiep.next_review_date, oiep.review_comment, oiep.iep_level_seq, oiep.approved_staff_id, oiep.create_datetime from off_incentives_earn_privs oiep where offender_book_id=:offenderBookId order by create_datetime desc 
}
OIDIEPLV_GET_STAFF_DETAILS{
  select STAFF.LAST_NAME || ', ' || STAFF.FIRST_NAME STAFF_NAME , STAFF.STAFF_ID , USER_ID, mail_id from STAFF_MEMBERS STAFF where USER_ID =:USER_NAME order by STAFF_NAME asc
}
OIDIEPLV_UPDATE_COMMENT{
update off_incentives_earn_privs set review_comment =:reviewComment, modify_datetime = current_timestamp, modify_user_id =:modifyUserId , next_review_date=:nextReviewDate where offender_book_id =:offenderBookId and iep_level_seq =:sequence 
}
OIDIEPLV_INSERT_DATA
{
insert into off_incentives_earn_privs(iep_level_seq, offender_book_id, iep_level_code, assigned_date, approved_staff_id, next_review_date, review_comment, create_datetime, create_user_id, modify_datetime, modify_user_id, seal_flag) values(nextval('IEP_LEVEL_SEQ'), :offenderBookId, :iepLevelCode, :dateAsigned, :staffId, :nextReviewDate, :reviewComment, current_timestamp, :createUserId, NULL, NULL, :sealFlag) 
}
GET_REVIEWCODE_FOR_IEPLEVELCODE{
select iep_level_code,review_days from incentives_earn_privs iep
}
GET_SYSTEM_GENERATED_STAFF_ID_USER_NAME {
select staff_id from staff_members where user_id =( select user_id from oms_users_list oul where user_id =(( select profile_value from system_profiles where profile_type = 'CLIENT' and profile_code = 'SYS_GEN_USER')))
}
OIDIEPLV_GET_SYSTEM_STAFF_ID{
select staff_id from staff_members where user_id =( select user_id from oms_users_list oul where user_id ='SYSTEM') 
}

OIDIEPLV_GET_REVIEW_DAYS{
select current_timestamp::date + ilm.review_days * interval '1 day' review_days from incentives_earn_privs ilm where iep_level_code =:iepLevelCode 
}