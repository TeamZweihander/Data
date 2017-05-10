package com.zweihander.navup.data.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.*;
import org.springframework.stereotype.Service;

import java.net.URL;
import javax.xml.ws.Response;


@Service
public class DataService {

	private String uname = "admin";
    private String pass = "Admin123";
    private WebClient webClient = null;
    private URL strURL;
    

    
    public void login() throws Exception{
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
    }
    
    public void getLocation(String Mac) throws Exception{
        String address = "https://137.215.6.208/api/v1/location?sta_eth_mac=" + Mac ;
    	strURL = new URL(address);
    	WebRequest _address = new WebRequest(strURL);
    	WebResponse location = webClient.loadWebResponse(_address);

    	//System.out.println("-----------------------------");
    	//System.out.println("web response:   " + location.getContentAsString());
     }
    
    public void close(){
    	webClient.close();
    }
     
}
