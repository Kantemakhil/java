package net.syscon.s4.sa.audit.maintenance.impl;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.dao.RecordGroup;
import net.syscon.s4.genericservices.BaseBusiness;
import net.syscon.s4.inst.legalscreens.maintenance.bean.TableColumnNameBean;
import net.syscon.s4.sa.audit.maintenance.OmsysjntRepository;
import net.syscon.s4.sa.audit.maintenance.OmsysjntService;
import net.syscon.s4.sa.audit.maintenance.userTabColumns;
/**
 * Class OmsysjntServiceImpl */
@Service
public class OmsysjntServiceImpl extends BaseBusiness implements OmsysjntService{

@Autowired
private OmsysjntRepository omsysjntRepository;


/**
 * This method is used to execute a record group
 *
 *@throws SQLException
*/
public List<RecordGroup> tableRgRecordGroup()  {
		return omsysjntRepository.tableRgRecordGroup();

}


@Override
public Integer createTr(Boolean insertTrigger) {
	 List<TableColumnNameBean> returnList = omsysjntRepository.createTr();
	 for(TableColumnNameBean bean :returnList) {
		 createOneTr(bean.getTableName(),insertTrigger);
	 }
	 return returnList.size();
}


@Override
public Integer createOneTr(String tableName,Boolean insertTrigger) {
	String tabnamejn;
	String trnamejn ;
	String vDdlStmt;
	List<userTabColumns> colLenCur = omsysjntRepository.colLenCur(tableName);
		if (tableName.length() > 25) {
			tabnamejn = tableName.substring(1, 26) + "_JN";
			trnamejn = tableName.substring(1, 26) + "_TJN";
		} else {
			tabnamejn = tableName + "_JN";
			trnamejn = tableName + "_TJN";
		}
		vDdlStmt =   "CREATE TABLE "+ tabnamejn +"(";
	      vDdlStmt =  vDdlStmt +"JN_OPERATION    CHAR(3)    NOT NULL ";
	      vDdlStmt = vDdlStmt +",JN_ORACLE_USER    VARCHAR2(30)    NOT NULL ";
	      vDdlStmt = vDdlStmt +",JN_DATETIME    DATE    NOT NULL ";
	      vDdlStmt = vDdlStmt +",JN_NOTES    VARCHAR2(240) ";
	      vDdlStmt = vDdlStmt +",JN_APPLN    VARCHAR2(30) ";
	      vDdlStmt = vDdlStmt +",JN_SESSION     NUMBER(38)";
		for (userTabColumns colLenRec : colLenCur) {
			if ("DATE".equals(colLenRec.getDataType())) {
				vDdlStmt = vDdlStmt + "," + colLenRec.getColumnName() + "  " + "DATE";

			} else if ("CHAR".equals(colLenRec.getDataType())) {
				vDdlStmt = vDdlStmt + "," + colLenRec.getColumnName() + "  " + colLenRec.getDataType() + "("
						 + colLenRec.getDataLength() + ")";
			} else if ("NUMBER".equals(colLenRec.getDataType())) {
				if (colLenRec.getDataPrecision() == null) {
					vDdlStmt = vDdlStmt + "," + colLenRec.getColumnName() + " NUMBER";
				} else if ("0".equals(colLenRec.getDataScale())) {
					vDdlStmt = vDdlStmt + "," + colLenRec.getColumnName() + " NUMBER" +
							"("+colLenRec.getDataPrecision()+ ")" ;
				} else
					vDdlStmt =  vDdlStmt + "," + colLenRec.getColumnName() + " NUMBER" +
							"("+colLenRec.getDataPrecision()+ "," +colLenRec.getDataScale()+ ")" ;
			}
		}
		vDdlStmt = vDdlStmt + ")";
		omsysjntRepository.createTable(vDdlStmt);
		vDdlStmt = null;
		String vInsStr = null;
		String str =" ";
		Integer ctr = 0;
		 if (insertTrigger) {
			 vInsStr = "INSERT OR "; 
		 }
		 vDdlStmt =  " CREATE OR REPLACE TRIGGER "+ trnamejn;
		 vDdlStmt = vDdlStmt +"   AFTER "+vInsStr+" UPDATE OR DELETE ON "+ tableName;
		 vDdlStmt = vDdlStmt +"   FOR EACH ROW";
		 vDdlStmt = vDdlStmt +"  BEGIN";
		 if(insertTrigger){
			       
			 vDdlStmt = vDdlStmt +"  IF INSERTING THEN";
		     vDdlStmt = vDdlStmt +"    INSERT INTO "+ tabnamejn +" (JN_OPERATION, ";
		     vDdlStmt = vDdlStmt +"    JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, ";
		         str = "    JN_SESSION";
		            for (userTabColumns x : colLenCur ){
		               str =  str  +", "+ x.getColumnName();
		               ctr = ctr + 1;
		               if( ctr >= 3 ){
		            	   vDdlStmt = vDdlStmt + str;
		                  ctr = 0;
		                  str = "   ";
		            }
		            }
		            str = str +" )";      
		            vDdlStmt = vDdlStmt + str;
		            vDdlStmt = vDdlStmt +"    VALUES ('INS'  , USER, SYSDATE, NULL, NULL, ";
		            str = "    0";
		            ctr = 0;
		            for (userTabColumns y : colLenCur ){
		               str =  str  +", :NEW."+ y.getColumnName();
		               ctr  = ctr + 1;
		               if (ctr >= 3 ){
		            	   vDdlStmt = vDdlStmt + str;
		                  ctr = 0;
		                  str = "   ";
		               }
		            }
		            str =str +" );";       
		            vDdlStmt = vDdlStmt +str;
		         vInsStr ="ELS";
          }
		 vDdlStmt = vDdlStmt +vInsStr+"IF UPDATING THEN";
          vDdlStmt = vDdlStmt +"    INSERT INTO "+ tabnamejn +" (JN_OPERATION, ";
          vDdlStmt = vDdlStmt +"    JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, ";
          str = "    JN_SESSION";
          for (userTabColumns x : colLenCur ){
          str =  str  +", "+ x.getColumnName();
          ctr = ctr + 1;
          if (ctr >= 3 ){
        	  vDdlStmt = vDdlStmt + str;
             ctr = 0;
             str = "   ";
          }
          }
       str = str + " )";      
       vDdlStmt = vDdlStmt +str;
       vDdlStmt = vDdlStmt +"    VALUES (  'UPD', USER, SYSDATE, NULL, NULL, ";
       str = "    0";
       ctr = 0;
       for (userTabColumns y : colLenCur ) {
          str =  str  +", :OLD."+ y.getColumnName();
          ctr = ctr + 1;
          if (ctr >= 3 ){
        	  vDdlStmt = vDdlStmt +str;
             ctr = 0;
             str = "    ";
          }
       }
       str = str + " );";      
       vDdlStmt = vDdlStmt + str;
       vDdlStmt  = vDdlStmt +"  ELSIF DELETING THEN";
       vDdlStmt  = vDdlStmt +"    INSERT INTO "+ tabnamejn +" (JN_OPERATION, ";
       vDdlStmt  = vDdlStmt +"    JN_ORACLE_USER, JN_DATETIME, JN_NOTES, JN_APPLN, ";
       str  = "    JN_SESSION";
       for (userTabColumns x : colLenCur ){
       str  =  str  +", "+ x.getColumnName();
       ctr = ctr + 1;
       if( ctr >= 3 ){
          vDdlStmt= vDdlStmt +str;
          ctr = 0;
          str = "    ";
       }
       }
    str  = str +" )";      
    vDdlStmt  = vDdlStmt +str;
    vDdlStmt  = vDdlStmt +"    VALUES ( 'DEL'  , USER, SYSDATE, NULL, NULL, ";
    str = "    0";
    ctr = 0;
    for (userTabColumns y : colLenCur ){
       str  =  str  +", :OLD."+ y.getColumnName();
       ctr  = ctr + 1;
       if( ctr >= 3 ){
          vDdlStmt  = vDdlStmt + str;
          ctr  = 0;
          str  = "    ";
    }
    }
    str  = str +" );";      
    vDdlStmt  = vDdlStmt +str;
 vDdlStmt  = vDdlStmt +"   END IF;";
 vDdlStmt  = vDdlStmt +"END;";
 
 omsysjntRepository.createTriggers(vDdlStmt);
 String lvExists = omsysjntRepository.checkSynonymCur(tabnamejn);
 if("N".equals(lvExists)){
	 String synonym = "CREATE PUBLIC SYNONYM "+ tabnamejn +" FOR "+ tabnamejn;
	 String grantqry="GRANT SELECT ON  "+ tabnamejn +" TO TAG_USER";
	 omsysjntRepository.synonyms(synonym);
	 omsysjntRepository.grantQuery(grantqry);
 }
	return 1;
}
}