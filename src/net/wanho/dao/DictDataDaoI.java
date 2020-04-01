package net.wanho.dao;

import net.wanho.po.DictData;
import net.wanho.vo.Page;

import java.util.List;

public interface DictDataDaoI {
    List<DictData> selectDictDataByType(DictData dd);
}
