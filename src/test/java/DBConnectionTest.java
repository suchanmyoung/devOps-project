import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.fail;

@Slf4j
public class DBConnectionTest {
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void db_text(){

        String uri = "jdbc:oracle:thin:@ec2-54-211-37-102.compute-1.amazonaws.com:1521/XEXDB";
        String user = "emoney";
        String pw = "1234";

        try(Connection conn = DriverManager.getConnection(uri, user, pw)){
            log.info(String.valueOf(conn));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}




