package part4;

import part1.MailStore.MailStore;
import part1.MailSystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@Config(store="part1.MemMailStore", log=false)
public class MailSystemConfig extends MailSystem {
    boolean log;

    /**
     * MailSystem constructor.
     *
     */
    public MailSystemConfig() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(
                Boolean.parseBoolean(MailSystemConfig.class.getAnnotations()[1].toString()) ?
                (MailStore) Proxy.newProxyInstance(
                        Class.forName(MailSystemConfig.class.getAnnotations()[0].toString()).getClassLoader(),
                        new Class[] { MailStore.class },
                        new MailStoreLog() )
                : (MailStore) Class.forName(MailSystemConfig.class.getAnnotations()[0].toString()).getDeclaredConstructor().newInstance() );
        log = Boolean.parseBoolean(MailSystemConfig.class.getAnnotations()[1].toString());
    }



}

