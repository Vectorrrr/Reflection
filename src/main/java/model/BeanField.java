package model;

/**
 * Created by igladush on 10.03.16.
 */
public class BeanField {
    private String fieldName;
    private String fieldType;

    public BeanField() {
    }

    public BeanField(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
