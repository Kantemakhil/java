package net.syscon.s4.pkgs.tag_wfmsg;

import java.sql.Clob;

public interface TagWfmsgService {

	void createXml();

	void append(final String name, final String number, final Clob xmlType);

	void append(final String name, final String value, final String type, final Clob xmlType);

}
