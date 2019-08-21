package com.example.dragon.main.service;

import com.example.dragon.main.util.ExcelHelper;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNAME InsertBOLB
 * @Description 数据库bolb类型直接复制粘贴有问题，写个工具类插入（excel版本）
 * @Author Xiongmao
 * @Date 2019-06-17
 */
@Slf4j
public class InsertBOLBService {

    public static void insertBOLB() {
        String path = "E:/***/***.xls";
        //读excel到list，首行和末行数据作为标题和总计，不操作
        List<ArrayList<String[]>> lists = ExcelHelper.explanExcelToAllList(path);
        // 不同的数据库有不同的驱动
        String driverName = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@IP:1521/**";
        String user = "user";
        String password = "password";
        Connection conn = null;
        try {
            // 加载驱动
            Class.forName(driverName);
            // 设置 配置数据
            // 1.url(数据看服务器的ip地址 数据库服务端口号 数据库实例)
            // 2.user
            // 3.password
            conn = DriverManager.getConnection(url, user, password);
            // 开始连接数据库
            log.info("数据库连接成功..");
            for (ArrayList<String[]> l : lists) {
                for (String[] s : l) {
                    String sql = "UPDATE prpd_product_text SET text=? WHERE sid=?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, s[1]);
                    ps.setString(2, s[0]);
                    String q = "UPDATE prpd_product_text SET text=" + s[1] + " WHERE sid=" + s[0];
                    System.out.println(q);
                    ps.executeUpdate();
                }
            }
            conn.close();
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
