package data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection conn() {
        return getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = conn();
        runner.execute(connection, "DELETE FROM app.order_entity");
        runner.execute(connection, "DELETE FROM app.payment_entity");
        runner.execute(connection, "DELETE FROM app.credit_request_entity");
    }

    @SneakyThrows
    public static String getCardStatus() {
        String query = "SELECT * FROM app.payment_entity";
        val runner = new QueryRunner();
        try (Connection connection = conn()) {
            val cardStatus = runner.query(connection, query,
                    new BeanHandler<>(PaymentEntity.class));
            return cardStatus.getStatus();
        }
}


    //

    @Data
    @NoArgsConstructor

    public class CreditRequestEntity {
        String id;
        String bank_id;
        String created;
        String status;
    }

    @Data
    @NoArgsConstructor

    public class OrderEntity {
        String id;
        String created;
        String credit_id;
        String payment_id;
    }

    @Data
    @NoArgsConstructor

    public class PaymentEntity {
        String id;
        String amount;
        String created;
        String status;
        String transaction_id;
    }

    //


    //Добавить выдачу списка всех операций

//    @Test
//    @SneakyThrows
//    void stubTest() {
//        var countSQL = "SELECT COUNT(*) FROM users;";
//        var usersSQL = "SELECT * FROM users;";
//        var runner = new QueryRunner();
//
//        try (
//                var conn = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/app", "app", "pass"
//                );
//        ) {
//            var count = runner.query(conn, countSQL, new ScalarHandler<>());
//            System.out.println(count);
//            var first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
//            System.out.println(first);
//            var all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
//            System.out.println(all);
//        }
//    }
//}

}
