package com.coweii.user.dao;

import com.coweii.user.pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission,String> {
    @Query(value = "select p.id, p.path from tb_role as r, tb_permission as p, role_permission as rp where rp.rid=r.id and rp.pid=p.id and r.id=?", nativeQuery = true)
    List<Permission> findByRoleId(String roleId);
}
