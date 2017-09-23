package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Publication;

import java.util.List;

/**
 * Created by palah on 05.09.2017.
 */
public interface PublicationDao {
    /**
     * Insert new publication into database
     *
     * @param publication instance of Publication entity
     */
    void create(Publication publication);

    /**
     * get Publication from database
     *
     * @param id Publication identifier
     * @return instance of Publication entity
     */
    Publication get(int id);

    /**
     * Updating current publication
     *
     * @param publication instance of Publication entity which will be update
     */
    void update(Publication publication);

    /**
     * Deleting current publication
     *
     * @param ID Publication identifier
     */
    void delete(int ID);

    /**
     * Get all publications from database
     *
     * @return List which contains all publications
     */
    List<Publication> getAll();

    /**
     * Get all user's publications from database
     *
     * @param ID user identifier
     * @return List which contains all user's publications
     */
    List<Publication> getAllByUserID(int ID);
}
