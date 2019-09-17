package by.training.catalog.dao;

import by.training.catalog.bean.Account;

public interface UserDao extends Dao<Account> {
    Account findAccountByUsername(String username) throws PersistenceException;
    Account findAccountByEmail(String email) throws PersistenceException;
    Account findAccountByPhone(int phone) throws PersistenceException;
    int findAccountCount() throws PersistenceException;
}
