package com.lapka.nonogramportal.business.mappers;

import com.lapka.nonogramportal.business.repository.model.LikeDAO;
import com.lapka.nonogramportal.model.Like;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    Like likeDaoToLike(LikeDAO likeDao);
    LikeDAO likeToLikeDao(Like like);
}
