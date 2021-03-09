package com.laiteam.developerforfun.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class FavoriteApi {
  private final FavoriteService favoriteService;
  private final PostService postService;

  @Autowired
  public FavoriteApi(FavoriteService favoriteService, PostService postService) {
    this.favoriteService = favoriteService;
    this.postService = postService;
  }

  @RequestMapping(path = "/post/{postId}/favorite", method = POST)
  public Favorite addFavorite(@PathVariable Long postId, @RequestBody Long userId) {
    // If exists, return what is found
    final Optional<List<Favorite>> favoriteListByUser = favoriteService.findByUserId(userId);
    if (favoriteListByUser.isPresent()) {
      final Optional<Favorite> existFavorite =
              favoriteListByUser.get().stream()
                      .filter(favorite -> favorite.getPostId().equals(postId))
                      .findAny();
      if (existFavorite.isPresent()) {
        return existFavorite.get();
      }
    }
    final Favorite favorite = Favorite.builder().userId(userId).postId(postId).build();
    return favoriteService.save(favorite);
  }

  @RequestMapping(path = "/favorite/{favoriteId}/unfavorite", method = DELETE)
  public void deleteFavoriteById(@PathVariable Long favoriteId) {
    favoriteService.deleteById(favoriteId);
  }

  @RequestMapping(path = "/favorite/{userId}", method = GET)
  public List<Post> getFavoritePostsByUserId(@PathVariable Long userId) {
    return favoriteService
            .findByUserId(userId)
            .map(
                    favorites ->
                            favorites.stream()
                                    .map(favorite -> postService.findById(favorite.getPostId()))
                                    .filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(Collectors.toList()))
            .orElseGet(ArrayList::new);
  }
}
