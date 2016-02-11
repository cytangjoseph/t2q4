/* CDI bean */
/* using BufferedWriter PrintWriter to write to WEB-ING/data.txt */
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
    
    public void writeToFile() {

        System.out.println("now in writeToFile");
        Path aPath;
        String aRealPath;
        BufferedWriter bw = null;
        
        // Convert the string to a byte array
        String s = this.email+"|"+this.pass+"|"+this.name + "|" + this.address + "|" + this.yob + "\r\n";
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
        aRealPath = aFacesContext.getExternalContext().getRealPath("WEB-INF/data.txt");
        rootPath = aFacesContext.getExternalContext().getRealPath("/");
        //aPath = Paths.get(aRealPath);//if file not exist, nullPointerException
        try{
            //create WEB-INF/data.txt if not exist.
            if(aRealPath==null){
               File aFile = new File(rootPath + "WEB-INF/data.txt");
               aFile.createNewFile();
               System.out.println("data.txt not exist. createNewFile");
            }
            //write customer data to WEB-INF/data.txt
            aRealPath = aFacesContext.getExternalContext().getRealPath("WEB-INF/data.txt");
            bw = new BufferedWriter(new FileWriter(aRealPath, true));
            bw.write(s);
            bw.newLine();
            bw.close();
            }
         catch (IOException ex){
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
        writeToFile();
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
      
        FacesContext aFacesContext = FacesContext.getCurrentInstance();
        String aContextName = aFacesContext.getExternalContext().getContextName();//not used
        //--getRealPath: Returns a String containg the real path for a given virtual path
        rootPath = aFacesContext.getExternalContext().getRealPath("/"); 
        resourcePath = aFacesContext.getExternalContext().getResourcePaths("/").toString();
        
        return "resources?faces-redict=true";
    }
}

