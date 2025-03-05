package net.syscon.s4.inst.incidentsoic;

import java.util.List;

import net.syscon.s4.im.incidentsoic.beans.OffenderWeapons;

public interface OcuincwpRepository {
	
	
	public List<OffenderWeapons> offenderWeaponsData(OffenderWeapons objSearchDao);
	
	Integer offenderWeaponcommitInsert(List<OffenderWeapons> lInsertList);
	
	Integer offenderWeaponcommitUpdate(List<OffenderWeapons> updateList);
	
	Integer offenderWeaponcommitDelete(List<OffenderWeapons> updateList);
	
	Integer offenderWeaponePreInsert(Integer offenderbookId, String agencyIncidentId);
	
	
	
	
	

}
