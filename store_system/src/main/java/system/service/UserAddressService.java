package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.UserAddressMapper;
import system.pojo.UserAddress;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    @Qualifier("UserAddressMapper")
    private UserAddressMapper userAddressMapper;

    /**
     * 查询用户所有地址
     * @param userId 用户ID
     * @return 返回用户的所有地址列表
     */
    public Result selectAllByUserId(Integer userId) {
        List<UserAddress> addresses = userAddressMapper.selectByUserId(userId);
        return Result.SUCCESS(addresses);
    }

    /**
     * 查询用户默认地址
     * @param userId 用户ID
     * @return 返回用户的默认地址
     */
    public Result selectDefaultByUserId(Integer userId) {
        UserAddress address = userAddressMapper.selectDefaultByUserId(userId);
        return Result.SUCCESS(address);
    }

    /**
     * 添加用户地址
     * @param userAddress 地址对象
     * @return 操作结果
     */
    public Result insert(UserAddress userAddress) {
        // 如果是设置默认地址，先取消其他默认地址
        if (Boolean.TRUE.equals(userAddress.getIsDefault())) {
            userAddressMapper.cancelDefaultAddresses(userAddress.getUserId());
        }

        int result = userAddressMapper.insertUserAddress(userAddress);
        return result > 0 ? Result.SUCCESS() : Result.FAIL("添加失败");
    }

    /**
     * 更新用户地址
     * @param userAddress 地址对象
     * @return 操作结果
     */
    public Result update(UserAddress userAddress) {
        // 如果是设置默认地址，先取消其他默认地址
        if (Boolean.TRUE.equals(userAddress.getIsDefault())) {
            userAddressMapper.cancelDefaultAddresses(userAddress.getUserId());
        }

        int result = userAddressMapper.updateUserAddress(userAddress);
        return result > 0 ? Result.SUCCESS() : Result.FAIL("更新失败");
    }

    /**
     * 删除用户地址
     * @param userAddressId 地址ID
     * @return 操作结果
     */
    public Result delete(Integer userAddressId) {
        int result = userAddressMapper.deleteById(userAddressId);
        return result > 0 ? Result.SUCCESS() : Result.FAIL("删除失败");
    }

    /**
     * 设置默认地址
     * @param userId 用户ID
     * @param userAddressId 地址ID
     * @return 操作结果
     */
    public Result setDefaultAddress(Integer userId, Integer userAddressId) {
        // 取消所有默认地址
        userAddressMapper.cancelDefaultAddresses(userId);

        // 设置新默认地址
        int result = userAddressMapper.setDefaultAddress(userAddressId);
        return result > 0 ? Result.SUCCESS() : Result.FAIL("设置默认地址失败");
    }
}