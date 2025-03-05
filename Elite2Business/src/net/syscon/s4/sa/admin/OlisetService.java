package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.SystemLables;

/**
 * Interface OumrestaService
 */
public interface OlisetService {

	int insertContainerImg(Images offenderPptyContainers);

	List<Images> imagesExecuteQuery(Images searchBean);

	String inactiveImage(Images searchBean);
	
	List<Images> getLoginLogo();
	
	List<SystemLables> labelExecuteQuery(String codeInput);

	int updateSystemlabel(List<SystemLables> updateList);

}
