package net.syscon.s4.triggers;

public interface TempAbsenceTimeTablesT1Repository {
	Long lvTempAbsSchId();

	TempAbsenceTimeTables getTempAbsenceTimeTables(TempAbsenceTimeTables tempAbsenceTimeTables);
}
