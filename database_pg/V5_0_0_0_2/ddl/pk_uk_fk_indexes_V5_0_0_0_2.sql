--PKs
alter table module_triggers add constraint module_triggers_pk primary key(trigger_id);
alter table action_api add constraint action_api_pk primary key(api_id);

--UKs
alter table module_triggers add constraint module_triggers_uk1 unique(module_name,dto_name,module_description,trigger_name);
alter table automation_query_parameters add constraint automation_query_parameters_uk1 unique(query_key,parameter_code);
alter table module_dynamic_forms add constraint  module_dynamic_forms_uk1 unique(form_name);

--FKs
--drop
--we do not need to drop , this table is no longer used anyway alter table agy_loc_team_functions drop constraint agy_loc_team_fn_team_fn_fk;
--new
--we need to delete first records that are no longer valid; this is OK because this records are no longer required;
--the olad FK team_functions_teams_fk pointi g to teams was already dropped in V5_0_0_0_1
delete from agy_loc_team_functions where team_id not in ( select team_id from automation_teams );
delete from team_functions where team_id not in ( select team_id from automation_teams );
alter table team_functions add constraint team_functions_teams_fk1 foreign key (team_id) references automation_teams(team_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

alter table bpmn_process add constraint bpmn_process_fk foreign key (trigger_id) references module_triggers(trigger_id) ON DELETE NO ACTION NOT DEFERRABLE INITIALLY IMMEDIATE;

--indexes
DROP INDEX iwp_template_modules_uk;
CREATE UNIQUE INDEX iwp_template_modules_uk ON iwp_template_modules (template_id, module_name,(block_name is null)) TABLESPACE TAG_INDX;




