package logic;

import model.BeanField;
import model.PrimalType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by igladush on 07.03.16.
 */
public class Initializer {
    private final String ERROR_GET_CLASS = "When I try get class for name i have Exception";
    private final String ERROR_INSTANTIATION_EXCEPTION = "When I try create new Instance I have exception";
    private final String ERROR_ACCESS_INITIALIZATION = "When I try create instance I have Access Exception";


    public Object initialize(String className) {
        Class c = getClassOrNullIFException(className);
        Object instance = createInstanceOrNullIfException(c);
        Deque<BeanField> compositeField = new ArrayDeque<>();

        for (Field field : c.getDeclaredFields()) {
            String name = field.getName();
            String type = field.getType().toString();
            if (!isPrimal(type)) {
                compositeField.add(new BeanField(name, type.substring(6)));
                continue;
            }
            //todo May be make this other method, I think no, but I don't know
            if (containsMethod(c, name)) {
                Method setter = getSetter(c, name);
                List<Object> values = getValue(setter);
                doMethod(setter, values.toArray(), instance);
                System.out.println("Init field " + name);
            }
        }
        for (BeanField s : compositeField) {
            String fieldName = s.getFieldName();
            if (containsMethod(c, fieldName)) {
                Method setter = getSetter(c, fieldName);
                doMethod(setter, new Object[]{initialize(s.getFieldType())}, instance);
                System.out.println("Init field " + s);
            }

        }
        return instance;
    }

    //todo   How return not null
    private Class getClassOrNullIFException(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println(ERROR_GET_CLASS);
            e.printStackTrace();
        }
        return null;
    }

    //todo   How return not null
    private Object createInstanceOrNullIfException(Class className) {
        try {
            return className.newInstance();
        } catch (InstantiationException e) {
            System.out.println(ERROR_INSTANTIATION_EXCEPTION);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(ERROR_ACCESS_INITIALIZATION);
            e.printStackTrace();
        }
        return null;
    }

    //todo make constant
    private void doMethod(Method method, Object[] values, Object c) {
        try {
            method.invoke(c, values);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    //Method return List of value parameter for invoke this method
    private List<Object> getValue(Method m) {
        List<Object> o = new ArrayList<>();
        for (Class p : m.getParameterTypes()) {
            o.add(getNextInstansType(getPrimalType(p)));
        }
        return o;
    }

    //create next value for instance from console
    private Object getNextInstansType(PrimalType p) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the " + p.getWrapsName());
        return p.convertToType(sc.nextLine());
    }

    //return PrimalType for one class if Class not Primal return Exception
    private PrimalType getPrimalType(Class s) {
        for (PrimalType pt : PrimalType.values()) {
            if (pt.isThisType(s.toString())) {
                return pt;
            }
        }
        //todo make const
        throw new IllegalArgumentException();
    }

    //check is Primal type or not
    private boolean isPrimal(String s) {
        for (PrimalType pt : PrimalType.values()) {
            if (pt.isThisType(s)) {
                return true;
            }
        }
        return false;
    }

    //todo think about this and getSetter
    private boolean containsMethod(Class c, String nameField) {
        try {
            getSetter(c, nameField);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }

    //If class have setter for field this return this method,else throw new Exception
    private Method getSetter(Class c, String nameField) {
        String methodName = "set" + Character.toUpperCase(nameField.charAt(0)) + nameField.substring(1);
        for (Method m : c.getDeclaredMethods()) {
            if (methodName.equals(m.getName())) {
                return m;
            }
        }
        throw new IllegalStateException("The class don't have setter for this field");
    }
}
