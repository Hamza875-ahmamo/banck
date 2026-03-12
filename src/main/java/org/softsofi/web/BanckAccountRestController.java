package org.softsofi.web;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.softsofi.dto.BanckAccountDto;
import org.softsofi.dto.CurrentBanckAccountDto;
import org.softsofi.dto.SavingAccountDto;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.CustomerNotFoundExeception;
import org.softsofi.services.BanckAccountService;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@ApplicationScoped
@Path("/api")
@AllArgsConstructor
public class BanckAccountRestController {
    private final BanckAccountService banckAccountService;

    @Path("/account/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public BanckAccountDto getBanckAccountById(@PathParam("id") String accountId) throws BanckAccountNotFoundExeception{
        return banckAccountService.getBanckAccountById(accountId);
    }
    @Path("/accounts")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public List<BanckAccountDto> getAllBanckAccounts(){
        return banckAccountService.listBanckAccounts();
    }
    @Path("/accounts/save/current")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CurrentBanckAccountDto saveCurrentBanckAccount(@RequestBody CurrentBanckAccountDto currentBanckAccountDto) throws CustomerNotFoundExeception{
        return banckAccountService.saveCurrentBanckAccount(currentBanckAccountDto.getBalance(), currentBanckAccountDto.getOverDraft(), currentBanckAccountDto.getCustomer().getId());
    }   
    @Path("/accounts/save/saving")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SavingAccountDto saveSavingBanckAccount(@RequestBody SavingAccountDto savingAccountDto) throws CustomerNotFoundExeception{
        return banckAccountService.saveSavingBanckAccount(savingAccountDto.getBalance(), savingAccountDto.getInterestRate(), savingAccountDto.getCustomer().getId());
    }
   
    }
   

