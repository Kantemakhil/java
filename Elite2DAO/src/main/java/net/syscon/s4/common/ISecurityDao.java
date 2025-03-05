package net.syscon.s4.common;

import java.sql.SQLException;
import java.util.List;

@FunctionalInterface
public interface ISecurityDao {

	List<String> cursorAppCode(String lvFormName) throws SQLException;

}
