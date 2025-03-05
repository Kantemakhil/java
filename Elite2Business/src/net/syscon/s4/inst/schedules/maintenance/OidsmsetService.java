package net.syscon.s4.inst.schedules.maintenance;

import java.util.List;

import net.syscon.s4.inst.schedules.bean.SchMovSettingCommitBean;
import net.syscon.s4.inst.schedules.bean.ScheduleMovementSetting;

public interface OidsmsetService {

	Integer tapScheduleSettingCommit(SchMovSettingCommitBean commitBean);

	List<ScheduleMovementSetting> tapScheduleSettingExecuteQuery();

}
