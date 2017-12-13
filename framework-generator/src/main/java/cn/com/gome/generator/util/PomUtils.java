package cn.com.gome.generator.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * <Description> </Description>
 * <ClassName> PomUtils</ClassName>
 *
 * @Author liuxianzhao
 * @Date 2017年12月01日 18:51
 */
public class PomUtils {
    private static final Logger logger = LoggerFactory.getLogger(PomUtils.class);

    public static void editPomXml(String projectPath, String projectEng, String CHILD_PROJECT_ENG, String packages) throws IOException {
        projectPath += "//pom.xml";
        File file = new File(projectPath);
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(file);
            Element elementR = doc.getRootElement();
            Element element = elementR.element("modules");
            boolean t = true;
            if (element != null && element.elements().size() > 0) {
                List list1 = element.elements();
                Iterator iterator = list1.iterator();
                while (iterator.hasNext()) {
                    Element elem = (Element) iterator.next();
                    if ((projectEng + "-" + CHILD_PROJECT_ENG).equals(elem.getText())) {
                        t = false;
                    }
                }
            }
            if (t) {
                Element date = element.addElement("module");
                date.addText(projectEng + "-" + CHILD_PROJECT_ENG);
                FileWriter newFile = new FileWriter(new File(projectPath));
                XMLWriter newWriter = new XMLWriter(newFile);
                newWriter.write(doc);
                newWriter.close();
            }
            doc = reader.read(file);
            elementR = doc.getRootElement();
            element = elementR.element("dependencyManagement");
            element = element.element("dependencies");
            t = true;
            if (element != null && element.elements().size() > 0) {
                List list1 = element.elements();
                Iterator iterator = list1.iterator();
                while (iterator.hasNext()) {
                    Element elem = (Element) iterator.next();
                    List<Element> elements = elem.elements();
                    boolean flag = false;
                    for (int i = 0; i < elements.size(); i++) {
                        Element tmp = elements.get(i);
                        if (tmp.getName().equals("artifactId")) {
                            if (tmp.getText().equals(projectEng + "-" + CHILD_PROJECT_ENG)) {
                                flag = true;
                            }
                            break;
                        }
                    }
                    if (flag) {
                        t = false;
                        break;
                    }
                }
            }
            if (t) {
                Element dependency = element.addElement("dependency");
                dependency.addElement("groupId").addText(packages);
                dependency.addElement("artifactId").addText(projectEng + "-" + CHILD_PROJECT_ENG);
                dependency.addElement("version").addText("${project.version}");
                FileWriter newFile = new FileWriter(new File(projectPath));
                XMLWriter newWriter = new XMLWriter(newFile);
                newWriter.write(doc);
                newWriter.close();
            }
        } catch (DocumentException e) {
            logger.error("修改主pom文件异常：", e);
        }
    }
}
