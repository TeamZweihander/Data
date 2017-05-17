package com.zweihander.navup.data.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebRequest;
import org.apache.log4j.BasicConfigurator;
import org.springframework.stereotype.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.ws.Response;
import java.net.URL;



@Service
public class DataService {

	private String uname = "admin";
    private String pass = "Aruba123!";
    private WebClient webClient = null;
    private URL strURL;
    public Boolean isLoggedin = false ;
    
    public Boolean getLogIn(){
        return isLoggedin ;
    }
    
    public void login() throws Exception{
        BasicConfigurator.configure();
        webClient = new WebClient();
        webClient.getOptions().setUseInsecureSSL(true);
        HtmlPage logPage = webClient.getPage("https://137.215.6.208");

        HtmlForm logForm = logPage.getHtmlElementById("login-form");
        HtmlSubmitInput log = logForm.getInputByValue("Log In");
        HtmlTextInput inUser = logForm.getInputByName("j_username");
        HtmlPasswordInput inPass = logForm.getInputByName("j_password");
        inUser.setValueAttribute(uname);
        inPass.setValueAttribute(pass);
        HtmlPage homePage = log.click();
        isLoggedin = true ;
    }
    
    public String getLocation(String Mac) throws Exception{
        //String address = "https://137.215.6.208/api/v1/building" ;

        String address = "https://137.215.6.208/api/v1/location?sta_eth_mac=" + Mac ;
    	strURL = new URL(address);
    	WebRequest _address = new WebRequest(strURL);
    	WebResponse location = webClient.loadWebResponse(_address);

    	
    	return sanitization(location.getContentAsString()) ;
     }
    
    public void close(){
    	webClient.close();
    }

    public String sanitization(String json) {

        org.json.JSONObject cleanData = null;
        org.json.JSONObject dirtyData = null;
        String data = "";
        double sta_x = 0 ;
        double sta_y = 0 ;
        String buildID ="" ;

        try {
            dirtyData = new org.json.JSONObject(json);



            //get the address
            JSONArray mainArray = dirtyData.getJSONArray("Location_result");
            org.json.JSONObject position = mainArray.getJSONObject(0);
            org.json.JSONObject location = position.getJSONObject("msg");
            org.json.JSONObject address = location.getJSONObject("sta_eth_mac");

            //getting the sta_location_x,sta_location_y and BuildingID of the device
            sta_x = location.getDouble("sta_location_x");
            sta_y = location.getDouble("sta_location_y");
            buildID = location.getString("building_id") ;

        } catch (Exception e) {
            System.out.println("JSON object error");
        }
        return newCoords(sta_x, sta_y, buildID);
    }

    public static String newCoords(double dx, double dy, String id){
        /*NB!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        the ALE server relies very much on the strength of wifi,
        the below commented out code works for determining the location
        inside of the IT building. However when we tried to test by
        emb 4-152 we noticed that the ale servers response would not update
        this could be due to the fact that emb 4-152 room is not on the ale
        server as it only has two buildings listed being the it and emb, but it
        could be the emb building down as it sees emb 4-152 as part of the conference
        center. so we have mocked the output but you can test in the server room
        to see it work, just uncomment the code.
         */

        String result = "" ;
        double newLat = 0;
        double newLong = 0;
        if(id.equals("08BEB4DE95EA3D6B999119FAA9B0F1C1")) //IT building
        {
            newLat = -25.75560455 + ((-dy/1000)/6378) * (180 / Math.PI);
            newLong = 28.23221553 + (((dx/1000)/6378) * (180 / Math.PI))/Math.cos(-25.75560455 * (Math.PI/180)) ;
        }
        else if(id.equals("9E4C881FFFE335F596BF9420FDED8BC7"))
        {
            /* The wifi was not able to give the ale server information it needed to update the locations,
            at some points it could not even find the building we where in hard coded values for sake of demo
            these are locations near emb 4-152
             */
            //newLat = -25.7555246;
            //newLong = 28.2337153 ;

            newLat = latitudeOfRouterInEMSBuilding + ((-dy/1000)/6378) * (180 / Math.PI);
            newLong = LongitudeOfRouterInEMSBuilding + (((dx/1000)/6378) * (180 / Math.PI))/Math.cos(latitudeOfRouterInEMSBuilding * (Math.PI/180)) ;
        }

        String data ;
        data = "";
        data += "{\n";
        data += "      \"latitude\": " + newLat + ",\n";
        data += "      \"longitude\": " + newLong + "\n";
        data += "}";
        return data ;
    }
     
}
