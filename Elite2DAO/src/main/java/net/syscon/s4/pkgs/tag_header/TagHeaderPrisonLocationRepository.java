package net.syscon.s4.pkgs.tag_header;

import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;

public interface TagHeaderPrisonLocationRepository {

	String getDescCur(String agyLocId);

	Long checkMultiLocsCur(Long offenderId);

	String commAgyCur(Long offenderId);

	String getCommStaffCur(Long offenderBookId);

	String getInstStaffCur(Long offenderBookId);

	List<AgencyInternalLocations> getTagIntLocIntLocPath(Integer intenalLoctionId);

}
