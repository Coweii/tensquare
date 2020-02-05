package com.coweii.user.controller;
import com.coweii.common.entity.PageResult;
import com.coweii.common.entity.Result;
import com.coweii.common.entity.StatusCode;
import com.coweii.common.util.JwtUtil;
import com.coweii.user.pojo.User;
import com.coweii.user.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private HttpServletRequest request;
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		Claims claims = (Claims) request.getAttribute("user_claims");
		if(claims == null){
			return new Result(false,StatusCode.ACCESSERROR,"请先登录");
		}
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		System.out.println(request.getHeader("Authorization"));
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
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
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 用户注册
	 * @param user
	 */
	@PostMapping("/registry/{smscode}")
	public Result addUser(@RequestBody User user, @PathVariable String smscode){
		userService.add(user,smscode);
		return new Result(true,StatusCode.OK,"注册成功");
	}

	/**
	 * 用户登录
	 * @param map
	 * @return
	 */
	@GetMapping("/login")
	public Result login(@RequestBody Map<String,String> map){
		User user = userService.login(map.get("mobile"),map.get("password"));
		System.out.println("333333: 登录验证");
		if(user != null){
			String token = jwtUtil.creatJwt(user.getId(),user.getMobile(),"user");
			Map m = new HashMap();
			m.put("token",token);
			m.put("mobile",user.getMobile());
			return new Result(true,StatusCode.OK,"user登录成功",m);
		} else {
			throw new RuntimeException("用户名或密码错误");
		}
	}

	/**
	 * 获取验证码
	 * @param phoneNum 手机号码
	 * @return
	 */
	@GetMapping("/sendsms/{phoneNum}")
	public Result getSmsCode(@PathVariable String phoneNum){
		userService.sendPhoneNum(phoneNum);
		return new Result(true,StatusCode.OK,"验证码发送成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		Claims claims = (Claims) request.getAttribute("user_claims");
		if(claims != null){
			userService.deleteById(id);
			return new Result(true,StatusCode.OK,"用户注销成功");
		} else {
			return new Result(false,StatusCode.ACCESSERROR,"权限不足");
		}
	}

	/**
	 *
	 * @param id
	 * @param x  粉丝数变化
	 * @return
	 */
	@PutMapping("/fans/{id}/{x}")
	public Result updateFansCount(@PathVariable String id, @PathVariable int x){
		userService.updateFansCount(x,id);
		return new Result(true,StatusCode.OK,"粉丝数更新成功");
	}

	/**
	 *
	 * @param id
	 * @param x  关注数变化
	 * @return
	 */
	@PutMapping("/follow/{id}/{x}")
	public Result updateFollowCount(@PathVariable String id, @PathVariable int x){
		userService.updateFollowCount(x,id);
		return new Result(true,StatusCode.OK,"关注数更新成功");
	}
	
}
