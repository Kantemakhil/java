package net.syscon.s4.cm.programsservices;

import java.util.List;

import net.syscon.s4.iwp.beans.VAcpSchedules;
/**
 * Interface OcuschprService 
 */
public interface OcuschprService  {

	List<VAcpSchedules> vAcpSchedulesExecuteQuery(VAcpSchedules objVAcpSchedules) ;

}
