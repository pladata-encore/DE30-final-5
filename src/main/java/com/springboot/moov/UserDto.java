package com.springboot.moov;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private String nickname;
    private String password;
    private String username;
}
