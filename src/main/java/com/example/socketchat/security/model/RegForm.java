package com.example.socketchat.security.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Oleg Pavlyukov
 * on 03.12.2019
 * cpabox777@gmail.com
 */
@Data
public class RegForm {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private  boolean asAdmin;

}
