package org.example.task1.model;
import org.example.task1.enumes.Domain;

import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProject {

    @XmlElement(name = "id")
    private Integer id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "domain")
    private Domain domain;

    @XmlElement(name = "createdAt")
    private LocalDateTime createdAt;

    @XmlElement(name = "updatedAt")
    private LocalDateTime updatedAt;

    @XmlElement(name = "lastSyncAt")
    private LocalDateTime lastSyncAt;

    public XmlProject() {}

    public XmlProject(Integer id, String name, String description, Domain domain) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.domain = domain != null ? domain : Domain.TECHNOLOGY;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id; }
    public void setId(Integer id) {
        this.id = id; }

    public String getName() {
        return name; }
    public void setName(String name) {
        this.name = name; }

    public String getDescription() {
        return description; }
    public void setDescription(String description) {
        this.description = description; }

    public Domain getDomain() {
        return domain; }
    public void setDomain(Domain domain) {
        this.domain = domain; }

    public LocalDateTime getCreatedAt() {
        return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() {
        return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt; }

    public LocalDateTime getLastSyncAt() {
        return lastSyncAt; }
    public void setLastSyncAt(LocalDateTime lastSyncAt) {
        this.lastSyncAt = lastSyncAt; }
}
