package com.whj.usercenter.util;

import com.whj.usercenter.dao.Weibo;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/9/11
 * @desc
 */
public class DownloadExcel {
    private static final String CLASSPATH_XML_TEMPXML_XML = "xml/tempxml.xml";
    static byte[] byteArray = null;

    /**
     * 解析XML文件并返回输入流
     */
    public static ByteArrayInputStream prepareModule(List<Weibo> weiboList) throws Exception{
        ClassPathResource cpr = new ClassPathResource(CLASSPATH_XML_TEMPXML_XML);
        SAXReader reader =  new SAXReader();
        Document document = reader.read(cpr.getInputStream());
        Element rootElement = document.getRootElement();
        Element sheetsElement = rootElement.element("sheets");
        Element sheetElement = sheetsElement.element("sheet");
        Attribute sheetAttr = sheetElement.attribute("name");
        String name = sheetAttr.getValue();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(name);
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle cellStyle = wb.createCellStyle();
        //水平对齐方式
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        //不是根节点时，节点地址前需要加
        List<Element> list = document.selectNodes("//sheets/sheet/row/cell");
        for (int i =0;i<list.size();i++) {
            Element listElement = list.get(i);
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(listElement.getText());
        }
        XSSFRow xssfRow = null;
        XSSFCell cell = null;
        for (int j =0;j<weiboList.size();j++){
            //设置自适应列宽
            sheet.autoSizeColumn(j);
            sheet.setColumnWidth(j,sheet.getColumnWidth(j)*17/10);
            xssfRow = sheet.createRow(j+1);
            Weibo weibo =weiboList.get(j);
            cell = xssfRow.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getUserid());
            cell = xssfRow.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getUsername());
            cell = xssfRow.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getFollownum());
            //1.将粉丝数转换成以万为单位的数字
            double d = (double) weibo.getFansnum();
            double num = d / 10000;
            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
            cell = xssfRow.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(f1);
            cell = xssfRow.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getBlognum());
            cell = xssfRow.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getOriginalblognum());
            cell = xssfRow.createCell(6);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getHomelink());
            cell = xssfRow.createCell(7);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getRegistrationtime());
            cell = xssfRow.createCell(8);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getBlogauthenticate());
            cell = xssfRow.createCell(9);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getIndustry());
            cell = xssfRow.createCell(10);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(weibo.getBci());
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wb.write(output);
        byteArray = output.toByteArray();
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
        return input;
    }
}
