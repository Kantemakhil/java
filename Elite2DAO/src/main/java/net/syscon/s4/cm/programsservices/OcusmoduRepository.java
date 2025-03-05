package net.syscon.s4.cm.programsservices;
import java.util.List;
/**
 * Interface OcusmoduRepository
 */
public interface OcusmoduRepository {
	List<VAcpSchedules> vAcpSchExecuteQuery(VAcpSchedules objVAcpSchedules) ;

	String getWeekDay(String schDate);

}
