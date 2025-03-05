package net.syscon.s4.inst.victimmanagement;

import java.util.List;

import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogs;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferences;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffenders;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecords;

public interface OivctmngRepository {

	Integer insertVictimRecords(List<VictimRecords> insertList);

	Integer updateVictimRecords(List<VictimRecords> updateList);

	List<VictimRecords> getAllVictimRecords();

	Integer insertVictimLinkedOffenders(List<VictimLinkedOffenders> insertList);

	Integer updateVictimLinkedOffenders(List<VictimLinkedOffenders> updateList);

	List<VictimLinkedOffenders> getAllVictimLinkedOffenders(Integer victimId);

	Integer insertVictimContactLogs(List<VictimContactLogs> insertList);

	Integer updateVictimContactLogs(List<VictimContactLogs> updateList);

	List<VictimContactLogs> getAllinsertVictimContactLogs(Integer victimId);

	Integer insertVictimContactPreferences(List<VictimContactPreferences> insertList);

	Integer updateVictimContactPreferences(List<VictimContactPreferences> updateList);

	List<VictimContactPreferences> getAllvictimContactPreferences(Integer victimId);
	
	Integer getVictimId();

}
