package com.whj.usercenter.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/8/14
 * @desc excel相关操作
 */
@Component
public class ExcelUtil {

    @Autowired
    private ExcelUtil excelUtil;
    @Autowired
    private XmlUtil xmlUtil;

    @Value("orgId")
    private String orgId;
    @Value("orgUser")
    private String orgUser;

    private final static String excel2003 =".xls";
    private final static String excel2007 =".xlsx";
//    private static Pattern PHONE = Pattern.compile("^\\d{11}$");

    /**
     * @param in
     * @param fileName
     * 处理上传的excel文件
     *
     * */
//    public  List<WhitelistInfo> getListByExcel(InputStream in, String fileName) throws Exception{
//        List<WhitelistInfo> list = new ArrayList<>();
//        //创建Excel工作薄
//        Workbook work = this.getWorkbook(in,fileName);
//        if(null == work){
//            throw new Exception("创建Excel工作薄为空！");
//        }
//        Sheet sheet = null;
//        Row row = null;
//        Cell cell = null;
//        for (int i = 0; i < work.getNumberOfSheets(); i++) {
//            sheet = work.getSheetAt(i);
//            if(sheet==null){continue;}
//
//            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
//                row = sheet.getRow(j);
//                if(row==null||row.getFirstCellNum()==j){
//                    continue;
//                }
//                WhitelistInfo listInfo = rowToPo(row);
//                list.add(listInfo);
//            }
//        }
//        work.close();
//        return list;
//    }

    /**
     * 判断上传文件的版本
     * @param inStr
     * @param fileName
     * @return wb
     * */
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003.equals(fileType)){
            wb = new HSSFWorkbook(inStr);
        }else if(excel2007.equals(fileType)){
            wb = new XSSFWorkbook(inStr);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

//    /**
//     * 获取导入excel的标题
//     * @return
//     * @throws Exception
//     */
//    public List<String> getTitle() throws Exception{
//        List<String> list = new ArrayList<>();
//        String fileName="白名单库导入模板.xlsx";
//        ByteArrayInputStream inputStream = MakeTemp.getInputStream();
//        Workbook workbook = getWorkbook(new BufferedInputStream(inputStream), fileName);
//        Row row = workbook.getSheetAt(0).getRow(0);
//        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
//            Cell cell = row.getCell(i);
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//            list.add(cell.toString().trim());
//        }
//        return list;
//    }
//
//    /**
//     * 将excel行数据转换为po
//     * @param row
//     * @return
//     * @throws Exception
//     */
//    public WhitelistInfo rowToPo(Row row) throws Exception{
//        WhitelistInfo whitelistInfo = new WhitelistInfo();
//        whitelistInfo.setCustomerId("WC"+idGenerator.generateId());
//        whitelistInfo.setInputOrgId(orgId);
//        whitelistInfo.setInputUserId(orgUser);
//        whitelistInfo.setInputDate(DateUtil.getCurrentTimeStr(DateUtil.DATETIME_FORMAT_T));
//        List<String> strings = excelUtil.getTitle();
//        String content;
//        Cell cell;
//        for (int i = row.getFirstCellNum(); i < strings.size(); i++) {
//            String title = strings.get(i);
//            if(title==null) {
//                break;
//            }
//            cell = row.getCell(i);
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//            content = cell.toString().trim();
//            String property = xmlUtil.getFieldAttr(title);
//            String methodName = "set" + property;
//            Method method = WhitelistInfo.class.getMethod(methodName, String.class);
//            method.invoke(whitelistInfo, content);
//        }
//        return whitelistInfo;
//    }

}
