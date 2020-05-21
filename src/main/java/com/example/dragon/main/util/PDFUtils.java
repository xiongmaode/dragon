package com.example.dragon.main.util;

import com.lowagie.text.pdf.BaseFont;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassNAME PDFUtil
 * @Description 生成pdf和压缩文件下载
 * @Author XiongMao
 * @Date 2020-1-16
 */
@Slf4j
@Service
public class PDFUtils {

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 生成pdf，下载
     *
     * @param request
     * @param response
     */
    @SneakyThrows
    public void getPdf(HttpServletRequest request, HttpServletResponse response) {
        Context ctx = new Context();
        //TODO 获取pdf填充数据
        //*.fillContent(key, ctx);ctx.setVariable("person", person);
        String content = templateEngine.process("home", ctx);

        ITextRenderer renderer = new ITextRenderer();
        // 设置字体，否则不支持中文,在html中使用字体，html{ font-family: SimSun;}
        // BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        renderer.getFontResolver().addFont("static/font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(content, "file://classpath:static/");
        renderer.layout();
        renderer.createPDF(response.getOutputStream());
        renderer.finishPDF();
    }

    /**
     * 生成pdf，下载zip
     *
     * @param request
     * @param response
     */
    @SneakyThrows
    public void getZip(HttpServletRequest request, HttpServletResponse response) {
        // response.addHeader("Content-Disposition", "attchement");
        //基础设置
        Map<String, Integer> nameCount = new HashMap<>();
        //TODO 修改zip名称
        String zipName = "ZipName.zip";
        response.setHeader("Content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(zipName, "UTF-8"));
        ZipOutputStream zip = new ZipOutputStream(response.getOutputStream());
        //TODO 拿到需要循环生成的数据
        List<Map<String, String>> reportInfo = new ArrayList<>();

        //循环生成PDF
        for (Map<String, String> map : reportInfo) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Context ctx = new Context();
            //TODO 获取pdf填充数据
            //*.fillContent(key, ctx);ctx.setVariable("person", person);

            //把数据转成XHTML文件
            String content = templateEngine.process("home", ctx);

            ITextRenderer renderer = new ITextRenderer();
            // 设置字体，否则不支持中文,在html中使用字体，html{ font-family: SimSun;}
            // BaseFont bfChinese = BaseFont.createFont( "STSongStd-Light" ,"UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            renderer.getFontResolver().addFont("static/font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            renderer.setDocumentFromString(content, "file://classpath:static/");
            renderer.layout();
            renderer.createPDF(out);
            renderer.finishPDF();

            //TODO 生成报告的pdf名称
            String pdfName = "-";
            //判断名字是否重复，如果重复则重命名
            Integer con = nameCount.get(pdfName);
            if (null != con) {
                nameCount.put(pdfName, con + 1);
                pdfName = pdfName.replace(".pdf", con + 1 + ".pdf");
            } else {
                nameCount.put(pdfName, 1);
            }
            ZipEntry entry = new ZipEntry(pdfName);
            zip.putNextEntry(entry);
            out.writeTo(zip);
            zip.closeEntry();
            out.close();
        }
        zip.close();
    }
}
