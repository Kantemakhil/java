package net.syscon.s4.inst.schedules.maintenance;

import java.util.List;

import net.syscon.s4.inst.schedules.maintenance.bean.ReleaseSchedulesSettingsBean;

public interface OimrelscRepository {

	List<ReleaseSchedulesSettingsBean> retrieveGridData(ReleaseSchedulesSettingsBean searchBean);

	Integer getKeyDateCount();

	Integer updateSubmitFormData(ReleaseSchedulesSettingsBean odynfrmSubmitDataBean);

	Integer submitFormData(ReleaseSchedulesSettingsBean odynfrmSubmitDataBean);

	ReleaseSchedulesSettingsBean retrieveAlertGridData(ReleaseSchedulesSettingsBean searchBean);

	Integer getAlertsDateCount();

	Integer updateSubmitAlertsFormData(ReleaseSchedulesSettingsBean finalalertsGridData);

	Integer updateSubmitChargesFormData(ReleaseSchedulesSettingsBean finalchargesGridData);

	Integer getChargeIndCount();

}
