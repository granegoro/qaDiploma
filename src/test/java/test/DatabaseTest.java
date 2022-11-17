package test;

import data.SQLHelper;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.sql.DriverManager.getConnection;

public class DatabaseTest {

    @Test
    @SneakyThrows
    void stubTest() {
        var query = "SELECT * FROM app.payment_entity";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {

            var first = runner.query(conn, query, new BeanHandler<>(SQLHelper.PaymentEntity.class));
            System.out.println(first);

        }
    }
}
