package redis;

import redis.clients.jedis.*;

public class redistest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.append("fodo", "bar");
        String value = jedis.get("fodo");

        System.out.println(value);
    }

}
