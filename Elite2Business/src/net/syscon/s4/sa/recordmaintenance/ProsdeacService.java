package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public interface ProsdeacService {
	
	List<BpmnProcess> processDeacExecuteQuery();
	
	Integer suspendProcess(BpmnProcess bpmnProcess);
	
	Integer activatedProcess(BpmnProcess bpmnProcess);
	
	Integer deleteProcess(BpmnProcess bpmnProcess);

}
