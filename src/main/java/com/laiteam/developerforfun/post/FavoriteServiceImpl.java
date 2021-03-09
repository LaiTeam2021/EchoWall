package com.laiteam.developerforfun.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    final FavoriteRepository favoriteRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Optional<Favorite> findById(Long id) {
        return favoriteRepository.findById(id);
    }

    @Override
    public Optional<List<Favorite>> findByUserId(Long userId) {
        return Optional.ofNullable(favoriteRepository.findByUserId(userId));
    }

    @Override
    public Optional<List<Favorite>> findByPostId(Long postId) {
        return Optional.ofNullable(favoriteRepository.findByPostId(postId));
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteById(Long id) {
        favoriteRepository.deleteById(id);
    }
}
