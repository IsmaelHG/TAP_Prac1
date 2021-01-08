package part4;

import part1.MailStore.MailStore;
import part1.MailSystem;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

@ConfigMail(store="part1.MailStore.MemMailStore", log=true)
public class MailSystemConfig extends MailSystem {

    /**
     * MailSystem constructor.
     * MailStore will be the indicated in "store" field from annotation and the boolean field "log" will
     * activate or deactivate loging capabilities of the mail store.
     */
    public MailSystemConfig() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        super(
                ((ConfigMail)MailSystemConfig.class.getAnnotations()[0]).log() ?
                (MailStore) Proxy.newProxyInstance(
                        Class.forName(((ConfigMail)MailSystemConfig.class.getAnnotations()[0]).store()).getClassLoader(),
                        new Class[] { MailStore.class },
                        new MailStoreLog() )
                : (MailStore) Class.forName(((ConfigMail)MailSystemConfig.class.getAnnotations()[0]).store()).getDeclaredConstructor().newInstance() );
    }



}

