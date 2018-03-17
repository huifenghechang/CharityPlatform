package com.seu.beyondtheboundary.charityplatform.service;


import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import com.seu.beyondtheboundary.charityplatform.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

	public List<Project> listProjects(Sort var1) {
		return projectRepository.findAll(var1);
	}



	public List<Project> listProjectsByStatusAndCategoryOrderAlreadyDonation(Long category, Long status, String title) {
		title = "%" + title + "%";
		List<Project> projects = projectRepository.findByTitleLikeAndCategoryAndStatusOrderByAlreadyDonationAsc( title, category , status);
		return projects;
	}
	public List<Project> listProjectsByStatusAndCategoryOrderId(Long category, Long status, String title) {
		title = "%" + title + "%";
		List<Project> projects = projectRepository.findByTitleLikeAndCategoryAndStatusOrderByIdDesc( title, category , status);
		return projects;
	}
//	public List<Project> listProjectsByStatusAndCategory(Long category, Long status) {
//
//		return projectRepository.findByCategoryAndStatusByTitleLike(category , status);
//	}
	public List<Project> listProjectsByTitleLikeOrderByAlreadyDonationAsc(String title) {
		// 模糊查询
		title = "%" + title + "%";
		List<Project> projects = projectRepository.findByTitleLikeOrderByAlreadyDonationAsc(title);
		return projects;
	}
	public List<Project> listProjectsByTitleLikeOrderByIdDesc(String title) {
		// 模糊查询
		title = "%" + title + "%";
		List<Project> projects = projectRepository.findByTitleLikeOrderByIdDesc(title);
		return projects;
	}
}

