package org.example.task1.model;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public XmlEmployee() {}

    public XmlEmployee(Integer id, String fullName, String email, LocalDate startDateOfWork) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.startDateOfWork = startDateOfWork;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDateOfWork() {
        return startDateOfWork;
    }
    public void setStartDateOfWork(LocalDate startDateOfWork) {
        this.startDateOfWork = startDateOfWork;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastSyncAt() {
        return lastSyncAt;
    }
    public void setLastSyncAt(LocalDateTime lastSyncAt) {
        this.lastSyncAt = lastSyncAt;
    }

    public List<XmlEmployeeProject> getProjects() { return projects; }
    public void setProjects(List<XmlEmployeeProject> projects) { this.projects = projects; }
}