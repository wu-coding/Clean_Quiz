package com.example.clean_quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_quiz.databinding.QuizFragmentBinding
import com.example.quiz.QuizViewAdapter

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }
// LinearLayoutManager(Context context)

    private lateinit var viewModel: QuizViewModel
    private lateinit var recyclerView:RecyclerView
    private lateinit var binding:QuizFragmentBinding
    lateinit var displayQuestion:TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = QuizFragmentBinding.inflate(inflater)
        return binding.root
    }

    // depreceated method -> should move to oncreateview
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Replace with view-binding later
        displayQuestion = binding.questionText

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        viewModel.apply {
            loadAnswers()
            loadQuestion(displayQuestion)
        }
        recyclerView = view?.findViewById(R.id.quiz_recyclerView) ?: throw Exception("recyclerview findview error")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = QuizViewAdapter (viewModel.currentQuestion, { answerCheck: Int -> viewModel.checkAnswer(answerCheck) })
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner,  {
            recyclerView.adapter?.notifyDataSetChanged()
            viewModel.loadQuestion(displayQuestion)
            // Navigation
        })
    }

}

