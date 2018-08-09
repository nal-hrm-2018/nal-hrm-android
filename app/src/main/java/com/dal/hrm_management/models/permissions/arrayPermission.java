package com.dal.hrm_management.models.permissions;

import com.dal.hrm_management.models.profile.Permission;

public class arrayPermission {
    public static permissionModel[] permissionModels = {
            new permissionModel("view_profile",false),
            new permissionModel("edit_profile",false),
            new permissionModel("edit_password",false),
            new permissionModel("view_list_project",false),
            new permissionModel("view_absence_history",false),
            new permissionModel("add_new_absence",false),
            new permissionModel("view_list_employee",false),
            new permissionModel("search_employee",false),
            new permissionModel("export_list_employee",false),
            new permissionModel("view_eployee_CV",false),
            new permissionModel("view_employee_basic",false),
            new permissionModel("view_employee_project",false),
            new permissionModel("view_project_absence_history",false),
            new permissionModel("export_project_absence_history",false),
            new permissionModel("approve_absence",false),
            new permissionModel("reject_absence",false),
            new permissionModel("export_template_file",false),
            new permissionModel("import_employee_file",false),
            new permissionModel("add_new_employee",false),
            new permissionModel("reset_password_employee",false),
            new permissionModel("delete_employee",false),
            new permissionModel("edit_employee_basic",false),
            new permissionModel("view_employee_absence_history",false),
            new permissionModel("cancel_emplyee_absence_history",false),
            new permissionModel("view_holiday_list",false),
            new permissionModel("edit_holiday_list",false),
            new permissionModel("add_holiday_list",false),
            new permissionModel("remove_holiday_list",false)

    };

}
