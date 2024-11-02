package com.ruoyi.logistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.logistics.domain.Dispatcher;
import com.ruoyi.logistics.mapper.WuliuMapper;
import com.ruoyi.logistics.domain.Wuliu;
import com.ruoyi.logistics.service.IWuliuService;

/**
 * 物流信息Service业务层处理
 * 
 * @author yangwang
 * @date 2024-10-25
 */
@Service
public class WuliuServiceImpl implements IWuliuService 
{
    @Autowired
    private WuliuMapper wuliuMapper;

    /**
     * 查询物流信息
     * 
     * @param wuliuId 物流信息主键
     * @return 物流信息
     */
    @Override
    public Wuliu selectWuliuByWuliuId(String wuliuId)
    {
        return wuliuMapper.selectWuliuByWuliuId(wuliuId);
    }

    /**
     * 查询物流信息列表
     * 
     * @param wuliu 物流信息
     * @return 物流信息
     */
    @Override
    public List<Wuliu> selectWuliuList(Wuliu wuliu)
    {
        return wuliuMapper.selectWuliuList(wuliu);
    }

    /**
     * 新增物流信息
     * 
     * @param wuliu 物流信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWuliu(Wuliu wuliu)
    {
        int rows = wuliuMapper.insertWuliu(wuliu);
        insertDispatcher(wuliu);
        return rows;
    }

    /**
     * 修改物流信息
     * 
     * @param wuliu 物流信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWuliu(Wuliu wuliu)
    {
        wuliuMapper.deleteDispatcherByDispatcherId(wuliu.getWuliuId());
        insertDispatcher(wuliu);
        return wuliuMapper.updateWuliu(wuliu);
    }

    /**
     * 批量删除物流信息
     * 
     * @param wuliuIds 需要删除的物流信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWuliuByWuliuIds(String[] wuliuIds)
    {
        wuliuMapper.deleteDispatcherByDispatcherIds(wuliuIds);
        return wuliuMapper.deleteWuliuByWuliuIds(wuliuIds);
    }

    /**
     * 删除物流信息信息
     * 
     * @param wuliuId 物流信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWuliuByWuliuId(String wuliuId)
    {
        wuliuMapper.deleteDispatcherByDispatcherId(wuliuId);
        return wuliuMapper.deleteWuliuByWuliuId(wuliuId);
    }

    /**
     * 新增调度员信息信息
     * 
     * @param wuliu 物流信息对象
     */
    public void insertDispatcher(Wuliu wuliu)
    {
        List<Dispatcher> dispatcherList = wuliu.getDispatcherList();
        String wuliuId = wuliu.getWuliuId();
        if (StringUtils.isNotNull(dispatcherList))
        {
            List<Dispatcher> list = new ArrayList<Dispatcher>();
            for (Dispatcher dispatcher : dispatcherList)
            {
                dispatcher.setDispatcherId(wuliuId);
                list.add(dispatcher);
            }
            if (list.size() > 0)
            {
                wuliuMapper.batchDispatcher(list);
            }
        }
    }
}
