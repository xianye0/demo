package com.example.demo.plugins.model.enumtype;

/**
 * 抽象的包含整形值的枚举
 * 
 * @author rock
 * @date 2018/6/12
 */
public abstract class AbstractIntValueHolder<T extends Enum<T>> implements IntValueHolder<T> {

	private AbstractIntValueHolder(){}

	/**
	 * 通过整型值获取枚举
	 * 
	 * @param clazz
	 *            枚举类
	 * @param value
	 *            整型值
	 * @return 枚举
	 * @throws IllegalArgumentException
	 *             当传入的枚举值不支持时
	 * @throws ClassCastException
	 *             当传入的枚举类不是IntValueHolder接口时
	 * @throws NullPointerException
	 *             clazz为null时
	 */
	@SuppressWarnings("rawtypes")
	public static <K extends Enum<K>> K valueOf(Class<K> clazz, int value) {
		for (K entry : clazz.getEnumConstants()) {
			if (((IntValueHolder) entry).getValue() == value) {
				return entry;
			}
		}

		throw new IllegalArgumentException("Unsupported value of " + clazz.getName() + ":" + value);
	}

}
