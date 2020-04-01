package net.wanho.servlet;


import com.alibaba.fastjson.JSON;
import net.wanho.exception.ServiceException;
import net.wanho.factory.ObjectFactory;
import net.wanho.po.Dept;
import net.wanho.po.DictData;
import net.wanho.po.DictType;
import net.wanho.service.DictDataServiceI;
import net.wanho.service.UserServiceI;
import net.wanho.vo.Page;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 字典数据表
 * @description: 查找item.dictValue
 * @author: Mr.songbeichang
 * @create: 2020-04-01 00:30
 **/
@WebServlet("/DictDataServlet")
public class DictDataServlet extends BaseServlet{


    /**
     *	页面所在路径前缀
     *	http://localhost:8086/DictDataServlet?method=dictDataByType
     */
    private String prefix="WEB-INF/admin/system/"+"dictData/";

    /**
     * 工厂，建立对象
     */
    private DictDataServiceI dictDataService=(DictDataServiceI) ObjectFactory.getObject("dictDataService");


    protected void dictDataByType(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        DictData dd = new DictData();
        try {
            List<DictData> list = dictDataService.selectDictDataByType(dd);
            out.println(JSON.toJSON(list));
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
