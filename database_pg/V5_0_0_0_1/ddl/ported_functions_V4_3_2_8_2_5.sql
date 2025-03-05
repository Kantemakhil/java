-----------------------------------------------------------------------------------------------
----------------------------------3.2.1.0------------------------------------------------------
-----------------------------------------------------------------------------------------------

--missing TAG_ASS_TREAT_PROTS and ATP_VR_IUD --to talk to Akhil --moved to Java

-----------------------------------------------------------------------------------------------
----------------------------------3.2.7.1------------------------------------------------------
-----------------------------------------------------------------------------------------------

--missing tag_prg --to talk to Akhil --moved to Java

-----------------------------------------------------------------------------------------------
----------------------------------3.2.8.0------------------------------------------------------
-----------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION oms_owner.TAG_WORKFLOW_ADM_sort_by_team_name ( P_TEAM_ID TEAMS.TEAM_ID%TYPE ) RETURNS varchar AS $body$
DECLARE

      L_TEAM_NAME   TEAMS.DESCRIPTION%TYPE;
      L_NUM         bigint;

BEGIN
      IF (P_TEAM_ID IS NOT NULL AND P_TEAM_ID::text <> '')
      THEN
         SELECT DESCRIPTION
           INTO STRICT L_TEAM_NAME
           FROM automation_teams
          WHERE TEAM_ID = P_TEAM_ID;
      END IF;
      RETURN( L_TEAM_NAME );
   EXCEPTION
      WHEN OTHERS
      THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;