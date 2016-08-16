package hans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

    @Size(min=2, max=50)
    private String firstname,lastname;
    
    @NotNull
    @Size(min=2, max=50)
    private String username;

    @Size(min=8, max=50)
    private String p1;
    
    @Size(min=8, max=50)
    private String p2;

    @Min(18)
    private Integer age;

    public String getFirstname() {
        return this.firstname;
    }
    
     public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String name) {
        this.username = name;
    }
    
      public String getP1() {
        return this.p1;
    }
    
    public void setP1(String name) {
        this.p1 = name;
    }
    
      public String getP2() {
        return this.p2;
    }
    
    public void setP2(String name) {
        this.p2 = name;
    }
    
    public String getLastname() {
        return this.lastname;
    }

    public void setFirstname(String name) {
        this.firstname = name;
    }
    
    public void setLastname(String name) {
        this.lastname = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.firstname + " " + this.lastname + ", Age: " + this.age + ", Username: " + this.username+ ")";
    }
}
