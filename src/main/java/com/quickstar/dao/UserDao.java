package com.quickstar.dao;

import com.quickstar.mapper.UserDTOMapper;
import com.quickstar.pojo.dto.UserDTO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by L on 2017/5/26.
 */
@Repository
public class UserDao {
    @Resource
    UserDTOMapper userDTOMapper;

    public UserDTO insertUser(UserDTO userDTO) {
        if (userDTOMapper.insert(userDTO) > 0) {
            return userDTO;
        }
        return null;
    }

}
