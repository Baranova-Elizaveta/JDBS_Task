package org.example.task1.model;




import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEmployeeProject {

    @XmlElement(name = "projectId")
    private Integer projectId;

    @XmlElement(name = "startDateOfWork")
    private LocalDate startDateOfWork;

    @XmlElement(name = "endDateOfWork")
    private LocalDate endDateOfWork;

    @XmlElement(name = "createdAt")
    private LocalDateTime createdAt;

    @XmlElement(name = "updatedAt")
    private LocalDateTime updatedAt;

    @XmlElement(name = "lastSyncAt")
    private LocalDateTime lastSyncAt;

    public XmlEmployeeProject() {}

    public XmlEmployeeProject(Integer projectId, LocalDate startDateOfWork, LocalDate endDateOfWork) {
        this.projectId = projectId;
        this.startDateOfWork = startDateOfWork;
        this.endDateOfWork = endDateOfWork;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public LocalDate getStartDateOfWork() {
        return startDateOfWork;
    }
    public void setStartDateOfWork(LocalDate startDateOfWork) {
        this.startDateOfWork = startDateOfWork;
    }

    public LocalDate getEndDateOfWork() {
        return endDateOfWork;
    }
    public void setEndDateOfWork(LocalDate endDateOfWork) {
        this.endDateOfWork = endDateOfWork;
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
}
