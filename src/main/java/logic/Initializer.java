package logic;

import model.Test;
import org.omg.Dynamic.Parameter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by igladush on 07.03.16.
 */
public class Initializer {
    private final String ERROR_GET_CLASS="When I try get class for name i have Exception";
    private final String ERROR_INSTANTIATION_EXCEPTION="When I try create new Instance I have exception";
    private final String ERROR_ACCESS_INITIALIZATION="When I try create instance I have Access Exception";

    public void initialaize(){
        Class c=null ;
        Object object=null;

        try {
             c=Class.forName("model.Test");
             object=c.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println(ERROR_GET_CLASS);
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println(ERROR_INSTANTIATION_EXCEPTION);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println();
            e.printStackTrace();
        }
        Test t=(Test)object;
        Field[] fields=c.getDeclaredFields();
        Method[] methods=c.getDeclaredMethods();
        for(Method m: methods){
            List<Object>param=getValue(m);
            try {
                m.invoke(t,param.toArray());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    //Method return List of value parameter for invoke this method
    private List<Object> getValue(Method m){
        Scanner sc=new Scanner(System.in);
        List<Object> o=new ArrayList<Object>();
        for(Class c:m.getParameterTypes()){
            String[] temp=c.getName().toString().split("[\\p{P} \\t\\n\\r]");
            String typeName=temp[temp.length-1];
            switch(typeName){
                case("Integer"):
                    System.out.println("Input next integer");
                    o.add(Integer.valueOf(sc.nextInt()));
                    break;
                case("String"):
                    System.out.println("Input string");
                    o.add(sc.nextLine());
            }
        }
    return o;
    }
}
