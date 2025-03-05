package net.syscon.s4.sa.admin.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.syscon.s4.common.beans.ReferenceCodes;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.im.beans.MovementReasons;
import net.syscon.s4.sa.admin.OumcdtabRepository;
import net.syscon.s4.sa.admin.OumcdtabService;
import net.syscon.s4.sa.admin.beans.AllTabColumns;
import net.syscon.s4.sa.admin.beans.CopyTables;
import net.syscon.s4.sa.admin.beans.CopyTablesCommitBean;
/**
 * Class OumcdtabServiceImpl */
@Service
public class OumcdtabServiceImpl extends BaseBusiness implements OumcdtabService{

@Autowired
private OumcdtabRepository oumcdtabRepository;

/**Fetch the records from database table
 *
 * @param searchRecord
 *
 * @throws SQLException
 */
public List<CopyTables> modifyTabExecuteQuery(CopyTables searchRecord)  {
		   return oumcdtabRepository.modifyTabExecuteQuery(searchRecord);

}

/**Insert the records from database table
 *
 * @param lstMODIFY_TAB
 *
 * @throws SQLException
 */
@Transactional
public List<CopyTables> modifyTabCommit(CopyTablesCommitBean commitBean)  {
	List<CopyTables> returnList=new ArrayList<CopyTables>();
	CopyTables returnObject=new CopyTables();
		int liReturn = 0;
			if (commitBean.getInsertList() != null	&& commitBean.getInsertList().size() > 0) {
				  for (CopyTables obj : commitBean.getInsertList()) {
					obj.setCreateUserId(commitBean.getCreateUserId());
				}
				liReturn = oumcdtabRepository.modifyTabInsertCopyTables(commitBean.getInsertList());
				returnObject.setReturnValue(liReturn);
			}
			  if (commitBean.getUpdateList() != null	&& commitBean.getUpdateList().size() > 0) {
				for (CopyTables obj : commitBean.getUpdateList()) {
					obj.setModifyUserId(commitBean.getCreateUserId());
				}
				liReturn = oumcdtabRepository.modifyTabUpdateCopyTables(commitBean.getUpdateList());
				returnObject.setReturnValue(liReturn);
			}
			if (commitBean.getDeleteList() != null	&& commitBean.getDeleteList().size() > 0) {
				commitBean.getDeleteList().forEach(bo -> {bo.setModifyUserId(commitBean.getCreateUserId());});
				liReturn = oumcdtabRepository.modifyTabDeleteCopyTables(commitBean.getDeleteList());
				returnObject.setReturnValue(liReturn);
			}
			returnList.add(returnObject);
			return returnList;
}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<ReferenceCodes> cgfkModifyTabMovementTypeRecordGroup()  {
		return oumcdtabRepository.cgfkModifyTabMovementTypeRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<MovementReasons> cgfkModifyTabMovementReasoRecordGroup(String movementType)  {
	List<MovementReasons> resultList=new ArrayList<MovementReasons>();
		 resultList=oumcdtabRepository.cgfkModifyTabMovementReasoRecordGroup(movementType);
		 for (MovementReasons movementReasons : resultList) {
			 if("Y".equals(movementReasons.getActiveFlag())){
				 movementReasons.setCanDisplay(true);
			 } else {
				 movementReasons.setCanDisplay(false);
			 }
			
		}
		return resultList;

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<AllTabColumns> lovParentTableRecordGroup()  {
		return oumcdtabRepository.lovParentTableRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<AllTabColumns> lovTableNameRecordGroup()  {
		return oumcdtabRepository.lovTableNameRecordGroup();

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<AllTabColumns> lovColumnNameRecordGroup(String tableName)  {
		return oumcdtabRepository.lovColumnNameRecordGroup(tableName);

}
/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<AllTabColumns> lovSeqNameRecordGroup()  {
		return oumcdtabRepository.lovSeqNameRecordGroup();

}

}