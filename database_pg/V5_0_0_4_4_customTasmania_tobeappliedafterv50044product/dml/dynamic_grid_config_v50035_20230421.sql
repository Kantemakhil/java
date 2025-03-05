--missed v5_0_0_3_5 
/* --updated in V5_0_0_4_1 
UPDATE DYNAMIC_GRID_CONFIG 
  SET modify_datetime = current_timestamp
     ,modify_user_id = 'OMS_OWNER' 
     ,CONFIG_JSON ='[{"field":"termType","fieldName":"termToLine.termtype","dataType":"lov","source":"link","sourceType":"OIMSREQS","url":"ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType","required":true},{"field":"years","fieldName":"termToLine.years","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"months","fieldName":"termToLine.months","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"weeks","fieldName":"termToLine.weeks","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"days","fieldName":"termToLine.days","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"indefinite","fieldName":"termToLine.indefinite","dataType":"checkbox"}]' 
 WHERE MODULE_NAME = 'OCDLEGLO' AND GRID_NAME = 'terms';
 */

/* --redone in V5_0_0_4_3 
UPDATE DYNAMIC_GRID_CONFIG 
   SET modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER' 
      ,CONFIG_JSON ='[{"field":"termType","fieldName":"ncusTerm.termtype","dataType":"lov","source":"link","sourceType":"OIMSREQS","url":"ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType","required":true},{"field":"years","fieldName":"ncusTerm.years","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"months","fieldName":"ncusTerm.months","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"weeks","fieldName":"ncusTerm.weeks","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"days","fieldName":"ncusTerm.days","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"fixedExpiry","fieldName":"ncusTerm.fixedExpiry","dataType":"checkbox"},{"field":"indefinite","fieldName":"ncusTerm.indefinite","dataType":"checkbox"}]' 
 WHERE MODULE_NAME = 'OCDNCODE' AND GRID_NAME = 'nonCustTerm';
*/

/* --re-done in V5_0_0_3_6_HF1 and later patches
UPDATE DYNAMIC_GRID_CONFIG 
   SET modify_datetime = current_timestamp
     ,modify_user_id = 'OMS_OWNER' 
     ,CONFIG_JSON ='[{"field":"termType","fieldName":"termToLine.termtype","dataType":"lov","source":"link","sourceType":"OIMSREQS","url":"ocmpconf/populateTermType?sentCategory=:orderType&sentType=:sentType","required":true,"editable":false},{"field":"years","fieldName":"termToLine.years","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"months","fieldName":"termToLine.months","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"weeks","fieldName":"termToLine.weeks","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"days","fieldName":"termToLine.days","dataType":"number","maxValue":99.9,"format":"1.1-1"},{"field":"fixedExpiry","fieldName":"durationToLine.fixedexpiry","dataType":"checkbox"},{"field":"indefinite","fieldName":"termToLine.indefinite","dataType":"checkbox"}]' 
 WHERE MODULE_NAME = 'OCDPAROR' AND GRID_NAME = 'duration';
*/

--required, it does not appear in any other later patches
UPDATE DYNAMIC_GRID_CONFIG 
   SET modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER' 
      ,CONFIG_JSON ='[{"field":"outcome","fieldName":"ocuchgou.outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","required":true,"editable":true},{"field":"disposition","fieldName":"ocuchgou.disposition","dataType":"lov","source":"domain","url":"OFF_RESULT","required":false,"editable":false},{"field":"status","fieldName":"ocuchgou.status","dataType":"lov","source":"domain","url":"CHARGE_STS","required":false,"editable":false},{"field":"orderedDate","fieldName":"ocuchgou.ordereddate","dataType":"date","required":false,"editable":false},{"field":"linkedTo","fieldName":"ocuchgou.linkedTo","dataType":"text","required":false,"editable":false},{"field":"updatedDateDisplay","fieldName":"ocuchgou.updatedon","dataType":"text","required":true,"editable":false},{"field":"updatedDate","dataType":"text","required":false,"editable":false,"hide":true},{"field":"staffName","fieldName":"ocuchgou.staffname","dataType":"lov","source":"link","url":"ocucalcr/getStaffMembers","required":false,"editable":false},{"field":"updateReason","fieldName":"ocuchgou.updatereason","dataType":"lov","source":"domain","url":"CHOUTUPREA","required":true,"editable":true}]' 
 WHERE MODULE_NAME = 'OCUCHGOU' AND GRID_NAME = 'chargeOutcome';

