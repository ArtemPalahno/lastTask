package ua.nure.palagno.db.dao.interfaces;

import ua.nure.palagno.db.entity.Role;

/**
 * Created by palah on 05.09.2017.
 */
public interface RoleDao {
    /**
     * get Role from database
     * @param key Role identifier
     * @return instance of Role entity
     */
     Role get(int key);
}
