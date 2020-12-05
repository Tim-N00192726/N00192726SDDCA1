package com.company;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    private List<Watch> watches;
    private WatchTableGateway gateway;

    private Model() {
        try {
            Connection conn = DBConnection.getInstance();
            this.gateway = new WatchTableGateway(conn);

            this.watches = gateway.getWatches();

        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "Problem Connecting to the Database, have you added your JDBC Driver .jar file?", ex);
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }






//        this.watches = new ArrayList<>();
//
//        this.watches.add(
//
//                new Watch("Watch series 6", "Apple", "Navy", "Magnetic Charging Cable", 18)
//        );
//
//        this.watches.add(
//                new Watch("Venu", "Garmin", "Black", "USB Cable", 5)
//        );

//        this.watches.add(
//                new Watch("Versa 2", "Fitbit", "Pink", "Charging Cable", 5)
//       );
//    }

    public List<Watch> getWatches() {
        return (gateway.getWatches());
    }

    public boolean addWatch (Watch w) {
        return (gateway.insertWatch(w));


    }

    public boolean deleteWatch(int id) {
            return(gateway.deleteWatch(id));
    }

    public boolean updateWatch (int id) {
        return (gateway.updateWatch(id));


    }

}
