CREATE OR REPLACE PACKAGE BODY OMS_OWNER.TAG_WORKFLOW
IS
/*
||    Purpose: This package provides the basic functions of the work flow module.
||             It creates work and completes work automatically, etc...
||
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    ------------------------
||    Person                     DATE     Version  Comments
||    ---------              -----------  -------  -----------------------------------
||   Syscon                 30-NOV-2020  10.2.31.1.8  V4 change from Om
||   Syscon                 22-SEP-2020  10.2.31.1.7  V4 change
||   Nasir                  13-AUG-2015  10.2.31.1.6  HPQC#25893 : Modify exception in FORWARD_CASE_NOTE procedure to skip the error if the staff id is null
||   NDB                    25-Nov-2011  10.2.31.1.5  QC 11384 Changes for PTI. New routine create_agency_workflow. Changes to assignment processing.
||   Ruxandra               21-Feb-2011  10.2.31.1.4  Added commnets related to reserved trigger name ADHOC_EMAIL
||   Ruxandra               30-AUG-2010  10.2.31.1.3  Modifications to accommodate executable for email, dynamic recipients, body, subject
||   Ruxandra               30-AUG-2010  10.2.31.1.2  Modifications to accommodate executable for email, dynamic recipients, body, subject
||   ignore line, incorrect version  Ruxandra               30-AUG-2010  10.2.31.1.1.1.0 SDSTDOC  Modifications to accommodate executable for email, dynamic recipients, body, subject
||    Steven	26-JAN-2010	10.2.31.1.1 Bug fixes
||    Ruxandra               20-FEB-2009  10.2.31.1.0 Enhancement for Staff memos and Emails messages
||    NIKO                   25-NOV-2008  10.2.31  Added a new field function_type to queue_rec
||                                                 Modified the PROCEDURE browse_queue and PROCEDURE browse_work_message
||    GJC                    11-Jun-2007  10.2.30  Remove ORIGINAL_TAG_WF_MESSAGE from WORKFLOW_HISTORY and replace with individual elements
||    NDB                    13-MAR-2007  10.2.29  #6247 Corrected.
||    NDB                    12-MAR-2007  10.2.28  #6247 Corrected.
||    NDB                    09-MAR-2007  10.2.27  #6247 Changes to support new event date
||    GJC                    14-Oct-2005  2.26     SHOW_VERSION changed from procedure to function
||    Patrick                07-SEP-2006  2.25     Defect 4458. Added ORIGINAL_MSGID in scan_queue and browse queue for iwp linkage
||    Erin                   27-JUN-2006  10.2.24  Remove FUNCTION chk_team_id
||    Erin                   26-Jun-2006  10.2.23  Fix version to re-compile
||    Erin                   22-JUN-2006  10.2.22  Added FUNCTION chk_team_id for from OCMDEFTM
||    GJC                    31-MAY-2006  10.2.21  Defect 1.1 Release 1.1 Workflows
||    GJC                    31-MAY-2006  10.2.20  Defect 1.1 Release 1.1 Workflows
||    GJC                    31-MAY-2006  10.2.19  Defect 1.1 Release 1.1 Workflows
||    Neil                   25-May-2006  10.2.21  Fixed version numbers
||    Neil                   25-May-2006  10.2.20  Changed insert_work_message.
||    Neil                   25-May-2006  10.2.19  Changed workflow history control of open and closing workflows.
||    GJC                    09-MAY-2006  10.2.18  Add work_trigger_executables processing
||    Neil                   05-May-2006  10.2.17  Assignment date is now set to sysdate for every re-assignment
||    Neil                   24-Apr-2006  10.2.16  Fixed add of complete reason code for auto completion.
||    Neil                   21-Apr-2006  10.2.15  Added complete reason code to complete workflow.
||    Neil                   20-Apr-2006  10.2.14  Fixed search_queue
||    Neil                   19-Apr-2006  10.2.13  insert_case_note: added date_creation
||    GJC                    05-Apr-2006  10.2.12  Add check_outstanding_task.
||    Neil                   04-Apr-2006  10.2.11  Changed order by on browse_queue.
||    Neil                   04-Apr-2006  10.2.10  Fixed updates of work count.
||    GJC                    13-Mar-2006  10.2.9   New version for AQ workflow rewrite
*/
   FUNCTION ack_queue_msgid ( p_queue_name IN VARCHAR2, p_msgid IN VARCHAR2 )
      RETURN tag_wf_message
   IS
      l_dequeue_options      DBMS_AQ.dequeue_options_t;
      l_message_properties   DBMS_AQ.message_properties_t;
      l_message              tag_wf_message;
      l_msgid                RAW ( 16 );
   BEGIN
      l_dequeue_options.consumer_name := 'ENDUSER';
      l_dequeue_options.msgid := HEXTORAW ( p_msgid );
      l_dequeue_options.WAIT := DBMS_AQ.no_wait;
      l_dequeue_options.visibility := DBMS_AQ.on_commit;
      l_dequeue_options.navigation := DBMS_AQ.first_message;
      DBMS_AQ.dequeue ( queue_name              => p_queue_name,
                        dequeue_options         => l_dequeue_options,
                        message_properties      => l_message_properties,
                        payload                 => l_message,
                        msgid                   => l_msgid );

      IF     l_message.team_member_id IS NOT NULL
         AND UPPER ( l_message.workflow_type ) = 'TASK'
         AND l_message.trigger_reason IS NULL
      THEN
         Tag_Workflow.adjust_tm_no_of_tasks
                              ( p_team_member_id      => l_message.team_member_id,
                                p_adjustment          => -1 );
      END IF;

      RETURN ( l_message );
   EXCEPTION
      WHEN e_empty_queue
      THEN
         Tag_Error.raise_app_error
                              ( -20000,
                                'Cannot find message - message queue empty.',
                                TRUE );
      WHEN e_no_messages
      THEN
         Tag_Error.raise_app_error ( -20000,
                                     'Cannot find message - maybe locked.',
                                     TRUE );
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE create_task_assignment_hty (
      p_msgid             IN   VARCHAR2,
      p_message           IN   tag_wf_message,
      p_original_msgid    IN   VARCHAR2 DEFAULT NULL,
      p_completion_date   IN   DATE DEFAULT NULL )
   IS
      l_original_message_id   VARCHAR2 ( 64 );
      l_completion_date       DATE;
   BEGIN
      IF p_original_msgid IS NOT NULL
      THEN
         l_original_message_id := p_original_msgid;
      ELSE
         l_original_message_id := p_message.original_msgid;
      END IF;

	  -- 6247 added event date into insert

      INSERT INTO TASK_ASSIGNMENT_HTY
                  ( task_assignment_hty_id,
                    offender_book_id, work_id,
                    assignment_date, event_date, team_id,
                    staff_id, team_member_id,
                    due_date, details,
                    completion_date, complete_reason_code,
                    complete_comment_text,
                    complete_user_id, message_id,
                    workflow_history_id,
                    function_type )
           VALUES ( task_assignment_hty_id.NEXTVAL,
                    p_message.offender_book_id, p_message.work_id,
                    p_message.assignment_date, p_message.spare_date, p_message.team_id,
                    p_message.staff_id, p_message.team_member_id,
                    p_message.due_date, p_message.MESSAGE_TEXT,
                    p_completion_date, p_message.complete_reason_code,
                    p_message.complete_comment_text,
                    p_message.complete_user_id, p_msgid,
                    TO_NUMBER ( l_original_message_id ),
                    p_message.SPARE_TEXT );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
             ( p_error_code         => SQLCODE,
               p_log                => TRUE,
               p_user_module        => 'TAG_WF_WORKFLOW.CREATE_TASK_ASSIGNMENT_HTY',
               p_user_message       => 'MSGID:' || p_original_msgid,
               p_user_location      => 'EXCEPTION',
               p_reraise            => TRUE );
   END;

   FUNCTION get_cluster (
      p_staff_id   IN   NUMBER DEFAULT NULL,
      p_team_id    IN   NUMBER DEFAULT NULL )
      RETURN TEAMS.queue_cluster_id%TYPE
   IS
      l_queue_cluster_id   TEAMS.queue_cluster_id%TYPE;
   BEGIN
      IF p_staff_id IS NOT NULL
      THEN
         SELECT queue_cluster_id
           INTO l_queue_cluster_id
           FROM STAFF_MEMBERS
          WHERE staff_id = p_staff_id;
      ELSIF p_team_id IS NOT NULL
      THEN
         SELECT queue_cluster_id
           INTO l_queue_cluster_id
           FROM TEAMS
          WHERE team_id = p_team_id;
      END IF;

      RETURN ( l_queue_cluster_id );
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END;

   PROCEDURE complete_msgid (
      p_queue_name             IN   VARCHAR2,
      p_msgid                  IN   VARCHAR2,
      p_complete_reason_code   IN   VARCHAR2 )
   IS
      l_message   tag_wf_message;
   BEGIN
      BEGIN
         l_message := ack_queue_msgid ( p_queue_name, p_msgid );
      EXCEPTION
         WHEN OTHERS
         THEN
            IF SQLCODE = -20000
            THEN
               NULL;
            ELSE
               RAISE;
            END IF;
      END;

      l_message.complete_reason_code := p_complete_reason_code;
      create_task_assignment_hty
                               ( p_msgid                => p_msgid,
                                 p_message              => l_message,
                                 p_original_msgid       => l_message.original_msgid,
                                 p_completion_date      => SYSDATE );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                ( p_error_code         => SQLCODE,
                  p_log                => TRUE,
                  p_user_module        => 'TAG_WF_WORKFLOW.COMPLETE_ORIGINAL_MSGID',
                  p_user_message       => 'MSGID:' || p_msgid,
                  p_user_location      => 'EXCEPTION',
                  p_reraise            => TRUE );
   END;

   FUNCTION get_queue_name (
      p_staff_id        IN   NUMBER DEFAULT NULL,
      p_team_id         IN   NUMBER DEFAULT NULL,
      p_workflow_type   IN   VARCHAR2 DEFAULT NULL )
      RETURN VARCHAR2
   IS
      l_queue_name   VARCHAR2 ( 50 );
      l_cluster_id   TEAMS.queue_cluster_id%TYPE;
   BEGIN
      IF p_staff_id IS NOT NULL
      THEN
         l_cluster_id := get_cluster ( p_staff_id => p_staff_id );

         IF UPPER ( p_workflow_type ) = 'TASK'
         THEN
            l_queue_name :=
                  'TAGWFC'
               || l_cluster_id
               || '.TAG_WF_PERSONAL_T_C'
               || l_cluster_id;
         ELSE
            l_queue_name :=
                  'TAGWFC'
               || l_cluster_id
               || '.TAG_WF_PERSONAL_M_C'
               || l_cluster_id;
         END IF;
      ELSIF p_team_id IS NOT NULL
      THEN
         l_cluster_id := get_cluster ( p_team_id => p_team_id );
         l_queue_name :=
              'TAGWFC' || l_cluster_id || '.TAG_WF_GENERAL_C' || l_cluster_id;
      END IF;

      RETURN ( l_queue_name );
   END;

   PROCEDURE get_trigger_registration (
      p_work_trigger              IN       WORK_TRIGGER_EXECUTABLES.work_trigger%TYPE,
      p_trigger_usage             IN       WORK_TRIGGER_EXECUTABLES.trigger_usage%TYPE,
      p_work_context              IN       WORK_TRIGGER_EXECUTABLES.work_context%TYPE,
      p_trigger_executable        OUT      WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE,
      p_trigger_executable_type   OUT      WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE,
      p_message_template          OUT      WORK_TRIGGER_EXECUTABLES.message_template%TYPE )
   IS
      CURSOR trg_cur
      IS
         SELECT trigger_executable, trigger_executable_type,
                message_template
           FROM WORK_TRIGGER_EXECUTABLES
          WHERE work_trigger = p_work_trigger
            AND trigger_usage = p_trigger_usage
            AND work_context = p_work_context;
   BEGIN
      OPEN trg_cur;

      FETCH trg_cur
       INTO p_trigger_executable, p_trigger_executable_type,
            p_message_template;

      CLOSE trg_cur;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                 ( p_error_code         => SQLCODE,
                   p_log                => TRUE,
                   p_user_module        => 'TAG_WF_WORKFLOW.GET_TRIGGER_EXECUTABLE',
                   p_user_location      => 'EXCEPTION',
                   p_reraise            => TRUE );
   END;

   PROCEDURE execute_trigger_executable (
      p_message                   IN OUT   tag_wf_message,
      p_params                    IN       XMLTYPE,
      p_work_trigger              IN       WORK_TRIGGER_EXECUTABLES.work_trigger%TYPE,
      p_work_context              IN       WORK_TRIGGER_EXECUTABLES.work_context%TYPE
            DEFAULT 'DEFAULT',
      p_trigger_executable        IN       WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE,
      p_trigger_executable_type   IN       WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE,
      p_message_template          IN       WORK_TRIGGER_EXECUTABLES.message_template%TYPE,
      p_continue                  OUT      VARCHAR2 )
   IS
      invalid_executable_type   EXCEPTION;
      l_message                 tag_wf_message;
   BEGIN

      p_message.MESSAGE_TEXT := p_message_template;

      BEGIN
         IF p_trigger_executable IS NOT NULL
         THEN
            CASE p_trigger_executable_type
               WHEN 'PACKAGE PROCEDURE'
               THEN
                  EXECUTE IMMEDIATE    'BEGIN '
                                    || p_trigger_executable
                                    || '(:FULL_MESSAGE,:MESSAGE_TEMPLATE,:MESSAGE_PARAMS,:CONTINUE); END;'
                              USING IN OUT p_message,
                                    IN     p_message_template,
                                    IN     p_params,
                                    OUT    p_continue;
               ELSE
                  RAISE invalid_executable_type;
            END CASE;
         END IF;

         IF p_continue IS NULL
         THEN
            p_continue := 'Y';
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            oms_owner.Tag_Error.handle
               ( p_error_code         => SQLCODE,
                 p_log                => TRUE,
                 p_user_module        => 'EXECUTE_TRIGGER_EXECUTABLE',
                 p_user_message       => 'Unsupported Trigger no executable found.',
                 p_user_location      => 'EXCEPTION',
                 p_reraise            => FALSE );
            RAISE;
         WHEN invalid_executable_type
         THEN
            oms_owner.Tag_Error.handle
               ( p_error_code         => SQLCODE,
                 p_log                => TRUE,
                 p_user_module        => 'EXECUTE_TRIGGER_EXECUTABLE',
                 p_user_message       => 'Unsupported Trigger Excutable Type found',
                 p_user_location      => 'EXCEPTION',
                 p_reraise            => FALSE );
            RAISE;
         WHEN OTHERS
         THEN
            oms_owner.Tag_Error.handle
                             ( p_error_code         => SQLCODE,
                               p_log                => TRUE,
                               p_user_module        => 'EXECUTE_TRIGGER_EXECUTABLE',
                               p_user_message       =>    'CALLING ROUTINE:'
                                                       || p_trigger_executable
                                                       || ':'
                                                       || p_message.MESSAGE_TEXT,
                               p_user_location      => 'EXCEPTION',
                               p_reraise            => FALSE );
            RAISE;
      END;
   END;

   -- QC 11384 NDB New routine for PTI
   
   FUNCTION open_team_cursor ( p_message IN tag_wf_message )
   RETURN SYS_REFCURSOR
   IS
      lv_cur SYS_REFCURSOR;
   BEGIN
      IF p_message.spare_text IS NOT NULL
      THEN
         OPEN lv_cur FOR
            SELECT w.work_id, w.workflow_type, t.team_id,
                   NVL ( p_message.override_due_date,
                      p_message.due_date
                      + ( wt.due * nvl(p_message.due_date_period,1) ) ) due_date,
                   t.queue_cluster_id
              FROM WORK_TRIGGERS wt,
                  WORK_FUNCTIONS wf,
                  WORKS w,
                  TEAMS t,
                  TEAM_FUNCTIONS tf
            WHERE wt.trigger_name = p_message.work_trigger
              AND wt.work_id = w.work_id
              AND wf.work_id = wt.work_id
              AND t.agy_loc_id = p_message.spare_text
              AND tf.team_id = t.team_id
              AND tf.function_type = wf.function_type
              AND wt.active_flag = 'Y'
              AND w.active_flag = 'Y'
              AND w.workflow_type IN ( 'MEMO', 'TASK' );
         
         ELSE
            OPEN lv_cur FOR
               SELECT w.work_id, w.workflow_type, o.team_id,
                      NVL ( p_message.override_due_date,
                        p_message.due_date
                      + ( t.due * nvl(p_message.due_date_period,1) ) ) due_date,
                      c.queue_cluster_id
                 FROM OFFENDER_TEAM_ASSIGNMENTS o,
                      WORK_TRIGGERS t,
                      WORK_FUNCTIONS f,
                      WORKS w,
                      TEAMS c
                WHERE o.offender_book_id = p_message.offender_book_id
                  AND t.trigger_name = p_message.work_trigger
                  AND t.work_id = w.work_id
                  AND f.work_id = t.work_id
                  AND c.team_id = o.team_id
                  AND f.function_type = o.function_type
                  AND t.active_flag = 'Y'
                  AND w.active_flag = 'Y'
                  AND w.workflow_type IN ( 'MEMO', 'TASK' );
            
         END IF;  
         
         RETURN (lv_cur);
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE create_team_messages ( p_message IN tag_wf_message )
   IS
      l_queue_name       VARCHAR2 ( 30 );
      l_message          tag_wf_message;
      l_message_handle   RAW ( 16 );
      c_enqueus SYS_REFCURSOR; -- QC 11384 NDB Changes for PTI
      l_queue_cluster_id teams.queue_cluster_id%TYPE;
      /*
      CURSOR c_enqueus
      IS
         SELECT w.work_id, w.workflow_type, o.team_id,
                NVL ( p_message.override_due_date,
                        p_message.due_date
                      + ( t.due * nvl(p_message.due_date_period,1) ) ) due_date,
                c.queue_cluster_id
           FROM OFFENDER_TEAM_ASSIGNMENTS o,
                WORK_TRIGGERS t,
                WORK_FUNCTIONS f,
                WORKS w,
                TEAMS c
          WHERE o.offender_book_id = p_message.offender_book_id
            AND t.trigger_name = p_message.work_trigger
            AND t.work_id = w.work_id
            AND f.work_id = t.work_id
            AND c.team_id = o.team_id
            AND f.function_type = o.function_type
            AND t.active_flag = 'Y'
            AND w.active_flag = 'Y'
            AND w.workflow_type IN ( 'MEMO', 'TASK' );
           */
   BEGIN
      -- check we have got all the required data
      IF    p_message.offender_book_id IS NULL
         OR p_message.work_trigger IS NULL
      THEN
         Tag_Error.raise_app_error
                 ( p_error_code         => -20000,
                   p_error_message      =>    'offender_book_id :'
                                           || TO_CHAR
                                                  ( p_message.offender_book_id )
                                           || ': or work trigger :'
                                           || p_message.work_trigger
                                           || ': is not set',
                   p_stack_trace        => TRUE );
      END IF;

      IF NVL ( l_message.trigger_reason, 'XXXXXXXX' ) != 'CASENOTE'
      THEN
         -- check we have the original message id
         IF p_message.original_msgid IS NULL
         THEN
            Tag_Error.raise_app_error
                       ( p_error_code         => -20001,
                         p_error_message      =>    'original_msgid has no value'
                                                 || ' for work trigger :'
                                                 || p_message.work_trigger,
                         p_stack_trace        => TRUE );
         END IF;
         
         -- QC 11384 NDB Changes for PTI: Now gets vituals teams or admin teams
         
          /*
         FOR r_enqueue IN c_enqueus
         LOOP
            l_message := p_message;
            l_message.team_id := r_enqueue.team_id;
            l_message.due_date := r_enqueue.due_date;
-- 6247            l_message.assignment_date := SYSDATE;
            l_message.work_id := r_enqueue.work_id;
            l_message.workflow_type := r_enqueue.workflow_type;
            l_queue_name :=
                  'TAGWFC'
               || r_enqueue.queue_cluster_id
               || '.TAG_WF_GENERAL_C'
               || r_enqueue.queue_cluster_id;
            send_message
                    ( p_queue_name          => l_queue_name,
                      p_message             => l_message,
                      p_original_msgid      => RAWTOHEX
                                                    ( l_message.original_msgid ),
                      p_message_handle      => l_message_handle );

            IF UPPER ( l_message.workflow_type ) = 'TASK'
            THEN
               create_task_assignment_hty
                    ( p_msgid               => RAWTOHEX ( l_message_handle ),
                      p_message             => l_message,
                      p_original_msgid      => RAWTOHEX
                                                    ( l_message.original_msgid ) );
            END IF;
         END LOOP;
        */

         
         c_enqueus := open_team_cursor(p_message);


         LOOP
            l_message := p_message;
            FETCH c_enqueus
            INTO  l_message.work_id,
                  l_message.workflow_type,
                  l_message.team_id,
                  l_message.due_date,
                  l_queue_cluster_id;
            
            IF c_enqueus%NOTFOUND
            THEN
               EXIT;
            END IF;      
            
            l_queue_name :=
                  'TAGWFC'
               || l_queue_cluster_id
               || '.TAG_WF_GENERAL_C'
               || l_queue_cluster_id;
            send_message
                    ( p_queue_name          => l_queue_name,
                      p_message             => l_message,
                      p_original_msgid      => RAWTOHEX
                                                    ( l_message.original_msgid ),
                      p_message_handle      => l_message_handle );

            IF UPPER ( l_message.workflow_type ) = 'TASK'
            THEN
               create_task_assignment_hty
                    ( p_msgid               => RAWTOHEX ( l_message_handle ),
                      p_message             => l_message,
                      p_original_msgid      => RAWTOHEX
                                                    ( l_message.original_msgid ) );
            END IF;
         END LOOP;
         
         CLOSE c_enqueus;
                 
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                   ( p_error_code         => SQLCODE,
                     p_log                => TRUE,
                     p_user_module        => 'TAG_WF_WORKFLOW.CREATE_TEAM_MESSAGES',
                     p_user_location      => 'EXCEPTION',
                     p_reraise            => TRUE );
   END;

   FUNCTION get_workflow_id
      RETURN PLS_INTEGER
   IS
      CURSOR wf_cur
      IS
         SELECT workflow_history_id.NEXTVAL
           FROM DUAL;

      lv_seq   PLS_INTEGER;
   BEGIN
      OPEN wf_cur;

      FETCH wf_cur
       INTO lv_seq;

      CLOSE wf_cur;

      RETURN ( lv_seq );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE completion_message_handler (
      p_message          IN   tag_wf_message,
      p_message_handle   IN   RAW )
   IS
      l_message                   tag_wf_message;
      l_trigger_executable        WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE;
      l_trigger_executable_type   WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE;
      l_message_template          WORK_TRIGGER_EXECUTABLES.message_template%TYPE;
      l_workflow_id               PLS_INTEGER;
      l_continue                  VARCHAR2 ( 1 );
      l_params                    XMLTYPE;
      l_null_xml                  XMLTYPE := NULL;
   BEGIN
      l_message := p_message;

      IF NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) IN ( 'AUTOCOMP' )
      THEN
         get_trigger_registration ( l_message.work_trigger,
                                    'COMPLETE',
                                    l_message.workflow_type,
                                    l_trigger_executable,
                                    l_trigger_executable_type,
                                    l_message_template );

         IF l_trigger_executable IS NOT NULL
         THEN
            IF l_message.MESSAGE_TEXT IS NOT NULL
            THEN
               l_params := XMLTYPE.createxml (l_message.MESSAGE_TEXT);
            END IF;

            execute_trigger_executable ( l_message,
                                         l_params,
                                         l_message.work_trigger,
                                         l_message.workflow_type,
                                         l_trigger_executable,
                                         l_trigger_executable_type,
                                         l_message_template,
                                         l_continue );
         END IF;

         IF NVL ( l_continue, 'Y' ) = 'Y'
         THEN
            l_workflow_id := get_workflow_id;
            Tag_Workflow.close_workflow
                                    ( p_message                  => l_message,
                                      p_workflow_history_id      => l_workflow_id,
                                      p_key                      => l_message.original_msgid );
         END IF;
      ELSIF NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) = 'MANUALCOMP'
      THEN
         Tag_Workflow.create_task_assignment_hty
                             ( p_msgid                => RAWTOHEX
                                                            ( p_message_handle ),
                               p_message              => l_message,
                               p_completion_date      => l_message.assignment_date );

         get_trigger_registration (l_message.work_trigger
                                     ,'MANUALCOMP'
                                     ,'DEFAULT'
                                     ,l_trigger_executable
                                     ,l_trigger_executable_type
                                     ,l_message_template
                                     );

         IF l_trigger_executable IS NOT NULL
         THEN
             execute_trigger_executable (l_message
                                           ,l_null_xml
                                           ,l_message.work_trigger
                                           ,l_message.workflow_type
                                           ,l_trigger_executable
                                           ,l_trigger_executable_type
                                           ,l_message_template
                                           ,l_continue
                                           );
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                ( p_error_code         => SQLCODE,
                  p_log                => TRUE,
                  p_user_module        => 'TAG_WORKFLOW.COMPLETION_MESSAGE_HANDLER',
                  p_user_message       =>    ' TRIGGER_REASON:'
                                          || l_message.trigger_reason
                                          || ' TRIGGER_NAME:'
                                          || l_message.work_trigger,
                  p_user_location      => 'EXCEPTION',
                  p_reraise            => TRUE );
   END;

   PROCEDURE workflow_message_handler (
      p_message          IN   tag_wf_message,
      p_message_handle   IN   RAW )
   IS
      l_message                   tag_wf_message;
      l_trigger_executable        WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE;
      l_trigger_executable_type   WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE;
      l_message_template          WORK_TRIGGER_EXECUTABLES.message_template%TYPE;
      l_workflow_id               PLS_INTEGER;
      l_continue                  VARCHAR2 ( 1 );
      l_key                       WORKFLOW_HISTORY.KEY%TYPE;
      l_params                    XMLTYPE;

      lv_open_workflow_flag       VARCHAR2(1) := NULL;
      lv_persist_flag             VARCHAR2(1) := NULL;
   BEGIN
      l_message := p_message;

      IF    l_message.trigger_reason IS NULL
         OR NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) = 'CASENOTE'
      THEN
         get_trigger_registration ( l_message.work_trigger,
                                    'CREATE',
                                    l_message.workflow_type,
                                    l_trigger_executable,
                                    l_trigger_executable_type,
                                    l_message_template );

         IF l_trigger_executable IS NOT NULL
         THEN
            IF l_message.MESSAGE_TEXT IS NOT NULL
            THEN
               l_params := XMLTYPE.createxml (l_message.MESSAGE_TEXT);
            END IF;

            execute_trigger_executable ( l_message,
                                         l_params,
                                         l_message.work_trigger,
                                         l_message.workflow_type,
                                         l_trigger_executable,
                                         l_trigger_executable_type,
                                         l_message_template,
                                         l_continue );
         ELSE
            l_message.MESSAGE_TEXT := l_message_template;
         END IF;
      END IF;

      IF NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) = 'CASENOTE'
      THEN
         IF NVL ( l_continue, 'Y' ) = 'Y'
         THEN
            Tag_Workflow.forward_case_note ( p_message => l_message );
         END IF;
      --20-Feb-2009 automatically generated emails
      --30-AUG-2010
      ELSIF NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) = 'WORKEMAIL'
      THEN

         l_key := l_message.original_msgid;

         Tag_Workflow.forward_email( p_message            => l_message);

         IF    l_message.spare_number IS NULL -- to faciliate for older signature of create_email_workflow that opened workflow for all email
            OR l_message.spare_number IN (111, 110) -- to faciliate for new signature of create_email_workflow
         THEN
            l_workflow_id := get_workflow_id;
            Tag_Workflow.open_workflow( p_message                  => l_message,
                                        p_workflow_history_id      => l_workflow_id,
                                        p_key                      => l_key );
         END IF;

      --30-AUG-2010
      ELSIF NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) = 'EMAIL'
      THEN
         l_key := l_message.original_msgid;

         Tag_Workflow.forward_email ( p_message            => l_message);

         IF l_message.spare_number IN (111, 110)
         THEN
            l_workflow_id := get_workflow_id;
            Tag_Workflow.open_workflow( p_message                  => l_message,
                                        p_workflow_history_id      => l_workflow_id,
                                        p_key                      => l_key );
         END IF;

      ELSIF NVL ( l_message.trigger_reason, 'XXXXXXXXXXXX' ) = 'WORKMESSAGE'
      THEN
         Tag_Workflow.forward_work_message ( p_message => l_message );
         l_workflow_id := TO_NUMBER ( l_message.original_msgid );
         l_key := l_message.original_msgid;

         IF l_message.workflow_type = 'TASK'
         THEN
            Tag_Workflow.open_workflow
                                    ( p_message                  => l_message,
                                      p_workflow_history_id      => l_workflow_id,
                                      p_key                      => l_key );
         END IF;
      ELSE
         IF NVL ( l_continue, 'Y' ) = 'Y'
         THEN
            l_key := l_message.original_msgid;

            IF l_key IS NULL
            THEN
               Tag_Error.raise_app_error ( -20000,
                                           'Key value is not supplied',
                                           TRUE );
            END IF;

            l_workflow_id := get_workflow_id;
            l_message.original_msgid := TO_CHAR ( l_workflow_id );
            Tag_Workflow.create_team_messages ( l_message );
            Tag_Workflow.open_workflow
                                     ( p_message                  => l_message,
                                       p_workflow_history_id      => l_workflow_id,
                                       p_key                      => l_key );
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                ( p_error_code         => SQLCODE,
                  p_log                => TRUE,
                  p_user_module        => 'TAG_WORKFLOW.WORKFLOW_MESSAGE_HANDLER',
                  p_user_message       =>    ' TRIGGER_REASON:'
                                          || l_message.trigger_reason
                                          || ' TRIGGER_NAME:'
                                          || l_message.work_trigger,
                  p_user_location      => 'EXCEPTION',
                  p_reraise            => TRUE );
   END;

   PROCEDURE send_message (
      p_queue_name       IN       VARCHAR2 DEFAULT 'TAGWF1.TAG_WF_ROUTER',
      p_message          IN OUT   tag_wf_message,
      p_original_msgid   IN       VARCHAR2 DEFAULT NULL,
      p_priority         IN       PLS_INTEGER DEFAULT 10,
      p_message_handle   OUT      RAW )
   IS
      l_enqueue_options      DBMS_AQ.enqueue_options_t;
      l_message_properties   DBMS_AQ.message_properties_t;
   BEGIN
      IF p_original_msgid IS NOT NULL
      THEN
         p_message.original_msgid := p_original_msgid;
      END IF;

      IF p_message.staff_id IS NOT NULL
      THEN
         l_message_properties.correlation := p_message.staff_id;
      ELSIF p_message.team_id IS NOT NULL
      THEN
         l_message_properties.correlation := p_message.team_id;
      END IF;

      /* 20-Feb-2009
      Staff Memos can require  acknowledgement receipt to be sent to the original sender.
      The original sender has to NOT be overridden.
      */
      IF p_message.sender_id IS NULL THEN
         p_message.sender_id := USER;
      END IF;

      l_message_properties.expiration := c_message_life;
      l_message_properties.priority := p_priority;
      DBMS_AQ.enqueue ( queue_name              => p_queue_name,
                        enqueue_options         => l_enqueue_options,
                        message_properties      => l_message_properties,
                        payload                 => p_message,
                        msgid                   => p_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         RAISE;
   END send_message;

   FUNCTION read_queue_msgid ( p_queue_name IN VARCHAR2, p_msgid IN VARCHAR2 )
      RETURN tag_wf_message
   IS
      l_dequeue_options      DBMS_AQ.dequeue_options_t;
      l_message_properties   DBMS_AQ.message_properties_t;
      l_message              tag_wf_message;
      l_msgid                RAW ( 16 );
   BEGIN
      l_dequeue_options.consumer_name := 'ENDUSER';
      l_dequeue_options.dequeue_mode := DBMS_AQ.browse;
      l_dequeue_options.WAIT := DBMS_AQ.no_wait;
      l_dequeue_options.visibility := DBMS_AQ.on_commit;
      l_dequeue_options.navigation := DBMS_AQ.first_message;
      l_dequeue_options.msgid := HEXTORAW ( p_msgid );
      DBMS_AQ.dequeue ( queue_name              => p_queue_name,
                        dequeue_options         => l_dequeue_options,
                        message_properties      => l_message_properties,
                        payload                 => l_message,
                        msgid                   => l_msgid );
      RETURN ( l_message );
   EXCEPTION
      WHEN e_empty_queue
      THEN
         Tag_Error.raise_app_error
                              ( -20000,
                                'Cannot find message - message queue empty.',
                                TRUE );
      WHEN e_no_messages
      THEN
         Tag_Error.raise_app_error ( -20001,
                                     'Cannot find message - maybe locked.',
                                     TRUE );
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   FUNCTION get_queue_msgid (
      p_queue_name       IN   VARCHAR2,
      p_msgid            IN   VARCHAR2,
      p_remove_message   IN   BOOLEAN DEFAULT TRUE )
      RETURN tag_wf_message
   IS
      l_message   tag_wf_message;
   BEGIN
      IF p_remove_message
      THEN
         l_message := ack_queue_msgid ( p_queue_name, p_msgid );
      ELSE
         l_message := read_queue_msgid ( p_queue_name, p_msgid );
      END IF;

      RETURN ( l_message );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE lock_queue (
      p_queue_name   IN   VARCHAR2,
      p_msgid        IN   VARCHAR2,
      p_wait_time    IN   PLS_INTEGER DEFAULT 0 )
   IS
      e_not_found    EXCEPTION;
      PRAGMA EXCEPTION_INIT ( e_not_found, -20000 );
      l_sqlbuffer    VARCHAR2 ( 1000 );
      l_table_name   VARCHAR2 ( 40 );
      l_user         VARCHAR2 ( 30 );
      l_queue        VARCHAR2 ( 30 );
      l_return       PLS_INTEGER;
      l_msgid        RAW ( 16 )        := HEXTORAW ( p_msgid );
      l_message      tag_wf_message;
   BEGIN
      l_user := SUBSTR ( p_queue_name, 1, INSTR ( p_queue_name, '.' ) - 1 );
      l_queue := SUBSTR ( p_queue_name, INSTR ( p_queue_name, '.' ) + 1 );

      SELECT queue_table
        INTO l_table_name
        FROM all_queues
       WHERE owner = l_user
         AND NAME = l_queue;

      l_sqlbuffer :=
            'SELECT 1 FROM '
         || l_user
         || '.'
         || l_table_name
         || ' WHERE MSGID = :msgid FOR UPDATE WAIT '
         || TO_CHAR ( p_wait_time );

      BEGIN
         EXECUTE IMMEDIATE l_sqlbuffer
                      INTO l_return
                     USING IN p_msgid;

         IF l_return IS NULL
         THEN
            RAISE e_not_found;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            RAISE e_not_found;
         WHEN OTHERS
         THEN
            IF SQLCODE = -30006
            THEN
               Tag_Error.raise_app_error
                             ( -20002,
                                  'Can not lock the record - Timeout after '
                               || TO_CHAR ( p_wait_time )
                               || ' seconds.',
                               TRUE );
            ELSE
               RAISE;
            END IF;
      END;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE lock_queue ( p_queue_name IN VARCHAR2, p_msgid IN VARCHAR2 )
   IS
   BEGIN
      lock_queue ( p_queue_name, p_msgid, 1 );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE browse_queue (
      p_staff_id           IN       NUMBER DEFAULT NULL,
      p_team_id            IN       NUMBER DEFAULT NULL,
      p_type               IN       VARCHAR2 DEFAULT 'ROUTER',
      p_search             IN       VARCHAR2 DEFAULT NULL,
      p_event_date         IN       DATE DEFAULT NULL,
      p_offender_book_id   IN       NUMBER DEFAULT NULL,
      p_due_date           IN       DATE DEFAULT NULL,
      p_queue              IN OUT   refcur_qs )
   IS
      l_search_condition   VARCHAR2 ( 1000 )             := NULL;
      l_queue_name         VARCHAR2 ( 50 )               := NULL;
      l_queue_cluster_id   TEAMS.queue_cluster_id%TYPE;
      l_correlation        VARCHAR2 ( 50 );
   BEGIN
      IF p_type = 'ROUTER'
      THEN
         l_queue_name := 'TAGWF1.TAG_WF_ROUTER';
      ELSE
         l_queue_name :=
            get_queue_name ( p_staff_id           => p_staff_id,
                             p_team_id            => p_team_id,
                             p_workflow_type      => p_type );

         IF p_staff_id IS NOT NULL
         THEN
            l_search_condition := 'tab.user_data.staff_id=' || p_staff_id;
            l_correlation := p_staff_id;
         ELSIF p_team_id IS NOT NULL
         THEN
            l_search_condition := 'tab.user_data.team_id=' || p_team_id;
            l_correlation := p_team_id;
         END IF;
      END IF;

      IF l_queue_name IS NULL
      THEN
         RETURN;
      END IF;

      IF p_event_date IS NOT NULL
      THEN
         l_correlation := NULL;

         IF l_search_condition IS NOT NULL
         THEN
            l_search_condition :=
                  l_search_condition
               || ' AND trunc(tab.user_data.spare_date)=''' --#6247
               || TRUNC ( p_event_date )
               || '''';
         ELSE
            l_search_condition :=
                  'trunc(tab.user_data.spare_date)=''' -- #6247
               || TRUNC ( p_event_date )
               || '''';
         END IF;
      END IF;

      IF p_due_date IS NOT NULL
      THEN
         l_correlation := NULL;

         IF l_search_condition IS NOT NULL
         THEN
            l_search_condition :=
                  l_search_condition
               || ' AND trunc(tab.user_data.due_date)='''
               || TRUNC ( p_due_date )
               || '''';
         ELSE
            l_search_condition :=
                  'trunc(tab.user_data.due_date)='''
               || TRUNC ( p_due_date )
               || '''';
         END IF;
      END IF;

      IF p_offender_book_id IS NOT NULL
      THEN
         l_correlation := NULL;

         IF l_search_condition IS NOT NULL
         THEN
            l_search_condition :=
                  l_search_condition
               || ' AND tab.user_data.offender_book_id='
               || p_offender_book_id;
         ELSE
            l_search_condition :=
                      'tab.user_data.offender_book_id=' || p_offender_book_id;
         END IF;
      END IF;

      IF p_search IS NOT NULL
      THEN
         l_correlation := NULL;

         IF l_search_condition IS NOT NULL
         THEN
            l_search_condition := l_search_condition || ' AND ' || p_search;
         ELSE
            l_search_condition := p_search;
         END IF;
      END IF;

      OPEN p_queue
       FOR
          SELECT   queue_name, team_id, staff_id, team_member_id, work_id,
                   offender_book_id, assignment_date, due_date, msgid,
                   MESSAGE_TEXT, workflow_type, ORIGINAL_MSGID,   -- added ORIGINAL_MSGID  for Defect 4458.
        				   event_date,function_type,  -- added Niko
                   severity_code, acknowledgement_required, -- 20-Feb-2009 Staff Memos enhancement
                   acknowledgement_subject, sender_id -- 20-Feb-2009 Staff Memos enhancement
              FROM TABLE ( Tag_Workflow.scan_queue ( l_queue_name,
                                                     l_search_condition,
                                                     l_correlation ) )
          ORDER BY assignment_date DESC, due_date;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   FUNCTION scan_queue (
      p_queue               IN   VARCHAR2 DEFAULT 'TAGWF1.TAG_WF_ROUTER',
      p_search_conditions   IN   VARCHAR2 DEFAULT NULL,
      p_correlation         IN   VARCHAR2 DEFAULT NULL )
      RETURN queue_set PIPELINED
   IS
      l_dequeue_options      DBMS_AQ.dequeue_options_t;
      l_message_properties   DBMS_AQ.message_properties_t;
      l_message_handle       RAW ( 16 );
      l_message              tag_wf_message;
      l_outrec               queue_rec;
   BEGIN
      l_dequeue_options.consumer_name := 'ENDUSER';
      l_dequeue_options.dequeue_mode := DBMS_AQ.browse;
      l_dequeue_options.visibility := DBMS_AQ.IMMEDIATE;
      l_dequeue_options.WAIT := DBMS_AQ.no_wait;
      l_dequeue_options.navigation := DBMS_AQ.first_message;

      IF p_search_conditions IS NOT NULL
      THEN
         l_dequeue_options.deq_condition := p_search_conditions;
      ELSIF p_correlation IS NOT NULL
      THEN
         l_dequeue_options.correlation := p_correlation;
      END IF;

      WHILE TRUE
      LOOP
         DBMS_AQ.dequeue ( queue_name              => p_queue,
                           dequeue_options         => l_dequeue_options,
                           message_properties      => l_message_properties,
                           payload                 => l_message,
                           msgid                   => l_message_handle );
         l_dequeue_options.navigation := DBMS_AQ.next_message;

         IF l_message.trigger_reason IS NULL
         THEN
            l_outrec.workflow_type := l_message.workflow_type;
            l_outrec.team_id := l_message.team_id;
            l_outrec.staff_id := l_message.staff_id;
            l_outrec.team_member_id := l_message.team_member_id;
            l_outrec.work_id := l_message.work_id;
            l_outrec.offender_book_id := l_message.offender_book_id;
            l_outrec.assignment_date := l_message.assignment_date;
			l_outrec.event_date := l_message.spare_date; -- #6247
            l_outrec.due_date := l_message.due_date;
            l_outrec.msgid := RAWTOHEX ( l_message_handle );
            l_outrec.MESSAGE_TEXT := l_message.MESSAGE_TEXT;
            l_outrec.queue_name := p_queue;
            l_outrec.ORIGINAL_MSGID := l_message.ORIGINAL_MSGID;  -- added for Defect 4458.
            l_outrec.severity_code := l_message.note_source_code; -- 20-Feb-2009 Staff Memos enhancement
            l_outrec.acknowledgement_required := l_message.spare_number; -- 20-Feb-2009 Staff Memos enhancement
            l_outrec.acknowledgement_subject := l_message.complete_comment_text; -- 20-Feb-2009 Staff Memos enhancement
            l_outrec.sender_id := l_message.sender_id; -- 20-Feb-2009 Staff Memos enhancement
            PIPE ROW ( l_outrec );
         END IF;
      END LOOP;
   EXCEPTION
      WHEN e_no_messages
      THEN
         RETURN;
      WHEN e_empty_queue
      THEN
         RETURN;
      WHEN OTHERS
      THEN
         RAISE;
   END;

 -- QC 11384 NDB New routine for PTI
 
   PROCEDURE update_ptr_assignment  (
      p_ptr_id                offender_ptr.ptr_id%TYPE,
      p_staff_id              staff_members.staff_id%TYPE,
      p_assignment_date DATE)
   IS
      lv_ptr_id offender_ptr.ptr_id%TYPE;
      CURSOR opd_cur
      IS
         SELECT ptr_id
           FROM offender_ptr
         WHERE ptr_id = p_ptr_id
         FOR UPDATE OF ptr_id WAIT 1;
   BEGIN
      OPEN opd_cur;
      FETCH opd_cur
       INTO lv_ptr_id;
      UPDATE offender_ptr
         SET assign_receiv_staff_id = p_staff_id,
             assign_receiv_staff_date = p_assignment_date
       WHERE ptr_id = p_ptr_id;
      CLOSE opd_cur;  
    EXCEPTION
      WHEN OTHERS
      THEN
         IF SQLCODE = -30006
         THEN
            tag_error.raise_app_error ( -20951, 'offender_ptr record is locked.' );
         ELSE
         tag_error.handle;
         END IF;
   END;  

 -- QC 11384 NDB New routine for PTI
    
   FUNCTION check_custom_work (
      p_work_id           IN works.work_id%TYPE,
      p_work_type         IN works.work_type%TYPE,
      p_work_sub_type     IN works.work_sub_type%TYPE
   )
   RETURN BOOLEAN
   IS 
     lv_work_id works.work_id%TYPE;
     CURSOR wrk_cur
      IS
      SELECT work_id 
         FROM works
        WHERE work_id = p_work_id
             AND work_type = p_work_type 
             AND work_sub_type = p_work_sub_type;
   BEGIN
      OPEN wrk_cur;
      FETCH wrk_cur INTO lv_work_id;
      CLOSE wrk_cur;
      
      IF lv_work_id IS NOT NULL
      THEN
         RETURN (TRUE);
      ELSE
         RETURN (FALSE);
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;
   
   -- QC 11384 NDB New routine for PTI
   
   PROCEDURE custom_assignment_processing (p_message IN tag_wf_message)
   IS
     lv_assignment_date DATE;   
   BEGIN
         IF p_message.work_trigger = 'EXT_OWN_PTR'  AND
             check_custom_work(p_message.work_id, 'CA', 'PTR')
         THEN

               
               IF p_message.staff_id IS NULL
               THEN
                   lv_assignment_date := NULL;
               ELSE
                   lv_assignment_date :=  p_message.assignment_date;
               END IF;
                                
               update_ptr_assignment (
                  p_message.note_source_code,
                  p_message.staff_id,
                  lv_assignment_date);
          END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;         
   
   PROCEDURE reassign_to_team (
      p_queue_name       IN   VARCHAR2,
      p_msgid            IN   VARCHAR2,
      p_new_team_id      IN   TEAM_MEMBERS.team_id%TYPE,
      p_remove_message   IN   BOOLEAN DEFAULT TRUE )
   IS
      l_new_team_cluster_id   TEAMS.queue_cluster_id%TYPE;
      l_new_team_queue_name   VARCHAR2 ( 50 );
      l_message               tag_wf_message;
      l_message_handle        RAW ( 16 );
   BEGIN
      l_new_team_cluster_id := get_cluster ( p_team_id => p_new_team_id );
      l_new_team_queue_name :=
            'TAGWFC'
         || l_new_team_cluster_id
         || '.TAG_WF_GENERAL_C'
         || l_new_team_cluster_id;
      l_message :=
         get_queue_msgid ( p_queue_name          => p_queue_name,
                           p_msgid               => p_msgid,
                           p_remove_message      => p_remove_message );
      l_message.trigger_reason := 'RASSIGNTEAM';
      l_message.team_id := p_new_team_id;
      l_message.staff_id := NULL;
      l_message.team_member_id := NULL;
      l_message.assignment_date := SYSDATE;

      /* 20-Feb-2009 Staff Memos enhancement
      Reset the information related to acknowledgement receipt.

      The acknowledgement receipt is sent only by the first recipients of the work item:
         - if the work item (memo) was sent to a team/teams, the receipt is sent when the team leads reassigns the work to a another team,
           completes it manually or assigns it to staff. Subsequent recipients will send no receipt.
         - if the work item (memo) was sent directly to a staff/staffs, the receipt is sent when the work item (memo) is completed
      */
      l_message.spare_number            := NULL;
      l_message.complete_comment_text := NULL;

      send_message ( p_queue_name          => l_new_team_queue_name,
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
                     
      -- QC 11384 NDB for PTI                       
      custom_assignment_processing(l_message);

   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE reassign_to_team_member (
      p_queue_name           IN   VARCHAR2,
      p_msgid                IN   VARCHAR2,
      p_new_team_member_id   IN   TEAM_MEMBERS.team_member_id%TYPE,
      p_remove_message       IN   BOOLEAN DEFAULT TRUE )
   IS
      l_new_staff_queue_name   VARCHAR2 ( 50 );
      l_message                tag_wf_message;
      l_new_team_cluster_id    TEAMS.queue_cluster_id%TYPE;
      l_message_handle         RAW ( 16 );
      l_new_team_id            TEAMS.team_id%TYPE;
      l_new_staff_id           STAFF_MEMBERS.staff_id%TYPE;
   BEGIN
      SELECT team_id, staff_id
        INTO l_new_team_id, l_new_staff_id
        FROM TEAM_MEMBERS
       WHERE team_member_id = p_new_team_member_id;

      l_new_team_cluster_id := get_cluster ( p_team_id => l_new_team_id );
      l_message :=
         get_queue_msgid ( p_queue_name          => p_queue_name,
                           p_msgid               => p_msgid,
                           p_remove_message      => p_remove_message );
      l_message.trigger_reason := 'ASSIGNSTAFF';
      l_message.staff_id := l_new_staff_id;
      l_message.team_id := l_new_team_id;
      l_message.team_member_id := p_new_team_member_id;
      l_message.assignment_date := SYSDATE;
      l_new_staff_queue_name :=
            'TAGWFC'
         || l_new_team_cluster_id
         || '.TAG_WF_GENERAL_C'
         || l_new_team_cluster_id;
      send_message ( p_queue_name          => l_new_staff_queue_name,
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
                     
      -- QC 11384 NDB for PTI                    
      custom_assignment_processing(l_message);
      
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE assign_to_team_member (
      p_queue_name       IN   VARCHAR2,
      p_msgid            IN   VARCHAR2,
      p_team_member_id   IN   TEAM_MEMBERS.team_member_id%TYPE,
      p_remove_message   IN   BOOLEAN DEFAULT TRUE )
   IS
      l_team_cluster_id   TEAMS.queue_cluster_id%TYPE;
      l_message           tag_wf_message;
      l_message_handle    RAW ( 16 );
      l_team_id           TEAMS.team_id%TYPE;
      l_staff_id          STAFF_MEMBERS.staff_id%TYPE;
   BEGIN
      SELECT staff_id, team_id
        INTO l_staff_id, l_team_id
        FROM TEAM_MEMBERS
       WHERE team_member_id = p_team_member_id;

      l_message :=
         get_queue_msgid ( p_queue_name          => p_queue_name,
                           p_msgid               => p_msgid,
                           p_remove_message      => p_remove_message );
      l_message.trigger_reason := 'ASSIGNSTAFF';
      l_message.staff_id := l_staff_id;
      l_message.team_id := l_team_id;
      l_message.team_member_id := p_team_member_id;
      l_message.assignment_date := SYSDATE;

      /* 20-Feb-2009 Staff Memos enhancement
      Reset the information related to acknowledgement receipt.

      The acknowledgement receipt is sent only by the first recipients of the work item:
         - If the work item (memo) was sent to a team/teams, the receipt is sent when the team leads reassigns the work to a another team,
           completes it manually or assigns it to staff. Subsequent recipients will send no receipt.
         - If the work item (memo) was sent directly to a staff/staffs, the receipt is sent when the work item (memo) is completed.
      */
      l_message.spare_number          := NULL;
      l_message.complete_comment_text := NULL;

      send_message ( p_queue_name          => p_queue_name,
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
                     
      -- QC 11384 NDB for PTI                           
      custom_assignment_processing(l_message);

   EXCEPTION
      WHEN e_empty_queue
      THEN
         RETURN;
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE assign_to_staff_id ( p_message IN tag_wf_message )
   IS
      l_staff_queue_name   VARCHAR2 ( 50 );
      l_message            tag_wf_message  := p_message;
      l_message_handle     RAW ( 16 );
   BEGIN
      l_staff_queue_name :=
         get_queue_name ( p_staff_id           => l_message.staff_id,
                          p_workflow_type      => l_message.workflow_type );
      l_message.trigger_reason := NULL;
      send_message ( p_queue_name          => l_staff_queue_name,
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );

      IF UPPER ( l_message.workflow_type ) = 'TASK'
      THEN
         Tag_Workflow.adjust_tm_no_of_tasks
                              ( p_team_member_id      => l_message.team_member_id,
                                p_adjustment          => 1 );
         create_task_assignment_hty ( p_msgid        => RAWTOHEX
                                                            ( l_message_handle ),
                                      p_message      => l_message );
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                     ( p_error_code         => SQLCODE,
                       p_log                => TRUE,
                       p_user_module        => 'TAG_WF_WORKFLOW.ASSIGN_TO_STAFF_ID',
                       p_user_message       =>    'MSGID:'
                                               || RAWTOHEX ( l_message_handle ),
                       p_user_location      => 'EXCEPTION',
                       p_reraise            => TRUE );
   END;

   PROCEDURE assign_to_team_id ( p_message IN tag_wf_message )
   IS
      l_team_cluster_id   TEAMS.queue_cluster_id%TYPE;
      l_team_queue_name   VARCHAR2 ( 50 );
      l_message           tag_wf_message                := p_message;
      l_message_handle    RAW ( 16 );
   BEGIN
      l_team_cluster_id := get_cluster ( p_team_id => p_message.team_id );
      l_team_queue_name :=
            'TAGWFC'
         || l_team_cluster_id
         || '.TAG_WF_GENERAL_C'
         || l_team_cluster_id;
      l_message.trigger_reason := NULL;
      send_message ( p_queue_name          => l_team_queue_name,
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );

      IF UPPER ( l_message.workflow_type ) = 'TASK'
      THEN
         create_task_assignment_hty ( p_msgid        => RAWTOHEX
                                                            ( l_message_handle ),
                                      p_message      => l_message );
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                      ( p_error_code         => SQLCODE,
                        p_log                => TRUE,
                        p_user_module        => 'TAG_WF_WORKFLOW.ASSIGN_TO_TEAM_ID',
                        p_user_message       =>    'MSGID:'
                                                || RAWTOHEX ( l_message_handle ),
                        p_user_location      => 'EXCEPTION',
                        p_reraise            => TRUE );
   END;

   PROCEDURE remove_from_queue ( p_queue_name IN VARCHAR2, p_msgid IN VARCHAR2 )
   IS
      l_message   tag_wf_message;
   BEGIN
      l_message :=
         ack_queue_msgid ( p_queue_name      => p_queue_name,
                           p_msgid           => p_msgid );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE complete_workflow (
      p_trigger_name   IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_key            IN   VARCHAR2 DEFAULT NULL,
      p_context        IN   VARCHAR2 DEFAULT 'DEFAULT',
      p_params         IN   XMLTYPE DEFAULT NULL )
   IS
      l_message          tag_wf_message
         := tag_wf_message ( NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL );
      l_message_handle   RAW ( 16 );
   BEGIN
      IF     p_key IS NULL
         AND p_params IS NULL
      THEN
         Tag_Error.raise_app_error
                                  ( -20000,
                                    'p_key and p_params cannot both be null',
                                    TRUE );
      END IF;

      l_message.original_msgid := p_key;
      l_message.work_trigger := p_trigger_name;
      l_message.workflow_type := p_context;
      l_message.assignment_date := SYSDATE;

      IF p_params IS NOT NULL
      THEN
         l_message.MESSAGE_TEXT := p_params.getstringval ( );
      END IF;

      l_message.trigger_reason := 'AUTOCOMP';
      send_message ( p_queue_name          => 'TAGWF2.TAG_WF_AUTO_COMP',
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE complete_task (
      p_queue_name              IN   VARCHAR2,
      p_msgid                   IN   VARCHAR2,
      p_complete_reason_code    IN   VARCHAR2,
      p_complete_comment_text   IN   VARCHAR2,
      p_complete_user_id        IN   VARCHAR2 )
   IS
      l_message          tag_wf_message;
      l_message_handle   RAW ( 16 );
      lv_event_id        NUMBER;

      CURSOR c1
      IS
         SELECT event_id
         FROM orders
         WHERE offender_book_id = l_message.OFFENDER_BOOK_ID
         AND workflow_id = l_message.ORIGINAL_MSGID
         FOR UPDATE OF order_status;
   BEGIN
      l_message := ack_queue_msgid ( p_queue_name      => p_queue_name,
                                     p_msgid           => p_msgid );

      OPEN c1;
      FETCH c1 INTO lv_event_id;
      IF c1%FOUND THEN
         UPDATE orders
         SET order_status = 'C', complete_date = sysdate
         WHERE offender_book_id = l_message.OFFENDER_BOOK_ID
         AND workflow_id = l_message.ORIGINAL_MSGID;
      END IF;
      CLOSE c1;

      l_message.complete_reason_code := p_complete_reason_code;
      l_message.complete_comment_text := p_complete_comment_text;
      l_message.complete_user_id := p_complete_user_id;
      l_message.assignment_date := SYSDATE;
      l_message.trigger_reason := 'MANUALCOMP';
      send_message ( p_queue_name          => 'TAGWF2.TAG_WF_AUTO_COMP',
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE insert_work_message (
      p_message       IN       queue_rec,
      p_workflow_id   OUT      PLS_INTEGER )
   IS
      l_staff_id         STAFF_MEMBERS.staff_id%TYPE;
      l_cluster_id       TEAMS.queue_cluster_id%TYPE;
      l_queue_name       VARCHAR2 ( 50 );
      l_workflow_type    VARCHAR2 ( 12 );
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
         := tag_wf_message ( NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL );
   BEGIN
      IF p_message.team_member_id IS NOT NULL
      THEN
         SELECT staff_id
           INTO l_staff_id
           FROM TEAM_MEMBERS
          WHERE team_member_id = p_message.team_member_id;
      END IF;

      IF p_message.workflow_type IS NULL
      THEN
         Tag_Error.raise_app_error
            ( -20000,
              'tag_workflow.insert_work_message: Workflow Type must be specified',
              TRUE );
      END IF;

      l_queue_name := 'TAGWF1.TAG_WF_ROUTER';
      l_message.team_id := p_message.team_id;

      /* 20-Feb-2009
      Team member supersedes staff.
      For Staff memos generated by screen "Staff Memos", the member is not provided but a staff is provided.
      In this case, the staff should not be overridden with NULL.
      */
      IF p_message.team_member_id IS NOT NULL THEN
         l_message.staff_id := l_staff_id;
      ELSIF p_message.staff_id IS NOT NULL THEN
         l_message.staff_id := p_message.staff_id;
      END IF;

      l_message.MESSAGE_TEXT := p_message.MESSAGE_TEXT;
      l_message.offender_book_id := p_message.offender_book_id;
      l_message.assignment_date := SYSDATE;
	    l_message.spare_date := NVL(p_message.event_date, SYSDATE); --#6247
      l_message.work_id := p_message.work_id;
      l_message.due_date := p_message.due_date;
      l_message.trigger_reason := 'WORKMESSAGE';
      l_message.work_trigger := 'WORKMESSAGE';
      l_message.workflow_type := p_message.workflow_type;
	    p_workflow_id := get_workflow_id;
      l_message.original_msgid := TO_CHAR(p_workflow_id);
      l_message.SPARE_TEXT := p_message.function_type;

      /* 20-Feb-2009
      Staff Memos enhancement
      */
      l_message.note_source_code := p_message.severity_code;
      l_message.spare_number := p_message.acknowledgement_required; --1 if required, NULL otherwise
      l_message.complete_comment_text := p_message.acknowledgement_subject;

      send_message ( p_queue_name          => l_queue_name,
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );

   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE forward_work_message ( p_message IN OUT tag_wf_message )
   IS
      l_queue_name       VARCHAR2 ( 50 );
      l_cluster_id       TEAMS.queue_cluster_id%TYPE;
      l_message_handle   RAW ( 16 );
   BEGIN
      l_queue_name :=
         get_queue_name ( p_staff_id           => p_message.staff_id,
                          p_team_id            => p_message.team_id,
                          p_workflow_type      => p_message.workflow_type );
      p_message.trigger_reason := NULL;
      send_message ( p_queue_name          => l_queue_name,
                     p_message             => p_message,
                     p_original_msgid      => p_message.original_msgid,
                     p_message_handle      => l_message_handle );

      /* 20-Feb-2009
        Consistent with the rest of the application, only tasks generate records in table task_assignments_hty.
        This approach also allows for using the same procedure to send Staff Memos that do not always provide
        booking information (mandatory for table task_assignments_hty).
      */
      IF UPPER ( p_message.workflow_type ) = 'TASK'
      THEN
      create_task_assignment_hty
                     ( p_msgid               => RAWTOHEX ( l_message_handle ),
                       p_message             => p_message,
                       p_original_msgid      => RAWTOHEX
                                                    ( p_message.original_msgid ) );
      END IF;
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                   ( p_error_code         => SQLCODE,
                     p_log                => TRUE,
                     p_user_module        => 'TAG_WF_WORKFLOW.FORWARD_WORK_MESSAGE',
                     p_user_message       =>    'MSGID:'
                                             || RAWTOHEX ( l_message_handle ),
                     p_user_location      => 'EXCEPTION',
                     p_reraise            => TRUE );
   END;

   FUNCTION scan_all_queue (
      p_queue               IN   VARCHAR2 DEFAULT 'TAGWF1.TAG_WF_ROUTER',
      p_search_conditions   IN   VARCHAR2 DEFAULT NULL )
      RETURN queue_set PIPELINED
   IS
      l_dequeue_options      DBMS_AQ.dequeue_options_t;
      l_message_properties   DBMS_AQ.message_properties_t;
      l_message_handle       RAW ( 16 );
      l_message              tag_wf_message;
      l_outrec               queue_rec;
   BEGIN
      l_dequeue_options.consumer_name := 'ENDUSER';
      l_dequeue_options.dequeue_mode := DBMS_AQ.browse;
      l_dequeue_options.visibility := DBMS_AQ.IMMEDIATE;
      l_dequeue_options.WAIT := DBMS_AQ.no_wait;
      l_dequeue_options.navigation := DBMS_AQ.first_message;

      IF p_search_conditions IS NOT NULL
      THEN
         l_dequeue_options.deq_condition := p_search_conditions;
      END IF;

      WHILE TRUE
      LOOP
         DBMS_AQ.dequeue ( queue_name              => p_queue,
                           dequeue_options         => l_dequeue_options,
                           message_properties      => l_message_properties,
                           payload                 => l_message,
                           msgid                   => l_message_handle );
         l_dequeue_options.navigation := DBMS_AQ.next_message;
         l_outrec.workflow_type := l_message.workflow_type;
         l_outrec.team_id := l_message.team_id;
         l_outrec.staff_id := l_message.staff_id;
         l_outrec.team_member_id := l_message.team_member_id;
         l_outrec.work_id := l_message.work_id;
         l_outrec.offender_book_id := l_message.offender_book_id;
         l_outrec.assignment_date := l_message.assignment_date;
		 l_outrec.event_date := l_message.spare_date; -- #6247
         l_outrec.due_date := l_message.due_date;
         l_outrec.msgid := RAWTOHEX ( l_message_handle );
         l_outrec.MESSAGE_TEXT := l_message.MESSAGE_TEXT;
         l_outrec.queue_name := p_queue;
         l_outrec.severity_code := l_message.note_source_code; -- 20-Feb-2009 Staff Memos enhancement
         l_outrec.acknowledgement_required := l_message.spare_number; -- 20-Feb-2009 Staff Memos enhancement
         l_outrec.acknowledgement_subject := l_message.complete_comment_text; -- 20-Feb-2009 Staff Memos enhancement
         l_outrec.sender_id := l_message.sender_id; -- 20-Feb-2009 Staff Memos enhancement
         PIPE ROW ( l_outrec );
      END LOOP;
   EXCEPTION
      WHEN e_no_messages
      THEN
         RETURN;
      WHEN e_empty_queue
      THEN
         RETURN;
      WHEN OTHERS
      THEN
         RAISE;
   END;

   PROCEDURE browse_work_message (
      p_original_msgid   IN       VARCHAR2,
      p_queue            IN OUT   refcur_qs )
   IS
      l_staff_id           STAFF_MEMBERS.staff_id%TYPE;
      l_team_id            TEAMS.team_id%TYPE;
      l_queue_name         VARCHAR2 ( 50 );
      l_search_condition   VARCHAR2 ( 200 );

      CURSOR c_latest
      IS
         SELECT   staff_id, team_id
             FROM TASK_ASSIGNMENT_HTY
            WHERE message_id = p_original_msgid
         ORDER BY task_assignment_hty_id DESC;
   BEGIN
      OPEN c_latest;

      FETCH c_latest
       INTO l_staff_id, l_team_id;

      CLOSE c_latest;

      l_queue_name :=
          get_queue_name ( p_staff_id      => l_staff_id,
                           p_team_id       => l_team_id );
      l_search_condition :=
                'tab.user_data.original_msgid=''' || p_original_msgid || '''';

      OPEN p_queue
       FOR
          SELECT queue_name, team_id, staff_id, team_member_id, work_id,
                 offender_book_id, assignment_date, due_date, msgid,
                 MESSAGE_TEXT, workflow_type, ORIGINAL_MSGID,
				         event_date,function_type,  -- added Niko
                 severity_code, acknowledgement_required, -- 20-Feb-2009 Staff Memos enhancement
                 acknowledgement_subject, sender_id -- 20-Feb-2009 Staff Memos enhancement
            FROM TABLE ( Tag_Workflow.scan_all_queue ( l_queue_name,
                                                       l_search_condition ) );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE find_and_lock_message (
      p_msgid        IN       VARCHAR2,
      p_complete     OUT      BOOLEAN,
      p_queue_name   OUT      VARCHAR2 )
   IS
      CURSOR queue_cur
      IS
         SELECT owner || '.' || NAME queue_name
           FROM all_queues
          WHERE owner LIKE 'TAGWFC%'
            AND (    NAME LIKE 'TAG%WF%PERSONAL%T%C%'
                  OR NAME LIKE 'TAG%WF%GENERAL%C%' );
   BEGIN
      p_complete := FALSE;

      FOR queue_rec IN queue_cur
      LOOP
         BEGIN
            -- lock queue record, wait 5 minutes
            lock_queue ( queue_rec.queue_name, p_msgid, 300 );
            p_complete := TRUE;
            p_queue_name := queue_rec.queue_name;
            EXIT;
         EXCEPTION
            WHEN OTHERS
            THEN
               IF SQLCODE = -20000
               THEN
                  -- record not found on queue
                  NULL;
               ELSIF SQLCODE = -20001
               THEN
                  /* timout occurred, raise an error and skip the record */
                  Tag_Error.handle
                     ( p_error_code         => SQLCODE,
                       p_log                => TRUE,
                       p_user_module        => 'TAG_WF_WORKFLOW.FIND_AND_LOCK_MESSAGE',
                       p_user_message       =>    'Failed to complete MSGID:'
                                               || p_msgid
                                               || '. Lock Timeout exceeded.',
                       p_user_location      => 'EXCEPTION',
                       p_reraise            => FALSE );
                  EXIT;
               ELSE
                  RAISE;
               END IF;
         END;
      END LOOP;
   END;

   PROCEDURE complete_original_task ( p_message IN OUT tag_wf_message )
   IS
      CURSOR c_latest (
         p_workflow_history_id   TASK_ASSIGNMENT_HTY.workflow_history_id%TYPE )
      IS
         SELECT message_id
           FROM TASK_ASSIGNMENT_HTY h
          WHERE ( h.work_id, h.task_assignment_hty_id ) IN (
                            SELECT   work_id, MAX ( task_assignment_hty_id )
                                FROM TASK_ASSIGNMENT_HTY
                               WHERE workflow_history_id =
                                                        p_workflow_history_id
                            GROUP BY work_id )
            AND h.workflow_history_id = p_workflow_history_id
            AND h.completion_date IS NULL;

      l_staff_id     STAFF_MEMBERS.staff_id%TYPE;
      l_team_id      TEAMS.team_id%TYPE;
      l_queue_name   VARCHAR2 ( 50 );
      l_msgid        VARCHAR2 ( 64 );
      l_complete     BOOLEAN                       := TRUE;
      l_message      tag_wf_message;
   BEGIN
      l_message := p_message;

      -- check we have got all the required data
      IF l_message.original_msgid IS NULL
      THEN
         Tag_Error.raise_app_error
            ( p_error_code         => -20000,
              p_error_message      => 'No value found for original message id in tag_workflow',
              p_stack_trace        => TRUE );
      END IF;

      -- look at history to find location of messages that have been routed
      OPEN c_latest ( TO_NUMBER ( l_message.original_msgid ) );

      LOOP
         FETCH c_latest
          INTO l_msgid;

         EXIT WHEN c_latest%NOTFOUND;
         find_and_lock_message ( l_msgid, l_complete, l_queue_name );

         IF l_complete
         THEN
            complete_msgid ( p_queue_name                => l_queue_name,
                             p_msgid                     => l_msgid,
                             p_complete_reason_code      => 'ACOMP' );
         END IF;
      END LOOP;

      CLOSE c_latest;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                 ( p_error_code         => SQLCODE,
                   p_log                => TRUE,
                   p_user_module        => 'TAG_WF_WORKFLOW.COMPLETE_ORIGINAL_TASK',
                   p_user_message       => 'MSGID:'
                                           || l_message.original_msgid,
                   p_user_location      => 'EXCEPTION',
                   p_reraise            => TRUE );
   END;

   PROCEDURE adjust_tm_no_of_tasks (
      p_team_member_id   IN   TEAM_MEMBERS.team_member_id%TYPE,
      p_adjustment       IN   PLS_INTEGER )
   IS
   BEGIN
      UPDATE TEAM_MEMBERS
         SET no_of_tasks = NVL ( no_of_tasks, 0 ) + p_adjustment
       WHERE team_member_id = p_team_member_id;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE create_workflow (
      p_trigger_name        IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_key                 IN   VARCHAR2 DEFAULT NULL,
      p_context             IN   VARCHAR2 DEFAULT 'DEFAULT',
      p_params              IN   XMLTYPE DEFAULT NULL,
      p_offender_book_id    IN   OFFENDER_BOOKINGS.offender_book_id%TYPE
            DEFAULT NULL,
      p_event_date          IN   DATE DEFAULT NULL,
      p_override_due_date   IN   DATE DEFAULT NULL,
      p_due_date_period     IN   NUMBER DEFAULT NULL )
   IS
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
         := tag_wf_message ( NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL );
   BEGIN
      l_message.offender_book_id := p_offender_book_id;
      l_message.original_msgid := p_key;
      l_message.work_trigger := p_trigger_name;
      l_message.workflow_type := p_context;

      IF p_params IS NOT NULL
      THEN
         l_message.MESSAGE_TEXT := p_params.getstringval ( );
      END IF;

      l_message.sender_id := USER;
      l_message.assignment_date := SYSDATE;
	  l_message.spare_date := NVL(p_event_date, SYSDATE); -- #6247
      l_message.due_date := p_event_date;
      l_message.override_due_date := p_override_due_date;
      l_message.due_date_period := p_due_date_period;
      send_message ( p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE insert_case_note (
      p_offender_book_id     IN   OFFENDER_CASE_NOTES.offender_book_id%TYPE,
      p_staff_id             IN   OFFENDER_CASE_NOTES.staff_id%TYPE,
      p_contact_time         IN   OFFENDER_CASE_NOTES.contact_time%TYPE,
      p_case_note_type       IN   OFFENDER_CASE_NOTES.case_note_type%TYPE,
      p_case_note_sub_type   IN   OFFENDER_CASE_NOTES.case_note_sub_type%TYPE,
      p_case_note_text       IN   OFFENDER_CASE_NOTES.CASE_NOTE_TEXT%TYPE,
      p_note_source_code     IN   OFFENDER_CASE_NOTES.note_source_code%TYPE,
      p_event_id             IN   OFFENDER_CASE_NOTES.event_id%TYPE,
	  p_sender_id            IN   OFFENDER_CASE_NOTES.create_user_id%TYPE ) --#6247
   IS
   BEGIN
      -- #6247
      INSERT INTO OFFENDER_CASE_NOTES
                  ( offender_book_id, case_note_id, staff_id,
                    contact_date, contact_time,
                    case_note_type, case_note_sub_type,
                    CASE_NOTE_TEXT, note_source_code, event_id,
                    date_creation, time_creation, create_user_id )
           VALUES ( p_offender_book_id, case_note_id.NEXTVAL, p_staff_id,
                    TRUNC ( p_contact_time ), p_contact_time,
                    p_case_note_type, p_case_note_sub_type,
                    p_case_note_text, p_note_source_code, p_event_id,
                    TRUNC ( SYSDATE ), SYSDATE, p_sender_id );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                       ( p_error_code         => SQLCODE,
                         p_log                => TRUE,
                         p_user_module        => 'TAG_WF_WORKFLOW.INSERT_CASE_NOTE',
                         p_user_message       => 'EVENT_ID:' || p_event_id,
                         p_user_location      => 'EXCEPTION',
                         p_reraise            => TRUE );
   END;

   PROCEDURE forward_case_note ( p_message IN tag_wf_message )
   IS
      l_staff_id   OFFENDER_CASE_NOTES.staff_id%TYPE;

      CURSOR c_wkflw
      IS
         SELECT w.work_type work_type, w.work_sub_type work_sub_type
           FROM WORK_TRIGGERS t, WORKS w
          WHERE t.trigger_name = p_message.work_trigger
            AND t.work_id = w.work_id
            AND w.workflow_type = 'CNOTE'
            AND t.active_flag = 'Y'
            AND w.active_flag = 'Y';
   BEGIN
      l_staff_id := Sjs_Lib.get_staff_id_from_user ( p_message.sender_id );

      FOR r_wkflw IN c_wkflw
      LOOP
	     -- #6247 Added sender id. Changed source of p_contact time
         insert_case_note ( p_offender_book_id        => p_message.offender_book_id,
                            p_staff_id                => l_staff_id,
                            p_contact_time            => p_message.spare_date,
                            p_case_note_type          => r_wkflw.work_type,
                            p_case_note_sub_type      => r_wkflw.work_sub_type,
                            p_case_note_text          => p_message.MESSAGE_TEXT,
                            p_note_source_code        => p_message.note_source_code,
                            p_event_id                => p_message.event_id,
							p_sender_id               => p_message.sender_id ); --6247
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         IF l_staff_id  is null THEN
             null;
         ELSE 
            Tag_Error.handle
                      ( p_error_code         => SQLCODE,
                        p_log                => TRUE,
                        p_user_module        => 'TAG_WF_WORKFLOW.FORWARD_CASE_NOTE',
                        p_user_message       =>    'EVENT_ID:'
                                                || p_message.event_id,
                        p_user_location      => 'EXCEPTION',
                        p_reraise            => TRUE );
         END IF;
   END;

   PROCEDURE create_case_note (
      p_offender_book_id   IN   OFFENDER_BOOKINGS.offender_book_id%TYPE,
      p_trigger_name       IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_message            IN   VARCHAR2,
      p_event_id           IN   OFFENDER_CASE_NOTES.event_id%TYPE DEFAULT NULL,
	  p_event_date         IN   DATE DEFAULT NULL,
      p_note_source_code   IN   OFFENDER_CASE_NOTES.note_source_code%TYPE
            DEFAULT 'AUTO' )
   IS
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
         := tag_wf_message ( NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL );
   BEGIN
      l_message.offender_book_id := p_offender_book_id;
      l_message.work_trigger := p_trigger_name;
      l_message.sender_id := USER;
      l_message.trigger_reason := 'CASENOTE';
      l_message.workflow_type := 'CASENOTE';
      l_message.assignment_date := SYSDATE;
	  l_message.spare_date := NVL(p_event_date, SYSDATE); --#6247
      l_message.event_id := p_event_id;
      l_message.note_source_code := p_note_source_code;
      l_message.MESSAGE_TEXT := p_message;
      send_message ( p_queue_name          => 'TAGWF1.TAG_WF_ROUTER',
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   FUNCTION search_queue (
      p_queue_name         IN   VARCHAR2,
      p_search_condition   IN   VARCHAR2 )
      RETURN BOOLEAN
   IS
      l_dequeue_options      DBMS_AQ.dequeue_options_t;
      l_message_properties   DBMS_AQ.message_properties_t;
      l_message              tag_wf_message;
      l_msgid                RAW ( 16 );
   BEGIN
      l_dequeue_options.consumer_name := 'ENDUSER';
      l_dequeue_options.WAIT := DBMS_AQ.no_wait;
      l_dequeue_options.dequeue_mode := DBMS_AQ.browse;
      l_dequeue_options.visibility := DBMS_AQ.IMMEDIATE;
      l_dequeue_options.deq_condition := p_search_condition;
      l_dequeue_options.navigation := DBMS_AQ.first_message;
      DBMS_AQ.dequeue ( queue_name              => p_queue_name,
                        dequeue_options         => l_dequeue_options,
                        message_properties      => l_message_properties,
                        payload                 => l_message,
                        msgid                   => l_msgid );
      RETURN ( TRUE );
   EXCEPTION
      WHEN e_empty_queue
      THEN
         RETURN ( FALSE );
      WHEN e_no_messages
      THEN
         RETURN ( FALSE );
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   FUNCTION check_outstanding_task (
      p_offender_book_id   IN   OFFENDER_BOOKINGS.offender_book_id%TYPE )
      RETURN BOOLEAN
   IS
      CURSOR c_queues
      IS
         SELECT owner || '.' || NAME
           FROM all_queues
          WHERE owner LIKE 'TAGWFC%'
            AND (    NAME LIKE 'TAG%WF%PERSONAL%T%C%'
                  OR NAME LIKE 'TAG%WF%GENERAL%C%' );

      l_queue_name         VARCHAR2 ( 70 );
      l_search_condition   VARCHAR2 ( 120 )
         :=    'tab.user_data.offender_book_id='''
            || p_offender_book_id
            || ''' AND '
            || 'tab.user_data.workflow_type=''TASK'' AND '
            || 'tab.user_data.trigger_reason IS NULL';
   BEGIN
      OPEN c_queues;

      LOOP
         FETCH c_queues
          INTO l_queue_name;

         EXIT WHEN NOT c_queues%FOUND;

         IF search_queue ( p_queue_name            => l_queue_name,
                           p_search_condition      => l_search_condition )
         THEN
            CLOSE c_queues;

            RETURN ( TRUE );
         END IF;
      END LOOP;

      CLOSE c_queues;

      RETURN ( FALSE );
   EXCEPTION
      WHEN OTHERS
      THEN
         IF c_queues%ISOPEN
         THEN
            CLOSE c_queues;
         END IF;

         Tag_Error.handle;
   END;

   PROCEDURE insert_workflow_history (
      p_message               IN   tag_wf_message,
      p_status                IN   VARCHAR2,
      p_workflow_history_id   IN   WORKFLOW_HISTORY.workflow_history_id%TYPE,
      p_key                   IN   VARCHAR2 )
   IS
   BEGIN
      INSERT INTO WORKFLOW_HISTORY
                  ( WORKFLOW_HISTORY_ID, KEY, WORK_TRIGGER,
                  ORIG_ORIGINAL_MSGID,
			ORIG_SENDER_ID,
			ORIG_TEAM_ID,
			ORIG_STAFF_ID,
			ORIG_TEAM_MEMBER_ID,
			ORIG_WORK_TRIGGER,
			ORIG_TRIGGER_REASON,
			ORIG_WORK_ID,
			ORIG_OFFENDER_BOOK_ID,
			ORIG_COMPLETE_REASON_CODE,
			ORIG_COMPLETE_COMMENT_TEXT,
			ORIG_COMPLETE_USER_ID,
			ORIG_DUE_DATE_PERIOD,
			ORIG_ASSIGNMENT_DATE,
			ORIG_DUE_DATE,
			ORIG_OVERRIDE_DUE_DATE,
			ORIG_MESSAGE_TEXT,
			ORIG_WORKFLOW_TYPE,
			ORIG_DAYS,
			ORIG_NOTE_SOURCE_CODE,
			ORIG_EVENT_ID,
			ORIG_SPARE_TEXT,
			ORIG_SPARE_NUMBER,
			ORIG_SPARE_DATE,
                  STATUS,
                  REQUEST_DATE )
           VALUES ( p_workflow_history_id, p_key, p_message.work_trigger,
                    DECODE ( p_status, 'C', p_message.ORIGINAL_MSGID, NULL ),
                    DECODE ( p_status, 'C', p_message.SENDER_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.TEAM_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.STAFF_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.TEAM_MEMBER_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.WORK_TRIGGER, NULL ),
                    DECODE ( p_status, 'C', p_message.TRIGGER_REASON, NULL ),
                    DECODE ( p_status, 'C', p_message.WORK_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.OFFENDER_BOOK_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.COMPLETE_REASON_CODE, NULL ),
                    DECODE ( p_status, 'C', p_message.COMPLETE_COMMENT_TEXT, NULL ),
                    DECODE ( p_status, 'C', p_message.COMPLETE_USER_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.DUE_DATE_PERIOD, NULL ),
                    DECODE ( p_status, 'C', p_message.ASSIGNMENT_DATE, NULL ),
                    DECODE ( p_status, 'C', p_message.DUE_DATE, NULL ),
                    DECODE ( p_status, 'C', p_message.OVERRIDE_DUE_DATE, NULL ),
                    DECODE ( p_status, 'C', p_message.MESSAGE_TEXT, NULL ),
                    DECODE ( p_status, 'C', p_message.WORKFLOW_TYPE, NULL ),
                    DECODE ( p_status, 'C', p_message.DAYS, NULL ),
                    DECODE ( p_status, 'C', p_message.NOTE_SOURCE_CODE, NULL ),
                    DECODE ( p_status, 'C', p_message.EVENT_ID, NULL ),
                    DECODE ( p_status, 'C', p_message.SPARE_TEXT, NULL ),
                    DECODE ( p_status, 'C', p_message.SPARE_NUMBER, NULL ),
                    DECODE ( p_status, 'C', p_message.SPARE_DATE, NULL ),
                    p_status,
                    p_message.assignment_date );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;

   PROCEDURE open_workflow (
      p_message               IN OUT   tag_wf_message,
      p_workflow_history_id   IN       WORKFLOW_HISTORY.workflow_history_id%TYPE,
      p_key                   IN       VARCHAR2 )
   IS
      l_workflow_history_id   WORKFLOW_HISTORY.workflow_history_id%TYPE;
      l_message          tag_wf_message
         := tag_wf_message ( NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL );
      l_message_handle        RAW ( 16 );
   BEGIN
      BEGIN
         SELECT WORKFLOW_HISTORY_ID,
                  ORIG_ORIGINAL_MSGID,
			ORIG_SENDER_ID,
			ORIG_TEAM_ID,
			ORIG_STAFF_ID,
			ORIG_TEAM_MEMBER_ID,
			ORIG_WORK_TRIGGER,
			ORIG_TRIGGER_REASON,
			ORIG_WORK_ID,
			ORIG_OFFENDER_BOOK_ID,
			ORIG_COMPLETE_REASON_CODE,
			ORIG_COMPLETE_COMMENT_TEXT,
			ORIG_COMPLETE_USER_ID,
			ORIG_DUE_DATE_PERIOD,
			ORIG_ASSIGNMENT_DATE,
			ORIG_DUE_DATE,
			ORIG_OVERRIDE_DUE_DATE,
			ORIG_MESSAGE_TEXT,
			ORIG_WORKFLOW_TYPE,
			ORIG_DAYS,
			ORIG_NOTE_SOURCE_CODE,
			ORIG_EVENT_ID,
			ORIG_SPARE_TEXT,
			ORIG_SPARE_NUMBER,
			ORIG_SPARE_DATE
           INTO l_workflow_history_id,
                  l_message.ORIGINAL_MSGID,
			l_message.SENDER_ID,
			l_message.TEAM_ID,
			l_message.STAFF_ID,
			l_message.TEAM_MEMBER_ID,
			l_message.WORK_TRIGGER,
			l_message.TRIGGER_REASON,
			l_message.WORK_ID,
			l_message.OFFENDER_BOOK_ID,
			l_message.COMPLETE_REASON_CODE,
			l_message.COMPLETE_COMMENT_TEXT,
			l_message.COMPLETE_USER_ID,
			l_message.DUE_DATE_PERIOD,
			l_message.ASSIGNMENT_DATE,
			l_message.DUE_DATE,
			l_message.OVERRIDE_DUE_DATE,
			l_message.MESSAGE_TEXT,
			l_message.WORKFLOW_TYPE,
			l_message.DAYS,
			l_message.NOTE_SOURCE_CODE,
			l_message.EVENT_ID,
			l_message.SPARE_TEXT,
			l_message.SPARE_NUMBER,
			l_message.SPARE_DATE
           FROM WORKFLOW_HISTORY
          WHERE KEY = p_key
            AND work_trigger = p_message.work_trigger
            AND status = 'C'
            AND request_date > p_message.assignment_date
            AND ROWNUM = 1;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            l_workflow_history_id := NULL;
         WHEN OTHERS
         THEN
            RAISE;
      END;

      IF l_workflow_history_id IS NULL
      THEN
         insert_workflow_history ( p_message,
                                   'O',
                                   p_workflow_history_id,
                                   p_key );
      ELSE
         send_message ( p_queue_name          => 'TAGWF2.TAG_WF_AUTO_COMP',
                        p_message             => l_message,
                        p_message_handle      => l_message_handle );
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                       ( p_error_code         => SQLCODE,
                         p_log                => TRUE,
                         p_user_module        => 'TAG_WF_WORKFLOW.OPEN_WORKFLOW',
                         p_user_message       =>    'WORKFLOW_HISTORY_ID:'
                                                 || TO_CHAR
                                                       ( p_workflow_history_id )
                                                 || 'KEY:'
                                                 || p_key,
                         p_user_location      => 'EXCEPTION',
                         p_reraise            => TRUE );
   END;

   PROCEDURE close_workflow (
      p_message               IN OUT   tag_wf_message,
      p_workflow_history_id   IN       WORKFLOW_HISTORY.workflow_history_id%TYPE,
      p_key                   IN       VARCHAR2 )
   IS
      l_workflow_history_id   WORKFLOW_HISTORY.workflow_history_id%TYPE;

      CURSOR open_cur
      IS
         SELECT     workflow_history_id
               FROM WORKFLOW_HISTORY
              WHERE KEY = p_key
                AND work_trigger = p_message.work_trigger
                AND status = 'O'
                AND request_date < p_message.assignment_date
           ORDER BY request_date
         FOR UPDATE WAIT 10;
   BEGIN
      insert_workflow_history ( p_message, 'C', p_workflow_history_id, p_key );

      OPEN open_cur;

      LOOP
         BEGIN
            FETCH open_cur
             INTO l_workflow_history_id;

            EXIT WHEN open_cur%NOTFOUND;
            p_message.original_msgid := TO_CHAR ( l_workflow_history_id );
            Tag_Workflow.complete_original_task ( p_message => p_message );

            DELETE FROM WORKFLOW_HISTORY
                  WHERE workflow_history_id = l_workflow_history_id;
         EXCEPTION
            WHEN OTHERS
            THEN
               IF SQLCODE = -30006
               THEN
                  NULL;
               END IF;
         END;
      END LOOP;
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle
                       ( p_error_code         => SQLCODE,
                         p_log                => TRUE,
                         p_user_module        => 'TAG_WF_WORKFLOW.CLOSE_WORKFLOW',
                         p_user_message       =>    'WORKFLOW_HISTORY_ID:'
                                                 || TO_CHAR
                                                       ( p_workflow_history_id )
                                                 || 'KEY:'
                                                 || p_key,
                         p_user_location      => 'EXCEPTION',
                         p_reraise            => TRUE );
   END;
---------------------------------------------------------------------------------------
   FUNCTION Show_Version
   RETURN VARCHAR2
   IS
   BEGIN
      RETURN(vcp_version);
   END Show_Version;
----------------------------------------------------------------------------------------------
   --20-Feb-2009 Automatically generated emails
   --30-Aug-2010
   --deprecated
   --use the new signature
   PROCEDURE create_email_workflow (
      p_trigger_name        IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_params              IN   XMLTYPE,
      p_offender_book_id    IN   OFFENDER_BOOKINGS.offender_book_id%TYPE DEFAULT NULL,
	    p_event_date          IN   DATE DEFAULT NULL)
   IS
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
       := tag_wf_message ( NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL );
   BEGIN
      l_message.trigger_reason := 'WORKEMAIL';
      l_message.workflow_type := 'EMAIL';
      l_message.sender_id := USER;
      l_message.work_trigger := p_trigger_name;

      l_message.assignment_date := SYSDATE;
      l_message.original_msgid := TO_CHAR(get_workflow_id);
	    l_message.spare_date := NVL(p_event_date, SYSDATE); --#6247
      l_message.offender_book_id := p_offender_book_id;

      IF p_params IS NOT NULL
      THEN
         l_message.MESSAGE_TEXT := p_params.getstringval ( );
      END IF;

      send_message ( p_queue_name          => 'TAGWF1.TAG_WF_ROUTER',
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END create_email_workflow;

----------------------------------------------------------------------------------------------
  --30-Aug-2010
   /*
    --public method to be used when creating workflow emails defined as email work items defined via work items maintenance
    --an email work item has to be defined
    --an executable can be used additionally in combination with the email work item
   */
   PROCEDURE create_email_workflow (
      p_trigger_name        IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_params              IN   XMLTYPE,
      p_offender_book_id    IN   OFFENDER_BOOKINGS.offender_book_id%TYPE DEFAULT NULL,
	    p_event_date          IN   DATE DEFAULT NULL,
      p_key                 IN  VARCHAR2,
      p_open_workflow_flag  IN   VARCHAR2 DEFAULT 'Y',
      p_persist_flag        IN   VARCHAR2 DEFAULT 'N')  --persist flag is placeholder for now, it is not implemented yet
   IS
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
       := tag_wf_message ( NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL );
   BEGIN
      l_message.trigger_reason := 'WORKEMAIL';
      l_message.workflow_type := 'EMAIL';
      l_message.sender_id := USER;
      l_message.work_trigger := p_trigger_name;

      l_message.assignment_date := SYSDATE;
      l_message.original_msgid := p_key;
      l_message.spare_text     := p_key; --overload and retain the key send originally in case of email failure memo
	    l_message.spare_date := NVL(p_event_date, SYSDATE); --#6247
      l_message.offender_book_id := p_offender_book_id;

      --overloading spare number for email with open workflow flag and persist flag
      IF p_open_workflow_flag = 'Y' AND p_persist_flag = 'Y'
      THEN
         l_message.spare_number := 111;
      ELSIF p_open_workflow_flag = 'Y'
      THEN
         l_message.spare_number := 110;
      ELSIF p_persist_flag = 'Y'
      THEN
         l_message.spare_number := 101;
      ELSE
         l_message.spare_number := 100;
      END IF;

      IF p_params IS NOT NULL
      THEN
         l_message.MESSAGE_TEXT := p_params.getstringval ( );
      END IF;

      send_message ( p_queue_name          => 'TAGWF1.TAG_WF_ROUTER',
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END create_email_workflow;
----------------------------------------------------------------------------------------------

/*--30-AUG-2010
  --generic code to execute email executable to facilitate dynamic recipients, body, subject
*/
PROCEDURE exe_email_trigger_executable(p_message                   IN OUT   tag_wf_message
                                      ,p_params                    IN       XMLTYPE
                                      ,p_work_trigger              IN       WORK_TRIGGER_EXECUTABLES.work_trigger%TYPE
                                      ,p_work_context              IN       WORK_TRIGGER_EXECUTABLES.work_context%TYPE DEFAULT 'DEFAULT'
                                      ,p_trigger_executable        IN       WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE
                                      ,p_trigger_executable_type   IN       WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE
                                      ,p_message_template          IN       WORK_TRIGGER_EXECUTABLES.message_template%TYPE
                                      ,p_email                     IN OUT NOCOPY  RT_EMAIL
                                      ,p_continue                  OUT      VARCHAR2)
IS
   invalid_executable_type   EXCEPTION;
BEGIN

   IF p_trigger_executable IS NOT NULL
   THEN
      CASE p_trigger_executable_type
      WHEN 'PACKAGE PROCEDURE'
      THEN
         EXECUTE IMMEDIATE    'BEGIN '
                           || p_trigger_executable
                           || '(:FULL_MESSAGE,:MESSAGE_TEMPLATE,:MESSAGE_PARAMS, :RT_EMAIL,:CONTINUE); END;'
           USING IN OUT p_message,
                 IN     p_message_template,
                 IN     p_params,
                 IN OUT p_email,
                 OUT    p_continue;
      ELSE
         RAISE invalid_executable_type;
      END CASE;
   END IF;

   IF p_continue IS NULL
   THEN
      p_continue := 'Y';
   END IF;


EXCEPTION
   WHEN NO_DATA_FOUND
    THEN
            oms_owner.Tag_Error.handle
               ( p_error_code         => SQLCODE,
                 p_log                => TRUE,
                 p_user_module        => 'EXECUTE_EMAIL_TRIGGER_EXECUTABLE',
                 p_user_message       => 'Unsupported Trigger no executable found.',
                 p_user_location      => 'EXCEPTION',
                 p_reraise            => FALSE );
            RAISE;
   WHEN invalid_executable_type
   THEN
            oms_owner.Tag_Error.handle
               ( p_error_code         => SQLCODE,
                 p_log                => TRUE,
                 p_user_module        => 'EXECUTE_EMAIL_TRIGGER_EXECUTABLE',
                 p_user_message       => 'Unsupported Trigger Excutable Type found',
                 p_user_location      => 'EXCEPTION',
                 p_reraise            => FALSE );
            RAISE;
   WHEN OTHERS
   THEN
            oms_owner.Tag_Error.handle
                             ( p_error_code         => SQLCODE,
                               p_log                => TRUE,
                               p_user_module        => 'TAG_WORKFLOW.EXE_EMAIL_TRIGGER_EXECUTABLE',
                               p_user_message       =>    'CALLING ROUTINE:'
                                                       || p_trigger_executable
                                                       || ':'
                                                       || p_message.MESSAGE_TEXT,
                               p_user_location      => 'TAG_WORKFLOW.EXE_EMAIL_TRIGGER_EXECUTABLE',
                               p_reraise            => FALSE );
            RAISE;
END exe_email_trigger_executable;

----------------------------------------------------------------------------------------------
   --20-Feb-2009 Automatically generated emails
   --30 -AUG -2010
   /*used by the listener on email queue; not recommended to be called directly*/
   PROCEDURE generate_work_item_email ( p_message IN tag_wf_message )
   IS

      lv_trigger_executable        WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE      := NULL;
      lv_trigger_executable_type   WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE := NULL;
      lv_message_template          WORK_TRIGGER_EXECUTABLES.message_template%TYPE        := NULL;
      lv_continue                  VARCHAR2(1)                                           := NULL;

      lv_key                       VARCHAR2(64);

      lv_params   XMLTYPE;
      lvrt_email  rt_email := rt_email(NULL, NULL, NULL, NULL,
                                       nt_email_addresses(), nt_email_addresses(), nt_email_addresses(),
                                       nt_attachments() );

      lv_message   tag_wf_message;

      i   PLS_INTEGER;

      CURSOR c_wkflw --expects only one record
      IS
         SELECT w.work_id work_id
           FROM WORK_TRIGGERS t, WORKS w
          WHERE t.trigger_name = lv_message.work_trigger
            AND t.work_id = w.work_id
            AND w.workflow_type = 'EMAIL'
            AND t.active_flag = 'Y'
            AND w.active_flag = 'Y';

   BEGIN

      lv_message := p_message;
      lv_key     := p_message.spare_text; --spare text has retained the key

      IF lv_message.MESSAGE_TEXT IS NOT NULL
      THEN
         lv_params := XMLTYPE.createxml (lv_message.MESSAGE_TEXT);
      END IF;

      dbms_lob.createtemporary(lvrt_email.email_body, FALSE, DBMS_LOB.CALL);

      -- 30-AUG-2010 getting the excutable
      get_trigger_registration ( lv_message.work_trigger,
                                 'CREATE',
                                 lv_message.workflow_type,
                                 lv_trigger_executable,
                                 lv_trigger_executable_type,
                                 lv_message_template );

      IF lv_trigger_executable IS NOT NULL
      THEN

         -- 30-AUG-2010 excutable
         exe_email_trigger_executable(p_message                   =>  lv_message
                                     ,p_params                    =>  lv_params
                                     ,p_work_trigger              =>  lv_message.work_trigger
                                     ,p_work_context              =>  lv_message.workflow_type
                                     ,p_trigger_executable        =>  lv_trigger_executable
                                     ,p_trigger_executable_type   =>  lv_trigger_executable_type
                                     ,p_message_template          =>  lv_message_template
                                     ,p_email                     =>  lvrt_email
                                     ,p_continue                  =>  lv_continue);
      END IF;

      IF NVL ( lv_continue, 'Y' ) = 'Y'
      THEN

         FOR r_wkflw IN c_wkflw --expects only one record
         LOOP
	          -- 30-AUG-2010 modify to pass the email record
            tag_wf_email.build_work_item_email(p_work_id            => r_wkflw.work_id
                                              ,p_params             => lv_params
                                              ,p_offender_book_id   => lv_message.offender_book_id
                                              ,p_event_date         => lv_message.spare_date
                                              ,p_base64             => 'N'
                                              ,p_email              => lvrt_email);

            tag_wf_email.send_email(p_email => lvrt_email);

         END LOOP;
      END IF;


      IF dbms_lob.istemporary(lvrt_email.email_body) = 1 THEN
         dbms_lob.freetemporary(lvrt_email.email_body);
      END IF;

      IF lvrt_email.attachments IS NOT NULL
      THEN
         i := lvrt_email.attachments.FIRST;
         WHILE i IS NOT NULL
         LOOP
            IF dbms_lob.istemporary(lvrt_email.attachments(i).content)  = 1 THEN
               dbms_lob.freetemporary(lvrt_email.attachments(i).content);
            END IF;
            i := lvrt_email.attachments.NEXT(i);
         END LOOP;
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         BEGIN
         --send email failure notification (autonomous procedure)
         tag_wf_email.create_email_failure_memo(p_offender_book_id     => lv_message.offender_book_id
                                               ,p_failed_trigger_name  => lv_message.work_trigger
                                               ,p_key                  => lv_key);
         EXCEPTION
            WHEN OTHERS THEN
               null;
         END;

         BEGIN
             IF dbms_lob.istemporary(lvrt_email.email_body) = 1 THEN
                dbms_lob.freetemporary(lvrt_email.email_body);
             END IF;
         EXCEPTION
            WHEN OTHERS THEN
               null;
         END;

         BEGIN
             IF lvrt_email.attachments IS NOT NULL
            THEN
               i := lvrt_email.attachments.FIRST;
               WHILE i IS NOT NULL
               LOOP
                  IF dbms_lob.istemporary(lvrt_email.attachments(i).content) =  1 THEN
                     dbms_lob.freetemporary(lvrt_email.attachments(i).content);
                  END IF;
                  i := lvrt_email.attachments.NEXT(i);
               END LOOP;
            END IF;
         EXCEPTION
            WHEN OTHERS THEN
               null;
         END;

         Tag_Error.handle
                      ( p_error_code         => SQLCODE,
                        p_log                => TRUE,
                        p_user_module        => 'TAG_WORKFLOW.GENERATE_WORK_ITEM_EMAIL',
                        p_user_location      => 'TAG_WORKFLOW.GENERATE_WORK_ITEM_EMAIL',
                        p_reraise            => TRUE );
   END generate_work_item_email ;

----------------------------------------------------------------------------------------------

   --30-Aug-2010
   /*--public method to be used when creating emails that are NOT associated with an email work item defined via work items maintenance but use workflow infrastructure
     --to be able to use the workflow queues, an executable has to be used and the executable is defined in work_trigger_executables although no work item exists(ex. used for LA Title 15)
     --this is also used for ADHOC emails
     --trigger name 'ADHOC_EMAIL' is a reserved trigger name and CANNOT be used as a trigger name in the maintenance screen Maintain Work Items (OCMWORKS)
     --trigger name 'ADHOC_EMAIL' is also defined in table work_trigger_executables against the plssql code that takes the email information from the staging area for ADHOC_EMAIL
   */
   PROCEDURE create_email (
      p_exe_trigger_name    IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_params              IN   XMLTYPE,
      p_offender_book_id    IN   OFFENDER_BOOKINGS.offender_book_id%TYPE DEFAULT NULL,
	    p_event_date          IN   DATE DEFAULT NULL,
      p_key                 IN  VARCHAR2,
      p_open_workflow_flag  IN   VARCHAR2 DEFAULT 'N', --normally create_email should not open workflow
      p_persist_flag        IN   VARCHAR2 DEFAULT 'N')  --persist flag is placeholder for now, it is not implemented yet

   IS
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
       := tag_wf_message ( NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
                             NULL, NULL );
   BEGIN
      l_message.trigger_reason := 'EMAIL';
      l_message.workflow_type := 'EMAIL';
      l_message.sender_id := USER;
      l_message.work_trigger := p_exe_trigger_name ;

      l_message.assignment_date := SYSDATE;
      l_message.original_msgid := p_key;
      l_message.spare_text     := p_key; --overload and retain the key send originally in case of email failure memo
	    l_message.spare_date := NVL(p_event_date, SYSDATE); --#6247
      l_message.offender_book_id := p_offender_book_id;

      --overloading spare number for email with open workflow flag and persist flag
      IF p_open_workflow_flag = 'Y' AND p_persist_flag = 'Y'
      THEN
         l_message.spare_number := 111;
      ELSIF p_open_workflow_flag = 'Y'
      THEN
         l_message.spare_number := 110;
      ELSIF p_persist_flag = 'Y'
      THEN
         l_message.spare_number := 101;
      ELSE
         l_message.spare_number := 100;
      END IF;

      IF p_params IS NOT NULL
      THEN
         l_message.MESSAGE_TEXT := p_params.getstringval ( );
      END IF;

      send_message ( p_queue_name          => 'TAGWF1.TAG_WF_ROUTER',
                     p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END create_email;

   ----------------------------------------------------------------------------------------------
    --30 -AUG -2010
   /*used by the listener on email queue; not recommended to be called directly*/
   PROCEDURE generate_email ( p_message IN tag_wf_message)
   IS

      lv_trigger_executable        WORK_TRIGGER_EXECUTABLES.trigger_executable%TYPE      := NULL;
      lv_trigger_executable_type   WORK_TRIGGER_EXECUTABLES.trigger_executable_type%TYPE := NULL;
      lv_message_template          WORK_TRIGGER_EXECUTABLES.message_template%TYPE        := NULL;
      lv_continue                  VARCHAR2(1)                                           := NULL;

      lv_key                       VARCHAR2(64);

      i   PLS_INTEGER;

      lv_params   XMLTYPE;
      lvrt_email  rt_email := rt_email(NULL, NULL, NULL, NULL,
                                       nt_email_addresses(), nt_email_addresses(), nt_email_addresses(),
                                       nt_attachments() );

      lv_message   tag_wf_message;

   BEGIN

      lv_message := p_message;
      lv_key     := p_message.spare_text;  --spare text has retained the key

      IF lv_message.MESSAGE_TEXT IS NOT NULL
      THEN
         lv_params := XMLTYPE.createxml (lv_message.MESSAGE_TEXT);
      END IF;

      -- 30-AUG-2010 getting the excutable
      get_trigger_registration ( lv_message.work_trigger,
                                 'CREATE',
                                 lv_message.workflow_type,
                                 lv_trigger_executable,
                                 lv_trigger_executable_type,
                                 lv_message_template );

      --an executable has to be declared for emails generated with public method create_email
      IF lv_trigger_executable IS NOT NULL
      THEN
         dbms_lob.createtemporary(lvrt_email.email_body, FALSE, DBMS_LOB.CALL);

         -- 30-AUG-2010 excutable
         exe_email_trigger_executable(p_message                   =>  lv_message
                                     ,p_params                    =>  lv_params
                                     ,p_work_trigger              =>  lv_message.work_trigger
                                     ,p_work_context              =>  lv_message.workflow_type
                                     ,p_trigger_executable        =>  lv_trigger_executable
                                     ,p_trigger_executable_type   =>  lv_trigger_executable_type
                                     ,p_message_template          =>  lv_message_template
                                     ,p_email                     =>  lvrt_email
                                     ,p_continue                  =>  lv_continue);

         IF NVL ( lv_continue, 'Y' ) = 'Y'
         THEN

            tag_wf_email.send_email(p_email => lvrt_email);

         END IF;

         IF dbms_lob.istemporary(lvrt_email.email_body) = 1 THEN
            dbms_lob.freetemporary(lvrt_email.email_body);
         END IF;

         IF lvrt_email.attachments IS NOT NULL
         THEN
            i := lvrt_email.attachments.FIRST;
            WHILE i IS NOT NULL
            LOOP
               IF dbms_lob.istemporary(lvrt_email.attachments(i).content) = 1 THEN
                  dbms_lob.freetemporary(lvrt_email.attachments(i).content);
               END IF;
               i := lvrt_email.attachments.NEXT(i);
            END LOOP;
         END IF;

      ELSE
         --log lack of executable
         raise_application_error(-20215, 'Executable ' || lv_message.work_trigger || ' is missing');
      END IF;

   EXCEPTION
      WHEN OTHERS THEN
         BEGIN
            --send email failure notification (autonomous procedure)
            tag_wf_email.create_email_failure_memo(p_offender_book_id     => lv_message.offender_book_id
                                               ,p_failed_trigger_name  => lv_message.work_trigger
                                               ,p_key                  => lv_key);
         EXCEPTION
            WHEN OTHERS THEN
               null;
         END;

         BEGIN
             IF dbms_lob.istemporary(lvrt_email.email_body) = 1 THEN
                dbms_lob.freetemporary(lvrt_email.email_body);
             END IF;
         EXCEPTION
            WHEN OTHERS THEN
               null;
         END;

         BEGIN
             IF lvrt_email.attachments IS NOT NULL
            THEN
               i := lvrt_email.attachments.FIRST;
               WHILE i IS NOT NULL
               LOOP
                   IF dbms_lob.istemporary(lvrt_email.attachments(i).content) = 1 THEN
                      dbms_lob.freetemporary(lvrt_email.attachments(i).content);
                   END IF;
                  i := lvrt_email.attachments.NEXT(i);
               END LOOP;
            END IF;
         EXCEPTION
            WHEN OTHERS THEN
               null;
         END;

         Tag_Error.handle
                      ( p_error_code         => SQLCODE,
                        p_log                => TRUE,
                        p_user_module        => 'TAG_WORKFLOW.GENERATE_EMAIL',
                        p_user_location      => 'TAG_WORKFLOW.GENERATE_EMAIL',
                        p_reraise            => TRUE );
   END generate_email;

/*************************************************************************/

   --30-AUG-2010
   /*used by the listener on router; not recommended to be called directly*/
   PROCEDURE forward_email ( p_message IN tag_wf_message)
   IS
      lv_queue_name       VARCHAR2 ( 30 );
      lv_message           tag_wf_message;
      lv_message_handle   RAW ( 16 );

      lv_queue_cluster_id NUMBER(1) := NULL;
   BEGIN
      --all emails go in cluster 1
      lv_queue_cluster_id := 1;

      lv_queue_name :=
                  'TAGWFC'
               || lv_queue_cluster_id
               || '.TAG_WF_GENERAL_C'
               || lv_queue_cluster_id;

      lv_message :=  p_message;

      tag_workflow.send_message
                    ( p_queue_name          => lv_queue_name,
                      p_message             => lv_message,
                      p_original_msgid      => RAWTOHEX
                                                    ( lv_message.original_msgid ),
                      p_message_handle      => lv_message_handle );


   EXCEPTION
      WHEN OTHERS THEN

         Tag_Error.handle
                      ( p_error_code         => SQLCODE,
                        p_log                => TRUE,
                        p_user_module        => 'TAG_WORKFLOW.FORWARD_EMAIL',
                        p_user_location      => 'TAG_WORKFLOW.FORWARD_EMAIL',
                        p_reraise            => TRUE );
   END forward_email;

/*************************************************************************/
  
   -- QC 11384 NDB New routine for PTI
 
   PROCEDURE create_agency_workflow (
      p_trigger_name        IN   WORK_TRIGGERS.trigger_name%TYPE,
      p_agy_loc_id          IN   AGENCY_LOCATIONS.agy_loc_id%TYPE,
      p_key                 IN   VARCHAR2 DEFAULT NULL,
      p_context             IN   VARCHAR2 DEFAULT 'DEFAULT',
      p_params              IN   XMLTYPE DEFAULT NULL,
      p_offender_book_id    IN   OFFENDER_BOOKINGS.offender_book_id%TYPE
            DEFAULT NULL,
      p_event_date          IN   DATE DEFAULT NULL,
      p_override_due_date   IN   DATE DEFAULT NULL,
      p_due_date_period     IN   NUMBER DEFAULT NULL )
   IS
      l_message_handle   RAW ( 16 );
      l_message          tag_wf_message
         := tag_wf_message ( NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL,
                             NULL );
   BEGIN
      l_message.offender_book_id := p_offender_book_id;
      l_message.spare_text := p_agy_loc_id;
      l_message.original_msgid := p_key;
      l_message.note_source_code := p_key;
      l_message.work_trigger := p_trigger_name;
      l_message.workflow_type := p_context;

      IF p_params IS NOT NULL
      THEN
         l_message.MESSAGE_TEXT := p_params.getstringval ( );
      END IF;

      l_message.sender_id := USER;
      l_message.assignment_date := SYSDATE;
      l_message.spare_date := NVL(p_event_date, SYSDATE);
      l_message.due_date := p_event_date;
      l_message.override_due_date := p_override_due_date;
      l_message.due_date_period := p_due_date_period;
      send_message ( p_message             => l_message,
                     p_message_handle      => l_message_handle );
   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;
   
   --22-SEP-2020  10.2.31.1.7  V4 change  --overloading method  
    PROCEDURE reassign_to_team_member (
        p_queue_name           IN   VARCHAR2
       ,p_msgid                IN   VARCHAR2
       ,p_new_team_member_id   IN   TEAM_MEMBERS.team_member_id%TYPE
       ,p_remove_message       IN   INT DEFAULT 1)  
    IS  
    BEGIN
       IF  p_remove_message = 1 THEN
           tag_workflow.reassign_to_team_member(p_queue_name,p_msgid,p_new_team_member_id,true);
       ELSE 
          tag_workflow.reassign_to_team_member(p_queue_name,p_msgid,p_new_team_member_id,false);
       END IF;

   EXCEPTION
      WHEN OTHERS THEN
         Tag_Error.handle;
   END;

   --30-NOV-2020  10.2.31.1.8  V4 change  --overloading method  
   PROCEDURE assign_to_team_member (
      p_queue_name       IN   VARCHAR2,
      p_msgid            IN   VARCHAR2,
      p_team_member_id   IN   TEAM_MEMBERS.team_member_id%TYPE,
      p_remove_message   IN   INT DEFAULT 1 )
   IS
   
   BEGIN
      if p_remove_message = 1 then
      assign_to_team_member(p_queue_name,p_msgid,p_team_member_id,true);
    else
    assign_to_team_member(p_queue_name,p_msgid,p_team_member_id,false);
    END IF;
   EXCEPTION
      WHEN e_empty_queue
      THEN
         RETURN;
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;


   --30-NOV-2020  10.2.31.1.8  V4 change  --overloading method  
   PROCEDURE reassign_to_team (
      p_queue_name       IN   VARCHAR2,
      p_msgid            IN   VARCHAR2,
      p_new_team_id      IN   TEAM_MEMBERS.team_id%TYPE,
      p_remove_message   IN   INT DEFAULT 1)
   IS
      l_new_team_cluster_id   TEAMS.queue_cluster_id%TYPE;
      l_new_team_queue_name   VARCHAR2 ( 50 );
      l_message               tag_wf_message;
      l_message_handle        RAW ( 16 );
   BEGIN
   if p_remove_message = 1 then
      reassign_to_team(p_queue_name,p_msgid,p_new_team_id,true);
    else
    reassign_to_team(p_queue_name,p_msgid,p_new_team_id,false);
    END IF;

   EXCEPTION
      WHEN OTHERS
      THEN
         Tag_Error.handle;
   END;    

END;
/

