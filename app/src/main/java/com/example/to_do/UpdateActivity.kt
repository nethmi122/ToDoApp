package com.example.to_do

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.to_do.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TasksDatabaseHelper
    private var taskId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updatetitleEditText.setText(task.title)
        binding.updatecontentEditText.setText(task.content)

        binding.updatesaveButton.setOnClickListener {
            val newTitle = binding.updatetitleEditText.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updatedTask = Task(taskId, newTitle, newContent)
            db.updateTask(updatedTask)
            finish()
            Toast.makeText(this, "Changes saved", Toast.LENGTH_SHORT).show()
        }

    }
}