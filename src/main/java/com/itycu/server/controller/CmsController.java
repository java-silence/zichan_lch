package com.itycu.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itycu.server.dao.CmsNewsDao;
import com.itycu.server.dao.PermissionDao;
import com.itycu.server.model.CmsNews;
import com.itycu.server.model.Permission;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.service.PermissionService;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableResponse;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限相关接口
 * 
 * @author 小威老师
 *
 */
@Api(tags = "权限")
@RestController
@RequestMapping("/cms")
public class CmsController {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private PermissionService permissionService;

	@Autowired
    private CmsNewsDao cmsNewsDao;

	private void setChild(List<Permission> permissions) {
		permissions.parallelStream().forEach(per -> {
			List<Permission> child = permissions.stream().filter(p -> p.getParentId().equals(per.getId()))
					.collect(Collectors.toList());
			per.setChild(child);
		});
	}

	/**
	 * 菜单列表
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param list
	 */
	private void setPermissionsList(Long pId, List<Permission> permissionsAll, List<Permission> list) {
		for (Permission per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				list.add(per);
				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					setPermissionsList(per.getId(), permissionsAll, list);
				}
			}
		}
	}

	@GetMapping
	@ApiOperation(value = "菜单列表")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public List<Permission> permissionsList() {
		List<Permission> permissionsAll = permissionDao.listAll();

		List<Permission> list = Lists.newArrayList();
		setPermissionsList(0L, permissionsAll, list);

		return list;
	}


	@ApiOperation(value = "根据id获取某个栏目")
	@GetMapping(value="/cmsmenu",params = "id")
	public List<Permission> cmsmenu(Long id) {
		List<Permission> permissionsAll = permissionDao.listAll();
		List<Permission> list = Lists.newArrayList();
		setPermissionsList(id, permissionsAll, list);

		final List<Permission> permissions = list.stream().filter(l -> l.getType().equals(1))
				.collect(Collectors.toList());

		setChild(permissions);

		return permissions.stream().filter(p -> p.getParentId().equals(id)).collect(Collectors.toList());
	}

	@ApiOperation(value = "根据id获取子栏目")
	@GetMapping(value="/childmenu",params = "id")
	public List<Permission> childmenu(Long id) {
		List<Permission> list = permissionDao.listChildMenu(id);
		return list;
	}

    @ApiOperation(value = "根据子栏目id获取子栏目中最新一条内容")
    @GetMapping(value="/getlastcontent",params = "id")
    public CmsNews lastcontent(Long id) {
        CmsNews cmsNews = cmsNewsDao.LastContent(id);
        return cmsNews;
    }

	@ApiOperation(value = "根据文章id获取新闻内容")
	@GetMapping(value="/getcontent",params = "id")
	public CmsNews getcontent(Long id) {
		CmsNews cmsNews = cmsNewsDao.getContent(id);

		cmsNewsDao.addhits(id);

		return cmsNews;
	}

	@ApiOperation(value = "根据小栏目编号，提取最新5条数据")
	@GetMapping(value="/getnewslist")//,params = "id"
	public List<CmsNews> getnewslist(@RequestParam("id") Long id ,@RequestParam("limit") int limit) {
		List<CmsNews> cmsNews = cmsNewsDao.getNewsList(id,limit);
		return cmsNews;
	}

	@ApiOperation(value = "根据小栏目编号，提取最新5条学术预报数据，当前日期之后，升序排列")
	@GetMapping(value="/geteventlist")//,params = "id"
	public List<CmsNews> geteventlist(@RequestParam("id") Long id ,@RequestParam("limit") int limit) {
		List<CmsNews> cmsNews = cmsNewsDao.getEventList(id,limit);
		return cmsNews;
	}

	@ApiOperation(value = "根据栏目编号,无栏目编码则取所有栏目，提取最新1条数据")
	@GetMapping(value="/gethomeimg")//,params = "id"
	public List<CmsNews> gethomeimg(@RequestParam("imgtype") String imgtype ,@RequestParam("limit") int limit) {
		List<CmsNews> cmsNews = cmsNewsDao.getHomeImg(imgtype,limit);
		return cmsNews;
	}

	@GetMapping("/all")
	@ApiOperation(value = "所有菜单")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public JSONArray permissionsAll() {
		List<Permission> permissionsAll = permissionDao.listAll();
		JSONArray array = new JSONArray();
		setPermissionsTree(0L, permissionsAll, array);

		return array;
	}

	@GetMapping("/parents")
	@ApiOperation(value = "一级菜单")
	@PreAuthorize("hasAuthority('sys:menu:query')")
	public List<Permission> parentMenu() {
		List<Permission> parents = permissionDao.listParents();

		return parents;
	}

	/**
	 * 菜单树
	 * 
	 * @param pId
	 * @param permissionsAll
	 * @param array
	 */
	private void setPermissionsTree(Long pId, List<Permission> permissionsAll, JSONArray array) {
		for (Permission per : permissionsAll) {
			if (per.getParentId().equals(pId)) {
				String string = JSONObject.toJSONString(per);
				JSONObject parent = (JSONObject) JSONObject.parse(string);
				array.add(parent);

				if (permissionsAll.stream().filter(p -> p.getParentId().equals(per.getId())).findAny() != null) {
					JSONArray child = new JSONArray();
					parent.put("child", child);
					setPermissionsTree(per.getId(), permissionsAll, child);
				}
			}
		}
	}


	@GetMapping("listdoc")
	@ApiOperation(value = "列表")
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return cmsNewsDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<CmsNews> list(PageTableRequest request) {
				return cmsNewsDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}
}
