package in.regres.models;

import lombok.Data;

@Data
public class CreatePersonResponseModel {
    String name;
    String job;
    String id;
    String createdAt;
}
