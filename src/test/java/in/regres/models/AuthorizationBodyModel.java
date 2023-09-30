package in.regres.models;

import lombok.Data;

@Data
public class AuthorizationBodyModel {
    String email;
    String password;
}