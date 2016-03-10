package model;

/**
 * Created by CraZy_IVAN on 09.03.16.
 */
public enum PrimalType {
    BYTE("Byte", "byte") {
        @Override
        public Object convertToType(String value) {
            return Byte.valueOf(value);
        }
    },
    SHORT("Short", "short") {
        @Override
        public Object convertToType(String value) {
            return Short.valueOf(value);
        }
    },
    INTEGER("Integer", "int") {
        @Override
        public Object convertToType(String value) {
            return Integer.valueOf(value);
        }
    },
    LONG("Long", "long") {
        @Override
        public Object convertToType(String value) {
            return Long.valueOf(value);
        }
    },
    FLOAT("Float", "float") {
        @Override
        public Object convertToType(String value) {
            return Float.valueOf(value);
        }
    },
    DOUBLE("Double", "double") {
        @Override
        public Object convertToType(String value) {
            return Double.valueOf(value);
        }
    },
    CHARACTER("Character", "char") {
        @Override
        public Object convertToType(String value) {
            return value.charAt(0);
        }
    },
    BOOLEAN("Boolean", "boolean") {
        @Override
        public Object convertToType(String value) {
            return Boolean.valueOf(value);
        }
    },
    STRING("String", "String") {
        @Override
        public Object convertToType(String value) {
            return value;
        }
    };

    private String wrapsName;
    private String primalName;

    public String getWrapsName() {
        return wrapsName;
    }

    public String getPrimalName() {
        return primalName;
    }

    PrimalType(String name, String primalName) {
        this.wrapsName = name;
        this.primalName = primalName;
    }

    public boolean isThisType(String s) {
        String[] temp = s.split("[\\p{P} \\t\\n\\r]");
        return this.wrapsName.equals(temp[temp.length - 1]) || this.primalName.equals(s);
    }

    public abstract Object convertToType(String value);
}
