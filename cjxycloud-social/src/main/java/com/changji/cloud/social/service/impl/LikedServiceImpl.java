package com.changji.cloud.social.service.impl;

import com.changji.cloud.social.dto.LikedCountDTO;
import com.changji.cloud.social.enums.LikedStatusEnum;
import com.changji.cloud.social.model.FriendCircleLike;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.repository.FriendCircleLikeRepository;
import com.changji.cloud.social.service.FriendCircleService;
import com.changji.cloud.social.service.LikedRedisService;
import com.changji.cloud.social.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private FriendCircleLikeRepository friendCircleLikeRepository;

    @Override
    @Transactional
    public FriendCircleLike save(FriendCircleLike friendCircleLike) {
        return friendCircleLikeRepository.save(friendCircleLike);
    }

    @Override
    public FriendCircleLike update(FriendCircleLike friendCircleLike) {
        return friendCircleLikeRepository.update(friendCircleLike);
    }

    @Override
    @Transactional
    public List<FriendCircleLike> saveAll(List<FriendCircleLike> list) {
        return friendCircleLikeRepository.saveAll(list);
    }

    @Override
    public Page<FriendCircleLike> getLikedListByLikedFriendCircleId(Long likedFriendCircleId, Pageable pageable) {
        return friendCircleLikeRepository.findByLikedFriendCircleIdAndStatus(likedFriendCircleId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public Page<FriendCircleLike> getLikedListByUserId(Long userId, Pageable pageable) {
        return friendCircleLikeRepository.findByUserIdAndStatus(userId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    @Override
    public FriendCircleLike getByLikedFriendCircleIdAndUserId(Long likedFriendCircleId, Long userId) {
        return friendCircleLikeRepository.findByLikedFriendCircleIdAndUserId(likedFriendCircleId, userId);
    }

    @Override
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
