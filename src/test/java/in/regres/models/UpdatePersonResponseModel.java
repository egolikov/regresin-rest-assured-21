package in.regres.models;

import lombok.Data;

@Data
public class UpdatePersonResponseModel {
    String name;
    String job;
    String updatedAt;
}