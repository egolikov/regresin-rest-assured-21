package in.regres.models;

import lombok.Data;

@Data
public class ListUsersDataResponseModel {
    int id;
    String email;
    String first_name;
    String last_name;
    String avatar;
}
