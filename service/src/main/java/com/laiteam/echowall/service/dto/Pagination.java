package com.laiteam.echowall.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class Pagination<E> {
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;
    private List<E> page;

    public Pagination(List<E> page, Long totalCount, Integer totalPages) {
        this.page = page;
        this.totalCount = totalCount;
        this.totalPages = totalPages;
    }
}
