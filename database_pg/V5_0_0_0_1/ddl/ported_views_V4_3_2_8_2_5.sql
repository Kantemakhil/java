
-----------------------------------------------------------------------------------------------
----------------------------------3.2.7.1------------------------------------------------------
-----------------------------------------------------------------------------------------------

  CREATE OR REPLACE VIEW OMS_OWNER.V_PROGRAM_PROVIDERS (PARTY_ID, PARTY_CODE, PARTY_NAME, PARTY_CLASS, LAST_NAME, FIRST_NAME) AS 
  (SELECT
/* MODIFICATION HISTORY
   Person       Date        Version       Comments
   David Ng     03/01/2006  2.0           NOMIS project(10.2.0)
   Anwesh       10/03/2022  3.0           Updated to remove old teams and include Automation teams
*/
           person_id, NULL, last_name || ', ' || first_name, 'PER', last_name,
           first_name
      FROM persons
    UNION ALL
    SELECT staff_id, NULL, last_name || ', ' || first_name, 'STF', last_name,
           first_name
      FROM staff_members
    UNION ALL
    SELECT NULL, agy_loc_id, description, 'AGY', NULL, NULL
      FROM agency_locations
     WHERE agy_loc_id NOT IN ('OUT', 'TRN')
    UNION ALL
    SELECT at.team_id, at.team_code, at.description, 'TEAM', NULL, NULL
      FROM automation_teams at
     WHERE EXISTS (
              SELECT 'x'
                FROM staff_members ssm, team_staff_members tsm
               WHERE tsm.active_flag = 'Y'
                 AND ssm.user_id = USER
                 AND ssm.staff_id = tsm.staff_id
                 AND at.team_id = tsm.team_id)
    UNION ALL
    SELECT corporate_id, NULL, corporate_name, 'CORP', NULL, NULL
      FROM corporates)
 ;

-----------------------------------------------------------------------------------------------
----------------------------------others------------------------------------------------------
-----------------------------------------------------------------------------------------------


CREATE OR REPLACE VIEW oms_owner.v_pims_name_search (offender_id, offender_id_display, offender_book_id, last_name, first_name, middle_name, birth_date, active_flag, living_unit_id, root_offender_id, prison_location) AS SELECT DISTINCT
      /* =========================================================
          Version Number = 6.1.0.0  Date Modified = 02/26/2002
          ========================================================= */
      /* MODIFICATION HISTORY
        Person     	 Date       	Version     	   Comments
        ---------    ------     	------------  	 ------------------------------
        Steve        25-Nov-2008  6.1.0.2          Tr#5518 Replaced living unit description
                                                   with prison location.
        Steve        19-Nov-2008  6.1.0.1          Tr#5518 Added living unit description
        Jagdeep      26-FEB-2002  6.1.0.0          TR#11293.Duplicate records on OCINAMES.
                                                   Added distinct clause in the select statement
     */
        OFF_NAME.OFFENDER_ID
       ,OFF_NAME.OFFENDER_ID_DISPLAY
       ,OFF_BKG.OFFENDER_BOOK_ID
       ,OFF_NAME.LAST_NAME
       ,OFF_NAME.FIRST_NAME
       ,OFF_NAME.MIDDLE_NAME
       ,OFF_NAME.BIRTH_DATE
       ,OFF_BKG.ACTIVE_FLAG
       ,OFF_BKG.LIVING_UNIT_ID
       ,OFF_NAME.ROOT_OFFENDER_ID
       ,SUBSTR(Tag_Header_get_prison_location( OFF_BKG.active_flag,
                                                OFF_BKG.community_active_flag,
                                                OFF_BKG.intake_agy_loc_id,
                                                OFF_BKG.agy_loc_id,
                                                OFF_BKG.living_unit_id,
                                                OFF_BKG.agency_iml_id,
                                                OFF_BKG.offender_book_id ),
                                                0, 105 ) prison_location
   FROM offender_bookings  OFF_BKG,
        offenders  OFF_NAME,
        caseload_agency_locations CAL,
        staff_members STAFF,
        offender_booking_agy_locs OBAL
  WHERE OFF_NAME.offender_id  =  OFF_BKG.offender_id
    AND CAL.caseload_id = STAFF.working_caseload_Id
    AND upper(STAFF.USER_ID) = upper(user)
    AND OBAL.agy_loc_id = CAL.agy_loc_id
    AND coalesce(OBAL.removed_date::text, '') = ''
    AND OBAL.Offender_book_ID = OFF_BKG.Offender_Book_ID

;