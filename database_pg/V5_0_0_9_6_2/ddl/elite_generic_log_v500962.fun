CREATE OR REPLACE FUNCTION oms_owner.elite_generic_log()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$ 
DECLARE 
   lv_offender_book_id  INTEGER;
   lv_offender_id       INTEGER;
   lv_root_offender_id  INTEGER;
BEGIN
  IF TG_OP = 'DELETE' THEN
  
	 lv_offender_book_id := (TRIM(to_jsonb(OLD)->>'offender_book_id'))::int; 	 
	 IF lv_offender_book_id IS NOT NULL THEN 	 
	     WITH  with_addition_details AS (SELECT offender_id_display, booking_no FROM oms_owner.v_audit_bkg_details WHERE oms_owner.v_audit_bkg_details .offender_book_id = lv_offender_book_id )
	 	 INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	   VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD),(select offender_id_display from with_addition_details),(select booking_no from with_addition_details));  
	 ELSE 	 
	    lv_offender_id := (TRIM(to_jsonb(OLD)->>'offender_id'))::int;		
	    IF lv_offender_id IS NOT NULL THEN 		
		   --addressing financial tables here also as they have a column called offender_id that actually represents the root_offender_id
	       --because all offenders have one record where offender_id = root_offender_id, we can search for offender_id only 
	       WITH  with_addition_details AS ( SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.offender_id = lv_offender_id ) 
	 	   INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	      VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL);       
		ELSE 		
		  lv_root_offender_id := (TRIM(to_jsonb(OLD)->>'root_offender_id'))::int;		
	      IF lv_root_offender_id IS NOT NULL THEN 
	         --there can be multiple aliases with the same root_offender_id, limit to one record
	         WITH  with_addition_details AS (SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.root_offender_id = lv_root_offender_id LIMIT 1 )
	 	     INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL);   
		  ELSE 
	 	     INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD), NULL, NULL);    		  
	      END IF;
        END IF;		  
	 END IF;   
  
  ELSIF TG_OP = 'UPDATE' THEN 
 
	 lv_offender_book_id := (TRIM(to_jsonb(NEW)->>'offender_book_id'))::int; 	 
	 IF lv_offender_book_id IS NOT NULL THEN 	 
	     WITH  with_addition_details AS (SELECT offender_id_display, booking_no FROM oms_owner.v_audit_bkg_details WHERE oms_owner.v_audit_bkg_details .offender_book_id = lv_offender_book_id )
	 	 INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	   VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD),(select offender_id_display from with_addition_details),(select booking_no from with_addition_details)); 
	 ELSE 	 
	    lv_offender_id := (TRIM(to_jsonb(NEW)->>'offender_id'))::int;		
	    IF lv_offender_id IS NOT NULL THEN 		
	       WITH  with_addition_details AS ( SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.offender_id = lv_offender_id ) 
	 	   INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	      VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL);       
		ELSE 		
		  lv_root_offender_id := (TRIM(to_jsonb(NEW)->>'root_offender_id'))::int;		
	      IF lv_root_offender_id IS NOT NULL THEN 
	         --there can be multiple aliases with the same root_offender_id, limit to one record
	         WITH  with_addition_details AS (SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.root_offender_id = lv_root_offender_id LIMIT 1 )
	 	     INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL); 
		  ELSE 
	 	     INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD), NULL, NULL); 		  
	      END IF;		
		END IF;
	 END IF;   		 
		 		 		 
  ELSIF TG_OP = 'INSERT' THEN

	 lv_offender_book_id := (TRIM(to_jsonb(NEW)->>'offender_book_id'))::int; 	 
	 IF lv_offender_book_id IS NOT NULL THEN 	 
	     WITH  with_addition_details AS (SELECT offender_id_display, booking_no FROM oms_owner.v_audit_bkg_details WHERE oms_owner.v_audit_bkg_details .offender_book_id = lv_offender_book_id )
	 	 INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	   VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL,(select offender_id_display from with_addition_details),(select booking_no from with_addition_details)); 
	 ELSE 	 
	    lv_offender_id := (TRIM(to_jsonb(NEW)->>'offender_id'))::int;		
	    IF lv_offender_id IS NOT NULL THEN 		
	       WITH  with_addition_details AS ( SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.offender_id = lv_offender_id ) 
	 	   INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	      VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL,(select offender_id_display from with_addition_details), NULL);       
		ELSE 		
		  lv_root_offender_id := (TRIM(to_jsonb(NEW)->>'root_offender_id'))::int;		
	      IF lv_root_offender_id IS NOT NULL THEN 
	         --there can be multiple aliases with the same root_offender_id, limit to one record
	         WITH  with_addition_details AS (SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.root_offender_id = lv_root_offender_id LIMIT 1 )
	 	     INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL,(select offender_id_display from with_addition_details), NULL); 
		  ELSE 
	 	     INSERT INTO oms_owner.elite_generic_log_remote (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL, NULL, NULL); 		  
	      END IF;
        END IF;		  
	 END IF;  
		 		 
  ELSIF TG_OP = 'TRUNCATE' THEN
     INSERT INTO oms_owner.elite_generic_log_remote  (audit_id, audittime, action, username, table_name, row_data_new, row_data_old, offender_id_display, booking_no) 
	    VALUES (nextval('iudsql_audit_id_seq'), now(), 'T', session_user, TG_TABLE_NAME, NULL, NULL, NULL, NULL);				
  ELSE 
     NULL;
  END IF;
  
  RETURN NULL;
 
