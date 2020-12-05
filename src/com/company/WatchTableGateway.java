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

    public boolean insertWatch(Watch w) {

        String query;
        PreparedStatement stmt;
        int numRowsAffected;

        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + ", " +
                COLUMN_MAKE + ", " +
                COLUMN_COLOUR + ", " +

                COLUMN_CHARGINGTYPE + ", " +
                COLUMN_BATTERYLIFE +
                ") VALUES (?, ?, ?, ?, ?)";

        try {
            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, w.getName());
            stmt.setString(2, w.getMake());
            stmt.setString(3, w.getColour());

            stmt.setString(4, w.getChargingType());
            stmt.setDouble(5, w.getBatteryLife());

            numRowsAffected = stmt.executeUpdate();

            if (numRowsAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WatchTableGateway : insertProgrammer(), Check the SQL you have created to see where your error is", e);
        }
        return false;
    }


    public List<Watch> getWatches() {
        String query;

        List<Watch> watches = new ArrayList<>();

        String name, make, colour, chargingType;
        int id;
        double batteryLife;

        Watch w;
        query = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement stmt;
            ResultSet rs;

            stmt = this.mConnection.createStatement();
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

        return watches;

    }

    public boolean deleteWatch(int id)    {

        int numRowsAffected;



        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= ?";


        try {
            PreparedStatement stmt;


            stmt = mConnection.prepareStatement(query);
            stmt.setInt(1, id);

            System.out.println("\n\nTHE SQL LOOKS LIKE THIS " + stmt.toString() + "\n\n");

            numRowsAffected = stmt.executeUpdate();
            if (numRowsAffected == 1) {
                return true;
            }
        }
        catch (SQLException e)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WatchTableGateway : deleteWatch(), Check the SQL you have created to see where your error is", e);
        }


        return false;


    }

    public boolean updateWatch(int id) {
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = ?, " + COLUMN_MAKE + " = ?, " + COLUMN_COLOUR + " = ?, " + COLUMN_CHARGINGTYPE + " = ?, " + COLUMN_BATTERYLIFE + " = ?";
        query += " WHERE " + COLUMN_ID + " = ?";

        try {
            PreparedStatement stmt;
            int rowUpdated;


            stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "Versa 2");
            stmt.setString(2, "Fitbit");
            stmt.setString(3, "Pink");
            stmt.setString(4, "Charging Cable");
            stmt.setDouble(5, 6.0);
            stmt.setInt(6, id);

            rowUpdated = stmt.executeUpdate();

            if (rowUpdated == 1) {

                return true;
            }
        }  catch (SQLException e) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in WatchTableGateway : insertProgrammer(), Check the SQL you have created to see where your error is", e);
        }
        return false;

    }

}
