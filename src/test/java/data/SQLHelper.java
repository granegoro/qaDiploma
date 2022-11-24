package data;

import dbEntity.CreditRequestEntity;
import dbEntity.PaymentEntity;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private SQLHelper(){
    }

    @SneakyThrows
    public static Connection getConnection(){
        var url = System.getProperty("db.url");
        var login = System.getProperty("db.login");
        var password = System.getProperty("db.password");

        return DriverManager.getConnection(url, login, password);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var runner = new QueryRunner();
        var connection = getConnection();
        runner.execute(connection, "DELETE FROM app.order_entity");
        runner.execute(connection, "DELETE FROM app.payment_entity");
        runner.execute(connection, "DELETE FROM app.credit_request_entity");
    }

    public static String getCardStatusForDebit() {
        var statusQuery = "SELECT * FROM app.payment_entity";
        var runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            var cardStatus = runner.query(connection, statusQuery,
                    new BeanHandler<>(PaymentEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getCardStatusForCredit() {
        var statusQuery = "SELECT * FROM app.credit_request_entity";
        var runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            var cardStatus = runner.query(connection, statusQuery,
                    new BeanHandler<>(CreditRequestEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}