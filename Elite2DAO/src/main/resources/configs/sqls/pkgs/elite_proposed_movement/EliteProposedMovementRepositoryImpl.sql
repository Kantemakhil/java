ELITE_PROPOSED_MOVEMENT_IF_ROLE_ASSIGNED{

  select tag_transport_if_role_assigned ( :userId, :roleNm )

}

ELITE_PROPOSED_MOVEMENT_GET_SEQ_CUR_LOC_DTLS {

   SELECT coalesce(MAX(detail_seq), 0) + 1  FROM offender_loc_chng_dtls WHERE offender_book_id = :p_off_bkg  AND location_seq = :p_loc_seq
   
}

ELITE_PROPOSED_MOVEMENT_INSERT_LOC_CHNG_DTLS {

INSERT INTO offender_loc_chng_dtls(offender_book_id,location_seq,detail_seq,status_code,recorded_by,recorded_date,app_rsn,txn_status,txn_rsn,CREATE_USER_ID , CREATE_DATETIME)
      VALUES(:offenderBookId,:locationSeq,:detailSeq,:statusCode,:recordedBy,:recordedDate,:appRsn,:txnStatus,:txnRsn, :createUserId, CURRENT_TIMESTAMP)
}

ELITE_PROPOSED_MOVEMENT_GET_SEQ_CUR_INST{
SELECT coalesce (MAX(location_seq), 0) + 1  FROM offender_proposed_intlocs WHERE offender_book_id = :offenderBookId
}

ELITE_PROPOSED_MOVEMENT_STATUS_INM_CUR_INT_LOC {
 SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
               ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_loc_chng_dtls omd
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
            AND detail_seq =
                (SELECT MAX(detail_seq)
                   FROM offender_loc_chng_dtls
                  WHERE offender_book_id = :p_off_bkg
                    AND location_seq = :p_loc_seq
                    AND ((app_rsn IS NOT NULL AND :p_choice = 'APP') OR
                        (txn_rsn IS NOT NULL AND :p_choice = 'TXN') OR
                        (status_code = :p_choice AND
                        :p_choice NOT IN ('APP', 'TXN')))
                    AND txn_status != 'CREQ')
}

ELITE_PROPOSED_MOVEMENT_MAX_STATUS_INM_CUR_INT_LOC {
SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
               ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_loc_chng_dtls omd
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
            AND detail_seq = (SELECT MAX(detail_seq)
                                FROM offender_loc_chng_dtls
                               WHERE offender_book_id = :p_off_bkg
                                 AND location_seq = :p_loc_seq
                                 AND txn_status = :p_choice)
            AND txn_status = :p_choice
}


ELITE_PROPOSED_MOVEMENT_IF_INTR_NON_ASSO_EXISTS {
select tag_transport_IF_INTR_NON_ASSO_EXISTS ( :offender_book_id, :curr_agy_id, :to_living_unit_id )
}

ELITE_PROPOSED_MOVEMENT_IMPSTATUS_CUR{
SELECT imprisonment_status
           FROM offender_imprison_statuses
          WHERE offender_book_id = :offender_book_id 
            AND expiry_date IS NULL
          ORDER BY imprison_status_seq DESC
}

ELITE_PROPOSED_MOVEMENT_SANCTION_CUR{
  SELECT COUNT(*)
           FROM offender_oic_sanctions
          WHERE offender_book_id = :offender_book_id
            AND status = 'ACTIVE'
}

ELITE_PROPOSED_MOVEMENT_STGAFFI_CUR{
 SELECT stg.description
           FROM offender_stg_affiliations osa, security_threat_groups stg
          WHERE offender_book_id =:offender_book_id
            AND stg_seq = (SELECT MAX(stg_seq)
                             FROM offender_stg_affiliations
                            WHERE offender_book_id = :offender_book_id
                              AND active_flag = 'Y')
            AND osa.stg_id = stg.stg_id
}


