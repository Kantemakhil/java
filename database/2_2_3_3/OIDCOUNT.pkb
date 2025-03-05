CREATE OR REPLACE PACKAGE BODY oidcount
IS
/*
|| Purpose: Package created for module OIDCOUNT
||
|| MODIFICATION HISTORY (Please put version history in a reverse-chronological order below)
||
|| ------------------------------------------------------------------------------------
|| Person        Date          Version    Comments
|| ------------  -----------   ---------  ---------------------------------------------
|| Syscon        16-SEP-2020   1.24       TAS-16  dbms_jobs.submit not executing
|| Nasir         28-NOV-2012   1.23       HPQC#18552  Modify set_temp_oidcount procedure.
|| Aditya	 25-SEP-2012   1.22	  Commented the condition "Outcome is null" to fix defect# 20191.
|| Himanshu      17-Jul-2012   1.21       HPQC#3728 Added function Show version and commented procedure show version
|| NIKO          08-SEP-2008   1.20       Modified the  PROCEDURE set_temp_oidcount as per 10G data model
|| EDWARD        27-FEB-2008   1.19       Added total_male_out, total_female_out, total_other_out to set_temp_oidcount.
|| EDWARD        28-Jan-2008   1.18       Added total_male, total_female, total_other to set_temp_oidcount.
|| Abu           13-Jan-2008   1.17       removed check on V$session for dead sessions.
||                                           Count Job will be terminated only if User wishes to Cancel the job
||                                           or Admin deletes record in Locked_Modules table.
|| Jason         15-Aug-2007   1.16       added logic for check_all_dead_jobs
|| Dragan        24-May-2007   1.15       added new column out_total and perform calculations for it
|| Abu           03-May-2007   1.14       performance fix
|| Abu           29-Mar-2007   1.12       performance fix
|| Abu           10-Dec-2006   1.10       modified to not retrieve zero counts
|| JASON         18-SEP-2006   1.7        Created Module specific Package
|| ====================================================================================
*/
   l_agy_loc_id                                 agency_count_types.agy_loc_id%TYPE;
   l_count_type_id                              agency_location_counts.count_type_id%TYPE;
   l_scheduled_time                             agency_count_types.scheduled_time%TYPE;
   l_reporting_loc_id                           agency_location_counts.reporting_loc_id%TYPE;
   l_location_id                                agency_reporting_locs.location3_id%TYPE;
   l_vrl_rec                                    v_reporting_locations%ROWTYPE;
   l_temp_oidcoun_rec                           temp_oidcount%ROWTYPE;
   l_report_count                               NUMBER;
   l_total                                      NUMBER;

   l_all_complete_flag                          VARCHAR2(1);
   -- ====================================================================================
   vcp_version                         CONSTANT VARCHAR2(60)
            := '1.24 16-SEP-2020';
   -- ====================================================================================
   /*PROCEDURE show_version
   IS
   BEGIN
      DBMS_OUTPUT.put_line ('==============================================');
      DBMS_OUTPUT.put ('Version : ');
      DBMS_OUTPUT.put_line (vcp_version);
      DBMS_OUTPUT.put_line ('==============================================');
   EXCEPTION
      WHEN OTHERS
      THEN
         DBMS_OUTPUT.put_line ('OmsErr-0001: SHOW_VERSION(). ' || SQLERRM);
   END show_version;*/--Commented by Himanshu HPQC#3728
   
   ----Added by Himanshu HPQC#3728	
	FUNCTION show_version
	  RETURN VARCHAR2
	IS
	BEGIN
	  RETURN vcp_version;
	END show_version;
  ----End by Himanshu
   PROCEDURE submit_count_job (
      p_session_id              NUMBER,
      p_agy_loc_id         IN   agency_count_types.agy_loc_id%TYPE,
      p_reporting_loc_id   IN   agency_location_counts.reporting_loc_id%TYPE,
      p_count_type_id      IN   agency_location_counts.count_type_id%TYPE,
      p_scheduled_time     IN   agency_count_types.scheduled_time%TYPE
   )
   IS
      l_job_number                                 NUMBER;
   BEGIN 
     /* TAS-16
      DBMS_JOB.submit (
         l_job_number,
         'oidcount.set_temp_oidcount(' ||
         p_session_id ||
         ',' ||
         '''' ||
         p_agy_loc_id ||
         '''' ||
         ',' ||
         p_reporting_loc_id ||
         ',' ||
         p_count_type_id ||
         ',' ||
         '''' ||
         p_scheduled_time ||
         '''' ||
         ');',
         SYSDATE,
         NULL,
         FALSE
      ); 
      
       COMMIT;
      */
      
      --TAS-16
      dbms_scheduler.create_job(job_name => 'INIT_COUNT_' || TO_CHAR(p_session_id),
                                job_type => 'PLSQL_BLOCK',
                                job_action => 'begin oidcount.set_temp_oidcount(' || p_session_id || ',' || '''' || p_agy_loc_id || '''' || ',' || p_reporting_loc_id || ',' || p_count_type_id || ',' || '''' ||  p_scheduled_time || '''' || '); end;',
                                start_date => NULL,
                                enabled => TRUE,
                                auto_drop => TRUE,
                                comments => 'OIDCOUNT session ' || TO_CHAR(p_session_id) || ' count type ' || TO_CHAR(p_count_type_id) || ' reporting loc id ' || TO_CHAR(p_reporting_loc_id)
                                );                                            
     
   EXCEPTION
      WHEN OTHERS
      THEN
         raise_application_error (-20009, 'Error at job submit: ' || SQLERRM);
   END submit_count_job;
   PROCEDURE cancel_count (p_session_id NUMBER)
   IS
      PRAGMA AUTONOMOUS_TRANSACTION;
   BEGIN
      INSERT INTO temp_oidcount
                  (session_id, agy_loc_id)
         SELECT p_session_id,
                'CANCEL'
           FROM dual
          WHERE NOT EXISTS (SELECT 1
                              FROM temp_oidcount
                             WHERE session_id = p_session_id
                               AND agy_loc_id = 'CANCEL');
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise_application_error (-20009, 'Error at cancel job: ' || SQLERRM);
   END cancel_count;
   PROCEDURE remove_oidcount_job (p_session_id NUMBER, p_user_id VARCHAR2)
   IS
      l_job                                        NUMBER;
      CURSOR l_current_job_cur (p_session_id VARCHAR2, p_user_id VARCHAR2)
      IS
         SELECT job
           FROM all_jobs
          WHERE log_user = p_user_id
            AND UPPER (REPLACE (what, ' ', NULL)) LIKE
                   'OIDCOUNT.SET_TEMP_OIDCOUNT(' || p_session_id || ',%';
                   
      --TAS-16             
      CURSOR l_scheduler_current_job_cur (p_session_id VARCHAR2, p_user_id VARCHAR2)
      IS
      SELECT job_name 
           FROM all_scheduler_jobs
          WHERE job_name = 'INIT_COUNT_' || p_session_id;
          
      lv_job_name VARCHAR2(1000);                                    
   BEGIN
      l_job := NULL;
      OPEN l_current_job_cur (p_session_id, p_user_id);
      FETCH l_current_job_cur INTO l_job;
      CLOSE l_current_job_cur;
      IF l_job IS NOT NULL
      THEN
         DBMS_JOB.remove (l_job);
         COMMIT;
      END IF;
      
      --TAS-16
      lv_job_name := NULL;
      OPEN l_scheduler_current_job_cur (TO_CHAR(p_session_id), p_user_id);
      FETCH l_scheduler_current_job_cur INTO lv_job_name; --TAS-16
      CLOSE l_scheduler_current_job_cur;
      IF lv_job_name IS NOT NULL
      THEN
         --won't work while running DBMS_SCHEDULER.DROP_JOB (job_name => lv_job_name); 
         cancel_count (p_session_id); --we cancel and the job will be dropped automatically;
         DBMS_LOCK.sleep (1);
      END IF;
      --end TAS-16
   EXCEPTION
      WHEN OTHERS
      THEN
         raise_application_error (-20009, 'Error at remove job: ' || SQLERRM);
   END remove_oidcount_job;
