package org.issue;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/repr")
public class GreetingResource {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @PermitAll
  @Path("/unprotected")
  public String unprotectedRes() {
    System.out.println("Inside GreetingResource/unprotected");
    System.out.println("===================================");
    return "Hello from RESTEasy Reactive (unprotected)";
  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/protected")
  @RolesAllowed("ADMIN")
  public String protectedRes() {
    System.out.println("Inside GreetingResource/protected");
    System.out.println("=================================");
    return "Hello from RESTEasy Reactive (protected)";
  }
}
