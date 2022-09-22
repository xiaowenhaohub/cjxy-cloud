package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.core.exception.ServiceException;
import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.dto.FriendCircleDTO;
import com.changji.cloud.social.dto.LikedDTO;
import com.changji.cloud.social.mapper.FriendCircleMessageMapper;
import com.changji.cloud.social.model.FriendCircleLike;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.repository.FriendCircleMessageRepository;
import com.changji.cloud.social.service.FriendCircleService;
import com.changji.cloud.social.service.LikedRedisService;
import com.changji.cloud.social.service.LikedService;
import com.changji.cloud.social.utils.LikedUtils;
import com.changji.cloud.social.vo.FriendCircleMessageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 小问号
 * @date 2022/9/20 14
 * description
 */
@Service
public class FriendCircleServiceImpl implements FriendCircleService {

    @Autowired
    private FriendCircleMessageMapper friendCircleMessageMapper;

    @Autowired
    private LikedRedisService likedRedisService;

    @Autowired
    private LikedService likedService;

    @Autowired
    private FriendCircleMessageRepository friendCircleMessageRepository;

    @Autowired
    private MapperFacade mapperFacade;


    @Override
    @Transactional
    public void saveFriendCircleMessage(FriendCircleDTO friendCircleDTO) {
        FriendCircleMessage friendCircleMessage = mapperFacade.map(friendCircleDTO, FriendCircleMessage.class);

        friendCircleMessage.setUserId(SecurityUtils.getLoginUser().getUserId());

        friendCircleMessageMapper.save(friendCircleMessage);
    }

    @Override
    public FriendCircleMessage updateFriendCircleMessage(FriendCircleMessage friendCircleMessage) {
        return friendCircleMessageRepository.updateFriendCircleMessage(friendCircleMessage);
    }

    @Override
    @Transactional
    public void likedFriendCircle(Long friendCircleId) {
        Long userId = SecurityUtils.getLoginUser().getUserId();

        //判断是否存在该朋友圈信息
        FriendCircleMessage friendCircleMessageById = getFriendCircleMessageById(friendCircleId);
        if (Objects.isNull(friendCircleMessageById)) {
            throw new ServiceException("没有该朋友圈信息");
        }
        //判断是否已经点赞过
        //redis
        LikedDTO redisFriendCircleLike = likedRedisService.getOneLikedDataFromRedis(LikedUtils.getLikedKey(String.valueOf(friendCircleId), String.valueOf(userId)));
        if (redisFriendCircleLike.getValue() != null && redisFriendCircleLike.getValue() == 1) {
            throw new ServiceException("您已经点赞过该朋友圈");
        }
        //mysql
        FriendCircleLike friendCircleLike = likedService.getByLikedFriendCircleIdAndUserId(friendCircleId, userId);
        if (friendCircleLike != null && friendCircleLike.getStatus() == 1) {
            throw new ServiceException("您已经点赞过该朋友圈");
        }

        // 点赞信息缓存到redis
        likedRedisService.saveLiked2Redis(friendCircleId, userId);
        likedRedisService.incrementLikedCount(friendCircleId);

    }

    @Override
    public List<FriendCircleMessageVO> getFriendCircleList(Page page) {

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<FriendCircleMessage> list = friendCircleMessageMapper.getFriendCircle();
        PageInfo<FriendCircleMessage> pageInfo = new PageInfo<>(list);
        List<FriendCircleMessage> friendCircleMessageList = pageInfo.getList();

        List<FriendCircleMessageVO> friendCircleMessageVOList = new ArrayList<>();

        for (FriendCircleMessage friendCircleMessage : friendCircleMessageList) {
            FriendCircleMessageVO friendCircleVO = mapperFacade.map(friendCircleMessage, FriendCircleMessageVO.class);


            friendCircleMessageVOList.add(friendCircleVO);
        }



        return friendCircleMessageVOList;
    }

    @Override
    public FriendCircleMessage getFriendCircleMessageById(Long id) {
        return friendCircleMessageMapper.getFriendCircleMessageById(id);
    }
}
