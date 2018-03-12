package com.seu.beyondtheboundary.charityplatform.service;


import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Project 服务.
 *

 */
@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Transactional
	@Override
	public Project saveProject(Project project) {
			return projectRepository.save(project);
		}


	@Transactional
	@Override
	public void removeProject(Long id) {
		projectRepository.delete(id);
	}




	@Transactional
	@Override
	public void removeProjectsInBatch(List<Project> projects) {
		projectRepository.deleteInBatch(projects);
	}

	@Transactional
	@Override
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project getProjectById(Long id) {
		return projectRepository.getOne(id);
	}

	@Override
	public List<Project> listProjects() {
		return projectRepository.findAll();
	}
}

