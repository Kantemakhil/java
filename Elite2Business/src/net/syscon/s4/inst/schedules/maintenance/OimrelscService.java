package net.syscon.s4.inst.schedules.maintenance;

import java.util.List;

import net.syscon.s4.inst.schedules.maintenance.bean.FinalSubmitBeanReleaseScheduleSetting;
import net.syscon.s4.inst.schedules.maintenance.bean.ReleaseSchedulesSettingsBean;

public interface OimrelscService {

	List<ReleaseSchedulesSettingsBean> retrieveGridData(ReleaseSchedulesSettingsBean searchBean);

	Integer submitFormData(FinalSubmitBeanReleaseScheduleSetting odynfrmSubmitDataBean);

	ReleaseSchedulesSettingsBean retrieveAlertGridData(ReleaseSchedulesSettingsBean searchBean);

}
