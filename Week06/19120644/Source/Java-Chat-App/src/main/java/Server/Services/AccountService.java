package Server.Services;

import Server.DAO.AccountDAO;
import Server.Entities.Account;

public class AccountService {
    public void signUpAccount(String username,String password){
        AccountDAO accountDAO=new AccountDAO();
        Account newAccount=new Account(username,password);
        accountDAO.addData(newAccount);
    }
    public boolean authenticateAccount(String username,String password){
        AccountDAO accountDAO=new AccountDAO();
        Account temp=null;
        try{
            temp=accountDAO.getAccount(username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (temp==null){
            return false;
        }
        return true;
    }

}
