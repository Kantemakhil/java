--S4-19897

--1
--Community Case Management → Demographics 
--The Keep Separates screen is at the bottom, below the maintenance sub-menu. Please move it above the maintenance.

UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = (SELECT COALESCE(MAX(sort_order), 0) + 1 
                     FROM menu_securities 
                    WHERE parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Demographics' 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management')
                                             )
                   )
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OIDONONA'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Demographics' 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = (SELECT COALESCE(MAX(sort_order), 0) + 1 
                     FROM menu_securities 
                    WHERE parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Demographics' 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management')
                                             )
                   )
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Maintenance'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Demographics' 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );     

--2				  
--Community Case Management → Assessments
--There is a non-associations menu item at the bottom of this menu. Please remove it. 				  

DELETE FROM  menu_securities 
 WHERE menu_id = (SELECT menu_id  
                    FROM menu_securities 
                   WHERE module_name = 'OIDONONA' 
                    AND parent_menu_id = (SELECT menu_id 
                                            FROM menu_securities 
                                           WHERE menu_item = 'Assessments' and module_name IS NULL
                                            AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management')
                                         ) 
                  );

--3                       
--Community Case Management → Case Management
--Please set the screens in the following order:	

--Contact Logs (OCDCLOGS)
--Scheduler (OCDCSCH)
--Case Plan (OCDIPLAN)
--Substance Abuse (OCDSABUS)
--Substance Testing (OIDSTEST)
--Offender Calendar (CALSCH)

--Inquiry
----Case Note Inquiry (OCIOCNOT)
----Case Plan Inquiry (OCIIPLAN)
----Office/Officer Diary (OCIDIARY)

--Maintenance
----Maintain Case Notes (OCMWORKS)
----Maintain Schedulers (OCMEVENT)
----Case Plan Review Periods (OCMCPREV)

--The Keep Separate and Community Non-Associations menu items should be removed. 	

DELETE FROM  menu_securities 
 WHERE menu_id IN (SELECT menu_id  
                    FROM menu_securities 
                   WHERE module_name = 'OIDONONA' 
                    AND parent_menu_id = (SELECT menu_id 
                                            FROM menu_securities 
                                           WHERE menu_item = 'Case Management' and module_name IS NULL  
                                            AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management')
                                         ) 
                  );	
				  
				  
DELETE FROM  menu_securities 
 WHERE menu_id = (SELECT menu_id  
                    FROM menu_securities 
                   WHERE module_name = 'OCDVTEAM' 
                    AND parent_menu_id = (SELECT menu_id 
                                            FROM menu_securities 
                                           WHERE menu_item = 'Case Management' and module_name IS NULL  
                                            AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management')
                                         ) 
                  );	
				  				   			  
				  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 7 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'CALSCH'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Case Management' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 8 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Inquiry' and module_name IS NULL  
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Case Management' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );                             
                 
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 9 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Maintenance' and module_name IS NULL  
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Case Management' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );           
				  
				  
--4
--Community Case Management → Court Cases & Orders
--Please rename this menu to Community Case Management → Legals / Sentence Calculation

--Please configure this menu as follows:
--Legal Orders (OCDLEGLO)
--Non-Custodial Orders (OCDNCODE)
--Charge Summary (OCDCHGSU)
--Legal Summary (OCDLEGLS)
--Court Events (OIDCRTEV)
--Sanctions and Violations (OSANVIOS) - this menu item should be renamed to Sanction Management. 
--Court Reports (OCDPSREP)
--Court Actions (OCDENFOR)

--Maintenance
----Maintenance sceens can be left as they are. 	

UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 1 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDLEGLO'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );      
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 2 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDNCODE'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  ); 
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 3 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDCHGSU'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 4 
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDLEGLS'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );   
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 5  
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OIDCRTEV'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 6  
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OSANVIOS'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  ); 
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 7  
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDPSREP'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );          
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 8   
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDENFOR'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );      
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 9    
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDCLIST'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );    
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 10    
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Maintenance' AND module_name IS NULL 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );    
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,menu_item = 'Sanction Management'  
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OSANVIOS'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  ); 
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,menu_item = 'Legals / Sentence Calculation'  
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Court Cases & Orders' and module_name IS NULL  
                      AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management')                                            
                  );            

--5
--Community Case Management → Community Service
--Please re-arrange this menu as follows:
--Community Service Projects (OCDUPROJ)
--Community Service Attendance (OCDUATTE)
--Community Service Project Inquiry (OCSPROIN)

--Maintenance
---Maintain Community Service Projects (OCMSUWPJ)

UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 1    
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDUPROJ'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Community Service' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );    
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 2    
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDUATTE'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Community Service' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  ); 
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 3    
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCSPROIN'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Community Service' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (SELECT menu_id 
                              FROM menu_securities 
                             WHERE menu_item = 'Community Service' and module_name IS NULL  
                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                            )
                    , 'Maintenance', NULL, 4, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);
                   
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,parent_menu_id = (SELECT menu_id 
                          FROM menu_securities 
                         WHERE menu_item = 'Maintenance' and module_name IS NULL  
                           AND parent_menu_id = (SELECT menu_id 
                                                   FROM menu_securities 
                                                  WHERE menu_item = 'Community Service' and module_name IS NULL  
                                                    AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                                )
                       )   
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCMSUWPJ'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Community Service' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );                     
                
