package com.example.herokutest2.responseEntity;

import com.example.herokutest2.entities.Role;

import com.example.herokutest2.entities.User;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponse {
    private Long id;
    private String nomeCompleto;
    private String email;
    private String username;
    private Set<Role> roles;

    public static UserResponse parseUser( User user ) {

        return UserResponse.builder()
                .id( user.getId() )
                .nomeCompleto( user.getNomeCompleto() )
                .email( user.getEmail() )
                .username( user.getUsername() )
                .roles( user.getRoles() )
                .build();
    }
}
