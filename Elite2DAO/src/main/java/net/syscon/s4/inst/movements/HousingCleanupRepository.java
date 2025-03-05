package net.syscon.s4.inst.movements;

import java.util.List;
import net.syscon.s4.im.beans.Offenders;

public interface HousingCleanupRepository {
	List<Offenders> getOffenderDetails(List<Offenders> offenderInsertList);

	List<Offenders> getOffenderDet(List<Offenders> offenderInsertList,String userName);
	Boolean checkAgyLocationExist();
	Integer assignDefaultLocation(String userName);
	
	Integer insertData(String userName);
	
}
