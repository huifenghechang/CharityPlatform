package com.seu.beyondtheboundary.charityplatform.service;

import com.seu.beyondtheboundary.charityplatform.domain.Project;

import java.util.List;

public  interface ProjectService {
    /**
     * 保存项目
     * @param project
     * @return
     */
    Project saveProject(Project project);

    /**
     * 删除项目
     * @param
     * @return
     */
    void removeProject(Long id);

    /**
     * 删除列表里面的项目
     * @param project
     * @return
     */
    void removeProjectsInBatch(List<Project> project);

    /**
     * 更新项目
     * @param project
     * @return
     */
    Project updateProject(Project project);

    /**
     * 根据id获取项目
     * @param
     * @return
     */
    Project getProjectById(Long id);

    /**
     * 获取项目列表
     * @param
     * @return
     */
    List<Project> listProjects();

//    /**
//     * 根据用户名进行分页模糊查询
//     * @param user
//     * @return
//     */
//    Page<User> listUsersByNameLike(String name, Pageable pageable);
}