AGENCY_INCIDENT_PARTIES_T1_AGENCY_INCIDENT_PARTIES{
SELECT count(*) FROM   agency_incident_parties  WHERE  offender_book_id = :offenderBookId AND    agency_incident_id = :agencyIncidentId
}