package com.example.testheroku.responseEntity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {

    private String nomeCompleto;
    private String email;
    private String username;
    private String password;

}
