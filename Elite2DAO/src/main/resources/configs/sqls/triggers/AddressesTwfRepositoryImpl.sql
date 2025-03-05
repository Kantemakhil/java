GET_ADDRESSES_OBJECT {
select ADDRESS_ID,OWNER_CLASS,OWNER_ID,OWNER_SEQ,OWNER_CODE,ADDRESS_TYPE,CITY_CODE,COUNTRY_CODE,VALIDATED_PAF_FLAG,PRIMARY_FLAG,MAIL_FLAG,CAPACITY,COMMENT_TEXT,CREATE_DATETIME,CREATE_USER_ID,MODIFY_DATETIME,MODIFY_USER_ID,NO_FIXED_ADDRESS_FLAG,SERVICES_FLAG,SPECIAL_NEEDS_CODE,CONTACT_PERSON_NAME,BUSINESS_HOUR,START_DATE,END_DATE,CITY_NAME,PROV_STATE_CODE,STREET,ZIP_POSTAL_CODE,SUITE_NUMBER,STREET_NUMBER,STREET_DIRECTION,MAIL_CARE_OF,SEAL_FLAG from ADDRESSES where ADDRESS_ID=:addressId
}

GET_OFFENDER_BOOK_ID {
SELECT ob.offender_book_id
        FROM offender_bookings ob
       WHERE ob.active_flag = 'Y'
         AND ob.booking_type = 'INST'
         AND ob.booking_end_date IS NULL
         AND ob.offender_id = :owner_id
}

GET_ADDRESS_ID {
 SELECT address_id
        FROM release_plans rp
      WHERE rp.offender_book_id = :offender_book_id
         AND rp.plan_status = 'INPRG'
}

GET_DISTINCT_1 {
SELECT DISTINCT 1
     FROM release_plans rp, staff_members sm
    WHERE rp.offender_book_id = :offender_book_id
      AND rp.case_manager_id = sm.staff_id
      AND rp.plan_status = 'INPRG'
      AND user_id = NVL(:modify_user_id,:create_user_id)
    UNION
   SELECT DISTINCT 1
     FROM release_plans rp, staff_members sm
    WHERE rp.offender_book_id = :offender_book_id
      AND rp.case_manager_id = sm.staff_id
      AND rp.plan_status = 'INPRG'
      AND user_id = NVL(:modify_user_id,:create_user_id)
}
