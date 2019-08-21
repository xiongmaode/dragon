package com.example.dragon.main.service;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassNAME UserCodeService
 * @Description 配合运维修改数据库用户编码（sql表模式）
 * @Author Xiongmao
 * @Date 2019-06-17
 */
@Slf4j
public class UserCodeService {
    public static void userCoding() {
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
            String sql = "select * from t_emp_hz";//hz xin didaqudao 查询证件号与客户编码
            Statement stmt = conn.createStatement();    //创建一个statement对象
            ResultSet rs = stmt.executeQuery(sql);
            String id, username;
            Map<String, String> map = new HashMap<>();
            while (rs.next()) {        //遍历结果集
                id = rs.getString("c_ply_no");
                username = rs.getString("n_prm");//n_prm  //c_no
                map.put(id, username);
                log.info(id + "\t" + username + "\t");
            }

            sql = "select * from t_emp_DONG";//qiao wei xue 查询批单号
            stmt = conn.createStatement();    //创建一个statement对象
            rs = stmt.executeQuery(sql);
            String i;
            List<String> list = new ArrayList<>();
            while (rs.next()) {        //遍历结果集
                i = rs.getString("c_ply_no");//c_ply_no  t_ply_base c_edr_no
                list.add(i);
                log.info(i);
            }
            if (rs != null) {
                stmt.close();
            }
            int t = 0, y = 0, z = 0;
            for (int p = 0; p < list.size(); p++) {
                log.info(list.get(p));
                sql = "select c_biz_cert_no from t_edr_insrntparlist where c_edr_no ='" + list.get(p) + "'"; //根据批单号查询所有的证件号
                stmt = conn.createStatement();
                log.info(sql);//创建一个statement对象
                rs = stmt.executeQuery(sql);
                String m = "";
                List<String> list1 = new ArrayList<>();
                while (rs.next()) {        //遍历结果集
                    m = rs.getString("c_biz_cert_no");
                    list1.add(m);
                    log.info(m);
                }
                if (rs != null) {
                    stmt.close();
                }
                for (int r = 0; r < list1.size(); r++) {
                    String c_no = map.get(list1.get(r));
                    if (c_no != null && c_no != "") {
                        sql = "update t_edr_insrntparlist set c_main_cde='" + c_no + "' where c_main_cde is null and c_edr_no='" + list.get(p) + "' and c_biz_cert_no='" + list1.get(r) + "'";
                        log.info(sql);
                        try {
                            stmt = conn.createStatement();
                            y = stmt.executeUpdate(sql);
                            log.info("resutl: " + y);
                            if (y != 0) {
                                z += 1;
                            }
                            log.info("suess：" + z);
                            t += 1;
                            log.info("num: " + t);
                            stmt.close();
                            if (t % 1000 == 0) {
                                log.info("commit");
                                conn.commit();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
            conn.commit();
            log.info("commit");
            conn.close();
            log.info("close");
        } catch (ClassNotFoundException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
