package com.bingbinlee.springcloud.micro.common.base;



import com.bingbinlee.springcloud.micro.common.base.model.BaseDataModel;

import java.util.List;
import java.util.Map;

/**
 * DAO操作基类,定义了数据库操作方法
 * @author tanfy 2016-07-11
 *
 * @param <T>
 */
public interface BaseDataDao<T extends BaseDataModel> {
	/**
	 * 添加对象
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public void insert(T t)throws Exception;
	/**
	 * 批量添加信息
	 * @author tanfy
	 * @param tList
	 * @throws Exception
	 * 2016年07月11日 下午6:47:33
	 */
	public void insertAll(List<T> tList) throws Exception;
	/**
	 * 更新
	 * @param t
	 * @throws Exception
	 */
	public void update(T t)throws Exception;
	/**
	 * 通过ID删除实体类
	 * @param id
	 * @param clazz
	 * @throws Exception
	 */
	public void deleteById(String id, Class<T> clazz) throws Exception;

	/**
	 * 删除
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public void deleteEntity(T t)throws Exception;
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public T selectById(String id, Class<T> clazz)throws Exception;


	/**
	 * 获取所有对象
	 * @return
	 * @throws Exception
	 */
	public List<T> getAll(Class<T> clazz) throws Exception;

	/**
	 * 根据isRepeatAllFieldValue查询是否有重复的数据--用于修改和添加
	 * @param isRepeatAllFieldValue
	 * @return
	 * @throws Exception
	 */
	int isRepeat(String isRepeatAllFieldValue, Class<T> clazz) throws Exception;
	/**
	 * 通过本节点id选出其子节点
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<T> selectChildrenById(String id, Class<T> clazz)throws Exception;

	/**
	 * 根据Id更新数据状态或者逻辑删除数据
	 *
	 * 主要修改 enable status 两个字段
	 * @param map
	 * @param clazz
	 * by tanfy 2016-07-29
	 * @throws Exception
	 */
	public void updateDataStatuById(Map<Object, Object> map, Class<T> clazz)	throws Exception ;
	/**
	 * mongoDB 的 分组查询方法
	 * @param map
	 * @param clazz
	 * @throws Exception
     */
	public long getGroupCount(Map<Object, Object> map, Class<T> clazz)	throws Exception ;
}
