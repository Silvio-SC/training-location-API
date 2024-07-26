package projeto.training_location.helper;

import org.springframework.beans.BeanUtils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class MyBeanUtils {
    public static void copyNonNullProperties(Object source, Object target) {
        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
        for (PropertyDescriptor targetPd : targetPds) {
            if (targetPd.getWriteMethod() != null) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null && sourcePd.getReadMethod() != null) {
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!readMethod.isAccessible()) {
                            readMethod.setAccessible(true);
                        }
                        Object value = readMethod.invoke(source);
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!writeMethod.isAccessible()) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        }
                    } catch (Exception ex) {
                        // Ignore this property if an exception occurs, log if needed
                        System.err.println("Could not copy property '" + targetPd.getName() + "' from source to target: " + ex.getMessage());
                    }
                }
            }
        }
    }
}