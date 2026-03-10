package org.softsofi.web;

import java.util.List;

import org.softsofi.entities.Customer;
import org.softsofi.services.BanckAccountService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@ApplicationScoped
@Path("/api")
@AllArgsConstructor
public class CustomerRestController {
    private BanckAccountService banckAccountService;
    @Path("/customers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomers() {
        return banckAccountService.listCustomers();
    }

    


    


}
