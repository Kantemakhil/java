package net.syscon.s4.common;

import java.util.List;

@FunctionalInterface
public interface IFormPathDao {

	List<String> vsCommCur(String lCurrentForm, String lCaseloadType);

}
