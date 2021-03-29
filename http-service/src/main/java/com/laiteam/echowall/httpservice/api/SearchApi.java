package com.laiteam.echowall.httpservice.api;

import com.laiteam.echowall.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SearchApi {
    private final SearchService searchService;

    @Autowired
    public SearchApi(SearchService searchService) {this.searchService = searchService;}

    @GetMapping(path = "/search/{searchText}")
    public ResponseEntity<?> searchPost(@PathVariable String searchText) {
        return ResponseEntity.ok(searchService.findByTitleContaining(searchText));
    }
}
