package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Group;

import java.util.List;

/**
 * Created by palah on 05.09.2017.
 */
public interface GroupDao {
    /**
     * get Group by user ID
     *
     * @param groupID - group identifier
     * @return Group
     */
    Group get(int groupID);

    /**
     * Get all groups from database
     *
     * @return list which contains all groups
     */
    List<Group> getAll();
}
