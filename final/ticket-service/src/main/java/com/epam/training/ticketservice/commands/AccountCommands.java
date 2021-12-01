package com.epam.training.ticketservice.commands;

import com.epam.training.ticketservice.services.AccountService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountCommands {

    AccountService accountService;

    public AccountCommands(AccountService accountService) {
        this.accountService = accountService;
    }

    @ShellMethod(key = "sign in privileged", value = "Sign in as privileged user.")
    public String signInPrivileged(String username, String password) {
        return accountService.signInPrivileged(username, password);
    }

    @ShellMethod(key = "sign out", value = "Sign out.")
    public void signOut() {
        accountService.signOut();
    }

    @ShellMethod(key = "describe account", value = "Describe account.")
    public String describeAccount() {
        return accountService.describeAccount();
    }

}
