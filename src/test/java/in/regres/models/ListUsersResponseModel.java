package in.regres.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ListUsersResponseModel {
    int page;
    @JsonProperty("per_page")
    int perPage;
    int total;
    @JsonProperty("total_pages")
    int totalPages;
    List<ListUsersDataResponseModel> data;
    ListUsersSupportResponseModel support;
}
