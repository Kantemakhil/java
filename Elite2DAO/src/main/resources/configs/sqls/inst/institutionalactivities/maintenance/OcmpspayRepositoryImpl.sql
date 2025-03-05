
OCMPSPAY_COMPENSATION_TYPE_RECORDGROUP_QUERY {
select to_char(program_id) CODE,program_code codes ,description,
case when active_flag = 'Y' then true else false end canDisplay from PROGRAM_SERVICES where program_category = :program_category 
}

OCMPSPAY_PROGRAM_SERVICES_QUERY {
select PROGRAM_ID ,program_code ,description,program_category from PROGRAM_SERVICES
}

OCMPSPAY_COMPENSATION_CODE_RECORDGROUP {
 select to_char(crs_acty_id) CODE, CODE codes,DESCRIPTION from COURSE_ACTIVITIES where PROGRAM_ID = :PROGRAM_ID  
 AND (CURRENT_DATE < schedule_end_date OR schedule_end_date IS NULL);
}

OCMPSPAY_UNIT_RECORDGROUP_QUERY {
select * from reference_codes where domain='UNIT' and parent_code = 'PSPAY'
}

OCMPSPAY_CATEGORY_EXECUTEQUERY {
SELECT * FROM PROGRAMS_PAY
}

OCMPSPAY_CATEGORY_INSERT_QUERY {
insert into programs_pay(program_category, active_flag, expiry_date, create_datetime, create_user_id) values(:programCategory, :activeFlag, :expiryDate, current_timestamp, :createUserId)
}

OCMPSPAY_CATEGORY_UPDATE_QUERY {
UPDATE PROGRAMS_PAY SET active_flag=:activeFlag ,expiry_date=:expiryDate, modify_datetime =current_timestamp, modify_user_id =:modifyUserId WHERE program_category=:programCategory
}

OCMPSPAY_CATEGORY_PRE_INSERT {
SELECT COUNT(*) FROM programs_pay MCR WHERE PROGRAM_CATEGORY in (:PROGRAM_CATEGORY) 
}


OCMPSPAY_COMPENSATION_EXECUTEQUERY {
SELECT * FROM programs_pay_compensation MCR WHERE PROGRAM_CATEGORY =:PROGRAM_CATEGORY
}

OCMPSPAY_COMPENSATION_INSERT_QUERY {
 insert into programs_pay_compensation(program_category, program_id, crs_acty_id, unit, rate, create_datetime, create_user_id) values(:programCategory, :programId, case when :crsActyId = 0 then null else :crsActyId end, :unit, :rate, current_timestamp , :createUserId)
}

OCMPSPAY_COMPENSATION_UPDATE_QUERY {
UPDATE programs_pay_compensation SET rate =:rate, modify_datetime =current_timestamp, modify_user_id =:modifyUserId WHERE program_category=:programCategory and program_id =:programId and coalesce(crs_acty_id,0) =:crsActyId
}

OCMPSPAY_COMPENSATION_DELETE_QUERY {
DELETE FROM programs_pay_compensation WHERE program_category=:programCategory and program_id =:programId and coalesce(crs_acty_id,0) =:crsActyId
}

OCMPSPAY_COMPENSATION_PRE_INSERT {
select COUNT(*) from  programs_pay_compensation 
	where PROGRAM_CATEGORY in (:PROGRAM_CATEGORY) and program_id in (:program_id) AND coalesce(crs_acty_id,0) in (:crsActyId)
}


