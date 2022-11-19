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
    private static Connection getConnection(){
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app",
                "app", "pass");
    }

    @SneakyThrows
    public static void cleanDatabase() {
        val runner = new QueryRunner();
        var connection = getConnection();
        runner.execute(connection, "DELETE FROM app.order_entity");
        runner.execute(connection, "DELETE FROM app.payment_entity");
        runner.execute(connection, "DELETE FROM app.credit_request_entity");
    }

    public static String getCardStatusForDebit() {
        String statusQuery = "SELECT * FROM app.payment_entity";
        val runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            val cardStatus = runner.query(connection, statusQuery,
                    new BeanHandler<>(PaymentEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static String getCardStatusForCredit() {
        String statusQuery = "SELECT * FROM app.credit_request_entity";
        val runner = new QueryRunner();
        try (Connection connection = getConnection()) {
            val cardStatus = runner.query(connection, statusQuery,
                    new BeanHandler<>(CreditRequestEntity.class));
            return cardStatus.getStatus();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}