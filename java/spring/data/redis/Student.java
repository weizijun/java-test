package spring.data.redis;

import java.io.Serializable;  

public class Student implements Serializable{  
      
    private static final long serialVersionUID = 1L;    
    private String id;    
    private String name;  
    private int money;  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getMoney() {  
        return money;  
    }  
    public void setMoney(int money) {  
        this.money = money;  
    }    
}  