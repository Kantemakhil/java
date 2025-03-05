package net.syscon.s4.inst.property;
import java.util.List;

import net.syscon.s4.common.beans.AgencyInternalLocations;
import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.common.beans.SystemProfilesCommitBean;
import net.syscon.s4.im.beans.AgencyLocations;
import net.syscon.s4.im.beans.Dual;
import net.syscon.s4.inst.property.bean.OffenderPptyContainers;
import net.syscon.s4.inst.property.bean.OffenderPptyContainersCommitBean;
import net.syscon.s4.inst.property.bean.OffenderPptyItems;
import net.syscon.s4.inst.property.bean.PropertyStorages;
/**
 * Interface OidtpconService 
 */
public interface OidtpconService  {
	AgencyLocations cgfkchkOffConOffConAgy(AgencyLocations paramBean);

	List<AgencyLocations> cgfkOffConTrnToAgyLocIdRecordGroup(String agyLocId);

	Dual cgwhenNewFormInstance(String user) ;

	AgencyInternalLocations offConPostQuery(AgencyInternalLocations paramBean);

	List<Object> vPheadOnCheckDeleteMaster(OffenderPptyContainers paramBean);

	List<AgencyLocations> rgSelectAllRecordGroup();

	List<SystemProfiles> sysPflExecuteQuery(SystemProfiles objSystemProfiles);

	PropertyStorages cgfkchkOffConOffConPpty(PropertyStorages paramBean);

	Integer sysPflCommit(SystemProfilesCommitBean commitBean);

	String offConWhenNewRecordInstance(Integer paramBean);

	Integer offConCommit(OffenderPptyContainersCommitBean commitBean);

	List<OffenderPptyContainers> offConExecuteQuery(OffenderPptyContainers offPptyCon,String opertaion);

	ReferenceCodes cgfkchkOffConOffConRef(ReferenceCodes paramBean);

	List<OffenderPptyItems> getItemStatus(OffenderPptyItems paramBean);
	
	Integer offPendPropCommit(OffenderPptyContainersCommitBean commitBean);
	
	Integer offPenPropConCommit(OffenderPptyContainersCommitBean commitBean);

}
