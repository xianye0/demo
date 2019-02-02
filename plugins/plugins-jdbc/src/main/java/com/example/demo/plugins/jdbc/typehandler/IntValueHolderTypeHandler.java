package com.example.demo.plugins.jdbc.typehandler;

import com.example.demo.plugins.model.enumtype.AbstractIntValueHolder;
import com.example.demo.plugins.model.enumtype.IntValueHolder;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数字类型转换枚举类型初始化类
 *
 * @param <T>
 *
 * @author rock
 * @date 2018/6/12
 */
@MappedTypes(IntValueHolder.class)
@MappedJdbcTypes(value = { JdbcType.NUMERIC, JdbcType.INTEGER})
public class IntValueHolderTypeHandler<T extends Enum<T>> extends BaseTypeHandler<T> {

	/**
	 * 类型
	 */
	private Class<T> type;

	/**
	 * 构造方法
	 * 
	 * @param type
	 *            处理的类型
	 */
	public IntValueHolderTypeHandler(Class<T> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#setNonNullParameter(java.sql.
	 * PreparedStatement, int, java.lang.Object,
	 * org.apache.ibatis.type.JdbcType)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
		ps.setInt(i, ((IntValueHolder) parameter).getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * ResultSet, java.lang.String)
	 */
	@Override
	public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int i = rs.getInt(columnName);

		if (rs.wasNull()) {
			return null;
		}

		return AbstractIntValueHolder.valueOf(type, i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * ResultSet, int)
	 */
	@Override
	public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int i = rs.getInt(columnIndex);

		if (rs.wasNull()) {
			return null;
		}

		return AbstractIntValueHolder.valueOf(type, i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.type.BaseTypeHandler#getNullableResult(java.sql.
	 * CallableStatement, int)
	 */
	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int i = cs.getInt(columnIndex);

		if (cs.wasNull()) {
			return null;
		}

		return AbstractIntValueHolder.valueOf(type, i);
	}

}
