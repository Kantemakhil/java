package net.syscon.s4.screensrelated.impl;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.syscon.s4.common.beans.RelatedScreens;
import net.syscon.s4.common.relatedScreen.RelatedScreensRepository;
import net.syscon.s4.relatedscreens.RelatedScreensService;

@Service
public class RelatedScreensServiceImpl implements RelatedScreensService {
	
	
	@Autowired
	RelatedScreensRepository relatedScreens;
	
	@Override
	public List<RelatedScreens> getRelatedScreens() throws SQLException {  
		return (List<RelatedScreens>) relatedScreens.getRelatedScreens();
	}

}

