package com.coweii.user.dao;

import com.coweii.user.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, String> {
    @Query(value = "select r.id, r.name, r.namezh from tb_user as u, tb_role as r, user_role as ur where u.id=ur.uid and r.id=ur.rid and u.id = ?", nativeQuery = true)
    List<Role> findRolesById(String id);
}
