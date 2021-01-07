package part3;

import part1.MailStore.MailStore;
import part1.Message;
import redis.clients.jedis.Jedis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/***
 *
 * MailStore implementation via Redis.
 * Messages will be sent and get from a Redis server.
 *
 * This class is Singleton
 */
public class RedisMailStore implements MailStore {
    private final Jedis conn;
    private static RedisMailStore mymailstore;

    /***
     *
     * Gets the current MailStore instance. If it doesn't exist, it will create one
     *
     * @param connect Jedis connection
     * @return RedisMailStore
     */
    public static RedisMailStore getRedisMailStore (Jedis connect) {
        if (mymailstore==null) {
            mymailstore = new RedisMailStore(connect);
        }
        return mymailstore;
    }


    private RedisMailStore(Jedis connect) {
        this.conn=connect;
    }


    @Override
    public void SendMail(Message mail) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.conn.lpush(mail.getReceiver(),mail.getSubject()+";"+mail.getSender()+";"+mail.getBody()+";"+mail.getCreationtime().format(formatter));


    }

    @Override
    public List<Message> GetMail(String username) {
        List<Message> messagelist = new LinkedList<>();
        List<String> messagestringlist = this.conn.lrange(username, 0, -1);

        for (String s : messagestringlist) {
            StringTokenizer tk = new StringTokenizer(s,";");
            String subject = tk.nextToken();
            String sender = tk.nextToken();
            String body = tk.nextToken();
            String date = tk.nextToken();
            messagelist.add(new Message(subject, sender, username ,body, LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        }

        return messagelist;
    }
}
