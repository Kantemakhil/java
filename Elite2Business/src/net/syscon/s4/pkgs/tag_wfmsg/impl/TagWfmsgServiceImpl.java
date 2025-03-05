package net.syscon.s4.pkgs.tag_wfmsg.impl;

import java.sql.Clob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgRepository;
import net.syscon.s4.pkgs.tag_wfmsg.TagWfmsgService;
/*
 * Below comments are copied from package TAG_WFMSG
||    Purpose: This package provides utility functions for handling parameters
||             in workflow
||
||    MODIFICATION HISTORY (Please put version history IN a REVERSE-chronological ORDER below)
||    ------------------------
||    Person                     DATE     Version  Comments
||    ---------              -----------  -------  -----------------------------------
||    Himanshu               17-Jul-2012 2.3  HPQC#3728 Added function Show version and commented procedure show version
||    GJC                    21-Jun-2006  2.2       Substitue XML special characters
||    NDB                    19-Mar-2006  2.1       Error messages added
*/

@Service
public class TagWfmsgServiceImpl implements TagWfmsgService {

	private static final String N = "N";
	@Autowired
	private TagWfmsgRepository tagWfmsgRepository;

	@Override
	public void createXml() {
		// TODO In this method xml type code is present

		/**
		 * lv_xml XMLType; lv_xml := XMLType('<ps></ps>');
		 * 
		 * RETURN (lv_xml);
		 */

	}

	/*
	 * This procedure is migrated from oracle TAG_WFMSG
	 * 
	 * @Procedure APPEND to be used for replacing the value
	 *
	 */
	@Override
	public void append(final String name, final String value, final String type, final Clob xmlType) {
		String lValue = null;
		lValue = lValue != null ? lValue.replaceAll("&(?!amp;)", "&amp;") : null;
		lValue = lValue != null ? lValue.replaceAll(">", "&gt;") : null;
		lValue = lValue != null ? lValue.replaceAll("<", "&lt;") : null;
		lValue = lValue != null ? lValue.replaceAll("''", "&apos;") : null;
		lValue = lValue != null ? lValue.replaceAll("``", "&quot;") : null;

		/*
		 * lv_xml:= XMLTYpe('<' || p_name || '>' || '<type>' || p_type || '</type>' ||
		 * '<value>'|| l_value || '</value>' || '</' || p_name || '>');
		 */
		// p_xml := p_xml.appendchildxml('/ps', lv_xml);

	}

	// This PROCEDURE append is migrated from oracle
	@Override
	public void append(final String name, final String number, final Clob xmlType) {
		this.append(name, number + "", N, xmlType);
	}
}
