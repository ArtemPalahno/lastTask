package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Publication;
import ua.nure.palagno.db.entity.Subscription;

import java.util.List;

/**
 * Created by palah on 05.09.2017.
 */
public interface SubscriptionsDao {
    /**
     * Get all user subscriptions
     * @param UserId User identifier
     * @return User subscription list
     */
    public List<Publication> getAllPublicationsByUserID(int UserId);

    /**
     * Check is user has current publication(pubID)
     * @param userID User identifier
     * @param pubID current publication
     * @return true - if user has publication , false - opposite
     */
    public boolean IsUserHasPublication(int userID, int pubID) ;

    /**
     * Add user's subscription to publication
     * @param userID User identifier
     * @param bookID Publication identifier
     */
    public void Add(int userID , int bookID);
}
