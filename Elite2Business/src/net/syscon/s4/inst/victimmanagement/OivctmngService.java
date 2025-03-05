package net.syscon.s4.inst.victimmanagement;

import java.util.List;

import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogs;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactLogsCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferences;
import net.syscon.s4.inst.victimmanagement.beans.VictimContactPreferencesCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffenders;
import net.syscon.s4.inst.victimmanagement.beans.VictimLinkedOffendersCommitBean;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecords;
import net.syscon.s4.inst.victimmanagement.beans.VictimRecordsCommitBean;

public interface OivctmngService {

	List<VictimRecords> getAllVictimRecords();

	Integer saveVictimRecords(VictimRecordsCommitBean commitBean);

	List<VictimLinkedOffenders> getAllVictimLinkedOffenders(Integer victimId);

	Integer saveVictimLinkedOffenders(VictimLinkedOffendersCommitBean commitBean);

	List<VictimContactLogs> getAllVictimContactLogs(Integer victimId);

	Integer saveVictimContactLogs(VictimContactLogsCommitBean commitBean);

	List<VictimContactPreferences> getAllvictimContactPreferences(Integer victimId);

	Integer saveVictimContactPreferences(VictimContactPreferencesCommitBean commitBean);

	Integer getVictimId();
}
