package com.example.capsule.ui.quiz

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capsule.R
import com.example.capsule.data_model.Mcq
import com.example.capsule.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))[QuizViewModel::class.java]

        if (viewModel.isQuizFinished){
            showResult()
        }

        binding.questionNo.text = "Question ${viewModel.qNo}/5"

        setQuestion(Mcq.questionList[viewModel.qNo-1])
        selectOption()

        if (viewModel.isAnswered){
            showCorrectAnswer()
        }

        binding.next.setOnClickListener {
            if (viewModel.optionSelected == Mcq.questionList[viewModel.qNo-1].answer){
                viewModel.marks++
            }
            if (viewModel.optionSelected != 0 && Mcq.questionList.size > viewModel.qNo){
                deselectOptions()
                viewModel.isAnswered = false
                viewModel.qNo++
                viewModel.optionSelected = 0
                binding.questionNo.text = "Question ${viewModel.qNo}/5"
                binding.progressBar.progress = viewModel.qNo * 20
                setQuestion(Mcq.questionList[viewModel.qNo-1])
            }
            else if (viewModel.optionSelected == 0){
                //Do nothing
                Toast.makeText(requireContext(), "Select an option", Toast.LENGTH_SHORT).show()
            }
            else{
                openResultDialog()
            }
        }

        binding.checkAnswer.setOnClickListener {
            if (viewModel.optionSelected != 0){
                viewModel.isAnswered = true
                showCorrectAnswer()
            }
        }

        binding.opt1.setOnClickListener {
            if (!viewModel.isAnswered){
                deselectOptions()
                viewModel.optionSelected = 1
                selectOption()
            }
        }

        binding.opt2.setOnClickListener {
            if (!viewModel.isAnswered){
                deselectOptions()
                viewModel.optionSelected = 2
                selectOption()
            }
        }
        binding.opt3.setOnClickListener {
            if (!viewModel.isAnswered){
                deselectOptions()
                viewModel.optionSelected = 3
                selectOption()
            }
        }
        binding.opt4.setOnClickListener {
            if (!viewModel.isAnswered){
                deselectOptions()
                viewModel.optionSelected = 4
                selectOption()
            }
        }

        binding.info.setOnClickListener {
            Toast.makeText(requireContext(), "Info button clicked", Toast.LENGTH_SHORT).show()
        }

        binding.bookmark.setOnClickListener {
            Toast.makeText(requireContext(), "Bookmark button clicked", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun showCorrectAnswer() {
        if (viewModel.optionSelected == Mcq.questionList[viewModel.qNo-1].answer){
            Toast.makeText(requireContext(), "Correct", Toast.LENGTH_SHORT).show()
            when(viewModel.optionSelected){
                1 -> binding.opt1.setBackgroundResource(R.drawable.correct_option_bg)
                2 -> binding.opt2.setBackgroundResource(R.drawable.correct_option_bg)
                3 -> binding.opt3.setBackgroundResource(R.drawable.correct_option_bg)
                4 -> binding.opt4.setBackgroundResource(R.drawable.correct_option_bg)
            }
        }
        else{
            Toast.makeText(requireContext(), "Incorrect", Toast.LENGTH_SHORT).show()
            when(Mcq.questionList[viewModel.qNo-1].answer){
                1 -> binding.opt1.setBackgroundResource(R.drawable.correct_option_bg)
                2 -> binding.opt2.setBackgroundResource(R.drawable.correct_option_bg)
                3 -> binding.opt3.setBackgroundResource(R.drawable.correct_option_bg)
                4 -> binding.opt4.setBackgroundResource(R.drawable.correct_option_bg)
            }
            when(viewModel.optionSelected){
                1 -> binding.opt1.setBackgroundResource(R.drawable.incorrect_option_bg)
                2 -> binding.opt2.setBackgroundResource(R.drawable.incorrect_option_bg)
                3 -> binding.opt3.setBackgroundResource(R.drawable.incorrect_option_bg)
                4 -> binding.opt4.setBackgroundResource(R.drawable.incorrect_option_bg)
            }
        }
    }

    private fun openResultDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.result_dialog)
        dialog.findViewById<TextView>(R.id.title).text = resources.getString(R.string.result)
        dialog.findViewById<TextView>(R.id.message).text = resources.getString(R.string.dialog_message)
        val yesButton = dialog.findViewById<Button>(R.id.button1)
        val noButton = dialog.findViewById<Button>(R.id.button2)
        yesButton.text = resources.getString(R.string.yes)
        noButton.text = resources.getString(R.string.no)

        yesButton.setOnClickListener {
            viewModel.isQuizFinished = true
            showResult()
            dialog.cancel()
        }

        noButton.setOnClickListener {
            dialog.cancel()
        }

        dialog.create()
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun selectOption(){
        when(viewModel.optionSelected){
            1 -> binding.opt1.setBackgroundResource(R.drawable.selected_option_bg)
            2 -> binding.opt2.setBackgroundResource(R.drawable.selected_option_bg)
            3 -> binding.opt3.setBackgroundResource(R.drawable.selected_option_bg)
            4 -> binding.opt4.setBackgroundResource(R.drawable.selected_option_bg)
        }
    }

    private fun deselectOptions(){
        binding.opt1.setBackgroundResource(R.drawable.option_bg)
        binding.opt2.setBackgroundResource(R.drawable.option_bg)
        binding.opt3.setBackgroundResource(R.drawable.option_bg)
        binding.opt4.setBackgroundResource(R.drawable.option_bg)
    }
    private fun setQuestion(mcq: Mcq) {
        binding.question.text = mcq.question
        binding.opt1.text = mcq.options[0]
        binding.opt2.text = mcq.options[1]
        binding.opt3.text = mcq.options[2]
        binding.opt4.text = mcq.options[3]
    }
    private fun showResult(){
        binding.quizScreen.visibility = View.GONE
        binding.resultScreen.visibility = View.VISIBLE
        binding.result.text = "${viewModel.marks}/5"
    }



    companion object {

        @JvmStatic
        fun newInstance() = QuizFragment()
    }
}