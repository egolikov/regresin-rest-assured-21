package in.regres.models;

import lombok.Data;

import java.util.List;

@Data
public class ListResourcesResponseModel {
    int page;
    int per_page;
    int total;
    int total_pages;
    List<ListResourcesDataResponseModel> data;
    ListResourcesSupportResponseModel support;
}
