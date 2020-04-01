package net.wanho.dao.impl;

import net.wanho.dao.DictDataDaoI;
import net.wanho.po.DictData;
import net.wanho.po.Role;
import net.wanho.vo.Page;

import java.util.ArrayList;
import java.util.List;



/**
 * @description:
 * @author: Mr.songbeichang
 * @create: 2020-04-01 00:47
 **/
public class DictDataDaoImpl extends BaseDaoImpl<DictData>  implements DictDataDaoI {
    @Override
    public List<DictData> selectDictDataByType(DictData dd) {
        StringBuilder sql = new StringBuilder(
                "SELECT dd.dict_code dictCode,dd.dict_sort dictSort,dd.dict_label dictLabel,dd.dict_value dictValue,dd.dict_type dictType,dd.css_class cssClass,dd.is_default isDefault,dd.`status`,dd.create_by,dd.create_time,dd.update_by,dd.update_time,dd.remark FROM sys_dict_data AS dd where dict_type='sys_normal_disable'");
        List<Object> params = new ArrayList<>();
        return this.execQuery(sql.toString(), params.toArray());
    }
}
