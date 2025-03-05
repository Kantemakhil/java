CREATE OR REPLACE FUNCTION oms_owner.tag_prg_prg_event_type(p_program_id bigint)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 

      l_program_category  program_services.program_category%TYPE;
      l_parent_program_id program_services.parent_program_id%TYPE;
   BEGIN


    
      SELECT program_category,
             parent_program_id
        INTO l_program_category,
             l_parent_program_id
        FROM program_services
       WHERE program_id = p_program_id;

      IF (l_program_category IS NOT NULL) THEN
         RETURN l_program_category;
      ELSE
         RETURN tag_prg_prg_event_type(l_parent_program_id);
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         RETURN NULL;

   END;
$function$
;