--required, it does not appear in any other later patches
UPDATE DYNAMIC_GRID_CONFIG 
   SET modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER' 
      ,CONFIG_JSON ='[{"field":"chargeId","dataType":"number","editable":false,"hide":true},{"field":"select","dataType":"checkbox","fieldName":"ocdchgsu.select","editable":true},{"field":"matter","fieldName":"ocdchgsu.matter","dataType":"text","required":true,"editable":true},{"field":"description","fieldName":"ocdchgsu.description","required":true,"dataType":"text","editable":false},{"field":"descriptionLaunch","dataType":"hyperlink","displayFields":["code","description","act","legislativeBody"],"parentField":["code","description","act","offenceId"],"lovUrl":"ocmpconf/getOffencesOnStatute?statuteCode=:act","link":"/multiLov"},{"field":"code","fieldName":"ocdchgsu.code","dataType":"text","required":false,"editable":false},{"field":"act","fieldName":"ocdchgsu.act","dataType":"lov","source":"link","url":"ocmpconf/populateStatutes","sourceType":"OIMSTATU","editable":true},{"field":"type","fieldName":"ocdchgsu.type","dataType":"lov","source":"link","sourceType":"OIMOFFEN","parentFields":["code","act"],"url":"ocmpconf/getAllowableOffenceType?offenceCode=:code&statuteCode=:act","editable":true},{"fieldName":"ocdchgsu.chargeoutcome","field":"outcome","dataType":"lov","source":"link","url":"ocmpconf/populateOutcome","sourceType":"OCMORCOD","editable":false,"hide":false},{"field":"incidentDate","dataType":"text","editable":false,"hide":true},{"field":"Range","dataType":"text","editable":false,"hide":true},{"field":"plea","dataType":"text","editable":false,"hide":true},{"field":"particulars","dataType":"text","editable":false,"hide":true},{"field":"offenceId","dataType":"text","editable":false,"hide":true},{"fieldName":"ocdchgsu.details","field":"details","dataType":"hyperlink","link":"/OCDCHGDT"}]' 
 WHERE MODULE_NAME = 'OCDCHGSU' AND GRID_NAME = 'chargesDlg';

/* --re-done in V5_0_0_3_6 and later patches
UPDATE DYNAMIC_GRID_CONFIG 
   SET modify_datetime = current_timestamp
      ,modify_user_id = 'OMS_OWNER' 
      ,CONFIG_JSON ='[{"field":"displayNo","fieldName":"custorder.no","dataType":"text","editable":false},{"field":"orderNo","hide":true,"dataType":"text","editable":false},{"field":"type","fieldName":"custorder.type","dataType":"lov","source":"link","url":"ocmpconf/populateSentType?sentCategory=CUST","sourceType":"OIMSREQS","editable":false},{"field":"termTypeAndLength","fieldName":"custorder.termtypeandlength","dataType":"text","editable":false},{"field":"opTerm","fieldName":"ocdlegls.operativeheading","dataType":"text","editable":false},{"field":"commenceType","fieldName":"custorder.commencetype","dataType":"lov","source":"domain","url":"LO_REL_TYPE","editable":false},{"field":"relatedTo","fieldName":"custorder.linkto","dataType":"text","editable":false},{"field":"commenceDate","fieldName":"custorder.commencedate","dataType":"text","editable":false},{"field":"erd","fieldName":"custorder.erd","dataType":"text","editable":false},{"field":"ped","fieldName":"custorder.ped","dataType":"date","editable":false},{"field":"lrd","fieldName":"custorder.lrd","dataType":"text","editable":false},{"field":"holdExpiryDate","fieldName":"ocdleglo.holdExpirydate","dataType":"text","editable":false},{"field":"status","fieldName":"custorder.status","dataType":"lov","source":"domain","url":"ACTIVE_TYPE","editable":false},{"field":"orderType","hide":true,"dataType":"text","editable":false}]' 
 WHERE MODULE_NAME = 'OCDLEGLS' AND GRID_NAME = 'custOrders';
 */
 