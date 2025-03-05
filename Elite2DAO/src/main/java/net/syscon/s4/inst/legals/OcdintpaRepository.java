package net.syscon.s4.inst.legals;

import java.util.List;

import net.syscon.s4.inst.legals.beans.InterestedParties;

public interface OcdintpaRepository {

	List<InterestedParties> executeQuery(InterestedParties interestedParties);

	Integer insertInterestedParties(List<InterestedParties> insertList);

	Integer updateInterestedParties(List<InterestedParties> updateList);

	Integer deleteInterestedParties(List<InterestedParties> deleteList);

}
