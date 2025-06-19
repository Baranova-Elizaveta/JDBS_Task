package org.example.task1.reader;

import org.example.task1.model.XmlEmployee;
import org.example.task1.model.XmlEmployees;
import org.example.task1.model.XmlProject;
import org.example.task1.model.XmlProjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class XmlService {

    private static final Logger logger = LoggerFactory.getLogger(XmlService.class);

    private final XmlReader xmlReader;

    @Autowired
    public XmlService(XmlReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    public List<XmlEmployee> readEmployees(String filePath) {
        Optional<XmlEmployees> xmlEmployees = xmlReader.read(filePath, XmlEmployees.class, "employees.xsd");

        if (xmlEmployees.isPresent()) {
            List<XmlEmployee> employees = xmlEmployees.get().getEmployees();
            if (employees == null || employees.isEmpty()) {
                logger.warn("The employee file is empty: {}", filePath);
                throw new XmlReadException("The employee file does not contain any data: " + filePath);
            }
            logger.info("Successfully read {} employees from the file: {}", employees.size(), filePath);
            return employees;
        }

        if (!xmlReader.isFileExists(filePath)) {
            throw new XmlFileNotFoundException("The employee file was not found: " + filePath);
        }

        if (!xmlReader.isFileReadable(filePath)) {
            throw new XmlReadException("No rights to read the employee file: " + filePath);
        }

        throw new XmlValidationException("The employee file was not validated according to the XSD scheme: " + filePath);
    }

    public List<XmlProject> readProjects(String filePath) {
        Optional<XmlProjects> xmlProjects = xmlReader.read(filePath, XmlProjects.class);

        if (xmlProjects.isPresent()) {
            List<XmlProject> projects = xmlProjects.get().getProjects();
            if (projects == null || projects.isEmpty()) {
                logger.warn("The project file is empty: {}", filePath);
                throw new XmlReadException("The project file does not contain any data: " + filePath);
            }
            logger.info("Successfully read {} projects from the file: {}", projects.size(), filePath);
            return projects;
        }

        if (!xmlReader.isFileExists(filePath)) {
            throw new XmlFileNotFoundException("The project file was not found: " + filePath);
        }

        if (!xmlReader.isFileReadable(filePath)) {
            throw new XmlReadException("No rights to read the project file: " + filePath);
        }
        throw new XmlReadException("Couldn't parse the projects file: " + filePath);
    }

    public boolean isValidEmployeesXml(String filePath) {
        if (!xmlReader.isFileExists(filePath)) {
            throw new XmlFileNotFoundException("The employee file was not found for validation.: " + filePath);
        }

        if (!xmlReader.isFileReadable(filePath)) {
            throw new XmlReadException("No rights to read the employee file for validation: " + filePath);
        }

        boolean isValid = xmlReader.read(filePath, XmlEmployees.class, "employees.xsd").isPresent();
        if (!isValid) {
            throw new XmlValidationException("The employee file was not validated according to the XSD scheme: " + filePath);
        }

        return true;
    }

    public boolean isValidProjectsXml(String filePath) {
        if (!xmlReader.isFileExists(filePath)) {
            throw new XmlFileNotFoundException("The project file was not found for validation.: " + filePath);
        }

        if (!xmlReader.isFileReadable(filePath)) {
            throw new XmlReadException("I don't have permission to read the project file for validation: " + filePath);
        }

        boolean isValid = xmlReader.read(filePath, XmlProjects.class).isPresent();
        if (!isValid) {
            throw new XmlReadException("The project file could not be parsed: " + filePath);
        }

        return true;
    }

    public static class XmlException extends RuntimeException {
        public XmlException(String message) {
            super(message);
        }

        public XmlException(String message, Throwable cause) {
            super(message, cause);
        }
    }


    public static class XmlFileNotFoundException extends XmlException {
        public XmlFileNotFoundException(String message) {
            super(message);
        }
    }

    public static class XmlReadException extends XmlException {
        public XmlReadException(String message) {
            super(message);
        }

        public XmlReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class XmlValidationException extends XmlException {
        public XmlValidationException(String message) {
            super(message);
        }

        public XmlValidationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}