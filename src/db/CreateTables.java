package db;

public class CreateTables {

    protected static final String CREATE_CLIENT_TABLE = "CREATE TABLE clients (client_id int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            "first_name VARCHAR(50) NOT NULL," +
            "last_name VARCHAR(50) NOT NULL," +
            "phone varchar(20)," +
            "birth_date DATE NOT NULL," +
            "origin_state VARCHAR(5) NOT NULL," +
            "comments varchar(50) NOT NULL)";

}

