package com.seu.beyondtheboundary.charityplatform.repository;


import com.seu.beyondtheboundary.charityplatform.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2017年3月2日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 根据用户名分页查询用户列表
	 * @param realname
	 * @param pageable
	 * @return
	 */
	Page<User> findByRealnameLike(String realname, Pageable pageable);
	public User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);

	public User findById(Long id);

	public List<User> findAllByVerified(Integer verified);
}
