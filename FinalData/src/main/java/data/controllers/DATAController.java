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
        if(dataService.getLogIn() == false) {
            dataService.login();
        }
        String result = dataService.getLocation("DC:0B:34:F2:6E:56");
        return result;
    }
}
