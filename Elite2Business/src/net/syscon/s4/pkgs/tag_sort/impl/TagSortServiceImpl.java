package net.syscon.s4.pkgs.tag_sort.impl;

import org.springframework.stereotype.Service;

import net.syscon.s4.pkgs.tag_sort.TagSortService;

@Service
public class TagSortServiceImpl implements TagSortService {
	//This method is used to get orderBy from database
	@Override
	public String getOrderBy() {
		final String gvSordOrder = null;
		if (gvSordOrder != null) {
			return "ORDER BY " + gvSordOrder;
		}
		return gvSordOrder;
	}

}
