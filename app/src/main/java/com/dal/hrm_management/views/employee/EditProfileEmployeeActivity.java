package com.dal.hrm_management.views.employee;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.Team;
import com.dal.hrm_management.presenters.profile.ProfileEditPresenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EditProfileEmployeeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE = 1001;
    private ImageView imv_avatar;
    private EditText edt_name;
    private Spinner spn_position;
    private EditText edt_email;
    private EditText edt_address;
    private EditText edt_phone;
    private RadioButton rb_male, rb_female;
    private Spinner spn_maritalStatus;
    private TextView tv_birthday;
    private Spinner spn_type;
    private TextView tv_team;
    private TextView tv_start;
    private TextView tv_end;
    private ProgressBar progressBar;
    Bitmap avatarBitmap;
    ArrayAdapter<CharSequence> adapter_position, adapter_maritalStatus, adapter_type;
    ArrayList<String> teamList;
    private boolean[] mSelectedTeams, mSelectedTeamsBackup;
    private ProfileEditPresenter profileEditPresenter;
    public static String position = "", maritalStatus = "", type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_employee);
        displayBackHome();
        initPresenter();
        getDataFromServer();
        initUI();
        initData();
        setEvent();
    }

    private void getDataFromServer() {

    }


    private void initPresenter() {

    }

    private void initData() {
        adapter_position = ArrayAdapter.createFromResource(this, R.array.position_arr, android.R.layout.simple_spinner_item);
        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_position.setAdapter(adapter_position);

        adapter_maritalStatus = ArrayAdapter.createFromResource(this, R.array.marital_status_arr, android.R.layout.simple_spinner_item);
        adapter_maritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_maritalStatus.setAdapter(adapter_maritalStatus);

        adapter_type = ArrayAdapter.createFromResource(this, R.array.type_arr, android.R.layout.simple_spinner_item);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_type.setAdapter(adapter_type);

    }

    private void setEvent() {
        imv_avatar.setOnClickListener(this);
        tv_birthday.setOnClickListener(this);
        tv_team.setOnClickListener(this);
        tv_start.setOnClickListener(this);
        tv_end.setOnClickListener(this);
    }

    private void initUI() {
        imv_avatar = (ImageView) findViewById(R.id.imv_avatar);
        edt_name = (EditText) findViewById(R.id.edt_name);
        spn_position = (Spinner) findViewById(R.id.spn_position);
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_address = (EditText) findViewById(R.id.edt_address);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        spn_maritalStatus = (Spinner) findViewById(R.id.spn_maritalStatus);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        spn_type = (Spinner) findViewById(R.id.spn_type);
        tv_team = (TextView) findViewById(R.id.tv_team);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        teamList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.team_arr)));
        mSelectedTeams = new boolean[teamList.size()];
        mSelectedTeamsBackup = new boolean[teamList.size()];
    }

    private void displayBackHome() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_edit_profile_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_profile_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                Toast.makeText(getApplicationContext(), "Saving profile!!!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_refresh:
                // todo: refresh data
                refreshDataOnView();
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshDataOnView() {
        Toast.makeText(getApplicationContext(), "Refresh profile!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_avatar:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri data = Uri.parse(pictureDirectoryPath);
                photoPickerIntent.setDataAndType(data, "image/*");
                startActivityForResult(photoPickerIntent, REQUEST_IMAGE);
                break;
            case R.id.tv_birthday:
                showDatePicker(v, v.getId());
                break;
            case R.id.tv_team:
                showCheckBoxDialog();
                break;
            case R.id.tv_start:
                showDatePicker(v, v.getId());
                break;
            case R.id.tv_end:
                showDatePicker(v, v.getId());
                break;
        }
    }

    private void showCheckBoxDialog() {
        tv_team.setText("");
        for (int k = 0; k < mSelectedTeams.length; k++) {
            mSelectedTeamsBackup[k] = mSelectedTeams[k];
        }
        String[] teams = getResources().getStringArray(R.array.team_arr);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_title_checkbox, null);

        TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
        final CheckBox cbAll = (CheckBox) layout.findViewById(R.id.cbSelectAll);

        tvTitle.setText("Select Teams");
        cbAll.setChecked(isCheckAllTeams());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCustomTitle(layout);
        builder.setMultiChoiceItems(teams, mSelectedTeams, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                cbAll.setChecked(isCheckAllTeams());
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<String> list = new ArrayList<>();
                for (int k = 0; k < mSelectedTeams.length; k++) {
                    if (mSelectedTeams[k]) {
                        list.add(teamList.get(k));
                    }
                }
                if (list.size() != 0) {
                    for (int i = 0; i < list.size() - 1; i++) {
                        tv_team.setText(tv_team.getText() + list.get(i) + ", ");
                    }
                    tv_team.setText(tv_team.getText() + list.get(list.size() - 1));
                }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int k = 0; k < mSelectedTeams.length; k++) {
                    mSelectedTeams[k] = mSelectedTeamsBackup[k];
                }
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.show();

        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = cbAll.isChecked();
                ListView listView = dialog.getListView();
                for (int i = 0; i < listView.getCount(); i++) {
                    listView.setItemChecked(i, isChecked);
                    mSelectedTeams[i] = isChecked;
                }
            }
        });
    }

    private boolean isCheckAllTeams() {
        for (int i = 0; i < mSelectedTeams.length; i++) {
            if (!mSelectedTeams[i]) return false;
        }
        return true;
    }


    private void showDatePicker(View view, final int id) {
        Calendar calendar = Calendar.getInstance();
        final int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                if (id == R.id.tv_birthday) {
                    tv_birthday.setText(dayOfMonth + "/" + month + "/" + year);
                }
                if (id == R.id.tv_start) {
                    tv_start.setText(dayOfMonth + "/" + month + "/" + year);
                }
                if (id == R.id.tv_end) {
                    tv_end.setText(dayOfMonth + "/" + month + "/" + year);
                }
            }
        }, year, month, date);
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE) {
                Uri imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap imageAvatar = BitmapFactory.decodeStream(inputStream);
                    imv_avatar.setImageBitmap(imageAvatar);
                    avatarBitmap = imageAvatar;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Image not found", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void loadDataToView(Profile profile) {
        position = profile.getRole().getNameRole();
        maritalStatus = profile.getMaritalStatusDTO().getTypeMaritalStatus();
        type = profile.getEmployeeType().getNameEmployeeType();
        edt_name.setText(profile.getNameEmployee());
        edt_email.setText(profile.getEmail());
        edt_address.setText(profile.getAddress());
        edt_phone.setText(profile.getMobile());
        if (profile.getGenderDTO().getGender() == 1) {
            rb_female.setChecked(true);
        } else {
            rb_male.setChecked(true);
        }
        List<Team> teamList = profile.getTeams();
        for (int i = 0; i < teamList.size() - 1; i++) {
            tv_team.setText(tv_team.getText() + teamList.get(i).getNameTeam() + ", ");
        }
        tv_team.setText(tv_team.getText() + teamList.get(teamList.size() - 1).getNameTeam());
        tv_birthday.setText(profile.getBirthday());
        tv_start.setText(profile.getStartWorkDate());
        tv_end.setText(profile.getEndWorkDate());
        if (!position.equals("")) {
            spn_position.setSelection(adapter_position.getPosition(position));
        }
        if (!maritalStatus.equals("")) {
            spn_maritalStatus.setSelection(profile.getMaritalStatusDTO().getMaritalStatus() - 1);
        }
        if (!type.equals("")) {
            spn_type.setSelection(profile.getEmployeeType().getIdEmployeeType() - 1);
        }
        progressBar.setVisibility(View.GONE);
    }

}
