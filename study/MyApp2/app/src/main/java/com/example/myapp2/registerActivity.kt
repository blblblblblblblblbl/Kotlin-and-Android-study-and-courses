package com.example.myapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapp2.databinding.ActivityAvatarBinding
import com.example.myapp2.databinding.ActivityRegisterBinding

class registerActivity : AppCompatActivity() {
    lateinit var binding:ActivityRegisterBinding;
    private var launcher: ActivityResultLauncher<Intent>? = null
    var index:Int = 0;
    var avatarsLength:Int = 4;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.imageView2.setImageResource(this.resources.getIdentifier("avatar${index}","drawable",this.packageName))

        launcher=registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
                result:ActivityResult->
            if(result.resultCode == RESULT_OK)
            {
                //val text = result.data?.getShortExtra("img").toString()
                index = result.data?.getStringExtra("img")?.replace("avatar","")?.toInt() ?: 0
                binding.imageView2.setImageResource(this.resources.getIdentifier(result.data?.getStringExtra("img"),"drawable",this.packageName))

            }
        }
    }
    fun onBackClick(view: View)
    {
        finish();
    }
    fun onNextClick(view: View)
    {
        if (index==avatarsLength-1)
        {
            index=0;
        }
        else
        {
            index++;
        }
        setImage("avatar${index}")
    }
    fun onPrevClick(view:View)
    {
        if (index==0)
        {
            index=avatarsLength-1;
        }
        else
        {
            index--;
        }
        setImage("avatar${index}")
    }
    fun onRegisterClick(view: View)
    {
        Info.persons.add(Person(binding.editName2.text.toString(),binding.editPassword2.text.toString(),"avatar${index}"))
        binding.textView3.text = "${binding.editName2.text.toString()} has been registered"
    }
    fun setImage(name:String)
    {
        binding.imageView2.setImageResource(this.resources.getIdentifier(name,"drawable",this.packageName))

    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==100 && data!=null&& resultCode== RESULT_OK )
        {
            //var str: String? = data.getStringExtra("img")
            index = data.getStringExtra("img")?.replace("avatar","")?.toInt() ?: 0
            binding.imageView2.setImageResource(this.resources.getIdentifier(data.getStringExtra("img"),"drawable",this.packageName))
        }
    }*/
    fun menuOnClick(view: View)
    {
        //var intent:Intent = Intent(this,AvatarActivity::class.java)
        //startActivityForResult(intent,100)

        launcher?.launch(Intent(this,AvatarActivity::class.java))
    }
}