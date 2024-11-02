package com.ruoyi.logistics.mapper;

import java.util.List;
import com.ruoyi.logistics.domain.Wuliu;
import com.ruoyi.logistics.domain.Dispatcher;

/**
 * 物流信息Mapper接口
 * 
 * @author yangwang
 * @date 2024-10-25
 */
public interface WuliuMapper 
{
    /**
     * 查询物流信息
     * 
     * @param wuliuId 物流信息主键
     * @return 物流信息
     */
    public Wuliu selectWuliuByWuliuId(String wuliuId);

    /**
     * 查询物流信息列表
     * 
     * @param wuliu 物流信息
     * @return 物流信息集合
     */
    public List<Wuliu> selectWuliuList(Wuliu wuliu);

    /**
     * 新增物流信息
     * 
     * @param wuliu 物流信息
     * @return 结果
     */
    public int insertWuliu(Wuliu wuliu);

    /**
     * 修改物流信息
     * 
     * @param wuliu 物流信息
     * @return 结果
     */
    public int updateWuliu(Wuliu wuliu);

    /**
     * 删除物流信息
     * 
     * @param wuliuId 物流信息主键
     * @return 结果
     */
    public int deleteWuliuByWuliuId(String wuliuId);

    /**
     * 批量删除物流信息
     * 
     * @param wuliuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWuliuByWuliuIds(String[] wuliuIds);

    /**
     * 批量删除调度员信息
     * 
     * @param wuliuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDispatcherByDispatcherIds(String[] wuliuIds);
    
    /**
     * 批量新增调度员信息
     * 
     * @param dispatcherList 调度员信息列表
     * @return 结果
     */
    public int batchDispatcher(List<Dispatcher> dispatcherList);
    

    /**
     * 通过物流信息主键删除调度员信息信息
     * 
     * @param wuliuId 物流信息ID
     * @return 结果
     */
    public int deleteDispatcherByDispatcherId(String wuliuId);
}
