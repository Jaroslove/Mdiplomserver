
public enum Queries {
    INSERT_USER(0
            , "INSERT INTO mydbtest.user ("
            + "name ,"
            + "longitude ,"
            + "latitude ,"
            + "date )"
            + "VALUES(?,?,?,?)"
    ),
    ALL_USERS_ONE_HOUR(1
            , "SELECT * FROM mydbtest.user WHERE date" +
            " >= DATE_SUB(NOW(), INTERVAL 1 hour);"
    ),
    ALL_EVENTS(2
            , "SELECT * FROM mydbtest.event"
    ),
    UPDATE_USER(3
            , "UPDATE mydbtest.user SET longitude = ?, latitude=? WHERE user.name = ?"
    ),
    DELETE_USER(4
            , "DELETE FROM mydbtest.user WHERE user.name = ?"
    ),
    INSERT_EVENT(5
            , "INSERT INTO mydbtest.event ("
            + "name ,"
            + "date ,"
            + "idUser )"
            + "VALUES(?,?,?)"
    ),
    UPDATE_EVENT(6
            , "UPDATE mydbtest.event SET name = ? WHERE event.name = ?"
    ),
    DELETE_EVENT(7
            , "DELETE FROM mydbtest.event WHERE event.name = ?"
    );

    private int index;
    private String value;


    Queries(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
