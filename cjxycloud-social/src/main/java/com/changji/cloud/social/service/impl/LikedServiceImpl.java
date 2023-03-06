package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.social.model.dto.LikedCountDTO;
import com.changji.cloud.social.enums.LikedStatusEnum;
import com.changji.cloud.social.mapper.FriendCircleLikeMapper;
import com.changji.cloud.social.model.FriendCircleLike;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.service.FriendCircleService;
import com.changji.cloud.social.service.LikedRedisService;
import com.changji.cloud.social.service.LikedService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.CaseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 小问号
 * @date 2022/9/21 09
 * description
 */
@Service
public class LikedServiceImpl implements LikedService {

    @Autowired
    private LikedRedisService likedRedisService;

    @Autowired
    private FriendCircleService friendCircleService;

    @Autowired
    private FriendCircleLikeMapper friendCircleLikeMapper;

    @Override
    @Transactional
    public FriendCircleLike save(FriendCircleLike friendCircleLike) {
        int i = friendCircleLikeMapper.save(friendCircleLike);
        if (i != 1) {
            throw new ServiceException("保存点赞失败");
        }
        return friendCircleLike;
    }

    @Override
    public FriendCircleLike update(FriendCircleLike friendCircleLike) {
        friendCircleLikeMapper.update(friendCircleLike);
        return friendCircleLike;
    }

    @Override
    @Transactional
    public List<FriendCircleLike> saveAll(List<FriendCircleLike> list) {
        int i = friendCircleLikeMapper.saveAll(list);
        if (i != list.size()) {
            throw new ServiceException("保存点赞失败");
        }
        return list;
    }

    @Override
    public Page<FriendCircleLike> getLikedListByFriendCircleId(Long friendCircleId, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSort().stream().map(liked -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, liked.getProperty()) + " " + liked.getDirection()).collect(Collectors.joining(",")));
        PageInfo<FriendCircleLike> friendCircleLikePageInfo = new PageInfo<>(friendCircleLikeMapper.findByLikedFriendCircleIdAndStatus(friendCircleId, LikedStatusEnum.LIKE.getCode()));
        return new PageImpl(friendCircleLikePageInfo.getList());
    }

    @Override
    public Page<FriendCircleLike> getLikedListByUserId(Long userId, Pageable pageable) {
        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize(),
                pageable.getSort().stream().map(liked -> CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, liked.getProperty()) + " " + liked.getDirection()).collect(Collectors.joining(",")));
        PageInfo<FriendCircleLike> friendCircleLikePageInfo = new PageInfo<>(friendCircleLikeMapper.findByUserIdAndStatus(userId, LikedStatusEnum.LIKE.getCode()));
        return new PageImpl(friendCircleLikePageInfo.getList());
    }

    @Override
    public FriendCircleLike getByLikedFriendCircleIdAndUserId(Long friendCircleId, Long userId) {
        return friendCircleLikeMapper.findByLikedFriendCircleIdAndUserId(friendCircleId, userId);
    }

    @Override
    @Transactional
    public void transLikedFromRedis2DB() {
        List<FriendCircleLike> list = likedRedisService.getLikedDataFromRedis();
        for (FriendCircleLike like : list) {
            FriendCircleLike friendCircleLike = getByLikedFriendCircleIdAndUserId(like.getFriendCircleId(), like.getUserId());
            if (friendCircleLike == null) {
                //没有记录，直接存入
                save(like);
            } else {
                //有记录，需要更新
                friendCircleLike.setStatus(like.getStatus());
                update(friendCircleLike);
            }
        }
    }

    @Override
    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = likedRedisService.getAllLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            FriendCircleMessage friendCircleMessage = friendCircleService.getFriendCircleMessageById(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (friendCircleMessage != null){
                Integer likeCount = friendCircleMessage.getLikedCount() + dto.getCount();
                friendCircleMessage.setLikedCount(likeCount);
                //更新点赞数量
                friendCircleService.updateFriendCircleMessage(friendCircleMessage);
            }
        }
    }
}
