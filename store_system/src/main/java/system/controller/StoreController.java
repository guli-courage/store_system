package system.controller;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import system.common.RedisKey;
import system.common.Result;
import system.pojo.Store;
import system.pojo.dto.UserDto;
import system.service.StoreService;
import system.vo.StoreVo;

@RestController
@RequestMapping("store")
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 根据用户ID查询其所有的商店
     * @param userDto 只传用户token
     */
    @PostMapping("/searchStore")
    public Result searchStore(@RequestBody UserDto userDto) {
        String json = stringRedisTemplate.opsForValue().get(RedisKey.TOKEEN + userDto.getToken());
        JSONObject jsonObject = JSON.parseObject(json);
        Integer userId = Integer.valueOf(jsonObject.getString("userId"));
        StoreVo storeVo = new StoreVo();
        storeVo.setStoreUserId(userId);
        return storeService.selectAllByVO(storeVo);
    }

    /**
     * 更新商店信息并进入审核状态
     * @param store（修改后的商店信息）
     */
    @PostMapping("/updateStore")
    public Result updateStore(@RequestBody Store store) {
        return storeService.update(store);
    }
}
