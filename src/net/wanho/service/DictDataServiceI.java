package net.wanho.service;

import net.wanho.po.DictData;
import net.wanho.po.Role;
import net.wanho.po.User;
import net.wanho.vo.Page;

import java.util.List;

/**
 * 数据字典 服务层接口
 * 
 *  @description:
 *  @author: Mr.songbeichang
 *  @create: 2020-03-29 19:26
 */
public interface DictDataServiceI
{



    /**
     * 分页查询数据字典列表
     * 
     * @param DictData 数据字典信息
     */
    List<DictData> selectDictDataByType(DictData dd);

}
