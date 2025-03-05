package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.InterestedParties;

public interface OcdintpaService {

	public List<InterestedParties> executeQuery(InterestedParties interestedParties);

	public Integer insertInterestedParties(List<InterestedParties> insertList);

	public Integer updateInterestedParties(List<InterestedParties> updateList);

	public Integer deleteInterestedParties(List<InterestedParties> deleteList);

}
