package th.ac.ku.atm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;



@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private BankAccountService accountService;

    public BankAccountController(BankAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        accountService.openAccount(bankAccount);
        model.addAttribute("bankaccounts",accountService.getBankAccounts());
        return "redirect:bankaccount";
    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model){
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-edit";
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model){
        accountService.editBankAccount(bankAccount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@ PathVariable int id,
                                @ModelAttribute BankAccount bankAccount,
                                Model model){
        BankAccount storedAccount = accountService.getBankAccount(id);
        accountService.deleteBankAccount(storedAccount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositBankAccountPage(@PathVariable int id, Model model){
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-deposit";
    }

    @PostMapping("/deposit/{id}")
    public String deposit(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model, double amount){
        BankAccount storedAccount = accountService.getBankAccount(id);
        accountService.deposit(storedAccount, amount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawBankAccountPage(@PathVariable int id, Model model){
        BankAccount bankAccount = accountService.getBankAccount(id);
        model.addAttribute("bankAccount", bankAccount);
        return "bankaccount-withdraw";
    }

    @PostMapping("/withdraw/{id}")
    public String withdraw(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model, double amount){
        BankAccount storedAccount = accountService.getBankAccount(id);
        accountService.withdraw(storedAccount, amount);
        model.addAttribute("bankaccounts", accountService.getBankAccounts());
        return "redirect:/bankaccount";
    }
}
