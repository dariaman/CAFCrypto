/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.caf.Controllers;

import co.id.caf.crypto.TripleDES;
import javax.crypto.BadPaddingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject;

/**
 *
 * @author dariaman.siagian
 */
@Path("/decrypt")
public class DecryptController {
    
    @GET
    @Produces("application/json")
    @Path("/DecryptText/{CipherText}")
    public String DecString(@PathParam("CipherText") String CipherText) throws Exception
    {
        try{
            String PlainText = new TripleDES().decrypt3DES(CipherText);
            return JSONObject.quote(PlainText);
        }catch(BadPaddingException bpe){
            return JSONObject.quote("Cipher and Key not match");
        }
    }
}
