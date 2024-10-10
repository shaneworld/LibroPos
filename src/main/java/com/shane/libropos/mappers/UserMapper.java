package com.shane.libropos.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.shane.libropos.entity.User;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {
  User findByUsername(@Param("username") String username);
  void insertUser(User user);
}
