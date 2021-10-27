package com.example.budgetservice.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.budgetservice.model.UserDto;

@Mapper
public interface UserMapper {
	UserDto getUser(long userId);
}
