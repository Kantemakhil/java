--offender/bookings specific tables
drop table IF EXISTS oms_owner.off_obs_prd_characteristics;
drop table IF EXISTS oms_owner.off_obs_add_check_details;
drop table IF EXISTS oms_owner.off_obs_period_checks;
drop table IF EXISTS oms_owner.off_observation_periods;

--maintenance specific tables 
drop table IF EXISTS oms_owner.off_obs_add_details;
drop table IF EXISTS oms_owner.off_obs_characteristics;
drop table IF EXISTS oms_owner.offender_observation_types;
drop table IF EXISTS oms_owner.off_obs_zone_details;
drop table IF EXISTS oms_owner.offender_observation_zones;
drop table IF EXISTS oms_owner.off_obs_profile_codes;

--
drop table IF EXISTS oms_owner.api_off_obs_staging;
 