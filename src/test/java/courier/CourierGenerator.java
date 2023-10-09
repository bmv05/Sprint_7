package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static Courier random() {
        return new Courier(RandomStringUtils.randomAlphabetic(5, 10), "12345", "Elena");
    }

    public static Courier withoutLogin() {
        return new Courier("", "12345", "Elena");
    }

    public static Courier withoutPassword() {
        return new Courier(RandomStringUtils.randomAlphabetic(5, 10), "", "Elena");
    }
}
