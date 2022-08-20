package com.changji.cloud.leaf.feign;

import com.changji.cloud.api.leaf.feign.SegmentFeignClient;
import com.changji.cloud.common.core.response.ServerResponseEntity;
import com.changji.cloud.leaf.common.Result;
import com.changji.cloud.leaf.common.Status;
import com.changji.cloud.leaf.exception.LeafServerException;
import com.changji.cloud.leaf.exception.NoKeyException;
import com.changji.cloud.leaf.service.SegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author 小问号
 * @date 2022/7/23 17
 * discription
 */

@RestController
public class SegmentFeignController implements SegmentFeignClient {

	private static final Logger logger = LoggerFactory.getLogger(SegmentFeignController.class);

	@Autowired
	private SegmentService segmentService;

	@Override
	public ServerResponseEntity<Long> getSegmentId(String key) {
		return ServerResponseEntity.success(get(key, segmentService.getId(key)));
	}


	private Long get(String key, Result id) {
		Result result;
		if (key == null || key.isEmpty()) {
			throw new NoKeyException();
		}
		result = id;
		if (Objects.equals(result.getStatus(), Status.EXCEPTION)) {
			throw new LeafServerException(result.toString());
		}
		return result.getId();
	}
}