--
-- PROCEDURE to set temp_oidcount
--
   PROCEDURE set_temp_oidcount (
      p_session_id         IN   temp_oidcount.session_id%TYPE,
      p_agy_loc_id         IN   agency_count_types.agy_loc_id%TYPE,
      p_reporting_loc_id   IN   agency_location_counts.reporting_loc_id%TYPE,
      p_count_type_id      IN   agency_location_counts.count_type_id%TYPE,
      p_scheduled_time     IN   agency_count_types.scheduled_time%TYPE
   )
   IS
      CURSOR l_temp_count_cur (p_session_id NUMBER)
      IS
         SELECT ROWID,
                agy_loc_id,
                agy_seq,
                location_type,
                lowest_location_id,
                location_description,
                actual_count,
                reported_count,
					 out_total,
                count_type_id,
                reporting_loc_id,
								total_male,
								total_female,
								total_other,
								total_male_out,
								total_female_out,
								total_other_out
           FROM temp_oidcount
          WHERE session_id = p_session_id
            AND agy_loc_id <> 'CANCEL'
          ORDER BY location_type, list_seq;
      CURSOR l_count_init_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings
          WHERE active_flag = 'Y'
           -- AND agency_iml_id = p_location_id  --@@@ Nasir 29-NOV-2012 comment out
            AND agy_loc_id = p_agy_loc_id
            AND in_out_status = 'IN'
            --@@@ Nasir 29-NOV-2012 added below condition to check the child locations
            AND agency_iml_id IN (SELECT internal_location_id
                      FROM agency_internal_locations
                     CONNECT BY PRIOR internal_location_id = parent_internal_location_id
                     START WITH internal_location_id = p_location_id);
      CURSOR l_count_init_male_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            --AND ob.agency_iml_id = p_location_id   --@@@ Nasir 29-NOV-2012 comment out
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.in_out_status = 'IN'
	    AND ob.offender_id = ofd.offender_id
	    AND ofd.sex_code = 'M'
            --@@@ Nasir 29-NOV-2012 added below condition to check the child locations
            AND agency_iml_id IN (SELECT internal_location_id
                      FROM agency_internal_locations
                     CONNECT BY PRIOR internal_location_id = parent_internal_location_id
                     START WITH internal_location_id = p_location_id);

      CURSOR l_count_init_female_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            --AND ob.agency_iml_id = p_location_id   --@@@ Nasir 29-NOV-2012 comment out
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.in_out_status = 'IN'
	    AND ob.offender_id = ofd.offender_id
            AND ofd.sex_code = 'F'
            --@@@ Nasir 29-NOV-2012 added below condition to check the child locations
            AND agency_iml_id IN (SELECT internal_location_id
                      FROM agency_internal_locations
                     CONNECT BY PRIOR internal_location_id = parent_internal_location_id
                     START WITH internal_location_id = p_location_id);

      CURSOR l_count_init_other_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            --AND ob.agency_iml_id = p_location_id   --@@@ Nasir 29-NOV-2012 comment out
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.in_out_status = 'IN'
	    AND ob.offender_id = ofd.offender_id
            AND ofd.sex_code NOT IN ('M','F')
            --@@@ Nasir 29-NOV-2012 added below condition to check the child locations
            AND agency_iml_id IN (SELECT internal_location_id
                      FROM agency_internal_locations
                     CONNECT BY PRIOR internal_location_id = parent_internal_location_id
                     START WITH internal_location_id = p_location_id);

      CURSOR l_count_liv_unit_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings
          WHERE active_flag = 'Y'
            AND in_out_status = 'IN'
            AND agency_iml_id IS NULL
            AND agy_loc_id = p_agy_loc_id
            AND living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id);

      CURSOR l_count_liv_unit_male_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.in_out_status = 'IN'
            AND ob.agency_iml_id IS NULL
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id)
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code = 'M';

      CURSOR l_count_liv_unit_female_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.in_out_status = 'IN'
            AND ob.agency_iml_id IS NULL
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id)
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code = 'F';

      CURSOR l_count_liv_unit_other_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.in_out_status = 'IN'
            AND ob.agency_iml_id IS NULL
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id)
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code NOT IN ('M','F');

      CURSOR l_reported_count_cur (
         p_reporting_loc_id   NUMBER,
         p_count_type_id      NUMBER,
         p_agy_seq            NUMBER
      )
      IS
         SELECT DECODE (
                   alc.recount_total,
                   NULL, alc.reported_count,
                   alc.recount_total
                ),
                alc.date_submitted,
                alc.entered_by_userid
           FROM agency_location_counts alc
          WHERE reporting_loc_id = p_reporting_loc_id
            AND count_type_id = p_count_type_id
            AND agy_seq = p_agy_seq
            AND rcnt_in_progress_flag = 'N';
		--Dragan 24-may-2007
      CURSOR l_out_init_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings
          WHERE active_flag = 'Y'
            AND agency_iml_id = p_location_id
            AND agy_loc_id = p_agy_loc_id
            AND in_out_status = 'OUT';

