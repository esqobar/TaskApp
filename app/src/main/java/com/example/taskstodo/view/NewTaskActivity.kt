package com.example.taskstodo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import com.example.taskstodo.R
import com.example.taskstodo.utils.*
import com.google.android.material.textfield.TextInputEditText

class NewTaskActivity : AppCompatActivity() {

    private lateinit var mTitleInputEditText: TextInputEditText
    private lateinit var mDescriptionInputEditText: TextInputEditText
    private lateinit var mDateInputEditText: TextInputEditText

    private lateinit var mSaveBtn: Button
    private lateinit var mDeleteBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        mTitleInputEditText = findViewById(R.id.titleEditTxt)
        mDescriptionInputEditText = findViewById(R.id.descriptionEditTxt)
        mDateInputEditText= findViewById(R.id.dateEditTxt)

        mSaveBtn = findViewById(R.id.saveBtn)
        mDeleteBtn= findViewById(R.id.deleteBtn)

        mSaveBtn.setOnClickListener {
            val intent = Intent()
            if (TextUtils.isEmpty(mTitleInputEditText.text) || TextUtils.isEmpty(mDescriptionInputEditText.text) || TextUtils.isEmpty(mDateInputEditText.text)){
                setResult(RESULT_ERROR, intent)
            } else {
                intent.putExtra(EXTRA_KEY_TITLE, mTitleInputEditText.text.toString() )
                intent.putExtra(EXTRA_KEY_DESCRIPTION, mDescriptionInputEditText.text.toString() )
                intent.putExtra(EXTRA_KEY_DATE, mDateInputEditText.text.toString() )
                setResult(RESULT_SAVE, intent)
            }
            finish()
        }
        mDeleteBtn.setOnClickListener {

        }
    }
}
