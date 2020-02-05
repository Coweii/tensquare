package com.coweii.user.dao;

import com.coweii.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
	User findByMobile(String phoneNum);

	/**
	 * 更新用户粉丝数
	 * @param x  粉丝数变化
	 * @param id  用户id
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE tb_user SET fanscount=fanscount+? WHERE id=?", nativeQuery = true)
	int updateFanscount(int x, String id);

	/**
	 * 更新用户关注数
	 * @param x  关注数变化
	 * @param id  用户id
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE tb_user SET followcount=followcount+? WHERE id=?", nativeQuery = true)
	int updateFollowcount(int x, String id);
}
