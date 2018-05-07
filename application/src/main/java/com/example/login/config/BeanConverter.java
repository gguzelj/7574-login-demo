package com.example.login.config;

import com.example.login.domain.User;
import com.example.login.dto.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class BeanConverter {

    @Bean
    public DefaultConversionService defaultConversionService() {
        DefaultConversionService dcs = new DefaultConversionService();
        dcs.addConverter(new UserDtoToUserDomain());
        dcs.addConverter(new UserDomainToUserDto());
        return dcs;
    }


    public class UserDtoToUserDomain implements Converter<UserDto, User> {
        @Override
        public User convert(UserDto userDto) {
            return new User(userDto.getId(), userDto.getEmail(), userDto.getPassword());
        }
    }

    public class UserDomainToUserDto implements Converter<User, UserDto> {
        @Override
        public UserDto convert(User user) {
            return new UserDto(user.getId(), user.getEmail(), user.getPassword());
        }
    }

}
