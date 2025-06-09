package system.service;


import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import system.common.RedisKey;
import system.common.Result;
import system.mapper.StoreMapper;
import system.mapper.UserMapper;
import system.model.WXAuth;
import system.model.WxUserInfo;
import system.pojo.Store;
import system.pojo.User;
import system.pojo.dto.UserDto;
import system.utils.JWTUtils;
import system.vo.StoreVo;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {

    @Autowired
    WxService wxService;
    @Value("${weixin.appid}")
    private String appid;
    @Value("${weixin.secret}")
    private String secret;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    @Qualifier("UserMapper")
    private UserMapper userMapper;

    @Autowired
    @Qualifier("StoreMapper")
    private StoreMapper storeMapper;

    /**
     * 用户微信一键登录
     * @param wxAuth（微信数据）
     * @return 用户token及其他数据
     */
    public Result authLogin(WXAuth wxAuth) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", wxAuth.getCode());
        String res = HttpUtil.get(replaceUrl);
        String uuid = UUID.randomUUID().toString();
        //将用户数据存入redis
        stringRedisTemplate.opsForValue().set(RedisKey.WX_SESSION_ID + uuid, res, 30, TimeUnit.MINUTES);
        wxAuth.setSessionId(uuid);
        System.out.println("sessionID:" + uuid);
        try {
            String wxRes = wxService.wxDecrypt(wxAuth.getEncryptedData(), wxAuth.getSessionId(), wxAuth.getIv());
             log.info("用户信息："+wxRes);
            //用户信息：{"openId":"o20","nickName":"juana","gender":2,"language":"zh_CN","city":"Changsha","province":"Hunan","country":"China","avatarUrl":"头像链接","watermark":{"timestamp":dsfs,"appid":"应用id"}}
            WxUserInfo wxUserInfo = JSON.parseObject(wxRes, WxUserInfo.class);
            //调用redis，从其中获取OpenId
            String json = stringRedisTemplate.opsForValue().get(RedisKey.WX_SESSION_ID + uuid);
            JSONObject jsonObject = JSON.parseObject(json);
            String openid = (String) jsonObject.get("openid");
            wxUserInfo.setOpenid(openid);
            // 业务操作：你可以在这里利用数据 对数据库进行查询， 如果数据库中没有这个数据，就添加进去，即实现微信账号注册
              System.out.println(wxUserInfo);
            // 如果是已经注册过的，就利用数据，生成jwt 返回token，实现登录状态
            User user = userMapper.selectByOpenId(wxUserInfo.getOpenid());
            UserDto userDto = new UserDto();
            userDto.from(wxUserInfo);
            if (user == null) {
                return this.register(userDto);
            } else {
                userDto.setUserId(user.getUserId());
                return this.login(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.FAIL();
    }

    /**
     * 登录数据存储redis
     * @param userDto（部分用户数据）
     * @return 用户token及其他数据
     */
    private Result login(UserDto userDto) {
        String token = JWTUtils.sign(Long.valueOf(userDto.getUserId()));
        userDto.setToken(token);
        userDto.setUserOpenId(null);
        userDto.setUserUnionId(null);
        //把token存入redis
        stringRedisTemplate.opsForValue().set(RedisKey.TOKEEN + token, JSON.toJSONString(userDto), 7, TimeUnit.DAYS);
        return Result.SUCCESS(userDto);
    }

    /**
     * 创建用户存储于数据库
     * @param userDto（部分用户数据）
     * @return 调用登录函数
     */
    private Result register(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userMapper.insert(user);
        userDto.setUserId(userMapper.selectByOpenId(userDto.getUserOpenId()).getUserId());
        return this.login(userDto);
    }

    /**
     * 申请创建商店
     * @param store（用户填写的商店数据)
     * @return 如果商店不存在则创建成功，否则失败
     */
    public Result addStore(Store store) {
        store.setStoreState("verify");
        StoreVo storeVo = new StoreVo();
        BeanUtils.copyProperties(store, storeVo);
        if (storeMapper.selectAll(storeVo)==null){
            if (storeMapper.insertStore(store)!=0){
                return Result.SUCCESS();
            }else {
                return Result.FAIL("创建失败");
            }
        }else {
            return Result.FAIL("该商店已经申请");
        }



    }
}
