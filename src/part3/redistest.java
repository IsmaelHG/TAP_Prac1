package part3;

import redis.clients.jedis.*;

public class redistest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.149",6379);
        jedis.append("fodo", "bar");
        String value = jedis.get("fodo");

        System.out.println(value);
    }

}
