package com.ruoyi.logistics.service;

import java.util.List;
import com.ruoyi.logistics.domain.Wuliu;

/**
 * 物流信息Service接口
 * 
 * @author yangwang
 * @date 2024-10-25
 */
public interface IWuliuService 
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
     * 批量删除物流信息
     * 
     * @param wuliuIds 需要删除的物流信息主键集合
     * @return 结果
     */
    public int deleteWuliuByWuliuIds(String[] wuliuIds);

    /**
     * 删除物流信息信息
     * 
     * @param wuliuId 物流信息主键
     * @return 结果
     */
    public int deleteWuliuByWuliuId(String wuliuId);
}
