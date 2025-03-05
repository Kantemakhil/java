CREATE OR REPLACE FUNCTION oms_owner.oms_utils_display_user_message ( p_message_number bigint, p_application_system text ) RETURNS varchar AS $body$
DECLARE

   --
   -- A SQL cursor to get a system message
   --
      get_system_messages_cur CURSOR FOR
         SELECT sys_msg.message_type,
                sys_msg.message_text,
                sys_msg.action_text,
                sys_msg.system_remarks_text
           FROM system_messages sys_msg
          WHERE sys_msg.message_number = p_message_number
            AND sys_msg.appln_code = p_application_system;
      --
      --
      v_system_remarks_text   varchar(240);
      --
      --
      v_action_text           varchar(240);
      --
      --
      v_message_type          varchar(240);
      --
      --
      v_message_text          varchar(240);

BEGIN
      -- if cursor not already open, then open it.
      IF not exists(SELECT * FROM pg_cursors WHERE name = 'get_system_messages_cur')
      THEN
         OPEN get_system_messages_cur;
      END IF;
      -- get the message
      FETCH get_system_messages_cur INTO v_message_type,
                                         v_message_text,
                                         v_action_text,
                                         v_system_remarks_text;
      -- handle if message not found
      IF get_system_messages_cur%NOTFOUND
      THEN
         CLOSE get_system_messages_cur;
         RETURN('Message number ' ||
                p_message_number::varchar ||
                ' not found in SYSTEM_MESSAGES table. Call support');
      END IF;
      CLOSE get_system_messages_cur;
      -- return the error
      RETURN(p_application_system ||
             ' ' ||
             p_message_number::varchar ||
             ' ' ||
             v_message_type ||
             ' ' ||
             v_message_text ||
             ' ' ||
             v_action_text);
   END;
$body$
LANGUAGE PLPGSQL
;