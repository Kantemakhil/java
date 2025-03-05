AGENCY_INCIDENT_CHARGES_T1_LIDS_CUR{
SELECT coalesce ((MAX (a.lids_charge_number) + 1),TO_NUMBER (TO_CHAR (current_timestamp , 'YY') || '01', '99G999D9S') )
        FROM agency_incident_charges a,agency_incident_parties b WHERE a.agency_incident_id = b.agency_incident_id
         AND a.party_seq = b.party_seq AND b.offender_book_id = 
         (SELECT offender_book_id FROM agency_incident_parties WHERE agency_incident_id = :agencyIncidentId  AND party_seq = :partySeq)

}