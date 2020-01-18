package com.itycu.server.controller;

import com.itycu.server.dao.DeptDao;
import com.itycu.server.dao.TodoDao;
import com.itycu.server.dao.UserDao;
import com.itycu.server.dto.TodoDto;
import com.itycu.server.dto.TodoVO;
import com.itycu.server.model.Todo;
import com.itycu.server.page.table.PageTableHandler;
import com.itycu.server.page.table.PageTableHandler.CountHandler;
import com.itycu.server.page.table.PageTableHandler.ListHandler;
import com.itycu.server.page.table.PageTableRequest;
import com.itycu.server.page.table.PageTableResponse;
import com.itycu.server.service.TodoService;
import com.itycu.server.utils.UserUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoDao todoDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private TodoService todoService;

    @PostMapping
    @ApiOperation(value = "保存")
    public Todo save(@RequestBody Todo todo) {
        todoDao.save(todo);

        return todo;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public Todo get(@PathVariable Long id) {
        return todoDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public Todo update(@RequestBody TodoDto todoDto) {
        return  todoService.update(todoDto);
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        Object isSendby = request.getParams().get("isSendby");
        if (isSendby == null){
             request.getParams().put("sendby", UserUtil.getLoginUser().getId());
        }else{
             if ("1".equals((String)isSendby)){
                 request.getParams().put("sendby",UserUtil.getLoginUser().getId());
             }else{
                 request.getParams().put("auditby",UserUtil.getLoginUser().getId());
             }
        }

        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return todoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TodoVO> list(PageTableRequest request) {
                return todoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @GetMapping("/listbyuser")
    @ApiOperation(value = "列表")
    public PageTableResponse listByUser(PageTableRequest request) {

        request.getParams().put("auditby", UserUtil.getLoginUser().getId());
        request.getParams().put("orderBy", "ORDER BY id desc");

        PageTableResponse handle = new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return todoDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TodoVO> list(PageTableRequest request) {
                return todoDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
        return handle;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        todoDao.delete(id);
    }

    @GetMapping(params = "type")
    public List<Todo> listByUser(String type) {
        return todoDao.listByUser(type,UserUtil.getLoginUser().getId() );
    }

    @GetMapping(value="/listvo")
    public List<TodoVO> listTodoVOByUser(@RequestParam("type") String type,@RequestParam("limit") int limit) {
        return todoDao.listTodoVOByUser(type,UserUtil.getLoginUser().getId(),limit);
    }

    @GetMapping(value = "/listbizid")
    public List<TodoVO> listByBizid(@RequestParam("bizid")Long bizid,@RequestParam("flowid") Long flowid){
        List<TodoVO> todos = todoDao.listByBizid(bizid,flowid);

//        for(Todo todo : todos){   //循环里提取数据会降低效率
//            SysUser byId = userDao.getById(todo.getAuditby());
//            Dept byId1 = deptDao.getById(byId.getDeptid());
//            todo.setC01(byId.getNickname());
//            todo.setC02(byId1.getDeptname());
//        }
        return  todos;
    }

}
