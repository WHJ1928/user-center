package com.whj.usercenter.util;


import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/8/15
 * @desc xml操作
 */
@Component
public class MakeTemp {

    private static final String CLASSPATH_XML_TEMPXML_XML = "xml/tempxml.xml";
    private static final String CLASSPATH_XML_CELLXML_XML = "xml/cellxml.xml";
    static byte[] byteArray = null;
    
    public static ByteArrayInputStream getInputStream() throws Exception {
        if(byteArray==null) {
            prepareModule();
        }
        return new ByteArrayInputStream(byteArray);
    }
    /**
     * 解析XML文件并返回输入流
     */
    private static ByteArrayInputStream prepareModule() throws Exception{
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
        //颜色
        cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        //边框
        cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        cellStyle.setWrapText(true);
        sheet.setColumnWidth(0, 10 * 256);
        /*不是根节点时，节点地址前需要加 //*/
        List<Element> list = document.selectNodes("//sheets/sheet/row/cell");
        for (int i =0;i<list.size();i++) {
            Element listElement = list.get(i);
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(listElement.getText());
        }
        List<String[]> downData = new ArrayList<>();
        analysisXML(downData);
        String [] downRows = analysis();
        for(int r=0;r<downRows.length;r++){
            //获取下拉对象
            String[] dlData = downData.get(r);
            int rownum = Integer.parseInt(downRows[r]);
            sheet.addValidationData(setDataValidation(sheet, dlData, 1, 50000, rownum ,rownum));
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        wb.write(output);
        byteArray = output.toByteArray();
        ByteArrayInputStream input = new ByteArrayInputStream(byteArray);
        return input;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框
     * @param sheet
     * @param textList 下拉框显示的内容
     * @param firstRow
     * @param endRow
     * @param firstCol
     * @param endCol
     * @return
     */
    private static XSSFDataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {

        // 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                .createExplicitListConstraint(textList);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        //设置数据有效性校验的错误提示
        validation.setShowErrorBox(true);
        validation.createErrorBox("错误提示", "您输入的内容，不符合限制条件");
        return validation;
    }

    private static List<String[]> analysisXML(List<String[]> options) throws Exception{
        ClassPathResource cpr = new ClassPathResource(CLASSPATH_XML_CELLXML_XML);
        SAXReader reader =  new SAXReader();
        Document document = reader.read(cpr.getInputStream());
        List<Element> list = document.selectNodes("//data");
        for (Element em: list){
            List<Element> elements = em.elements("option");
            String[] strings = new String[elements.size()];
            for (int i = 0;i<elements.size();i++){
                strings[i] = elements.get(i).getStringValue();
            }
            options.add(strings);
        }
        return options;
    }
    private static String[] analysis() throws Exception{
        ClassPathResource cpr = new ClassPathResource(CLASSPATH_XML_CELLXML_XML);
        SAXReader reader =  new SAXReader();
        Document document = reader.read(cpr.getInputStream());
        List<Element> list = document.selectNodes("//rowsum/option");
        String[] strings = new String[list.size()];
        for (int i = 0;i<list.size();i++){
            strings[i] = list.get(i).getStringValue();
        }
        return strings;
    }

}
