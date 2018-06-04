package io.github.ddmonk.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("test")
public class SimpleResource {

    public SimpleResource() {
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setCroMsg(){
        return Response.ok("with cross origin info").build();
    }


}
