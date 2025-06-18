package org.example.task1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProjects {

    @XmlElement(name = "project")
    private List<XmlProject> projects;

    public XmlProjects() {}

    public XmlProjects(List<XmlProject> projects) {
        this.projects = projects;
    }

    public List<XmlProject> getProjects() { return projects; }
    public void setProjects(List<XmlProject> projects) { this.projects = projects; }
}

