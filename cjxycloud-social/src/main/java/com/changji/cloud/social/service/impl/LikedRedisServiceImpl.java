package com.changji.cloud.social.service.impl;

import com.changji.cloud.common.redis.service.RedisService;
import com.changji.cloud.social.dto.LikedCountDTO;
import com.changji.cloud.social.enums.LikedStatusEnum;
import com.changji.cloud.social.model.FriendCircleLike;
import com.changji.cloud.social.service.LikedRedisService;
import com.changji.cloud.social.utils.LikedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 小问号
 * @date 2022/9/21 09
 * description
 */
@Service
public class LikedRedisServiceImpl implements LikedRedisService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveLiked2Redis(Long likedFriendCircleId, Long userId) {
        String key = LikedUtils.getLikedKey(String.valueOf(likedFriendCircleId), String.valueOf(userId));
        redisTemplate.opsForHash().put(LikedUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(Long likedFriendCircleId, Long userId) {
        String key = LikedUtils.getLikedKey(String.valueOf(likedFriendCircleId), String.valueOf(userId));
        redisTemplate.opsForHash().put(LikedUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(Long likedFriendCircleId, Long userId) {
        String key = LikedUtils.getLikedKey(String.valueOf(likedFriendCircleId), String.valueOf(userId));
        redisTemplate.opsForHash().delete(LikedUtils.MAP_KEY_USER_LIKED, key);
    }

    @Override
    public void incrementLikedCount(Long likedFriendCircleId) {
        redisTemplate.opsForHash().increment(LikedUtils.MAP_KEY_FRIEND_CIRCLE_COUNT, likedFriendCircleId, 1);
    }

    @Override
    public void decrementLikedCount(Long likedFriendCircleId) {
        redisTemplate.opsForHash().increment(LikedUtils.MAP_KEY_FRIEND_CIRCLE_COUNT, likedFriendCircleId, -1);
    }

    @Override
    public List<FriendCircleLike> getLikedDataFromRedis() {

        Cursor<Map.Entry<Object, Object>> cursor =redisService.ScanCacheMapValue(LikedUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<FriendCircleLike> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedFriendCircleId，userId
            String[] split = key.split("::");
            Long likedFriendCircleId = Long.valueOf(split[0]);
            Long userId = Long.valueOf(split[1]);
            Integer value = (Integer) entry.getValue();

            //组装成 FriendCircleLike 对象
            FriendCircleLike friendCircleLike = new FriendCircleLike(likedFriendCircleId, userId, value);
            list.add(friendCircleLike);

            //存到 list 后从 Redis 中删除
            redisService.deleteCacheMapValue(LikedUtils.MAP_KEY_USER_LIKED, key);
        }

        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {

        Cursor<Map.Entry<Object, Object>> cursor = redisService.ScanCacheMapValue(LikedUtils.MAP_KEY_FRIEND_CIRCLE_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            String key = (String)map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisService.deleteCacheMapValue(LikedUtils.MAP_KEY_FRIEND_CIRCLE_COUNT, key);
        }
        return list;
    }
}