ELITE_PROPOSED_MOVEMENT_STATUS_INM_CUR{
  SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
              ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_movement_details omd
          WHERE offender_book_id = :p_off_bkg
            AND movement_seq = :p_move_seq
            AND detail_seq =
                (SELECT MAX(detail_seq)
                   FROM offender_movement_details
                  WHERE offender_book_id = :p_off_bkg
                    AND movement_seq = :p_move_seq
                    AND ((app_rsn IS NOT NULL AND :p_choice = 'APP') OR
                        (txn_rsn IS NOT NULL AND :p_choice = 'TXN') OR
                        (status_code = :p_choice AND
                        :p_choice NOT IN ('APP', 'TXN')))
                    AND txn_status != 'CREQ')	

}


ELITE_PROPOSED_MOVEMENT_MAX_STATUS_INM_CUR{
SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
              ,tag_utils_get_staff_name ( tag_utils_get_staff_id ( recorded_by )) recorded_by
               ,recorded_date
           FROM offender_movement_details omd
          WHERE offender_book_id = :p_off_bkg
            AND location_seq = :p_loc_seq
            AND detail_seq = (SELECT MAX(detail_seq)
                                FROM offender_movement_details
                               WHERE offender_book_id = :p_off_bkg
                                 AND location_seq = :p_loc_seq
                                 AND txn_status = :p_choice)
             AND txn_status = :p_choice                
}



ELITE_PROPOSED_MOVEMENTT_MAX_STATUS_NON_INM_CUR{
  SELECT status_code
               ,app_rsn
               ,txn_status
               ,txn_rsn
               ,recorded_by
               ,recorded_date
           FROM non_adm_inm_mvmt_details omd
          WHERE non_adm_inmate_id = :p_inm_id
            AND detail_seq = (SELECT MAX(detail_seq)
                    FROM non_adm_inm_mvmt_details
                    WHERE non_adm_inmate_id = :p_inm_id
                    AND txn_status = :p_choice)
            AND txn_status = :p_choice
	}

ELITE_PROPOSED_MOVEMENT_STATUS_NON_INM_CUR{
  select
	status_code
               ,
	app_rsn
               ,
	txn_status
               ,
	txn_rsn
               ,
	recorded_by
               ,
	recorded_date
from
	non_adm_inm_mvmt_details omd
where
	non_adm_inmate_id = :p_inm_id
	and detail_seq =
                (
	select
		MAX(detail_seq)
	from
		non_adm_inm_mvmt_details
	where
		non_adm_inmate_id = :p_inm_id
		and ((app_rsn is not null
			and p_choice = 'APP')
		or
                        (txn_rsn is not null
			and p_choice = 'TXN')
		or
                        (status_code = :p_choice
			and
                        p_choice not in ('APP', 'TXN')))
			and txn_status != 'CREQ')
}

ELITE_PROPOSED_MOVEMENTT_INSERTINTO_OFFENDERMOVEMENTDETAILS{
    INSERT INTO offender_movement_details
         (offender_book_id
         ,movement_seq
         ,detail_seq
         ,status_code
         ,recorded_by
         ,recorded_date
         ,app_rsn
         ,txn_status
         ,txn_rsn
         ,CREATE_USER_ID 
 		 ,CREATE_DATETIME)
      VALUES
         (:offenderBookId
         ,:movementSeq
         ,:detailSeq
         ,:statusCode
         ,:recordedBy
         ,CURRENT_TIMESTAMP
         ,:appRsn
         ,:txnStatus
         ,:txnRsn
         ,:createUserId
         ,CURRENT_TIMESTAMP)
         
}


ELITE_PROPOSED_MOVEMENTT_GET_SEQ_CUR_OFFENDER_MOVEMENT_DETAILS{
SELECT coalesce(MAX(detail_seq), 0) + 1
           FROM offender_movement_details
          WHERE offender_book_id =:pOffBkg
            AND movement_seq =:pMoveSeq
}



