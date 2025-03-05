DELETE_ORDERS{
 DELETE FROM orders  WHERE case_id =:p_case_id AND offender_book_id =:p_offender_book_id AND event_id =:p_event_id
}

SELECT_ORDERS_CUR{
 SELECT order_id FROM orders  WHERE case_id =:p_case_id AND offender_book_id =:p_offender_book_id AND event_id =:p_event_id
}
DELETE_ORDER_PPSL_COND_ACTIVITIES {
 DELETE FROM order_ppsl_cond_activities  WHERE order_proposal_condition_id IN ( SELECT order_proposal_condition_id FROM order_proposal_conditions WHERE order_id =:order_id)                                          
}
 
ORDER_PURPOSES{
 DELETE FROM order_purposes  WHERE order_id =:order_id
}
 ORDER_PROPOSAL_COND{
 DELETE FROM order_proposal_conditions WHERE order_id = :order_id
}
 ORDER_PROPOSALS{
  DELETE FROM order_proposals  WHERE order_id =:order_id
}
DELETE_OFFENDER_SENTENCE_TERMS{
DELETE FROM offender_sentence_terms WHERE offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq
}
DELETE_OFFENDER_SENTENCE_ADJUSTS{
DELETE FROM offender_sentence_adjusts WHERE offender_book_id =:p_offender_book_id  AND sentence_seq =:p_sentence_seq
}
DELETE_OFFENDER_SENTENCE_STATUSES{
 DELETE FROM offender_sentence_statuses WHERE offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq
 }
 DELETE_OFFENDER_SENTENCE_HTY{
 DELETE FROM offender_sentence_hty WHERE offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq
 }
 DELETE_OFFENDER_LICENCE_SENTENCES{
 DELETE FROM offender_licence_sentences WHERE offender_book_id =:p_offender_book_id AND sentence_seq =:p_sentence_seq
 }
 DELETE_CASE_ASSOCIATED_PERSONS{
 DELETE FROM case_associated_persons WHERE offender_book_id =:p_offender_book_id AND case_id =:p_case_id
 }
 
 DELETE_OFFENDER_CASE_ASSOCIATIONS{
 DELETE FROM offender_case_associations  WHERE offender_book_id =:p_offender_book_id  AND case_id =:p_case_id
 }
 
 DELETE_OFFENDER_CASE_IDENTIFIERS{
  DELETE FROM offender_case_identifiers WHERE case_id =:p_case_id
 }
 
 DELETE_OFFENDER_CASE_STATUSES{
 DELETE FROM offender_case_statuses WHERE case_id =:p_case_id
 }
 DELETE_OFFENDER_SENT_COND_STATUSES{
DELETE FROM offender_sent_cond_statuses WHERE offender_sent_condition_id =:p_sent_condition_id
}
DELETE_OFFENDER_SENT_CONDITIONS{
DELETE FROM offender_sent_conditions WHERE offender_book_id =:p_offender_book_id  AND sentence_seq =:p_sentence_seq
AND offender_sent_condition_id =:p_sent_condition_id
}
DELETE_SENTENCE_CHARGES{
DELETE FROM offender_sentence_charges
	    WHERE offender_book_id =:p_offender_book_id
	      AND offender_charge_id =:p_offender_charge_id
	      AND sentence_seq =:p_sentence_seq
}
DELETE_OFFENDER_SENTENCES{
DELETE FROM offender_sentences WHERE offender_book_id = :p_offender_book_id AND sentence_seq = :p_sentence_seq
}
GET_COUNT_SELECT{
 SELECT COUNT (*)  FROM link_case_txns  WHERE offender_charge_id = :p_offender_charge_id
  } 
  
 EXIST_CUR{
         SELECT 'X'
           FROM offender_sentence_charges
          WHERE offender_book_id = :p_offender_book_id
            AND offender_charge_id = :p_offender_charge_id
            }
   
 EXIST_FINE_SENT{      
 SELECT COUNT (*)  FROM offender_sentences   WHERE offender_book_id = :p_offender_book_id  AND sentence_calc_type = 'A/FINE'
 }
  OFFENDER_FINE_PAYMENTS{          
  DELETE FROM offender_fine_payments WHERE offender_book_id = :p_offender_book_id
 }

 SENTENCE_SEQ_QUERY{
          SELECT :P_OFFENDER_BOOK_ID offender_book_id,
                :P_SENTENCE_SEQ sentence_seq,
                osc.offender_charge_id offender_charge_id,
              --  offense_code_description(off_chg.offence_code, off_chg.statute_code) offence_description,
              (SELECT description
						FROM offences
						WHERE
					offence_code = off_chg.offence_code AND
						statute_code = off_chg.statute_code) as offence_description,
                to_number(to_char(nvl(osc.modify_datetime, osc.create_datetime), 'DDHH24MISSFF4')) check_sum
           FROM offender_charges          off_chg,
                offender_sentence_charges osc
          WHERE osc.offender_book_id = :P_OFFENDER_BOOK_ID
            AND osc.sentence_seq = :P_SENTENCE_SEQ
            AND osc.offender_charge_id = off_chg.offender_charge_id;
 }
 
 
TAG_REFERENCE_CODES_GETDESCCODE{ 
 SELECT :p_offender_book_id offender_book_id, offender_sent_condition_id, :p_sentence_seq sentence_seq, description, LENGTH, length_unit, start_date, tag_reference_codes_getdesccode('ACTIVE_TYPE', condition_status ), long_comment_text, (TO_CHAR(coalesce(osc.modify_datetime, osc.create_datetime ), 'DDHH24MISSFF4' ) )::numeric check_sum FROM offender_sent_conditions osc, community_conditions cc WHERE osc.sentence_seq = :p_sentence_seq AND osc.offender_book_id = :p_offender_book_id AND cc.comm_condition_type = osc.comm_condition_type AND cc.comm_condition_code = osc.comm_condition_code
}