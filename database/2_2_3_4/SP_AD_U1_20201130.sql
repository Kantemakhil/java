INSERT INTO SYSTEM_PROFILES(PROFILE_TYPE, PROFILE_CODE, DESCRIPTION, PROFILE_VALUE, PROFILE_VALUE_2) 
  SELECT 'DB','AD_U1','AD User 1','tuelite001@mvpastriab2c.onmicrosoft.com', 'OMS_OWNER'
    FROM dual 
  WHERE NOT EXISTS (SELECT 'Y' FROM system_profiles WHERE PROFILE_TYPE = 'DB' AND PROFILE_CODE = 'AD_U1');


UPDATE SYSTEM_PROFILES 
   SET PROFILE_VALUE = 'tuelite001@mvpastriab2c.onmicrosoft.com' 
      ,PROFILE_VALUE_2 = 'OMS_OWNER' 
 WHERE PROFILE_TYPE = 'DB' 
   AND PROFILE_CODE = 'AD_U1';


COMMIT;

