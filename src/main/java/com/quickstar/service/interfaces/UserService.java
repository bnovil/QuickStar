package com.quickstar.service.interfaces;

import com.quickstar.pojo.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Created by lzq on 2017/5/27.
 *
 * @Description
 */

public interface UserService {
    UserDTO addUser(UserDTO userDTO);

    UserDTO getUser(int id);

    UserDTO updateUser(UserDTO userDTO);

    int deleteUser(int id);
}
