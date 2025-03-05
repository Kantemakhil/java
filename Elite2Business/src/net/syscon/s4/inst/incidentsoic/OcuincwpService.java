package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.OffenderWeapons;
import net.syscon.s4.im.incidentsoic.beans.OffenderWeaponsCommitBean;

public interface OcuincwpService {
	
 Integer offednerWeaponsInsertQuery(OffenderWeaponsCommitBean commitBean);

  List<OffenderWeapons> offenderWeaponsData(OffenderWeapons objSearchDao);
 
  Integer offenderWeaponcommitUpdate(List<OffenderWeapons> lUpdateList);


 
}
