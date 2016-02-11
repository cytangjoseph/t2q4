/* CDI bean */

import javax.inject.Named;//CDI bean
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.nio.file.*;
import java.io.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.FacesContext;

@Named
@SessionScoped//scope; user specific
public class register implements Serializable{
    private String email;
    private String pass;
    private String name;
    private String address;
    private String yob;
    private String rootPath;
    private String resourcePath;
    
    public void writeToFile(String email, String pass, String name, String address, String yob) {

        String aRealPath;
        Boolean bool;
        File aFile=null;
        // Convert the string to a byte array
        String s = email + "|" + pass + "|" + name + "|" + address + "|" + yob + "\r\n";
        byte data[] = s.getBytes();
        
        //--String path2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath(
        //        "/WEB-INF/customerdata.txt)");
        /**  https://javaserverfaces.java.net/docs/2.3/javadocs/javax/faces/context/ExternalContext.html */
        //FacesContext: Contains all of the per-request state information related to the
        //  processing of a single JavaServer Faces request, and the rendering of the corresponding response
        //getCurrentInstance: return the FacesContext instance for the request that is being processed by the current thread
        //--ExternalContext: This class allows the Faces API to be unaware of the nature of its containing application environment
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        String aContextName = aFacesContext.getExternalContext().getContextName();//not used
        //--getRealPath: Returns a String containg the real path for a given virtual path
        //to obtain real path of /WEB-INF
        aRealPath = aFacesContext.getExternalContext().getRealPath("WEB-INF");
        //to make the real path for the WEB-INF/data.txt
        aFile = new File(aRealPath + File.separator + "data.txt");
        
        try{ 
            FileWriter fw = new FileWriter(aFile,true);//create file if not exist, append mode
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.append(s);
            pw.close();
            bw.close();
            fw.close();
        } catch(IOException ex) {
            System.err.println(ex);
        }
    }
    
    //getters and setters
 
    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}
    
    public String getPass(){return pass;}
    public void setPass(String pass) {this.pass = pass;}
    
    public String getName(){return name;}
    public void setName(String name) {this.name = name;}
    
    public String getAddress(){return address;}
    public void setAddress(String address) {this.address = address;}
    
    public String getYob(){return yob;}
    public void setYob(String yob) {this.yob = yob;}
    
    public String getRootPath(){return rootPath;}
    public void setRootPath(String rootPath) {this.rootPath = rootPath;}
    
    public String getResourcePath(){return resourcePath;}
    public void setResourcePath(String resourcePath) {this.resourcePath = resourcePath;}
    
    public String submit1(){
      System.out.println("continue button pressed.");
        return "part2?faces-redirect=true";
    }
    
    public String submit2(String email, String pass, String name, String address, String yob){
        this.setEmail(email);
        this.setPass(pass);
        this.setName(name);
        this.setAddress(address);
        this.setYob(yob);
        System.out.println("email: " + getEmail() + ", password: " + pass + ", name: " + getName() + ", address: " + address + ", yob: " + getYob());
        writeToFile(email, pass, name, address, yob);
        return"success?faces-redirect=true";
    }
       
    public String start(){
        setEmail(null);
        setPass(null);
        setName(null);
        setAddress(null);
        setYob(null);
        return"index?faces-redirect=true";
    }
    
     public String listResources(){
        ;
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        String aContextName = aFacesContext.getExternalContext().getContextName();//not used
        //--getRealPath: Returns a String containg the real path for a given virtual path
        rootPath = aFacesContext.getExternalContext().getRealPath("/"); 
        resourcePath = aFacesContext.getExternalContext().getResourcePaths("/").toString();
        
        return "resources?faces-redict=true";
    }
}

