package com.dal.hrm_management.models.permissions;

public class arrayPermission {
    public static PermissionModel[] permissionModels = {
            new PermissionModel("view_profile",false),
            new PermissionModel("edit_profile",false),
            new PermissionModel("edit_password",false),
            new PermissionModel("view_list_project",false),
            new PermissionModel("view_absence_history",false),
            new PermissionModel("add_new_absence",false),
            new PermissionModel("view_list_employee",false),
            new PermissionModel("search_employee",false),
            new PermissionModel("export_list_employee",false),
            new PermissionModel("view_eployee_CV",false),
            new PermissionModel("view_employee_basic",false),
            new PermissionModel("view_employee_project",false),
            new PermissionModel("view_project_absence_history",false),
            new PermissionModel("export_project_absence_history",false),
            new PermissionModel("approve_absence",false),
            new PermissionModel("reject_absence",false),
            new PermissionModel("export_template_file",false),
            new PermissionModel("import_employee_file",false),
            new PermissionModel("add_new_employee",false),
            new PermissionModel("reset_password_employee",false),
            new PermissionModel("delete_employee",false),
            new PermissionModel("edit_employee_basic",false),
            new PermissionModel("view_employee_absence_history",false),
            new PermissionModel("cancel_emplyee_absence_history",false),
            new PermissionModel("view_holiday_list",false),
            new PermissionModel("edit_holiday_list",false),
            new PermissionModel("add_holiday_list",false),
            new PermissionModel("remove_holiday_list",false)

    };

}
