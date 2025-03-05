package net.syscon.s4.inst.schedules.maintenance;

import java.util.List;

import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;

public interface OidsmsetRepository {

	Integer tapScheduleSettingUpdate(List<ScheduleMovementSetting> updateList);

	List<ScheduleMovementSetting> tapScheduleSettingExecuteQuery();

}
