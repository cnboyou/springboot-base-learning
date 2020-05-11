package com.ehi.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * ClassName: a
 *
 * @Author: WangYiHai
 * @Date: 2020/5/11 16:19
 * @Description: TODO
 */
public class XmlDemo {
    public static void main(String[] args) throws DocumentException {
        String kmlPath = "D:\\KML测试\\清远_本部_500_东花乙线.kml";
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.创建kml文件
        File file = new File(kmlPath);
        //3.加载xml
        Document document = reader.read(file);
        Element rootElement = document.getRootElement();
        System.out.println("根结点名：" + rootElement.getName() + "-->根结点值：" + rootElement.getStringValue());
        List<Element> childNodes = rootElement.elements();
        //遍历子节点
        childNodes.forEach( node -> {
            System.out.println("子结点名：" + node.getName() + "-->子结点值：" + node.getStringValue());
        });
    }
}
