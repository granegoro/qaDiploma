package data;

import dbEntity.PaymentEntity;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class SQLHelper {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app",
                    "app", "pass");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    public static void dropDataBase() {
        val runner = new QueryRunner();
        val order = "DELETE FROM app.order_entity";
        val payment = "DELETE FROM app.payment_entity";
        val creditRequest = "DELETE FROM app.credit_request_entity";

        try (val connection = getConnection()) {
            runner.update(connection, order);
            runner.update(connection, payment);
            runner.update(connection, creditRequest);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static String getCardStatusForPayment() {
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
}

//
//
//
////        @SneakyThrows
////    public static void cleanDatabase() {
////        var connection = conn();
////        runner.execute(connection, "DELETE FROM app.order_entity");
////        runner.execute(connection, "DELETE FROM app.payment_entity");
////        runner.execute(connection, "DELETE FROM app.credit_request_entity");
////    }
//
//    @SneakyThrows
//    public static String verifyCardStatus() {
//        String qStat = "SELECT status FROM app.payment_entity";
//        val runner = new QueryRunner();
//        try (
//                var conn = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/app", "app", "pass"
//                );
//        ) {
//            val cardStatus = runner.query(conn, qStat,
//                    new BeanHandler<>(PaymentEntity.payment.class));
//            return cardStatus.getStatus();
//        }
//}
//
//
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//
//    public class CreditRequestEntity {
//        String id;
//        String bank_id;
//        String created;
//        String status;
//    }
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//
//    public class OrderEntity {
//        String id;
//        String created;
//        String credit_id;
//        String payment_id;
//    }
//
////    @Data
////    @NoArgsConstructor
////    @AllArgsConstructor
////
////    public class PaymentEntity {
////        String id;
////        String amount;
////        String created;
////        String status;
////        String transaction_id;
////    }
//
//
//    //
//
//
//
//    //
//
//
//    //Добавить выдачу списка всех операций
//
////    @Test
////    @SneakyThrows
////    void stubTest() {
////        var countSQL = "SELECT COUNT(*) FROM users;";
////        var usersSQL = "SELECT * FROM users;";
////        var runner = new QueryRunner();
////
////        try (
////                var conn = DriverManager.getConnection(
////                        "jdbc:mysql://localhost:3306/app", "app", "pass"
////                );
////        ) {
////            var count = runner.query(conn, countSQL, new ScalarHandler<>());
////            System.out.println(count);
////            var first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
////            System.out.println(first);
////            var all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
////            System.out.println(all);
////        }
////    }
////}
//
//}
