package net.syscon.s4.sa.recordmaintenance;

import java.util.List;

public interface ProsdeacRepository {
	List<BpmnProcess> getProcessDetList(List<String> procDefIdList);
}
