package net.syscon.s4.common.relatedScreen;

import java.sql.SQLException;
import java.util.List;

import net.syscon.s4.common.beans.RelatedScreens;

public interface RelatedScreensRepository {
	List<RelatedScreens>  getRelatedScreens() throws SQLException;
}
