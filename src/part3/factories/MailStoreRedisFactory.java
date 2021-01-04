package part3.factories;

import part1.MailStore.MailStore;
import part3.RedisMailStore;
import redis.clients.jedis.Jedis;

public class MailStoreRedisFactory extends MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return RedisMailStore.getRedisMailStore(new Jedis("192.168.1.149",6379));
    }
}
