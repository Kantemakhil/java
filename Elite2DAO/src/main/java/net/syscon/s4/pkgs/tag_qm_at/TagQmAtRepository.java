package net.syscon.s4.pkgs.tag_qm_at;

import net.syscon.s4.pkgs.QmActivities;
import net.syscon.s4.pkgs.QmConActivityTeams;

public interface TagQmAtRepository {
	Integer prInsQmActivities(QmActivities qmActivities);

	Integer prInsQmConActivityTeams(QmConActivityTeams qmConActivityTeams);

	Integer prUpdQmActivities(QmActivities qmActivities);

	Integer prUpdQmConActivityTeams(QmConActivityTeams qmConActivityTeams);

	Integer prDel(QmActivities qmActivities);

	Long activityIdCur();

}
