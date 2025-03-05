CREATE OR REPLACE FUNCTION oms_owner.tag_prg_provider_name(p_provider_class character varying, p_provider_id integer, p_provider_code character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 declare 
      lv_name VARCHAR(80);

begin
      if p_provider_class = 'TEAM' then
                 select
	description
           into
	lv_name
from
	automation_teams
where
	team_id = p_provider_id;

elsif p_provider_class = 'PER' then
                 select
	last_name || ' ,' first_name
           into
	lv_name
from
	persons
where
	person_id = p_provider_id;

elsif p_provider_class = 'CORP' then
                 select
	corporate_name
           into
	lv_name
from
	corporates
where
	corporate_id = p_provider_id;

elsif p_provider_class = 'AGY' then
                 select
	description
           into
	lv_name
from
	agency_locations
where
	agy_loc_id = p_provider_code;
end if;

return lv_name;
end;

$function$
;

CREATE OR REPLACE FUNCTION oms_owner.tag_prg_provider_name(p_provider_class character varying, p_provider_id bigint, p_provider_code character varying)
 RETURNS character varying
 LANGUAGE plpgsql
AS $function$
 DECLARE 
      lv_name VARCHAR(80);
   BEGIN
      IF p_provider_class = 'TEAM' THEN
                 SELECT description
           INTO lv_name
           FROM automation_teams
          WHERE team_id = p_provider_id;
      ELSIF p_provider_class = 'PER' THEN
                 SELECT last_name || ' ,' first_name
           INTO lv_name
           FROM persons
          WHERE person_id = p_provider_id;
      ELSIF p_provider_class = 'CORP' THEN
                 SELECT corporate_name
           INTO lv_name
           FROM corporates
          WHERE corporate_id = p_provider_id;
      ELSIF p_provider_class = 'AGY' THEN
                 SELECT description
           INTO lv_name
           FROM agency_locations
          WHERE agy_loc_id = p_provider_code;
      END IF;

      RETURN lv_name;
   END;
$function$
;