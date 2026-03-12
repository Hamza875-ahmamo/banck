package org.softsofi.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.softsofi.dto.AccountOperationDto;
import org.softsofi.dto.CreditDTO;
import org.softsofi.dto.DebitDTO;
import org.softsofi.dto.TransferDTO;
import org.softsofi.exeception.BanckAccountNotFoundExeception;
import org.softsofi.exeception.BlanceNotSufficientExeception;
import org.softsofi.services.AccountOperationService;

@ApplicationScoped
@AllArgsConstructor
@Path("/api/account-operations")

public class AccountOperationRestController {
    private final AccountOperationService accountOperationService;
    @GET
    @Path("/{accountId}")
    public List<AccountOperationDto> listAllAccountOperations(@PathParam("accountId") String accountId) {
        return accountOperationService.listAllAccountOperations(accountId);
    }
    @POST
    @Path("/debit")
    public void debit(@RequestBody DebitDTO debitDTO) throws BanckAccountNotFoundExeception, BlanceNotSufficientExeception {
        accountOperationService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
    }

    @POST
    @Path("/credit")
    public void credit(@RequestBody CreditDTO creditDTO) throws BanckAccountNotFoundExeception {
        accountOperationService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
    }

    @POST
    @Path("/transfer")
    public void transfer(@RequestBody TransferDTO transferDTO) throws BanckAccountNotFoundExeception, BlanceNotSufficientExeception {
        accountOperationService.transfer(transferDTO.getAccountIdSource(), transferDTO.getAccountIdDestination(), transferDTO.getAmount());
    }
}
