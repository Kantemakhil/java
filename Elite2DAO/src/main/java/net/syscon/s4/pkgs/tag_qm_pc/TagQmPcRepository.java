package net.syscon.s4.pkgs.tag_qm_pc;

import net.syscon.s4.pkgs.QmCompositions;
import net.syscon.s4.pkgs.QmObjects;
import net.syscon.s4.pkgs.QmProcesses;

public interface TagQmPcRepository {

	Integer prInsQmProcesses(QmProcesses qmProcesses);

	Integer prInsQmCompositions(QmCompositions qmCompositions);

	Integer prInsQmObjects(QmObjects qmObjects);

	Integer prUpdQmCompositions(QmCompositions qmCompositions);

	Long qmProcessesCur(String name);
	
}