--EDWARD 26-FEB-2008
            CURSOR l_total_male_out_init_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.agency_iml_id = p_location_id
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.in_out_status = 'OUT'
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code = 'M';

            CURSOR l_total_female_out_init_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.agency_iml_id = p_location_id
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.in_out_status = 'OUT'
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code = 'F';

            CURSOR l_total_other_out_init_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.agency_iml_id = p_location_id
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.in_out_status = 'OUT'
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code NOT IN ('M','F');

      CURSOR l_out_liv_unit_cur (
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings
          WHERE active_flag = 'Y'
            AND in_out_status = 'OUT'
            AND agency_iml_id IS NULL
            AND agy_loc_id = p_agy_loc_id
            AND living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id);
--EDWARD 26-FEB-2008
      CURSOR l_total_male_out_liv_unit_cur(
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.in_out_status = 'OUT'
            AND ob.agency_iml_id IS NULL
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id)
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code = 'M';

      CURSOR l_tot_female_out_liv_unit_cur(
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.in_out_status = 'OUT'
            AND ob.agency_iml_id IS NULL
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id)
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code = 'F';

      CURSOR l_tot_other_out_liv_unit_cur(
         p_agy_loc_id    agency_locations.agy_loc_id%TYPE,
         p_location_id   NUMBER
      )
      IS
         SELECT COUNT (*)
           FROM offender_bookings ob, offenders ofd
          WHERE ob.active_flag = 'Y'
            AND ob.in_out_status = 'OUT'
            AND ob.agency_iml_id IS NULL
            AND ob.agy_loc_id = p_agy_loc_id
            AND ob.living_unit_id IN
                   (SELECT living_unit_id
                      FROM living_units
                     CONNECT BY PRIOR living_unit_id = parent_living_unit_id
                     START WITH living_unit_id = p_location_id)
						AND ob.offender_id = ofd.offender_id
						AND ofd.sex_code NOT IN ('M','F');

      l_done_flag                                  VARCHAR2(1);
      l_count_existing                             PLS_INTEGER;
      l_cancel_flag                                PLS_INTEGER;
      l_terminated_session_flag                    PLS_INTEGER;
      l_all_complete_flag                          VARCHAR2(1);
      l_actual_count                               PLS_INTEGER;
			l_total_male                                 PLS_INTEGER;
			l_total_female                               PLS_INTEGER;
			l_total_other                                PLS_INTEGER;
      l_reported_count                             PLS_INTEGER;
      l_total_ac_count                             PLS_INTEGER;
      l_total_rp_count                             PLS_INTEGER;
      l_reporting_loc_id                           NUMBER;
      l_date_submitted                             DATE;
      l_entered_by_userid                          VARCHAR2(32);
  		l_out_total												PLS_INTEGER;
	  	l_total_male_out                  PLS_INTEGER;
			l_total_female_out                PLS_INTEGER;
			l_total_other_out                PLS_INTEGER;
   BEGIN
      /* delete existing records from the temp table */
      LOOP
         l_count_existing := 0;
         SELECT COUNT (*)
           INTO l_count_existing
           FROM temp_oidcount
          WHERE session_id = p_session_id
            AND agy_loc_id <> 'CANCEL';
         IF l_count_existing = 0
         THEN
            EXIT;
         ELSE
            cancel_count (p_session_id);
         END IF;
         DBMS_LOCK.sleep (1);
      END LOOP;
      COMMIT;
      /* insert into temp all location counts */
      INSERT INTO temp_oidcount
                  (session_id,
                   agy_seq,
                   list_seq,
                   agy_loc_id,
                   location_type,
                   location_description,
                   lowest_location_id,
                   count_type_id,
                   count_type_code,
                   reporting_loc_id,
                   scheduled_time
                  --date_submitted,
                  --entered_by_userid

                  )
         SELECT p_session_id,
                arl.agy_seq,
                arl.list_seq,
                act.agy_loc_id,
                arl.location_type,
                lu.description location_description,
                DECODE (
                   arl.location3_id,
                   NULL, DECODE (
                            arl.location2_id,
                            NULL, arl.location1_id,
                            arl.location2_id
                         ),
                   arl.location3_id
                ) lowest_location_id,
                alc.count_type_id,
                act.count_type_code,
                alc.reporting_loc_id,
                act.scheduled_time
           --alc.date_submitted,
           --alc.entered_by_userid
           FROM agency_location_counts alc,
                agency_reporting_locs arl,
                agency_count_types act,
                agency_counts ac,
                locked_modules lm,
                (SELECT 'LIV' unit_type,
                        living_unit_id,
                        description
                   FROM living_units
                  WHERE agy_loc_id = p_agy_loc_id
                 UNION ALL                
                 SELECT 'IML', 
                         internal_location_id agency_iml_id, 
                         internal_location_desc description                        
--                        
-- NIKO @ 08-SEP-2008                        
--                 FROM agency_internal_mvmt_locations                                                         
--                  WHERE agency_location_id = p_agy_loc_id) lu
--
                   FROM v_int_loc_summaries
                  WHERE agy_loc_id           =  p_agy_loc_id ) lu     
          WHERE arl.count_type_id = p_count_type_id
            AND alc.count_type_id = p_count_type_id
            AND act.count_type_id = p_count_type_id
            AND arl.count_type_id = alc.count_type_id
            AND arl.count_type_id = act.count_type_id
            AND (  (   arl.location_type <> 'INIT'
                   AND (  (   arl.location3_id IS NOT NULL
                          AND arl.location3_id = lu.living_unit_id
                          AND lu.unit_type = 'LIV')
                       OR (   arl.location2_id IS NOT NULL
                          AND arl.location2_id = lu.living_unit_id
                          AND lu.unit_type = 'LIV'
                          AND arl.location3_id IS NULL)
                       OR (   arl.location1_id IS NOT NULL
                          AND arl.location1_id = lu.living_unit_id
                          AND lu.unit_type = 'LIV'
                          AND (   arl.location2_id IS NULL
                              AND arl.location3_id IS NULL))))
                OR (   arl.location_type = 'INIT'
                   AND arl.location1_id = lu.living_unit_id
                   AND lu.unit_type = 'IML'))
            AND TRUNC (alc.date_submitted) = TRUNC (lm.locked_date)
            AND lm.session_id = p_session_id
            AND lm.module_name = 'OIDCOUNT'
            AND arl.agy_seq = alc.agy_seq
            AND act.active_flag = 'Y'
            AND arl.active_flag = 'Y'
            AND act.agy_loc_id = p_agy_loc_id
            AND act.count_type_id = p_count_type_id
            AND ac.reporting_loc_id = alc.reporting_loc_id
            AND ac.count_type_id = alc.count_type_id
            AND ac.outcome IS NULL
            AND ac.count_in_progress = 'Y'
            AND act.scheduled_time = p_scheduled_time
            AND (( arl.location_type <> 'INIT'
                OR (   arl.location_type = 'INIT'
                   AND (  EXISTS (SELECT 1
                                    FROM offender_bookings obx
                                   WHERE obx.active_flag = 'Y'
                                     AND obx.agency_iml_id =
                                            DECODE (
                                               arl.location3_id,
                                               NULL, DECODE (
                                                        arl.location2_id,
                                                        NULL, arl.location1_id,
                                                        arl.location2_id
                                                     ),
                                               arl.location3_id
                                            )
                                     AND obx.agy_loc_id = act.agy_loc_id
                                     AND obx.in_out_status = 'IN')
                       OR EXISTS (SELECT 1
                                    FROM system_profiles
                                   WHERE profile_type = 'CLIENT'
                                     AND profile_code = 'ZERO_COUNT'
                                     AND profile_value = 'Y')))
                OR EXISTS (SELECT 1
                             FROM system_profiles
                            WHERE profile_type = 'CLIENT'
                              AND profile_code = 'ZERO_COUNT'
                              AND profile_value = 'Y')))
            AND (( arl.location_type = 'INIT'
                OR (   arl.location_type <> 'INIT'
                   AND (  EXISTS (SELECT 1
                                    FROM offender_bookings obx,
                                         living_units lux
                                   WHERE obx.active_flag = 'Y'
                                     AND obx.agy_loc_id = p_agy_loc_id
                                     AND obx.living_unit_id =
                                            lux.living_unit_id
                                     AND obx.agency_iml_id IS NULL
                                     AND lux.agy_loc_id = p_agy_loc_id
                                     AND lux.description LIKE
                                            lu.description || '%'
                                     AND obx.agy_loc_id = act.agy_loc_id
                                     AND obx.in_out_status = 'IN')
                       OR EXISTS (SELECT 1
                                    FROM system_profiles
                                   WHERE profile_type = 'CLIENT'
                                     AND profile_code = 'ZERO_COUNT'
                                     AND profile_value = 'Y')))
                OR EXISTS (SELECT 1
                             FROM system_profiles
                            WHERE profile_type = 'CLIENT'
                              AND profile_code = 'ZERO_COUNT'
                              AND profile_value = 'Y')));
      COMMIT;
      SELECT reporting_loc_id
        INTO l_reporting_loc_id
        FROM temp_oidcount
       WHERE session_id = p_session_id
         AND reporting_loc_id IS NOT NULL
         AND agy_loc_id <> 'CANCEL'
         AND ROWNUM < 2;
      l_all_complete_flag := 'N';
      FOR l_rec IN l_temp_count_cur (p_session_id)
      LOOP
         /* check if user pressed cancel process */
         l_cancel_flag := NULL;
         SELECT COUNT (*)
           INTO l_cancel_flag
           FROM temp_oidcount
          WHERE session_id = p_session_id
            AND agy_loc_id = 'CANCEL';
         IF l_cancel_flag > 0
         THEN
            DELETE
              FROM temp_oidcount
             WHERE session_id = p_session_id;
            COMMIT;
            l_all_complete_flag := 'Y';
            EXIT;
         END IF;
         /* check if session terminated */
         l_terminated_session_flag := NULL;
         SELECT COUNT (*)
           INTO l_terminated_session_flag
           FROM locked_modules lm
          WHERE lm.session_id = p_session_id
            AND lm.module_name = 'OIDCOUNT';
         IF l_terminated_session_flag = 0
         THEN
            DELETE
              FROM temp_oidcount
             WHERE session_id = p_session_id;
            DELETE
              FROM agency_location_counts
             WHERE reporting_loc_id = l_reporting_loc_id;
            DELETE
              FROM agency_counts
             --WHERE outcome IS NULL --@@ Aditya, 11/09/2012, Commented the condition to fix defect# 20191.
               WHERE reporting_loc_id = l_reporting_loc_id;
            COMMIT;
            l_all_complete_flag := 'Y';
            EXIT;
         END IF;
         IF l_rec.actual_count IS NULL
         THEN
            IF l_rec.location_type = 'INIT'
            THEN
               l_actual_count := 0;
               OPEN l_count_init_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_init_cur INTO l_actual_count;
               CLOSE l_count_init_cur;


            ELSE
               l_actual_count := 0;
               OPEN l_count_liv_unit_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_liv_unit_cur INTO l_actual_count;
               CLOSE l_count_liv_unit_cur;
            END IF;
            UPDATE temp_oidcount
               SET actual_count = l_actual_count,
                   discrepancy_count = reported_count - actual_count
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
         END IF;
