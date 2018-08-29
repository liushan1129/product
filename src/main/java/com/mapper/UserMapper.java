package com.mapper;

import com.bean.user.User;
import com.bean.user.UserParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper{

//    List<User> select(User user);
//    @Select("select * from User")
    List<User> findUserListByParams(UserParams userParams);
    List<User> findUserList();

    void addUser(User user);

    User findUserByNameAndMobile(@Param("userName")String userName, @Param("mobile") String mobile);

//    @Select("select * from User where userName=#{userName}")
    User getUserByField(@Param("filedName") String filedName, @Param("filedValue")Object filedValue);


    void updateUser(@Param("id") Long id, @Param("user")User user);

    void deleteUser(@Param("id") Long id);
}
