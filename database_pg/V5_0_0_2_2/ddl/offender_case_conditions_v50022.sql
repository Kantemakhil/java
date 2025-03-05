alter table oms_owner.offender_case_conditions add team_id bigint;
comment on column oms_owner.offender_case_conditions.team_id is 'The team the condition has been assigned to.';

alter table oms_owner.offender_case_conditions add  team_member_id bigint;  
comment on column oms_owner.offender_case_conditions.team_member_id is 'The team member the condition has been assigned to (populated only if team_id is populated and it has to be a member of the team).';

alter table oms_owner.offender_case_conditions add  staff_id bigint; 
comment on column oms_owner.offender_case_conditions.staff_id is 'The staff the condition has been assigned to (populated only if team_id and team_member_id are not populated).';
 
alter table oms_owner.offender_case_conditions 
	add constraint offender_case_conditions_fk1 foreign key (team_id) 
	references oms_owner.automation_teams(team_id) on delete no action not deferrable initially immediate;	
	
alter table oms_owner.offender_case_conditions 
	add constraint offender_case_conditions_fk2 foreign key (team_member_id) 
	references oms_owner.team_staff_members(team_member_id) on delete no action not deferrable initially immediate;	

alter table oms_owner.offender_case_conditions 
	add constraint offender_case_conditions_fk3 foreign key (staff_id) 
	references oms_owner.staff_members(staff_id) on delete no action not deferrable initially immediate;		