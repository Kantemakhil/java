Connect to postgres user and execute the following 
create database Tasmqa1;
create user OMS_OWNER with password 'oms_owner';
grant all privileges on database Tasmqa1 to OMS_OWNER;
ALTER ROLE oms_owner SUPERUSER CREATEDB CREATEROLE;
Now connect to the database “Tasmqa1” with the user “oms_owner” which is created above and create the schema using below commands
create schema OMS_OWNER AUTHORIZATION OMS_OWNER;
Create a directory to the  tablespace’s on the server as shown in the below
cd  /var/lib/postgresql
mkdir postgresql_data
cd postgresql_data
mkdir  tag_data
mkdir  tag_indx
mkdir  tag_setup_data
mkdir  tag_setup_indx
mkdir  tag_wf
mkdir  users
mkdir  interface
mkdir  tag_biometric
mkdir  tag_images
mkdir  tag_image_originals
mkdir  tag_iwp
mkdir  tag_jn
chown -R postgres:postgres /var/lib/postgresql/postgresql_data
chmod -R 775 /var/lib/postgresql/postgresql_data
Now execute the create tablespace commands in the workbench as shown below
CREATE TABLESPACE tag_data OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_data';
CREATE TABLESPACE tag_indx OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_indx';
CREATE TABLESPACE tag_setup_data OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_setup_data';
CREATE TABLESPACE tag_setup_indx OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_setup_indx';
CREATE TABLESPACE tag_wf OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_wf';
CREATE TABLESPACE interface OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/interface';
CREATE TABLESPACE tag_biometric OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_biometric';
CREATE TABLESPACE tag_images OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_images';
CREATE TABLESPACE tag_image_originals OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_image_originals';
CREATE TABLESPACE tag_iwp OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_iwp';
CREATE TABLESPACE tag_jn OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_jn'; 

Now execute the scripts in a sequential order as shown below
1_nt_email_addresses.sql
2_table_tasmqa1.sql
3_CONSTRAINTS_table_tasmqa1.sql
4_sequence.sql
5_camunda_user_table.sql
6_agency_locations_data.sql
7_component_configs_data.sql
8_oms_modules_data.sql
9_menu_securities_data.sql
10_module_privileges_data.sql
11_oms_modules_help_data.sql
12_oms_roles_data.sql
13_reference_codes_data.sql
14_reference_domains_data.sql
15_staff_members_data.sql
16_staff_member_roles_data.sql
17_caseloads_data.sql
18_printer_types_data.sql
19_printers_data.sql
20_system_profiles_data.sql
21_system_labels_data.sql
22_soundex.sql
23_INDEXES_table_tasmqa1.sql
24_oracle_sys_functions.sql
25_oracle_sys_views.sql
26_views_dependency_functions.sql
27_tasmqa1_views.sql
28_query_dependency_functions.sql
29_row_id_implementation.sql
30_triggers.sql
31_FKEYS_table_tasmqa1.sql
32_NEWLY_CREATED_FUNCTIONS.sql
