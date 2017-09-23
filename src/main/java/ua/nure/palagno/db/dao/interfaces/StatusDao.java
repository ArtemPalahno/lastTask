package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Status;

/**
 * Created by palah on 05.09.2017.
 */
public interface StatusDao {
    /**
     * get Status from database
     *
     * @param key Role identifier
     * @return instance of Status entity
     */
    Status get(int key);

    /**
     * get status ID from database by statusName
     *
     * @param statusName Status name in db
     * @return status ID
     */
    int get(String statusName);

}
