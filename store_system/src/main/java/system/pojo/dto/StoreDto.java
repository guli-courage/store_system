package system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.pojo.Store;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDto {
    private String token;
    private Store store;
}
