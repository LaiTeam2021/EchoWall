package com.laiteam.echowall.service.imp;

import com.laiteam.echowall.dal.entity.Post;
import com.laiteam.echowall.dal.repository.SearchRepository;
import com.laiteam.echowall.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {
    final SearchRepository searchRepository;

    @Autowired
    public SearchServiceImpl (SearchRepository searchRepository) {this.searchRepository = searchRepository;}

    @Override
    public Optional<Post> findByTitleContaining(String searchText){return searchRepository.findByTitleContaining(searchText);}
}
