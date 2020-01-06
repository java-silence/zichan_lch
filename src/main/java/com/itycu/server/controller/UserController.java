package com.itycu.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itycu.server.dao.*;
import com.itycu.server.dto.LoginUser;
import com.itycu.server.dto.SysUserDto;
import com.itycu.server.model.Dept;
import com.itycu.server.model.Warehouse;
import com.itycu.server.utils.UserUtil;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.annotation.LogAnnotation;
import com.itycu.server.dto.UserDto;
import com.itycu.server.model.SysUser;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户相关接口
 * 
 * @author 小威老师 xiaoweijiagou@163.com
 *
 */
@Api(tags = "用户")

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
    private NoticeDao noticeDao;
	@Autowired
    private TodoDao todoDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存用户")
	@PreAuthorize("hasAuthority('sys:user:add')")
	public SysUser saveUser(@RequestBody UserDto userDto) {
		SysUser u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改用户")
	@PreAuthorize("hasAuthority('sys:user:add')")
	public SysUser updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@LogAnnotation
	@PutMapping(params = "headImgUrl")
	@ApiOperation(value = "修改头像")
	public void updateHeadImgUrl(String headImgUrl) {
		SysUser user = UserUtil.getLoginUser();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userDto.setHeadImgUrl(headImgUrl);

		userService.updateUser(userDto);
		log.debug("{}修改了头像", user.getUsername());
	}

	@LogAnnotation
	@PutMapping("/{username}")
	@ApiOperation(value = "修改密码")
	@PreAuthorize("hasAuthority('sys:user:password')")
	public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
		userService.changePassword(username, oldPassword, newPassword);
	}

	@GetMapping
	@ApiOperation(value = "用户列表")
