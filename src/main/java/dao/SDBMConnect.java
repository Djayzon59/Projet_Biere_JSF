package dao;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;

public class SDBMConnect {

    private static Connection connexion;

    private SDBMConnect(){}

    public static Connection getInstance() {
        if (connexion == null) {
            try {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setServerName("127.0.0.1");
                ds.setPortNumber(1433);
                ds.setDatabaseName("SDBM");
                ds.setIntegratedSecurity(false);
                ds.setEncrypt(false);
                ds.setUser("*****");
                ds.setPassword("**********");
                connexion = ds.getConnection();
            } catch (SQLServerException e) {
                e.printStackTrace();
            }

        }
        return connexion;
    }

}
