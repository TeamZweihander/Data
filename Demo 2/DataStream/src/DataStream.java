
import org.json.JSONArray;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xoliswa.ntshingila,diana.obo,martha.mohlala ,mark.klingenberg
 */

public class DataStream {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public org.json.JSONObject sanitization(String json){
       
        org.json.JSONObject cleanData = null;
        org.json.JSONObject dirtyData = null;
        
        try{
            dirtyData = new org.json.JSONObject(json);
            
            float longitude = 0;
            float latitude = 0;
            float altitude = 0;
          
            //get the address 
            JSONArray mainArray = dirtyData.getJSONArray("Location_result");
            org.json.JSONObject position  = mainArray.getJSONObject(0);
            org.json.JSONObject location = position.getJSONObject("msg");
            org.json.JSONObject address = location.getJSONObject("sta_eth_mac");
            
           
            
            
            
        }
        catch(Exception e)
        {
     
        }
        return cleanData;
        
    
}
}
