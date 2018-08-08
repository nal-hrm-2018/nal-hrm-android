package com.dal.hrm_management.views.profile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_IMAGE = 1001;
    private ImageView imv_avatar;
    private EditText edt_name;
    private Spinner spn_position;
    private EditText edt_email;
    private EditText edt_address;
    private EditText edt_phone;
    private RadioButton rb_male,rb_female;
    private Spinner spn_maritalStatus;
    private TextView tv_birthday;
    private Spinner spn_type;
    private TextView tv_start;
    private TextView tv_end;
    private ProgressBar progressBar;
    Bitmap avatarBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        displayBackHome();
        initUI();
        initData();
        setEvent();
        getData();
    }

    private void initData() {
        //TODO: check role
        ArrayAdapter<CharSequence> adapter_position = ArrayAdapter.createFromResource(this,
                R.array.position_arr, android.R.layout.simple_spinner_item);
        adapter_position.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_position.setAdapter(adapter_position);

        ArrayAdapter<CharSequence> adapter_maritalStatus = ArrayAdapter.createFromResource(this,
                R.array.marital_status_arr, android.R.layout.simple_spinner_item);
        adapter_maritalStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_maritalStatus.setAdapter(adapter_maritalStatus);

        ArrayAdapter<CharSequence> adapter_type = ArrayAdapter.createFromResource(this, R.array.type_arr, android.R.layout.simple_spinner_item);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_type.setAdapter(adapter_type);


    }

    private void getData() {
    }

    private void setEvent() {
        imv_avatar.setOnClickListener(this);
        tv_birthday.setOnClickListener(this);
        //TODO: CHECK ROLE : DEV/PO: can't edit tv_start, tv_end, only HR have this permission
        tv_start.setOnClickListener(this);
       // tv_end.setOnClickListener(this);
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
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
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
                Toast.makeText(getApplicationContext(),"Saving profile!!!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_refresh:
               // todo: refresh data
                refreshDataOnView();
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshDataOnView() {
        Toast.makeText(getApplicationContext(),"Refresh profile!!!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imv_avatar:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureDirectoryPath = pictureDirectory.getPath();
                Uri data = Uri.parse(pictureDirectoryPath);
                photoPickerIntent.setDataAndType(data,"image/*");
                startActivityForResult(photoPickerIntent,REQUEST_IMAGE);
                break;
            case R.id.tv_birthday:
                showDatePicker(v,v.getId());
                break;
            case R.id.tv_start:
                showDatePicker(v,v.getId());
                break;
            case R.id.tv_end:
                showDatePicker(v,v.getId());
                break;
        }
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
        },year,month,date);
        datePickerDialog.show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode == REQUEST_IMAGE){
                Uri imageUri = data.getData();
                InputStream inputStream;
                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap imageAvatar = BitmapFactory.decodeStream(inputStream);
                    imv_avatar.setImageBitmap(imageAvatar);
                    avatarBitmap = imageAvatar;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Image not found",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
