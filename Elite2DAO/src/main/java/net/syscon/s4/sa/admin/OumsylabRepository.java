package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.common.beans.SystemProfiles;
import net.syscon.s4.im.beans.OmsModules;
import net.syscon.s4.im.beans.SystemLables;

/**
 * Interface OumrestaRepository
 */
public interface OumsylabRepository {

    List<OmsModules> rleInarcExecuteQuery(OmsModules objOmsModules);

	List<SystemLables> labelExecuteQuery(String codeInput);

	int updateSystemlabel(List<SystemLables> updateList);

	List<SystemLables> getPropertiesFromDb();
	
	Integer setSystemLables(SystemLables systemlable);

	List<SystemProfiles> getSystemProfiles();

	Integer setSystemProfileToSystemlabels(SystemLables systemLablesTemp);

	Integer countOfLabel();

	Integer countOfProfile();
	
	List<SystemLables> getSystemLabel(int startIndex,int lastIndex);
	
	List<SystemLables> labelByKeyExecuteQuery(String msgKey);


}
