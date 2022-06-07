package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
class ReflectionTest {
    @Test
    void reflection0() {
        final Hello target = new Hello();
        //공통 로직 1 시작
        log.info("start");
        final String result1 = target.callA();
        log.info("result1={}", result1);
        //공통 로직 1 종료

        //공통 로직 2 시작
        log.info("start");
        final String result2 = target.callB();
        log.info("result2={}", result2);
        //공통 로직 2 종료
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //class Information
        final Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        final Hello target = new Hello();

        //callA Method Info
        final Method methodCallA = classHello.getMethod("callA");
        final Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        //callB MethodInfo
        final Method methodCallB = classHello.getMethod("callB");
        final Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //class Information
        final Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        final Hello target = new Hello();

        //callA Method Info
        final Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //callB MethodInfo
        final Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(final Method method, final Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        log.info("result={}", method.invoke(target));
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
