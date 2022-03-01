package ru.itis;

import org.htmlcleaner.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

public class Main{
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL("https://www.lamoda.ru/c/371/clothes-trikotazh/?zbs_content=cat_ip_icons_827380_all_0610_cat_icons_w_clothes_c1");
            //Pattern reg = Pattern.compile(".+x-product-card__hit-area.+?href=\"([^\"]*)\"(?:\\n|.)+?(?:price-single.+?>(.+?)<|price-old.+?>(.+?)<.+(?:\\n|.)+?price-new.+?>(.+?)<)(?:\\n|.)+product-name(?:\\n|.)+href.+?>(.+)<");
        }catch (MalformedURLException e){
            System.out.println("Connection exception");
        }

        Document document = parseHtml(url);
        NodeList list = parseDocument("//div[@class='x-product-card-description']", document);

        NodeList li = parseItem(list.item(0));

        StringWriter buf = new StringWriter();
        try {
            Transformer xform = TransformerFactory.newInstance().newTransformer();
            xform.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            xform.setOutputProperty(OutputKeys.INDENT, "yes");
            xform.transform(new DOMSource(list.item(0)), new StreamResult(buf));
        } catch (TransformerException e) {
            System.out.println(e);
        }
        System.out.println(buf.toString());

        for (int j = 0; j < li.getLength(); j++) {
            System.out.println(li.item(j).getTextContent());
        }
    }

    private static Document parseHtml(URL url){
        TagNode tagNode;
        try {
            tagNode = new HtmlCleaner().clean(url);
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }

        Document document;
        try {
            document = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            return document;
        }catch (ParserConfigurationException e){
            throw new IllegalArgumentException("ParserException");
        }
    }

    private static NodeList parseDocument(String expression, Document document){
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList list;

        try {
            list = (NodeList) xPath.evaluate(expression, document, XPathConstants.NODESET);
            return list;
        }catch (XPathExpressionException e){
            throw new IllegalArgumentException("XPathException");
        }
    }

    private static NodeList parseItem(Node str){
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//div[@class='x-product-card-description__product-name']/a/@href | " +
                "//span[contains(@class, 'x-product-card-description__price-single')]/text() | " +
                "//span[@class='x-product-card-description__price-old']/text() | " +
                "//span[contains(@class, 'x-product-card-description__price-new')]/text() | " +
                "//div[@class='x-product-card-description__brand-name']/a/text() | " +
                "//div[@class='x-product-card-description__product-name']/a/text()";

        try {
            NodeList list = (NodeList) xPath.evaluate(expression, str, XPathConstants.NODESET);
            return list;
        }catch (XPathExpressionException e){
            throw new IllegalArgumentException("XPathException");
        }
    }
}
