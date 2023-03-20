package com.hmdp.controller;


import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.ShopType;
import com.hmdp.service.IShopTypeService;
import jodd.util.StringUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TYPE;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/shop-type")
public class ShopTypeController {
    @Resource
    private IShopTypeService typeService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("list")
    public Result queryTypeList() {
        String cacheShopTyp = CACHE_SHOP_TYPE;
        if (StringUtil.isBlank(cacheShopTyp)) {
            String jsonType = stringRedisTemplate.opsForValue().get(cacheShopTyp);
            List<ShopType> typeList = JSONUtil.toList(jsonType, ShopType.class);
            return Result.ok(typeList);
        } else {
            List<ShopType> typeList = typeService
                    .query().orderByAsc("sort").list();
            stringRedisTemplate.opsForValue().set(cacheShopTyp, JSONUtil.toJsonStr(typeList));
            return Result.ok(typeList);
        }
    }
}
