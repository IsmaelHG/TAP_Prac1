package redis;

import redis.clients.jedis.*;

public class redistest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.149",6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");

        System.out.println(value);
    }

}
