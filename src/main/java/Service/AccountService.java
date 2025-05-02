package Service;

import Model.Account;

import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account) {
        if(account.getUsername().trim().isEmpty()
            || account.getPassword().length() < 4
            || accountDAO.checkAccountExists(account)) {
            return null;
        }

        return accountDAO.insertAccount(account); 
    }

    public Account loginAccount(Account account) {
        return accountDAO.getAccountByUsernameAndPassword(account);
    }
}
