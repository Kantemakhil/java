package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.Images;
import net.syscon.s4.im.beans.SystemLables;

/**
 * Interface OumrestaRepository
 */
public interface OlisetRepository {


	int insertContainerImg(Images images);

	List<Images> imagesExecuteQuery(Images searchBean);

	String inactiveImage(Images searchBean);
	
	List<Images> getLoginLogo();
	
	List<SystemLables> labelExecuteQuery(String codeInput);

	int updateSystemlabel(List<SystemLables> updateList);


}
