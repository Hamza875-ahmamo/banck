package org.softsofi.web;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.softsofi.dto.*;
import org.softsofi.entities.Customer;
import org.softsofi.exeception.CustomerNotFoundExeception;
import org.softsofi.services.CustomerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@ApplicationScoped
@Path("/api")
@AllArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;
    @Path("/customers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDto> getAllCustomers() {
        return customerService.listCustomers();
    }
    @Path("/customers/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto getCustomerById(@PathParam("id") Long id) throws CustomerNotFoundExeception {
        return customerService.getCustomerById(id);
    }
    @Path("/customers")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto saveCustomer(CustomerDto customer) {
        return customerService.saveCustomer(customer);
    }
    @Path("/customers/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDto updateCustomer(@PathParam("id") Long id, @RequestBody CustomerDto customer) {
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    @Path("/customers/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteCustomer(@PathParam("id") Long id) {
        customerService.deleteCustomer(id);
    }

    


    


}
