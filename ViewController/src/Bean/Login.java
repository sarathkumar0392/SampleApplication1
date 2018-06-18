package Bean;

import oracle.binding.*;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ViewObject;

public class Login {
    private String Hidden; 
    public Login() {
        super();
    }


    public void setHidden(String Hidden) {
        this.Hidden = Hidden;
    }
    

    public String getHidden() {
        try{
            DCBindingContainer dc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterBind= dc.findIteratorBinding("LoginViewObjectIterator");
            ViewObject loginProgVo=iterBind.getViewObject();   
            if(loginProgVo != null){
                BindingContainer bindings =  BindingContext.getCurrent().getCurrentBindingsEntry();
                OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
                if(operationBinding != null){
                    operationBinding.execute();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return Hidden;
    }
}
