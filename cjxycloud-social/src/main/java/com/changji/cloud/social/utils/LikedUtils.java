package com.changji.cloud.social.utils;

/**
 * @author 小问号
 * @date 2022/9/20 15
 * description
 */

public class LikedUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";

    //保存朋友圈被点赞数量的key
    public static final String MAP_KEY_FRIEND_CIRCLE_COUNT = "MAP_KEY_FRIEND_CIRCLE_COUNT";

    /**
     * 拼接被点赞的朋友圈id和点赞的人的id作为key。格式 222222::333333
     * @param likedFriendCircleId 被点赞的朋友圈id
     * @param userId 点赞的人的id
     * @return
     */
    public static String getLikedKey(String likedFriendCircleId, String userId){
        StringBuilder builder = new StringBuilder();
        builder.append(likedFriendCircleId);
        builder.append("::");
        builder.append(userId);
        return builder.toString();
    }
}
