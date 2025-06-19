package org.example.task1.reader;

import org.example.task1.model.XmlEmployee;
import org.example.task1.model.XmlEmployees;
import org.example.task1.model.XmlProject;
import org.example.task1.model.XmlProjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;
import java.util.Optional;

@Component
public class XmlReader {

    private static final Logger logger = LoggerFactory.getLogger(XmlReader.class);

    public <T> Optional<T> read(String filePath, Class<T> clazz, String schemaPath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.warn("XML file not found: {}", filePath);
                return Optional.empty();
            }

            if (!file.canRead()) {
                logger.error("No rights to read the XML file: {}", filePath);
                return Optional.empty();
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            if (schemaPath != null && !schemaPath.trim().isEmpty()) {
                File schemaFile = new File(schemaPath);
                if (schemaFile.exists()) {
                    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                    Schema schema = schemaFactory.newSchema(schemaFile);
                    unmarshaller.setSchema(schema);
                    logger.debug("The XSD scheme has been applied for validation: {}", schemaPath);
                } else {
                    logger.warn("XSD schema not found: {}, validation skipped", schemaPath);
                }
            }


            @SuppressWarnings("unchecked")
            T result = (T) unmarshaller.unmarshal(file);

            logger.info("XML file read successfully: {} as {}", filePath, clazz.getSimpleName());
            return Optional.of(result);

        } catch (SAXException e) {
            logger.error("XSD validation error for the file: {}", filePath, e);
            return Optional.empty();
        } catch (JAXBException e) {
            logger.error("JAXB error when reading an XML file: {}", filePath, e);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("A common error when reading an XML file: {}", filePath, e);
            return Optional.empty();
        }
    }

    public <T> Optional<T> read(String filePath, Class<T> clazz) {
        return read(filePath, clazz, null);
    }

    public Optional<List<XmlEmployee>> readEmployees(String filePath) {
        Optional<XmlEmployees> xmlEmployees = read(filePath, XmlEmployees.class, "employees.xsd");
        return xmlEmployees.map(XmlEmployees::getEmployees);
    }

    public Optional<List<XmlProject>> readProjects(String filePath) {
        Optional<XmlProjects> xmlProjects = read(filePath, XmlProjects.class);
        return xmlProjects.map(XmlProjects::getProjects);
    }


    public boolean isFileExists(String filePath) {
        return new File(filePath).exists();
    }

    public Optional<Long> getFileLastModified(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return Optional.of(file.lastModified());
        }
        logger.debug("The file does not exist to get the modification time.: {}", filePath);
        return Optional.empty();
    }


    public Optional<Long> getFileSize(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return Optional.of(file.length());
        }
        return Optional.empty();
    }

    public boolean isFileReadable(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.canRead();
    }


    public boolean isValidEmployeesXml(String filePath) {
        return read(filePath, XmlEmployees.class, "employees.xsd").isPresent();
    }

    public boolean isValidProjectsXml(String filePath) {
        return read(filePath, XmlProjects.class).isPresent();
    }
}