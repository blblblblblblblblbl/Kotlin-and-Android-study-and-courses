package com.market.pomodorotimer.mvvm.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import com.market.pomodorotimer.R
import com.market.pomodorotimer.databinding.FragmentTimeSelectorBinding
import com.market.pomodorotimer.mvvm.viewmodels.MainViewModel
import com.market.pomodorotimer.mvvm.viewmodels.SharedCode

class TimeSelector : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var binding:FragmentTimeSelectorBinding

    var workTime:Int = 0
    var relaxTime: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeSelectorBinding.inflate(layoutInflater)
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            numPickerHour.minValue = 0
            numPickerHour.maxValue = 23
            numPickerMin.minValue = 0
            numPickerMin.maxValue = 59
            numPickerSec.minValue = 0
            numPickerSec.maxValue = 59

            bSelect.setOnClickListener(){selectOnClick(it)}
            bConfigOk.setOnClickListener(){okOnClick(it)}
            if (mainViewModel.timeWork.value==null || mainViewModel.timeRelax.value==null)
            {
                bConfigCancel.visibility = View.INVISIBLE
            }
            bConfigCancel.setOnClickListener(){cancelOnClick(it)}
        }
    }

    fun selectOnClick(view: View)
    {
        binding.apply {
            val time: Int =
                (numPickerHour.value * 60 + numPickerMin.value) * 60 + numPickerSec.value
            mainViewModel.timeToAct.value = time

            when (ToggleGroupTimer.checkedButtonId) {
                bToggleWork.id -> {
                    workTime = time
                    Toast.makeText(context, "work time set", Toast.LENGTH_SHORT).show()
                }
                bToggleRelax.id ->{
                    relaxTime = time
                    Toast.makeText(context, "relax time set", Toast.LENGTH_SHORT).show()
                }
            }
            activity!!.findViewById<TextView>(R.id.timeTV).text = SharedCode.getTimeStringFromDouble(time.toDouble())
        }
    }

    fun okOnClick(view: View)
    {
        if (workTime==0 || relaxTime==0)
        {
            if (relaxTime==0 && workTime ==0) Toast.makeText(context, "set work and relax time!", Toast.LENGTH_SHORT).show()
            else
            {
                if (relaxTime==0)  Toast.makeText(context, "set relax time!", Toast.LENGTH_SHORT).show()
                if (workTime == 0) Toast.makeText(context, "set work time!", Toast.LENGTH_SHORT).show()
            }
        }
        else
        {
            mainViewModel.timeWork.value = workTime
            mainViewModel.timeRelax.value = relaxTime
            closeFragment()
        }
    }

    fun cancelOnClick(view: View)
    {
        closeFragment()
    }

    private fun closeFragment()
    {
        val manager = requireActivity().supportFragmentManager
        binding.configLinearLayout.visibility = View.GONE
        activity!!.findViewById<ConstraintLayout>(R.id.centr).visibility = View.VISIBLE
        manager.beginTransaction().remove(this).commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = TimeSelector()
    }
}