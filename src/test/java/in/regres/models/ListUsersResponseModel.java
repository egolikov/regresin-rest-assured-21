package in.regres.models;

import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseModel {
    int page;
    int per_page;
    int total;
    int total_pages;
    List<ListUsersDataResponseModel> data;
    ListUsersSupportResponseModel support;
}
