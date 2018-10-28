package com.nseapiweb.api;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sun.swing.internal.plaf.synth.resources.synth;

/**
 *
 * @author kmacharia
 */
public class NSEAPI {

	 
	    
	    public static synchronized Map<String,Object> getHistoricalInfo(String symbol){
	    	
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	 try { 
	    		 
	         	Document doc = Jsoup.connect("https://www.nseindia.com/live_market/dynaContent/live_watch/get_quote/getHistoricalData.jsp?symbol="+symbol+"&series=EQ&fromDate=undefined&toDate=undefined&datePeriod=week").get();
	         	 
	         	
	         	//System.out.println(doc.getElementsByTag("table").get(0).getElementsByTag("tr").get(0));
	         	//System.out.println(doc.getElementsByTag("table").get(0).getElementsByTag("tr").get(1));
	         	
	         	Element historicalData = doc.getElementsByTag("table").get(0).getElementsByTag("tr").get(1);
	         	String previousHigh = historicalData.getElementsByTag("td").get(4).text();
	         	String previousLow = historicalData.getElementsByTag("td").get(5).text();
	         	String previousClose = historicalData.getElementsByTag("td").get(5).text();
	         	
	         	map.put("success", Boolean.TRUE.toString());
	         	map.put("symbol", symbol);
	         	map.put("priviousHigh", previousHigh);
	         	map.put("priviousLow", previousLow);
	         	map.put("previousClose", previousClose);
	         	
	         } catch (Exception ex) {
	        	 map.put("success", Boolean.FALSE.toString());
	        	 map.put("symbol", symbol);
		         map.put("priviousHigh", "");
		         map.put("priviousLow", "");
		         map.put("previousClose", "");
	         }
	    	
	    	return map;
	    }
	   
	    public static synchronized  Map<String,Object> getQuoteInfo(String symbol){
	    	
	    	Map<String,Object> map = new HashMap<String,Object>();
	    	 try { 
	    		 
	         	Document doc = Jsoup.connect("https://in.finance.yahoo.com/quote/"+symbol+".NS?p="+symbol+".NS&.tsrc=fin-srch-v1").get();
	         	Element header = doc.getElementById("quote-header-info");
	         	Element quoteSummary = doc.getElementById("quote-summary");
	         	String symbolName =  header.child(1).child(0).child(0).child(0).text().split("-")[1].substring(1);
	         	String open =  quoteSummary.text().split(" ")[4];
	         	String close =  header.child(2).text().split(" ")[0]; 
	         	map.put("sucess", Boolean.TRUE.toString());
	         	map.put("symbol", symbol);
	         	map.put("symbolName", symbolName);
	         	map.put("open", open);
	         	map.put("close", close);
	         }catch(Exception exception){
        	 	map.put("sucess", Boolean.FALSE.toString());
	         	map.put("symbol", symbol);
	         	map.put("symbolName", "");
	         	map.put("open", "");
	         	map.put("close", "");
		        	 
	         }
	    	
	    	return map;
	    }
	    public static void main(String[] args) {
        	 
        	System.out.println(getHistoricalInfo("TCS"));
    }
     
}
