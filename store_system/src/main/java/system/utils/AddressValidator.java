package system.utils;

import system.common.exceptions.BusinessException;
import system.pojo.OrderAddress;

public class AddressValidator {

    public static void validateAddress(OrderAddress address) {
        if (address == null) {
            throw new BusinessException("地址信息不能为空");
        }

        if (address.getReceiverName() == null || address.getReceiverName().isEmpty()) {
            throw new BusinessException("收件人姓名不能为空");
        }

        if (address.getReceiverPhone() == null || address.getReceiverPhone().isEmpty()) {
            throw new BusinessException("收件人电话不能为空");
        }

        if (!address.getReceiverPhone().matches("^1[3-9]\\d{9}$")) {
            throw new BusinessException("手机号码格式不正确");
        }

        if (address.getProvince() == null || address.getProvince().isEmpty()) {
            throw new BusinessException("省份不能为空");
        }

        if (address.getCity() == null || address.getCity().isEmpty()) {
            throw new BusinessException("城市不能为空");
        }

        if (address.getDetailAddress() == null || address.getDetailAddress().isEmpty()) {
            throw new BusinessException("详细地址不能为空");
        }
    }
}