CREATE OR REPLACE FUNCTION oms_owner.elite_generic_log()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
BEGIN
  IF TG_OP = 'DELETE' THEN
     INSERT INTO oms_owner.elite_generic_log_remote VALUES (now(), 'D', session_user, TG_TABLE_NAME, NULL , to_jsonb(OLD));
  ELSIF TG_OP = 'UPDATE' THEN 
     INSERT INTO oms_owner.elite_generic_log_remote VALUES (now(), 'U', session_user, TG_TABLE_NAME, to_jsonb(NEW),to_jsonb(OLD));
  ELSIF TG_OP = 'INSERT' THEN
     INSERT INTO oms_owner.elite_generic_log_remote VALUES (now(), 'I', session_user, TG_TABLE_NAME, to_jsonb(NEW), NULL);
  ELSIF TG_OP = 'TRUNCATE' THEN
     INSERT INTO oms_owner.elite_generic_log_remote VALUES (now(), 'T', session_user, TG_TABLE_NAME, NULL, NULL);	
  ELSE 
     NULL;
  END IF;
  
  RETURN NULL;
 
EXCEPTION 
  WHEN OTHERS THEN  
	  IF TG_OP = 'DELETE' THEN
		 INSERT INTO oms_owner.elite_generic_log VALUES (now(), 'D', session_user, TG_TABLE_NAME, NULL , to_jsonb(OLD));
	  ELSIF TG_OP = 'UPDATE' THEN
		 INSERT INTO oms_owner.elite_generic_log VALUES (now(), 'U', session_user, TG_TABLE_NAME,to_jsonb(NEW),to_jsonb(OLD));
      ELSIF TG_OP = 'INSERT' THEN
		 INSERT INTO oms_owner.elite_generic_log VALUES (now(), 'I', session_user, TG_TABLE_NAME,to_jsonb(NEW), NULL); 
      ELSIF TG_OP = 'TRUNCATE' THEN
         INSERT INTO oms_owner.elite_generic_log VALUES (now(), 'T', session_user, TG_TABLE_NAME, NULL, NULL);			 
      ELSE 
         NULL;		
	  END IF;
	  
	  RETURN NULL;
    
  
END;
$function$
;

