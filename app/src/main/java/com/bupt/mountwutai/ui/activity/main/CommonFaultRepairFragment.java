package com.bupt.mountwutai.ui.activity.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bupt.mountwutai.R;
import com.bupt.mountwutai.base.BaseFragment;
import com.bupt.mylibrary.utils.ViewUtils;


public class CommonFaultRepairFragment extends BaseFragment {

    EditText nameEdit;
    EditText addEdit;
    EditText phoneEdit;
    EditText appearanceEdit;
    Spinner spinner;
    Button sub;
    private int faultType;
    private String name;
    private String phone;
    private String add;
    private String appearance;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_changgui);
        initViews();
    }

    @Override
    protected boolean hasPopWindow() {
        return false;
    }

    @Override
    protected boolean isNeedInitBack() {
        return false;
    }

    @Override
    protected String getTopbarTitle() {
        return null;
    }

    private void initViews() {
        nameEdit = (EditText) findViewById(R.id.bao_name);
        phoneEdit = (EditText) findViewById(R.id.bao_phone);
        addEdit = (EditText) findViewById(R.id.bao_add);
        appearanceEdit = (EditText) findViewById(R.id.bao_appearance);
        spinner = (Spinner) findViewById(R.id.bao_type);
        sub = (Button) findViewById(R.id.bao_sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, R.layout.item2);
        adapter.add("+点击选择故障类型");
        adapter.add("电视业务故障");
        adapter.add("宽带业务故障");
        adapter.add("双向业务故障");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                faultType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void submit() {
        name = nameEdit.getText().toString().trim();
        add = addEdit.getText().toString().trim();
        phone = phoneEdit.getText().toString().trim();
        appearance = appearanceEdit.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("请填写手机号");
            return;
        }
        if (!ViewUtils.isMobileNO(phone)) {
            showToast("手机号格式不正确");
            return;
        }
        if (TextUtils.isEmpty(add)) {
            showToast("请填写地址");
            return;
        }

        if (faultType == 0) {
            showToast("请选择故障类型");
            return;
        }
        if (TextUtils.isEmpty(appearance)) {
            showToast("请填写故障现象");
            return;
        }
        showToast("提交成功");
    }
}
