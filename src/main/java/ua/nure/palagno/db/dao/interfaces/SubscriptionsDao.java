package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Publication;
import ua.nure.palagno.db.entity.Subscription;

import java.util.List;

/**
 * Created by palah on 05.09.2017.
 */
public interface SubscriptionsDao {
    public List<Publication> getAllPublicationsByUserID(int UserId);
}
