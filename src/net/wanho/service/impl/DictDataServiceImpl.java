package net.wanho.service.impl;

import net.wanho.dao.DictDataDaoI;
import net.wanho.dao.UserDaoI;
import net.wanho.exception.DaoException;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.DictData;
import net.wanho.po.User;
import net.wanho.service.DictDataServiceI;
import net.wanho.service.UserServiceI;
import net.wanho.util.Convert;
import net.wanho.util.StringUtils;
import net.wanho.vo.Page;

import java.util.Date;
import java.util.List;


/**
 * 员工 服务层实现
 * @author songbeichang
 */
public class DictDataServiceImpl implements DictDataServiceI {
	private DictDataDaoI dictDataDao = (DictDataDaoI) ObjectFactory.getObject("dictDataDao");


    @Override
    public List<DictData> selectDictDataByType(DictData dd) {
        return dictDataDao.selectDictDataByType( dd );
    }
}