package com.changji.cloud.leaf;


import com.changji.cloud.leaf.common.Result;

/**
 * @author leaf
 */
public interface IDGen {

	/**
	 * get
	 * @param key key
	 * @return Result
	 */
	Result get(String key);

	/**
	 * init
	 * @return inited
	 */
	boolean init();

}
