package com.changji.cloud.social.repository.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.social.mapper.FriendCircleLikeMapper;
import com.changji.cloud.social.model.FriendCircleLike;
import com.changji.cloud.social.repository.FriendCircleLikeRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小问号
 * @date 2022/9/21 10
 * description
 */
@Repository
public class FriendCircleLikeRepositoryImpl implements FriendCircleLikeRepository {

    @Autowired
    private FriendCircleLikeMapper friendCircleLikeMapper;

    @Override
    public FriendCircleLike save(FriendCircleLike friendCircleLike) {
        int i = friendCircleLikeMapper.save(friendCircleLike);
        if (i != 1) {
            throw new ServiceException("保存点赞失败");
        }
        return friendCircleLike;
    }

    @Override
    public List<FriendCircleLike> saveAll(List<FriendCircleLike> list) {
        int i = friendCircleLikeMapper.saveAll(list);
        if (i != list.size()) {
            throw new ServiceException("保存点赞失败");
        }
        return list;
    }

    @Override
    public Page<FriendCircleLike> findByLikedFriendCircleIdAndStatus(Long friendCircleId, Integer status, Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSort().stream().map(liked -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, liked.getProperty()) + " " + liked.getDirection()).collect(Collectors.joining(",")));

        PageInfo<FriendCircleLike> friendCircleLikePageInfo = new PageInfo<>(friendCircleLikeMapper.findByLikedFriendCircleIdAndStatus(friendCircleId, status));
        return new PageImpl(friendCircleLikePageInfo.getList());
    }

    @Override
    public Page<FriendCircleLike> findByUserIdAndStatus(Long userId, Integer status, Pageable pageable) {

        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSort().stream().map(liked -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, liked.getProperty()) + " " + liked.getDirection()).collect(Collectors.joining(",")));
        PageInfo<FriendCircleLike> friendCircleLikePageInfo = new PageInfo<>(friendCircleLikeMapper.findByUserIdAndStatus(userId, status));
        return new PageImpl(friendCircleLikePageInfo.getList());
    }

    @Override
    public FriendCircleLike findByLikedFriendCircleIdAndUserId(Long friendCircleId, Long userId) {
        return  friendCircleLikeMapper.findByLikedFriendCircleIdAndUserId(friendCircleId, userId);
    }
}
