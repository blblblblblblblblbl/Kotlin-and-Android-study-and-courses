package com.blblblbl.quiz

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.blblblbl.quiz.databinding.QuizQuestionBinding

class QuizQuestion
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr:Int = 0
): FrameLayout(context,attrs,defStyleAttr)
{
    private val binding = QuizQuestionBinding.inflate(LayoutInflater.from(context))
    init {
        val typedArray:TypedArray = context.obtainStyledAttributes(attrs,R.styleable.QuizQuestion)
        binding.tvQuestion.text = typedArray.getText(R.styleable.QuizQuestion_questionText)
        binding.bV1.text = typedArray.getText(R.styleable.QuizQuestion_firstVariantText)
        binding.bV2.text = typedArray.getText(R.styleable.QuizQuestion_secondVariantText)
        typedArray.recycle()
        addView(binding.root)
    }
    fun getAnswer():Int{
        return binding.tGroup.checkedButtonId-binding.bV1.id+1
    }
}