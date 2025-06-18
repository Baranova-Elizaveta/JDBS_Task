package org.example.task1.reader;


import org.example.task1.model.XmlEmployee;
import org.example.task1.model.XmlEmployees;
import org.example.task1.model.XmlProject;
import org.example.task1.model.XmlProjects;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class XmlReaderService {

    private static final Logger logger = LoggerFactory.getLogger(XmlReaderService.class);

    private final JAXBContext employeesContext;
    private final JAXBContext projectsContext;

    public XmlReaderService() throws JAXBException {
        this.employeesContext = JAXBContext.newInstance(XmlEmployees.class);
        this.projectsContext = JAXBContext.newInstance(XmlProjects.class);
    }


    public Optional<List<XmlEmployee>> readEmployees(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.warn("Файл сотрудников не найден: {}", filePath);
                return Optional.empty();
            }
            if (!file.canRead()) {
                logger.error("Нет прав на чтение файла: {}", filePath);
                return Optional.empty();
            }

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("employees.xsd"));

            Unmarshaller unmarshaller = employeesContext.createUnmarshaller();
            unmarshaller.setSchema(schema);

            XmlEmployees xmlEmployees = (XmlEmployees) unmarshaller.unmarshal(file);
            List<XmlEmployee> employees = xmlEmployees.getEmployees();
            logger.info("Успешно прочитано {} сотрудников из файла: {}",
                    employees != null ? employees.size() : 0, filePath);
            return Optional.ofNullable(employees);

        } catch (SAXException e) {
            logger.error("Ошибка валидации XSD для файла сотрудников: {}", filePath, e);
            return Optional.empty();
        } catch (JAXBException e) {
            logger.error("Ошибка JAXB при чтении файла сотрудников: {}", filePath, e);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Общая ошибка при чтении файла сотрудников: {}", filePath, e);
            return Optional.empty();
        }
    }


    public Optional<List<XmlProject>> readProjects(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                logger.warn("Файл проектов не найден: {}", filePath);
                return Optional.empty();
            }

            if (!file.canRead()) {
                logger.error("Нет прав на чтение файла: {}", filePath);
                return Optional.empty();
            }

            Unmarshaller unmarshaller = projectsContext.createUnmarshaller();
            XmlProjects xmlProjects = (XmlProjects) unmarshaller.unmarshal(file);

            List<XmlProject> projects = xmlProjects.getProjects();
            logger.info("Успешно прочитано {} проектов из файла: {}",
                    projects != null ? projects.size() : 0, filePath);

            return Optional.ofNullable(projects);

        } catch (JAXBException e) {
            logger.error("Ошибка JAXB при чтении файла проектов: {}", filePath, e);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Общая ошибка при чтении файла проектов: {}", filePath, e);
            return Optional.empty();
        }
    }

    public boolean isFileExists(String filePath) {
        return new File(filePath).exists();
    }

    public Optional<Long> getFileLastModified(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return Optional.of(file.lastModified());
        }
        logger.debug("Файл не существует для получения времени модификации: {}", filePath);
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
        try {
            File file = new File(filePath);
            if (!file.exists() || !file.canRead()) {
                logger.debug("Файл сотрудников не существует или недоступен: {}", filePath);
                return false;
            }

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("employees.xsd"));
            Unmarshaller unmarshaller = employeesContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            unmarshaller.unmarshal(file); // Только для валидации
            return true;
        } catch (SAXException e) {
            logger.debug("Файл сотрудников не валиден по XSD: {}", filePath, e);
            return false;
        } catch (Exception e) {
            logger.debug("Файл сотрудников не валиден: {}", filePath, e);
            return false;
        }
    }

    public boolean isValidProjectsXml(String filePath) {
        try {
            return readProjects(filePath).isPresent();
        } catch (Exception e) {
            logger.debug("Файл проектов не валиден: {}", filePath, e);
            return false;
        }
    }
}

