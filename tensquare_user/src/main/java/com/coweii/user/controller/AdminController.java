package com.coweii.user.controller;
import java.util.HashMap;
import java.util.Map;

import com.coweii.user.pojo.Admin;
import com.coweii.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.coweii.user.service.AdminService;

import com.coweii.common.entity.PageResult;
import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private HttpServletRequest request;

	/**
	 * admin登录
	 * @param map
	 * @return
	 */
	@PostMapping("/login")
	public Result login(@RequestBody Map map){
		Admin admin = adminService.findByLoginNameAndPassword((String)map.get("loginname"), (String)map.get("password"));
		if(admin != null){
			String token = jwtUtil.creatJwt(admin.getId(),admin.getLoginname(),"admin");
			Map m = new HashMap();
			m.put("token",token);
			m.put("name",admin.getLoginname());
			return new Result(true,StatusCode.OK,"admin登录成功",m);
		} else {
			throw new RuntimeException("用户名或密码错误");
		}
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",adminService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",adminService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Admin>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",adminService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param admin
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Admin admin  ){
		adminService.add(admin);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param admin
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Admin admin, @PathVariable String id ){
		admin.setId(id);
		adminService.update(admin);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		Claims claims = (Claims) request.getAttribute("admin_claims");
		if(claims != null){
			adminService.deleteById(id);
			return new Result(true,StatusCode.OK,"管理员注销成功");
		} else {
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
	}
	
}