EXCEPTION 
  WHEN OTHERS THEN  
  IF TG_OP = 'DELETE' THEN
  
	 lv_offender_book_id := (TRIM(to_jsonb(OLD)->>'offender_book_id'))::int; 	 
	 IF lv_offender_book_id IS NOT NULL THEN 	 
	     WITH  with_addition_details AS (SELECT offender_id_display, booking_no FROM oms_owner.v_audit_bkg_details WHERE oms_owner.v_audit_bkg_details .offender_book_id = lv_offender_book_id )
	 	 INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	   VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD),(select offender_id_display from with_addition_details),(select booking_no from with_addition_details));  
	 ELSE 	 
	    lv_offender_id := (TRIM(to_jsonb(OLD)->>'offender_id'))::int;		
	    IF lv_offender_id IS NOT NULL THEN 		
		   --addressing financial tables here also as they have a column called offender_id that actually represents the root_offender_id
	       --because all offenders have one record where offender_id = root_offender_id, we can search for offender_id only 
	       WITH  with_addition_details AS ( SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.offender_id = lv_offender_id ) 
	 	   INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	      VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL);       
		ELSE 		
		  lv_root_offender_id := (TRIM(to_jsonb(OLD)->>'root_offender_id'))::int;		
	      IF lv_root_offender_id IS NOT NULL THEN 
	         --there can be multiple aliases with the same root_offender_id, limit to one record
	         WITH  with_addition_details AS (SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.root_offender_id = lv_root_offender_id LIMIT 1 )
	 	     INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL);   
		  ELSE 
	 	     INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'D', COALESCE(to_jsonb(OLD)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, NULL , to_jsonb(OLD), NULL, NULL);    		  
	      END IF;		
		END IF;  
	 END IF;   
  
  ELSIF TG_OP = 'UPDATE' THEN 
 
	 lv_offender_book_id := (TRIM(to_jsonb(NEW)->>'offender_book_id'))::int; 	 
	 IF lv_offender_book_id IS NOT NULL THEN 	 
	     WITH  with_addition_details AS (SELECT offender_id_display, booking_no FROM oms_owner.v_audit_bkg_details WHERE oms_owner.v_audit_bkg_details .offender_book_id = lv_offender_book_id )
	 	 INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	   VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD),(select offender_id_display from with_addition_details),(select booking_no from with_addition_details)); 
	 ELSE 	 
	    lv_offender_id := (TRIM(to_jsonb(NEW)->>'offender_id'))::int;		
	    IF lv_offender_id IS NOT NULL THEN 		
	       WITH  with_addition_details AS ( SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.offender_id = lv_offender_id ) 
	 	   INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	      VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL);       
		ELSE 		
		  lv_root_offender_id := (TRIM(to_jsonb(NEW)->>'root_offender_id'))::int;		
	      IF lv_root_offender_id IS NOT NULL THEN 
	         --there can be multiple aliases with the same root_offender_id, limit to one record
	         WITH  with_addition_details AS (SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.root_offender_id = lv_root_offender_id LIMIT 1 )
	 	     INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD),(select offender_id_display from with_addition_details), NULL); 
		  ELSE 
	 	     INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'U', COALESCE(to_jsonb(NEW)->>'modify_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), to_jsonb(OLD), NULL, NULL); 		  
	      END IF;
        END IF;		  
	 END IF;   		 
		 		 		 
  ELSIF TG_OP = 'INSERT' THEN

	 lv_offender_book_id := (TRIM(to_jsonb(NEW)->>'offender_book_id'))::int; 	 
	 IF lv_offender_book_id IS NOT NULL THEN 	 
	     WITH  with_addition_details AS (SELECT offender_id_display, booking_no FROM oms_owner.v_audit_bkg_details WHERE oms_owner.v_audit_bkg_details .offender_book_id = lv_offender_book_id )
	 	 INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	   VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL,(select offender_id_display from with_addition_details),(select booking_no from with_addition_details)); 
	 ELSE 	 
	    lv_offender_id := (TRIM(to_jsonb(NEW)->>'offender_id'))::int;		
	    IF lv_offender_id IS NOT NULL THEN 		
	       WITH  with_addition_details AS ( SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.offender_id = lv_offender_id ) 
	 	   INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	      VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL,(select offender_id_display from with_addition_details), NULL);       
		ELSE 		
		  lv_root_offender_id := (TRIM(to_jsonb(NEW)->>'root_offender_id'))::int;		
	      IF lv_root_offender_id IS NOT NULL THEN 
	         --there can be multiple aliases with the same root_offender_id, limit to one record
	         WITH  with_addition_details AS (SELECT oms_owner.offenders.offender_id_display FROM oms_owner.offenders WHERE oms_owner.offenders.root_offender_id = lv_root_offender_id LIMIT 1 )
	 	     INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL,(select offender_id_display from with_addition_details), NULL); 
		  ELSE 
	 	     INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old,offender_id_display,booking_no)
    	        VALUES (nextval('iudsql_audit_id_seq'), now(), 'I', COALESCE(to_jsonb(NEW)->>'create_user_id', 'NOUSERVALUE'), TG_TABLE_NAME, to_jsonb(NEW), NULL, NULL, NULL); 		  
	      END IF;	
        END IF;		  
	 END IF;  
		 		 
     ELSIF TG_OP = 'TRUNCATE' THEN
         INSERT INTO oms_owner.elite_generic_log (audit_id, audittime, action, username, table_name, row_data_new, row_data_old, offender_id_display, booking_no) 
		   VALUES (nextval('iudsql_audit_id_seq'), now(), 'T', session_user, TG_TABLE_NAME, NULL, NULL, NULL, NULL);			 
     ELSE 
        NULL;		
	 END IF;
	  
	 RETURN NULL;
    
  
END;
$function$
;

