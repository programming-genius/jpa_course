package it.jpa.logging;

import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("deprecation")
public class HibernateRegionStatistics {

	private static final Logger logger = LoggerFactory.getLogger(HibernateRegionStatistics.class);
	
	public static void printCacheRegionStatistics(String region, Session session) {
		SecondLevelCacheStatistics statistics = 
				session.getSessionFactory().getStatistics().getSecondLevelCacheStatistics(region);
		logger.debug("\nRegion:{},\nStatistics:{},\nEntries:{}", region, statistics, statistics.getEntries());
	}
	
}