--@@@ 28-JAN-2008: Added calculation of total_male, total_female, total_other
				 IF l_rec.total_male IS NULL
         THEN
            IF l_rec.location_type = 'INIT'
            THEN
               l_total_male := 0;
               OPEN l_count_init_male_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_init_male_cur INTO l_total_male;
               CLOSE l_count_init_male_cur;


            ELSE
               l_total_male := 0;
               OPEN l_count_liv_unit_male_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_liv_unit_male_cur INTO l_total_male;
               CLOSE l_count_liv_unit_male_cur;
            END IF;
            UPDATE temp_oidcount
               SET total_male = l_total_male
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
         END IF;

				 IF l_rec.total_female IS NULL
         THEN
            IF l_rec.location_type = 'INIT'
            THEN
               l_total_female := 0;
               OPEN l_count_init_female_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_init_female_cur INTO l_total_female;
               CLOSE l_count_init_female_cur;


            ELSE
               l_total_female := 0;
               OPEN l_count_liv_unit_female_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_liv_unit_female_cur INTO l_total_female;
               CLOSE l_count_liv_unit_female_cur;
            END IF;
            UPDATE temp_oidcount
               SET total_female = l_total_female
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
         END IF;

				 IF l_rec.total_other IS NULL
         THEN
            IF l_rec.location_type = 'INIT'
            THEN
               l_total_other := 0;
               OPEN l_count_init_other_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_init_other_cur INTO l_total_other;
               CLOSE l_count_init_other_cur;


            ELSE
               l_total_other := 0;
               OPEN l_count_liv_unit_other_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_count_liv_unit_other_cur INTO l_total_other;
               CLOSE l_count_liv_unit_other_cur;
            END IF;
            UPDATE temp_oidcount
               SET total_other = l_total_other
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
         END IF;
