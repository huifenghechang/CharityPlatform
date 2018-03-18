package com.seu.beyondtheboundary.charityplatform.repository;


import com.seu.beyondtheboundary.charityplatform.domain.Project;
import com.seu.beyondtheboundary.charityplatform.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {

	/**
	 * 根据用户名分页查询用户列表
	 * @param name
	 * @param pageable
	 * @return
	 */
	public List<Project> findAllByCategoryAndStatus(Long category , Long status);

	//public List<Project> findByCategoryAndStatusByTitleLike(Long category , Long statu);

	public List<Project> findByTitleLikeAndCategoryAndStatusOrderByAlreadyDonationAsc(String title,Long category , Long statu );
	public List<Project> findByTitleLikeAndCategoryAndStatusOrderByIdDesc(String title,Long category , Long statu);
	List<Project> findByTitleLikeOrderByIdDesc( String title);
	List<Project> findByTitleLikeOrderByAlreadyDonationAsc( String title);
}
