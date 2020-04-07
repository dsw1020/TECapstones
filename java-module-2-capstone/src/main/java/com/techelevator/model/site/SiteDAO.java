package com.techelevator.model.site;

import java.time.LocalDate;
import java.util.List;






public interface SiteDAO {
	
	
	public List<Site> getAllSites(long campId,LocalDate fromDate,LocalDate toDate);
	

}
