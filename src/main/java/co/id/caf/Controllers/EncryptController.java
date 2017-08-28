/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.caf.Controllers;

import co.id.caf.crypto.TripleDES;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

/**
 *
 * @author DJ
 */
@Path("/encrypt") 
public class EncryptController {
    
    @GET
    @Produces("application/json")
    @Path("/hello")
    public String Test(){
        return JSONObject.quote("Dariaman");
    }
    
    @GET
    @Produces("application/json")
    @Path("/EncryptText/{PlainText}")
    public String EncGetString(@PathParam("PlainText") String PlainText) throws Exception{
        String ChiperText = new TripleDES().encrypt3DES(PlainText);
        return JSONObject.quote(ChiperText);
    }
    
}
