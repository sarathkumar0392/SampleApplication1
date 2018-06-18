package Bean;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class Registration {
    private String Hidden;
    public Registration() {
        super();
    }

    public void setHidden(String Hidden) {
        this.Hidden = Hidden;
    }

    public String getHidden() {
        try{
            DCBindingContainer dc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding iterBind= dc.findIteratorBinding("EmployeeRegVOIterator");
            ViewObject loginProgVo=iterBind.getViewObject();   
            if(loginProgVo != null){
                Row row = loginProgVo.createRow();
                loginProgVo.insertRow(row);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return Hidden;
    }
    
    public boolean validate(){
        boolean flag = true;
        oracle.jbo.domain.Number empId = null,phnNo = null;
        String empName = null,empEmail = null,password = null,confPass = null;
        try {
           DCBindingContainer dc = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
           DCIteratorBinding iterBind= dc.findIteratorBinding("EmployeeRegVOIterator");
           ViewObject loginProgVo=iterBind.getViewObject();
            if(loginProgVo != null){
                Row row = loginProgVo.getCurrentRow();
                if(row != null){
//                    empId = (oracle.jbo.domain.Number) row.getAttribute("EmpId");
                    phnNo = (oracle.jbo.domain.Number) row.getAttribute("EmpPhnNo");
                    empName = (String) row.getAttribute("EmpName");
                    empEmail = (String) row.getAttribute("EmpEmailId");
                    password = (String) row.getAttribute("Password");
                    confPass = (String) row.getAttribute("ConfirmPassword");
                }
//                if(empId == null){
//                    JSFUtils.addFacesErrorMessage("Employee ID must be entered");
//                    flag = false;
//                }
                if(empName == null){
                    JSFUtils.addFacesErrorMessage("Employee Name must be entered");
                    flag = false;
                }
                if(empEmail == null){
                    JSFUtils.addFacesErrorMessage("Employee Enail ID must be entered");
                    flag = false;
                }
                if(password == null){
                    JSFUtils.addFacesErrorMessage("Password must be entered");
                    flag = false;
                }
                if(confPass == null){
                    JSFUtils.addFacesErrorMessage("Confirm Password must be entered");
                    flag = false;
                }
                if(phnNo == null){
                    JSFUtils.addFacesErrorMessage("Phone No must be entered");
                    flag = false;
                }
                if(password != null && confPass != null && !password.equalsIgnoreCase(confPass)){
                    JSFUtils.addFacesErrorMessage("Password and Confirm Password must be same");
                    flag = false;
                }
            }
       } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public String saveDetails() {
        ApplicationModule appMod = null;
        try {
            if (validate()) {
                   appMod = ADFUtils.getApplicationModuleForDataControl("AppModuleDataControl");
                   if(appMod != null){
                   appMod.getTransaction().commit();
                   }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
