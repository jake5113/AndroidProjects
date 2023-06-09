package com.mrhi2023.ex99jetpacklivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.mrhi2023.ex99jetpacklivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.vm= MyViewModel()

        val vm:MyViewModel= MyViewModel()
        binding.vm= vm

        //뷰모델 안에 있는 LiveData 변수를 관찰하는 설정
        vm.name.observe(this){
            Toast.makeText(this, "데이터변경 감지 - $it", Toast.LENGTH_SHORT).show()
            binding.tv.text= it
        }
//        vm.name.observe(this, object : Observer<String>{
//            override fun onChanged(t: String?) {
//                //이렇게 objec 익명객체를 사용하는 것은 너무 길어..짜증
//            }
//        })

        // 자동 갱신 2번째 방법 [대부분 이방식]
        binding.lifecycleOwner= this
    }
}