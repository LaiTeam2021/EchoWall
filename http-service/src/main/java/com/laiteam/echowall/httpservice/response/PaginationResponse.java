package com.laiteam.echowall.httpservice.response;

import com.laiteam.echowall.dal.entity.Profile;
import com.laiteam.echowall.service.dto.Pagination;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class PaginationResponse<E> {

    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;
    private List<E> page;

    public static PaginationResponse<ProfileResponse> convertProfileResponse(Pagination<Profile> profilePagination) {
        PaginationResponse<ProfileResponse> response = new PaginationResponse<>();
        response.setCurrentPage(profilePagination.getCurrentPage());
        response.setPageSize(profilePagination.getPageSize());
        response.setTotalPages(profilePagination.getTotalPages());
        response.setTotalCount(profilePagination.getTotalCount());
        response.setPage(ProfileResponse.convertList(profilePagination.getPage()));
        return response;
    }
}
