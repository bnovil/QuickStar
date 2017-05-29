package com.quickstar.service.imp;

import com.quickstar.dao.UserDao;
import com.quickstar.pojo.dto.UserDTO;
import com.quickstar.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lzq on 2017/5/28.
 *
 * @Description
 */
@Service
public class UserServiceImp implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        UserDTO user = userDao.insertUser(userDTO);
        return user;
    }

    @Override
    public UserDTO getUser(UserDTO userDTO) {
        UserDTO user = userDao.getUser(userDTO);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        userDao.updateUser(userDTO);
        return userDao.getUser(userDTO);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.deleteUser(id);
    }
}
