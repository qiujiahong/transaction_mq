# jdbc事务



## 代码如上

```java
package com.nick.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LocalJDBCTransApplication {
    private static final Logger log = LoggerFactory.getLogger(LocalJDBCTransApplication.class);
    public static void main(String[] args) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            String sql1 = "UPDATE t_user SET amount = amount-100 where username=?";
            PreparedStatement ps1 = connection.prepareStatement(sql1);

            String sql2 = "UPDATE t_user SET amount = amount+100 where username =?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            ps1.setString(1,"nick");
            ps1.executeUpdate();

            ps2.setString(1,"elaine");
            ps2.executeUpdate();

            throwException();   //这里抛出异常，事务回滚
            connection.commit();

            ps1.close();
            ps2.close();
            connection.close();

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
    }
    private static void throwException() throws Exception {
        throw new Exception("test exception");
    }

    private static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test111";
        String userName="root";
        String password= "123456";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            return null;
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
```


* 设置事务，需要设置链接属性不自动提交``connection.setAutoCommit(false);``
* 提交事务，使用：``connection.commit();``
* 查询，如果需要锁定，则修改sql语句加上``FOR UPDATE``,但是千万留意，for update最好加上条件，避免查询锁全表，如:``select *  from t_user where id=1 FOR UPDATE`` 