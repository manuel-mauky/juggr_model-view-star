package de.jug_gr.modelviewstar.jsf;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("registerController")
@RequestScoped
public class RegisterController {


    @Inject
    private RegisterModel model;


    public void register(){

        if(!model.getFirstPassword().equals(model.getSecondPassword())){
            addGlobalMessage("The passwords don't match.");
        }else{
            addGlobalMessage("Account was successfully added");
        }
    }

    private void addGlobalMessage(String message){
        System.out.println("message:" + message);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

}
