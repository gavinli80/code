package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ProductDao;
import com.zs.pms.dao.SkuDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TProduct;
import com.zs.pms.po.TSku;
import com.zs.pms.service.ProductService;
import com.zs.pms.service.RedisService;

@Service
@Transactional // 开启事务
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao pdao;

	@Autowired
	SkuDao sdao;

	@Autowired
	RedisService rs;

	@Override
	public List<TProduct> queryAll() {
		// TODO Auto-generated method stub
		return pdao.queryAll();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int addProduct(TProduct product) throws AppException {
		// TODO Auto-generated method stub
		// 新增商品
		int rtn = pdao.insert(product);
		// 新增sku库存 根据颜色、尺码、材质
		// 颜色数组 1,2,3
		String cls[] = product.getColors().split(",");
		// 尺码数组
		String sizs[] = product.getSizes().split(",");
		// 材质数组
		String feas[] = product.getFeatures().split(",");

		for (String c : cls) {

			for (String s : sizs) {

				for (String f : feas) {
					// 新增sku
					TSku sku = new TSku();
					sku.setColor(Integer.parseInt(c)); // 颜色id
					sku.setCreator(product.getCreator()); // 创建者
					sku.setFeature(Integer.parseInt(f));// 材质id
					sku.setLimi(0); // 购买限制 默认0
					sku.setPid(product.getId());// 商品id
					sku.setPrice(0); // 市场价
					sku.setRecont(0);// 剩余数量
					sku.setSafcont(0);// 安全库存
					sku.setSellcont(0);// 销售数量
					sku.setSellcost(0);// 售价
					sku.setSiz(Integer.parseInt(s));// 尺码id
					//存中文
					sku.setSkuname(product.getPname() + " " + rs.getCodeName("C", sku.getColor()) + " "
							+ rs.getCodeName("S", sku.getSiz()) + " " + rs.getCodeName("F", sku.getFeature()));// sku名称
					sku.setSkupic("xxx.jpg");
					sku.setTrancost(0);// 运费
					sku.setWpos("5号仓库");// 仓库号
					// 新增
					sdao.insert(sku);

				}

			}

		}

		// 返回主键
		return product.getId();
	}

}
