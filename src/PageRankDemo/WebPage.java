/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageRankDemo;

import edu.uci.ics.jung.graph.DirectedSparseGraph;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author DreamingTraveler
 */
public class WebPage {
    private String url = "";
    private String title = "";
    private Set<String> urlSet = new HashSet();
    
    public WebPage(String url){
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getUrlSet() {
        return urlSet;
    }
    
    public void search(MyDatabase mydb, String url, DirectedSparseGraph dg) throws IOException, SQLException{
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a");
        urlSet.clear();
        
        String nextLink = "";
        for(Element ele: links){
            if(ele.hasText() && ele.hasAttr("abs:href")){
                nextLink = ele.attr("abs:href");
                
                if(urlSet.contains(nextLink)){
                    mydb.updateData(nextLink, ele.text());
                }
                else{
                    urlSet.add(nextLink);
                    System.out.println("Link: " + nextLink);
                    System.out.println("Title: " + ele.text());
                    mydb.insertData(nextLink, ele.text(), 0.0);
                } 
                dg.addEdge(dg.getEdgeCount(), url, nextLink);
            } 
        }
    }
}
