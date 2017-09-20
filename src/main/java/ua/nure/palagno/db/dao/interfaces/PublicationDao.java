package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Publication;

import java.util.List;

/**
 * Created by palah on 05.09.2017.
 */
public interface PublicationDao {
    public void create(Publication publication) ;
    public Publication get(int key) ;
    public void update(Publication publication);
    public void delete(int ID);
    public List<Publication> getAll();

}
