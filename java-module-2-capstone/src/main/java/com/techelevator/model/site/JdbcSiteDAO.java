package com.techelevator.model.site;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;



public class JdbcSiteDAO implements SiteDAO {
	private JdbcTemplate jdbcTemplate;

	public JdbcSiteDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}


		@Override
		public List<Site> getAllSites(long campId,LocalDate fromDate,LocalDate toDate) {
			List<Site> siteInfo = new ArrayList<>();
			String site = "select * from site s where s.campground_id = ? and s.site_id not in (SELECT r.site_id FROM reservation r " +
						   "WHERE (r.to_date < ? AND r.from_date > ?) " +
						   "OR (r.to_date < ? AND r.from_date > ?) " +
						   "OR (r.to_date > ? AND r.from_date < ?)) LIMIT 5";
			SqlRowSet siteNextRow = jdbcTemplate.queryForRowSet(site, campId,
																fromDate,fromDate,
																toDate,toDate,
																fromDate,toDate);
																
			while(siteNextRow.next()){
				Site newSite = mapRowToSite(siteNextRow);
				siteInfo.add(newSite);
			}
			return siteInfo;
		}
	
	
	private Site mapRowToSite(SqlRowSet results){
		Site theSite = new Site();
			theSite.setSite_id(results.getLong("site_id"));
			theSite.setCampground_id(results.getLong("campground_id"));
			theSite.setSite_number(results.getLong("site_number"));
			theSite.setMax_occupancy(results.getLong("max_occupancy"));
			theSite.setAccessible(results.getBoolean("accessible"));
			theSite.setMax_rv_length(results.getLong("max_rv_length"));
			theSite.setUtilities(results.getBoolean("utilities"));
			
	
			return theSite;
	}


	
}
