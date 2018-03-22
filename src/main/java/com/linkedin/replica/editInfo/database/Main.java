package com.linkedin.replica.editInfo.database;

import com.linkedin.replica.editInfo.database.DatabaseSeed;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException, ClassNotFoundException {

        new DatabaseSeed();
        DatabaseSeed.insertUsers();
    }
}

