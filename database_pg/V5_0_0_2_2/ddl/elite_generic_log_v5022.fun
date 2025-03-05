CREATE OR REPLACE FUNCTION oms_owner.elite_generic_log()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
BEGIN
  IF TG_OP = 'DELETE' THEN
    INSERT INTO "oms_owner".elite_generic_Log VALUES (now(), 'D', session_user, TG_TABLE_NAME, NULL , to_jsonb(OLD));
  ELSIF TG_OP = 'UPDATE' THEN
    INSERT INTO "oms_owner".elite_generic_Log VALUES (now(), TG_OP::char , session_user, TG_TABLE_NAME,to_jsonb(NEW),to_jsonb(OLD));
  ELSE 
    INSERT INTO "oms_owner".elite_generic_Log VALUES (now(),TG_OP::char , session_user, TG_TABLE_NAME,to_jsonb(NEW), NULL);
  END IF;
  RETURN NULL;
END;
$function$
;

