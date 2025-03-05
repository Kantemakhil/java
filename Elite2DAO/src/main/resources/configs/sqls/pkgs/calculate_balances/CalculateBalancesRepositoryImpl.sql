LV_CALC_BAL_CUR{
 select ADDITTIONAL_DAYS, ADDITTIONAL_DAYS_REASON from OFF_BALANCE_CALC_CUSTODY_DTL where OFFENDER_BAL_CALC_ID = :P_OFF_BAL_CALC_ID and admission_Date = :P_ADM_DATE and (release_date = :P_RELEASE_DATE or release_date is null)      
}