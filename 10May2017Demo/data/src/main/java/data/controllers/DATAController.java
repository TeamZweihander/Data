package com.zweihander.navup.data.controllers;

import com.zweihander.navup.data.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DATAController {
    // Todo: inject

    //@Autowired
   DataService dataService = new DataService();

    @RequestMapping(value = "/getLocation", method = RequestMethod.GET)
    public @ResponseBody
    String login() throws Exception {
         dataService.login();
         dataService.getLocation("dc:ob:34:f2:6e:56");
         /*Log into the ale server was not working when we tried, we looked hard into this 
         and couldnt find a solution so for the demo we have left the code in that did not 
         log in correctly this is mostly the login, we than have a santize class that takes 
         the result of the location call and cleans it, but due to the problems this will be
         showcased with mock data rather than real data we can recieve, we have 
         however we are simply return a mock location inside ems building for the 
         demo. Soryy */
         return "{" +
                "Longitude:-25.755532," +
                "Latitude:28.233169" +
                "}";
    }
}
