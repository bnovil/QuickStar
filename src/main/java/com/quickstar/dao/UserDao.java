package com.quickstar.dao;

import com.quickstar.redis.RedisUtil;
import com.quickstar.mapper.UserDTOMapper;
import com.quickstar.pojo.dto.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

/**
 * Created by L on 2017/5/26.
 */
@Repository
public class UserDao {
    @Resource
    private UserDTOMapper userDTOMapper;
    @Resource
    private RedisUtil<String, UserDTO> redisUtil;

    @Transactional
    public UserDTO insertUser(UserDTO userDTO) {
        if (userDTOMapper.insert(userDTO) > 0) {
            redisUtil.set(userDTO.getUsername(), userDTO,600L);
            return userDTO;
        }
        return null;
    }

    public UserDTO getUser(UserDTO userDTO) {
        UserDTO user = redisUtil.get(userDTO.getUsername());
        if (user != null) {
            user = userDTOMapper.selectByPrimaryKey(userDTO.getId());
            return user;
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
