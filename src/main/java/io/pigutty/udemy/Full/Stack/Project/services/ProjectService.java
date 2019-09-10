package io.pigutty.udemy.Full.Stack.Project.services;

import io.pigutty.udemy.Full.Stack.Project.domain.Project;
import io.pigutty.udemy.Full.Stack.Project.exceptions.ProjectIdException;
import io.pigutty.udemy.Full.Stack.Project.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID"+project.getProjectIdentifier().toUpperCase()+"already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist.");
        }
        return project;
    }
}
