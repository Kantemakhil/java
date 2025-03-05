package net.syscon.s4.cm.programsservices;
import java.util.List;

import net.syscon.s4.iwp.beans.VAcpSchedules;

 
public interface OcuschprRepository {
	List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules objVAcpSchedules) ;

}
