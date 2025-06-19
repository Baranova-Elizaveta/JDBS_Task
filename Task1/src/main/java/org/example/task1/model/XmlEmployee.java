package org.example.task1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEmployee {

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "fullName")
    private String fullName;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "startDateOfWork")
    private LocalDate startDateOfWork;

    @XmlElement(name = "createdAt")
    private LocalDateTime createdAt;

    @XmlElement(name = "updatedAt")
    private LocalDateTime updatedAt;

    @XmlElement(name = "lastSyncAt")
    private LocalDateTime lastSyncAt;

    @XmlElementWrapper(name = "projects")
    @XmlElement(name = "employeeProject")
    private List<XmlEmployeeProject> projects;

    public XmlEmployee(Integer id, String fullName, String email, LocalDate startDateOfWork) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.startDateOfWork = startDateOfWork;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}