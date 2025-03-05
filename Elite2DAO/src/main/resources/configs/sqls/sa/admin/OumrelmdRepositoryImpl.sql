OUMRELMD_SELECT_BY_MODULE_NAME
{
SELECT  MAF.MODULE_NAME,MAF.ACCESS_MODULE_NAME,MAF.LIST_SEQ,OMS.DESCRIPTION as DESCRIPTION
from MODULE_ACCESSIBLE_FORMS MAF
LEFT JOIN OMS_MODULES OMS ON OMS.MODULE_NAME=MAF.ACCESS_MODULE_NAME  where   MAF.MODULE_NAME=:moduleName
ORDER BY LIST_SEQ
}
OUMRELMD_RELATED_MODULE_LOV
{
 	SELECT OMS_MOD.MODULE_NAME as CODE ,OMS_MOD.DESCRIPTION   FROM   OMS_MODULES OMS_MOD
 	order by OMS_MOD.MODULE_NAME
}
OUMRELMD_INSERT_RELATED_MODULES 
{
insert into MODULE_ACCESSIBLE_FORMS  (module_name,access_module_name,list_seq,create_user_id,create_datetime, modify_datetime) values
(:moduleName,:accessModuleName,:listSeq,:createUserId, CURRENT_TIMESTAMP, null)
}

OUMRELMD_UPDATE_RELATED_MODULES
{
update MODULE_ACCESSIBLE_FORMS set module_name=:moduleName, access_module_name=:accessModuleName,
list_seq=:listSeq,modify_user_id=:modifyUserId, modify_datetime = CURRENT_TIMESTAMP where module_name=:moduleName and  access_module_name=:moduleType
}
OUMRELMD_DELETE_RELATED_MODULES
{
delete from MODULE_ACCESSIBLE_FORMS where module_name=:moduleName and  access_module_name=:accessModuleName
}