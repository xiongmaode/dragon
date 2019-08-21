package com.example.dragon.main.service;

import com.example.dragon.main.util.ExcelHelper;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * @ClassNAME AgentInfoService
 * @Description 微信人员配置表没有页面配置，需要按照excel模版导入
 * @Author Xiongmao
 * @Date 2019-06-17
 */
@Slf4j
public class AgentInfoService {

    public static void agentInfo() throws SQLException {
        //TODO 配置表格路径
        String path = "C:/Users/Administrator/Desktop/****.xlsx";
        //读excel到list，首行和末行数据作为标题和总计，不操作
        List<ArrayList<String[]>> lists = ExcelHelper.explanExcelToAllList(path);
        //TODO 存储产品主键和产品的产品险种代码
        HashMap<String, String> productMap = new HashMap<>();
        productMap.put("081910", "13");
        productMap.put("066801", "15");
        productMap.put("066701", "19");
        //存储产品手续费比例
        HashMap<String, String> agentrateMap = new HashMap<>();
        ArrayList<String[]> productInfos = lists.get(1);
        if (null == productInfos || productInfos.size() == 0) {
            log.info("哇，第二个sheet页没有内容！");
            return;
        }
        for (int y = 2; y < productInfos.size(); y++) {
            //产品代码
            String productCode = productInfos.get(y)[4];
            //手续费比例
            String agentrate = productInfos.get(y)[7];
            agentrate = agentrate.replace("%", "");
            //放入集合存储
            agentrateMap.put(productCode, agentrate);
        }
        //获取第一个sheet页的东西
        ArrayList<String[]> agentInfos = lists.get(0);
        if (null == agentInfos || agentInfos.size() == 0) {
            log.info("哇，第一个sheet页没有内容！");
            return;
        }
        //数据处理计数器
        int count = 0;

        //TODO 不同的数据库有不同的地址和账户
        String driverName = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@IP:1521/**";
        String user = "user";
        String password = "password";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            // 加载驱动
            Class.forName(driverName);
            // 设置 配置数据
            // 1.url(数据看服务器的ip地址 数据库服务端口号 数据库实例)
            // 2.user
            // 3.password
            conn = DriverManager.getConnection(url, user, password);
            //设置不自动提交事务
            conn.setAutoCommit(false);
            //设置事务的隔离级别。
//            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLEREAD);
            // 开始连接数据库
            log.info("哈哈...数据库连接成功了..");

            //业务逻辑开始
            for (int i = 7; i < agentInfos.size(); i++) {
                //归属机构代码
                String comcode = agentInfos.get(i)[2];
                if(null==comcode || "".equals(comcode)){
                    log.info("哇，遇到了空数据，需要检查一下了..");
                    return;
                }
                //代理人名称
                String agentname = agentInfos.get(i)[3];
                //中介代码
                String agentcode = agentInfos.get(i)[4];
                //协议号
                String agentcontract = agentInfos.get(i)[5];
                //下级销售人员名称
                String name = agentInfos.get(i)[6].trim();
                //下级销售人员代码
                String intermeagentcode = agentInfos.get(i)[7];
                //业务来源 0:直销业务，05:个人代理，04:专业代理，06：经纪业务，03：兼业代理
                String protocol = "03";
                //归属业务员名称
                String salesname = agentInfos.get(i)[9];
                //归属业务员代码
                String salescode = agentInfos.get(i)[10];
                //出单员名称
                String handlername = agentInfos.get(i)[11];
                if ("".equals(handlername) || null == handlername) {
                    handlername = salesname;
                }
                //出单员代码
                String handlercode = agentInfos.get(i)[12];
                //出单员归属机构代码
                String handlercomcode = comcode;
                //状态,01有效,00无效
                String status = "1";
                //出单渠道1.微门店,2微商城
                String agenttype = "1";
                for (Map.Entry<String, String> entry : productMap.entrySet()) {
                    //数据主键
                    String sid = UUID.randomUUID().toString().replace("-", "");
                    //险种代码
                    String riskcode = entry.getKey().substring(0, 4);
                    //产品主键
                    String productkey = entry.getValue();
                    //手续费比例
                    String agentrate = agentrateMap.get(entry.getKey()).trim();
                    if ("无".equals(agentrate) || null == agentrate || "".equals(agentrate)){
                        log.info("哇，【{}】这个产品手续费比例没有，省了一条...",entry.getKey());
                        continue;
                    }
                    //组装sql
                    StringBuffer sql = new StringBuffer("insert into prpd_agent_info(AGENTCODE," +
                            "PROTOCOL," +
                            "NAME," +
                            "COMCODE," +
                            "CATEGORY," +
                            "SUBCLASS," +
                            "STATUS," +
                            "RISKCODE," +
                            "AGENTNAME," +
                            "SALESCODE," +
                            "AGENTCONTRACT," +
                            "AGENTSUBCONTRACT," +
                            "AGENTRATE," +
                            "PRODUCTKEY," +
                            "AREACODE," +
                            "AGTSLSNAME," +
                            "AGTSLSPHONE," +
                            "USERCODE," +
                            "SID," +
                            "OPERATORCODE," +
                            "SALERFREERATE," +
                            "CAPTENFREERATE," +
                            "CHANNELFREERATE," +
                            "SEPCIALFREERATE," +
                            "AGENTTYPE," +
                            "AGREEPLANNO," +
                            "FEERATE," +
                            "HANDLERCODE," +
                            "HANDLERNAME," +
                            "HANDLERCOMCODE," +
                            "HANDLERCOMNAME," +
                            "COMMISSION," +
                            "INTERMEAGENTCODE," +
                            "VUSERFLAG) values (" );
                    sql.append("'"+agentcode);//AGENTCODE
                    sql.append("','"+protocol);//PROTOCOL
                    sql.append("','"+name);//NAME
                    sql.append("','"+comcode);//COMCODE
                    sql.append("',"+"''");//CATEGORY渠道大类
                    sql.append(","+"''");//SUBCLASS渠道子类
                    sql.append(",'"+status);//STATUS
                    sql.append("','"+riskcode);//RISKCODE
                    sql.append("','"+agentname);//AGENTNAME
                    sql.append("','"+ salescode);//SALESCODE
                    sql.append("','"+ agentcontract);//AGENTCONTRACT
                    sql.append("',"+ "''");//AGENTSUBCONTRACT代理子协议
                    sql.append(","+ agentrate);//AGENTRATE代理手续费0.00
                    sql.append(",'"+ productkey);//PRODUCTKEY
                    sql.append("',"+ "''");//AREACODE区域代码
                    sql.append(","+ "''");//AGTSLSNAME代理经办人姓名
                    sql.append(","+ "''");//AGTSLSPHONE代理经办人联系电话
                    sql.append(","+ "''");//USERCODE代理输入代码
                    sql.append(",'"+ sid);//SID
                    sql.append("',"+ "''");//OPERATORCODE代理机构出单员
                    sql.append(","+ "''");//SALERFREERATE代理业务员绩效
                    sql.append(","+ "''");//CAPTENFREERATE代理团队长绩效比例
                    sql.append(","+ "''");//CHANNELFREERATE代理渠道维护费用比例
                    sql.append(","+ "''");//SEPCIALFREERATE代理特批费用比例
                    sql.append(",'"+ agenttype);//AGENTTYPE
                    sql.append("',"+ "''");//AGREEPLANNO协议方案代码
                    sql.append(","+"''");//FEERATE
                    sql.append(",'"+ handlercode);//HANDLERCODE
                    sql.append("','"+ handlername);//HANDLERNAME
                    sql.append("','"+ handlercomcode);//HANDLERCOMCODE
                    sql.append("',"+ "''");//HANDLERCOMNAME出单员归属机构名称
                    sql.append(","+ null);//COMMISSION佣金比例
                    sql.append(",'"+ intermeagentcode);//INTERMEAGENTCODE
                    sql.append("',"+"''");//VUSERFLAG虚拟业务员
                    sql.append(")");
                    log.info("牛逼！sql语句为：{}",sql.toString());

                    //初始化sql
                    preparedStatement = conn
                            .prepareStatement(sql.toString());
                    //执行sql
                    preparedStatement.executeUpdate();
                    //计数器+1
                    count += 1;
//                    int len = preparedStatement.executeUpdate();
//                    log.info("更新行数:" + len);
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
                log.info("牛逼！业务员【{}】插入数据已经完成...", name);
                log.info("牛逼！目前已经插入数据【{}】条...",count);
                conn.commit();
            }
            //业务逻辑完成
            conn.close();
        } catch (Exception e) {
            // 若事务发生异常，回滚事务
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
