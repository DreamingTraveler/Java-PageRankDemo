/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageRankDemo;

import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import javax.net.ssl.SSLHandshakeException;
import javax.swing.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.collections15.*;

/**
 *
 * @author DreamingTraveler
 */
public class PageRankDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            //MyFrame myFrame = new MyFrame();
            //myFrame.getText();
            //HashSet<String> result = new HashSet();
            MyDatabase mydb = new MyDatabase();
            String mainUrl = "http://aps.ntut.edu.tw/course/tw/course.jsp";
            WebPage webpage = new WebPage(mainUrl);
            Set<String> allUrlSet = new HashSet();
            DirectedSparseGraph<String,Integer> directedGraph= new DirectedSparseGraph();
            int urlCount = 0;//Number of web page
            
            webpage.search(mydb, webpage.getUrl(), directedGraph);
            allUrlSet.addAll(webpage.getUrlSet());
            //System.out.println("-------------------------------------------------------------------");
            for(String eachUrl: allUrlSet){
                System.out.println("-------------------------------------------------------------------");
                //System.out.println(eachUrl);
                urlCount++;
                webpage.setUrl(eachUrl);
                webpage.search(mydb, eachUrl, directedGraph);
                //allUrlSet2.addAll(webpage.getUrlSet());
            }
            PageRank pagerank = new PageRank(directedGraph, 0.15);//alpha = 0.15
            pagerank.setTolerance(0.002);//2000 web page
            pagerank.setMaxIterations(urlCount);
            pagerank.evaluate();

            for(String url: directedGraph.getVertices()){
                System.out.println(url);
                if(pagerank.getVertexScore(url) instanceof Double){
                    System.out.println("PR = " + pagerank.getVertexScore(url));
                }
                double prValue = (double) pagerank.getVertexScore(url);
                mydb.addPageRank(url, prValue);
            }
            
//            for(String eachUrl: allUrlSet2){
//                System.out.println("****************************************");
//                System.out.println(eachUrl);
//                count++;
//                webpage.setUrl(eachUrl);
//                webpage.search(mydb, eachUrl);
//                
//            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
}
