package com.ehi.xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/11 16:17
 * @Description: TODO
 */
public class TestKml {
    public static void main(String[] args) throws IOException {
        //创建KML文档对象
        Document document = DocumentHelper.createDocument();
        //添加一个根节点
        Element root = document.addElement("kml");
        //给根节点kml添加属性
        root.addAttribute("xmlns", "http://earth.google.com/kml/2.2");
        //给根节点kml添加子节点  Document
        Element documentElement = root.addElement("Document");

        //给节点添加注释
        documentElement.addComment("配置部分");
        Element extendedData1 = documentElement.addElement("ExtendedData");
        //添加命名空间
        extendedData1.addNamespace("mis","自定义");
        Element data1 = extendedData1.addElement("Data");
        data1.addAttribute("name","Doc");
        extendedData1.addElement("mis:version").addText("1.0");

        Element extendedData2 = documentElement.addElement("ExtendedData");
        extendedData2.addNamespace("mis","自定义");
        Element data2 = extendedData2.addElement("Data");
        data2.addAttribute("name","Mission");
        extendedData2.addComment("mission config");
        extendedData2.addElement("mis:version").addText("1.0");
        extendedData2.addElement("mis:type").addText("Waypoint");

        Element extendedData3 = documentElement.addElement("ExtendedData");
        extendedData3.addNamespace("mis","自定义");
        Element data3 = extendedData3.addElement("Data");
        data3.addAttribute("name","Point");
        extendedData3.addComment("point list");
        for (int i = 0;i < 5;i++) {
            Element point = extendedData3.addElement("mis:Point");
            point.addComment("point config");
        }

        Element extendedData4 = documentElement.addElement("ExtendedData");
        extendedData4.addNamespace("mis","自定义");
        Element data4 = extendedData4.addElement("Data");
        data4.addAttribute("name","Action");
        extendedData4.addComment("action list");
        for (int i = 0;i < 5;i++) {
            Element action = extendedData3.addElement("mis:Action");
            action.addComment("point config");
        }

        extendedData4.addComment("实景地图显示部分（包含航点坐标信息、格式为KML标准格式）");
        Element folder = documentElement.addElement("Folder");
        folder.addElement("name").addText("Mission");
        Element childFolder = folder.addElement("Folder");
        childFolder.addComment("points list");
        Element placeMarker = childFolder.addElement("PlaceMarker");
        placeMarker.addElement("Point").addComment("point position");
        Element placeMarker1 = childFolder.addElement("PlaceMarker");
        placeMarker1.addElement("Point").addComment("point position");
        Element placeMarker3 = folder.addElement("PlaceMarker").addComment("WayLine ");
        Element lineString = placeMarker3.addElement("LineString");
        lineString.addElement("coordinates").addComment("line points");

        //将kml写出本地
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("test.kml"),format);
        xmlWriter.write(document);
        xmlWriter.close();
    }
}