//	@PreAuthorize("hasAuthority('sys:user:query')")
	public PageTableResponse listUsers(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return userDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<SysUserDto> list(PageTableRequest request) {
				List<SysUserDto> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除")
	public void delete(@PathVariable Long id) {
		userDao.delete(id);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current")
	public SysUser currentUser() {
		SysUser sysUser = UserUtil.getLoginUser();
//		if(null!=sysUser){
//			Long deptId = sysUser.getDeptid();
//			Dept dept = deptDao.getById(deptId);
//			if(null!=dept){
//				if("2".equals(dept.getZhfhgl())  || "3".equals(dept.getZhfhgl()) ){
//					Dept depts = deptDao.getById(dept.getPid());
//					if(null!=depts){
//						sysUser.setLoginUserDepartName(depts.getDeptname());
//					}else{
//						sysUser.setLoginUserDepartName("");
//					}
//				}else{
//					sysUser.setLoginUserDepartName(dept.getDeptname());
//				}
//			}else{
//				sysUser.setLoginUserDepartName("");
//			}
//		}
		return sysUser;
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current1")
	public SysUserDto current1User() {
		SysUserDto sysUserDto = userDao.getDeptnameById(UserUtil.getLoginUser().getId());
		sysUserDto.setRoles(roleDao.listByUserId(UserUtil.getLoginUser().getId()));;
		return sysUserDto;
	}

	@GetMapping("/saveClientInfo")
	@ApiOperation(value = "保存设备信息")
	public SysUser saveClientInfo(String clientid) {
		LoginUser loginUser = UserUtil.getLoginUser();
		userDao.clearClientInfo(clientid);
		userDao.saveClientInfo(clientid,loginUser.getId());
		return loginUser;
	}

	@ApiOperation(value = "根据用户id获取用户")
	@GetMapping("/{id}")
//	@PreAuthorize("hasAuthority('sys:user:query')")
	public SysUserDto user(@PathVariable Long id) {
		return userDao.getById(id);
	}

	@ApiOperation(value = "初始化密码")
	@GetMapping("/rePassword")
	public void rePassword(Long id) {
		userDao.changePassword(id,passwordEncoder.encode("123456"));
	}

	@GetMapping("/list2")
	@ApiOperation(value = "列表〃")
	//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
	public Map list2(PageTableRequest request) {
		if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:user:querydept") > 0){
			request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
		}

		Map map = new HashMap();

		Integer page = Integer.valueOf((String)request.getParams().get("offset"));
		Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

		int count = userDao.count(request.getParams());

		List list = userDao.list(request.getParams(), page*limit-limit, limit);

		map.put("data",list);
		map.put("count",count);
		map.put("code","0");
		map.put("msg","");

		return map;
	}

	@GetMapping("/list3")
	@ApiOperation(value = "列表〃")
	//    @PreAuthorize("hasAuthority('sys:jjxx:query')")
	public Map list3(PageTableRequest request) {
//		if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:user:querydept") > 0){
//			request.getParams().put("deptid", UserUtil.getLoginUser().getDeptid());
//		}

		Map map = new HashMap();

		Integer page = Integer.valueOf((String)request.getParams().get("offset"));
		Integer limit = Integer.valueOf((String)request.getParams().get("limit"));

		int count = userDao.count(request.getParams());

		List list = userDao.list(request.getParams(), page*limit-limit, limit);

		map.put("data",list);
		map.put("count",count);
		map.put("code","0");
		map.put("msg","");

		return map;
	}
	@GetMapping("/listByCondition")
	@ApiOperation(value = "根据条件列出人员")
	public List<SysUserDto> listByCondition(PageTableRequest request) {
		return userDao.listByCondition(request.getParams());
	}

	@PostMapping("/export")
	@ApiOperation(value = "导出人员档案")
	public void export(PageTableRequest request, HttpServletResponse response) {
          userService.export(request,response);
	}

	@ApiOperation(value = "当前登录用户及部门仓库")
	@GetMapping("/currentwarehouse")
	public SysUserDto currentwarehouse() {
		SysUserDto sysUserDto = userDao.getDeptnameById(UserUtil.getLoginUser().getId());
		sysUserDto.setRoles(roleDao.listByUserId(UserUtil.getLoginUser().getId()));
		Warehouse warehouse = warehouseDao.getByDeptId(sysUserDto.getDeptid());
		sysUserDto.setWhid(warehouse.getId());
		sysUserDto.setWhname(warehouse.getWhname());
		return sysUserDto;
	}

	@GetMapping("/listusers")
	@ApiOperation(value = "列出所有用户")
	public List<SysUserDto> listUsers() {
		List<SysUserDto> users;
		if(permissionDao.hasPermission(UserUtil.getLoginUser().getId(),"sys:user:querydept") > 0){
			users= userDao.listUsersByDept(UserUtil.getLoginUser().getDeptid());
		}else {
			users = userDao.listUsers();
		}


		return users;
	}

	@GetMapping("/listbydept")
	@ApiOperation(value = "列出所有某个部门和岗位的用户")
	public List<SysUserDto> listbydept(@RequestParam("deptid") Long deptid) {
		List<SysUserDto> users = userDao.listUsersByDept(deptid);
		return users;
	}


	@GetMapping("/getUserByRoleid")
	@ApiOperation(value = "根据角色找用户")
	public List<SysUser> getUserByRoleid(Long roleId) {
		return userDao.getUserByRoleid(roleId);
	}

	@GetMapping("/getUsersByRoleDept")
	@ApiOperation(value = "显示登录用户所在部门，某一角色用户")
	public List<SysUser> getDeptUserByRole(Long roleId) {
		return userDao.getUsersByRoleDept(roleId,UserUtil.getLoginUser().getDeptid());
	}

	/**
	 * 统计是否有未读消息
	 * @return
	 */
	@GetMapping("/countNotice")
	@ApiOperation(value = "查询用户消息")
	public Map countNotice() {
	    Map data = new HashMap();
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId",UserUtil.getLoginUser().getId());
        params.put("status",0);
        int count = noticeDao.countNoticeByUser(params);
        data.put("num",count);
        data.put("code",0);
        data.put("msg","成功");
        return data;
	}

    @GetMapping("/countTodo")
    @ApiOperation(value = "查询是否有待办任务")
    public Map countTodo() {
        Map data = new HashMap();
        Long userId = UserUtil.getLoginUser().getId();
        HashMap<String, Object> params = new HashMap<>();
        params.put("auditby",userId);
        params.put("status","0");
        int count = todoDao.count(params);
        data.put("code","0");
        data.put("msg","成功");
        data.put("num",count);
        return data;
    }

}
