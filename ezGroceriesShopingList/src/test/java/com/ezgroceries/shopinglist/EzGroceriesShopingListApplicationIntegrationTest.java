package com.ezgroceries.shopinglist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("hsqldb")
class EzGroceriesShopingListApplicationIntegrationTest {
    @Test
    public void main() {
        EzGroceriesShopingListApplication.main(new String[] {});
    }
}
