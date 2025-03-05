SELECT_QUERY{
SELECT COALESCE (MAX (lids_sanction_number), 0) + 1
        FROM offender_oic_sanctions
       WHERE offender_book_id = :offender_book_id
         AND oic_incident_id = :oic_incident_id
         }