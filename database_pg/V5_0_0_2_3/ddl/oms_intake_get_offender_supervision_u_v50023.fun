CREATE OR REPLACE FUNCTION oms_owner.oms_intake_get_offender_supervision_u(p_offender_book_id bigint, p_username text)
 RETURNS character varying
 LANGUAGE plpgsql
 SECURITY DEFINER
AS $function$
DECLARE

      lv_level_code        varchar(12);
      lv_domain            reference_codes.domain%TYPE;
      lv_caseload_type     caseloads.caseload_type%TYPE;
      lv_level_description varchar(40);
      lv_value1            system_profiles.profile_value%TYPE;
      lv_value2            system_profiles.profile_value_2%TYPE;

      ref_cur CURSOR(p_sup_level text, p_domain text) FOR
         SELECT description
           FROM reference_codes
          WHERE domain = lv_domain
            AND code = p_sup_level;

      sys_cur CURSOR FOR
         SELECT profile_value, profile_value_2
           FROM system_profiles
          WHERE profile_type = 'CLIENT'
            AND profile_code = 'PENDING_STAT';

   
BEGIN
      lv_level_code    := pims_weight_get_sup_level_u(p_offender_book_id,p_username);
      lv_caseload_type := oms_intake_get_caseload_type_U(p_username);
      --IF lv_caseload_type = 'COMM' THEN
      --lv_domain := 'COM_SUP_LVLS';
      --ELSE
      --lv_domain := 'SUP_LVL_TYPE';
      --END IF;
      lv_domain := 'SUP_LVL_TYPE';
      OPEN ref_cur(lv_level_code, lv_domain);
      FETCH ref_cur
         INTO lv_level_description;
      CLOSE ref_cur;
      IF coalesce(lv_level_description::text, '') = ''
      THEN
         OPEN sys_cur;
         FETCH sys_cur
            INTO lv_value1, lv_value2;
         CLOSE sys_cur;
         IF (lv_value2 IS NOT NULL AND lv_value2::text <> '')
         THEN
            lv_level_description := lv_value2;
         END IF;
      END IF;
      RETURN lv_level_description;
   END;
   --
   /*
   -- To copy the health problem to another booking.
   FUNCTION copy_health_problem(
   p_old_offender_book_id  NUMBER ,
   p_new_offender_book_id  NUMBER ,
   p_old_problem_seq  VARCHAR2 )
   RETURN NUMBER IS
     --
     --
     l_problem_seq NUMBER;
   BEGIN
      SELECT nvl(max(PROBLEM_SEQ),0) + 1
      INTO l_problem_seq
      FROM   OFFENDER_HEALTH_PROBLEMS
      WHERE  OFFENDER_BOOK_ID = p_new_offender_book_id;
     INSERT INTO offender_health_problems
        (offender_book_id,
        problem_seq,
        problem_type,
        problem_code,
        onset_date,
        partial_onset_date_flag,
        inactive_date,
        partial_inactive_date_flag,
        comment_text,
        create_date,
        problem_status,
        modify_datetime,
        modify_user_id)
     SELECT p_new_offender_book_id,
                    l_problem_seq,
                    problem_type,
                    problem_code,
                    onset_date,
                    partial_onset_date_flag,
                    inactive_date,
                    partial_inactive_date_flag,
                    comment_text,
                    SYSDATE,
                    problem_status,
                    SYSDATE,
                    USER
     FROM    offender_health_problems
     WHERE offender_book_id = p_old_offender_book_id
     AND      problem_seq = p_old_problem_seq;
     RETURN(l_problem_seq);
     EXCEPTION
        WHEN OTHERS THEN
           RAISE_APPLICATION_ERROR(
              -20000,
              oms_utils_display_user_message(
                 10, 'OMS', 'OMPIOSAU', SQLERRM )
           );
          RETURN(NULL);
   END;
   
   */
$function$
;