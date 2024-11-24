package com.example.bws_design.Dashboard.ui.history.tabs

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bws_design.R
import com.example.bws_design.databinding.ActivityAddWasteBinding
class AddWasteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddWasteBinding
    // Access AppBar elements

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val selectedImageUri = data.data
                    binding.imageInput.setImageURI(selectedImageUri)
                }
            }
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                if (imageBitmap != null) {
                    binding.imageInput.setImageBitmap(imageBitmap)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddWasteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.appbar)
        val toolbarTitle = findViewById<TextView>(R.id.app_title)
        toolbarTitle.text = "Waste & get Point"
        toolbar.setNavigationOnClickListener {
            finish()
        }
        // Initialize Spinner and Adapter
        val spinner = binding.wasteTypeInput
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.waste_types,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set Spinner item selection listener
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedWasteType = parent?.getItemAtPosition(position).toString()
                // Do something with the selected waste type
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected
            }
        }

        // Set imageInput click listener
        binding.imageInput.setOnClickListener {
            showImagePickerDialog()
        }
    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Choose from Gallery", "Take a Photo")
        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Select Image Source")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        val pickImageIntent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        pickImageLauncher.launch(pickImageIntent)
                    }
                    1 -> {
                        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        cameraLauncher.launch(takePictureIntent)
                    }
                }
            }
        builder.create().show()
    }
}