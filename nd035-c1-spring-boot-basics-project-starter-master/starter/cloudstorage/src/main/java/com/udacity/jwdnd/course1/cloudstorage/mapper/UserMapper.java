package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    public User getUsers(String username);

    @Select("SELECT * FROM USERS WHERE userid= #{userid}")
    public User getUserbyid(Integer userid);

    @Insert("INSERT INTO USERS(userid,username,salt,password,firstname,lastname) VALUES(#{userid},#{username},#{salt},#{password},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    public int insert(User user);

}
