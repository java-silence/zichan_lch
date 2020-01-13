package com.itycu.server.app.controller;

import com.itycu.server.app.dto.personal.UpdatePassWordDTO;
import com.itycu.server.app.util.FailMap;
import com.itycu.server.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api("App中的个人中心接口")
@RequestMapping(value = "/mine")
@RestController
public class AppPersonCenterController {


    @Autowired
    private UserService userService;


    @PostMapping(value = "/updatePassword")
    @ApiOperation(value = "修改密码", tags = "修改用户密码")
    public Map<String, Object> changePassword(@RequestBody UpdatePassWordDTO updatePassWordDTO) {
        int result = userService.changePassword(updatePassWordDTO.getUsername(),
                updatePassWordDTO.getOldPassword(), updatePassWordDTO.getNewPassword());
        if (result > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 0);
            map.put("message", "修改成功");
            map.put("data", null);
            return map;
        } else {
            return FailMap.createFailMapMsg("修改密码失败!");
        }
    }


}
