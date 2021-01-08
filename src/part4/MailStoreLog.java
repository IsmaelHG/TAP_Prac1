package part4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * InvocationHandler for the mailstore
 *
 */
public class MailStoreLog implements InvocationHandler {
    private Object impl;

    public MailStoreLog(Object impl) {
        this.impl = impl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        System.out.println("Invoked method: {" +method.getName()+"}");
        return method.invoke(this.impl,args);
    }

}
