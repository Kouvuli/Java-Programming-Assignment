package Server.DAO;

import Server.Entities.Account;
import Server.Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AccountDAO {
    public int addData(Account data) {
        Session session= HibernateUtils.getFACTORY().openSession();
        Transaction transaction=session.beginTransaction();
        session.save(data);
        transaction.commit();
        session.close();
        return 0;
    }
    public Account getAccountByUsername(String username){
        Session session=HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(Account.class);
        Root<Account> root=query.from(Account.class);
//        String s1=String.format("%%%s%%",username);
//        String s2=String.format("%%%s%%",password);
        Predicate p1=cb.equal(root.get("username").as(String.class),username);

        query.where(p1);
        Account account = null;
        try{
            account = (Account) session.createQuery(query).getSingleResult();

        }catch (NoResultException nre){
            //Ignore this because as per your logic this is ok!
        }
        return account;
    }
    public Account getAccount(String username,String password){
        Session session=HibernateUtils.getFACTORY().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery query=cb.createQuery(Account.class);
        Root<Account> root=query.from(Account.class);
//        String s1=String.format("%%%s%%",username);
//        String s2=String.format("%%%s%%",password);
        Predicate p1=cb.equal(root.get("username").as(String.class),username);
        Predicate p2=cb.equal(root.get("password").as(String.class),password);

        query.where(cb.and(p1,p2));
        Account account = null;
        try{
            account = (Account) session.createQuery(query).getSingleResult();

        }catch (NoResultException nre){
            //Ignore this because as per your logic this is ok!
        }
        return account;
    }
}
