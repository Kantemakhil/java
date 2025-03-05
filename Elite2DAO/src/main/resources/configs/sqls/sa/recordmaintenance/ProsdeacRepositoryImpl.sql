PROSDEAC_GET_PROCESS_DET_LIST {
	select bpmn_file, timer_process, proc_def_id from bpmn_process bp where proc_def_id in (:procDefIdList)
}


