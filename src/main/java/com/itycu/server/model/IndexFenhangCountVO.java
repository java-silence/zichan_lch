package com.itycu.server.model;


public class IndexFenhangCountVO {



    private String name;

    private int resultValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResultValue() {
        return resultValue;
    }

    public void setResultValue(int resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public String toString() {
        return "IndexFenhangCountVO{" +
                "name='" + name + '\'' +
                ", resultValue=" + resultValue +
                '}';
    }
}
