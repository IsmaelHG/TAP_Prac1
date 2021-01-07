package part4;

import part1.MailStore.MailStore;
import part1.MailSystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@Config(store="part1.MemMailStore", log=false)
/**
 *
 * MailSystem implementation with dependency injection mechanisms by using an annotation
 *
 */
public class MailSystemConfig extends MailSystem {

    /**
     * MailSystem constructor.
     * MailStore will be the indicated in "store" field from annotation and the boolean field "log" will
     * activate or deactivate loging capabilities of the mail store.
     */
    public MailSystemConfig() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(
                Boolean.parseBoolean(MailSystemConfig.class.getAnnotations()[1].toString()) ?
                (MailStore) Proxy.newProxyInstance(
                        Class.forName(MailSystemConfig.class.getAnnotations()[0].toString()).getClassLoader(),
                        new Class[] { MailStore.class },
                        new MailStoreLog() )
                : (MailStore) Class.forName(MailSystemConfig.class.getAnnotations()[0].toString()).getDeclaredConstructor().newInstance() );
    }



}

