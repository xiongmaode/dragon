package com.example.dragon.main.service;

import com.example.dragon.main.util.ExcelHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassNAME ReadExcleToBeen
 * @Description 把word中的接口信息换成javabean打印出来，需要先粘贴到excle中
 * @Author XiongMao
 * @Date 2019-8-2
 */
public class ReadExcleToBeen {
    public static void getBeen() {
        //TODO 配置表格路径
        String path = "C:/Users/Administrator/Desktop/linshi.xlsx";
        //读excel到list，首行和末行数据作为标题和总计，不操作
        List<ArrayList<String[]>> lists = ExcelHelper.explanExcelToAllList(path);
        ArrayList<String[]> list1 = lists.get(0);
        for (String[] s : list1) {
            //字段为空跳过
            if ("".equals(s[1]) || null == s[1])
                continue;
//            if (s[3].contains("N") || s[3].contains("n") || "".equals(s[3]) || null == s[3])
//                continue;
            System.out.println("//" + s[4]);
            System.out.println("@XmlElement(name = \"" + s[1].toUpperCase() + "\")");
            System.out.println("private String " + s[1].toLowerCase() + ";");

        }
    }
}
