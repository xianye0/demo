package com.example.demo.plugins.model.enumtype;

/**
 * 保存整型数枚举的接口
 * 
 * @author rock
 * @date 2018/6/12
 *
 */
public interface IntValueHolder<T extends Enum<T>> {

	/**
	 * @return 获取数值
	 */
	int getValue();

}
