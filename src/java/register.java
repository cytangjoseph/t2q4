/* CDI bean */

import javax.inject.Named;//CDI bean
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;


@Named
@SessionScoped//scope; user specific
public class register implements Serializable {
    private String email;
    private String pass;
    private String name;
    private String address;
    private String dob;
    
    //getters and setters
 
    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}
    
    public String getPass(){return pass;}
    public void setPass(String pass) {this.pass = pass;}
    
    public String getName(){return name;}
    public void setName(String name) {this.name = name;}
    
    public String getAddress(){return address;}
    public void setAddress(String address) {this.address = address;}
    
    public String getDob(){return dob;}
    public void setDob(String dob) {this.dob = dob;}
    
    public String submit1(){
      System.out.println("continue button pressed.");
        return "part2?faces-redirect=true";
    }
    
    public String submit2(String email, String pass, String name, String address, String dob){
        this.setEmail(email);
        this.setPass(pass);
        this.setName(name);
        this.setAddress(address);
        this.setDob(dob);
        System.out.println("email: " + getEmail() + ", password: " + pass + ", name: " + getName() + ", address: " + address + ", dob: " + getDob());
        setEmail(null);
        setPass(null);
        setName(null);
        setAddress(null);
        setDob(null);
        return"index?faces-redirect=true";
    }
}