--@@@ 28-JAN-2008: Added calculation of total_male, total_female, total_other
								--Dragan, 24-may-2007
			IF l_rec.out_total IS NULL THEN
				IF l_rec.location_type = 'INIT' THEN
               l_out_total := 0;
               OPEN l_out_init_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_out_init_cur INTO l_out_total;
               CLOSE l_out_init_cur;
				ELSE
               l_out_total := 0;
               OPEN l_out_liv_unit_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_out_liv_unit_cur INTO l_out_total;
               CLOSE l_out_liv_unit_cur;
				END IF;
            UPDATE temp_oidcount
               SET out_total = l_out_total
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
			END IF;
--@@@EDWARD 26-FEB-2008: Added calculation of total_male_out, total_female_out, total_other_out
			IF l_rec.total_male_out IS NULL THEN
				IF l_rec.location_type = 'INIT' THEN
               l_total_male_out := 0;
               OPEN l_total_male_out_init_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_total_male_out_init_cur INTO l_total_male_out;
               CLOSE l_total_male_out_init_cur;
				ELSE
               l_total_male_out := 0;
               OPEN l_total_male_out_liv_unit_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_total_male_out_liv_unit_cur INTO l_total_male_out;
               CLOSE l_total_male_out_liv_unit_cur;
				END IF;
            UPDATE temp_oidcount
               SET total_male_out = l_total_male_out
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
			END IF;
			IF l_rec.total_female_out IS NULL THEN
				IF l_rec.location_type = 'INIT' THEN
               l_total_female_out := 0;
              OPEN l_total_female_out_init_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_total_female_out_init_cur INTO l_total_female_out;
               CLOSE l_total_female_out_init_cur;
				ELSE
               l_total_female_out := 0;
               OPEN l_tot_female_out_liv_unit_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_tot_female_out_liv_unit_cur INTO l_total_female_out;
               CLOSE l_tot_female_out_liv_unit_cur;
				END IF;
            UPDATE temp_oidcount
               SET total_female_out = l_total_female_out
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
			END IF;

			IF l_rec.total_other_out IS NULL THEN
				IF l_rec.location_type = 'INIT' THEN
               l_total_other_out := 0;
              OPEN l_total_other_out_init_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_total_other_out_init_cur INTO l_total_other_out;
               CLOSE l_total_other_out_init_cur;
				ELSE
               l_total_other_out := 0;
               OPEN l_tot_other_out_liv_unit_cur (
                       l_rec.agy_loc_id,
                       l_rec.lowest_location_id
                    );
               FETCH l_tot_other_out_liv_unit_cur INTO l_total_other_out;
               CLOSE l_tot_other_out_liv_unit_cur;
				END IF;
            UPDATE temp_oidcount
               SET total_other_out = l_total_other_out
             WHERE ROWID = l_rec.ROWID;
            COMMIT;
			END IF;

      END LOOP;
      IF l_all_complete_flag = 'N'
      THEN
         LOOP
            /* check if user pressed cancel process */
            l_cancel_flag := NULL;
            SELECT COUNT (*)
              INTO l_cancel_flag
              FROM temp_oidcount
             WHERE session_id = p_session_id
               AND agy_loc_id = 'CANCEL';
            IF l_cancel_flag > 0
            THEN
               DELETE
                 FROM temp_oidcount
                WHERE session_id = p_session_id;
               COMMIT;
               EXIT;
            END IF;
            /* check if session terminated */
            l_terminated_session_flag := NULL;
            SELECT COUNT (*)
              INTO l_terminated_session_flag
              FROM locked_modules lm
             WHERE lm.session_id = p_session_id
               AND lm.module_name = 'OIDCOUNT';
            IF l_terminated_session_flag = 0
            THEN
               DELETE
                 FROM temp_oidcount
                WHERE session_id = p_session_id;
               DELETE
                 FROM agency_location_counts
                WHERE reporting_loc_id = l_reporting_loc_id;
               DELETE
                 FROM agency_counts
                --WHERE outcome IS NULL --@@ Aditya, 11/09/2012, Commented the condition to fix defect# 20191.
                  WHERE reporting_loc_id = l_reporting_loc_id;
               COMMIT;
               EXIT;
            END IF;
            DBMS_LOCK.sleep (10);
         END LOOP;
      END IF;
      --TAS-16 not required since the job is auto drop -- remove_oidcount_job (p_session_id, USER);
   EXCEPTION
      WHEN OTHERS
      THEN
         raise_application_error (-20009, 'Error process job: ' || SQLERRM);
   END set_temp_oidcount;
   PROCEDURE commit_rollback (p_commit_rollback_flag VARCHAR2)
   IS
   BEGIN
      IF UPPER (p_commit_rollback_flag) = 'COMMIT'
      THEN
         COMMIT;
      ELSE
         ROLLBACK;
      END IF;
   END;
   PROCEDURE check_remove_dead_jobs (p_session_id NUMBER)
   IS
      CURSOR l_check_dead_jobs (p_session_id NUMBER, p_user_id VARCHAR2)
      IS
         SELECT session_id
           FROM locked_modules
          WHERE module_name = 'OIDCOUNT'
            AND user_id = p_user_id
            AND session_id <> p_session_id
          ORDER BY session_id;
      CURSOR l_temp_oidcount_cur (p_session_id NUMBER)
      IS
         SELECT reporting_loc_id
           FROM temp_oidcount
          WHERE session_id = p_session_id
            AND agy_loc_id <> 'CANCEL'
            AND ROWNUM < 2;
      CURSOR l_dead_job (p_session_id VARCHAR2, p_user_id VARCHAR2)
      IS
         SELECT job
           FROM all_jobs
          WHERE log_user = p_user_id
            AND UPPER (REPLACE (what, ' ', NULL)) LIKE
                   'OIDCOUNT.SET_TEMP_OIDCOUNT(' || p_session_id || ',%';
      l_done_flag                                  VARCHAR2(1);
      l_reporting_loc_id                           NUMBER;
      l_user_id                                    VARCHAR2(32) := USER;
      
      
      --TAS-16
      CURSOR l_scheduler_dead_jobs_cur (p_session_id VARCHAR2, p_user_id VARCHAR2)
      IS
      SELECT job_name 
        FROM all_scheduler_jobs
       WHERE job_name = 'INIT_COUNT_' || p_session_id;
       
      lv_job_name VARCHAR2(500);  
   BEGIN
      FOR l_rec IN l_check_dead_jobs (p_session_id, USER)
      LOOP
         FOR l_jobs IN l_dead_job (
                          LTRIM (RTRIM (TO_CHAR (l_rec.session_id))),
                          USER
                       )
         LOOP
            DBMS_JOB.remove (l_jobs.job);
            COMMIT;
         END LOOP;
         
         
         --TAS-16
         FOR l_jobs IN l_scheduler_dead_jobs_cur (
                          LTRIM (RTRIM (TO_CHAR (l_rec.session_id))),
                          USER
                       )
         LOOP 
            --won't work while running DBMS_SCHEDULER.DROP_JOB (job_name => l_jobs.job_name); 
            cancel_count (p_session_id); --we cancel and the job will be dropped automatically;
            DBMS_LOCK.sleep (1);
         END LOOP;  
         DBMS_LOCK.sleep (1);
         --end TAS-16       
         
         OPEN l_temp_oidcount_cur (l_rec.session_id);
         FETCH l_temp_oidcount_cur INTO l_reporting_loc_id;
         CLOSE l_temp_oidcount_cur;
         DELETE
           FROM temp_oidcount
          WHERE session_id = l_rec.session_id;
         COMMIT;
         DELETE
           FROM agency_location_counts
          WHERE reporting_loc_id = l_reporting_loc_id;
         COMMIT;
         DELETE
           FROM agency_counts
          --WHERE outcome IS NULL --@@ Aditya, 11/09/2012, Commented the condition to fix defect# 20191.
            WHERE reporting_loc_id = l_reporting_loc_id;
         COMMIT;
      END LOOP;
      DELETE
        FROM locked_modules
       WHERE module_name = 'OIDCOUNT'
         AND user_id = l_user_id
         AND session_id <> p_session_id;
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         raise_application_error (-20009, 'Error kill dead jobs: ' || SQLERRM);
   END check_remove_dead_jobs;

END;   -- Package body
/
