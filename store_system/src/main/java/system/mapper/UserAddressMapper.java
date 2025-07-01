package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.UserAddress;

import java.util.List;

@Repository("UserAddressMapper")
public interface UserAddressMapper {

    /**
     * 插入用户地址
     * @param userAddress 地址对象
     * @return 插入的行数
     */
    int insertUserAddress(UserAddress userAddress);

    /**
     * 根据地址ID查询地址
     * @param userAddressId 地址ID
     * @return 地址对象
     */
    UserAddress selectById(@Param("userAddressId") Integer userAddressId);

    /**
     * 根据用户ID查询所有地址
     * @param userId 用户ID
     * @return 地址列表
     */
    List<UserAddress> selectByUserId(@Param("userId") Integer userId);

    /**
     * 查询用户的默认地址
     * @param userId 用户ID
     * @return 默认地址对象
     */
    UserAddress selectDefaultByUserId(@Param("userId") Integer userId);

    /**
     * 更新用户地址
     * @param userAddress 地址对象
     * @return 更新的行数
     */
    int updateUserAddress(UserAddress userAddress);

    /**
     * 根据地址ID删除地址
     * @param userAddressId 地址ID
     * @return 删除的行数
     */
    int deleteById(@Param("userAddressId") Integer userAddressId);

    /**
     * 根据用户ID删除地址
     * @param userId 用户ID
     * @return 删除的行数
     */
    int deleteByUserId(@Param("userId") Integer userId);

    /**
     * 设置默认地址
     * @param userAddressId 地址ID
     * @return 更新的行数
     */
    int setDefaultAddress(@Param("userAddressId") Integer userAddressId);

    /**
     * 取消用户的所有默认地址
     * @param userId 用户ID
     * @return 更新的行数
     */
    int cancelDefaultAddresses(@Param("userId") Integer userId);
}