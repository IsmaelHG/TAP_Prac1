package part3.factories;

import part1.MailStore.MailStore;
import part3.MailStoreRedis;
import redis.clients.jedis.Jedis;

public class MailStoreRedisFactory extends MailStoreFactory{
    @Override
    public MailStore createMailStore() {
        return MailStoreRedis.getRedisMailStore(new Jedis("192.168.1.149",6379));
    }
}
