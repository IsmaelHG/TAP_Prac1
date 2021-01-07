package part3.factories;

import part1.MailStore.MailStore;
import part3.RedisMailStore;
import redis.clients.jedis.Jedis;

/***
 *
 * MailStoreFactory with redis implementation. The server must be 127.0.0.1:6379
 *
 */
public class MailStoreRedisFactory extends MailStoreFactory {
    @Override
    public MailStore createMailStore() {
        return RedisMailStore.getRedisMailStore(new Jedis("192.168.1.149",6379));
    }
}
