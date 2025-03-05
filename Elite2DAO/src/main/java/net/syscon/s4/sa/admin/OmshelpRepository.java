package net.syscon.s4.sa.admin;

import java.util.List;

import net.syscon.s4.im.beans.BaseHelpUrl;
import net.syscon.s4.im.beans.OmsModulesHelp;

/**
 * Interface OumrestaRepository
 */
public interface OmshelpRepository {


	List<OmsModulesHelp> moduleHelpExecuteQuery();

	int moduleHelpInsertCommit(List<OmsModulesHelp> insertList);

	int moduleHelpUpdateCommit(List<OmsModulesHelp> updateList);

	int moduleHelpDeleteCommit(List<OmsModulesHelp> deleteList);

	int insertBaseUrl(BaseHelpUrl commitBean);


	BaseHelpUrl urlExecuteQuery();

	


}
