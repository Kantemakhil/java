package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.BaseHelpUrl;
import net.syscon.s4.im.beans.OmsModulesHelp;
import net.syscon.s4.sa.admin.beans.OmsModulesHelpCommitBean;

/**
 * Interface OumrestaService
 */
public interface OmshelpService {

	

	List<OmsModulesHelp> moduleHelpExecuteQuery();

	int moduleHelpCommit(OmsModulesHelpCommitBean commitBean);

	int insertBaseUrl(BaseHelpUrl commitBean);


	BaseHelpUrl urlExecuteQuery();

}
