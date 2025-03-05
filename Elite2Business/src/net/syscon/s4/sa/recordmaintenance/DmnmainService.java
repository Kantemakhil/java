package net.syscon.s4.sa.recordmaintenance;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import net.syscon.s4.common.beans.ReferenceCodes;

public interface DmnmainService {
	List<DmnProcess> dmnsExecuteQuery();

	Integer dmnsCommit(DmnProcessCommitBean commitBean);
	
	Integer deployeDmn(DmnProcessCommitBean commitBean, String path);
	
	List<DmnProcess> getVersionHistory(DmnProcess dmnProcess);
	
	List<ReferenceCodes> getDmnsDeployedList();
	
	List<DmnProcess> getDmnDataByDmnDesc(DmnProcess dmnDetails);
	
	String getDmnFile(String dmnProcessKey);
	
	byte[] exportDmns(List<DmnProcess> lstOfProcessMain, String savedLocation) throws Exception;
	
	List<Map<String,Object>> importDmns(MultipartFile zipFile, String savedLocation) throws Exception;
}
