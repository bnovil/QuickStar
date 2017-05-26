package com.quickstar;

import com.quickstar.dao.UserDao;
import com.quickstar.mapper.UserDTOMapper;
import com.quickstar.pojo.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by L on 2017/5/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.business.mapper")
public class UserTest {

    @Resource
    UserDao userDao;

    @Test
    public void addUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setUsername("Jack");
        userDTO.setPassword("123");
        userDTO.setUpdateTime(new Date());
        userDao.insertUser(userDTO);
    }
}
