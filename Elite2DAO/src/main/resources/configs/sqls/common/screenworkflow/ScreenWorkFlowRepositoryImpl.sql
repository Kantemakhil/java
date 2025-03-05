
GET_SCREEN_WORKFLOW_LIST{
SELECT ws.WORKFLOW_CODE, ws.MODULE_NAME, ws.WORKFLOW_SEQ,ws.TOOL_TIP, wf.active_flag  FROM  oms_owner.WORKFLOW_SCREENS ws 
left join oms_owner.workflow_folders wf on wf.workflow_code = ws.workflow_code
WHERE NOT ws.MODULE_NAME='OCDSABUS'and wf.active_flag != 'N' GROUP BY 
ws.WORKFLOW_CODE,ws.MODULE_NAME,ws.WORKFLOW_SEQ, wf.active_flag ORDER BY ws.WORKFLOW_CODE,ws.WORKFLOW_SEQ
}
