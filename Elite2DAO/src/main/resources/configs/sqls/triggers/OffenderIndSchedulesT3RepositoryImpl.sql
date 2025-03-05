OFFENDER_IND_SCHEDULE_T3_GET_V_NUM {
      SELECT count(*) 
      FROM   offender_ind_schedules
      WHERE  offender_Book_ID = :OFFENDER_BOOK_ID
      AND    Event_Type       = :EVENT_TYPE
      AND    Reference_ID     = :REFERENCE_ID
}