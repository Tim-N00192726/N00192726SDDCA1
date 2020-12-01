package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WatchTableGateway {
    private static final String TABLE_NAME = "watch";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_COLOUR = "colour";
    private static final String COLUMN_CHARGINGTYPE = "chargingtype";
    private static final String COLUMN_BATTERYLIFE = "batteryLife";

    private Connection mConnection;

    public WatchTableGateway(Connection connection) {
        mConnection = connection;
    }

    // Called from the Model when the user wants to create a new programmer in the database, the new ID is created in the database and returned here
    public boolean insertWatch(Watch w) {

        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // I am going to create a new Programmer object, this object will have the Id after the row is inserted into the database
        //   Programmer dbProgrammer = null;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_MAKE + ", " +
                COLUMN_COLOUR + ", " +

                COLUMN_CHARGINGTYPE + ", " +
                COLUMN_BATTERYLIFE +
                ") VALUES (?, ?, ?, ?, ?)";

        try {
            // create a PreparedStatement object to execute the query and insert the values into the query
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, w.getName());
            stmt.setString(2, w.getMake());
            stmt.setString(3, w.getColour());

            stmt.setString(4, w.getChargingType());
            stmt.setDouble(5, w.getBatteryLife());

            //  execute the query and make sure that one and only one row was inserted into the database
            numRowsAffected = stmt.executeUpdate();

            // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
            // so assign this new ID to the ID.
            if (numRowsAffected == 1) {
//                    // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
//                    ResultSet keys = stmt.getGeneratedKeys();
//                    keys.next();
//
//                    id = keys.getInt(1);
//                    //dbProgrammer.setId(id);
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WatchTableGateway : insertProgrammer(), Check the SQL you have created to see where your error is", e);
        }

        // return the Programmer object created with the new ID, The calling program in the Model should check to ensure it is not null
        return false;
    }


    // Called from the Model to get the complete list of programmers from the programmer table in the database
    public List<Watch> getWatches() {
        String query;

        List<Watch> watches = new ArrayList<>();

        String name, make, colour, chargingType;
        int id;
        double batteryLife;

        Watch w;                   // a Programmer object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
            ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query

            stmt = this.mConnection.createStatement();
            // rs is a ResultSet object. It contains the rows of data from the database.
            rs = stmt.executeQuery(query);


            while (rs.next()) {
                id = rs.getInt(COLUMN_ID);
                name = rs.getString(COLUMN_NAME);
                make = rs.getString(COLUMN_MAKE);
                colour = rs.getString(COLUMN_COLOUR);

                chargingType = rs.getString(COLUMN_CHARGINGTYPE);
                batteryLife = rs.getDouble(COLUMN_BATTERYLIFE);

                w = new Watch(id, name, make, colour, chargingType, batteryLife);
                watches.add(w);
            }
        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WatchTableGateway : getWatches(), Check the SQL you have created to see where your error is", e);
        }

        // return the arraylist of Programmer objects to the model.
        return watches;

    }
}
