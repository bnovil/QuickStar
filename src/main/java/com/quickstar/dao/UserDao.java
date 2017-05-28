package com.quickstar.dao;

import com.quickstar.mapper.UserDTOMapper;
import com.quickstar.pojo.dto.UserDTO;
import org.apache.catalina.User;
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

    public UserDTO getUser(Integer id) {
        UserDTO userDTO = userDTOMapper.selectByPrimaryKey(id);
        if (userDTO != null) {
            return userDTO;
        }
        return null;
    }

    public int updateUser(UserDTO userDTO) {
        return userDTOMapper.updateByPrimaryKey(userDTO);
    }

    public int deleteUser(Integer id) {
        return userDTOMapper.deleteByPrimaryKey(id);
    }
}
