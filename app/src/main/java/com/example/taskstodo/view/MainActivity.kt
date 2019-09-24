package com.example.taskstodo.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskstodo.R
import com.example.taskstodo.model.Task
import com.example.taskstodo.utils.*
import com.example.taskstodo.viewModel.TaskViewModel

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: TaskListAdapter
    private var mTasks: List<Task> = mutableListOf<Task>()
    private lateinit var mTaskViewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mRecyclerView = findViewById(R.id.recyclerView)
        mAdapter = TaskListAdapter(this)
        mAdapter.setTasks(mTasks)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

       // mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        mTaskViewModel.getAllTasks().observe(this, Observer { tasks ->
            tasks?.let{
                mAdapter.setTasks(it)
            }
        })

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewTaskActivity::class.java
            )
            startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_SAVE){
            data?.let {
                val task = Task(it.getStringExtra(EXTRA_KEY_TITLE), it.getStringExtra(
                    EXTRA_KEY_DESCRIPTION), it.getStringExtra(EXTRA_KEY_DATE))
                mTaskViewModel.insertTask(task)
            }
        } else if(requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_SAVE){
            Toast.makeText(this, getString(R.string.empty_task_not_saved), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
