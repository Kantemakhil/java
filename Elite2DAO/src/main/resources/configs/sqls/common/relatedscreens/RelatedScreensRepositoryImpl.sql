GET_RELATED_SCREENS{
SELECT  MAF.MODULE_NAME,MAF.ACCESS_MODULE_NAME,MAF.LIST_SEQ,OMS.DESCRIPTION from MODULE_ACCESSIBLE_FORMS MAF
LEFT JOIN OMS_MODULES OMS ON OMS.MODULE_NAME=MAF.ACCESS_MODULE_NAME ORDER BY MAF.MODULE_NAME,LIST_SEQ
}