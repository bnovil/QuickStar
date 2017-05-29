package com.quickstar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quickstar.common.json.JsonUtil;
import com.quickstar.pojo.dto.UserDTO;
import com.quickstar.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lzq on 2017/5/27.
 *
 * @Description
 */
@RestController
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(UserDTO userDTO) {
        if (userService.addUser(userDTO) != null) {
            return "add user succeed";
        }
        return "add user failed";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getUser(UserDTO userDTO) throws JsonProcessingException {
        UserDTO user = userService.getUser(userDTO);
        String userJson = JsonUtil.objectToJson(user);
        return userJson;
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(UserDTO userDTO) throws JsonProcessingException {
        UserDTO user = userService.updateUser(userDTO);
        String userJson = JsonUtil.objectToJson(user);
        return userJson;
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser(UserDTO userDTO) throws JsonProcessingException {
        if (userService.deleteUser(userDTO.getId()) > 0) {
            return "delete user succeed";
        }
        return "delete user failed";
    }
}