--6			
--Community Case Management → Programs and Services
--Please re-arrange this menu as follows:
--Resource Directory (OCISCATA)
--Offender Programs and Evaluation Measures (OCDPROGR)
--Accredited Program Attendance (OCDPATTE)
--Offender Specialized Programs (OCDXPROG)
--Offender Specialized Programs Inquiry (OCSPROGR)

--Maintenance
----Maintain Services (OCMSERVI)
----Maintain Accredited Programs (OCMSVACP)
----Maintain Specialized Programs (OCMXPROG)
----Maintain Specialized Programs Status (OCMXPSTM)		
		
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 1    
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCISCATA'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 2     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDPROGR'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  ); 
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 3     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDPATTE'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );     
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 4     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDXPROG'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );    
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 5     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCSPROGR'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );   
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 6     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Maintenance' and module_name IS NULL  
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 4    
     ,parent_menu_id = (SELECT menu_id 
                          FROM menu_securities 
                         WHERE menu_item = 'Maintenance' and module_name IS NULL  
                           AND parent_menu_id = (SELECT menu_id 
                                                   FROM menu_securities 
                                                  WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                                    AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )                     
                       )
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCMXPSTM'
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                            )
                  );  
                  
DELETE FROM menu_securities     
  WHERE menu_id = ( SELECT menu_id 
                   FROM menu_securities 
                  WHERE module_name = 'OCMSUWPJ' 
                    AND parent_menu_id = (SELECT menu_id 
                                           FROM menu_securities 
                                          WHERE menu_item = 'Maintenance' and module_name IS NULL  
                                           AND parent_menu_id = (SELECT menu_id 
                                                                   FROM menu_securities 
                                                                  WHERE menu_item = 'Programs & Services' and module_name IS NULL  
                                                                    AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Community Case Management') 
                                                                )
                                          ) 
                  );        
				  
--7
--Please add the following screen to the System Administration → System Setup menu:
--Maintain Agencies/Corporates (OUMAGENC)

INSERT INTO OMS_OWNER.MENU_SECURITIES(MENU_ID, PARENT_MENU_ID, MENU_ITEM, MODULE_NAME, SORT_ORDER, CREATE_DATETIME, CREATE_USER_ID, MODIFY_DATETIME, MODIFY_USER_ID, SEAL_FLAG)
VALUES(nextval('MENU_ID'), (SELECT menu_id 
                              FROM menu_securities 
                             WHERE menu_item = 'System Setup'  and module_name IS NULL 
                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'System Administration')
                            )
                    , 'Maintain Agencies/Corporates', 'OUMAGENC', 99, current_timestamp, 'OMS_OWNER', current_timestamp, NULL, NULL);		

--8
--Inmate Case Management → Legals/Sentence Calculation 
--Please re-arrange this menu as follows:
--Legal Orders (OCDLEGLO)
--Non-Custodial Orders (OCDNCODE)
--Charge Summary (OCDCHGSU)
--Legal Summary (OCDLEGLS)
--Court Events (OIDCRTEV)
--Court Schedules (OIICMOCI)
--Holds/Warrants/Detainers (OIDHWDET)
--Court Diary (OCDCLIST)

--Maintenance
--Maintenance sceens can be left as they are. 

DELETE FROM menu_securities 
 WHERE module_name IN ('OCDPSREP', 'OSANVIOS', 'OCDENFOR', 'OCDLEGLN')  
   AND parent_menu_id = (SELECT menu_id 
                           FROM menu_securities 
                          WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                            AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                        );  

UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 1     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDLEGLO' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );  
                 
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 2     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDNCODE' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );    
                 
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 3     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDCHGSU' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );     
                 
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 4     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDLEGLS' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );                 
                       
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 5     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OIDCRTEV' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );     
				  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 6     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OIICMOCI' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );  

UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 7     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OIDHWDET' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );    

UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 8     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE module_name = 'OCDCLIST' 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );    				  
                  
UPDATE menu_securities 
  SET modify_user_id = 'OMS_OWNER' 
     ,modify_datetime = current_timestamp 
     ,sort_order = 20     
  WHERE menu_id = (SELECT menu_id 
                     FROM menu_securities 
                    WHERE menu_item = 'Maintenance' AND module_name IS NULL 
                      AND parent_menu_id = (SELECT menu_id 
                                              FROM menu_securities 
                                             WHERE menu_item = 'Legals/Sentence Calculation'  and module_name IS NULL 
                                               AND parent_menu_id = (SELECT menu_id FROM menu_securities WHERE menu_item = 'Inmate Case Management')
                                            )
                  );         				