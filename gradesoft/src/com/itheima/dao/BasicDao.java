package com.itheima.dao;

public interface BasicDao<T> {
	/**
	 * 添加
	 */
	void add();
/**
 * 通过id获取实体
 * @param id
 * @return
 */
	T getById(String id);
	
	/**
	 * 删除实体
	 * @param t
	 * @return
	 */
	boolean delete(String  id);
	/**
	 * 修改实体
	 * @param t
	 * @return
	 */
	T update(T t);
}
