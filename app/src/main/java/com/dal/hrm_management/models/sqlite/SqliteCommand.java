package com.dal.hrm_management.models.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.PixelCopy;

import java.security.PublicKey;

public class SqliteCommand extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "HRM";
    public static final int DATABASE_VERSION = 1;
    //table employee
    public static final String TABLE_EMPLOYEE = "employee";
    public static final String COL_EMP_IDEMPLOYEE = "idEmployee";
    public static final String COL_EMP_email = "email";
    public static final String COL_EMP_NAME = "nameEmployee";
    public static final String COL_EMP_BIRTHDAY = "birthday";
    public static final String COL_EMP_GENDER = "gender";
    public static final String COL_EMP_MOBILE = "mobile";
    public static final String COL_EMP_ADDRESS = "address";
    public static final String COL_EMP_MARITALSTATUS = "MaritalStatus";
    public static final String COL_EMP_STARTWORKDAY = "StartWorkDay";
    public static final String COL_EMP_ENDWORKDAY = "EndWorkDay";
    public static final String COL_EMP_CURRICULUM_VITAE = "curriculum_vitae";
    public static final String COL_EMP_COMPANY = "Company";
    public static final String COL_EMP_AVATAR = "avatar";
    public static final String COL_EMP_IDEMPLOYEETYPE = "idEmployeeType";
    public static final String COL_EMP_IDROLE = "idRole";
    public static final String COL_EMP_ISMANAGER = "isManager";
    public static final String COL_EMP_SALARYID = "salaryId";
    public static final String COL_EMP_UPDATEDAT = "updatedAt";
    public static final String COL_EMP_CREATEDAT = "createdAt";
    public static final String COL_EMP_EMPLOYEE = "employee";
    //Table genderDTO
    public static final String TABLE_GENDERDTO = "genderDTO";
    public static final String COL_GEN_GENDER ="gender";
    public static final String COL_GEN_NAMEGENDER ="nameGender";
    //Table maritalStatusDTO
    public static final String TABLE_MARITALSTATUSDTO = "maritoStatusDTO";
    public static final String COL_MAR_MARITALSTATUS = "maritalStatus";
    public static final String COL_MAR_TYPEMARITALSTATUS = "typeMaritalStatus";
    //Table Employeetype
    public static final String TABLE_EMPLOYEETYPE = "EmployeeType";
    public static final String COL_EMPTYPE_IDEMPLOYEETYPE ="idEmployeeType";
    public static final String COL_EMPTYPE_NAMEEMPLOYEETYPE ="nameEmployeeType";
    //Table Role
    public static final String TABLE_ROLE= "Role";
    public static final String COL_ROLE_IDROLE = "idRole";
    public static final String COL_ROLE_NAMEROLE = "nameRole";
    //Table Team
    public static final String TABLE_TEAM= "Team";
    public static final String COL_TEAM_IDTEAM = "idTeam";
    public static final String COL_TEAM_NAMETEAM = "nameTeam";
    //Table Permission
    public static final String TABLE_PERMISSION = "permission";
    public static final String COL_PER_IDPERMISSION = "idPermission";
    public static final String COL_PER_NAMEPERMISSION = "namePermission";
    //Table link employee with permission
    public static final String TABLE_LINK_EMP_PER= "LINK_EMP_PER";
    public static final String COL_EMP_PER_IDEMP = "idEmployee";
    public static final String COL_EMP_PER_IDPER = "idPermission";
    //Table link employee with team
    public static final String TABLE_LINK_EMP_TEAM= "LINK_EMP_TEAM";
    public static final String COL_EMP_TEAM_IDEMP = "idEmployee";
    public static final String COL_EMP_TEAM_IDTEAM = "idTeam";
    //Create table
    //Employee
    public static final String CREATE_TABLE_EMPLOYEE = "Create table " + TABLE_EMPLOYEE + "("
            +COL_EMP_IDEMPLOYEE+" INTEGER PRIMARY KEY,"
            +COL_EMP_email+" text,"
            +COL_EMP_NAME+ " text,"
            +COL_EMP_BIRTHDAY+ " text,"
            +COL_EMP_GENDER+ " integer,"
            +COL_EMP_MOBILE+ " text,"
            +COL_EMP_ADDRESS+ " text,"
            +COL_EMP_MARITALSTATUS+ " integer,"
            +COL_EMP_STARTWORKDAY+ " text,"
            +COL_EMP_ENDWORKDAY+ " text,"
            +COL_EMP_CURRICULUM_VITAE+ " text,"
            +COL_EMP_COMPANY+ " text,"
            +COL_EMP_AVATAR+ " text,"
            +COL_EMP_IDEMPLOYEETYPE+ " integer,"
            +COL_EMP_IDROLE+ " integer,"
            +COL_EMP_ISMANAGER+ " integer,"
            +COL_EMP_SALARYID+ " integer,"
            +COL_EMP_UPDATEDAT+ " text,"
            +COL_EMP_CREATEDAT+ " text,"
            +COL_EMP_EMPLOYEE+ " text,"
            +"FOREIGN KEY ("+COL_EMP_GENDER+") REFERENCES "+TABLE_GENDERDTO+"("+COL_GEN_GENDER+"),"
            +"FOREIGN KEY ("+COL_EMP_MARITALSTATUS+") REFERENCES "+TABLE_MARITALSTATUSDTO+"("+COL_MAR_MARITALSTATUS+"),"
            +"FOREIGN KEY ("+COL_EMP_IDROLE+") REFERENCES "+TABLE_ROLE+"("+COL_ROLE_IDROLE+"))";
    //genderDTO
    public static final String CREATE_TABLE_GENDERDTO = "Create table "+ TABLE_GENDERDTO+"(" +
            COL_GEN_GENDER+" INTEGER PRIMARY KEY,"+
            COL_GEN_NAMEGENDER + " TEXT)";
    //maritalStatusDTO
    public static final String CREATE_TABLE_MARITALSTATUSDTO = "Create table "+ TABLE_MARITALSTATUSDTO+"(" +
            COL_MAR_MARITALSTATUS+" INTEGER PRIMARY KEY,"+
            COL_MAR_TYPEMARITALSTATUS + " TEXT)";
    //employeeType
    public static final String CREATE_TABLE_EMPLOYEETYPE = "Create table "+ TABLE_EMPLOYEETYPE+"(" +
            COL_EMPTYPE_IDEMPLOYEETYPE+" INTEGER PRIMARY KEY,"+
            COL_EMPTYPE_NAMEEMPLOYEETYPE + " TEXT)";
    //ROLE
    public static final String CREATE_TABLE_ROLE = "Create table "+ TABLE_ROLE+"(" +
            COL_ROLE_IDROLE+" INTEGER PRIMARY KEY,"+
            COL_ROLE_NAMEROLE + " TEXT)";
    //TEAM
    public static final String CREATE_TABLE_TEAM = "Create table "+ TABLE_TEAM+"(" +
            COL_TEAM_IDTEAM+" INTEGER PRIMARY KEY,"+
            COL_TEAM_NAMETEAM + " TEXT)";
    //permission
    public static final String CREATE_TABLE_PERMISSION = "Create table "+ TABLE_PERMISSION+"(" +
            COL_PER_IDPERMISSION+" INTEGER PRIMARY KEY,"+
            COL_PER_NAMEPERMISSION + " TEXT)";
    //TABLE_LINK_EMP_PER
    public static final String CREATE_TABLE_LINK_EMP_PER = "Create table "+ TABLE_LINK_EMP_PER+"(" +
            COL_EMP_PER_IDEMP+" INTEGER ,"+
            COL_EMP_PER_IDPER + "INTEGER, " +
            "FOREIGN KEY ("+COL_EMP_PER_IDEMP+") REFERENCES "+TABLE_EMPLOYEE+"("+COL_EMP_IDEMPLOYEE+")," +
            "FOREIGN KEY ("+COL_EMP_PER_IDPER+") REFERENCES "+TABLE_PERMISSION+"("+COL_PER_IDPERMISSION+")," +
            "PRIMARY KEY("+COL_EMP_PER_IDPER+","+COL_EMP_PER_IDEMP+"))";
    //TABLE_LINK_EMP_TEAM
    public static final String CREATE_TABLE_LINK_EMP_TEAM = "Create table "+ TABLE_LINK_EMP_TEAM+"(" +
            COL_EMP_TEAM_IDEMP+" INTEGER ,"+
            COL_EMP_TEAM_IDTEAM + "INTEGER, " +
            "FOREIGN KEY ("+COL_EMP_TEAM_IDEMP+") REFERENCES "+TABLE_EMPLOYEE+"("+COL_EMP_IDEMPLOYEE+")," +
            "FOREIGN KEY ("+COL_EMP_TEAM_IDTEAM+") REFERENCES "+TABLE_TEAM+"("+COL_TEAM_IDTEAM+")," +
            "PRIMARY KEY("+COL_EMP_TEAM_IDTEAM+","+COL_EMP_PER_IDEMP+"))";

    public SqliteCommand(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_GENDERDTO);
        sqLiteDatabase.execSQL(CREATE_TABLE_MARITALSTATUSDTO);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEETYPE);
        sqLiteDatabase.execSQL(CREATE_TABLE_ROLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TEAM);
        sqLiteDatabase.execSQL(CREATE_TABLE_PERMISSION);
        sqLiteDatabase.execSQL(CREATE_TABLE_EMPLOYEE);
        sqLiteDatabase.execSQL(CREATE_TABLE_LINK_EMP_PER);
        sqLiteDatabase.execSQL(CREATE_TABLE_LINK_EMP_TEAM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_GENDERDTO + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_MARITALSTATUSDTO + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_EMPLOYEETYPE + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_ROLE + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_TEAM + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_PERMISSION + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_EMPLOYEE + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_LINK_EMP_PER + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_LINK_EMP_TEAM + "'");
        onCreate(sqLiteDatabase);

    }
}
