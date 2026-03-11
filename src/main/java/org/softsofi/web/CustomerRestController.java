package org.softsofi.web;

import java.util.List;

import org.softsofi.dto.*;
import org.softsofi.entities.Customer;
import org.softsofi.exeception.CustomerNotFoundExeception;
import org.softsofi.services.BanckAccountService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
    public List<CustomerDto> getAllCustomers() {
        return banckAccountService.listCustomers();
    }
    @Path("/customers/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto getCustomerById(@PathParam("id") Long id) throws CustomerNotFoundExeception {
        return banckAccountService.getCustomerById(id);
    }
    @Path("/customers")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto saveCustomer(CustomerDto customer) {
        return banckAccountService.saveCustomer(customer);
    }

    

    


    


}
