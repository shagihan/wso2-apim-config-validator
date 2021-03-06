package org.wso2.confvalidator.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Created by nipun on Dec, 2017
 */
public class DOMBuilder {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Document document;

    /**
     * Returns all configuration files for a given node
     * @param profile
     * @return
     */
    public Map<String, Document> loadFiles(String profile) {
        Map<String, Document> configs = new HashMap();

        for (int i = 0; i < Constants.CONF_PATH_ARRAY.length; i++) {
            String filePath = Constants.CONF_ROOT + Constants.NODE_PATH_MAP.get(profile) + Constants.CONF_PATH_ARRAY[i];
            File configFile = new File(filePath);
            try {
                this.documentBuilder = this.documentBuilderFactory.newDocumentBuilder();
                document = documentBuilder.parse(configFile);
                document.getDocumentElement().normalize();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            configs.put(Constants.CONF_NAME_ARRAY[i], document);
        }
        return configs;
    }
}
