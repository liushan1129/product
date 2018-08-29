package com.service.sale.impl;

import com.alibaba.druid.util.StringUtils;
import com.bean.partner.Partner;
import com.bean.products.Products;
import com.bean.sale.Sale;
import com.bean.sale.SaleParams;
import com.bean.type.Stype;
import com.bean.user.User;
import com.exception.ProductsException;
import com.exception.SaleException;
import com.exception.TypeException;
import com.github.pagehelper.PageHelper;
import com.mapper.PartnerMapper;
import com.mapper.SaleMapper;
import com.mapper.TypeMapper;
import com.model.LoginUserModel;
import com.service.sale.SaleService;
import com.utils.OperateBeanUtil;
import com.utils.PageInfo;
import com.utils.Tips;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("saleService")
public class SaleServiceImpl implements SaleService {

    @Resource
    public SaleMapper saleMapper;
    @Resource
    public TypeMapper typeMapper;
    @Resource
    public PartnerMapper partnerMapper;



    @Override
    public Map<String, Object> saveOrUpdateSale(Long id, Sale sale, LoginUserModel model) {

        Map<String, Object> map = new HashMap<>();
        if(model == null) {
            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
            return map;

        }
        if(id == null) {
            map = this.saveSale(model, sale);
        }else {
            map = this.updateSale(id, model, sale);
        }

        return map;
    }

    private Map<String,Object> updateSale(Long id, LoginUserModel model, Sale sale) {
        Map<String, Object> map = new HashMap<>();
        if(id == null) {
            map.put(Tips.REPLY_CODE, Tips.PARAMS_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARAMS_ERROR));
            return map;

        }
        if(StringUtils.isEmpty(sale.getRemark())) {
            map.put(Tips.REPLY_CODE, Tips.SALE_REMARK_NULL);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.SALE_REMARK_NULL));
            return map;
        }

        Sale oldSale = saleMapper.getSaleByField("id", id);

        if(oldSale == null) {
            map.put(Tips.REPLY_CODE, Tips.SALE_NOT_EXIST);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.SALE_NOT_EXIST));
            return map;
        }

        try {
            sale = OperateBeanUtil.setPackBean(sale, oldSale);
        } catch (Exception e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Tips.PRODUCTS_UPDATE_ERROR);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.PARTNER_UPDATE_ERROR));
            return  map;
        }
        try {
            this.validParams(sale);
        } catch (SaleException e) {
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }

        saleMapper.updateSale(id, sale);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));
        map.put("sale", sale);

        return map;
    }

    private Map<String,Object> saveSale(LoginUserModel model, Sale sale) {
        Map<String, Object> map = new HashMap<>();
        User loginUser = model.getUser();
        //判断当前登录用户是否有权限
        //TODO


        //验证参数
        try {
            sale = this.validParams(sale);

        } catch (SaleException e) {
            e.printStackTrace();
            map.put(Tips.REPLY_CODE, Long.valueOf(e.getMessage()));
            map.put(Tips.REPLY_MSG, Tips.getTip(Long.valueOf(e.getMessage())));
            return  map;
        }

        Timestamp nowData = new Timestamp(new Date().getTime());
        sale.setAddTime(nowData);
        //添加人設置為當前登錄用戶的id
        sale.setAddUserId(loginUser.getId());
        saleMapper.addSale(sale);

        map.put("sale", sale);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));


        return map;
    }

    private Sale validParams(Sale sale) throws SaleException {
        if(sale == null) {

            return sale;
        }

        //获取类型id
        Long typeId = sale.getTypeId();
        if(typeId == null) {
            throw new SaleException(Tips.SALE_TYPEID_NULL);
        }
        //根据typeId查询类型是否存在
        Stype stype = typeMapper.getTypeByField("id", typeId);
        if(stype == null) {
            throw new SaleException(Tips.TYPE_NOTEXIST);
        }
        //获取数量
        Integer saleNum = sale.getSaleNum();
        if(saleNum == null) {
            throw new SaleException(Tips.SALE_NUM_NULL);
        }
        //获取销售对象
        Long partnerId = sale.getPartnerId();
        if(partnerId == null) {
            throw new SaleException(Tips.SALE_PARTNER_NULL);
        }
        //根据商户id,查询商户是否存在，并查询是否为销售对象
        Partner partner = partnerMapper.getPartnerByField("id", partnerId);
        if(partner == null) {
            throw new SaleException(Tips.PARTNER_NOT_EXIST);
        }
        if(Tips.PARTNER_OUTTO != partner.getMark()) {
            throw new SaleException(Tips.PARTNER_NOT_OUTTO);
        }
        return sale;
    }

    @Override
    public Map<String, Object> getSaleList(LoginUserModel model, SaleParams saleParams, Long pageNum, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
//        if(model == null) {
//            map.put(Tips.REPLY_CODE, Tips.NO_LOGIN);
//            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_LOGIN));
//            return map;
//        }
//        User loginUser = model.getUser();
        if(pageNum == null ) {
            pageNum = 1L;
        }
        if(pageSize == null) {
            pageSize = 5L;
        }
        //mapper分页
        PageHelper.startPage(Integer.parseInt(pageNum.toString()), Integer.parseInt(pageSize.toString()), true);
        List<Sale> saleList = saleMapper.findSaleListByParams(saleParams);
        if(CollectionUtils.isEmpty(saleList)) {
            map.put(Tips.REPLY_CODE, Tips.NO_DATA);
            map.put(Tips.REPLY_MSG, Tips.getTip(Tips.NO_DATA));
            return map;
        }
        PageInfo<Sale> salePageInfo = new PageInfo(saleList);

        map.put("salePageInfo", salePageInfo);
        map.put(Tips.REPLY_CODE, Tips.ACTION_SUCCESS);
        map.put(Tips.REPLY_MSG, Tips.getTip(Tips.ACTION_SUCCESS));

        return map;
    }
}
