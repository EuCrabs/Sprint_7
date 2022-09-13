package Methods;

import Courier.*;

import java.util.Random;

public class Courier {

    public static String userLoginGenerator() {
        Random random = new Random();
        return "EugeneK" + random.nextInt(1000);
    }
    public static CourierCreationRequest courierCreationRequest(String login, String password, String firstName) {
        return new CourierCreationRequest(login, password, firstName);
    }

    public static CourierCreationRequest courierCreationWithoutPasswordRequest(String login, String firstName) {
        return new CourierCreationRequest(login, firstName);
    }

    public static CourierLoginRequest courierLoginRequest(String login, String password) {
        return new CourierLoginRequest(login, password);
    }

    public static CourierLoginRequest courierLoginWithoutPasswordRequest(String login) {
        return new CourierLoginRequest(login);
    }
}
