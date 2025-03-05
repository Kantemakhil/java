package net.syscon.s4.sa.admin.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.BaseHelpUrl;
import net.syscon.s4.im.beans.OmsModulesHelp;
import net.syscon.s4.sa.admin.OmshelpRepository;
import net.syscon.s4.sa.admin.OmshelpService;
import net.syscon.s4.sa.admin.beans.OmsModulesHelpCommitBean;

/**
 * Class OumrestaServiceImpl
 */
@Service
public class OmshelpServiceImpl extends BaseBusiness implements OmshelpService {
	 private final static Logger logger = LogManager.getLogger(OmshelpServiceImpl.class.getName());
    public OmshelpServiceImpl() {
    }

    
    
    @Autowired
    private OmshelpRepository omshelpRepository;

    
   

   
    
    /**
     * Fetch the records from database table
     *
     * @param searchRecord
     * @throws SQLException
     */
   

    /**
     * Insert the records from database table
     *
     * @param commitBean
     * @throws SQLException
     */
    

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
   

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */
    

    /**
     * This method is used to execute a record group
     *
     * @throws SQLException
     */

	@Override
	public List<OmsModulesHelp> moduleHelpExecuteQuery() {
		return omshelpRepository. moduleHelpExecuteQuery();
	}

	@Override
	public int moduleHelpCommit(OmsModulesHelpCommitBean commitBean) {
		int result=0;
		if(commitBean!=null && commitBean.getInsertList()!=null && commitBean.getInsertList().size()>0) {
			commitBean.getInsertList().forEach(bean->bean.setCreateUserId(commitBean.getCreateUserId()));
			result=omshelpRepository. moduleHelpInsertCommit(commitBean.getInsertList());
		} else if(commitBean!=null && commitBean.getUpdateList()!=null && commitBean.getUpdateList().size()>0) {
			commitBean.getUpdateList().forEach(bean->bean.setModifyUserId(commitBean.getCreateUserId()));
			result=omshelpRepository. moduleHelpUpdateCommit(commitBean.getUpdateList());
		}else if(commitBean!=null && commitBean.getDeleteList()!=null && commitBean.getDeleteList().size()>0) {
			commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
			result=omshelpRepository. moduleHelpDeleteCommit(commitBean.getDeleteList());
		}
		
		return result;
	}

	@Override
	public int insertBaseUrl(BaseHelpUrl commitBean) {
		int retunlist =0;
		retunlist=omshelpRepository.insertBaseUrl(commitBean);
		return retunlist;
	}

	@Override
	public BaseHelpUrl urlExecuteQuery() {
		return omshelpRepository.urlExecuteQuery();
	}
}