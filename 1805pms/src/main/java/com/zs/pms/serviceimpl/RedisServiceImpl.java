package com.zs.pms.serviceimpl;

import java.util.List;

import javax.sound.midi.VoiceStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zs.pms.dao.BrandDao;
import com.zs.pms.dao.CodeDao;
import com.zs.pms.dao.TypeDao;
import com.zs.pms.po.TBrand;
import com.zs.pms.po.TCode;
import com.zs.pms.po.TPType;
import com.zs.pms.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	CodeDao cdao;

	@Autowired
	BrandDao bdao;

	@Autowired
	TypeDao tdao;

	@Autowired
	RedisTemplate rs;

	/**
	 * 将码表信息写入redis中
	 * 
	 * @param type
	 */
	public void setTCodes(String type) {
		// 根据类型获得指定码表列表
		List<TCode> list = cdao.queryByType(type);
		rs.opsForValue().set(type, list);
	}

	/**
	 * 从redis中读取码表
	 * 
	 * @param type
	 * @return
	 */
	public List<TCode> getTCodes(String type) {
		return (List<TCode>) rs.opsForValue().get(type);
	}

	@Override
	/**
	 * 将品牌列表写入Redis
	 */
	public void setBrands() {
		// TODO Auto-generated method stub
		rs.opsForValue().set("BRANDS", bdao.queryAll());

	}

	@Override
	public List<TBrand> getBrands() {
		// TODO Auto-generated method stub
		return (List<TBrand>) rs.opsForValue().get("BRANDS");
	}

	/**
	 * 将商品类别写入Redis
	 */
	@Override
	public void setTypes(int pid) {
		// TODO Auto-generated method stub
		rs.opsForValue().set("TYPES" + pid, tdao.queryByPid(pid));

	}

	@Override
	public List<TPType> getTypes(int pid) {
		// TODO Auto-generated method stub
		return (List<TPType>) rs.opsForValue().get("TYPES" + pid);
	}

	@Override
	public String getCodeName(String type, int cid) {
		// TODO Auto-generated method stub

		List<TCode> list = (List<TCode>) rs.opsForValue().get(type);
		for (TCode code : list) {
			if (code.getCid() == cid) {
				return code.getCname();
			}
		}
		return "";
	}

}
