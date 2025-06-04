package system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.pojo.Store;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVo {
    private String storeName;
    private String storeAvatar;
    private String storeState;
    private String storeDescription;
    private String storeAddress;
    private Integer storeUserId;

    public void from(Store store){
        this.storeName = store.getStoreName();
        this.storeAvatar = store.getStoreAvatar();
        this.storeState = store.getStoreState();
        this.storeDescription = store.getStoreDescription();
        this.storeAddress = store.getStoreAddress();
        this.storeUserId = store.getStoreUserId();
    }
}
