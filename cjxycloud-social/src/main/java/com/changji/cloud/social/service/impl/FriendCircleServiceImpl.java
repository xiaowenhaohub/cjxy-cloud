package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.core.model.Page;
import com.changji.cloud.common.security.utils.SecurityUtils;
import com.changji.cloud.social.dto.FriendCircleDTO;
import com.changji.cloud.social.mapper.FriendCircleMessageMapper;
import com.changji.cloud.social.model.FriendCircleMessage;
import com.changji.cloud.social.service.FriendCircleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private MapperFacade mapperFacade;


    @Override
    @Transactional
    public void saveFriendCircleMessage(FriendCircleDTO friendCircleDTO) {
        FriendCircleMessage friendCircleMessage = mapperFacade.map(friendCircleDTO, FriendCircleMessage.class);

        friendCircleMessage.setUserId(SecurityUtils.getLoginUser().getUserId());

        friendCircleMessageMapper.save(friendCircleMessage);
    }

    @Override
    public List<FriendCircleMessage> getFriendCircleList(Page page) {

        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<FriendCircleMessage> friendCircleMessageList = friendCircleMessageMapper.getFriendCircle();
        PageInfo<FriendCircleMessage> pageInfo = new PageInfo<>(friendCircleMessageList);

        return pageInfo.getList();
    }

    @Override
    public FriendCircleMessage getFriendCircleMessageById(Long id) {
        return friendCircleMessageMapper.getFriendCircleMessageById(id);
    }
}
