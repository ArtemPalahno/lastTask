package ua.nure.palagno.db;

/**
 * Created by Artem_Palagno on 07.09.2017.
 */
public class Fields {
    /**
     * ID column's  name in db.
     */
    public static final String ENTITY__ID = "ID";
    /**
     * Email column's  name in users table.
     */
    public static final String USER__EMAIL = "Email";
    /**
     * Password column's  name in users table.
     */
    public static final String USER__PASSWORD = "Password";
    /**
     * Name column's  name in users table.
     */
    public static final String USER__FIRST_NAME = "Name";
    /**
     * Surname column's  name in users table.
     */
    public static final String USER__LAST_NAME = "Surname";
    /**
     * Role column's  name in users table
     */
    public static final String USER__ROLE_ID = "Role";
    /**
     * Status column's  name in users table
     */
    public static final String USER__STATUS_ID = "Status";
    /**
     * Money column's  name in users table
     */
    public static final String USER__MONEY = "Money";
    /**
     * Session column's  name in users table.
     */
    public static final String USER__SESSION = "Session";

    /**
     * Group column's  name in groups table.
     */
    public static final String GROUP__NAME = "GroupName";

    /**
     * Name column's  name in publications  table.
     */
    public static final String PUBLICATION__NAME = "Name";
    /**
     * Author column's  name in publications table.
     */
    public static final String PUBLICATION__AUTHOR = "Author";
    /**
     * Price column's  name in publications table.
     */
    public static final String PUBLICATION__PRICE = "Price";
    /**
     * Group column's  name in publications table.
     */
    public static final String PUBLICATION__GROUP_ID = "GroupID";

    /**
     * Name column's name in roles table
     */
    public static final String ROLE__NAME = "RoleName";
    /**
     * Status column's name in status table
     */
    public static final String STATUS__NAME = "StatusName";
    /**
     * UserId column's name in subscription table.
     */
    public static final String SUBSCRIPTION__USER_ID = "userID";
    /**
     * PublicationID column's  name in subscription table.
     */
    public static final String SUBSCRIPTION__BOOK_ID = "bookID";






}
