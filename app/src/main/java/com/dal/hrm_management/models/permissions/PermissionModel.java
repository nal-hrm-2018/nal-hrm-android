package com.dal.hrm_management.models.permissions;

public class PermissionModel {
    private String name;
    private boolean isAlow;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlow() {
        return isAlow;
    }

    public void setAlow(boolean alow) {
        isAlow = alow;
    }

    public PermissionModel(String name, boolean isAlow) {

        this.name = name;
        this.isAlow = isAlow;
    }
    //    view_profile;
//    'edit_profile'
//    'edit_password',
//    'view_list_project',
//            'view_absence_history',
//            'add_new_absence',
//            'view_list_employee',
//            'search_employee',
//            'view_eployee_CV',
//            'export_list_employee',
//            'view_employee_basic',
//            'view_employee_project',
//            'view_project_absence_history',
//            'export_project_absence_history',
//            'approve_absence',
//            'reject_absence',
//            'export_template_file',
//            'import_employee_file',
//            'add_new_employee',
//            'reset_password_employee',
//            'delete_employee',
//            'edit_employee_basic',
//            'view_employee_absence_history',
//            'cancel_emplyee_absence_history',
//            'view_holiday_list',
//            'edit_holiday_list',
//            'add_holiday_list',
//            'remove_holiday_list',

}
