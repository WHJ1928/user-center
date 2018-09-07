package com.whj.usercenter.util;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author wanghaijun
 * @date 2018/8/15
 * @desc xml操作
 */
@Component
public class XmlUtil {
    private static final Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    private static final String CLASSPATH_XML_CELLXML_XML = "xml/cellxml.xml";
    private static final String CLASSPATH_XML_TEMPXML_XML = "xml/tempxml.xml";

    /**
     * 修改xml文件
     * @param str
     * @throws DocumentException
     * @throws IOException
     */
    public void updateXml(String str){
        try {
            ClassPathResource cpr = new ClassPathResource(CLASSPATH_XML_CELLXML_XML);
            //创建Document对象，读取已存在的Xml文件
            Document document=new SAXReader().read(cpr.getInputStream());
            Element root = document.getRootElement();
//            List<Element> dataList = root.elements("data");
            List<Element> list = document.selectNodes("//data");
            Element element = list.get(0);
            List<Element> optionList = element.elements("option");
            Element option = DocumentHelper.createElement("option");
//            Element option = element.addElement("option");
            option.setText(str);
            optionList.add(option);
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 利用格式化类对编码进行设置
            format.setNewLineAfterDeclaration(false);
            format.setEncoding("UTF-8");
            format.setExpandEmptyElements(true);
            // 输出

//            FileOutputStream outputStream = new FileOutputStream("src//main//resources//xml//cellxml.xml");
            FileOutputStream outputStream = new FileOutputStream(cpr.getFile().getPath());
            XMLWriter writer = new XMLWriter(outputStream, format);
            writer.write(document);
            LogOut.info(logger, "", "新增xml记录" , "",  "", "成功");
            writer.flush();
            writer.close();
        } catch (Exception e){
            LogOut.info(logger, "", "新增xml记录" , "",  "", "失败");
            e.printStackTrace();
        }
    }

    /**
     * 根据标题转换
     * @param str
     * @return
     * @throws Exception
     */
    public String getFieldAttr(String str) throws Exception{
        String propertyName = null;
        ClassPathResource cpr = new ClassPathResource(CLASSPATH_XML_TEMPXML_XML);
        //创建Document对象，读取已存在的Xml文件person.xml
        Document document=new SAXReader().read(cpr.getInputStream());
        List<Element> list = document.selectNodes("//row/cell");
        for (Element element:list){
            String name = element.getStringValue().trim();
            if (name.equals(str)){
                Attribute sheetAttr = element.attribute("propertyName");
                propertyName = sheetAttr.getValue();
            }else {
                continue;
            }
        }
        return propertyName;
    }
}
