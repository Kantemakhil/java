CREATE OR REPLACE FUNCTION oms_owner.tag_adjudication_gethearingtype ( p_hearing_id oic_hearings.oic_hearing_id%TYPE) RETURNS OIC_HEARINGS.OIC_HEARING_TYPE%TYPE AS $body$
DECLARE

      l_hearing_type   oic_hearings.oic_hearing_type%TYPE;
      hear_cur CURSOR FOR
         SELECT oic_hearing_type
           FROM oic_hearings
          WHERE oic_hearing_id = p_hearing_id;

BEGIN
      OPEN hear_cur;
      FETCH hear_cur
       INTO l_hearing_type;
      IF not FOUND
      THEN
         RAISE no_data_found;                   -- Caller must be made aware.
      END IF;
      CLOSE hear_cur;
      RETURN( l_hearing_type );
   EXCEPTION
      WHEN OTHERS
      THEN
         IF exists(SELECT * FROM pg_cursors WHERE name = 'hear_cur')
         THEN
            CLOSE hear_cur;
         END IF;
         WHEN OTHERS THEN
         raise '% %',sqlerrm,sqlstate;
   END;
$body$
LANGUAGE PLPGSQL
;