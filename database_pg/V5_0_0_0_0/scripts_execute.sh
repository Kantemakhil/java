#!/bin/sh
# Author : akhilk@vrnda.com
# Script follows here:
cd /var/lib/postgresql/postgresql_data
mkdir tag_data
mkdir tag_indx
mkdir tag_setup_data
mkdir tag_setup_indx
mkdir tag_wf
mkdir users
mkdir interface
mkdir tag_biometric
mkdir tag_images
mkdir tag_image_originals
mkdir tag_iwp
mkdir tag_jn
chown -R postgres:postgres /var/lib/postgresql/postgresql_data
chmod -R 0750 /var/lib/postgresql/postgresql_data
/usr/bin/psql -c 'create database tasmqa1;'
/usr/bin/psql -c "create user OMS_OWNER with password 'oms_owner';"
/usr/bin/psql -c 'ALTER ROLE oms_owner SET search_path TO oms_owner;'
/usr/bin/psql -c 'grant all privileges on database Tasmqa1 to oms_owner;'
/usr/bin/psql -c 'ALTER ROLE oms_owner SUPERUSER CREATEDB CREATEROLE;'
PGPASSWORD=oms_owner psql -d tasmqa1 -U oms_owner -c 'create schema OMS_OWNER AUTHORIZATION oms_owner;'
/usr/bin/psql -c "CREATE TABLESPACE tag_data OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_data';"
/usr/bin/psql -c "CREATE TABLESPACE tag_indx OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_indx';"
/usr/bin/psql -c "CREATE TABLESPACE tag_setup_data OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_setup_data';"
/usr/bin/psql -c "CREATE TABLESPACE tag_setup_indx OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_setup_indx';"
/usr/bin/psql -c "CREATE TABLESPACE tag_wf OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_wf';"
/usr/bin/psql -c "CREATE TABLESPACE interface OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/interface';"
/usr/bin/psql -c "CREATE TABLESPACE tag_biometric OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_biometric';"
/usr/bin/psql -c "CREATE TABLESPACE tag_images OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_images';"
/usr/bin/psql -c "CREATE TABLESPACE tag_image_originals OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_image_originals';"
/usr/bin/psql -c "CREATE TABLESPACE tag_iwp OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_iwp';"
/usr/bin/psql -c "CREATE TABLESPACE tag_jn OWNER OMS_OWNER LOCATION '/var/lib/postgresql/postgresql_data/tag_jn';"
cd  /var/lib/postgresql/client_scripts
psql -U oms_owner -d tasmqa1 -a -f 1_nt_email_addresses.sql;
psql -U oms_owner -d tasmqa1 -a -f 2_table_tasmqa1.sql;
psql -U oms_owner -d tasmqa1 -a -f 3_CONSTRAINTS_table_tasmqa1.sql;
psql -U oms_owner -d tasmqa1 -a -f 4_sequence.sql;
psql -U oms_owner -d tasmqa1 -a -f 5_automation_user_table.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/5_automation_user_table_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/6_agency_locations_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/7_component_configs_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/8_oms_modules_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/9_menu_securities_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/10_module_privileges_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/11_oms_modules_help_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/12_oms_roles_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/13_reference_codes_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/14_reference_domains_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/15_staff_members_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/16_staff_member_roles_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/17_caseloads_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/18_printer_types_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/19_printers_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/20_system_profiles_data.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/21_system_labels_data.sql;
psql -U oms_owner -d tasmqa1 -a -f 22_soundex.sql;
psql -U oms_owner -d tasmqa1 -a -f 23_INDEXES_table_tasmqa1.sql;
psql -U oms_owner -d tasmqa1 -a -f 24_oracle_sys_functions.sql;
psql -U oms_owner -d tasmqa1 -a -f setup_config/24_oracle_sys_functions_data.sql;
psql -U oms_owner -d tasmqa1 -a -f 25_oracle_sys_views.sql;
psql -U oms_owner -d tasmqa1 -a -f 26_views_dependency_functions.sql;
psql -U oms_owner -d tasmqa1 -a -f 27_tasmqa1_views.sql;
psql -U oms_owner -d tasmqa1 -a -f 28_query_dependency_functions.sql;
psql -U oms_owner -d tasmqa1 -a -f 29_row_id_implementation.sql;
psql -U oms_owner -d tasmqa1 -a -f 30_triggers.sql;
psql -U oms_owner -d tasmqa1 -a -f 31_FKEYS_table_tasmqa1.sql;
psql -U oms_owner -d tasmqa1 -a -f 32_NEWLY_CREATED_FUNCTIONS.sql;


