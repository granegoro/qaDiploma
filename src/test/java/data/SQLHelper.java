package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper(){
    }

    @SneakyThrows
    private static Connection getConn(){
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    private static Connection getPostgresConn(){
        return DriverManager.getConnection("jdbc:postgres://localhost:5432/app", "app", "pass");
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM app.order_entity");
        runner.execute(connection, "DELETE FROM app.payment_entity");
        runner.execute(connection, "DELETE FROM app.credit_request_entity");
    }

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
