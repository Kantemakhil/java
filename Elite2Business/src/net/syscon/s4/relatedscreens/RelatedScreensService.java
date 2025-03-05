package net.syscon.s4.relatedscreens;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.RelatedScreens;


public interface RelatedScreensService {
	
	List<RelatedScreens> getRelatedScreens() throws SQLException;
}
