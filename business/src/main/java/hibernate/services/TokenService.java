package hibernate.services;

import com.smirix.hibernate.HibernateExecutor;
import com.smirix.services.BusinessService;
import entities.Token;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

public class TokenService extends BusinessService {

    public Token getTokenByName(String tokenName) {
        return new HibernateExecutor<Token>().execute((session) ->
                {
                    try {
                        Query<Token> query = session.createNamedQuery("entities.Token.tokenName", Token.class);
                        query.setParameter("tokenName", tokenName);
                        return query.getSingleResult();
                    } catch (NoResultException e) {
                        return null;
                    }
                }
        );
    }

}
