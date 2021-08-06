package com.farmfriend.market;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BackgroundJobManager implements ServletContextListener {

    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new getLatestMarketPrices(), 0, 10, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        scheduler.shutdownNow();
    }

}

class getLatestMarketPrices implements Runnable {

	MarketPricesClient marketPricesClient=new MarketPricesClient();
	MarketPricesRepositoy marketPricesRepositoy=new MarketPricesRepositoy();
    @Override
    public void run() {
    	System.out.println("started batch job of every 10 mins to get latest market prices");
    	
    	//Call api.gov.in to get all latest market prices
    	MarketPricesResponse marketPricesResponse = marketPricesClient.getLatestMarketPricesFromAPI();
    	
    	//if API respones is not null then Save market prices in database table
    	if(marketPricesResponse!=null && marketPricesResponse.getRecords().size()>0) {
	    	marketPricesRepositoy.saveMarketPrices(marketPricesResponse);
	    	System.out.println("latest market prices saved in DB");
    	}
    	else {
    		System.out.println("latest market prices not found. keeping previous prices as it is in table.");
    	}
    	
    	
       
    }

}