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
            
        }
        catch(Exception e)
        {
     
        }
        return cleanData;
        
    
}
}
