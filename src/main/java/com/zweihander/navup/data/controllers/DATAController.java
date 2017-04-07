package com.zweihander.navup.data.controllers;


import com.zweihander.navup.data.domain.GISDataObject;
import com.zweihander.navup.data.domain.LocationRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class DATAController {
    // Todo: inject

    @RequestMapping(value = "/getDeviceLocation", method = RequestMethod.POST)
    public @ResponseBody
    GISDataObject getGISDOByCoordinates(@RequestBody LocationRequest deviceData) {
        
        GISDataObject gisDataObject = new GISDataObject();
        double[] location = new double[2] ;
        location[0] = -25.755165 ;
        location[1] = 28.231979 ;
        gisDataObject.setGpsCoords(location); 
        //gisDataObject.setGpsTags
        return gisDataObject;
    }
}
